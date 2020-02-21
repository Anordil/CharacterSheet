package com.guigeek.devilopers.dd5charactersheet.character.classes.sorcerer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Sorcerer extends BaseClass {
    static final long serialVersionUID = 210L;

    @Override
    public int gainedClassFeatures(int classLevel) {
        if (classLevel == 3) {
            return 2;
        }
        else if (classLevel == 10 || classLevel == 17) {
            return 1;
        }

        return 0;
    }

    @Override
    public List<Power> getAllClassFeatures(int iClassLevel) {
        return Arrays.asList(metamagicOptions);
    }


    Power[] metamagicOptions = new Power[]{
            new Power("[Metamagic] Careful Spell", "When you cast a spell that forces other creatures to make a saving throw, you can protect some of those creatures from the spell’s full force. To do so, you spend 1 sorcery point and choose a number of those creatures up to your Charisma modifier (minimum of one creature). A chosen creature automatically succeeds on its saving throw against the spell.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Metamagic] Distant Spell", "When you cast a spell that has a range of 5 feet or greater, you can spend 1 sorcery point to double the range of the spell.\n" +
                    "\n" +
                    "When you cast a spell that has a range of touch, you can spend 1 sorcery point to make the range of the spell 30 feet.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Metamagic] Empowered Spell", "When you roll damage for a spell, you can spend 1 sorcery point to reroll a number of the damage dice up to your Charisma modifier (minimum of one). You must use the new rolls.\n" +
                    "\n" +
                    "You can use Empowered Spell even if you have already used a different Metamagic option during the casting of the spell.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Metamagic] Extended Spell", "When you cast a spell that has a duration of 1 minute or longer, you can spend 1 sorcery point to double its duration, to a maximum duration of 24 hours.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Metamagic] Heightened Spell", "When you cast a spell that forces a creature to make a saving throw to resist its effects, you can spend 3 sorcery points to give one target of the spell disadvantage on its first saving throw made against the spell.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Metamagic] Quickened Spell", "When you cast a spell that has a casting time of 1 action, you can spend 2 sorcery points to change the casting time to 1 bonus action for this casting.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Metamagic] Subtle Spell", "When you cast a spell, you can spend 1 sorcery point to cast it without any somatic or verbal components.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Metamagic] Twinned Spell", "When you cast a spell that targets only one creature and doesn’t have a range of self, you can spend a number of sorcery points equal to the spell’s level to target a second creature in range with the same spell (1 sorcery point if the spell is a cantrip).\n" +
                    "\n" +
                    "To be eligible, a spell must be incapable of targeting more than one creature at the spell’s current level. For example, magic missile and scorching ray aren’t eligible, but ray of frost and chromatic orb are.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
    };


    @Override
    public int nbOfFeatures(int iLevel) {
        return iLevel >= 17 ? 4 : iLevel >= 10 ? 3 : iLevel >= 3 ? 2: 0;
    }

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
        else if (iName.equals(App.getResString(R.string.sorcerer_divine))) {
            return new Sorcerer_divine();
        }
        else if (iName.equals(App.getResString(R.string.sorcerer_shadow))) {
            return new Sorcerer_shadow();
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
    public List<String> getLevelUpBenefits(final int iNewCharacterLevel, final Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Sorcerer level " + iNewCharacterLevel + " benefits:");

        if (iNewCharacterLevel >= 2) {
            levelUp.add("You now have " + iNewCharacterLevel + " Sorcery points.");
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

        if (iLevel >= 3) {
            powers.add(new Power("Metamagic", "You gain the ability to twist your spells to suit your needs." +
                    "\n" +
                    "You can use only one Metamagic option on a spell when you cast it, unless otherwise noted.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.addAll(_chosenFeatures);
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
        return R.drawable.ic_warlock;
    }

    @Override
    public String[] getClassSkills() {
        return new String[] {
                Enumerations.Skills.ARCANA.toString(),
                Enumerations.Skills.DECEPTION.toString(),
                Enumerations.Skills.INSIGHT.toString(),
                Enumerations.Skills.INTIMIDATION.toString(),
                Enumerations.Skills.PERSUASION.toString(),
                Enumerations.Skills.RELIGION.toString(),
        };
    }
}
