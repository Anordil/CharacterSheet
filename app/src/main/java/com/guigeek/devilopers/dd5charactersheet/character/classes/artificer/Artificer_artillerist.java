package com.guigeek.devilopers.dd5charactersheet.character.classes.artificer;

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

public class Artificer_artillerist extends BaseArchetype {
    static final long serialVersionUID = 2003L;

    @Override
    public String getName() {
        return App.getResString(R.string.artificer_artillerist);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained proficiency with woodcarver’s tools. If you already have this proficiency, you gain proficiency with one other type of artisan’s tools of your choice.");
            levelUp.add("Gained Eldritch Cannon");
        }
        if (iNewCharacterLevel == 5) {
            levelUp.add("Gained Arcane Firearm");
        }
        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Explosive Cannon");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Fortified Position");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();


        if (iLevel >= 3) {
            powers.add(new Power("Eldritch Cannon", "Using woodcarver’s tools or smith’s tools, you can take an action to magically create a Small or Tiny eldritch cannon in an unoccupied space on a horizontal surface within 5 feet of you. A Small eldritch cannon occupies its space, and a Tiny one can be held in one hand.\n" +
                    "\n" +
                    "Once you create a cannon, you can’t do so again until you finish a long rest or until you expend a spell slot of 1st level or higher. You can have only one cannon at a time and can’t create one while your cannon is present.\n" +
                    "\n" +
                    "The cannon is a magical object. Regardless of size, the cannon has an AC of 18 and a number of hit points equal to five times your artificer level. It is immune to poison damage, psychic damage, and all conditions. If it is forced to make an ability check or a saving throw, treat all its ability scores as 10 (+0). If the mending spell is cast on it, it regains 2d6 hit points. It disappears if it is reduced to 0 hit points or after 1 hour. You can dismiss it early as an action.\n" +
                    "\n" +
                    "When you create the cannon, you determine its appearance and whether it has legs. You also decide which type it is, choosing from the options on the Eldritch Cannons table. On each of your turns, you can take a bonus action to cause the cannon to activate if you are within 60 feet of it. As part of the same bonus action, you can direct the cannon to walk or climb up to 15 feet to an unoccupied space, provided it has legs." +
                    "\n\n[Flamethrower]\n" +
                    "The cannon exhales fire in an adjacent 15-foot cone that you designate. Each creature in that area must make a Dexterity saving throw against your spell save DC, taking 2d8 fire damage on a failed save or half as much damage on a successful one. The fire ignites any flammable objects in the area that aren’t being worn or carried.\n" +
                    "\n" +
                    "[Force Ballista]\n" +
                    "Make a ranged spell attack, originating from the cannon, at one creature or object within 120 feet of it. On a hit, the target takes 2d8 force damage, and if the target is a creature, it is pushed up to 5 feet away from the cannon.\n" +
                    "\n" +
                    "[Protector]\n" +
                    "The cannon emits a burst of positive energy that grants itself and each creature of your choice within 10 feet of it a number of temporary hit points equal to 1d8 + your Intelligence modifier (minimum of +1).", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 5) {
            powers.add(new Power("Arcane Firearm", "You know how to turn a wand, staff, or rod into an arcane firearm, a conduit for your destructive spells. When you finish a long rest, you can use woodcarver’s tools to carve special sigils into a wand, staff, or rod and thereby turn it into your arcane firearm. The sigils disappear from the object if you later carve them on a different item. The sigils otherwise last indefinitely.\n" +
                    "\n" +
                    "You can use your arcane firearm as a spellcasting focus for your artificer spells. When you cast an artificer spell through the firearm, roll a d8, and you gain a bonus to one of the spell’s damage rolls equal to the number rolled.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Explosive Cannon", "Every eldritch cannon you create is more destructive:\n" +
                    "\n" +
                    "The cannon’s damage rolls all increase by 1d8.\n" +
                    "As an action, you can command the cannon to detonate if you are within 60 feet of it. Doing so destroys the cannon and forces each creature within 20 feet of it to make a Dexterity saving throw against your spell save DC, taking 3d8 force damage on a failed save or half as much damage on a successful one.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Fortified Position", "You’re a master at forming well-defended emplacements using Eldritch Cannon:\n" +
                    "\n" +
                    "You and your allies have half cover while within 10 feet of a cannon you create with Eldritch Cannon, as a result of a shimmering field of magical protection that the cannon emits.\n" +
                    "You can now have two cannons at the same time. You can create two with the same action (but not the same spell slot), and you can activate both of them with the same bonus action. You determine whether the cannons are identical to each other or different. You can’t create a third cannon while you have two.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getDescription() {
        return "An Artillerist specializes in using magic to hurl energy, projectiles, and explosions on a battlefield.";
    }
}
