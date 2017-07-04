package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;


public class Sorcerer_wild extends Sorcerer_base implements Externalizable {


    public static final long serialVersionUID = 208L;
    protected int _version = 1;


    public Sorcerer_wild(){}
    public Sorcerer_wild(Sorcerer_wild other) {
        _spellSlots = other._spellSlots;
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_spellSlots);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        _version = version;
        if (version >= 1) {
            _spellSlots = (int[][])oi.readObject();
        }
    }

    @Override
    public String getName() {
        return App.getResString(R.string.class_sorcerer_wild);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel);

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Magic Surge!");
            levelUp.add("You gained Tides of Chaos!");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Bend Luck!");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Controlled Chaos!");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("You gained Spell Bombardment!");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);


        if (iLevel >= 1) {
            powers.add(new Power("Magic Surge", "The DM can have you roll a D20 after casting a spell. On a 1, apply a random Magic Surge effect.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Tides of Chaos", "Gain advantage on an attack roll, ability check or saving throw. While this feature is exhausted, the DM can have you roll a Magic Surge - you then regain this feature. Otherwise, you regain it on a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
        	powers.add(new Power("Bend Luck", "When a creature that you can see makes an attack roll, ability check or saving throw, you may spend 2 Sorcery points as a reaction to roll 1D4 and apply the result as a bonus or malus. May be done after the creature's roll.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }

        if (iLevel >= 14) {
    		powers.add(new Power("Controlled Chaos", "You can roll twice and choose the roll to keep for Magic Surges.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
    	}

        if (iLevel >= 18) {
            powers.add(new Power("Spell Bombardment", "When you roll damage for a spell and a dice rolls its maximum, roll it again and add the result to the damage. Use only once per turn.", "Melee", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
