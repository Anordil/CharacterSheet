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
        _level = 1;

        _fettles = new LinkedList<>();
        _attributes = new int[6];
        for (int i =0; i < 6; i++) {
            _attributes[i] = 10;
        }

        initLevel();
        initSkills();
        initSavingThrows();
        refreshAC();

        doLongRest();
    }

    private void initLevel() {
        _hitDice = _level;
        _spellSlotsMax = _class.getSpellSlots(_level);
        _hpMax = _class.getHitDie() + (_level -1)*(int)Math.ceil(_class.getHitDie()/2);
    }

    private void doLongRest() {
        _hpCurrent = _hpMax;
        _hpTemp = 0;
        _hitDice = _level;


        _spellSlotsCurrent = new int[_spellSlotsMax.length];
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
            skill.recompute(this);
        }
    }

    private void initSkills() {
        _skills = new LinkedList<>();
        _skills.add(new Skill("Acrobatics", Enumerations.Attributes.DEX));
        _skills.add(new Skill("Animal Handling", Enumerations.Attributes.WIS));
        _skills.add(new Skill("Arcana", Enumerations.Attributes.INT));
        _skills.add(new Skill("Athletics", Enumerations.Attributes.STR));
        _skills.add(new Skill("Deception", Enumerations.Attributes.CHA));
        _skills.add(new Skill("History", Enumerations.Attributes.INT));
        _skills.add(new Skill("Insight", Enumerations.Attributes.WIS));
        _skills.add(new Skill("Intimidation", Enumerations.Attributes.CHA));
        _skills.add(new Skill("Investigation", Enumerations.Attributes.INT));
        _skills.add(new Skill("Medicine", Enumerations.Attributes.WIS));
        _skills.add(new Skill("Nature", Enumerations.Attributes.INT));
        _skills.add(new Skill("Perception", Enumerations.Attributes.WIS));
        _skills.add(new Skill("Performance", Enumerations.Attributes.CHA));
        _skills.add(new Skill("Persuasion", Enumerations.Attributes.CHA));
        _skills.add(new Skill("Religion", Enumerations.Attributes.INT));
        _skills.add(new Skill("Sleight of hand", Enumerations.Attributes.DEX));
        _skills.add(new Skill("Stealth", Enumerations.Attributes.DEX));
        _skills.add(new Skill("Survival", Enumerations.Attributes.WIS));
    }


    public void recomputeSavingThrows() {
        if (_savingThrows == null || _savingThrows.size() == 0) {
            initSavingThrows();
        }

        for (Skill skill: _savingThrows) {
            skill.recompute(this);
        }
    }

    private void initSavingThrows() {
        _savingThrows = new LinkedList<>();
        _savingThrows.add(new Skill("Strength", Enumerations.Attributes.STR));
        _savingThrows.add(new Skill("Dexterity", Enumerations.Attributes.DEX));
        _savingThrows.add(new Skill("Constitution", Enumerations.Attributes.CON));
        _savingThrows.add(new Skill("Intelligence", Enumerations.Attributes.INT));
        _savingThrows.add(new Skill("Wisdom", Enumerations.Attributes.WIS));
        _savingThrows.add(new Skill("Charisma", Enumerations.Attributes.CHA));
    }

}
