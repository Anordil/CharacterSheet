package com.guigeek.devilopers.dd5charactersheet.character.classes.bard;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Bard_valor extends BaseArchetype {
    static final long serialVersionUID = 2205L;

    public Bard_valor(){}

    @Override
    public String getName() {
        return App.getResString(R.string.bard_valor);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained proficiency with Medium armor, shields and Martial weapons");
            levelUp.add("Gained Combat Inspiration");
        }

        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Extra Attack");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Battle Magic");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Combat Inspiration", "A creature that has a Bardic Inspiration die from you can roll that die and add the number rolled to a weapon damage roll it just made. Alternatively, when an attack roll is made against the creature, it can use its reaction to roll the Bardic Inspiration die and add the number rolled to its AC against that attack, after seeing the roll but before knowing whether it hits or misses.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 14) {
            powers.add(new Power("Battle Magic", "When you use your action to cast a bard spell, you can make one weapon attack as a bonus action.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
