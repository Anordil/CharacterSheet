package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.AttributeAlteration;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.Race;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Dragonborn extends BaseRace {
    public static final long serialVersionUID = 106L;


    @Override
    public LinkedList<Fettle> getFettles() {
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

    private String getBreathDescription() {
        return "Each creature in a " + getBreathForm() + " must make a " + getSaveType()
                + " saving throw. The DC for this saving throw equals 8 + your Constitution modifier + your proficiency bonus. A creature takes 2d6 "
                + Enumerations.DamageTypes.values()[getDamageType()].toString()
                + " damage on a failed save, and half as much damage on a successful one. The damage increases to 3d6 at 6th level, 4d6 at 11th level, and 5d6 at 16th level. After you use your breath weapon, you can’t use it again until you complete a short or long rest.";
    }

    @Override
    public String getName() {
        return App.getResString(R.string.race_dragonborn) + " (" + _subRace + ")";
    }

    public Dragonborn() {
        _subRace = "Black";
    }

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[2];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.STR);
        raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
        return raceBonuses;
    }

    @Override
    public LinkedList<Power> getRacialFeatures() {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power("Breath", getBreathDescription(), "", 1, -1, false, Enumerations.ActionType.ACTION));

        return racialTraits;
    }
}
