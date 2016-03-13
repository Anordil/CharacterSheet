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
    public LinkedList<Power> _powers;

    public int _atkBonus, _dmgBonus;
    public String _weaponDmgDice;
    public boolean _isWeaponRanged;
    public int _gold;
    public String _allItems;


    public int[] _attributes;

    public Character(String name, Class iClass, Race iRace, int level, int[] attr) {
        _name = name;
        _class = iClass;
        _race = iRace;
        _level = level;

        _fettles = new LinkedList<>();
        _attributes = new int[6];
        for (int i =0; i < 6; i++) {
            _attributes[i] = attr[i];
        }

        _atkBonus = 0;
        _dmgBonus = 0;
        _weaponDmgDice = "1D4";
        _isWeaponRanged = false;
        _gold = 0;
        _allItems = "10 torches";


        initLevel();
        recomputeSkills();
        initSavingThrows();

        doLongRest();
    }

    public LinkedList<Power> getPowers() {
        if (_powers == null) {
            _powers = _class.getPowers(_level, this);
        }
        return _powers;
    }

    public void refresh() {
        initLevel();
        recomputeSkills();
        initSavingThrows();

        doLongRest();
    }

    private void initLevel() {
        _hitDice = _level;
        _spellSlotsMax = _class.getSpellSlots(_level);
        _hpMax = _class.getHitDie() + (_level -1)*(int)Math.ceil(_class.getHitDie()/2 +1) + _level*getModifier(Enumerations.Attributes.CON);
        _powers = _class.getPowers(_level, this);
    }

    public void doLongRest() {
        _hpCurrent = _hpMax;
        _hpTemp = 0;
        _hitDice = _level;


        _spellSlotsCurrent = new int[_spellSlotsMax.length];
        for (int i =0; i < _spellSlotsMax.length; i++) {
            _spellSlotsCurrent[i] = _spellSlotsMax[i];
        }

        if (_powers == null) {
            _powers = _class.getPowers(_level, this);
        }
        for (Power p: getPowers()) {
            p._left = p._max;
        }
    }

    public void doShortRest() {
        if (_powers == null) {
            _powers = _class.getPowers(_level, this);
        }
        for (Power p: getPowers()) {
            if (!p._isLongRest) {
                p._left = p._max;
            }
        }
    }


    @Override
    public String toString() {
        return _name + ", Level " + _level + " " + _race.getName() + " " + _class.getName();

    }

    public void changeHP(int iQuantity) {
        _hpCurrent += iQuantity;
        _hpCurrent = Math.min(_hpCurrent, _hpMax);
        _hpCurrent = Math.max(_hpCurrent, 0);
    }

    public void changeHD(int iQuantity) {
        _hitDice += iQuantity;
        _hitDice = Math.min(_hitDice, _level);
        _hitDice = Math.max(_hitDice, 0);
    }

    public void changeSpellSlot(int level, int diff) {
        _spellSlotsCurrent[level] += diff;
        _spellSlotsCurrent[level] = Math.min(_spellSlotsCurrent[level], _spellSlotsMax[level]);
        _spellSlotsCurrent[level] = Math.max(_spellSlotsCurrent[level], 0);
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
