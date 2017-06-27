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


public abstract class Rogue_base implements Class {

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
        return fettles;
    }

    int[][] _spellSlots = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 10
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 15
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//ln 20
    };


    public Rogue_base(){}
    public Rogue_base(Rogue_base other) {
        _spellSlots = other._spellSlots;
    }



    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public boolean isCaster() {
        return false;
    }

    @Override
    public int[] getSpellSlots(int iCharacterLevel) {
        return _spellSlots[Math.min(20, iCharacterLevel)];
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        return 1;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Welcome to Rogue level " + iNewCharacterLevel + "!");

        // Sneak attack
        if (iNewCharacterLevel %2 != 0) {
            levelUp.add("Your Sneak Attack now deals " + (iNewCharacterLevel+1)/2 + "D6 bonus damage.");
        }

        if (iNewCharacterLevel == 1) {
            levelUp.add("Choose two of your skill proficiencies (or one + thieves' tool). Your proficiency bonus is doubled for those.");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Thieves' cant !");
            levelUp.add("Gained Cunning Action !");
        }


        if (iNewCharacterLevel == 5) {
            levelUp.add("Gained Uncanny Dodge !");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Choose two more skill proficiency for a double proficiency bonus.");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Evasion !");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Gained Reliable Talent !");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Blindsense !");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("You are now proficient in Wisdom saving throws !");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Elusive !");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("Gained Stroke Of Luck !");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();


        int sneakAttackDice = (int) Math.ceil(((double)iLevel)/2);

        powers.add(new Power("Sneak Attack", "Once per turn, you may deal an extra " + sneakAttackDice + "D6 damage to one creature you hit with an attack if you have advantage on the attack, using a finesse or ranged weapon. You don't need advantage if the enemy is adjacent to an ally, isn't incapacitated and you don't have disadvantage.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));



        if (iLevel >= 2) {
            powers.add(new Power("Thieves Cant", "You speak the thieves' secret language.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Cunning Action", "You may use you bonus action to Dash, Disengage or Hide.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        if (iLevel >= 5) {
            powers.add(new Power("Uncanny Dodge", "When an attacked that you can see hits you with an attack, you can use your reaction to halve the damage against you.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Evasion", "When rolling a saving throw to halve the damages, you take no damage on a success and half damage on a failure.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Reliable Talent", "You cannot roll below 10 for ability checks you are proficient with.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Blindsense", "If you are able to hear, you are aware of the present of any hidden or invisible creature within 10ft of you.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Elusive", "No attack roll has the advantage against you while you aren't incapacitated.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Stroke of Luck", "You can turn a missed attack roll into a hit, or a failed ability check as a critical success. You may only use this feature once per short/long rest.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }


    @Override
    public int getIconResource() {
        return R.drawable.ic_rogue;
    }
}