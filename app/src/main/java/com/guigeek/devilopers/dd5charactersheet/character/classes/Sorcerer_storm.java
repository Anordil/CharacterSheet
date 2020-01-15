package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;


public class Sorcerer_storm extends Sorcerer_base {
    static final long serialVersionUID = 206L;

    public Sorcerer_storm(){}

    @Override
    public String getName() {
        return App.getResString(R.string.class_sorcerer_storm);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel);

        if (iNewCharacterLevel == 1) {
            levelUp.add("You now speak, read and write Primordial.");
            levelUp.add("You gained Tempestuous Magic!");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Heart of the Storm!");
            levelUp.add("You gained Storm Guide!");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Storm's Fury!");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("You gained Wind Soul!");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);


        if (iLevel >= 1) {
            powers.add(new Power("Tempestuous Magic", "Immediately after or before casting a spell of 1st level or higher, you may use your bonus action to move up to 10ft without provoking opportunity attacks.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }

        if (iLevel >= 6) {
        	powers.add(new Power("Heart of the Storm", "Resistant to lightning and thunder. When casting a lightning or thunder spell, all chosen creatures within 10ft take " + iLevel/2 + " lightning or thunder damage (same as spell).", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        	powers.add(new Power("Storm Guide", "You can use your action to stop the rain in a 20ft radius sphere around you (until you stop it with a bonus action), or change the wind direction in a 100ft sphere around you (until the end of your next turn)", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }

        if (iLevel >= 14) {
    		powers.add(new Power("Storm's Fury", "When hit by a melee attack, use your reaction to deal " + iLevel + " lightning damage to the attacker. On a failed STR check (DD" + (8+iCharac.getProficiencyBonus()+iCharac.getModifier(Enumerations.Attributes.CHA)) + "), the attacker is pushed 20ft away from you.", "", -1, -1, true, Enumerations.ActionType.REACTION));
    	}

        if (iLevel >= 18) {
            powers.add(new Power("Wind Soul", "Immune to lightning and thunder damage. You gain a flying speed of 60ft. As an action (once per short/long rest), you can reduce it to 30ft to grant a 30ft flying speed to " + (3+iCharac.getModifier(Enumerations.Attributes.CHA)) + " creatures within 30ft of you for 1 hour.", "Melee", 1, -1, false, Enumerations.ActionType.ACTION));
        }

        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        int level = character._class instanceof Sorcerer_storm ? character._level : character._levelSecondaryClass;
        if (level >= 18) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.DamageTypes.LIGHTNING.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.DamageTypes.THUNDER.ordinal()));
        }
        else if (level >= 6) {
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.LIGHTNING.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.THUNDER.ordinal()));
        }

        return fettles;
    }
}
