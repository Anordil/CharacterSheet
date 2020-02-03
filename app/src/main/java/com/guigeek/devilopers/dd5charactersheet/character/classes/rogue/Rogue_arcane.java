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


public class Rogue_arcane extends BaseArchetype {
    static final long serialVersionUID = 2071L;

    public Rogue_arcane(){}

    @Override
    public String getName() {
        return App.getResString(R.string.rogue_arcane);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("You know Mage Hand and two other Wizard cantrips.");
            levelUp.add("You know three 1st-level spells (2 of which must be from the enchantment and/orillusion list).");
            levelUp.add("Gained Mage Hand Legerdemain.");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Magical Ambush");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You know a 4th Wizard cantrip.");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Versatile Trickster");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Spell Thief");
        }


        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained a spell from any school.");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained a spell from any school.");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("Gained a spell from any school.");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Mage Hand Legerdemain", "When you cast mage hand, you can make the spectral hand invisible, and you can perform the following additional tasks with it:" +
                    "\n - You can stow one object the hand is holding in a container worn or carried by another creature" +
                    "\n - You can retrieve an object in a container worn or carried by another creature" +
                    "\n - You can use thieves’ tools to pick locks and disarm traps at range" +
                    "\n\nYou can perform one of these tasks without being noticed by a creature if you succeed on a Dexterity (Sleight of Hand) check contested by the creature’s Wisdom (Perception) check.\n" +
                    "\n" +
                    "In addition, you can use the bonus action granted by your Cunning Action to control the hand.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Magical Ambush", "If you are hidden from a creature when you cast a spell on it, the creature has disadvantage on any saving throw it makes against the spell this turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 13) {
            powers.add(new Power("Versatile Trickster", "You gain the ability to distract targets with your mage hand. As a bonus action on your turn, you can designate a creature within 5 feet of the spectral hand created by the spell. Doing so gives you advantage on attack rolls against that creature until the end of the turn.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Spell Thief", "You gain the ability to magically steal the knowledge of how to cast a spell from another spellcaster.\n" +
                    "\n" +
                    "Immediately after a creature casts a spell that targets you or includes you in its area of effect, you can use your reaction to force the creature to make a saving throw with its spellcasting ability modifier. The DC equals your spell save DC. On a failed save, you negate the spell’s effect against you, and you steal the knowledge of the spell if it is at least 1st level and of a level you can cast (it doesn’t need to be a wizard spell). For the next 8 hours, you know the spell and can cast it using your spell slots. The creature can’t cast that spell until the 8 hours have passed.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.REACTION));
        }

        return powers;
    }
}
