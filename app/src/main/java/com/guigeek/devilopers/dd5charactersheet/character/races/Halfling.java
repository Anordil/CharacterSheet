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
public class Halfling extends BaseRace {
    public static final long serialVersionUID = 108L;


    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.FEAR.ordinal()));

        if (_subRace.equals("Stout")) {
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.POISON.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.POISON.ordinal()));
        }
        return fettles;
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_halfling);
    }

    public Halfling(){}

    @Override
    public int getSpeedInFeet() {
        return 25;
    }

    @Override
    public int getSubraceArrayId() {
        return R.array.halflingSubRaces;
    }

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[2];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.DEX);

        if (_subRace.equals("Lightfoot")) {
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
        } else { // Stout
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CON);
        }
        return raceBonuses;
    }

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power("Lucky", "When you roll a 1 on the d20 for an attack roll, ability check, or saving throw, you can reroll the die and must use the new roll.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Brave", "Advantage on saving throws against being frightened.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Halfling Nimbleness", "You can move through the space of any creature that is of a size larger than yours.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));

        if (_subRace.equals("Lightfoot")) {
            racialTraits.add(new Power("Stealthy", "You can attempt to hide even when you are obscured only by a creature that is at least one size larger than you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        } else { // Stout
            racialTraits.add(new Power("Stout Resilience", "Advantage on saving throws against poison and resistance againast poison damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return racialTraits;
    }
}
