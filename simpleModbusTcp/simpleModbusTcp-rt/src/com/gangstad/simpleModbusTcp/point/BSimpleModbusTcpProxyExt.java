/**
 * Copyright 2022 gangstad, All Rights Reserved.
 */
package com.gangstad.simpleModbusTcp.point;

import javax.baja.control.BControlPoint;
import javax.baja.control.BNumericPoint;
import javax.baja.driver.util.BPollFrequency;
import javax.baja.sys.*;
import javax.baja.status.*;
import javax.baja.driver.point.*;

import com.gangstad.simpleModbusTcp.constants.BRegisterTypes;
import com.gangstad.simpleModbusTcp.message.SimpleModbusResp;
import com.gangstad.simpleModbusTcp.message.SimpleModbusTcpReq;
import com.tridium.ndriver.comm.NMessage;
import com.tridium.ndriver.datatypes.BIpAddress;
import com.tridium.ndriver.point.BNProxyExt;
import javax.baja.nre.annotations.*;

import com.gangstad.simpleModbusTcp.*;
import com.tridium.driver.util.DrUtil;
import com.tridium.ndriver.poll.BINPollable;

import java.util.StringTokenizer;

import static com.gangstad.simpleModbusTcp.SimpleModbusHelper.getCommandBasedOnRegisterType;
import static com.gangstad.simpleModbusTcp.SimpleModbusHelper.getWriteCommandBasedOnRegisterType;


/**
 * BSimpleModbusTcpProxyExt
 *
 *  @author   Kato Gangstad
 * @creation 2.jun.2022 
 */
@NiagaraType
@NiagaraProperty
        (
                name = "Address",
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
                name = "RegisterType",
                type = "BRegisterTypes",
                defaultValue = "BRegisterTypes.holding"
        )
@NiagaraProperty(name = "pollFrequency", type = "BPollFrequency", defaultValue = "BPollFrequency.normal")



