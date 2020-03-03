package com.guigeek.devilopers.dd5charactersheet.character.classes.bard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bloodhunter.BloodHunter_lycan;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;


public class Bard extends BaseClass {
    static final long serialVersionUID = 2200L;


    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.bardArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.bard_glamour))) {
            return new Bard_glamour();
        } else if (iName.equals(App.getResString(R.string.bard_lore))) {
            return new Bard_lore();
        } else if (iName.equals(App.getResString(R.string.bard_swords))) {
            return new Bard_swords();
        } else if (iName.equals(App.getResString(R.string.bard_valor))) {
            return new Bard_valor();
        } else if (iName.equals(App.getResString(R.string.bard_whispers))) {
            return new Bard_whispers();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.DEX,
                Enumerations.SavingThrows.CHA
        };
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
            {2, 4}, //character lv 1
            {2, 5},
            {2, 6},
            {3, 7},
            {3, 8},//lv 5
            {3, 9},
            {3, 10},
            {3, 11},
            {3, 12},
            {4, 14},//lv 10
            {4, 15},
            {4, 15},
            {4, 16},
            {4, 118},
            {4, 19},//lv 15
            {4, 19},
            {4, 20},
            {4, 22},
            {4, 22},
            {4, 22}//ln 20
    };


    public Bard(){
        _spellSlots = _spellSlotsOverride;
        _spellsKnown = _spellsKnownOverride;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_bard);
        return name;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Bard level " + iNewCharacterLevel + " benefits:");

        // Spells
        levelUp.add("You know " + _spellsKnown[iNewCharacterLevel][0] + " cantrips and " + _spellsKnown[iNewCharacterLevel][0] + " spells.");


        // Base-Blood Hunter powers
        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Bardic Inspiration (d6)");
        }
        if (iNewCharacterLevel == 2) {
            levelUp.add("You gained Song of Rest (d6)");
            levelUp.add("You gained Jack of All Trades");
        }
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Expertise (double your proficiency on 2 skills)");
        }
        if (iNewCharacterLevel == 5) {
            levelUp.add("You gained Bardic Inspiration (d8)");
            levelUp.add("You gained Font of Inspiration");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Countercharm");
        }
        if (iNewCharacterLevel == 9) {
            levelUp.add("You gained Song of Rest (d8)");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Bardic Inspiration (d10)");
            levelUp.add("You gained Expertise (double your proficiency on 2 skills)");
            levelUp.add("You gained Magical Secrets - Choose 2 spells from any list");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("You gained Song of Rest (d10)");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Magical Secrets - Choose 2 more spells from any list");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Bardic Inspiration (d12)");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("You gained Song of Rest (d12)");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("You gained Magical Secrets - Choose 2 more spells from any list");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Superior Inspiration");
        }


        return levelUp;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter, int classLevel) {
        return classLevel >= 6 && (_archetypes.get(0) instanceof Bard_swords || _archetypes.get(0) instanceof Bard_valor) ? 2 : 1;
    }

    public static int getInspirationDie(int iLevel) {
        return iLevel >= 15 ? 12 : iLevel >= 10 ? 10 : iLevel >= 5 ? 8 : 6;
    }

    public static int getRestDie(int iLevel) {
        return iLevel >= 17 ? 12 : iLevel >= 13 ? 10 : iLevel >= 9 ? 8 : 6;
    }


    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Bardic Inspiration", "Choose one creature other than yourself within 60 feet of you who can hear you. That creature gains one Bardic Inspiration die, a d" + getInspirationDie(iLevel) +
                    "\nOnce within the next 10 minutes, the creature can roll the die and add the number rolled to one ability check, attack roll, or saving throw it makes. The creature can wait until after it rolls the d20 before deciding to use the Bardic Inspiration die, but must decide before the DM says whether the roll succeeds or fails. Once the Bardic Inspiration die is rolled, it is lost. A creature can have only one Bardic Inspiration die at a time.", "60ft", iCharac.getModifier(Enumerations.Attributes.CHA), -1, iLevel < 5, Enumerations.ActionType.BONUS_ACTION));
        }

        if (iLevel >= 2) {
            powers.add(new Power("Song of Rest", "If you or any friendly creatures who can hear your performance regain hit points at the end of the short rest by spending one or more Hit Dice, each of those creatures regains an extra 1d" + getRestDie(iLevel), "", -1, -1, false, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Jack of All Trades", "You can add half your proficiency bonus, rounded down, to any ability check you make that doesn’t already include your proficiency bonus.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 5) {
            powers.add(new Power("Font of Inspiration", "You regain all of your expended uses of Bardic Inspiration when you finish a short or long rest.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Countercharm", "As an action, you can start a performance that lasts until the end of your next turn. During that time, you and any friendly creatures within 30 feet of you have advantage on saving throws against being frightened or charmed. A creature must be able to hear you to gain this benefit. The performance ends early if you are incapacitated or silenced or if you voluntarily end it (no action required).", "30ft", -1, -1, false, Enumerations.ActionType.ACTION));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Magical Secrets", "By 10th level, you have plundered magical knowledge from a wide spectrum of disciplines. Choose two spells from any classes, including this one. A spell you choose must be of a level you can cast, as shown on the Bard table, or a cantrip.\n" +
                    "\n" +
                    "The chosen spells count as bard spells for you and are included in the number in the Spells Known column of the Bard table.\n" +
                    "\n" +
                    "You learn two additional spells from any classes at 14th level and again at 18th level..", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 20) {
            powers.add(new Power("Superior Inspiration", "When you roll initiative and have no uses of Bardic Inspiration left, you regain one use.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public boolean isCaster() {
        return  true;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_bard;
    }

    @Override
    public String[] getClassSkills() {
        return new String[] {
                Enumerations.Skills.ANIMAL_HANDLING.toString(),
                Enumerations.Skills.ACROBATICS.toString(),
                Enumerations.Skills.ARCANA.toString(),
                Enumerations.Skills.ATHLETICS.toString(),
                Enumerations.Skills.DECEPTION.toString(),
                Enumerations.Skills.HISTORY.toString(),
                Enumerations.Skills.INSIGHT.toString(),
                Enumerations.Skills.INTIMIDATION.toString(),
                Enumerations.Skills.INVESTIHATION.toString(),
                Enumerations.Skills.NATURE.toString(),
                Enumerations.Skills.MEDICINE.toString(),
                Enumerations.Skills.PERCEPTION.toString(),
                Enumerations.Skills.PERFORMANCE.toString(),
                Enumerations.Skills.PERSUASION.toString(),
                Enumerations.Skills.RELIGION.toString(),
                Enumerations.Skills.SLEIGHT_OF_HAND.toString(),
                Enumerations.Skills.STEALTH.toString(),
                Enumerations.Skills.SURVIVAL.toString(),
        };
    }

    @Override
    public int getClassSkillCount() {
        return 3;
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

        proficiencies.add(Enumerations.Proficiencies.WEAPON_SIMPLE);
        proficiencies.add(Enumerations.Proficiencies.LONGSWORD);
        proficiencies.add(Enumerations.Proficiencies.SHORTSWORD);
        proficiencies.add(Enumerations.Proficiencies.RAPIER);
        proficiencies.add(Enumerations.Proficiencies.HAND_CROSSBOW);

        return proficiencies;
    }
}
