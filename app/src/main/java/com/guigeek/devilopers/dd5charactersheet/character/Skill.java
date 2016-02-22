package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Serializable;

/**
 * Created by ggallani on 22/02/2016.
 */
public class Skill implements Serializable {

    public String _name;
    public Enumerations.Attributes _attribute;
    public boolean _isProficient;
    public int _score;

    public Skill (String iName, Enumerations.Attributes iAttr) {
        _name = iName;
        _attribute = iAttr;
        _isProficient = false;
        _score = 10;
    }

    public void recompute(Character iChar) {
        _score = 10 + iChar.getModifier(_attribute);
        if (_isProficient) {
            _score += iChar.getProficiencyBonus();
        }
    }
}
