package com.gangstad.simpleModbusTcp.message;

import com.tridium.ndriver.datatypes.BIpAddress;

public class SimpleModbusTcpReq extends SimpleModbusTcpCommand {

    public SimpleModbusTcpReq(BIpAddress add, int unitID, int register, int command)
    {
        super(add,unitID, command,register);
    }

    protected int getDataLength() { return 6; }

    public void toTraceString(StringBuffer sb)
    {
        sb.append(getClass().getName()).append("\n");
        //sb.append("Unit ID:" + unitId + " Modbus Function:" + command + " TransactionIdentifier:" + transactionIdentifier+ " Register:" + register).append("\n");
    }
}