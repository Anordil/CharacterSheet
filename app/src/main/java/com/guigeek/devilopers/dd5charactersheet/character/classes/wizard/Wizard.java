package com.guigeek.devilopers.dd5charactersheet.character.classes.wizard;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;

import java.util.LinkedList;
import java.util.List;


public class Wizard extends BaseClass {
    static final long serialVersionUID = 2900L;


    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 2 && _archetypes.size() == 0 ? R.array.wizardArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.wizard_bladesinging))) {
            return new Wizard_bladesinging();
        } else if (iName.equals(App.getResString(R.string.wizard_abjuration))) {
            return new Wizard_abjuration();
        } else if (iName.equals(App.getResString(R.string.wizard_conjuration))) {
            return new Wizard_conjuration();
        } else if (iName.equals(App.getResString(R.string.wizard_divination))) {
            return new Wizard_divination();
        } else if (iName.equals(App.getResString(R.string.wizard_enchantment))) {
            return new Wizard_enchantment();
        } else if (iName.equals(App.getResString(R.string.wizard_evocation))) {
            return new Wizard_evocation();
        } else if (iName.equals(App.getResString(R.string.wizard_illusion))) {
            return new Wizard_illusion();
        } else if (iName.equals(App.getResString(R.string.wizard_necromancy))) {
            return new Wizard_necromancy();
        } else if (iName.equals(App.getResString(R.string.wizard_transmutation))) {
            return new Wizard_transmutation();
        } else if (iName.equals(App.getResString(R.string.wizard_war))) {
            return new Wizard_war();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.INT,
                Enumerations.SavingThrows.WIS
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        return fettles;
    }

    int[][] _spellSlotsOverride = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
            {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 2, 0, 0, 0, 0, 0, 0},//lv 5
            {0, 4, 3, 3, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 1, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 2, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 3, 1, 0, 0, 0, 0},
            {0, 4, 3, 3, 3, 2, 0, 0, 0, 0},//lv 10
            {0, 4, 3, 3, 3, 2, 1, 0, 0, 0},
            {0, 4, 3, 3, 3, 2, 1, 0, 0, 0},
            {0, 4, 3, 3, 3, 2, 1, 1, 0, 0},
            {0, 4, 3, 3, 3, 2, 1, 1, 0, 0},
            {0, 4, 3, 3, 3, 2, 1, 1, 1, 0},//lv 15
            {0, 4, 3, 3, 3, 2, 1, 1, 1, 0},
            {0, 4, 3, 3, 3, 2, 1, 1, 1, 1},
            {0, 4, 3, 3, 3, 3, 1, 1, 1, 1},
            {0, 4, 3, 3, 3, 3, 2, 1, 1, 1},
            {0, 4, 3, 3, 3, 3, 2, 2, 1, 1}//ln 20
    };

    int[][] _spellsKnownOverride = {
            // cantrips, spells
            {0, 0},
            {3, 0}, //character lv 1
            {3, 0},
            {3, 0},
            {3, 0},
            {4, 0},//lv 5
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0},//lv 10
            {5, 0},
            {5, 0},
            {5, 0},
            {5, 0},
            {5, 0},//lv 15
            {5, 0},
            {5, 0},
            {5, 0},
            {5, 0},
            {5, 0}//ln 20
    };


    public Wizard(){
        _spellSlots = _spellSlotsOverride;
        _spellsKnown = _spellsKnownOverride;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.INT;
    }

    @Override
    public int getHitDie() {
        return 6;
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_wizard);
        return name;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Wizard level " + iNewCharacterLevel + " benefits:");

        if (iNewCharacterLevel == 1) {
            levelUp.add("");
        }

        return levelUp;
    }


    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_wizard_staff;
    }
}
