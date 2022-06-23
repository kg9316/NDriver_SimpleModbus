package com.gangstad.simpleModbusTcp.constants;

import javax.baja.nre.annotations.NiagaraEnum;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.annotations.Range;
import javax.baja.sys.BFrozenEnum;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;


@NiagaraType

@NiagaraEnum
        (
                range = {
                        @Range(ordinal=1, value="status"),
                        @Range(ordinal=2, value="coil"),
                        @Range(ordinal=3, value="input"),
                        @Range(ordinal=4, value="holding"),
                },
                defaultValue = "holding"
        )


public final class BRegisterTypes extends BFrozenEnum {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.gangstad.simpleModbusTcp.constants.BRegisterTypes(1463559755)1.0$ @*/
/* Generated Thu Jun 02 23:16:22 GMT+01:00 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */
  
  /** Ordinal value for status. */
  public static final int STATUS = 1;
  /** Ordinal value for coil. */
  public static final int COIL = 2;
  /** Ordinal value for input. */
  public static final int INPUT = 3;
  /** Ordinal value for holding. */
  public static final int HOLDING = 4;
  
  /** BRegisterTypes constant for status. */
  public static final BRegisterTypes status = new BRegisterTypes(STATUS);
  /** BRegisterTypes constant for coil. */
  public static final BRegisterTypes coil = new BRegisterTypes(COIL);
  /** BRegisterTypes constant for input. */
  public static final BRegisterTypes input = new BRegisterTypes(INPUT);
  /** BRegisterTypes constant for holding. */
  public static final BRegisterTypes holding = new BRegisterTypes(HOLDING);
  
  /** Factory method with ordinal. */
  public static BRegisterTypes make(int ordinal)
  {
    return (BRegisterTypes)status.getRange().get(ordinal, false);
  }
  
  /** Factory method with tag. */
  public static BRegisterTypes make(String tag)
  {
    return (BRegisterTypes)status.getRange().get(tag);
  }
  
  /** Private constructor. */
  private BRegisterTypes(int ordinal)
  {
    super(ordinal);
  }
  
  public static final BRegisterTypes DEFAULT = holding;

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BRegisterTypes.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


}
