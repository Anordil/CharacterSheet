package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Fettle  implements Externalizable {

    public static final long serialVersionUID = 13L;
    int _version = 3;

    public Enumerations.FettleType _type;
    public int _describer;
    public int _value;

    public Fettle(){}


    public Fettle(Enumerations.FettleType iType, int iValue, int iDescriber) {
        _type = iType;
        _value = iValue;
        _describer = iDescriber;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_type);
        oo.writeInt(_describer);
        oo.writeInt(_value);
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
    }

    @Override
    public String toString() {
        switch(_type) {
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
            case ABILITY_CHECK_ADVANTAGE:
                return "Advantage on " + Enumerations.Skills.values()[_describer].toString() + " checks";
            case ABILITY_CHECK_DISADVANTAGE:
                return "Disadvantage on " + Enumerations.Skills.values()[_describer].toString() + " checks";
            case ABILITY_CHECK_MODIFIER:
                return (_value >= 0 ? "+" : "") + _value + " to " + Enumerations.Skills.values()[_describer].toString() + " checks";
            default:
                return super.toString();
        }
    }
}