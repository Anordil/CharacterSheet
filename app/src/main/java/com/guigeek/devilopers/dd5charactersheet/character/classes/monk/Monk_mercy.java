package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Monk_mercy extends BaseArchetype {
    static final long serialVersionUID = 2707L;
    public Monk_mercy(){}

    @Override
    public String getName() {
        return App.getResString(R.string.monk_mercy);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained ");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained ");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained ");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You gained ");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
