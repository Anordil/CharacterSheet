package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public abstract class Paladin_base implements Class {

    @Override
    public int getAC(Character character) {
        int ac = character._equippedArmor.getAC(character);

        if (character._equippedShield != null && character._equippedShield._type == Enumerations.ArmorTypes.SHIELD) {
            ac+= character._equippedShield.getAC(character);
        }

        return ac;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        int level = character._level;
        if (level >= 3) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.DISEASES.ordinal()));
        }
        if (level >= 6) {
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_MODIFIER, character.getModifier(Enumerations.Attributes.CHA), Enumerations.SavingThrows.ALL.ordinal()));
        }
        if (level >= 10) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.FEAR.ordinal()));
        }
        if (level >= 11) {
            fettles.add(new Fettle(Enumerations.FettleType.ATTACK_DAMAGE_DICE, "1D8", Enumerations.DamageTypes.RADIANT.ordinal()));
        }

        return fettles;
    }

    int[][] _spellSlots = {
                // spell level 0-9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
                {0, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 2, 0, 0, 0, 0, 0, 0, 0},//lv 5
                {0, 4, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 2, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 2, 0, 0, 0, 0, 0, 0},//lv 10
                {0, 4, 3, 3, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 1, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 1, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 2, 0, 0, 0, 0, 0},//lv 15
                {0, 4, 3, 3, 2, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 3, 1, 0, 0, 0, 0},
                {0, 4, 3, 3, 3, 1, 0, 0, 0, 0},
                {0, 4, 3, 3, 3, 2, 0, 0, 0, 0},
                {0, 4, 3, 3, 3, 2, 0, 0, 0, 0}//ln 20
        };

    public Paladin_base(){}
    public Paladin_base(Paladin_base other) {
        _spellSlots = other._spellSlots;
    }




    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public boolean isCaster() {
        return true;
    }

    @Override
    public int[] getSpellSlots(int iCharacterLevel) {
        return _spellSlots[Math.min(20, iCharacterLevel)];
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        int level = iCharacter._class.getName().startsWith("Paladin") ? iCharacter._level : iCharacter._levelSecondaryClass;
        return (level >= 5 ? 2 : 1);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Welcome to Paladin level " + iNewCharacterLevel + "!");
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Divine Sense", "Until the end of your next turn, you know the location of any celestial, fiend, or undead within 60 feet of you that is not behind total cover. You know the type (celestial, fiend, or undead) of any being whose presence you sense, but not its identity. Within the same radius, you also detect the presence of any place or object that has been consecrated or desecrated, as with the hallow spell.", "60ft", 1 + iCharac.getModifier(Enumerations.Attributes.CHA), -1, true, Enumerations.ActionType.ACTION));
            powers.add(new Power("Lay on hands", "Heal HP. 5hp to remove poison/disease.", "Melee", 5*iLevel, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 2) {
            powers.add(new Power("Divine Smite", "Expend spell slot to add (1 + spell level)D8 radiant damage to the next melee attack.", "60ft", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 3) {
            powers.add(new Power("Divine Health", "Immune to diseases", "Self", -1, -1, true,Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            if (iLevel >= 18) {
                powers.add(new Power("Aura of Protection", "Allies within 30ft gain +CHA to their saving throws", "30ft", -1, -1, true,Enumerations.ActionType.PASSIVE));
            }
            else {
                powers.add(new Power("Aura of Protection", "Allies within 10ft gain +CHA to their saving throws", "10ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        }
        if (iLevel >= 10) {
            if (iLevel >= 18) {
                powers.add(new Power("Aura of Courage", "Allies within 30ft are immune to fear", "30ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            else {
                powers.add(new Power("Aura of Courage", "Allies within 10ft are immune to fear", "10ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        }
        if (iLevel >= 11) {
            powers.add(new Power("Improved Divine Smite", "+1D8 radiant damage on melee hits.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Cleansing touch", "Remove spell on self/ally", "Melee", iCharac.getModifier(Enumerations.Attributes.CHA), -1, true,Enumerations.ActionType.ACTION));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_paladin;
    }
}
