package com.guigeek.devilopers.dd5charactersheet.character.races;

import android.content.Context;
import android.util.Log;

import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseRace implements Race, Externalizable {
    static final long serialVersionUID = 100L;

    int _version = 2;
    String _subRace = null;

    public BaseRace(){}


    @Override
    public int getSpeedInFeet() {
        return 30;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_subRace);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;
        _subRace = (String) oi.readObject();
        Log.d("RACE", "Subrace: " + _subRace);
    }

    @Override
    public String getName() {
        String name = getBaseRaceName();
        if (_subRace != null) {
            name = _subRace + " " + name;
        }

        return name;
    }

    @Override
    public void setSubRace(String iSubRace) {
        _subRace = iSubRace;
    }

    @Override
    public String getSubRace() {
        return _subRace;
    }

    @Override
    public String getAttributeBoostDescription() {
        return null;
    }

    @Override
    public int getSubraceArrayId() {
        return -1;
    }

    @Override
    public void chooseAttributeBoost(final Context context, final Character iCharac) {
        // By default nothing to do
    }

    @Override
    public List<Enumerations.Proficiencies> getArmorProficiencies() {
        return new LinkedList<>();
    }

    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        return new LinkedList<>();
    }
}
