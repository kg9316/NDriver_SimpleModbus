/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp.point;

import javax.baja.sys.*;
import javax.baja.driver.point.*;
import com.tridium.ndriver.point.*;


import com.gangstad.simpleModbusTcp.*;
import javax.baja.nre.annotations.*;

/**
 * BSimpleModbusTcpPointFolder
 *
 * @author   Kato Gangstad
 * @creation 2.jun.2022  
 */
@NiagaraType
public class BSimpleModbusTcpPointFolder
  extends BNPointFolder
{            

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.gangstad.simpleModbusTcp.point.BSimpleModbusTcpPointFolder(2979906276)1.0$ @*/
/* Generated Thu Jun 02 22:58:39 GMT+01:00 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSimpleModbusTcpPointFolder.class);

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
   * Get the device cast to a BSimpleModbusTcpDevice.
   * @return device as a BSimpleModbusTcpDevice.
   */
  public final BSimpleModbusTcpDevice getSimpleModbusTcpDevice()
  {
    return (BSimpleModbusTcpDevice)getDevice();
  }

}
