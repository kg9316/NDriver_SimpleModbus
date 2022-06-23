/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp.message;

import com.gangstad.simpleModbusTcp.comm.SimpleModbusTcpLinkMessage;
import com.tridium.ndriver.comm.*;
import com.tridium.ndriver.io.TypedInputStream;
import static com.gangstad.simpleModbusTcp.SimpleModbusHelper.byteArrayToHex;

/**
 * SimpleModbusTcpMessageFactory implementation of IMessageFactory.
 *
 * @author   Kato Gangstad
 * @creation 2.jun.2022 
 */
public class SimpleModbusTcpMessageFactory
  implements IMessageFactory
{
  public SimpleModbusTcpMessageFactory() {}
  
  public NMessage makeMessage(LinkMessage lm) 
      throws Exception
  {

    SimpleModbusTcpLinkMessage slm = (SimpleModbusTcpLinkMessage)lm;

    byte[] bytes = slm.getByteArray();
    byte[] buffer = new byte[slm.PDUlenght];
    System.arraycopy(bytes,0,buffer,0,slm.PDUlenght);

    //System.out.println("SimpleModbusTcpMessageFactory makeMessage "+byteArrayToHex(buffer));

    //TypedInputStream that will be passed to NMessage
    TypedInputStream ti = new TypedInputStream(buffer,0, buffer.length);

    //
    //convert linkMessage driver specific NMessage
    return new SimpleModbusResp(slm.transactionId,ti);
  }
}
