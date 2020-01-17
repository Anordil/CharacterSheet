package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.character.AttributeAlteration;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;

/**
 * Created by totou on 14/03/2016.
 */
public class HalfOrc extends BaseRace {

    public static final long serialVersionUID = 103L;

    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        return new LinkedList<Fettle>();
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_half_orc);
    }

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[2];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.STR);
        raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CON);
        return raceBonuses;
    }

    public HalfOrc(){}

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power("Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can't discern color in darkness, only shades of gray.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Menacing", "You gain proficiency in the Intimidation skill", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Relentless Endurance", "When you are reduced to O hit points but not killed outright. You can drop to 1 hit point instead. You can't use this feature again unti! you finish a long rest", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Savage Attacks", "When you score a critical hit with a melee weapon attack, you can roll one of the weapon's damage dice one additional time and add it to the extra damage of the criticaI hit", "", -1, -1, true, Enumerations.ActionType.PASSIVE));

        return racialTraits;
    }
}
