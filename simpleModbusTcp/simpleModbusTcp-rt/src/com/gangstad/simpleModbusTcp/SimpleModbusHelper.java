package com.gangstad.simpleModbusTcp;

import com.gangstad.simpleModbusTcp.constants.BRegisterTypes;
import com.gangstad.simpleModbusTcp.constants.ModBusConstants;

import java.io.IOException;
import java.io.InputStream;

public class SimpleModbusHelper {

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02X ", b));
        return sb.toString();
    }

    public static String byteArrayToHex(byte[] a,int size) {
        StringBuilder sb = new StringBuilder(size * 2);
        int d = 0;
        for(byte b: a) {
            sb.append(String.format("%02X ", b));
            d++;
            if (d>=size) break;
        }
        return sb.toString();
    }

    /**
     *  Fill up passed buffer with data from inputStream.
     *  return true if complete message received
     *  return false if we passed the end of inputStream.
     * @author   Kato Gangstad
     * @creation 2.jun.2022
     */
    public static boolean readFromInputStream (InputStream in, byte[] buffer) throws IOException {

        int input;
        for (int i = 0; i < buffer.length; i++) {
            // if read() gives back -1 we have passed the end, return false
            if  ((input = in.read()) != -1) {
                buffer[i] = (byte) input;
            }
            else
                return false;
        }
        return true;
    }

    public static int getCommandBasedOnRegisterType (int type){

        switch (type)
        {
            case BRegisterTypes.STATUS:
                return ModBusConstants.READ_INPUT_STATUS;
            case BRegisterTypes.COIL:
                return ModBusConstants.READ_COIL_STATUS;
            case BRegisterTypes.INPUT:
                return ModBusConstants.READ_INPUT_REGISTER;
            case BRegisterTypes.HOLDING:
                return ModBusConstants.READ_HOLDING_REGISTER;
            default:
                return ModBusConstants.READ_HOLDING_REGISTER;
        }
    }

    public static int getWriteCommandBasedOnRegisterType (int type) {

        switch (type) {
            case BRegisterTypes.COIL:
                return ModBusConstants.FORCE_SINGLE_COIL;
            case BRegisterTypes.HOLDING:
                return ModBusConstants.PRESET_SINGLE_REGISTER;
            default:
                return 0;
        }
    }
}
