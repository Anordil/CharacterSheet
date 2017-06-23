package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.AttributeAlteration;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.Race;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;

public class Human implements Race, Externalizable {

    public static final long serialVersionUID = 104L;
    int _version = 1;

    @Override
    public LinkedList<Fettle> getFettles() {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return fettles;
    }

    @Override
    public String getName() {
        return App.getResString(R.string.race_human);
    }

    @Override
    public int getSpeedInFeet() {
        return 30;
    }

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[1];
        raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.STR);
        raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
        raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.CON);
        raceBonuses[3] = new AttributeAlteration(1, Enumerations.Attributes.INT);
        raceBonuses[4] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
        raceBonuses[5] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
        return raceBonuses;
    }

    public Human(){}

    @Override
    public LinkedList<Power> getRacialFeatures() {
        LinkedList<Power> racialTraits = new LinkedList<>();
        return racialTraits;
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
