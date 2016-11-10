package com.guigeek.devilopers.dd5charactersheet.item;

import android.util.Log;

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
    int _version = 3;


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
                break;
            case DAGGER:
                break;
            case GREAT_CLUB:
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
                break;
            case JAVELIN:
                break;
            case LIGHT_HAMMER:
                break;
            case MACE:
                break;
            case QUARTERSTAFF:
                break;
            case SICKLE:
                break;
            case SPEAR:
                break;
            case LIGHT_CROSSBOW:
                break;
            case DART:
                break;
            case SHORTBOW:
                break;
            case SLING:
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
                break;
            case FLAIL:
                break;
            case GLAIVE:
                break;
            case GREATAXE:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _diceCount = 1;
                _diceValue = 12;
                _damageType = Enumerations.DamageTypes.SLASHING;
                break;
            case GREATSWORD:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.TWO_HANDED;
                _weight = Enumerations.WeaponWeightCategory.HEAVY;
                _diceCount = 2;
                _diceValue = 6;
                _damageType = Enumerations.DamageTypes.SLASHING;
                break;
            case HALBERD:
                break;
            case LANCE:
                break;
            case LONGSWORD:
                break;
            case MAUL:
                break;
            case MORNINGSTAR:
                break;
            case PIKE:
                break;
            case RAPIER:
                break;
            case SCIMITAR:
                break;
            case SHORTSWORD:
                break;
            case TRIDENT:
                break;
            case WAR_PICK:
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
                break;
            case WHIP:
                break;
            case BLOWGUN:
                break;
            case HAND_CROSSBOW:
                break;
            case HEAVY_CROSSBOW:
                break;
            case LONGBOW:
                break;
            case NET:
                break;
            case UNARMED:
                _distance = Enumerations.WeaponDistanceTypes.MELEE;
                _hands = Enumerations.WeaponHandCount.ONE_HANDED;
                _weight = Enumerations.WeaponWeightCategory.LIGHT;
                _diceCount = 1;
                _diceValue = 1;
                _damageType = Enumerations.DamageTypes.BLUDGEONING;
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
        oo.writeInt(_version);
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
    }
}
