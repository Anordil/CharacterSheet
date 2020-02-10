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
            levelUp.add("You gain proficiency in one of the following skills of your choice: History, Insight, Performance, or Persuasion. Alternatively, you learn one language of your choice.");
            levelUp.add("Gained Fighting Spirit");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Elegant Courtier");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Tireless Spirit");
            levelUp.add("Gained more HP with Fighting Spirit");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Rapid Strike");
            levelUp.add("Gained more HP with Fighting Spirit");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Strength before Death");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            int hp = iLevel >= 15 ? 15 : iLevel >= 10 ? 10 : 5;
            powers.add(new Power("Fighting Spirit", "As a bonus action on your turn, you can give yourself advantage on weapon attack rolls until the end of the current turn. When you do so, you also gain " + hp + " temporary hit points.", "", 3, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Elegant Courtier", "Whenever you make a Charisma (Persuasion) check, you gain a bonus to the check equal to your Wisdom modifier.\n" +
                    "\n" +
                    "Your self-control also causes you to gain proficiency in Wisdom saving throws. If you already have this proficiency, you instead gain proficiency in Intelligence or Charisma saving throws (your choice).", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Tireless Spirit", "When you roll initiative and have no uses of Fighting Spirit remaining, you regain one use.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Rapid Strike", "If you take the Attack action on your turn and have advantage on an attack roll against one of the targets, you can forgo the advantage for that roll to make an additional weapon attack against that target, as part of the same action. You can do so no more than once per turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Strength before Death", "If you take damage that reduces you to 0 hit points and doesn’t kill you outright, you can use your reaction to delay falling unconscious, and you can immediately take an extra turn, interrupting the current turn. While you have 0 hit points during that extra turn, taking damage causes death saving throw failures as normal, and three death saving throw failures can still kill you. When the extra turn ends, you fall unconscious if you still have 0 hit points.", "", 1, -1, true, Enumerations.ActionType.REACTION));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
