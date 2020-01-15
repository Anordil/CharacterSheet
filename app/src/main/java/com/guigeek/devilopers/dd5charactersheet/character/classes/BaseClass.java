package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.content.res.Resources;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by totou on 15/06/2016.
 */
public abstract class BaseClass implements Class, Externalizable {

    protected int _version = 1;
    protected Archetype _archetype = null;

    public int getArchetypes() {
        return 0;
    }

    int[][] _spellSlots = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 10
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 15
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//ln 20
    };

    int[][] _spellsKnown = {
            // cantrips, spells
            {0, 0},
            {0, 0}, //character lv 1
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},//lv 5
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},//lv 10
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},//lv 15
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0}//ln 20
    };

    @Override
    public int[] getSpellSlots(int iCharacterLevel) {
        return _spellSlots[Math.min(20, iCharacterLevel)];
    }

    @Override
    public boolean isCaster() {
        return false;
    }

    @Override
    public int[] getSpellsKnown(int iCharacterLevel) {
        return _spellsKnown[Math.min(20, iCharacterLevel)];
    }

    @Override
    public int getAC(Character character) {
        int ac = character._equippedArmor.getAC(character);

        if (character._equippedShield != null && character._equippedShield._type == Enumerations.ArmorTypes.SHIELD) {
            ac+= character._equippedShield.getAC(character);
        }

        return ac;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }

    @Override
    public void setArchetype(Archetype iArchetype) {
        _archetype = iArchetype;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        return 1;
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_archetype);
        oo.writeObject(_spellSlots);
        oo.writeObject(_spellsKnown);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        _version = version;
        if (version >= 1) {
            _archetype = (Archetype) oi.readObject();
            _spellSlots = (int[][])oi.readObject();
            _spellsKnown = (int[][])oi.readObject();
        }
    }
}
