package com.guigeek.devilopers.dd5charactersheet.character;

import android.content.Context;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseArchetype implements Archetype, Externalizable {
    static final long serialVersionUID = 299L;

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {

    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
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
    public List<Attack> getSpecialClassAttacks(Character iCharacter) {
        return new LinkedList<>();
    }
}
