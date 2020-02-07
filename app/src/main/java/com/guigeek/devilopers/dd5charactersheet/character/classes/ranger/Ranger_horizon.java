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

public class Ranger_horizon extends BaseArchetype {
    static final long serialVersionUID = 2803L;

    @Override
    public String getName() {
        return App.getResString(R.string.ranger_horizon);
    }



    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Detect Portal\n");
            levelUp.add("Gained Planar Warrior");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Ethereal Step");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Gained Distant Strike");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Spectral Defense");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Detect Portal", "As an action, you detect the distance and direction to the closest planar portal within 1 mile of you.", "", 1, -1, false, Enumerations.ActionType.ACTION));
            int dice = iLevel >= 11 ? 2 : 1;
            powers.add(new Power("Planar Warrior", "As a bonus action, choose one creature you can see within 30 feet of you. The next time you hit that creature on this turn with a weapon attack, all damage dealt by the attack becomes force damage, and the creature takes an extra " + dice + "d8 force damage from the attack.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Ethereal Step", "As a bonus action, you can cast the etherealness spell with this feature, without expending a spell slot, but the spell ends at the end of the current turn.", "", 1, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Distant Strike", "When you take the Attack action, you can teleport up to 10 feet before each attack to an unoccupied space you can see.\n" +
                    "\n" +
                    "If you attack at least two different creatures with the action, you can make one additional attack with it against a third creature.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Spectral Defense", "When you take damage from an attack, you can use your reaction to give yourself resistance to all of that attack’s damage on this turn.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
