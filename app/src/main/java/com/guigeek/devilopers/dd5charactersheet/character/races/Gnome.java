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
public class Gnome extends BaseRace {
    public static final long serialVersionUID = 107L;


    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return fettles;
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_gnome);
    }

    public Gnome(){}

    @Override
    public int getSpeedInFeet() {
        return 25;
    }

    @Override
    public int getSubraceArrayId() {
        return R.array.gnomeSubRaces;
    }

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[2];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.INT);

        if (_subRace.equals("Deep")) {
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
        } if (_subRace.equals("Forest")) {
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
        } else { // Rock
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CON);
        }
        return raceBonuses;
    }

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power(_subRace.equals("Deep") ? "Superior Darkvision":"Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can't discern color in darkness, only shades of gray.", _subRace.equals("Deep") ? "12ft" : "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Gnome Cunning", "You have advantage on INT/WIS/CHA saving throws against magic.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));

        if (_subRace.equals("Deep")) {
            racialTraits.add(new Power("Stone Camouflage", "Advantage on Stealth checks in rocky terrain.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        } if (_subRace.equals("Forest")) {
            racialTraits.add(new Power("Natural Illusionist", "You know the minor illusion cantrip. Intelligence is your spellcasting ability for it.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            racialTraits.add(new Power("Speak with small beasts", "Through sounds and gestures, you can communicate simple ideas with Small or smaller beasts. Forest gnomes love animals and often keep squirrels, badgers, rabbits, moles, woodpeckers, and other creatures as beloved pets.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        } else { // Rock
            racialTraits.add(new Power("Artificer's Lore", "Whenever you make an Intelligence (History) check related to magic items, alchemical objects, or technological devices, you can add twice your proficiency bonus, instead of any proficiency bonus you normally apply.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            racialTraits.add(new Power("Tinker", "You have proficiency with artisan’s tools (tinker’s tools). Using those tools, you can spend 1 hour and 10 gp worth of materials to construct a Tiny clockwork device (AC 5, 1 hp). The device ceases to function after 24 hours (unless you spend 1 hour repairing it to keep the device functioning), or when you use your action to dismantle it; at that time, you can reclaim the materials used to create it. You can have up to three such devices active at a time.\n" +
                    "\n" +
                    "When you create a device, choose one of the following options:\n" +
                    "\n" +
                    "Clockwork Toy. This toy is a clockwork animal, monster, or person, such as a frog, mouse, bird, dragon, or soldier. When placed on the ground, the toy moves 5 feet across the ground on each of your turns in a random direction. It makes noises as appropriate to the creature it represents.\n" +
                    "\n" +
                    "Fire Starter. The device produces a miniature flame, which you can use to light a candle, torch, or campfire. Using the device requires your action.\n" +
                    "\n" +
                    "Music Box. When opened, this music box plays a single song at a moderate volume.\n" +
                    "The box stops playing when it reaches the song’s end or when it is closed.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return racialTraits;
    }
}
