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


public class Rogue_assassin implements Class, Externalizable {

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

    public static final long serialVersionUID = 205L;
    protected int _version = 1;


    public Rogue_assassin(){}
    public Rogue_assassin(Rogue_assassin other) {
        _spellSlots = other._spellSlots;
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_spellSlots);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        _version = version;
        _spellSlots = (int[][])oi.readObject();
    }

    @Override
    public String getName() {
        return App.getResString(R.string.class_rogue);
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
    public String[] getLevelUpBenefits(int iNewCharacterLevel) {
        String[] levelUp = new String[100];
        int index = 0;
        levelUp[index++] = "Welcome to Rogue level " + iNewCharacterLevel + "!";

        // Sneak attack
        if (iNewCharacterLevel %2 != 0) {
            levelUp[index++] = "Your Sneak Attack now deals " + (iNewCharacterLevel+1)/2 + "D6 bonus damage.";
        }

        if (iNewCharacterLevel == 1) {
            levelUp[index++] = "Choose two of your skill proficiencies (or one + thieves' tool). Your proficiency bonus is doubled for those.";
        }

        if (iNewCharacterLevel == 2) {
            levelUp[index++] = "Gained Thieves' cant !";
            levelUp[index++] = "Gained Cunning Action !";
        }

        if (iNewCharacterLevel == 3) {
            levelUp[index++] = "You are now proficient with the Disguise and Poison kits.";
            levelUp[index++] = "Gained Assassinate !";
        }

        if (iNewCharacterLevel == 5) {
            levelUp[index++] = "Gained Uncanny Dodge !";
        }
        if (iNewCharacterLevel == 6) {
            levelUp[index++] = "Choose two more skill proficiency for a double proficiency bonus.";
        }
        if (iNewCharacterLevel == 7) {
            levelUp[index++] = "Gained Evasion !";
        }
        if (iNewCharacterLevel == 9) {
            levelUp[index++] = "Gained Infiltration Expertise !";
        }
        if (iNewCharacterLevel == 11) {
            levelUp[index++] = "Gained Reliable Talent !";
        }
        if (iNewCharacterLevel == 13) {
            levelUp[index++] = "Gained Impostor !";
        }
        if (iNewCharacterLevel == 14) {
            levelUp[index++] = "Gained Blindsense !";
        }
        if (iNewCharacterLevel == 15) {
            levelUp[index++] = "You are now proficient in Wisdom saving throws !";
        }
        if (iNewCharacterLevel == 17) {
            levelUp[index++] = "Gained Death strike !";
        }
        if (iNewCharacterLevel == 18) {
            levelUp[index++] = "Gained Elusive !";
        }
        if (iNewCharacterLevel == 20) {
            levelUp[index++] = "Gained Stroke Of Luck !";
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

        if (iLevel >= 3) {
            powers.add(new Power("Assassinate", "You have advantage on attack rolls against any creature that hasn't taken a turn in combat yet. In addition, any hit against a surprised creature is critical.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 5) {
            powers.add(new Power("Uncanny Dodge", "When an attacked that you can see hits you with an attack, you can use your reaction to halve the damage against you.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Evasion", "When rolling a saving throw to halve the damages, you take no damage on a success and half damage on a failure.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Infiltration Expertise", "You can unfailingly create false identities for yourself.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Reliable Talent", "You cannot roll below 10 for ability checks you are proficient with.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 13) {
            powers.add(new Power("Impostor", "You can mimic a person's speech, writing and behavior.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Blindsense", "If you are able to hear, you are aware of the present of any hidden or invisible creature within 10ft of you.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Death Strike", "When you attack and hit a surprised creature, it must make a CON saving throw of DC " + (8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.DEX)) + ". On a failed save, it takes double damage.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
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
