package com.guigeek.devilopers.dd5charactersheet.character.classes.wizard;

import android.content.Context;
import android.util.Log;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Wizard_bladesinging extends BaseArchetype {
    static final long serialVersionUID = 2901L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_bladesinging);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained proficiency with light armor, one type of one-handed melee weapon of your choice, and Performance.");
            levelUp.add("Gained Bladesong");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Extra Attack");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Song of Defense");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Song of Victory");
        }


        return levelUp;
    }

    @Override
    public int getChoosableFeature(int iLevel) {
        if (iLevel == 2) {
            return R.array.bladesingingWeapons;
        }
        return -1;
    }

    @Override
    public List<Enumerations.Proficiencies> getArmorProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.ARMOR_LIGHT);

        return proficiencies;
    }

    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();
        Log.d("Prof", _chosenStringFeature);
        switch (_chosenStringFeature) {
            case "Club": proficiencies.add(Enumerations.Proficiencies.CLUB); break;
            case "Dagger": proficiencies.add(Enumerations.Proficiencies.DAGGER); break;
            case "Handaxe": proficiencies.add(Enumerations.Proficiencies.HANDAXE); break;
            case "Javelin": proficiencies.add(Enumerations.Proficiencies.JAVELIN); break;
            case "Light hammer": proficiencies.add(Enumerations.Proficiencies.LIGHT_HAMMER); break;
            case "Mace": proficiencies.add(Enumerations.Proficiencies.MACE); break;
            case "Quarterstaff": proficiencies.add(Enumerations.Proficiencies.QUARTERSTAFF); break;
            case "Sickle": proficiencies.add(Enumerations.Proficiencies.SICKLE); break;
            case "Spear": proficiencies.add(Enumerations.Proficiencies.SPEAR); break;
            case "Battleaxe": proficiencies.add(Enumerations.Proficiencies.BATTLEAXE); break;
            case "Flail": proficiencies.add(Enumerations.Proficiencies.FLAIL); break;
            case "Longsword": proficiencies.add(Enumerations.Proficiencies.LONGSWORD); break;
            case "Morningstar": proficiencies.add(Enumerations.Proficiencies.MORNINGSTAR); break;
            case "Rapier": proficiencies.add(Enumerations.Proficiencies.RAPIER); break;
            case "Scimitar": proficiencies.add(Enumerations.Proficiencies.SCIMITAR); break;
            case "Shortsword": proficiencies.add(Enumerations.Proficiencies.SHORTSWORD); break;
            case "Trident": proficiencies.add(Enumerations.Proficiencies.TRIDENT); break;
            case "War pick": proficiencies.add(Enumerations.Proficiencies.WAR_PICK); break;
            case "Warhammer": proficiencies.add(Enumerations.Proficiencies.WARHAMMER); break;
        }
        return proficiencies;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Bladesong", "you can invoke a secret elven magic called the Bladesong, provided that you aren’t wearing medium or heavy armor or using a shield. It graces you with supernatural speed, agility, and focus.\n" +
                    "\n" +
                    "You can use a bonus action to start the Bladesong, which lasts for 1 minute. It ends early if you are incapacitated, if you don medium or heavy armor or a shield, or if you use two hands to make an attack with a weapon. You can also dismiss the Bladesong at any time you choose (no action required).\n" +
                    "\n" +
                    "While your Bladesong is active, you gain the following benefits:\n" +
                    "\n" +
                    "You gain a bonus to your AC equal to " + iCharac.getModifier(Enumerations.Attributes.INT) + ".\n" +
                    "Your walking speed increases by 10 feet.\n" +
                    "You have advantage on Dexterity (Acrobatics) checks.\n" +
                    "You gain a " + iCharac.getModifier(Enumerations.Attributes.INT) + " bonus to any Constitution saving throw you make to maintain your concentration on a spell.\n" +
                    "<i>You can use this feature twice. You regain all expended uses of it when you finish a short or long rest.</i>", "", 2, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }

        if (iLevel >= 10) {
            powers.add(new Power("Song of Defense", "When you take damage and your Bladesong is active, you can use your reaction to expend one spell slot and reduce that damage to you by an amount equal to five times the spell slot’s level.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Song of Victory", "You add your Intelligence modifier (minimum of +1) to the damage of your melee weapon attacks while your Bladesong is active.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getDescription() {
        return "<b>Restricted to Elves and Half-Elves</b><br>Bladesingers are elves who bravely defend their people and lands. They are elf wizards who master a school of sword fighting grounded in a tradition of arcane magic. In combat, a bladesinger uses a series of intricate, elegant maneuvers that fend off harm and allow the bladesinger to channel magic into devastating attacks and a cunning defense.";
    }
}
