package com.guigeek.devilopers.dd5charactersheet.character.classes.cleric;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Cleric_tempest extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2410L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_tempest);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained proficiency with Martial weapons and heavy armor");
            levelUp.add("Gained Wrath of the Storm");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Thunderbolt Strike");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Divine Strike");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Stormborn");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Wrath of the Storm", "When a creature within 5 feet of you that you can see hits you with an attack, you can use your reaction to cause the creature to make a Dexterity saving throw. The creature takes 2d8 lightning or thunder damage (your choice) on a failed saving throw, and half as much damage on a successful one.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (a minimum of once). You regain all expended uses when you finish a long rest.", "5ft", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Thunderbolt Strike", "When you deal lightning damage to a Large or smaller creature, you can also push it up to 10 feet away from you.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Divine Strike", "Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra " + (iLevel >= 14 ? 2 : 1) + "d8 thunder damage to the target. ", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Stormborn", "You have a flying speed equal to your current walking speed whenever you are not underground or indoors.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        return iLevel >= 2 ? "[Destructive Wrath] " : "When you roll lightning or thunder damage, you can use your Channel Divinity to deal maximum damage, instead of rolling.";
    }
}
