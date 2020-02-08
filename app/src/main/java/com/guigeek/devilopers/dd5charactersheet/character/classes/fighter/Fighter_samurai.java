package com.guigeek.devilopers.dd5charactersheet.character.classes.fighter;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Fighter_samurai extends BaseArchetype {
    static final long serialVersionUID = 2609L;

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_samurai);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained ");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained ");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained ");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained ");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained ");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
