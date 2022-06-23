/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp;

import javax.baja.driver.util.BPollFrequency;
import javax.baja.status.BStatus;
import javax.baja.sys.*;
import javax.baja.nre.annotations.*;

import com.gangstad.simpleModbusTcp.constants.BRegisterTypes;
import com.gangstad.simpleModbusTcp.constants.ModBusConstants;
import com.gangstad.simpleModbusTcp.message.SimpleModbusResp;
import com.gangstad.simpleModbusTcp.message.SimpleModbusTcpPing;
import com.tridium.ndriver.BNDevice;
import com.tridium.ndriver.comm.NMessage;
import com.tridium.ndriver.datatypes.BIpAddress;
import com.tridium.ndriver.poll.BINPollable;
import com.tridium.ndriver.util.SfUtil;

import com.gangstad.simpleModbusTcp.point.*;

import static com.gangstad.simpleModbusTcp.SimpleModbusHelper.getCommandBasedOnRegisterType;


/**
 *  BSimpleModbusTcpDevice models a single device
 *
 *  @author   Kato Gangstad
 *  @creation 2.jun.2022 
 */
@NiagaraType
@NiagaraProperty(name = "pollFrequency", type = "BPollFrequency",  defaultValue = "BPollFrequency.normal")
@NiagaraProperty
        (
                name = "unitIdentifier",
                type = "BInteger",
                defaultValue = "BInteger.make(0)",
                facets =
                        {
                                @Facet(name = "BFacets.MIN", value = "0"),
                                @Facet(name = "BFacets.MAX", value = "255")
                        }
        )

@NiagaraProperty
        (
                name = "port",
                type = "BInteger",
                defaultValue = "BInteger.make(502)",
                facets =
                        {
                                @Facet(name = "BFacets.MIN", value = "0"),
                                @Facet(name = "BFacets.MAX", value = "65535")
                        }
        )

@NiagaraProperty
        (
                name = "ipAddress",
                type = "BString",
                defaultValue = "###.###.###.###"
        )
@NiagaraProperty
        (
                name = "pingAddress",
                type = "BInteger",
                defaultValue = "BInteger.make(0)",
                facets =
                {
                        @Facet(name = "BFacets.MIN", value = "0"),
                        @Facet(name = "BFacets.MAX", value = "65534")
                }
        )
@NiagaraProperty
        (
                name = "pingRegisterType",
                type = "BRegisterTypes",
                defaultValue = "BRegisterTypes.holding"
        )

@NiagaraProperty(name = "points", type = "BSimpleModbusTcpPointDeviceExt",  defaultValue = "new BSimpleModbusTcpPointDeviceExt()")


