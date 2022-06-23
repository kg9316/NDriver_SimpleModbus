package com.gangstad.simpleModbusTcp.message;

import com.tridium.ndriver.comm.NMessage;
import com.tridium.ndriver.datatypes.BIpAddress;
import com.tridium.ndriver.io.TypedOutputStream;
import static com.gangstad.simpleModbusTcp.SimpleModbusHelper.*;

import java.io.OutputStream;

public abstract class SimpleModbusTcpCommand extends SimpleModbusTcpMessage{

    public SimpleModbusTcpCommand(BIpAddress add, int unitId, int command, int register){
        super(getNextTransactionIdentifier());
        setAddress(add);
        this.unitId = unitId;
        this.command = command;
        this.register = register;
        this.transactionIdentifier = super.transactionIdentifier;
    }

    public SimpleModbusTcpCommand(BIpAddress add, int unitId, int command, int register,int writevalue){
        super(getNextTransactionIdentifier());
        setAddress(add);
        this.unitId = unitId;
        this.command = command;
        this.register = register;
        this.transactionIdentifier = super.transactionIdentifier;
        this.writevalue = writevalue;
        this.writemode = true;
    }



    public boolean toOutputStream(OutputStream out)
            throws Exception
    {
        TypedOutputStream to = (TypedOutputStream)out;

        //Build standard Modbus PDU, can be used for ModbusTCP and ModbusRTU

        // Unit ID
        to.writeUnsigned8(unitId & 255);

        // Function Code
        to.write(command & 255);

        // Start register
        to.write(register >> 8 & 255);
        to.write(register & 255);

        if (!writemode) {
            //Number of registers to read
            to.write((numOfRegisters >> 8) & 255);
            to.write(numOfRegisters & 255);
        }
        else
        {
            //value to write
            to.write((writevalue >> 8) & 255);
            to.write(writevalue & 255);
        }

        //System.out.println("SimpleModbusTcpCommand toOutputStream: "+ byteArrayToHex(to.toByteArray()));
        to.flush();

        return false;
    }

    public int validateResponse(NMessage msg)
            throws Exception

    {
        SimpleModbusTcpMessage nn = (SimpleModbusTcpMessage) msg;

        //System.out.println("SimpleModbusTcpCommand validateResponse");
        if(((SimpleModbusTcpMessage)msg).isAck())
        {
            return ACK_SUCCESS_RESPONSE;
        }

        return SUCCESS_RESPONSE;
    }

    protected boolean isRequest() { return false; }

    protected boolean isResetTimeout() { return true; }

    protected abstract int getDataLength();

    private static int getNextTransactionIdentifier()
    {
        nxtTransactionIdentifier +=1;
        if(nxtTransactionIdentifier > 0x0ffff) nxtTransactionIdentifier = 1;
        //System.out.println("SimpleModbusTcpCommand getNextTransactionIdentifier: "+nxtTransactionIdentifier);
        return nxtTransactionIdentifier;
    }

    static int nxtTransactionIdentifier = 0;
    //boolean answer = true;

    int unitId = 0;
    int command = 0;
    int transactionIdentifier = 0;
    int register = 0;
    int numOfRegisters = 1; //limit to one in this example
    int writevalue = 0;
    boolean writemode = false;

    public void toTraceString(StringBuffer sb)
    {
        sb.append(getClass().getName()).append("\n");
        sb.append("Unit ID:" + unitId + " Modbus Function:" + command + " TransactionIdentifier:" + transactionIdentifier+ " Register:" + register).append("\n");
    }
}
