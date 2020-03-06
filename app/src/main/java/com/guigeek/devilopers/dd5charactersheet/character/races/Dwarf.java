package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.character.*;
import com.guigeek.devilopers.dd5charactersheet.character.Character;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Dwarf extends BaseRace {
    public static final long serialVersionUID = 102L;


    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.POISON.ordinal()));
        fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.POISON.ordinal()));
        return fettles;
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_dwarf);
    }

    @Override
    public int getSubraceArrayId() {
        return R.array.dwarfSubRaces;
    }

    @Override
    public int getSpeedInFeet() {
        return 25;
    }

    public Dwarf(){}

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[2];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.CON);

        if (_subRace.equals("Hill")) {
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
        }
        else { // Mountain
            raceBonuses[1] = new AttributeAlteration(2, Enumerations.Attributes.STR);
        }
        return raceBonuses;
    }

    @Override
    public String getAttributeBoostDescription() {
        return "+2 CON, +1 " + (_subRace.equals("Hill") ? "WIS" : "STR") + ", Darkvision, Dwarven resilience" + (_subRace.equals("Hill") ? ", +1 HP/level" : "");
    }

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power("Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can't discern color in darkness, only shades of gray.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Dwarven Resilience", "You have advantage on saving throws against poison, and you have resistance against poison damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Stonecutting", "Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered proficient in the History skill and add double your proficiency bonus to the check, instead of your normal proficiency bonus.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));

        if (_subRace.equals("Hill")) {
            racialTraits.add(new Power("Dwarven Toughness", "+1 HP/level.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        else { // Mountain
            racialTraits.add(new Power("Dwarven Armor Training", "Proficiency with Light and Medium armor.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return racialTraits;
    }

    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.BATTLEAXE);
        proficiencies.add(Enumerations.Proficiencies.HANDAXE);
        proficiencies.add(Enumerations.Proficiencies.LIGHT_HAMMER);
        proficiencies.add(Enumerations.Proficiencies.WARHAMMER);

        return proficiencies;
    }

    @Override
    public List<Enumerations.Proficiencies> getArmorProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        if (_subRace.equals("Mountain")) {
            proficiencies.add(Enumerations.Proficiencies.ARMOR_LIGHT);
            proficiencies.add(Enumerations.Proficiencies.ARMOR_MEDIUM);
        }
        return proficiencies;
    }
}
