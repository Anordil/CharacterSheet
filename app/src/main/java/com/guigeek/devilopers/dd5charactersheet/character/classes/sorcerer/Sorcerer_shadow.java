package com.guigeek.devilopers.dd5charactersheet.character.classes.sorcerer;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Sorcerer_shadow extends BaseArchetype {
    static final long serialVersionUID = 215L;

    public Sorcerer_shadow(){}

    @Override
    public String getName() {
        return App.getResString(R.string.sorcerer_shadow);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained Eyes of the Dark");
            levelUp.add("Gained Strength of the Grave");
        }
        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Darkness");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Hound of Ill Omen");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Shadow Walk");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Umbral Form");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();


        if (iLevel >= 1) {
            powers.add(new Power("Eyes of the Dark", "You have darkvision with a range of 120 feet.", "120ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Strength of the Grave", "When damage reduces you to 0 hit points, you can make a Charisma saving throw (DC 5 + the damage taken). On a success, you instead drop to 1 hit point. You can’t use this feature if you are reduced to 0 hit points by radiant damage or by a critical hit.\n" +
                    "\n" +
                    "After the saving throw succeeds, you can’t use this feature again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 3) {
            powers.add(new Power("Darkness", "You learn the darkness spell, which doesn’t count against your number of sorcerer spells known. In addition, you can cast it by spending 2 sorcery points or by expending a spell slot. If you cast it with sorcery points, you can see through the darkness created by the spell.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Hound of Ill Omen", "As a bonus action, you can spend 3 sorcery points to magically summon a hound of ill omen to target one creature you can see within 120 feet of you. The hound uses the dire wolf statistics (see the Monster Manual or appendix C in the Player’s Handbook), with the following changes:\n" +
                    "\n" +
                    "The hound is size Medium, not Large, and it counts as a monstrosity, not a beast.\n" +
                    "It appears with a number of temporary hit points equal to half your sorcerer level.\n" +
                    "It can move through other creatures and objects as if they were difficult terrain. The hound takes 5 force damage if it ends its turn inside an object.\n" +
                    "At the start of its turn, the hound automatically knows its target’s location. If the target was hidden, it is no longer hidden from the hound.\n" +
                    "The hound appears in an unoccupied space of your choice within 30 feet of the target. Roll initiative for the hound. On its turn, it can move only toward its target by the most direct route, and it can use its action only to attack its target. The hound can make opportunity attacks, but only against its target. Additionally, while the hound is within 5 feet of the target, the target has disadvantage on saving throws against any spell you cast. The hound disappears if it is reduced to 0 hit points, if its target is reduced to 0 hit points, or after 5 minutes.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }

        if (iLevel >= 14) {
            powers.add(new Power("Shadow Walk", "When you are in dim light or darkness, as a bonus action, you can magically teleport up to 120 feet to an unoccupied space you can see that is also in dim light or darkness.", "120ft", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
    	}

        if (iLevel >= 18) {
            powers.add(new Power("Umbral Form", "You can spend 6 sorcery points as a bonus action to magically transform yourself into a shadowy form. In this form, you have resistance to all damage except force and radiant damage, and you can move through other creatures and objects as if they were difficult terrain. You take 5 force damage if you end your turn inside an object.\n" +
                    "\n" +
                    "You remain in this form for 1 minute. It ends early if you are incapacitated, if you die, or if you dismiss it as a bonus action.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }

        return powers;
    }
}
