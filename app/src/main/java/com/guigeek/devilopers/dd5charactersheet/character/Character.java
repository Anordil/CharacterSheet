package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Character implements Serializable {

    public Class _class;
    public Race _race;
    public String _name;

    public int _level;
    public LinkedList<Fettle> _fettles;

    public int _hpCurrent, _hpMax, _hpTemp, _hitDice;
    public int _armorClass;
    public int[] _spellSlotsCurrent, _spellSlotsMax;
    public LinkedList<Skill> _skills;
    public LinkedList<Skill> _savingThrows;


    public int[] _attributes;

    public Character(String name, Class iClass, Race iRace) {
        _name = name;
        _class = iClass;
        _race = iRace;

        _attributes = new int[6];
        for (int i =0; i < 6; i++) {
            _attributes[i] = 10;
        }

        initSkills();
        initSavingThrows();
        refreshAC();

        doLongRest();
    }

    private void doLongRest() {
        _hpCurrent = _hpMax;
        _hpTemp = 0;
        _hitDice = _level;

        _spellSlotsMax = _class.getSpellSlots(_level);

        for (int i =0; i < _spellSlotsMax.length; i++) {
            _spellSlotsCurrent[i] = _spellSlotsMax[i];
        }
    }

    private void refreshAC() {
        _armorClass = 10 + getModifier(Enumerations.Attributes.DEX); //+ _inventory.getEquippedArmor().getACBonus();
    }

    @Override
    public String toString() {
        return _name + ", Level " + _level + " " + _race.getName() + " " + _class.getName();

    }

    public int getModifier(Enumerations.Attributes iAttr) {
        int value = _attributes[iAttr.ordinal()];
        return (int)Math.floor((value-10)/2);
    }

    public int getProficiencyBonus() {
        return (int)(1 + Math.ceil(_level/4));
    }


    public void recomputeSkills() {
        if (_skills == null || _skills.size() == 0) {
            initSkills();
        }

        for (Skill skill: _skills) {
            skill.recompute();
        }
    }

    private void initSkills() {
        _skills = new LinkedList<>();
        _skills.add(new Skills("Acrobatics", Enumerations.Attributes.DEX);
        _skills.add(new Skills("Animal Handling", Enumerations.Attributes.WIS);
        _skills.add(new Skills("Arcana", Enumerations.Attributes.INT);
        _skills.add(new Skills("Athletics", Enumerations.Attributes.STR);
        _skills.add(new Skills("Deception", Enumerations.Attributes.CHA);
        _skills.add(new Skills("History", Enumerations.Attributes.INT);
        _skills.add(new Skills("Insight", Enumerations.Attributes.WIS);
        _skills.add(new Skills("Intimidation", Enumerations.Attributes.CHA);
        _skills.add(new Skills("Investigation", Enumerations.Attributes.INT);
        _skills.add(new Skills("Medicine", Enumerations.Attributes.WIS);
        _skills.add(new Skills("Nature", Enumerations.Attributes.INT);
        _skills.add(new Skills("Perception", Enumerations.Attributes.WIS);
        _skills.add(new Skills("Performance", Enumerations.Attributes.CHA);
        _skills.add(new Skills("Persuasion", Enumerations.Attributes.CHA);
        _skills.add(new Skills("Religion", Enumerations.Attributes.INT);
        _skills.add(new Skills("Sleight of hand", Enumerations.Attributes.DEX);
        _skills.add(new Skills("Stealth", Enumerations.Attributes.DEX);
        _skills.add(new Skills("Survival", Enumerations.Attributes.WIS);
    }


    public void recomputeSavingThrows() {
        if (_savingThrows == null || _savingThrows.size() == 0) {
            initSavingThrows();
        }

        for (Skill skill: _savingThrows) {
            skill.recompute();
        }
    }

    private void initSavingThrows() {
        _savingThrows = new LinkedList<>();
        _savingThrows.add(new Skills("Strength", Enumerations.Attributes.STR);
        _savingThrows.add(new Skills("Dexterity", Enumerations.Attributes.DEX);
        _savingThrows.add(new Skills("Constitution", Enumerations.Attributes.CON);
        _savingThrows.add(new Skills("Intelligence", Enumerations.Attributes.INT);
        _savingThrows.add(new Skills("Wisdom", Enumerations.Attributes.WIS);
        _savingThrows.add(new Skills("Charisma", Enumerations.Attributes.CHA);
    }

}
