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


public class Rogue_soulknife extends BaseArchetype {
    static final long serialVersionUID = 2077L;

    public Rogue_soulknife(){}

    @Override
    public String getName() {
        return App.getResString(R.string.rogue_soul_knife);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Psychic Blade");
            levelUp.add("Gained Psionic Enhancement");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Terrifying Blade");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Psychic Veil");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Rend Mind");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Psychic Blade", "As a bonus action, you can create a magical blade of shimmering psychic power from one or both of your hands. While one of your hands is manifesting a blade, you can’t hold anything in that hand. You can dismiss one or both blades at any time (no action required), and they disappear if you’re incapacitated.\n" +
                    "\n" +
                    "The blade is a simple melee weapon with the finesse, light, and thrown properties. It has a normal range of 30 feet and a long range of 60 feet, and it deals 1d6 psychic damage on a hit. If you throw the blade as part of an attack, it vanishes immediately after it hits or misses its target. The blade otherwise disappears the instant it leaves your hand.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
            powers.add(new Power("Psionic Enhancement", "You can focus your psionic power to give yourself an extraordinary ability. When you finish a long rest, you gain one of the following benefits of your choice, which lasts until you finish a long rest:\\n\" +\n" +
                    "                    \"\\n\" +\n" +
                    "                    \"[Telepathy] \" +\n" +
                    "                    \"You can communicate telepathically with any creature you can see within 30 feet of you. If a creature can speak at least one language, it can respond to you telepathically.\\n\" +\n" +
                    "                    \"\\n\" +\n" +
                    "                    \"[Toughness] \" +\n" +
                    "                    \"Your hit point maximum and your current hit points increase by an amount equal to your Intelligence modifier plus your rogue level.\\n\" +\n" +
                    "                    \"\\n\" +\n" +
                    "                    \"[Walking Speed] \" +\n" +
                    "                    \"Increase your walking speed by 5 feet.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            int dd = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.INT);
            powers.add(new Power("", "Your psychic blades can now stoke terror within a target: When you damage a creature with your Psychic Blade, you can force the target to make a Wisdom saving throw (DC equal to " + dd + "). On a failed save, the creature is frightened of you until the start of your next turn. On a successful save, the creature isn’t frightened and is immune to your Terrifying Blade for 24 hours.", "", -1, dd, true, Enumerations.ActionType.PASSIVE));
        }
        int uses = Math.max(1, iCharac.getModifier(Enumerations.Attributes.INT));
        if (iLevel >= 13) {
            powers.add(new Power("Psychic Veil", "As an action, you can magically become invisible, along with anything you are wearing or carrying, for 10 minutes. This invisibility ends if you make an attack or if you force a creature to make a saving throw.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Intelligence modifier (minimum of once), and you regain all expended uses when you finish a long rest.", "", uses, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 17) {
            int dd = 10 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.INT);
            powers.add(new Power("Rend Mind", "As an action while you have at least one Psychic Blade manifested, you can force a creature you can see within 30 feet of you to make an Intelligence saving throw (DC equal to 10 + your proficiency bonus + your Intelligence modifier). If you are hidden from the target, it has disadvantage on the save. On a failed save, the target takes 12d6 psychic damage, and it is stunned until the start of your next turn. On a successful save, the target takes half as much damage and isn’t stunned. One of your Psychic Blades vanishes after using this feature.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Intelligence modifier (minimum of once), and you regain all expended uses when you finish a long rest.", "30ft", uses, dd, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
