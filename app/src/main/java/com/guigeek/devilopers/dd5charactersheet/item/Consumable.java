package com.guigeek.devilopers.dd5charactersheet.item;

import com.guigeek.devilopers.dd5charactersheet.character.Fettle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;

/**
 * Created by ggallani on 07/11/2016.
 */

public class Consumable implements Externalizable {

    public static final long serialVersionUID = 23L;
    int _version = 1;


    public String _name, _effect;
    public int _weight, _price, _charges;

    public Consumable() {}
    public Consumable(String iName, String iEffect, int iWeight, int iPrice, int iCharges) {
        _name = iName;
        _effect = iEffect;
        _weight = iWeight;
        _price = iPrice;
        _charges = iCharges;
    }


    @Override
    public String toString() {
        return _name +  " (x" + _charges + ")";
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);

        oo.writeObject(_name);
        oo.writeObject(_effect);
        oo.writeInt(_weight);
        oo.writeInt(_price);
        oo.writeInt(_charges);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;

        if (_version >= 1) {
            _name = (String) oi.readObject();
            _effect = (String) oi.readObject();
            _weight = oi.readInt();
            _price = oi.readInt();
            _charges = oi.readInt();
        }
    }
}
