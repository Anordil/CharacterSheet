package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Fettle  implements Externalizable {

    public static final long serialVersionUID = 13L;
    int _version = 1;

    public Enumerations.FettleType _type;
    public int _value;

    public Fettle(){}


    public Fettle(Enumerations.FettleType iType, int iValue) {
        _type = iType;
        _value = iValue;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;
    }
}