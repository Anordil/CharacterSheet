package com.guigeek.devilopers.dd5charactersheet.character;

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
    static final long serialVersionUID = 200L;

    protected int _version = 1;
    protected LinkedList<Archetype> _archetypes = null;

    public int getChoosableArchetypes() {
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
    public void addArchetype(Archetype iArchetype) {
        if (_archetypes == null) {
            _archetypes = new LinkedList<>();
        }
        _archetypes.add(iArchetype);
    }

    @Override
    public String getQualifiedClassName() {
        String name = getClassName();

        if (_archetypes != null && _archetypes.size() > 0) {
            name += " (";
            for (int i = 0; i < _archetypes.size(); ++i) {
                name += _archetypes.get(i).getName();
                if (i < _archetypes.size() -1) {
                    name += ", ";
                }
            }
            name += ")";
        }
        return name;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        return 1;
    }

    @Override
    public List<String> getAllLevelUpBenefits(int iNewCharacterLevel) {
        List<String> allItems = getLevelUpBenefits(iNewCharacterLevel);
        if (_archetypes != null) {
            for (Archetype arc: _archetypes) {
                allItems.addAll(arc.getLevelUpBenefits(iNewCharacterLevel));
            }
        }

        return allItems;
    }

    @Override
    public LinkedList<Power> getAllPowers(int iLevel, Character iCharac) {
        LinkedList<Power> allItems = getPowers(iLevel, iCharac);
        if (_archetypes != null) {
            for (Archetype arc: _archetypes) {
                allItems.addAll(arc.getPowers(iLevel, iCharac));
            }
        }

        return allItems;
    }

    @Override
    public LinkedList<Fettle> getAllFettles(Character character) {
        LinkedList<Fettle> allItems = getFettles(character);
        if (_archetypes != null) {
            for (Archetype arc: _archetypes) {
                allItems.addAll(arc.getFettles(character));
            }
        }

        return allItems;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_archetypes);
        oo.writeObject(_spellSlots);
        oo.writeObject(_spellsKnown);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        _version = version;
        if (version >= 1) {
            _archetypes = (LinkedList<Archetype>) oi.readObject();
            _spellSlots = (int[][])oi.readObject();
            _spellsKnown = (int[][])oi.readObject();
        }
    }
}
