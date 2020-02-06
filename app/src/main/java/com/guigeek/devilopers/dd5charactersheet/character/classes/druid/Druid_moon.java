package com.guigeek.devilopers.dd5charactersheet.character.classes.druid;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Druid_moon extends BaseArchetype {
    static final long serialVersionUID = 2504L;

    @Override
    public String getName() {
        return App.getResString(R.string.druid_moon);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Combat Wild Shape");
            levelUp.add("Gained Circle Forms");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Primal Strike");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Elemental Wild Shape");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Thousand Forms");
        }

        if (iNewCharacterLevel >=6 && iNewCharacterLevel % 3 == 0) {
            levelUp.add("The max CR for your Circle Forms increased!");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Combat Wild Shape", "You gain the ability to use Wild Shape on your turn as a bonus action, rather than as an action.\n" +
                    "\n" +
                    "Additionally, while you are transformed by Wild Shape, you can use a bonus action to expend one spell slot to regain 1d8 hit points per level of the spell slot expended.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            int maxCR = iLevel >= 6 ? (int)Math.floor(iLevel/3) : 1;
            powers.add(new Power("Circle Forms", "You can use your Wild Shape to transform into a beast with a max CR of " + maxCR, "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Primal Strike", "Your attacks in beast form count as magical for the purpose of overcoming resistance and immunity to nonmagical attacks and damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Elemental Wild Shape", "You can expend two uses of Wild Shape at the same time to transform into an air elemental, an earth elemental, a fire elemental, or a water elemental.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Thousand Forms", "You can cast the alter self spell at will.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
