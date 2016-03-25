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
    int _version = 1;

    public String _name;
    public Enumerations.Attributes _attribute;
    public boolean _isProficient;
    public int _score;

    public Skill() {
    }

    public Skill (String iName, Enumerations.Attributes iAttr) {
        _name = iName;
        _attribute = iAttr;
        _isProficient = false;
        _score = 0;
    }

    public void recompute(Character iChar) {
        _score = iChar.getModifier(_attribute);
        if (_isProficient) {
            _score += iChar.getProficiencyBonus();
        }
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_name);
        oo.writeObject(_attribute);
        oo.writeBoolean(_isProficient);
        oo.writeInt(_score);
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
    }
}
