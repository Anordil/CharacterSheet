package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Sorcerer extends BaseClass {

    @Override
    public int getArchetypes() {
        return R.array.sorcererArchetypes;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.CON,
                Enumerations.SavingThrows.CHA
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        int level = character._class instanceof Sorcerer ? character._level : character._levelSecondaryClass;
        return fettles;
    }

    int[][] _spellSlots = {
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

    int[][] _spellsKnown = {
            // cantrips, spells
            {0, 0},
            {4, 2}, //character lv 1
            {4, 3},
            {4, 4},
            {5, 5},
            {5, 6},//lv 5
            {5, 7},
            {5, 8},
            {5, 9},
            {5, 10},
            {6, 11},//lv 10
            {6, 12},
            {6, 12},
            {6, 13},
            {6, 13},
            {6, 14},//lv 15
            {6, 14},
            {6, 15},
            {6, 15},
            {6, 15},
            {6, 15}//ln 20
    };

    public Sorcerer(){}


    @Override
    public String getName() {
        String name = App.getResString(R.string.class_sorcerer);
        if (_archetype != null) {
            name += " (" + _archetype.getName() + ")";
        }
        return name;
    }

    @Override
    public int getHitDie() {
        return 6;
    }

    @Override
    public boolean isCaster() {
        return true;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Welcome to Sorcerer level " + iNewCharacterLevel + "!");

        if (iNewCharacterLevel >= 2) {
            levelUp.add("You now have " + iNewCharacterLevel + " Sorcery points.");
        }

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained 2 Metamagic options!");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained a 3rd Metamagic option!");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained a 4th Metamagic option!");
        }

        if (iNewCharacterLevel == 20) {
            levelUp.add("Gained Sorcerous Restoration!");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Font Magic", "Spend Sorcery points to restore spell slots or use metamagic options. Exhaust a spell slot to regain Sorcery points (Bonus Action). Spell level/sorcery point conversion: 1(2), 2(3), 3(5), 4(6), 5(7)", "", iLevel, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Sorcerous Restoration", "You gain 4 expended Sorcery points when you finish a Short Rest.", "Self", -1, -1, true,Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_wizard_staff;
    }
}
