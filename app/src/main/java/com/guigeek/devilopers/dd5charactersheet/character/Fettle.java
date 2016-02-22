package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Serializable;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Fettle  implements Serializable {

    public Enumerations.FettleType _type;
    public int _value;


    public Fettle(Enumerations.FettleType iType, int iValue) {
        _type = iType;
        _value = iValue;
    }
}