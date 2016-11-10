package com.guigeek.devilopers.dd5charactersheet.character;

import android.util.Log;

import com.guigeek.devilopers.dd5charactersheet.character.classes.Barbarian;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Paladin;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfElf;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfOrc;
import com.guigeek.devilopers.dd5charactersheet.character.races.MountainDwarf;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Item;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Character implements Externalizable {

    public static final long serialVersionUID = 30L;
    public int _version = 8;
    public static final int _latestVersion = 8;

    public Class _class;
    public Race _race;
    public String _name;
    public int[] _attributes;

    public int _level;
    public int _hpCurrent, _hpMax, _hpTemp, _hitDice;
    public int _armorClass;
    public int[] _spellSlotsCurrent, _spellSlotsMax;
    public int _atkBonus, _dmgBonus;
    public int _gold;

    public LinkedList<Skill> _skills;
    public LinkedList<SavingThrow> _savingThrows;
    public LinkedList<Power> _powers;
    public LinkedList<Power> _feats;


    public LinkedList<Fettle> _effect;


    // DEPRECATED
    public String _weaponDmgDice;
    public boolean _isWeaponRanged;
    public String _allItems;
    // end DEPRECATED

    public Armor _equippedArmor;
    public Armor _equippedShield;
    public Weapon _equippedWeapon, _offHandWeapon;
    public LinkedList<Item> _equippedItems;

    public LinkedList<Externalizable> _inventory;


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_latestVersion);

        oo.writeObject(_class);
        oo.writeObject(_race);
        oo.writeObject(_name);
        oo.writeObject(_attributes);

        oo.writeInt(_level);
        oo.writeInt(_hpCurrent);
        oo.writeInt(_hpMax);
        oo.writeInt(_hpTemp);
        oo.writeInt(_hitDice);
        oo.writeInt(_armorClass);
        oo.writeInt(_atkBonus);
        oo.writeInt(_dmgBonus);
        oo.writeInt(_gold);

        oo.writeObject(_skills);
        oo.writeObject(_savingThrows);
        oo.writeObject(_powers);

        oo.writeObject(_weaponDmgDice);
        oo.writeBoolean(_isWeaponRanged);
        oo.writeObject(_allItems);

        oo.writeObject(_spellSlotsCurrent);
        oo.writeObject(_spellSlotsMax);

        oo.writeObject(_feats);
        oo.writeObject(_effect);

        oo.writeObject(_equippedArmor);
        oo.writeObject(_equippedWeapon);
        oo.writeObject(_equippedItems);
        oo.writeObject(_equippedShield);
        oo.writeObject(_offHandWeapon);

        oo.writeObject(_inventory);
        Log.d("WRAP", "All good");
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        Log.d("TOTO", "Decoding a version " + version + " character");
        _version = version;
        if (version >= 1) {

            Log.d("UNWRAP", "Before class");
            Object aClass = oi.readObject();
            if (aClass instanceof Paladin) {
                _class = new Paladin((Paladin) aClass);
            }
            else if (aClass instanceof Warlock) {
                _class = new Warlock((Warlock) aClass);
            }
            else if (aClass instanceof Barbarian) {
                _class = new Barbarian((Barbarian) aClass);
            }
            Log.d("UNWRAP", "After class");

            Object aRace = oi.readObject();
            if (aRace instanceof HalfElf) {
                _race = (HalfElf) aRace;
            }
            else if (aRace instanceof MountainDwarf) {
                _race = (MountainDwarf) aRace;
            }
            else if (aRace instanceof HalfOrc) {
                _race = (HalfOrc) aRace;
            }
            Log.d("UNWRAP", "After race");


            _name = (String) oi.readObject();
            _attributes = (int[]) oi.readObject();

            _level = oi.readInt();
            _hpCurrent = oi.readInt();
            _hpMax = oi.readInt();
            _hpTemp = oi.readInt();
            _hitDice = oi.readInt();
            _armorClass = oi.readInt();
            _atkBonus = oi.readInt();
            _dmgBonus = oi.readInt();
            _gold = oi.readInt();

            _skills = (LinkedList<Skill>) oi.readObject();
            if (version >= 5) {
                _savingThrows = (LinkedList<SavingThrow>) oi.readObject();
            }
            else {
                LinkedList<Skill> oldSavingThrows = (LinkedList<Skill>) oi.readObject();
                _savingThrows = new LinkedList<>();
                for (Skill s : oldSavingThrows) {
                    Log.d("TOTO", "Creating new save from old " + s._name);
                    _savingThrows.add(new SavingThrow(s));
                }
            }

            _powers = (LinkedList<Power>) oi.readObject();

            _weaponDmgDice = (String) oi.readObject();
            _isWeaponRanged = oi.readBoolean();
            _allItems = (String) oi.readObject();
        }
        if (version >= 2) {
            _spellSlotsCurrent = (int[]) oi.readObject();
            _spellSlotsMax = (int[]) oi.readObject();
        }
        else {
            // Need to force-refresh spell slots as old version didn't serialize them.
            _spellSlotsMax = _class.getSpellSlots(_level);
            _spellSlotsCurrent = new int[_spellSlotsMax.length];
            for (int i = 0; i < _spellSlotsMax.length; i++) {
                _spellSlotsCurrent[i] = _spellSlotsMax[i];
            }
        }

        // Feats added in V3
        if (version >= 3) {
            _feats = (LinkedList<Power>) oi.readObject();
        }
        else {
            _feats = new LinkedList<Power>();
        }

        // Fettles added in V4
        if (version >= 4) {
            _effect = (LinkedList<Fettle>) oi.readObject();
        }
        else {
            _effect = new LinkedList<Fettle>();
        }

        // V6: armor and other equipment
        if (version >= 6) {
            _equippedArmor = (Armor)oi.readObject();
            _equippedWeapon = (Weapon) oi.readObject();
            _equippedItems = (LinkedList<Item>) oi.readObject();
            _equippedShield = (Armor)oi.readObject();
        }
        else {
            _equippedArmor = new Armor(Enumerations.ArmorTypes.NONE, 0, null);
            _equippedWeapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
            _equippedItems = new LinkedList<>();
            _equippedShield = new Armor(Enumerations.ArmorTypes.NONE, 0, null);
        }
        // V7: off hand weapons
        if (version >= 7) {
            _offHandWeapon = (Weapon) oi.readObject();
        }
        else {
            _offHandWeapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
        }


        // V8: added inventory list
        if (version >= 8) {
            _inventory = (LinkedList<Externalizable>)oi.readObject();
        }
        else {
            _inventory = new LinkedList<>();
        }
    }

    public Character(){
        this("New hero", new Paladin(), new HalfElf(), 1, new int[6]);
    };

    public Character(String name, Class iClass, Race iRace, int level, int[] attr) {
        _name = name;
        _class = iClass;
        _race = iRace;
        _level = level;

        _attributes = new int[6];
        for (int i = 0; i < 6; i++) {
            _attributes[i] = attr[i];
        }

        _atkBonus = 0;
        _dmgBonus = 0;
        _weaponDmgDice = "1D4";
        _isWeaponRanged = false;
        _gold = 0;
        _allItems = "10 torches";


        initLevel();

        doLongRest();
    }


    public LinkedList<Power> getPowers() {
        if (_powers == null) {
            _powers = _class.getPowers(_level, this);
        }
        return _powers;
    }

    public LinkedList<Power> getFeats() {
        if (_feats == null) {
            _feats = new LinkedList<Power>();
        }

        return _feats;
    }
    public LinkedList<Fettle> getEffectsFromRaceAndClass() {
        if (_effect == null) {
            _effect = new LinkedList<Fettle>();
        }
        return _effect;
    }

    public void refresh() {
        initLevel();
        doLongRest();
    }

    private void initLevel() {
        refreshFettles();
        recomputeSkills();
        recomputeSavingThrows();

        _hitDice = _level;
        _spellSlotsMax = _class.getSpellSlots(_level);
        _hpMax = _class.getHitDie() + (_level - 1) * (int) Math.ceil(_class.getHitDie() / 2 + 1) + _level * getModifier(Enumerations.Attributes.CON);
        _powers = _class.getPowers(_level, this);
    }

    public void doLongRest() {
        _hpCurrent = _hpMax;
        _hpTemp = 0;
        _hitDice = _level;


        _spellSlotsCurrent = new int[_spellSlotsMax.length];
        for (int i = 0; i < _spellSlotsMax.length; i++) {
            _spellSlotsCurrent[i] = _spellSlotsMax[i];
        }

        if (_powers == null) {
            _powers = _class.getPowers(_level, this);
        }
        for (Power p : getPowers()) {
            p._left = p._max;
        }
    }

    public void doShortRest() {
        if (_powers == null) {
            _powers = _class.getPowers(_level, this);
        }
        for (Power p : getPowers()) {
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

        // Bonus from equipment ?
        for (Fettle property : getFettles()) {
            if (property._type == Enumerations.FettleType.ATTRIBUTE_MODIFIER && property._describer == iAttr.ordinal()) {
                value += property._value;
                break;
            }
        }

        return (int) Math.floor((value - 10) / 2);
    }

    public int getProficiencyBonus() {
        double levelDouble = _level;
        return (int) (1 + Math.ceil(levelDouble / 4));
    }


    public void recomputeSkills() {
        if (_skills == null || _skills.size() == 0) {
            initSkills();
        }

        for (Skill skill : _skills) {
            skill.recompute(this);
        }
    }

    public int getAttacksPerRound() {
        return _class.getAttacksPerRound(_level);
    }

    public void equipWeapon(Weapon newWeapon) {
        _equippedWeapon = newWeapon;
    }

    private void initSkills() {
        _skills = new LinkedList<>();
        _skills.add(new Skill(Enumerations.Skills.ACROBATICS.toString(), Enumerations.Attributes.DEX));
        _skills.add(new Skill(Enumerations.Skills.ANIMAL_HANDLING.toString(), Enumerations.Attributes.WIS));
        _skills.add(new Skill(Enumerations.Skills.ARCANA.toString(), Enumerations.Attributes.INT));
        _skills.add(new Skill(Enumerations.Skills.ATHLETICS.toString(), Enumerations.Attributes.STR));
        _skills.add(new Skill(Enumerations.Skills.DECEPTION.toString(), Enumerations.Attributes.CHA));
        _skills.add(new Skill(Enumerations.Skills.HISTORY.toString(), Enumerations.Attributes.INT));
        _skills.add(new Skill(Enumerations.Skills.INSIGHT.toString(), Enumerations.Attributes.WIS));
        _skills.add(new Skill(Enumerations.Skills.INTIMIDATION.toString(), Enumerations.Attributes.CHA));
        _skills.add(new Skill(Enumerations.Skills.INVESTIHATION.toString(), Enumerations.Attributes.INT));
        _skills.add(new Skill(Enumerations.Skills.MEDICINE.toString(), Enumerations.Attributes.WIS));
        _skills.add(new Skill(Enumerations.Skills.NATURE.toString(), Enumerations.Attributes.INT));
        _skills.add(new Skill(Enumerations.Skills.PERCEPTION.toString(), Enumerations.Attributes.WIS));
        _skills.add(new Skill(Enumerations.Skills.PERFORMANCE.toString(), Enumerations.Attributes.CHA));
        _skills.add(new Skill(Enumerations.Skills.PERSUASION.toString(), Enumerations.Attributes.CHA));
        _skills.add(new Skill(Enumerations.Skills.RELIGION.toString(), Enumerations.Attributes.INT));
        _skills.add(new Skill(Enumerations.Skills.SLEIGHT_OF_HAND.toString(), Enumerations.Attributes.DEX));
        _skills.add(new Skill(Enumerations.Skills.STEALTH.toString(), Enumerations.Attributes.DEX));
        _skills.add(new Skill(Enumerations.Skills.SURVIVAL.toString(), Enumerations.Attributes.WIS));
    }


    public void recomputeSavingThrows() {
        if (_savingThrows == null || _savingThrows.size() == 0) {
            initSavingThrows();
        }

        for (SavingThrow skill : _savingThrows) {
            skill.recompute(this);
        }
    }

    public void refreshFettles() {
        if (_effect == null) {
            _effect = new LinkedList<Fettle>();
        }
        _effect.clear();

        for (Fettle effect : _race.getFettles()) {
            _effect.add(effect);
        }
        for (Fettle effect : _class.getFettles(this)) {
            _effect.add(effect);
        }
    }

    private void initSavingThrows() {
        _savingThrows = new LinkedList<>();
        _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.STR.toString(), Enumerations.Attributes.STR));
        _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.DEX.toString(), Enumerations.Attributes.DEX));
        _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.CON.toString(), Enumerations.Attributes.CON));
        _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.INT.toString(), Enumerations.Attributes.INT));
        _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.WIS.toString(), Enumerations.Attributes.WIS));
        _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.CHA.toString(), Enumerations.Attributes.CHA));
    }

    public boolean hasFeat(String s) {
        for (Power p : _feats) {
            if (p._name.toLowerCase().equals(s.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public HashSet<Fettle> getFettles() {
        HashSet<Fettle> properties = new HashSet<>();

        if (_equippedShield != null) for (Fettle property : _equippedShield._magicProperties) {
                properties.add(property);
        }
        if (_equippedWeapon != null) for (Fettle property : _equippedWeapon._magicProperties) {
                properties.add(property);
        }
        if (_offHandWeapon != null) for (Fettle property : _offHandWeapon._magicProperties) {
                properties.add(property);
        }
        if (_equippedArmor != null) for (Fettle property : _equippedArmor._magicProperties) {
                properties.add(property);
        }
        if (_inventory != null) for (Externalizable item : _inventory) {
            if (item instanceof Item) {
                for (Fettle property : ((Item)item)._magicProperties) {
                        properties.add(property);
                }
            }
        }

        for (Fettle property : getEffectsFromRaceAndClass()) {
            properties.add(property);
        }

        return properties;
    }
}
