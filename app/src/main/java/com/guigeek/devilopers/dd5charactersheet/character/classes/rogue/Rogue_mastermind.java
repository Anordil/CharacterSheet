package com.guigeek.devilopers.dd5charactersheet.character.classes.rogue;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Rogue_mastermind extends BaseArchetype {
    static final long serialVersionUID = 2074L;

    public Rogue_mastermind(){}

    @Override
    public String getName() {
        return App.getResString(R.string.rogue_mastermind);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Master of Intrigue");
            levelUp.add("Gained Master of Tactics");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Insightful Manipulator");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Misdirection");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Soul of Deceit");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Master of Intrigue", "You gain proficiency with the disguise kit, the forgery kit, and one gaming set of your choice. You also learn two languages of your choice.\n" +
                    "\n" +
                    "Additionally, you can unerringly mimic the speech patterns and accent of a creature that you hear speak for at least 1 minute, enabling you to pass yourself off as a native speaker of a particular land, provided that you know the language.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Master of Tactics", "You can use the Help action as a bonus action. Additionally, when you use the Help action to aid an ally in attacking a creature, the target of that attack can be within 30 feet of you, rather than within 5 feet of you, if the target can see or hear you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Insightful Manipulator", "If you spend at least 1 minute observing or interacting with another creature outside combat, you can learn certain information about its capabilities compared to your own. The DM tells you if the creature is your equal, superior, or inferior in regard to two of the following characteristics of your choice:\n" +
                    "\n" +
                    "Intelligence score\n" +
                    "Wisdom score\n" +
                    "Charisma score\n" +
                    "Class levels (if any)\n" +
                    "At the DM’s option, you might also realize you know a piece of the creature’s history or one of its personality traits, if it has any.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 13) {
            powers.add(new Power("Misdirection", "When you are targeted by an attack while a creature within 5 feet of you is granting you cover against that attack, you can use your reaction to have the attack target that creature instead of you.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Soul of Deceit", "Your thoughts can’t be read by telepathy or other means, unless you allow it. You can present false thoughts by succeeding on a Charisma (Deception) check contested by the mind reader’s Wisdom (Insight) check.\n" +
                    "\n" +
                    "Additionally, no matter what you say, magic that would determine if you are telling the truth indicates you are being truthful if you so choose, and you can’t be compelled to tell the truth by magic.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
