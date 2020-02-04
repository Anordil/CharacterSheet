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


public class Rogue_thief extends BaseArchetype {
    static final long serialVersionUID = 2079L;

    public Rogue_thief(){}

    @Override
    public String getName() {
        return App.getResString(R.string.rogue_thief);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Fast Hands");
            levelUp.add("Gained Second-Story Work");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Supreme Sneak");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Use Magic Device");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Thief’s Reflexes");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Fast Hands", "You can use the bonus action granted by your Cunning Action to make a Dexterity (Sleight of Hand) check, use your thieves’ tools to disarm a trap or open a lock, or take the Use an Object action.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Second-Story Work", "you gain the ability to climb faster than normal; climbing no longer costs you extra movement.\\n\" +\n" +
                    "                    \"\\n\" +\n" +
                    "                    \"In addition, when you make a running jump, the distance you cover increases by a number of feet equal to your Dexterity modifier.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Supreme Sneak", "You have advantage on a Dexterity (Stealth) check if you move no more than half your speed on the same turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 13) {
            powers.add(new Power("Use Magic Device", "You ignore all class, race, and level requirements on the use of magic items.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Thief’s Reflexes", "You can take two turns during the first round of any combat. You take your first turn at your normal initiative and your second turn at your initiative minus 10. You can’t use this feature when you are surprised.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
