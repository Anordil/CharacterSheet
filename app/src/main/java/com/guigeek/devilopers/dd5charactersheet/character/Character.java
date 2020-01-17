package com.guigeek.devilopers.dd5charactersheet.character;

import android.util.Log;

import com.guigeek.devilopers.dd5charactersheet.character.classes.Barbarian_base;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BloodHunter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Paladin_vengeance;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Rogue;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Sorcerer;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock;
import com.guigeek.devilopers.dd5charactersheet.character.races.Dragonborn;
import com.guigeek.devilopers.dd5charactersheet.character.races.Elf;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfElf;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfOrc;
import com.guigeek.devilopers.dd5charactersheet.character.races.Human;
import com.guigeek.devilopers.dd5charactersheet.character.races.Dwarf;
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
    public int _version = 10;

    public Class _class;
    public Class _secondaryClass;
    public Race _race;
    public String _name;
    public int[] _attributes;

    public int _level;
    public int _levelSecondaryClass;
    public int _hpCurrent, _hpMax, _hpTemp, _hitDice, _hitDiceSecondary;
    public int _armorClass;
    public int[] _spellSlotsCurrent, _spellSlotsMax;
    public int _atkBonus, _dmgBonus;
    public int _gold;

    public LinkedList<Skill> _skills;
    public LinkedList<SavingThrow> _savingThrows;
    public LinkedList<Power> _powers;
    public LinkedList<Power> _feats;


    public LinkedList<Fettle> _effect;
    public String _allItems;


    public Armor _equippedArmor;
    public Armor _equippedShield;
    public Weapon _equippedWeapon, _offHandWeapon;

    public LinkedList<Externalizable> _inventory;


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);

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

        oo.writeObject(_allItems);

        oo.writeObject(_spellSlotsCurrent);
        oo.writeObject(_spellSlotsMax);

        oo.writeObject(_feats);
        oo.writeObject(_effect);

        oo.writeObject(_equippedArmor);
        oo.writeObject(_equippedWeapon);
        oo.writeObject(null);
        oo.writeObject(_equippedShield);
        oo.writeObject(_offHandWeapon);

        oo.writeObject(_inventory);

        oo.writeObject(_secondaryClass);
        oo.writeInt(_levelSecondaryClass);
        oo.writeInt(_hitDiceSecondary);
        Log.d("WRAP", "Finished writing");
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        Log.d("TOTO", "Decoding a version " + version + " character");
        _version = version;

            Object aClass = oi.readObject();
        Log.d("UNWRAP", "class " + aClass.getClass());

        if (aClass instanceof Barbarian_base) {
            _class = (Barbarian_base) aClass;
        }
        else if (aClass instanceof BloodHunter) {
            _class = (BloodHunter) aClass;
        }
        else if (aClass instanceof Paladin_vengeance) {
            _class = (Paladin_vengeance) aClass;
        }
        else if (aClass instanceof Rogue) {
            _class = (Rogue) aClass;
        }
        else if (aClass instanceof Sorcerer) {
            _class = (Sorcerer) aClass;
        }
        else if (aClass instanceof Warlock) {
            _class = (Warlock) aClass;
        }

            Log.d("UNWRAP", "After class");

            Object aRace = oi.readObject();
            if (aRace instanceof HalfElf) {
                _race = (HalfElf) aRace;
            }
            else if (aRace instanceof Dwarf) {
                _race = (Dwarf) aRace;
            }
            else if (aRace instanceof HalfOrc) {
                _race = (HalfOrc) aRace;
            }
            else if (aRace instanceof Human) {
                _race = (Human) aRace;
            }
            else if (aRace instanceof Elf) {
                _race = (Elf) aRace;
            }
            else if (aRace instanceof Dragonborn) {
                _race = (Dragonborn) aRace;
            }
