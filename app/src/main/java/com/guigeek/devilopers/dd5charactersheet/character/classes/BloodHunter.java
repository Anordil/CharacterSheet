package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;


public class BloodHunter extends BaseClass {
    static final long serialVersionUID = 203L;

    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel >= 3 && _archetypes.size() == 0 ? R.array.bloodHunterOrders : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.bloodhunter_lycan))) {
            return new BloodHunter_lycan();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.WIS,
                Enumerations.SavingThrows.STR
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        // Hardened Soul
        if (character._level >= 14) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.FEAR.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.CHARM_MAGIC.ordinal()));
        }

        return fettles;
    }

    int[][] _spellsKnown = {
            // N/A, blood curses
            {0, 0},
            {0, 0}, //character lv 1
            {0, 1},
            {0, 1},
            {0, 1},
            {0, 2},//lv 5
            {0, 2},
            {0, 2},
            {0, 2},
            {0, 3},
            {0, 3},//lv 10
            {0, 3},
            {0, 3},
            {0, 4},
            {0, 4},
            {0, 4},//lv 15
            {0, 4},
            {0, 5},
            {0, 5},
            {0, 5},
            {0, 6}//ln 20
    };


    public BloodHunter(){

    }


    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        int level = iCharacter._class instanceof BloodHunter ? iCharacter._level : iCharacter._levelSecondaryClass;
        return (level >= 5 ? 2 : 1);
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_blood_hunter);
        return name;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Welcome to Blood Hunter level " + iNewCharacterLevel + "!");

        // Blood Curses
        if (iNewCharacterLevel == 2) {
            levelUp.add("You know 1 Blood Curse.");
        }
        else if (iNewCharacterLevel == 5) {
            levelUp.add("You know 2 Blood Curses.");
        }
        else if (iNewCharacterLevel == 9) {
            levelUp.add("You know 3 Blood Curses.");
        }
        else if (iNewCharacterLevel == 13) {
            levelUp.add("You know 4 Blood Curses.");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You know 5 Blood Curses.");
        }
        else if (iNewCharacterLevel == 20) {
            levelUp.add("You know 6 Blood Curses.");
        }


        // Base-Blood Hunter powers
        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Hunter's Bane.");
            levelUp.add("You gained one Primal Crimson Rite (Fire, Cold or Lightning).");
        }
        if (iNewCharacterLevel == 2) {
            levelUp.add("You gained a Fighting style! Choose it in the Feats screen.");
            levelUp.add("You gained Blood Maledict.");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained one Primal Crimson Rite (Fire, Cold or Lightning).");
            levelUp.add("Blood Maledict is now 2x/rest.");
        }
        if (iNewCharacterLevel == 9) {
            levelUp.add("You gained Grim Psychometry.");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Dark Velocity.");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Your Hunter's Bane improved.");
            levelUp.add("You gained one Primal Crimson Rite (Fire, Cold or Lightning).");
            levelUp.add("Blood Maledict is now 3x/rest.");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained one Esoteric Crimson Rite (Thunder, Necrotic or Psychic).");
            levelUp.add("You gained Hardened Soul.");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Blood Maledict is now 4x/rest.");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Sanguine Mastery.");
        }

        return levelUp;
    }

    private int getCrimsonRiteDie(int iLevel) {
        if (iLevel >= 17) return 10;
        else if (iLevel >= 11) return 8;
        else if (iLevel >= 5) return 6;
        else return 4;
    }

    private int getBloodMaledictUses(int iLevel) {
        if (iLevel >= 17) return 4;
        else if (iLevel >= 11) return 3;
        else if (iLevel >= 6) return 2;
        else return 1;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 11) {
            powers.add(new Power("Hunter's Bane 2", "You may suffer " + getCrimsonRiteDie(iLevel) + " damages to gain advantage on an Insight or Intimidation check.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        powers.add(new Power("Hunter's Bane", "You have advantage on Survival checks to track Fey, Fiends or Undead, as well as Intelligence checks to recall information about them. You cannot be surprised while actively tracking one of these creature types, and can only be tracking one type at a time.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        powers.add(new Power("Crimson Rite", "Suffer " + iLevel + " damages and lower your max HP by " + iLevel
                + " to imbue a weapon so it deals an extra 1D" + getCrimsonRiteDie(iLevel) + " damage of the type of a Rite you know. The Rite fades if you let go of the weapon. You may imbue several weapons, suffering the HP loss for each. When the rite fades, your max HP is restored to its previous level but you do not regain HP.", "Self", -1, -1, false, Enumerations.ActionType.BONUS_ACTION));


        if (iLevel >= 2) {
            powers.add(new Power("Blood Maledict", "You know " + _spellsKnown[iLevel][1] + " blood curses.\n" +
                    "When you use your Blood Maledict, you choose which curse to invoke.\n" +
                    "While invoking a blood curse, but before it affects the target, you may choose to amplify the curse by suffering 1D" + getCrimsonRiteDie(iLevel)
                    + ". An amplified curse gains an additional effect, noted in the curse’s description. Creatures that do not have blood in their bodies are immune to blood curses (DM’s discretion).", "", getBloodMaledictUses(iLevel), -1, false, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 9) {
            powers.add(new Power("Grim Psychometry", "You can take 10 minutes to meditate on an object to discern vague details regarding any lingering evil or wicked past surrounding it. Make a Wisdom ability check. Based on the result, the DM may reveal obscure information about dark events that may have previously surrounded the object, or hints toward a sinister purpose. This feature has no effect on objects untouched by evil. An object can only be targeted by this feature once, and future attempts reveal no further details. You cannot use this feature again until you finish a short or long rest.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Dark Velocity", "You gain darkvision out to 30 feet, or if you have darkvision, extend it out an additional 30 feet. While in dim light or darkness, your speed increases by 10 feet, and attacks of opportunity made against you have disadvantage.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Hardened Soul", "You can no longer become frightened, and you have advantage on saving throws against magical Charm effects.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Sanguine Mastery", "When you are below one fourth of your current maximum hit points, all of your crimson rite damage dice are maximized.\n" +
                    "\n" +
                    "In addition, when you critically hit with a weapon attack that bears your crimson rite, you regain a use of your Blood Maledict feature.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_werewolf;
    }
}
