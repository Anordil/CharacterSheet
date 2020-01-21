package com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;


public class Barbarian_berserker extends BaseArchetype {
    static final long serialVersionUID = 2102L;
    public Barbarian_berserker(){}

    @Override
    public String getName() {
        return App.getResString(R.string.barbarian_berserker);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Frenzy");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Mindless Rage");
        }
        else if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Intimidating Presence");
        }
        else if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Retaliation");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            powers.add(new Power("Frenzy", "You can go into a Frenzy when you rage. If you do so, you can make a single melee weapon attack as a bonus action on each of your turns after this one. When your rage ends, you suffer one level of exhaustion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Mindless Rage", "you can’t be charmed or frightened while raging. If you are charmed or frightened when you enter your rage, the effect is suspended for the duration of the rage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Intimidating Presence", "You can use your action to frighten someone with your menacing presence. When you do so, choose one creature that you can see within 30 feet of you. If the creature can see or hear you, it must succeed on a Wisdom saving throw (DC equal to 8 + your proficiency bonus + your Charisma modifier) or be frightened of you until the end of your next turn. On subsequent turns, you can use your action to extend the duration of this effect on the frightened creature until the end of your next turn. This effect ends if the creature ends its turn out of line of sight or more than 60 feet away from you.\n" +
                    "\n" +
                    "If the creature succeeds on its saving throw, you can’t use this feature on that creature again for 24 hours.", "30 ft", -1, 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.CHA), false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Retaliation", "When you take damage from a creature that is within 5 feet of you, you can use your reaction to make a melee weapon attack against that creature.", "5 ft", -1, -1, true, Enumerations.ActionType.REACTION));
        }

        return powers;
    }
}
