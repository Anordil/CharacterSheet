package com.guigeek.devilopers.dd5charactersheet.character.classes.paladin;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin_devotion extends BaseArchetype {
    static final long serialVersionUID = 225L;

    public Paladin_devotion(){}

    @Override
    public String getName() {
        return App.getResString(R.string.paladin_devotion);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Channel Divinity.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Aura of Devotion");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Purity of Spirit");
        }
        else if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Holy Nimbus");
        }

        return levelUp;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> perks = new LinkedList<>();

        if (classLevel >= 7) {
            perks.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.CHARM_MAGIC.ordinal()));
        }

        return perks;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "Sacred Weapon. As an action, you can imbue one weapon that you are holding with positive energy, using your Channel Divinity. For 1 minute, you add your Charisma modifier to attack rolls made with that weapon (with a minimum bonus of +1). The weapon also emits bright light in a 20-foot radius and dim light 20 feet beyond that. If the weapon is not already magical, it becomes magical for the duration.\n" +
                    "\n" +
                    "You can end this effect on your turn as part of any other action. If you are no longer holding or carrying this weapon, or if you fall unconscious, this effect ends.\n" +
                    "\n" +
                    "Turn the Unholy. As an action, you present your holy symbol and speak a prayer censuring fiends and undead, using your Channel Divinity. Each fiend or undead that can see or hear you within 30 feet of you must make a Wisdom saving throw. If the creature fails its saving throw, it is turned for 1 minute or until it takes damage.\n" +
                    "\n" +
                    "A turned creature must spend its turns trying to move as far away from you as it can, and it can’t willingly move to a space within 30 feet of you. It also can’t take reactions. For its action, it can use only the Dash action or try to escape from an effect that prevents it from moving. If there’s nowhere to move, the creature can use the Dodge action.", "", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 7) {
            String range = iLevel >= 18 ? "30ft" : "10ft";
            powers.add(new Power("Aura of Devotion", "You and friendly creatures within " + range + " of you can’t be charmed while you are conscious.", range, -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Purity of Spirit", "You are always under the effects of a protection from evil and good spell.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Holy Nimbus", "For 1 minute, bright light shines from you in a 30-foot radius, and dim light shines 30 feet beyond that.\n" +
                    "\n" +
                    "Whenever an enemy creature starts its turn in the bright light, the creature takes 10 radiant damage.\n" +
                    "\n" +
                    "In addition, for the duration, you have advantage on saving throws against spells cast by fiends or undead.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
