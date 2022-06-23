/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package  com.gangstad.simpleModbusTcp.message;

import com.gangstad.simpleModbusTcp.constants.ModBusConstants;
import com.tridium.ndriver.comm.*;
//import com.tridium.ndriver.io.*;
import com.tridium.ndriver.datatypes.*;

/**
 *  SimpleModbusTcpMessage is super class for all simpleModbusTcp messages
 *
 *  @author   Kato Gangstad
 *  @creation 2.jun.2022 
 */
public class SimpleModbusTcpMessage
  extends NMessage implements ModBusConstants
{

  public SimpleModbusTcpMessage (int transactionIdentifier )
  {
    //super(add);
    this.transactionIdentifier = transactionIdentifier;
  }
  
  protected boolean isAck() { return false; }

  public Object getTag() { return transactionIdentifier;}

  public String toTraceString()
  {
    StringBuffer sb = new StringBuffer();
    toTraceString(sb);
    return sb.toString();
  }

  public void toTraceString(StringBuffer sb){}
  int transactionIdentifier = 0;



}
