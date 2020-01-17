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
public class Tiefling extends BaseRace {
    public static final long serialVersionUID = 109L;


    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.FIRE.ordinal()));
        return fettles;
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_tiefling);
    }

    public Tiefling(){}


    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[2];
        raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.INT);
        raceBonuses[1] = new AttributeAlteration(2, Enumerations.Attributes.CHA);
        return raceBonuses;
    }

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power("Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can't discern color in darkness, only shades of gray.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Hellish Resistance", "Resistance to fire damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));

        racialTraits.add(new Power("Infernal Legacy", "You know the Thaumaturgy cantrip.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        if (iCharacter.getLevel() >= 3) {
            racialTraits.add(new Power("Infernal Legacy II", "Cast Hellish Rebuke as a level 2 spell.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iCharacter.getLevel() >= 5) {
            racialTraits.add(new Power("Infernal Legacy III", "Cast Darkness.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return racialTraits;
    }
}
