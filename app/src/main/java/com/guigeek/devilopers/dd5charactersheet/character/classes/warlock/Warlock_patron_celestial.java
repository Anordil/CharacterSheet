package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

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


public class Warlock_patron_celestial extends BaseArchetype {
    static final long serialVersionUID = 221L;

    public Warlock_patron_celestial(){}


    @Override
    public String getName() {
        return App.getResString(R.string.warlock_patron_celestial);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You know the Light and Sacred Flame cantrips.");
            levelUp.add("Gained Healing Light");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Radiant Soul");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Celestial Resistance");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Searing Vengeance");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Healing Light", "You have a pool of d6s that you spend to fuel this healing. The number of dice in the pool equals 1 + your warlock level.\n" +
                    "\n" +
                    "As a bonus action, you can heal one creature you can see within 60 feet of you, spending dice from the pool. The maximum number of dice you can spend at once equals your Charisma modifier (minimum of one die). Roll the dice you spend, add them together, and restore a number of hit points equal to the total.", "", 1+iLevel, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Radiant Soul", "You have resistance to radiant damage, and when you cast a spell that deals radiant or fire damage, you can add your Charisma modifier to one radiant or fire damage roll of that spell against one of its targets.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 10) {
            powers.add(new Power("Celestial Resistance", "You gain " + (iLevel + iCharac.getModifier(Enumerations.Attributes.CHA)) + " temporary hit points whenever you finish a short or long rest. Additionally, choose up to five creatures you can see at the end of the rest. Those creatures each gain " + ((int)Math.floor(iLevel/2) + iCharac.getModifier(Enumerations.Attributes.CHA)) + " temporary hit points", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Searing Vengeance", "When you have to make a death saving throw at the start of your turn, you can instead spring back to your feet with a burst of radiant energy. You regain hit points equal to half your hit point maximum, and then you stand up if you so choose. Each creature of your choice that is within 30 feet of you takes radiant damage equal to 2d8 + your Charisma modifier, and it is blinded until the end of the current turn.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> f = new LinkedList<>();

        if (character._level >= 6) {
            f.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.RADIANT.ordinal()));
        }

        return f;
    }
}
