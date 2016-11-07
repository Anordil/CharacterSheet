package com.guigeek.devilopers.dd5charactersheet.item;

import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by ggallani on 07/11/2016.
 */

public class Item implements Externalizable {

    public static final long serialVersionUID = 22L;
    int _version = 1;


    public String _name;
    public LinkedList<Fettle> _magicProperties;
    public int _weight, _price;

    public Item() {}
    public Item(String iName, int iWeight, int iPrice, LinkedList<Fettle> iProperties) {
        _name = iName;
        _weight = iWeight;
        _price = iPrice;
        _magicProperties = iProperties;
        if (_magicProperties == null) {
            _magicProperties = new LinkedList<>();
        }
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);

        oo.writeObject(_name);
        oo.writeObject(_magicProperties);
        oo.writeInt(_weight);
        oo.writeInt(_price);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;

        if (_version >= 1) {
            _name = (String) oi.readObject();
            _magicProperties = (LinkedList<Fettle>)oi.readObject();
            _weight = oi.readInt();
            _price = oi.readInt();
        }
    }
}
