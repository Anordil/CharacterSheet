package com.guigeek.devilopers.dd5charactersheet.character.classes.sorcerer;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
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
    static final long serialVersionUID = 210L;

    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.sorcererArchetypes : -1;
    }

    @Override
    public int getAC(Character character) {
        if (_archetypes.size() != 0 && _archetypes.get(0) instanceof Sorcerer_dragon) {
            int normalAc = super.getAC(character);
            int dragonAC = 13 + character.getModifier(Enumerations.Attributes.DEX);

            if (character._equippedArmor == null || character._equippedArmor._type == Enumerations.ArmorTypes.NONE) {
                return Math.max(normalAc, dragonAC);
            }
        }
        return super.getAC(character);
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.sorcerer_wild))) {
            return new Sorcerer_wild();
        } else if (iName.equals(App.getResString(R.string.sorcerer_storm))) {
            return new Sorcerer_storm();
        } else if (iName.equals(App.getResString(R.string.sorcerer_dragon))) {
            return new Sorcerer_dragon();
        }
        return null;
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

    int[][] _spellSlotsSubclass = {
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

    int[][] _spellsKnownSubclass = {
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

    public Sorcerer(){
        _spellSlots = _spellSlotsSubclass;
        _spellsKnown = _spellsKnownSubclass;
    }


    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_sorcerer);
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
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Sorcerer level " + iNewCharacterLevel + " benefits:");

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