public class BSimpleModbusTcpDevice
  extends BNDevice
  implements BINPollable
{
  
  // Add facet to include following in auto manager view
  public static final Property status = newProperty(Flags.TRANSIENT|Flags.READONLY|Flags.SUMMARY|Flags.DEFAULT_ON_CLONE, BStatus.ok, SfUtil.incl(SfUtil.MGR_EDIT_READONLY));




/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.gangstad.simpleModbusTcp.BSimpleModbusTcpDevice(2230360824)1.0$ @*/
/* Generated Tue Jun 21 20:30:45 CEST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "pollFrequency"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code pollFrequency} property.
   * @see #getPollFrequency
   * @see #setPollFrequency
   */
  public static final Property pollFrequency = newProperty(0, BPollFrequency.normal, null);
  
  /**
   * Get the {@code pollFrequency} property.
   * @see #pollFrequency
   */
  public BPollFrequency getPollFrequency() { return (BPollFrequency)get(pollFrequency); }
  
  /**
   * Set the {@code pollFrequency} property.
   * @see #pollFrequency
   */
  public void setPollFrequency(BPollFrequency v) { set(pollFrequency, v, null); }

////////////////////////////////////////////////////////////////
// Property "unitIdentifier"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code unitIdentifier} property.
   * @see #getUnitIdentifier
   * @see #setUnitIdentifier
   */
  public static final Property unitIdentifier = newProperty(0, ((BInteger)(BInteger.make(0))).getInt(), BFacets.make(BFacets.make(BFacets.MIN, 0), BFacets.make(BFacets.MAX, 255)));
  
  /**
   * Get the {@code unitIdentifier} property.
   * @see #unitIdentifier
   */
  public int getUnitIdentifier() { return getInt(unitIdentifier); }
  
  /**
   * Set the {@code unitIdentifier} property.
   * @see #unitIdentifier
   */
  public void setUnitIdentifier(int v) { setInt(unitIdentifier, v, null); }

////////////////////////////////////////////////////////////////
// Property "port"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code port} property.
   * @see #getPort
   * @see #setPort
   */
  public static final Property port = newProperty(0, ((BInteger)(BInteger.make(502))).getInt(), BFacets.make(BFacets.make(BFacets.MIN, 0), BFacets.make(BFacets.MAX, 65535)));
  
  /**
   * Get the {@code port} property.
   * @see #port
   */
  public int getPort() { return getInt(port); }
  
  /**
   * Set the {@code port} property.
   * @see #port
   */
  public void setPort(int v) { setInt(port, v, null); }

////////////////////////////////////////////////////////////////
// Property "ipAddress"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code ipAddress} property.
   * @see #getIpAddress
   * @see #setIpAddress
   */
  public static final Property ipAddress = newProperty(0, "###.###.###.###", null);
  
  /**
   * Get the {@code ipAddress} property.
   * @see #ipAddress
   */
  public String getIpAddress() { return getString(ipAddress); }
  
  /**
   * Set the {@code ipAddress} property.
   * @see #ipAddress
   */
  public void setIpAddress(String v) { setString(ipAddress, v, null); }

////////////////////////////////////////////////////////////////
// Property "pingAddress"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code pingAddress} property.
   * @see #getPingAddress
   * @see #setPingAddress
   */
  public static final Property pingAddress = newProperty(0, ((BInteger)(BInteger.make(0))).getInt(), BFacets.make(BFacets.make(BFacets.MIN, 0), BFacets.make(BFacets.MAX, 65534)));
  
  /**
   * Get the {@code pingAddress} property.
   * @see #pingAddress
   */
  public int getPingAddress() { return getInt(pingAddress); }
  
  /**
   * Set the {@code pingAddress} property.
   * @see #pingAddress
   */
  public void setPingAddress(int v) { setInt(pingAddress, v, null); }

////////////////////////////////////////////////////////////////
// Property "pingRegisterType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code pingRegisterType} property.
   * @see #getPingRegisterType
   * @see #setPingRegisterType
   */
  public static final Property pingRegisterType = newProperty(0, BRegisterTypes.holding, null);
  
  /**
   * Get the {@code pingRegisterType} property.
   * @see #pingRegisterType
   */
  public BRegisterTypes getPingRegisterType() { return (BRegisterTypes)get(pingRegisterType); }
  
  /**
   * Set the {@code pingRegisterType} property.
   * @see #pingRegisterType
   */
  public void setPingRegisterType(BRegisterTypes v) { set(pingRegisterType, v, null); }

////////////////////////////////////////////////////////////////
// Property "points"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code points} property.
   * @see #getPoints
   * @see #setPoints
   */
  public static final Property points = newProperty(0, new BSimpleModbusTcpPointDeviceExt(), null);
  
  /**
   * Get the {@code points} property.
   * @see #points
   */
  public BSimpleModbusTcpPointDeviceExt getPoints() { return (BSimpleModbusTcpPointDeviceExt)get(points); }
  
  /**
   * Set the {@code points} property.
   * @see #points
   */
  public void setPoints(BSimpleModbusTcpPointDeviceExt v) { set(points, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSimpleModbusTcpDevice.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
  
  
////////////////////////////////////////////////////////////////
// Overrides
////////////////////////////////////////////////////////////////
  
  /**
   * Returns the network type that the device runs on.
   * @return Type object representing the network
   */
  public Type getNetworkType() { return BSimpleModbusTcpNetwork.TYPE; }  


  /**
   * Override started 
   */
  public void started()
    throws Exception
  {
    super.started();
    
    // register device with poll scheduler 
    getSimpleModbusTcpNetwork().getPollScheduler().subscribe(this);
  }
  
  /**
   * Override stopped 
   */
  public void stopped()
    throws Exception
  {
    // unregister device with poll scheduler 
    getSimpleModbusTcpNetwork().getPollScheduler().unsubscribe(this);
    super.stopped();
  }
  
  
////////////////////////////////////////////////////////////////
// Implementation
////////////////////////////////////////////////////////////////
  /**
   * 
   */
  public void doPing() throws Exception {
    BSimpleModbusTcpNetwork network = getSimpleModbusTcpNetwork();
    BIpAddress ipAddress = new BIpAddress(getIpAddress(),getPort());

    int command = getCommandBasedOnRegisterType(getPingRegisterType().getOrdinal());

    SimpleModbusTcpPing pingReq = new SimpleModbusTcpPing(ipAddress,getUnitIdentifier(),getPingAddress(),command);
    NMessage resp =network.tcomm().sendRequest(pingReq);

    if (((SimpleModbusResp) resp).isOK())
      pingOk();
   else
      pingFail("TODO ping fail cause goes here");
  }


////////////////////////////////////////////////////////////////
// Polling support
////////////////////////////////////////////////////////////////

  /**
   * The poll() callback method called from BPollScheduler
   * when it is time to poll this object.
   */
  public void doPoll()
  {
    // TODO add poll support
  }
  
////////////////////////////////////////////////////////////////
// Utilities
////////////////////////////////////////////////////////////////
  /**
   * Get the network cast to a BSimpleModbusTcpNetwork.
   * @return network as a BSimpleModbusTcpNetwork.
   */
  public final BSimpleModbusTcpNetwork getSimpleModbusTcpNetwork()
  {
    return (BSimpleModbusTcpNetwork)getNetwork();
  }
  public BIpAddress address = new BIpAddress(getIpAddress(),getPort());

  
}