//            else if (aRace instanceof Gnome) {
//                _race = (Gnome) aRace;
//            }
//            else if (aRace instanceof Halfling) {
//                _race = (Halfling) aRace;
//            }
//            else if (aRace instanceof Tiefling) {
//                _race = (Tiefling) aRace;
//            }
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

            _allItems = (String) oi.readObject();


            _spellSlotsCurrent = (int[]) oi.readObject();
            _spellSlotsMax = (int[]) oi.readObject();



            _feats = (LinkedList<Power>) oi.readObject();



            _effect = (LinkedList<Fettle>) oi.readObject();



            try {
                _equippedArmor = (Armor) oi.readObject();
                _equippedWeapon = (Weapon) oi.readObject();
                oi.readObject(); //removed quipped items
                _equippedShield = (Armor) oi.readObject();
            }
            catch (Exception e) {
                _equippedArmor = new Armor(Enumerations.ArmorTypes.NONE, 0, null);
                _equippedWeapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
                oi.readObject(); //removed quipped items
                _equippedShield = new Armor(Enumerations.ArmorTypes.NONE, 0, null);
            }


            try {
                _offHandWeapon = (Weapon) oi.readObject();
            }
            catch (Exception e) {
                _offHandWeapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
            }


            try {
                _inventory = (LinkedList<Externalizable>)oi.readObject();
            }
            catch (Exception e) {
                _inventory = new LinkedList<>();
            }

            Object aSecondaryClass = oi.readObject();
            if (aSecondaryClass != null) {
                if (aSecondaryClass instanceof Barbarian_base) {
                    _secondaryClass = (Barbarian_base) aClass;
                }
                else if (aSecondaryClass instanceof BloodHunter) {
                    _secondaryClass = (BloodHunter) aClass;
                }
                else if (aSecondaryClass instanceof Paladin_vengeance) {
                    _secondaryClass = (Paladin_vengeance) aClass;
                }
                else if (aSecondaryClass instanceof Rogue) {
                    _secondaryClass = (Rogue) aClass;
                }
                else if (aSecondaryClass instanceof Sorcerer) {
                    _secondaryClass = (Sorcerer) aClass;
                }
                else if (aSecondaryClass instanceof Warlock) {
                    _secondaryClass = (Warlock) aClass;
                }
            }
            _levelSecondaryClass = oi.readInt();
            _hitDiceSecondary = oi.readInt();


        if (_equippedArmor == null) {
            _equippedArmor = new Armor(Enumerations.ArmorTypes.NONE, 0, null);
        }
        if (_equippedWeapon == null) {
            _equippedWeapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
        }
        if (_offHandWeapon == null) {
            _offHandWeapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
        }
        if (_equippedShield == null) {
            _equippedShield = new Armor(Enumerations.ArmorTypes.NONE, 0, null);
        }
        if (_inventory == null) {
            _inventory = new LinkedList<>();
        }
    }

    public Character(){
        this("New hero", new Paladin_vengeance(), new HalfElf(), 1, new int[6], null, 0);
    };

    public Character(String name, Class iClass, Race iRace, int level, int[] attr, Class iSecClass, int secondLevel) {
        _name = name;
        _class = iClass;
        _race = iRace;
        _level = level;

        _secondaryClass = iSecClass;
        _levelSecondaryClass = secondLevel;

        _attributes = new int[6];
        for (int i = 0; i < 6; i++) {
            _attributes[i] = attr[i];
        }

        _atkBonus = 0;
        _dmgBonus = 0;
        _gold = 0;
        _allItems = "";

        initLevel();

        doLongRest();
    }


    public LinkedList<Power> getClassPowers() {
        if (_powers == null) { _powers = new LinkedList<>(); }
        _powers = _class.getAllPowers(_level, this);

        if (_secondaryClass != null) {
           for (Power p : _secondaryClass.getAllPowers(_levelSecondaryClass, this)) {
               _powers.add(p);
           }
        }


        return _powers;
    }

    public LinkedList<Power> getFeats() {
        if (_feats == null) {
            _feats = new LinkedList<Power>();
        }

        return _feats;
    }
    private LinkedList<Fettle> getEffectsFromRaceAndClass() {
        LinkedList<Fettle> res = new LinkedList<>();
        for (Fettle effect : _race.getFettles(this)) {
            res.add(effect);
        }
        for (Fettle effect : _class.getAllFettles(this)) {
            res.add(effect);
        }
        if (_secondaryClass != null) {
            for (Fettle effect : _secondaryClass.getAllFettles(this)) {
                res.add(effect);
            }
        }

        return res;
    }


    public void refresh() {
        initLevel();
        doLongRest();
        refreshFettles();
    }

    private void initLevel() {
        refreshFettles();
        recomputeSkills();
        recomputeSavingThrows();

        _hitDice = _level;
        _hitDiceSecondary = _levelSecondaryClass;
        _spellSlotsMax = _class.getSpellSlots(_level);
        if (!_class.isCaster() && _secondaryClass != null &&_secondaryClass.isCaster()) {
            _spellSlotsMax = _secondaryClass.getSpellSlots(_levelSecondaryClass);
        }
        _hpMax = _class.getHitDie() + (_level - 1) * (int) Math.ceil(_class.getHitDie() / 2 + 1) + _level * getModifier(Enumerations.Attributes.CON);
        if (_secondaryClass != null) {
            _hpMax += (_levelSecondaryClass) * (int) Math.ceil(_secondaryClass.getHitDie() / 2 + 1) + _levelSecondaryClass * getModifier(Enumerations.Attributes.CON);
        }

        if (hasFeat("Tough")) {
            _hpMax += 2*getLevel();
        }
        if (hasPower("Dwarven Toughness")) {
            _hpMax += getLevel();
        }
        if (hasPower("Draconic Resilience")) {
            _hpMax += getLevel();
        }

        _powers = getClassPowers();
        _powers.addAll(_race.getRacialFeatures(this));
    }

    public void doLongRest() {
        _hpCurrent = _hpMax;
        _hpTemp = 0;
        _hitDice = Math.min(_level, _hitDice + (int)Math.ceil(((double)_level)/2));
        _hitDiceSecondary = Math.min(_levelSecondaryClass, _hitDiceSecondary + (int)Math.ceil(((double)_levelSecondaryClass)/2));

        Log.d("HD", "HD after long rest " + _hitDice + ", " +  _hitDiceSecondary);

        _spellSlotsCurrent = new int[_spellSlotsMax.length];
        for (int i = 0; i < _spellSlotsMax.length; i++) {
            _spellSlotsCurrent[i] = _spellSlotsMax[i];
        }

        if (_powers == null) {
            _powers = getClassPowers();
            _powers.addAll(_race.getRacialFeatures(this));
        }
        for (Power p : getClassPowers()) {
            p._left = p._max;
        }
    }

    public void doShortRest() {
        if (_powers == null) {
            _powers = getClassPowers();
            _powers.addAll(_race.getRacialFeatures(this));
        }
        for (Power p : getClassPowers()) {
            if (!p._isLongRest) {
                p._left = p._max;
            }
        }
    }


    @Override
    public String toString() {
        return _name + ", Level " + _level + " " + _race.getBaseRaceName() + " " + _class.getQualifiedClassName();

    }

    public void changeHP(int iQuantity) {

        // Consider temp HP
        if (iQuantity < 0) {
            _hpTemp += iQuantity;

            if (_hpTemp < 0) {
                iQuantity = _hpTemp;
                _hpTemp = 0;
            }
            else {
                iQuantity = 0;
            }
        }

        _hpCurrent += iQuantity;
        _hpCurrent = Math.min(_hpCurrent, _hpMax);
        _hpCurrent = Math.max(_hpCurrent, 0);
    }

    // Keep best source of THP
    public void adjustTemporaryHP(int newAmount) {
        _hpTemp = Math.max(_hpTemp, newAmount);
    }

    public void changeHD(int iQuantity) {
        _hitDice += iQuantity;
        _hitDice = Math.min(_hitDice, _level);
        _hitDice = Math.max(_hitDice, 0);
    }

    public void changeHDSecondary(int iQuantity) {
        _hitDiceSecondary += iQuantity;
        _hitDiceSecondary = Math.min(_hitDiceSecondary, _levelSecondaryClass);
        _hitDiceSecondary = Math.max(_hitDiceSecondary, 0);
    }

    public void changeSpellSlot(int level, int diff) {
        _spellSlotsCurrent[level] += diff;
        _spellSlotsCurrent[level] = Math.min(_spellSlotsCurrent[level], _spellSlotsMax[level]);
        _spellSlotsCurrent[level] = Math.max(_spellSlotsCurrent[level], 0);
    }

    public int getModifier(Enumerations.Attributes iAttr) {
        int value = _attributes[iAttr.ordinal()];

        // Bonus from equipment ?
        for (Fettle property : _effect) {
            if (property._type == Enumerations.FettleType.ATTRIBUTE_MODIFIER && property._describer == iAttr.ordinal()) {
                value += property._value;
                break;
            }
        }

        return getModifier(value);
    }

    public int getModifier(int value) {
        return (int) Math.floor((value - 10) / 2);
    }

    public int getProficiencyBonus() {
        double levelDouble = _level + _levelSecondaryClass;
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
        return Math.max(_class.getAttacksPerRound(this), _secondaryClass != null ? _secondaryClass.getAttacksPerRound(this) : 0);
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
        initSavingThrows();

        for (SavingThrow skill : _savingThrows) {
            skill.recompute(this);
        }
    }

    public void refreshFettles() {
        if (_effect == null) {
            _effect = new LinkedList<Fettle>();
        }
        _effect.clear();

        for (Fettle effect : getEffectsFromRaceAndClass()) {
            _effect.add(effect);
        }

        if (_equippedShield != null) for (Fettle property : _equippedShield._magicProperties) {
            _effect.add(property);
        }

        // For weapons, skill attack modifiers as they are handled in the Combat Screen
        if (_equippedWeapon != null) for (Fettle property : _equippedWeapon._magicProperties) {
            if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_DICE
                    || property._type == Enumerations.FettleType.ATTACK_DAMAGE_MODIFIER
                    || property._type == Enumerations.FettleType.ATTACK_BONUS_MODIFIER) {
                continue;
            }
            _effect.add(property);
        }
        if (_offHandWeapon != null) for (Fettle property : _offHandWeapon._magicProperties) {
            if (property._type == Enumerations.FettleType.ATTACK_DAMAGE_DICE
                    || property._type == Enumerations.FettleType.ATTACK_DAMAGE_MODIFIER
                    || property._type == Enumerations.FettleType.ATTACK_BONUS_MODIFIER) {
                continue;
            }
            _effect.add(property);
        }



        if (_equippedArmor != null) for (Fettle property : _equippedArmor._magicProperties) {
            _effect.add(property);
        }
        if (_inventory != null) for (Externalizable item : _inventory) {
            if (item instanceof Item) {
                for (Fettle property : ((Item)item)._magicProperties) {
                    _effect.add(property);
                    Log.d("INVENTORY", "Added a Fettle");
                }
            }
        }
    }

    private void initSavingThrows() {
        if (_savingThrows == null || _savingThrows.size() != 6) {
            _savingThrows = new LinkedList<>();
            _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.STR.toString(), Enumerations.Attributes.STR));
            _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.DEX.toString(), Enumerations.Attributes.DEX));
            _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.CON.toString(), Enumerations.Attributes.CON));
            _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.INT.toString(), Enumerations.Attributes.INT));
            _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.WIS.toString(), Enumerations.Attributes.WIS));
            _savingThrows.add(new SavingThrow(Enumerations.SavingThrows.CHA.toString(), Enumerations.Attributes.CHA));

            // Add class proficiencies
            for (Enumerations.SavingThrows proficientSave: _class.getSavingThrowsProficiencies()) {
                if (proficientSave == Enumerations.SavingThrows.STR) {
                    _savingThrows.get(0)._isProficient = true;
                }
                if (proficientSave == Enumerations.SavingThrows.DEX) {
                    _savingThrows.get(1)._isProficient = true;
                }
                if (proficientSave == Enumerations.SavingThrows.CON) {
                    _savingThrows.get(2)._isProficient = true;
                }
                if (proficientSave == Enumerations.SavingThrows.INT) {
                    _savingThrows.get(3)._isProficient = true;
                }
                if (proficientSave == Enumerations.SavingThrows.WIS) {
                    _savingThrows.get(4)._isProficient = true;
                }
                if (proficientSave == Enumerations.SavingThrows.CHA) {
                    _savingThrows.get(5)._isProficient = true;
                }
            }
        }
    }

    public boolean hasFeat(String s) {
        if (_feats == null) {
            _feats = new LinkedList<>();
        }
        for (Power p : _feats) {
            if (p != null && p._name != null &&  p._name.toLowerCase().equals(s.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
    public boolean hasPower(String s) {
        if (_powers == null) {
            _powers = getClassPowers();
            _powers.addAll(_race.getRacialFeatures(this));
        }
        for (Power p : _powers) {
            if (p != null && p._name != null &&  p._name.toLowerCase().equals(s.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public int getAC() {
        int armorClass = _class.getAC(this);
        if (_secondaryClass != null) {
            armorClass = Math.max(armorClass, _secondaryClass.getAC(this));
        }

        for (Fettle f : getCharacterFettles()) {
            if (f._type == Enumerations.FettleType.ARMOR_CLASS_MODIFIER) {
                armorClass += f._value;
                break;
            }
        }

        return armorClass;
    }

    public HashSet<Fettle> getCharacterFettles() {
        HashSet<Fettle> properties = new HashSet<>();
        for (Fettle effect : _effect) {
            properties.add(effect);
        }

        return properties;
    }

    public int getSpeedInFeet() {
        int baseSpeed = _race.getSpeedInFeet();

        for (Fettle fettle : getCharacterFettles()) {
            if (fettle._type == Enumerations.FettleType.MOVEMENT_SPEED_MODIFIER) {
                baseSpeed += fettle._value;
            }
        }
        return baseSpeed;
    }

    public int getLevel() {
        return _level + _levelSecondaryClass;
    }
}
