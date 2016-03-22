package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.character.AttributeAlteration;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Race;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * Created by totou on 14/03/2016.
 */
public class HalfElf implements Race, Externalizable {
    @Override
    public String getName() {
        return App.getResString(R.string.race_half_elf);
    }

    @Override
    public int getSpeedInFeet() {
        return 30;
    }

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[1];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.STR);
        return raceBonuses;
    }

    public HalfElf(){}


    public final long serialVersionUID = 101L;
    int _version = 1;
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
