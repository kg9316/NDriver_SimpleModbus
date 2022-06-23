/*
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp;

import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.nre.annotations.*;

import com.tridium.ndriver.BNDeviceFolder;

/**
 * BSimpleModbusTcpDeviceFolder is a folder for BSimpleModbusTcpDevice.
 *
 *  @author   Kato Gangstad
 *  @creation 2.jun.2022 
 */
@NiagaraType
public class BSimpleModbusTcpDeviceFolder
  extends BNDeviceFolder
{                       

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.gangstad.simpleModbusTcp.BSimpleModbusTcpDeviceFolder(2979906276)1.0$ @*/
/* Generated Thu Jun 02 22:58:38 GMT+01:00 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSimpleModbusTcpDeviceFolder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

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
   * @return true if parent is BSimpleModbusTcpNetwork or BSimpleModbusTcpDeviceFolder.
   */
  public boolean isParentLegal(BComponent parent)
  {
    return parent instanceof BSimpleModbusTcpNetwork ||
           parent instanceof BSimpleModbusTcpDeviceFolder;
  }


}
