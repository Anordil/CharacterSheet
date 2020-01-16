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


public class Warlock_blade_fiend extends Warlock_base {
    static final long serialVersionUID = 215L;

    public Warlock_blade_fiend(){}


    @Override
    public String getClassName() {
        return App.getResString(R.string.class_warlock_blade_fiend);
    }


    @Override
    public int getAttacksPerRound(Character iCharacter) {
        boolean hasThirstingBlade = false;
        for (Power feat : iCharacter.getFeats()) {
            if (feat._name.contains("Thirsting Blade")) {
                hasThirstingBlade = true;
                break;
            }
        }
        return hasThirstingBlade ? 2:1;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel);

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Dark One's Blessing!");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Dark One's Own Luck!");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Fiendish Resilience!");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Hurl through Hell!");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);


        if (iLevel >= 1) {
            int thp = iLevel + iCharac.getModifier(Enumerations.Attributes.CHA);
            powers.add(new Power("Dark One's Blessing", "Reducing a hostile creature to 0 HP grants " + thp + " temporary HP", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
        	powers.add(new Power("Dark One's own luck", "Add 1D10 to an ability check or saving throw, possibly after rolling.", "Melee", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 10) {
    		powers.add(new Power("Fiendish Resilience", "After a short or long rest, choose one damage type to gain resistance to.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Hurl through Hell", "When hitting a creature with a melee attack, teleports it to Hell. It reappears at the end of your next turn and is dealt 10D10 psychic damage if it's not a fiend.", "Melee", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