public class BSimpleModbusTcpProxyExt
  extends BNProxyExt implements BINPollable
{   
  
  // Override ProxyExt default status to clear stale state.
  // public static final Property status = newProperty(Flags.READONLY|Flags.TRANSIENT, BStatus.ok, null);
  



/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.gangstad.simpleModbusTcp.point.BSimpleModbusTcpProxyExt(2432232528)1.0$ @*/
/* Generated Thu Jun 23 10:37:31 CEST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "Address"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code Address} property.
   * @see #getAddress
   * @see #setAddress
   */
  public static final Property Address = newProperty(0, ((BInteger)(BInteger.make(0))).getInt(), BFacets.make(BFacets.make(BFacets.MIN, 0), BFacets.make(BFacets.MAX, 65534)));
  
  /**
   * Get the {@code Address} property.
   * @see #Address
   */
  public int getAddress() { return getInt(Address); }
  
  /**
   * Set the {@code Address} property.
   * @see #Address
   */
  public void setAddress(int v) { setInt(Address, v, null); }

////////////////////////////////////////////////////////////////
// Property "RegisterType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code RegisterType} property.
   * @see #getRegisterType
   * @see #setRegisterType
   */
  public static final Property RegisterType = newProperty(0, BRegisterTypes.holding, null);
  
  /**
   * Get the {@code RegisterType} property.
   * @see #RegisterType
   */
  public BRegisterTypes getRegisterType() { return (BRegisterTypes)get(RegisterType); }
  
  /**
   * Set the {@code RegisterType} property.
   * @see #RegisterType
   */
  public void setRegisterType(BRegisterTypes v) { set(RegisterType, v, null); }

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
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSimpleModbusTcpProxyExt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


  
////////////////////////////////////////////////////////////////
// Access
////////////////////////////////////////////////////////////////
  
  /**
   * Get the network cast to a BSimpleModbusTcpNetwork.
   */
  public final BSimpleModbusTcpNetwork getSimpleModbusTcpNetwork()
  {
    return (BSimpleModbusTcpNetwork)getNetwork();
  }

  /**
   * Get the device cast to a BSimpleModbusTcpDevice.
   */
  public final BSimpleModbusTcpDevice getBSimpleModbusTcpDevice()
  {
    return (BSimpleModbusTcpDevice)DrUtil.getParent(this, BSimpleModbusTcpDevice.TYPE);
  }

  /**
   * Get the point device ext cast to a BSimpleModbusTcpPointDeviceExt.
   */
  public final BSimpleModbusTcpPointDeviceExt getSimpleModbusTcpPointDeviceExt()
  {
    return (BSimpleModbusTcpPointDeviceExt)getDeviceExt();
  }
  
////////////////////////////////////////////////////////////////
// ProxyExt
////////////////////////////////////////////////////////////////
  public void readSubscribed(Context cx)
    throws Exception
  {
    BSimpleModbusTcpNetwork network = getSimpleModbusTcpNetwork();
    network.getPollScheduler().subscribe(this);

    //force read update of values
    doPoll();
  }
  
  public void readUnsubscribed(Context cx)
    throws Exception
  {
    // unsubscribe this point for polling
    BSimpleModbusTcpNetwork network = getSimpleModbusTcpNetwork();
    network.getPollScheduler().unsubscribe(this);
  }
  
  /**
   * Return the device type. 
   */
  public Type getDeviceExtType()
  {
    return BSimpleModbusTcpPointDeviceExt.TYPE;
  }                     
  
  /**
   * Return the read/write mode of this proxy.
   */
  public BReadWriteMode getMode()
  {
    // TODO
    return BReadWriteMode.readWrite;
  }
  public void doPoll()
  {
    BSimpleModbusTcpNetwork network = getSimpleModbusTcpNetwork();
    BSimpleModbusTcpDevice device = (BSimpleModbusTcpDevice)getDevice();

    BIpAddress ipAddress = device.address;
    int command = getCommandBasedOnRegisterType(getRegisterType().getOrdinal());
    SimpleModbusTcpReq readReq = new SimpleModbusTcpReq(ipAddress,device.getUnitIdentifier(),getAddress(),command);
    try
    {
      NMessage resp = network.tcomm().sendRequest(readReq);

      BControlPoint controlPoint = getParentPoint();

      if (((SimpleModbusResp) resp).isOK()) {
        // we only support numeric control points
        if (controlPoint instanceof BNumericPoint)
          readOk(new BStatusNumeric(((SimpleModbusResp) resp).getValue()));
        else
          readFail("Invalid parent control point type");
      }
      else
      {
        readFail("Exp code from server");
      }

    }
    catch(Exception e)
    {
      readFail(e.getMessage());
    }
  }

  @Override
  public boolean write(Context cx) throws Exception
  {

    BSimpleModbusTcpNetwork network = getSimpleModbusTcpNetwork();
    BSimpleModbusTcpDevice device = (BSimpleModbusTcpDevice)getDevice();

    BIpAddress ipAddress = device.address;
    int command = getWriteCommandBasedOnRegisterType(getRegisterType().getOrdinal());

    //if our value status is null, we have nothing to write,
    BStatusValue value = getWriteValue();
    if( value.getStatus().isNull())
      return false;

    //otherwise, get the value to write to our remote point
    BValue valueToWrite = value.getValueValue();
    //cast to integer
    int intvalue = (int)((BStatusNumeric)value).getValue();

    SimpleModbusTcpReq writeReq = new SimpleModbusTcpReq(ipAddress,device.getUnitIdentifier(),getAddress(),command,intvalue);

    try
    {
      NMessage resp = network.tcomm().sendRequest(writeReq);

      BControlPoint controlPoint = getParentPoint();

      if (((SimpleModbusResp) resp).isOK())
        writeOk((BStatusValue)value.newCopy());
        else
        writeFail("Todo Fail Cause");
    }
    catch(Exception e)
    {
      writeFail(e.getMessage());
    }

    //no additional writes pending, so return false
    return false;
  }

  public boolean isBoolean()
  {
    return getParentPoint().getOutStatusValue() instanceof BStatusBoolean;
  }
  
  public boolean isNumeric()
  {
    return getParentPoint().getOutStatusValue() instanceof BStatusNumeric;
  }
  
  public boolean isString()
  {
    return getParentPoint().getOutStatusValue() instanceof BStatusString;
  }
  
  public boolean isEnum()
  {
    return getParentPoint().getOutStatusValue() instanceof BStatusEnum;
  }
  
}
