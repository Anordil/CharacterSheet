package com.guigeek.devilopers.dd5charactersheet.character.classes.paladin;

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
public class Paladin extends BaseClass {
    static final long serialVersionUID = 205L;

    @Override
    public String getClassName() {
        return App.getResString(R.string.class_paladin);
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.WIS,
                Enumerations.SavingThrows.CHA
        };
    }

    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.paladinArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.paladin_vengeance))) {
            return new Paladin_vengeance();
        } else if (iName.equals(App.getResString(R.string.paladin_ancients))) {
            return new Paladin_ancients();
        } else if (iName.equals(App.getResString(R.string.paladin_devotion))) {
            return new Paladin_devotion();
        } else if (iName.equals(App.getResString(R.string.paladin_oathbreaker))) {
            return new Paladin_oathbreaker();
        }
        return null;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int level) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

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

    int[][] _spellSlotsSubclass = {
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

    public Paladin(){
        _spellSlots = _spellSlotsSubclass;
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
    public int getAttacksPerRound(Character iCharacter, int level) {
        return (level >= 5 ? 2 : 1);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Paladin level " + iNewCharacterLevel + " benefits:");
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

    @Override
    public String[] getClassSkills() {
        return new String[] {
                Enumerations.Skills.ATHLETICS.toString(),
                Enumerations.Skills.INSIGHT.toString(),
                Enumerations.Skills.INTIMIDATION.toString(),
                Enumerations.Skills.MEDICINE.toString(),
                Enumerations.Skills.PERSUASION.toString(),
                Enumerations.Skills.RELIGION.toString(),
        };
    }
}
