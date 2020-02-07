package com.guigeek.devilopers.dd5charactersheet.character.classes.wizard;

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

public class Wizard_abjuration extends BaseArchetype {
    static final long serialVersionUID = 2802L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_abjuration);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Arcane Ward");
            levelUp.add("Gained School Savant");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Projected Ward");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Improved Abjuration");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Spell Resistance");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("School Savant", "The gold and time you spend to copy a spell into your spellbook is halved for spells of your chosen school.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            int hp = 2*iLevel + iCharac.getModifier(Enumerations.Attributes.INT);
            powers.add(new Power("Arcane Ward", "When you cast an abjuration spell of 1st level or higher, you can simultaneously use a strand of the spell’s magic to create a magical ward on yourself that lasts until you finish a long rest. The ward has a hit point maximum equal to " + hp + ". Whenever you take damage, the ward takes the damage instead. If this damage reduces the ward to 0 hit points, you take any remaining damage.\n" +
                    "\n" +
                    "While the ward has 0 hit points, it can’t absorb damage, but its magic remains. Whenever you cast an abjuration spell of 1st level or higher, the ward regains a number of hit points equal to twice the level of the spell.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Projected Ward", "When a creature that you can see within 30 feet of you takes damage, you can use your reaction to cause your Arcane Ward to absorb that damage. If this damage reduces the ward to 0 hit points, the warded creature takes any remaining damage.", "30ft", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Improved Abjuration", "When you cast an abjuration spell that requires you to make an ability check as a part of casting that spell (as in counterspell and dispel magic), you add your proficiency bonus to that ability check.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Spell Resistance", "You have advantage on saving throws against spells.\n" +
                    "\n" +
                    "Furthermore, you have resistance against the damage of spells.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (character._level >= 14) {
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.SPELLS.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.SPELLS.ordinal()));
        }

        return fettles;
    }
}
