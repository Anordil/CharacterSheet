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

public class Fighter_purple extends BaseArchetype {
    static final long serialVersionUID = 2607L;

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_purple);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Rallying Cry");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Royal Envoy");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Inspiring Surge");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Bulwark");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Improved Inspiring Surge");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Rallying Cry", "When you use your Second Wind feature, you can choose up to three creatures within 60 feet of you that are allied with you. Each one regains hit points equal to your fighter level, provided that the creature can see or hear you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Royal Envoy", "You gain proficiency in the Persuasion skill. If you are already proficient in it, you gain proficiency in one of the following skills of your choice: Animal Handling, Insight, Intimidation, or Performance.\n" +
                    "\n" +
                    "Your proficiency bonus is doubled for any ability check you make that uses Persuasion. You receive this benefit regardless of the skill proficiency you gain from this feature.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Inspiring Surge", "When you use your Action Surge feature, you can choose " + (iLevel >= 18 ? "two creatures" : "one creature") + " within 60 feet of you that is allied with you. That creature can make one melee or ranged weapon attack with its reaction, provided that it can see or hear you.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Bulwark", "You can extend the benefit of your Indomitable feature to an ally. When you decide to use Indomitable to reroll an Intelligence, a Wisdom, or a Charisma saving throw and you aren’t incapacitated, you can choose one ally within 60 feet of you that also failed its saving throw against the same effect. If that creature can see or hear you, it can reroll its saving throw and must use the new roll.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
