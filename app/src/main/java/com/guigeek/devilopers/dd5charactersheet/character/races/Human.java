package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.AttributeAlteration;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;

public class Human extends BaseRace {

    public static final long serialVersionUID = 104L;

    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return fettles;
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_human);
    }

    @Override
    public int getSubraceArrayId() {
        return R.array.humanSubRaces;
    }

    @Override
    public String getAttributeBoostDescription() {
        if (_subRace.equals("Variant")) {
            return "Add +1 to two ability scores, gain a feat & an extra skill proficiency";
        }
        return super.getAttributeBoostDescription();
    }

    @Override
    public Fettle[] getAttributeBoost() {
        if (_subRace.equals("Standard")) {
            Fettle[] raceBonuses = new Fettle[6];
            raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.STR);
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.CON);
            raceBonuses[3] = new AttributeAlteration(1, Enumerations.Attributes.INT);
            raceBonuses[4] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
            raceBonuses[5] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
            return raceBonuses;
        }
        else {
            return new Fettle[0];
        }
    }

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();
        return racialTraits;
    }

    public Human(){}
}
