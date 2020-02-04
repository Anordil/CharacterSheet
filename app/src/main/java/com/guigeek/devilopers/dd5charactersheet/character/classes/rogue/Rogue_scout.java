package com.guigeek.devilopers.dd5charactersheet.character.classes.rogue;

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


public class Rogue_scout extends BaseArchetype {
    static final long serialVersionUID = 2076L;

    public Rogue_scout(){}

    @Override
    public String getName() {
        return App.getResString(R.string.rogue_scout);
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (character._level >= 9) {
            fettles.add(new Fettle(Enumerations.FettleType.MOVEMENT_SPEED_MODIFIER, 10, 0));
        }

        return fettles;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Skirmisher");
            levelUp.add("Gained Survivalist");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Superior Mobility");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Ambush Master");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Sudden Strike");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Skirmisher", "You can move up to half your speed as a reaction when an enemy ends its turn within 5 feet of you. This movement doesn’t provoke opportunity attacks.", "", -1, -1, true, Enumerations.ActionType.REACTION));
            powers.add(new Power("Survivalist", "You gain proficiency in the Nature and Survival skills if you don’t already have it. Your proficiency bonus is doubled for any ability check you make that uses either of those proficiencies.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Superior Mobility", "Your walking speed increases by 10 feet. If you have a climbing or swimming speed, this increase applies to that speed as well.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 13) {
            powers.add(new Power("Ambush Master", "You have advantage on initiative rolls. In addition, the first creature you hit during the first round of a combat becomes easier for you and others to strike; attack rolls against that target have advantage until the start of your next turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Sudden Strike", "If you take the Attack action on your turn, you can make one additional attack as a bonus action. This attack can benefit from your Sneak Attack even if you have already used it this turn, but you can’t use your Sneak Attack against the same target more than once in a turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
