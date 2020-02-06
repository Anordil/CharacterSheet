package com.guigeek.devilopers.dd5charactersheet.character.classes.druid;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Druid_land extends BaseArchetype {
    static final long serialVersionUID = 2503L;

    @Override
    public String getName() {
        return App.getResString(R.string.druid_land);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("You learn an additional Druid cantrip.");
            levelUp.add("Gained Natural Recovery");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Land’s Stride");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Nature’s Ward");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Nature’s Sanctuary");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Natural Recovery", "During a short rest, you choose expended spell slots to recover. The spell slots can have a combined level that is equal to or less than half your druid level (rounded up), and none of the slots can be 6th level or higher. You can’t use this feature again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Land’s Stride", "Moving through nonmagical difficult terrain costs you no extra movement. You can also pass through nonmagical plants without being slowed by them and without taking damage from them if they have thorns, spines, or a similar hazard.\n" +
                    "\n" +
                    "In addition, you have advantage on saving throws against plants that are magically created or manipulated to impede movement, such those created by the entangle spell.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Nature’s Ward", "You can’t be charmed or frightened by elementals or fey, and you are immune to poison and disease.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Nature’s Sanctuary", "Creatures of the natural world sense your connection to nature and become hesitant to attack you. When a beast or plant creature attacks you, that creature must make a Wisdom saving throw against your druid spell save DC. On a failed save, the creature must choose a different target, or the attack automatically misses. On a successful save, the creature is immune to this effect for 24 hours.\n" +
                    "\n" +
                    "The creature is aware of this effect before it makes its attack against you.", "", -1, 8+iCharac.getProficiencyBonus()+iCharac.getModifier(Enumerations.Attributes.WIS), true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        if (character._level >= 10) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.POISONED.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.DISEASES.ordinal()));
        }

        return fettles;
    }
}
