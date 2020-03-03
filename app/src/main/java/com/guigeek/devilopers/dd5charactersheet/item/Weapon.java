package com.guigeek.devilopers.dd5charactersheet.item;

import android.util.Log;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by ggallani on 07/11/2016.
 */

public class Weapon implements Externalizable {

    public static final long serialVersionUID = 21L;
    int _version = 7;
    static int LATEST = 7;


    public Enumerations.WeaponTypes _type;
    public Enumerations.WeaponDistanceTypes _distance;
    public Enumerations.WeaponHandCount _hands;
    public Enumerations.WeaponWeightCategory _weight;
    public Enumerations.DamageTypes _damageType;

    public boolean _hasReach, _isFinesse;
    public int _distMin, _distMax;

    public int _diceCount, _diceValue;
    public int _diceCountVersatile, _diceValueVersatile;

    public int _magicModifier;
    public LinkedList<Fettle> _magicProperties;

    public String _name;

    public boolean _isMagical, _isMonk;

    public boolean _isFirearm, _isExplosive;
    public int _reload, _misfire;

    public Enumerations.WeaponCategories _weaponCategory;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Weapon) {
            Weapon other = (Weapon) obj;
            return other._type == _type && _name.equals(other._name)
                    && _magicModifier == other._magicModifier
                    && _magicProperties.equals(_magicProperties);
        }

        return super.equals(obj);
    }

    public String getDescription() {
        LinkedList<String> keywordList = new LinkedList<>();

        keywordList.add(_weaponCategory.toString());
        if (_hands == Enumerations.WeaponHandCount.VERSATILE) keywordList.add("Versatile");
        if (_hands == Enumerations.WeaponHandCount.TWO_HANDED) keywordList.add("Two-handed");
        if (_weight == Enumerations.WeaponWeightCategory.HEAVY) keywordList.add("Heavy");
        if (_weight == Enumerations.WeaponWeightCategory.LIGHT) keywordList.add("Light");
        if (_hasReach) keywordList.add("Reach");
        if (_isFinesse) keywordList.add("Finesse");
        if (_isMonk) keywordList.add("Monk");
        if (_distance == Enumerations.WeaponDistanceTypes.DISTANCE) keywordList.add("Ranged (" + _distMin + "-" + _distMax + ")");
        if (_distance == Enumerations.WeaponDistanceTypes.THROWN) keywordList.add("Thrown (" + _distMin + "-" + _distMax + ")");

        String keywords = keywordList.toString();
        String damage = _diceCount + "D" + _diceValue + " " + _damageType + (_hands == Enumerations.WeaponHandCount.VERSATILE ? " (" + _diceCountVersatile + "D" + _diceValueVersatile + ")" : "");

        if (_type != Enumerations.WeaponTypes.NET) {
            keywords += "\n" + damage;
        }

        if (_type == Enumerations.WeaponTypes.NET) {
            keywords += "\nRestrain Large or smaller creatures";
        }
        if (_type == Enumerations.WeaponTypes.LANCE) {
            keywords += "\nDisadvantage to hit adjacent targets";
        }

        return keywords;
    }


    public Weapon(){
        _distance = Enumerations.WeaponDistanceTypes.THROWN;
        _distMin = 20;
        _distMax = 60;
        _hands = Enumerations.WeaponHandCount.ONE_HANDED;
        _weight = Enumerations.WeaponWeightCategory.LIGHT;
        _diceCount = 1;
        _diceValue = 6;
        _damageType = Enumerations.DamageTypes.SLASHING;
    }

    public Weapon(Enumerations.WeaponTypes iType) {
        this(iType, 0, null);
    }

    public Weapon(Enumerations.WeaponTypes iType, int magicModifier, LinkedList<Fettle> magicProperties) {

        _type = iType;
        _name = _type.toString();

        // Force-init
        _distMin = 0;
        _distMax = 0;
        _hasReach = false;
        _isFinesse = false;
        _diceCountVersatile = 0;
        _diceValueVersatile = 0;

        // Magic stuff
        _magicModifier = magicModifier;
        _magicProperties = magicProperties;
        if (_magicProperties == null) {
            _magicProperties = new LinkedList<>();
        }

        // Init generic weapon details
        switch (_type) {
            case CLUB:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _diceCount = 1;
                _diceValue = 4;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _isMonk = true;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case DAGGER:
                _distance = Enumerations.WeaponDistanceTypes.THROWN;
                _distMin = 20;
                _distMax = 60;
                _diceCount = 1;
                _diceValue = 4;
                _isFinesse = true;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case GREAT_CLUB:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _diceCount = 1;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case HANDAXE:
                _distance = Enumerations.WeaponDistanceTypes.THROWN;
                _distMin = 20;
                _distMax = 60;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _isMonk = true;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case JAVELIN:
                _distance = Enumerations.WeaponDistanceTypes.THROWN;
                _distMin = 30;
                _distMax = 120;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _isMonk = true;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case LIGHT_HAMMER:
                _distance = Enumerations.WeaponDistanceTypes.THROWN;
                _distMin = 20;
                _distMax = 60;
                _diceCount = 1;
                _diceValue = 4;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case MACE:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case QUARTERSTAFF:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _hands = Enumerations.WeaponHandCount.VERSATILE;
                _diceCountVersatile = 1;
                _diceValueVersatile = 8;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _isMonk = true;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case SICKLE:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _diceCount = 1;
                _diceValue = 4;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case SPEAR:
                _distance = Enumerations.WeaponDistanceTypes.THROWN;
                _distMin = 20;
                _distMax = 60;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _hands = Enumerations.WeaponHandCount.VERSATILE;
                _diceCountVersatile = 1;
                _diceValueVersatile = 8;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _isMonk = true;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case LIGHT_CROSSBOW:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 80;
                _distMax = 320;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case DART:
                _distance = Enumerations.WeaponDistanceTypes.THROWN;
                _isFinesse = true;
                _distMin = 20;
                _distMax = 60;
                _diceCount = 1;
                _diceValue = 4;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                _isMonk = true;
                break;
            case SHORTBOW:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 80;
                _distMax = 320;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;
            case SLING:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 30;
                _distMax = 120;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 4;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _weaponCategory = Enumerations.WeaponCategories.SIMPLE;
                break;


            case BATTLEAXE:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.VERSATILE;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 8;
                _diceCountVersatile = 1;
                _diceValueVersatile = 10;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case FLAIL:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case GLAIVE:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _hasReach = true;
                _diceCount = 1;
                _diceValue = 10;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case GREATAXE:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _diceCount = 1;
                _diceValue = 12;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case GREATSWORD:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _diceCount = 2;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case HALBERD:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _hasReach = true;
                _diceCount = 1;
                _diceValue = 10;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case LANCE:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _hasReach = true;
                _diceCount = 1;
                _diceValue = 12;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case LONGSWORD:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.VERSATILE;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 8;
                _diceCountVersatile = 1;
                _diceValueVersatile = 10;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case MAUL:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _diceCount = 2;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case MORNINGSTAR:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case PIKE:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _diceCount = 1;
                _diceValue = 10;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                _hasReach = true;
                break;
            case RAPIER:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                _isFinesse = true;
                break;
            case SCIMITAR:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                _isFinesse = true;
                break;
            case SHORTSWORD:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                _isFinesse = true;
                _isMonk = true;
                break;
            case TRIDENT:
                _distance = Enumerations.WeaponDistanceTypes.THROWN;
                _distMin = 20;
                _distMax = 60;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 6;
                _diceValueVersatile = 1;
                _diceValueVersatile = 8;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case WAR_PICK:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case WARHAMMER:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.VERSATILE;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 8;
                _diceCountVersatile = 1;
                _diceValueVersatile = 10;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case WHIP:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 4;
                _isFinesse = true;
                _hasReach = true;
                _damageType = Enumerations.DamageTypes.SLASHING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case BLOWGUN:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 25;
                _distMax = 10;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 1;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case HAND_CROSSBOW:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 30;
                _distMax = 120;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _diceCount = 1;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case HEAVY_CROSSBOW:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 100;
                _distMax = 400;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _diceCount = 1;
                _diceValue = 10;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case LONGBOW:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 150;
                _distMax = 600;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _diceCount = 1;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case NET:
                _distance = Enumerations.WeaponDistanceTypes.THROWN;
                _distMin = 5;
                _distMax = 15;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 0;
                _diceValue = 0;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _weaponCategory = Enumerations.WeaponCategories.MARTIAL;
                break;
            case UNARMED:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _diceCount = 1;
                _diceValue = 1;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
                _weaponCategory = Enumerations.WeaponCategories.UNARMED;
                break;


            case PALM_PISTOL:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 40;
                _distMax = 160;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _diceCount = 1;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.FIREARM;
                _isFirearm = true;
                _misfire = 1;
                _reload = 1;
                break;
            case PISTOL:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 60;
                _distMax = 240;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 10;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.FIREARM;
                _isFirearm = true;
                _misfire = 1;
                _reload = 4;
                break;
            case MUSKET:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 120;
                _distMax = 480;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 12;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.FIREARM;
                _isFirearm = true;
                _misfire = 2;
                _reload = 1;
                break;
            case PEPPERBOX:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 80;
                _distMax = 320;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 1;
                _diceValue = 10;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.FIREARM;
                _isFirearm = true;
                _misfire = 2;
                _reload = 6;
                break;
            case BLUNDERBUSS:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 15;
                _distMax = 60;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 2;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.FIREARM;
                _isFirearm = true;
                _misfire = 2;
                _reload = 1;
                break;
            case BAD_NEWS:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 200;
                _distMax = 800;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 2;
                _diceValue = 12;
                _damageType = Enumerations.DamageTypes.PIERCING;
                _weaponCategory = Enumerations.WeaponCategories.FIREARM;
                _isFirearm = true;
                _misfire = 3;
                _reload = 1;
                _isExplosive = true;
                break;
            case HAND_MORTAR:
                _distance = Enumerations.WeaponDistanceTypes.DISTANCE;
                _distMin = 30;
                _distMax = 60;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.NORMAL;
                _diceCount = 2;
                _diceValue = 8;
                _damageType = Enumerations.DamageTypes.FIRE;
                _weaponCategory = Enumerations.WeaponCategories.FIREARM;
                _isFirearm = true;
                _misfire = 3;
                _reload = 1;
                _isExplosive = true;
                break;

            default:
                break;
        }
    }

    @Override
    public String toString() {
        return _name + " (" + (_magicModifier > 0 ? "+" + _magicModifier + " " : "") + _type.toString() + ")";
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(LATEST);
        oo.writeObject(_type);
        oo.writeObject(_distance);
        oo.writeObject(_weight);
        oo.writeObject(_damageType);

        oo.writeBoolean(_hasReach);
        oo.writeBoolean(_isFinesse);

        oo.writeInt(_distMin);
        oo.writeInt(_distMax);
        oo.writeInt(_diceCount);
        oo.writeInt(_diceValue);
        oo.writeInt(_diceCountVersatile);
        oo.writeInt(_diceValueVersatile);

        oo.writeInt(_magicModifier);
        oo.writeObject(_magicProperties);

        oo.writeObject(_name);
        oo.writeObject(_hands);

        oo.writeBoolean(_isMagical);
        oo.writeBoolean(_isMonk);

        oo.writeBoolean(_isFirearm);
        oo.writeBoolean(_isExplosive);
        oo.writeInt(_misfire);
        oo.writeInt(_reload);

        oo.writeObject(_weaponCategory);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;

        if (_version >= 1) {
            _type = (Enumerations.WeaponTypes) oi.readObject();
            _distance = (Enumerations.WeaponDistanceTypes) oi.readObject();
            _weight = (Enumerations.WeaponWeightCategory) oi.readObject();
            _damageType = (Enumerations.DamageTypes) oi.readObject();

            _hasReach = oi.readBoolean();
            _isFinesse = oi.readBoolean();

            _distMin = oi.readInt();
            _distMax = oi.readInt();
            _diceCount = oi.readInt();
            _diceValue = oi.readInt();
            _diceCountVersatile = oi.readInt();
            _diceValueVersatile = oi.readInt();

            _magicModifier = oi.readInt();
            _magicProperties = (LinkedList<Fettle>)oi.readObject();
        }
        if (version >= 2) {
            _name = (String)oi.readObject();
        }
        else {
            _name = _type.toString();
        }
        if (version >= 3) {
            _hands = (Enumerations.WeaponHandCount)oi.readObject();
        }
        if (version >= 4) {
            _isMagical = oi.readBoolean();
        } else {
            _isMagical = false;
        }

        if (version >= 5) {
            _isMonk = oi.readBoolean();
        } else {
            _isMonk = false;
        }

        if (_version >= 6) {
            _isFirearm = oi.readBoolean();
            _isExplosive = oi.readBoolean();
            _misfire = oi.readInt();
            _reload = oi.readInt();
        } else {
            _isFirearm = false;
            _isExplosive = false;
        }

        if (_version >= 7) {
            _weaponCategory = (Enumerations.WeaponCategories) oi.readObject();
        } else {
            _weaponCategory = Enumerations.WeaponCategories.UNKNOWN;
        }
    }

    public static int getWeaponIcon(Weapon weapon) {
        switch(weapon._type) {
            case CLUB:
                return R.drawable.ic_spiked_mace;
            case DAGGER:
                return R.drawable.ic_broad_dagger;
            case GREAT_CLUB:
                return R.drawable.ic_spiked_mace;
            case HANDAXE:
                return R.drawable.ic_fire_axe;
            case JAVELIN:
                return R.drawable.ic_barbed_spear;
            case LIGHT_HAMMER:
                return R.drawable.ic_flat_hammer;
            case MACE:
                return R.drawable.ic_flanged_mace;
            case QUARTERSTAFF:
                return R.drawable.ic_wizard_staff;
            case SICKLE:
                return R.drawable.ic_sickle;
            case SPEAR:
                return R.drawable.ic_barbed_spear;
            case LIGHT_CROSSBOW:
                return R.drawable.ic_crossbow;
            case DART:
                return R.drawable.ic_thrown_daggers;
            case SHORTBOW:
                return R.drawable.ic_pocket_bow;
            case SLING:
                return R.drawable.ic_slingshot;
            case BATTLEAXE:
                return R.drawable.ic_battle_axe;
            case FLAIL:
                return R.drawable.ic_mace_head;
            case GLAIVE:
                return R.drawable.ic_halberd;
            case GREATAXE:
                return R.drawable.ic_battle_axe;
            case GREATSWORD:
                return R.drawable.ic_broadsword;
            case HALBERD:
                return R.drawable.ic_halberd;
            case LANCE:
                return R.drawable.ic_barbed_spear;
            case LONGSWORD:
                return R.drawable.ic_broadsword;
            case MAUL:
                return R.drawable.ic_flat_hammer;
            case MORNINGSTAR:
                return R.drawable.ic_mace_head;
            case PIKE:
                return R.drawable.ic_barbed_spear;
            case RAPIER:
                return R.drawable.ic_sword_hilt;
            case SCIMITAR:
                return R.drawable.ic_broadsword;
            case SHORTSWORD:
                return R.drawable.ic_broadsword;
            case TRIDENT:
                return R.drawable.ic_trident;
            case WAR_PICK:
                return R.drawable.ic_flat_hammer;
            case WARHAMMER:
                return R.drawable.ic_flat_hammer;
            case WHIP:
                return R.drawable.ic_whip;
            case BLOWGUN:
                return R.drawable.ic_sawed_off_shotgun;
            case HAND_CROSSBOW:
                return R.drawable.ic_crossbow;
            case HEAVY_CROSSBOW:
                return R.drawable.ic_crossbow;
            case LONGBOW:
                return R.drawable.ic_pocket_bow;
            case NET:
                return R.drawable.ic_fishing_net;
            case UNARMED:
                return R.drawable.ic_fist;

            case PISTOL:
            case PALM_PISTOL:
                return R.drawable.ic_crossed_pistols;
            case MUSKET:
                return R.drawable.ic_musket;
            case BLUNDERBUSS:
            case PEPPERBOX:
                return R.drawable.ic_blunderbuss;
            case BAD_NEWS:
            case HAND_MORTAR:
                return R.drawable.ic_mortar;

            default: return R.drawable.ic_fire_axe;
        }
    }
}
