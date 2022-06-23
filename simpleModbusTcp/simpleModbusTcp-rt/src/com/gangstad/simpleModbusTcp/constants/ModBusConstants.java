package com.gangstad.simpleModbusTcp.constants;

public interface ModBusConstants {

    //Function Codes Read
    int READ_COIL_STATUS = 1;
    int READ_INPUT_STATUS = 2;
    int READ_HOLDING_REGISTER = 3;
    int READ_INPUT_REGISTER = 4;

    //Function Codes Write
    int FORCE_SINGLE_COIL = 5;
    int PRESET_SINGLE_REGISTER = 6;
    int FORCE_MULTIPLE_COILS = 15;
    int PRESET_MULTIPLE_REGISTER = 16;


}
