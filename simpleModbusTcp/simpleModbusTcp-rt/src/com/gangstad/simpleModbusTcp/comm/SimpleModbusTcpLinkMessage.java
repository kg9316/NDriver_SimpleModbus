/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp.comm;

import java.io.InputStream;
import java.io.OutputStream;

import com.gangstad.simpleModbusTcp.message.SimpleModbusTcpMessage;
import com.tridium.ndriver.comm.LinkMessage;
import com.tridium.ndriver.comm.NMessage;
import com.tridium.ndriver.io.TypedOutputStream;

import static com.gangstad.simpleModbusTcp.SimpleModbusHelper.*;


 /* The structure of a Modbus TCP message is:

    Transaction Id	  Protocol	  Length	Unit Address	Message
    2 Bytes	          2 Bytes	  2 Bytes	1 Byte	        N Bytes
    Where:

    The Transaction Id field identifies the transaction.
    The Protocol field is zero to indicate Modbus protocol.
    The Length field is the number of following bytes.
    The Unit Address field is the PLC Address encoded as single byte.
    The Message field is a Modbus PDU. The maximum length of the Message field is is 253 bytes.
    The maximum Modbus TCP message length is 257 bytes.
*/


/**
 * SimpleModbusTcpLinkMessage streams data to and from a byte array representation.
 *
 * @author   Kato Gangstad
 * @creation 2.jun.2022 
 */
public class SimpleModbusTcpLinkMessage
  extends LinkMessage
{
  public SimpleModbusTcpLinkMessage(int maxLen)  { super(maxLen); }
  
  /** 
   * Get byte data from inputStream.
   *  return true if complete message received 
   */
  public boolean receive(InputStream in) throws Exception
  {
    //First verify that this actual is a valid modbusTCP frame.

    OutputStream os = getOutputStream();

    byte[] APDU = new byte[7];

    if (!readFromInputStream(in,APDU)) return false;

    // get transactionId from received message, store it in public variable for validation later
    // Byte.toUnsignedInt....: bytes in java are signed... -128 to 127 we want unsigned 0 to 255

    transactionId = (Byte.toUnsignedInt(APDU[0])<<8)+Byte.toUnsignedInt(APDU[1]);
    int protocol = (APDU[2]<<8)+APDU[3];
    int len = ((Byte.toUnsignedInt(APDU[4])<<8)+Byte.toUnsignedInt(APDU[5]));

    // Check if this is a valid ModbusTcp frame
    if (protocol==0x00 && len>0) {
      int reminingByteToRead = len-1;
      byte[] PDU = new byte[reminingByteToRead];
      if (!readFromInputStream(in,PDU)) return false;

      //Store length of bytearray, we will use this later in MessageFactory
      PDUlenght = PDU.length;
      //Pass only the PDU portion of the message, APDU stuff was only for modbusTCP Transport
      //this way we can reuse the rest of the classes when we implement ModbusRTU
      os.write(PDU,0,PDU.length);

      //System.out.println("SimpleModbusTcpLinkMessage Received data on network and passed to MessageFactory");

      //Correct formatted message, pass it
      return true;
    }
    //Not a correct formatted message, reject it
    return false;
  }

////////////////////////////////////////////////////////////////
//Handle outgoing messages
////////////////////////////////////////////////////////////////

  @Override
  public boolean setMessage(NMessage msg)
          throws Exception
  {
    //cast passed NMessage to our type SimpleModbusTcpMessage
    SimpleModbusTcpMessage sms = (SimpleModbusTcpMessage) msg;
    //get the destination address of our original NMessage
    address = sms.getAddress();

    //translate the message to the output stream
    TypedOutputStream tos = new TypedOutputStream(256);
    sms.toOutputStream(tos);

    byte[] PDU = tos.toByteArray();

    OutputStream os = getOutputStream();

    //Start message with bytes for TransactionID and Protocol

    //TransactionID 2 bytes

    os.write(((int) sms.getTag()  >> 8 ) & 0xFF);
    os.write(((int) sms.getTag()) & 0xFF);

    //Protocol ID 2 bytes (always 0x0000 for ModbusTCP)
    os.write(0x00);
    os.write(0x00);

    //Data Length
    os.write(PDU.length >> 8 & 255);
    os.write(PDU.length & 255);

    //Pass inn Modbus PDU generated in SimpleModbusTcpCommand
    os.write(PDU);

    //Write to the network
    os.flush();

    //System.out.println("SimpleModbusTcpLinkMessage setMessage wrote data to network");

    //no fragmentation supported
    return false;
  }

    public int transactionId = 0;
    public int PDUlenght = 0;


}