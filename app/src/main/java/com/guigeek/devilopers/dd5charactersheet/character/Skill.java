package com.guigeek.devilopers.dd5charactersheet.character;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by ggallani on 22/02/2016.
 */
public class Skill implements Externalizable {

    public static final long serialVersionUID = 15L;
    int _version = 3;

    public String _name;
    public Enumerations.Attributes _attribute;
    public boolean _isProficient, _isDoublyProficient;
    public int _score;

    public boolean hasAdvantage = false, hasDisadvantage = false;

    public Skill() {
    }

    public Skill (String iName, Enumerations.Attributes iAttr) {
        _name = iName;
        _attribute = iAttr;
        _isProficient = false;
        _score = 0;
        _isDoublyProficient = false;
    }

    public void recompute(Character iChar) {
        int bonus = 0;
        boolean isProficientFromFettle = false;
        for (Fettle fettle : iChar.getFettles()) {
            if (fettle._type == Enumerations.FettleType.ABILITY_CHECK_MODIFIER &&
                    (fettle._describer == Enumerations.Skills.ALL.ordinal() ||  Enumerations.Skills.values()[fettle._describer].toString().equals(_name)))
            {
                bonus = Math.max(bonus, fettle._value);
            }
            else if (fettle._type == Enumerations.FettleType.ABILITY_PROFICIENCY &&
                    (fettle._describer == Enumerations.Skills.ALL.ordinal() ||  Enumerations.Skills.values()[fettle._describer].toString().equals(_name)))
            {
                isProficientFromFettle = true;
            }
        }

        _score = iChar.getModifier(_attribute);
        if (_isProficient || isProficientFromFettle) {
            _score += iChar.getProficiencyBonus();

            if (_isDoublyProficient) {
                _score += iChar.getProficiencyBonus();
            }
        }

        _score += bonus;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_name);
        oo.writeObject(_attribute);
        oo.writeBoolean(_isProficient);
        oo.writeInt(_score);

        oo.writeBoolean(hasAdvantage);
        oo.writeBoolean(hasDisadvantage);
        oo.writeBoolean(_isDoublyProficient);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;

        if (_version >= 1) {
            _name = (String)oi.readObject();
            _attribute = (Enumerations.Attributes)oi.readObject();
            _isProficient = oi.readBoolean();
            _score = oi.readInt();
        }
        if (_version >= 2) {
            hasAdvantage = oi.readBoolean();
            hasDisadvantage = oi.readBoolean();
        }
        if (_version >= 3) {
            _isDoublyProficient = oi.readBoolean();
        }
    }
}
