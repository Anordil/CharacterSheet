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

public class Cleric_trickery extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2411L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_trickery);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained Blessing of the Trickster");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Divine Strike");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Improved Duplicity");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Blessing of the Trickster", "You can use your action to touch a willing creature other than yourself to give it advantage on Dexterity (Stealth) checks. This blessing lasts for 1 hour or until you use this feature again.", "", -1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Divine Strike", "Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra " + (iLevel >= 14 ? 2 : 1) + "d8 poison damage to the target. ", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Improved Duplicity", "You can create up to four duplicates of yourself, instead of one, when you use Invoke Duplicity. As a bonus action on your turn, you can move any number of them up to 30 feet, to a maximum range of 120 feet.", "", -1, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        String des = "";

        if (iLevel >= 2) des += "[Invoke Duplicity] As an action, you create " + (iLevel >= 17 ? "up to 4" : "a") + " perfect illusion of yourself that lasts for 1 minute, or until you lose your concentration (as if you were concentrating on a spell). The illusion appears in an unoccupied space that you can see within 30 feet of you. As a bonus action on your turn, you can move the illusion up to 30 feet to a space you can see, but it must remain within 120 feet of you.\n" +
                "\n" +
                "For the duration, you can cast spells as though you were in the illusion’s space, but you must use your own senses. Additionally, when both you and your illusion are within 5 feet of a creature that can see the illusion, you have advantage on attack rolls against that creature, given how distracting the illusion is to the target.";
        if (iLevel >= 6) des += "\n\n[Cloak of Shadows] As an action, you become invisible until the end of your next turn. You become visible if you attack or cast a spell.";
        return des;
    }
}
