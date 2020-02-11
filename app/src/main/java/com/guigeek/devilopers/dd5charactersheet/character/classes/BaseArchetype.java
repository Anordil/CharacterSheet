package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.content.Context;
import android.util.Log;

import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseArchetype implements Archetype, Externalizable {
    static final long serialVersionUID = 3000L;
    protected int _version = 1;
    protected String _chosenFeature = null;

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(_version);
        objectOutput.writeObject(_chosenFeature);

        Log.d("WRITE", _version + ", " + _chosenFeature);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        try {
            _version = objectInput.readInt();
            _chosenFeature = (String)objectInput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            _version = 1;
            _chosenFeature = null;
        }

        Log.d("Archetype",  getName() + ", Feature: " + _chosenFeature);
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return  fettles;
    }

    @Override
    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        return new LinkedList<>();
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        return new LinkedList<>();
    }

    @Override
    public List<Attack> getSpecialClassAttacks(Character iCharacter, int classLevel) {
        return new LinkedList<>();
    }

    @Override
    public int getChoosableFeature(int iLevel) {
        return -1;
    }

    @Override
    public void setArchetypeFeature(String iFeature) {
        _chosenFeature = iFeature;
    }
}
