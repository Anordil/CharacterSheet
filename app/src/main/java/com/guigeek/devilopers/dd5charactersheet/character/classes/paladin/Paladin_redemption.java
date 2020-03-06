package com.guigeek.devilopers.dd5charactersheet.character.classes.paladin;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin_redemption extends BaseArchetype {
    static final long serialVersionUID = 228L;

    public Paladin_redemption(){}

    @Override
    public String getName() {
        return App.getResString(R.string.paladin_redemption);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Channel Divinity.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Aura of the Guardian");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Protective Spirit");
        }
        else if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Emissary of Redemption");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "" +
                    "<b>Emissary of Peace</b> You can use your Channel Divinity to augment your presence with divine power. As a bonus action, you grant yourself a +5 bonus to Charisma (Persuasion) checks for the next 10 minutes.\n" +
                    "<b>Rebuke the Violent</b> You can use your Channel Divinity to rebuke those who use violence. Immediately after an attacker within 30 feet of you deals damage with an attack against a creature other than you, you can use your reaction to force the attacker to make a Wisdom saving throw. On a failed save, the attacker takes radiant damage equal to the damage it just dealt. On a successful save, it takes half as much damage.", "", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 7) {
            String range = iLevel >= 18 ? "30ft" : "10ft";
            powers.add(new Power("Aura of the Guardian", "When a creature within 10 feet of you takes damage, you can use your reaction to magically take that damage, instead of that creature taking it. This feature doesn’t transfer any other effects that might accompany the damage, and this damage can’t be reduced in any way.", range, -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Protective Spirit", "You regain hit points equal to 1d6 + half your paladin level if you end your turn in combat with fewer than half of your hit points remaining and you aren’t incapacitated.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Emissary of Redemption", "You become an avatar of peace, which gives you two benefits:\n" +
                    "\n" +
                    "You have resistance to all damage dealt by other creatures (their attacks, spells, and other effects).\n" +
                    "Whenever a creature hits you with an attack, it takes radiant damage equal to half the damage you take from the attack.\n" +
                    "If you attack a creature, cast a spell on it, or deal damage to it by any means but this feature, neither benefit works against that creature until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
