package com.guigeek.devilopers.dd5charactersheet.character;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by ggallani on 22/02/2016.
 */
public class SavingThrow extends Skill implements Externalizable {

    public static final long serialVersionUID = 16L;
    int _version = 2;

    public String _name;
    public Enumerations.Attributes _attribute;
    public boolean _isProficient;
    public int _score;

    public boolean hasAdvantage = false, hasDisadvantage = false;

    public SavingThrow() {}

    public SavingThrow (Skill iSkill) {
        _name = iSkill._name;
        _attribute = iSkill._attribute;
        _isProficient = iSkill._isProficient;
        _score = iSkill._score;
        hasAdvantage = iSkill.hasAdvantage;
        hasDisadvantage = iSkill.hasDisadvantage;
    }

    public SavingThrow (String iName, Enumerations.Attributes iAttr) {
        _name = iName;
        _attribute = iAttr;
        _isProficient = false;
        _score = 0;
    }

    @Override
    public void recompute(Character iChar) {
        _score = iChar.getModifier(_attribute);

        boolean isProficientFromFettle = false;
        int bonus = 0;
        for (Fettle fettle : iChar.getFettles()) {
            if (fettle._type == Enumerations.FettleType.SAVING_THROW_MODIFIER &&
                    (fettle._describer == Enumerations.SavingThrows.ALL.ordinal() ||  Enumerations.SavingThrows.values()[fettle._describer].toString().equals(_name)))
            {
                bonus = Math.max(bonus, fettle._value);
            }
            else if (fettle._type == Enumerations.FettleType.SAVING_THROW_PROFICIENCY &&
                    (fettle._describer == Enumerations.SavingThrows.ALL.ordinal() ||  Enumerations.SavingThrows.values()[fettle._describer].toString().equals(_name)))
            {
                isProficientFromFettle = true;
            }
        }
        if (_isProficient || isProficientFromFettle) {
            _score += iChar.getProficiencyBonus();
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
    }
}
