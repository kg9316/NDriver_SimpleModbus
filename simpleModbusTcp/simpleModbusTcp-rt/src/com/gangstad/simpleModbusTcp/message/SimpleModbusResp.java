package com.gangstad.simpleModbusTcp.message;

import com.tridium.ndriver.io.TypedInputStream;

/**
 *  SimpleModbusResp is class for all ModbusTCp and RTU messages
 *
 *  @author   Kato Gangstad
 *  @creation 2.jun.2022
 */

public class SimpleModbusResp extends SimpleModbusTcpMessage  {

    private boolean modbusExpeption = true; // assume the worst.
    private int command = 0;
    private int value;

    public SimpleModbusResp(int seq,TypedInputStream in)
    {
        super(seq);
        //System.out.println("SimpleModbusTcpPingResp SimpleModbusResp");

        TypedInputStream tis = (TypedInputStream) in;
        command = tis.read();
        modbusExpeption = true; // assume the worst.

        //if value >= 80 then we got an error from server.
        if (command >= 0x80) {
            command -= 0x80;
        } else {
            modbusExpeption = false;
        }
        int len = tis.read();
        //only decode values with length 2 at this moment
        if (len==2 && !modbusExpeption) {
            switch (command){
                case READ_HOLDING_REGISTER:
                case READ_INPUT_REGISTER:
                    //only decode unit16 at this moment
                    value = tis.readUnsigned16();
                    break;
                case PRESET_SINGLE_REGISTER:
                case PRESET_MULTIPLE_REGISTER:
                case FORCE_SINGLE_COIL:
                case FORCE_MULTIPLE_COILS:
                    // Don't decode, this was a write command
                    break;
            }
        }
    }

    @Override
    public boolean isResponse()  {
        //Return true if we got a valid response to the request we sent
        return true;
    }

    public boolean isOK(){ return !modbusExpeption;}

    public int getValue(){ return value;}

    public void toTraceString(StringBuffer sb)
    {
        sb.append(getClass().getName()).append("\n");
        //sb.append("Unit ID:" + unitId + " Modbus Function:" + command + " TransactionIdentifier:" + transactionIdentifier+ " Register:" + register).append("\n");
    }
}