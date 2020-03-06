package com.guigeek.devilopers.dd5charactersheet.item;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;

/**
 * Created by ggallani on 04/11/2016.
 */

public class Armor implements Externalizable {

    public static final long serialVersionUID = 20L;
    int _version = 1;

    public Enumerations.ArmorTypes _type;
    public int _price, _weight;
    public String _name;
    public boolean _hasStealthDisadvantage;
    public int _magicModifier;

    public LinkedList<Fettle> _magicProperties;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Armor) {
            Armor other = (Armor) obj;
            return other._type == _type && _name.equals(other._name)
                    && _magicModifier == other._magicModifier
                    && _magicProperties.equals(_magicProperties);
        }

        return super.equals(obj);
    }

    public Armor(){}

    public Armor(Enumerations.ArmorTypes iType) {
        this(iType, 0, null);
    }

    public String getDescription() {
        switch (_type) {
            case PADDED:
            case LEATHER:
                return "Light Armor (AC: 11 + DEX)";
            case STUDDED_LEATHER:
                return "Light armor (AC: 12 + DEX)";

            case HIDE:
                return "Medium armor (AC: 12 + DEX [max 2])";
            case CHAIN_SHIRT:
                return "Medium armor (AC: 13 + DEX [max 2])";
            case BREASTPLATE:
            case SCALE_MAIL:
                return "Medium armor (AC: 14 + DEX [max 2])";
            case HALF_PLATE:
                return "Medium armor (AC: 15 + DEX [max 2])";

            case RING_MAIL:
                return "Heavy armor (AC: 14)";
            case CHAIN_MAIL:
                return "Heavy armor (AC: 16), min STR: 13";
            case SPLINT:
                return "Heavy armor (AC: 17), min STR: 15";
            case PLATE:
                return "Heavy armor (AC: 18), min STR: 15";

            default: return "";
        }
    }

    public int getMinStrength() {
        switch (_type) {
            case CHAIN_MAIL:
                return 13;
            case SPLINT:
            case PLATE:
                return 15;

            default: return 0;
        }
    }

    public Enumerations.Proficiencies getRequiredProficiency() {
        switch (_type) {
            case PADDED:
            case LEATHER:
            case STUDDED_LEATHER:
                return Enumerations.Proficiencies.ARMOR_LIGHT;
            case HIDE:
            case CHAIN_SHIRT:
            case BREASTPLATE:
            case SCALE_MAIL:
            case HALF_PLATE:
                return Enumerations.Proficiencies.ARMOR_MEDIUM;
            case RING_MAIL:
            case CHAIN_MAIL:
            case SPLINT:
            case PLATE:
                return Enumerations.Proficiencies.ARMOR_HEAVY;
            case SHIELD:
                return Enumerations.Proficiencies.SHIELD;

            default: return null;
        }
    }

    public Armor(Enumerations.ArmorTypes type, int magicModifier, LinkedList<Fettle> magicProperties) {
        _type = type;
        _name = type.toString();
        _price = 0;
        _weight = 0;
        _hasStealthDisadvantage = isStealthDisadvantage(type);
        _magicModifier = magicModifier;
        _magicProperties = magicProperties;

        if (_magicProperties == null) {
            _magicProperties = new LinkedList<>();
        }

        if (_hasStealthDisadvantage) {
            boolean alreadyThere = false;
            for (Fettle property : _magicProperties) {
                if (property._type.equals(Enumerations.FettleType.ABILITY_CHECK_DISADVANTAGE) && property._describer == Enumerations.Skills.STEALTH.ordinal()) {
                    alreadyThere = true;
                    break;
                }
            }
            if (!alreadyThere) {
                _magicProperties.add(new Fettle(Enumerations.FettleType.ABILITY_CHECK_DISADVANTAGE, 0, Enumerations.Skills.STEALTH.ordinal()));
            }
        }
    }


    public boolean isStealthDisadvantage(Enumerations.ArmorTypes type) {
        if (type == Enumerations.ArmorTypes.PADDED ||
            type == Enumerations.ArmorTypes.SCALE_MAIL ||
            type == Enumerations.ArmorTypes.HALF_PLATE ||
            type == Enumerations.ArmorTypes.RING_MAIL ||
            type == Enumerations.ArmorTypes.CHAIN_MAIL ||
            type == Enumerations.ArmorTypes.SPLINT ||
            type == Enumerations.ArmorTypes.PLATE) {
            return true;
        }

        return false;
    }


    public int getAC(Character iCharacter) {
        int armorAC = 10;

        int dexBonusLightArmor = iCharacter.getModifier(Enumerations.Attributes.DEX);
        int dexBonusMediumArmor = Math.min(dexBonusLightArmor, 2);

        switch (_type) {
            case NONE: { armorAC = 10 + dexBonusLightArmor; break; }
            case SHIELD: { armorAC = 2; break; }
            case PADDED: { armorAC = 11 + dexBonusLightArmor; break; }
            case LEATHER: { armorAC = 11 + dexBonusLightArmor; break; }
            case STUDDED_LEATHER: { armorAC = 12 + dexBonusLightArmor; break; }
            case HIDE: { armorAC = 12 + dexBonusMediumArmor; break; }
            case CHAIN_SHIRT: { armorAC = 13 + dexBonusMediumArmor; break; }
            case SCALE_MAIL: { armorAC = 14 + dexBonusMediumArmor; break; }
            case BREASTPLATE: { armorAC = 14 + dexBonusMediumArmor; break; }
            case HALF_PLATE: { armorAC = 15 + dexBonusMediumArmor; break; }
            case RING_MAIL: { armorAC = 14; break; }
            case CHAIN_MAIL: { armorAC = 16; break; }
            case SPLINT: { armorAC = 17; break; }
            case PLATE: { armorAC = 18; break; }
            default: return 10;
        }

        return armorAC + _magicModifier;
    }

    @Override
    public String toString() {
        return _name + " (" + (_magicModifier > 0 ? "+" + _magicModifier + " " : "") + _type.toString() + ")";
    }

    public String toHtmlString() {
        return "<b>" + _name + "</b> (" + (_magicModifier > 0 ? "+" + _magicModifier + " " : "") + _type.toString() + ")";
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_type);
        oo.writeObject(_name);
        oo.writeInt(_weight);
        oo.writeInt(_price);
        oo.writeBoolean(_hasStealthDisadvantage);
        oo.writeInt(_magicModifier);
        oo.writeObject(_magicProperties);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;

        if (_version >= 1) {
            _type = (Enumerations.ArmorTypes)oi.readObject();
            _name = (String)oi.readObject();
            _weight = oi.readInt();
            _price = oi.readInt();
            _hasStealthDisadvantage = oi.readBoolean();
            _magicModifier = oi.readInt();
            _magicProperties = (LinkedList<Fettle>)oi.readObject();
        }
    }

    public boolean isHeavy() {
        if (_type == Enumerations.ArmorTypes.RING_MAIL ||
            _type == Enumerations.ArmorTypes.CHAIN_MAIL ||
            _type == Enumerations.ArmorTypes.SPLINT ||
            _type == Enumerations.ArmorTypes.PLATE) {
            return true;
        }

        return false;
    }

    public static int getArmorIcon(Armor armor) {
        switch (armor._type) {
            case NONE:
                return R.drawable.ic_swap_bag;
            case PADDED:
                return R.drawable.ic_leather_vest;
            case LEATHER:
                return R.drawable.ic_leather_vest;
            case STUDDED_LEATHER:
                return R.drawable.ic_leather_vest;
            case HIDE:
                return R.drawable.ic_leather_vest;
            case CHAIN_SHIRT:
                return R.drawable.ic_chain_mail;
            case SCALE_MAIL:
                return R.drawable.ic_scale_mail;
            case BREASTPLATE:
                return R.drawable.ic_armor_vest;
            case HALF_PLATE:
                return R.drawable.ic_armor_vest;
            case RING_MAIL:
                return R.drawable.ic_breastplate;
            case CHAIN_MAIL:
                return R.drawable.ic_breastplate;
            case SPLINT:
                return R.drawable.ic_breastplate;
            case PLATE:
                return R.drawable.ic_breastplate;
            case SHIELD:
                return R.drawable.ic_round_shield;
            default:
                return R.drawable.ic_armor_vest;
        }
    }
}
