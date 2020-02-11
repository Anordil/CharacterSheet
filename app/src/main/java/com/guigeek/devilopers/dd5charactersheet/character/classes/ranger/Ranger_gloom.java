package com.guigeek.devilopers.dd5charactersheet.character.classes.ranger;

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

public class Ranger_gloom extends BaseArchetype {
    static final long serialVersionUID = 2802L;

    @Override
    public String getName() {
        return App.getResString(R.string.ranger_gloom);
    }



    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Dread Ambusher");
            levelUp.add("Gained Umbral Sight");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Iron Mind");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Gained Stalker’s Flurry");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Shadowy Dodge");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Dread Ambusher", "You can give yourself a bonus to your initiative rolls equal to your Wisdom modifier.\n" +
                    "\n" +
                    "At the start of your first turn of each combat, your walking speed increases by 10 feet, which lasts until the end of that turn. If you take the Attack action on that turn, you can make one additional weapon attack as part of that action. If that attack hits, the target takes an extra 1d8 damage of the weapon’s damage type.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Umbral Sight", "You gain darkvision out to a range of 60 feet. If you already have darkvision from your race, its range increases by 30 feet.\n" +
                    "\n" +
                    "You are also adept at evading creatures that rely on darkvision. While in darkness, you are invisible to any creature that relies on darkvision to see you in that darkness.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Iron Mind", "You gain proficiency in Wisdom saving throws. If you already have this proficiency, you instead gain proficiency in Intelligence or Charisma saving throws (your choice).", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Stalker’s Flurry", "Once on each of your turns when you miss with a weapon attack, you can make another weapon attack as part of the same action.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Shadowy Dodge", "Whenever a creature makes an attack roll against you and doesn’t have advantage on the roll, you can use your reaction to impose disadvantage on it. You must use this feature before you know the outcome of the attack roll.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }


        return powers;
    }
}
