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

public class Fighter_cavalier extends BaseArchetype {
    static final long serialVersionUID = 2603L;

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_cavalier);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Born to the Saddle");
            levelUp.add("Gained Unwavering Mark");
            levelUp.add("You gain proficiency in one of the following skills of your choice: Animal Handling, History, Insight, Performance, or Persuasion. Alternatively, you learn one language of your choice.");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Warding Maneuver");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Hold the Line");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Ferocious Charger");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Vigilant Defender");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Born to the Saddle", "You have advantage on saving throws made to avoid falling off your mount. If you fall off your mount and descend no more than 10 feet, you can land on your feet if you’re not incapacitated.\n" +
                    "\n" +
                    "Finally, mounting or dismounting a creature costs you only 5 feet of movement, rather than half your speed.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Unwavering Mark", "When you hit a creature with a melee weapon attack, you can mark the creature until the end of your next turn. This effect ends early if you are incapacitated or you die, or if someone else marks the creature.\n" +
                    "\n" +
                    "While it is within 5 feet of you, a creature marked by you has disadvantage on any attack roll that doesn’t target you.\n" +
                    "\n" +
                    "In addition, if a creature marked by you deals damage to anyone other than you, you can make a special melee weapon attack against the marked creature as a bonus action on your next turn. You have advantage on the attack roll, and if it hits, the attack’s weapon deals extra damage to the target equal to half your fighter level.\n" +
                    "\n" +
                    "Regardless of the number of creatures you mark, you can make this special attack a number of times equal to your Strength modifier (minimum of once), and you regain all expended uses of it when you finish a long rest.", "", iCharac.getModifier(Enumerations.Attributes.STR), -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Warding Maneuver", "If you or a creature you can see within 5 feet of you is hit by an attack, you can roll 1d8 as a reaction if you’re wielding a melee weapon or a shield. Roll the die, and add the number rolled to the target’s AC against that attack. If the attack still hits, the target has resistance against the attack’s damage.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Constitution modifier (minimum of once), and you regain all expended uses of it when you finish a long rest.", "", iCharac.getModifier(Enumerations.Attributes.CON), -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Hold the Line", "Creatures provoke an opportunity attack from you when they move 5 feet or more while within your reach, and if you hit a creature with an opportunity attack, the target’s speed is reduced to 0 until the end of the current turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Ferocious Charger", "f you move at least 10 feet in a straight line right before attacking a creature and you hit it with the attack, that target must succeed on a Strength saving throw or be knocked prone. You can use this feature only once on each of your turns.", "", -1, 8+iCharac.getProficiencyBonus()+iCharac.getModifier(Enumerations.Attributes.STR), true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Vigilant Defender", "You get a special reaction that you can take once on every creature’s turn, except your turn. You can use this special reaction only to make an opportunity attack, and you can’t use it on the same turn that you take your normal reaction.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }


        return powers;
    }
}
