/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp;

//import javax.baja.license.Feature;
import javax.baja.naming.BOrd;
import javax.baja.sys.*;
import javax.baja.util.Lexicon;
import javax.baja.nre.annotations.*;

import com.tridium.ndriver.BNNetwork;
import com.tridium.ndriver.comm.*;
import com.tridium.ndriver.datatypes.*;
import com.tridium.ndriver.discover.*;
import com.tridium.ndriver.poll.*;
import com.gangstad.simpleModbusTcp.comm.BSimpleModbusTcpTcpCommConfig;



/**
 *  BSimpleModbusTcpNetwork models a network of devices
 *
 *  @author   Kato Gangstad
 *  @creation 2.jun.2022 
 */
@NiagaraType
  @NiagaraProperty(name = "pollScheduler", type = "BNPollScheduler",  defaultValue = "new BNPollScheduler()")
  @NiagaraProperty(name = "tcpConfig", type = "BSimpleModbusTcpTcpCommConfig",  defaultValue = "new BSimpleModbusTcpTcpCommConfig()")

public class BSimpleModbusTcpNetwork 
  extends BNNetwork
{

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.gangstad.simpleModbusTcp.BSimpleModbusTcpNetwork(3424006957)1.0$ @*/
/* Generated Thu Jun 02 22:58:38 GMT+01:00 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "pollScheduler"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code pollScheduler} property.
   * @see #getPollScheduler
   * @see #setPollScheduler
   */
  public static final Property pollScheduler = newProperty(0, new BNPollScheduler(), null);
  
  /**
   * Get the {@code pollScheduler} property.
   * @see #pollScheduler
   */
  public BNPollScheduler getPollScheduler() { return (BNPollScheduler)get(pollScheduler); }
  
  /**
   * Set the {@code pollScheduler} property.
   * @see #pollScheduler
   */
  public void setPollScheduler(BNPollScheduler v) { set(pollScheduler, v, null); }

////////////////////////////////////////////////////////////////
// Property "tcpConfig"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code tcpConfig} property.
   * @see #getTcpConfig
   * @see #setTcpConfig
   */
  public static final Property tcpConfig = newProperty(0, new BSimpleModbusTcpTcpCommConfig(), null);
  
  /**
   * Get the {@code tcpConfig} property.
   * @see #tcpConfig
   */
  public BSimpleModbusTcpTcpCommConfig getTcpConfig() { return (BSimpleModbusTcpTcpCommConfig)get(tcpConfig); }
  
  /**
   * Set the {@code tcpConfig} property.
   * @see #tcpConfig
   */
  public void setTcpConfig(BSimpleModbusTcpTcpCommConfig v) { set(tcpConfig, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSimpleModbusTcpNetwork.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  
  
  
   

  /** Specify name for network resources. */
  public String getNetworkName() { return "SimpleModbusTcpNetwork"; }

  /** return device folder type  */
  @Override
  public Type getDeviceFolderType()
  {
    return BSimpleModbusTcpDeviceFolder.TYPE;
  }

  /** return device type */
  @Override
  public Type getDeviceType()
  {
    return BSimpleModbusTcpDevice.TYPE;
  }
  
  /** TODO - Add license check if needed
  @Override
  public final Feature getLicenseFeature()
  {
    return Sys.getLicenseManager().getFeature("?? vendor", "?? feature");
  }
  */
  
  @Override
  public void changed(Property p, Context cx)
  {
    super.changed(p, cx);
    if(!isRunning()) return;

    if(p == status)
    {
      // Give any comms opportunity to respond to status changes
      getTcpConfig().statusUpdate();
  
    }
  }
  

  
  
////////////////////////////////////////////////////////////////
//Utilities
////////////////////////////////////////////////////////////////
  
  
  /**Access the tcp comm stack */
  public NComm tcomm()
  {
    return (NComm)getTcpConfig().comm();
  }

  public static Lexicon LEX = Lexicon.make(BSimpleModbusTcpNetwork.class);

}
