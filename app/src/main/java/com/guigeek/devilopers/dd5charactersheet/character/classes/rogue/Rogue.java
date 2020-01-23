package com.guigeek.devilopers.dd5charactersheet.character.classes.rogue;

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


public class Rogue extends BaseClass {
    static final long serialVersionUID = 207L;

    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.rogueArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.rogue_assassin))) {
            return new Rogue_assassin();
        } else if (iName.equals(App.getResString(R.string.rogue_swashbuckler))) {
            return new Rogue_swashbuckler();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.DEX,
                Enumerations.SavingThrows.INT
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return fettles;
    }

    public Rogue(){}

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_rogue);
        return name;
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Rogue level " + iNewCharacterLevel + " benefits:");

        // Sneak attack
        if (iNewCharacterLevel %2 != 0) {
            levelUp.add("Your Sneak Attack now deals " + (iNewCharacterLevel+1)/2 + "D6 bonus damage.");
        }

        if (iNewCharacterLevel == 1) {
            levelUp.add("Choose two of your skill proficiencies (or one + thieves' tool). Your proficiency bonus is doubled for those.");
            levelUp.add("Gained Thieves' cant !");
        }

        if (iNewCharacterLevel == 2) {
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
        powers.add(new Power("Thieves Cant", "You speak the thieves' secret language.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));


        if (iLevel >= 2) {
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
            powers.add(new Power("Stroke of Luck", "You can turn a missed attack roll into a hit, or a failed ability check into a critical success. You may only use this feature once per short/long rest.", "Self", 1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_rogue;
    }
}
