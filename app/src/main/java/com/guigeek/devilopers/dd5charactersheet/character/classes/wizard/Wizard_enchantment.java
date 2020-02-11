package com.guigeek.devilopers.dd5charactersheet.character.classes.wizard;

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

public class Wizard_enchantment extends BaseArchetype {
    static final long serialVersionUID = 2805L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_enchantment);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Hypnotic Gaze");
            levelUp.add("Gained School Savant");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Instinctive Charm");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Split Enchantment");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Alter Memories");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.INT);

        if (iLevel >= 2) {
            powers.add(new Power("School Savant", "The gold and time you spend to copy a spell into your spellbook is halved for spells of your chosen school.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Hypnotic Gaze", "As an action, choose one creature that you can see within 5 feet of you. If the target can see or hear you, it must succeed on a Wisdom saving throw against your wizard spell save DC or be charmed by you until the end of your next turn. The charmed creature’s speed drops to 0, and the creature is incapacitated and visibly dazed.\n" +
                    "\n" +
                    "On subsequent turns, you can use your action to maintain this effect, extending its duration until the end of your next turn. However, the effect ends if you move more than 5 feet away from the creature, if the creature can neither see nor hear you, or if the creature takes damage.\n" +
                    "\n" +
                    "Once the effect ends, or if the creature succeeds on its initial saving throw against this effect, you can’t use this feature on that creature again until you finish a long rest.", "5ft", -1, dc, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Instinctive Charm", "When a creature you can see within 30 feet of you makes an attack roll against you, you can use your reaction to divert the attack, provided that another creature is within the attack’s range. The attacker must make a Wisdom saving throw against your wizard spell save DC. On a failed save, the attacker must target the creature that is closest to it, not including you or itself. If multiple creatures are closest, the attacker chooses which one to target. On a successful save, you can’t use this feature on the attacker again until you finish a long rest.\n" +
                    "\n" +
                    "You must choose to use this feature before knowing whether the attack hits or misses. Creatures that can’t be charmed are immune to this effect.", "30ft", -1, dc, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Split Enchantment", "When you cast an enchantment spell of 1st level or higher that targets only one creature, you can have it target a second creature.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Alter Memories", "When you cast an enchantment spell to charm one or more creatures, you can alter one creature’s understanding so that it remains unaware of being charmed.\n" +
                    "\n" +
                    "Additionally, once before the spell expires, you can use your action to try to make the chosen creature forget some of the time it spent charmed. The creature must succeed on an Intelligence saving throw against your wizard spell save DC or lose " + Math.max(1, 1 + iCharac.getModifier(Enumerations.Attributes.CHA)) + " hours of its memories. You can make the creature forget less time, and the amount of time can’t exceed the duration of your enchantment spell.", "", -1, dc, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
