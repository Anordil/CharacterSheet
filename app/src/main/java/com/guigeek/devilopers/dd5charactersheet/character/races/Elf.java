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
public class Elf extends BaseRace {
    public static final long serialVersionUID = 105L;


    @Override
    public LinkedList<Fettle> getFettles() {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.CHARM_MAGIC.ordinal()));
        fettles.add(new Fettle(Enumerations.FettleType.ABILITY_PROFICIENCY, 0, Enumerations.Skills.PERCEPTION.ordinal()));
        return fettles;
    }

    @Override
    public String getName() {
        return App.getResString(R.string.race_elf);
    }

    public Elf(){}

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[1];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.DEX);
        return raceBonuses;
    }

    @Override
    public LinkedList<Power> getRacialFeatures() {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power("Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can't discern color in darkness, only shades of gray.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Fey Ancestry", "You have advantage on saving throws against being charmed, and magic can't put you to sleep.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Keen Senses", "You have proficiency in the Perception skill.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Trance", "Elves don’t need to sleep. Instead, they meditate deeply, remaining semiconscious, for 4 hours a day. (The Common word for such meditation is “trance.”) While meditating, you can dream after a fashion; such dreams are actually mental exercises that have become reflexive through years of practice. After resting in this way, you gain the same benefit that a human does from 8 hours of sleep.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));

        return racialTraits;
    }
}
