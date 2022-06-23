/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp.comm;

import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.nre.annotations.*;

import com.tridium.ndriver.comm.IMessageFactory;
import com.tridium.ndriver.comm.LinkMessage;
import com.tridium.ndriver.comm.NLinkMessageFactory;
import com.tridium.ndriver.datatypes.BIpAddress;
import com.tridium.ndriver.datatypes.BTcpCommConfig;

import com.gangstad.simpleModbusTcp.message.SimpleModbusTcpMessageFactory;

/**
 * BSimpleModbusTcpTcpCommConfig Handles the tcp communication for the driver. 
 *
 * @author   Kato Gangstad
 * @creation 2.jun.2022 
 *
 */
@NiagaraType
public class BSimpleModbusTcpTcpCommConfig
  extends BTcpCommConfig
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.gangstad.simpleModbusTcp.comm.BSimpleModbusTcpTcpCommConfig(2979906276)1.0$ @*/
/* Generated Thu Jun 02 22:58:39 GMT+01:00 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSimpleModbusTcpTcpCommConfig.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /** Empty constructor  */
  public BSimpleModbusTcpTcpCommConfig() {  }
  
//  /** Override to configure the maximum number of request messages that can
//   *  be outstanding at the same time.  Default implementation returns 32. */
//  public int getMaxOutstandingTransactions() { return 1; }

  /** Provide custom LinkMessage factory.   */
  protected NLinkMessageFactory makeLinkMessageFactory()
  {    
    // link message factory must create
    return new NLinkMessageFactory(260)
      {
        protected LinkMessage createLinkMessage()
        {
          return new SimpleModbusTcpLinkMessage(1024); 
        }
      };
  }
  
  /** Provide custom Message factory.   */
  protected IMessageFactory makeMessageFactory()
  {
    return new SimpleModbusTcpMessageFactory();
  }
  
}
