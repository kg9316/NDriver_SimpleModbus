/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp.point;

import javax.baja.sys.*;
import javax.baja.agent.AgentList;
import javax.baja.driver.point.BPointDeviceExt;
import com.tridium.ndriver.util.AgentInfoUtil;

import com.gangstad.simpleModbusTcp.*;
import javax.baja.nre.annotations.*;

/**
 * BSimpleModbusTcpPointDeviceExt is a container for simpleModbusTcp proxy points.
 *
 * @author   Kato Gangstad
 * @creation 2.jun.2022 
 */
@NiagaraType
public class BSimpleModbusTcpPointDeviceExt
  extends BPointDeviceExt 
{            


/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.gangstad.simpleModbusTcp.point.BSimpleModbusTcpPointDeviceExt(2979906276)1.0$ @*/
/* Generated Thu Jun 02 22:58:39 GMT+01:00 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSimpleModbusTcpPointDeviceExt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * Adds BNPointManager and renames.
   *
   * @see NAgentInfo.getAgentsHelp
   */
  public AgentList getAgents(Context cx)
  {
    AgentList agents = super.getAgents(cx);
    agents.add("ndriver:NPointManager");
    agents.add("ndriver:NPointUxManager");

    try {
      AgentInfoUtil.getAgentsHelp(
        agents,
        getType().getTypeInfo().getModuleName(),
        "ndriver:NPointUxManager",
        "PointUxManager",
        "Point Ux Manager"
      );
    }
    catch (TypeNotFoundException ex)
    {
      // OK if ndriver:NPointUxManager doesn't exist, ie ux profile not supported
    }

    return AgentInfoUtil.getAgentsHelp(
        agents,
        getType().getTypeInfo().getModuleName(),
        "ndriver:NPointManager",
        "PointManager",
        "Point Manager"
    );
  }

////////////////////////////////////////////////////////////////
// Access
////////////////////////////////////////////////////////////////
  
  /**
   * Get the network cast to a BSimpleModbusTcpNetwork.
   * @return network as a BSimpleModbusTcpNetwork.
   */
  public final BSimpleModbusTcpNetwork getSimpleModbusTcpNetwork()
  {
    return (BSimpleModbusTcpNetwork)getNetwork();
  }

  /**
   * Get the device cast to a BSimpleModbusTcpDevice.
   * @return device as a BSimpleModbusTcpDevice.
   */
  public final BSimpleModbusTcpDevice getSimpleModbusTcpDevice()
  {
    return (BSimpleModbusTcpDevice)getDevice();
  }

////////////////////////////////////////////////////////////////
// PointDeviceExt
////////////////////////////////////////////////////////////////
  
  /**
   * @return the Device type.
   */
  public Type getDeviceType()
  {
    return BSimpleModbusTcpDevice.TYPE;
  }

  /**
   * @return the PointFolder type.
   */
  public Type getPointFolderType()
  {
    return BSimpleModbusTcpPointFolder.TYPE;
  }
  
  /**
   * @return the ProxyExt type.
   */
  public Type getProxyExtType()
  {
    return BSimpleModbusTcpProxyExt.TYPE;
  }
  
}
