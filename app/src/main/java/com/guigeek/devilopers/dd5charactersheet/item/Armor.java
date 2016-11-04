package com.guigeek.devilopers.dd5charactersheet.item;

import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

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

    public Armor(){}

    public Armor(Enumerations.ArmorTypes type) {
        _type = type;
        _name = type.toString();
        _price = 0;
        _weight = 0;
        _hasStealthDisadvantage = isStealthDisadvantage(type);
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
        int armorAC = 0;

        int dexBonusLightArmor = iCharacter.getModifier(Enumerations.Attributes.DEX);
        int dexBousMediumArmor = Math.min(dexBonusLightArmor, 2);

        switch (_type) {
            case PADDED: { armorAC = 11 + dexBonusLightArmor; break; }
            case LEATHER: { armorAC = 11 + dexBonusLightArmor; break; }
            case STUDDED_LEATHER: { armorAC = 12 + dexBonusLightArmor; break; }
            case HIDE: { armorAC = 12 + dexBousMediumArmor; break; }
            case CHAIN_SHIRT: { armorAC = 13 + dexBousMediumArmor; break; }
            case SCALE_MAIL: { armorAC = 14 + dexBousMediumArmor; break; }
            case BREASTPLATE: { armorAC = 14 + dexBousMediumArmor; break; }
            case HALF_PLATE: { armorAC = 15 + dexBousMediumArmor; break; }
            case RING_MAIL: { armorAC = 14; break; }
            case CHAIN_MAIL: { armorAC = 16; break; }
            case SPLINT: { armorAC = 17; break; }
            case PLATE: { armorAC = 18; break; }
        }
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
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;

        if (_version >= 1) {
            _name = (String)oi.readObject();
            _type = (Enumerations.ArmorTypes)oi.readObject();
            _weight = oi.readInt();
            _price = oi.readInt();
            _hasStealthDisadvantage = oi.readBoolean();
        }
    }
}
