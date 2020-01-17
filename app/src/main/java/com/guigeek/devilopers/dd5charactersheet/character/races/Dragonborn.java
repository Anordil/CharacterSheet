package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.AttributeAlteration;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Dragonborn extends BaseRace {
    public static final long serialVersionUID = 106L;


    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, getDamageType()));
        return fettles;
    }

    private int getDamageType() {
        if (_subRace.equals("Black") || _subRace.equals("Copper")) {
            return Enumerations.DamageTypes.ACID.ordinal();
        }
        else if (_subRace.equals("Blue") || _subRace.equals("Bronze")) {
            return Enumerations.DamageTypes.LIGHTNING.ordinal();
        }
        else if (_subRace.equals("Brass") || _subRace.equals("Gold") || _subRace.equals("Red")) {
            return Enumerations.DamageTypes.FIRE.ordinal();
        }
        else if (_subRace.equals("Green")) {
            return Enumerations.DamageTypes.POISON.ordinal();
        }
        else {
            return Enumerations.DamageTypes.COLD.ordinal();
        }
    }

    private String getBreathForm() {
        if (_subRace.equals("Black") || _subRace.equals("Blue") || _subRace.equals("Brass") || _subRace.equals("Bronze") || _subRace.equals("Copper")) {
            return "5 by 30 ft. line";
        }
        else {
            return "15 ft. cone";
        }
    }

    private String getSaveType() {
        if (_subRace.equals("Green") || _subRace.equals("Silver") || _subRace.equals("White")) {
            return "CON";
        }
        else {
            return "DEX";
        }
    }

    private String getBreathDescription(Character iCharacter) {
        int lvl = iCharacter.getLevel();
        String damage = "2d6";
        if (lvl >= 16) damage = "5d6";
        else if (lvl >= 11) damage = "4d6";
        else if (lvl >= 6) damage = "3d6";

        return "Each creature in a " + getBreathForm() + " must make a " + getSaveType()
                + " saving throw. A creature takes "
                + damage + " "
                + Enumerations.DamageTypes.values()[getDamageType()].toString()
                + " damage on a failed save, and half as much damage on a successful one.";
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_dragonborn);
    }

    public Dragonborn() {
        _subRace = "Black";
    }

    @Override
    public int getSubraceArrayId() {
        return R.array.draconicAncestries;
    }

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[2];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.STR);
        raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
        return raceBonuses;
    }

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();

        int dd = 8 + iCharacter.getModifier(Enumerations.Attributes.CON) + iCharacter.getProficiencyBonus();
        racialTraits.add(new Power("Breath", getBreathDescription(iCharacter), "", 1, dd, false, Enumerations.ActionType.ACTION));

        return racialTraits;
    }
}
