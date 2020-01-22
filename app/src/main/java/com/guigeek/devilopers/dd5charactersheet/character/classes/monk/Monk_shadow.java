package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Monk_shadow extends BaseArchetype {
    static final long serialVersionUID = 2708L;
    public Monk_shadow(){}

    @Override
    public String getName() {
        return App.getResString(R.string.monk_shadow);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Shadow Arts");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Shadow Step");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Cloak of Shadows");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You gained Opportunist");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            powers.add(new Power("Shadow Arts", "As an action, you can spend 2 ki points to cast darkness, darkvision, pass without trace, or silence, without providing material components. Additionally, you gain the minor illusion cantrip if you don’t already know it.", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Shadow Step", "When you are in dim light or darkness, as a bonus action you can teleport up to 60 feet to an unoccupied space you can see that is also in dim light or darkness. You then have advantage on the first melee attack you make before the end of the turn.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Cloak of Shadows", "When you are in an area of dim light or darkness, you can use your action to become invisible. You remain invisible until you make an attack, cast a spell, or are in an area of bright light.", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Opportunist", "Whenever a creature within 5 feet of you is hit by an attack made by a creature other than you, you can use your reaction to make a melee attack against that creature.", "5 ft", -1, -1, true, Enumerations.ActionType.REACTION));
        }

        return powers;
    }
}
