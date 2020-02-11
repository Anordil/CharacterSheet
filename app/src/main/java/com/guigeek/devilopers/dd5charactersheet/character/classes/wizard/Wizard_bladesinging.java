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

public class Wizard_bladesinging extends BaseArchetype {
    static final long serialVersionUID = 2801L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_bladesinging);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained proficiency with light armor, one type of one-handed melee weapon of your choice, and Performance.");
            levelUp.add("Gained Bladesong");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Extra Attack");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Song of Defense");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Song of Victory");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Bladesong", "you can invoke a secret elven magic called the Bladesong, provided that you aren’t wearing medium or heavy armor or using a shield. It graces you with supernatural speed, agility, and focus.\n" +
                    "\n" +
                    "You can use a bonus action to start the Bladesong, which lasts for 1 minute. It ends early if you are incapacitated, if you don medium or heavy armor or a shield, or if you use two hands to make an attack with a weapon. You can also dismiss the Bladesong at any time you choose (no action required).\n" +
                    "\n" +
                    "While your Bladesong is active, you gain the following benefits:\n" +
                    "\n" +
                    "You gain a bonus to your AC equal to your Intelligence modifier (minimum of +1).\n" +
                    "Your walking speed increases by 10 feet.\n" +
                    "You have advantage on Dexterity (Acrobatics) checks.\n" +
                    "You gain a bonus to any Constitution saving throw you make to maintain your concentration on a spell. The bonus equals your Intelligence modifier (minimum of +1).\n" +
                    "You can use this feature twice. You regain all expended uses of it when you finish a short or long rest.", "", 2, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }

        if (iLevel >= 10) {
            powers.add(new Power("Song of Defense", "When you take damage and your Bladesong is active, you can use your reaction to expend one spell slot and reduce that damage to you by an amount equal to five times the spell slot’s level.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Song of Victory", "You add your Intelligence modifier (minimum of +1) to the damage of your melee weapon attacks while your Bladesong is active.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
