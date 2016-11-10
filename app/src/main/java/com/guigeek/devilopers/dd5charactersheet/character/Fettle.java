package com.guigeek.devilopers.dd5charactersheet.character;

import android.util.Log;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Fettle  implements Externalizable {

    public static final long serialVersionUID = 13L;
    int _version = 4;

    public Enumerations.FettleType _type;
    public int _describer;
    public int _value;
    public String _valueStr;
    boolean isValueString = false;

    public Fettle(){}


    public Fettle(Enumerations.FettleType iType, int iValue, int iDescriber) {
        _type = iType;
        _value = iValue;
        isValueString = false;
        _describer = iDescriber;
    }

    public Fettle(Enumerations.FettleType iType, String iValue, int iDescriber) {
        _type = iType;
        _valueStr = iValue;
        isValueString = true;
        _describer = iDescriber;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_type);
        oo.writeInt(_describer);
        oo.writeInt(_value);

        oo.writeObject(_valueStr);
        oo.writeBoolean(isValueString);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;

        if (_version >= 2) {
            _type = (Enumerations.FettleType)oi.readObject();
            _describer = oi.readInt();
        }
        if (_version >= 3) {
            _value = oi.readInt();
        }
        if (_version >= 4) {
            _valueStr = (String)oi.readObject();
            isValueString = oi.readBoolean();
        }
        else {
            isValueString = false;
        }
    }

    @Override
    public int hashCode() {
        return 649 * _type.hashCode() + _describer;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Fettle) {
            Fettle other = (Fettle)o;

            return other._type == _type && other._describer == _describer;
        }
        return super.equals(o);
    }

    @Override
    public String toString() {


        switch(_type) {
            case TEXT_FETTLE:
                return _valueStr;
            case ATTACK_DAMAGE_DICE:
                return "+" + _valueStr + "(" + Enumerations.DamageTypes.values()[_describer].toString() + ")" + " to attack damage";
            case DAMAGE_RESISTANCE:
                return "Damage resistance: " + Enumerations.DamageTypes.values()[_describer].toString();
            case DAMAGE_VULNERABILITY:
                return "Damage vulnerability: " + Enumerations.DamageTypes.values()[_describer].toString();
            case SAVING_THROW_ADVANTAGE:
                return "Advantage to saving throws against " + Enumerations.SavingThrows.values()[_describer].toString();
            case SAVING_THROW_DISADVANTAGE:
                return "Disadvantage to saving throws against " + Enumerations.SavingThrows.values()[_describer].toString();
            case SAVING_THROW_MODIFIER:
                return (_value >= 0 ? "+" : "") + _value + " to " + Enumerations.SavingThrows.values()[_describer].toString() + " saving throws";
            case IMMUNITY:
                return "Immune to " + Enumerations.Immunities.values()[_describer].toString();
            case ATTRIBUTE_MODIFIER:
                return (_value >= 0 ? "+" : "") + _value + " to " + Enumerations.Attributes.values()[_describer].toString();
            case ABILITY_CHECK_ADVANTAGE:
                return "Advantage on " + Enumerations.Skills.values()[_describer].toString() + " checks";
            case ABILITY_CHECK_DISADVANTAGE:
                return "Disadvantage on " + Enumerations.Skills.values()[_describer].toString() + " checks";
            case ABILITY_CHECK_MODIFIER:
                return (_value >= 0 ? "+" : "") + _value + " to " + Enumerations.Skills.values()[_describer].toString() + " checks";
            case ARMOR_CLASS_MODIFIER:
                return (_value >= 0 ? "+" : "") + _value + " to AC";
            case MOVEMENT_SPEED_MODIFIER:
                return (_value >= 0 ? "+" : "") + _value + " to movement speed";
            case ATTACK_BONUS_MODIFIER:
                return (_value >= 0 ? "+" : "") + _value + " to attack rolls";
            case ATTACK_DAMAGE_MODIFIER:
                return (_value >= 0 ? "+" : "") + _value + "(" + Enumerations.DamageTypes.values()[_describer].toString() + ")" + " to attack damage";
            default:
                return super.toString();
        }
    }
}