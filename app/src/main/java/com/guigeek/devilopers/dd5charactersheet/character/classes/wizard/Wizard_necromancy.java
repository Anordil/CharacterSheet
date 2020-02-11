package com.guigeek.devilopers.dd5charactersheet.character.classes.wizard;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Wizard_necromancy extends BaseArchetype {
    static final long serialVersionUID = 2808L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_necromancy);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Grim Harvest");
            levelUp.add("Gained School Savant");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Undead Thralls");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Inured to Undeath");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Command Undead");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("School Savant", "The gold and time you spend to copy a spell into your spellbook is halved for spells of your chosen school.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Grim Harvest", "Once per turn when you kill one or more creatures with a spell of 1st level or higher, you regain hit points equal to twice the spell’s level, or three times its level if the spell belongs to the School of Necromancy. You don’t gain this benefit for killing constructs or undead.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Undead Thralls", "You add the animate dead spell to your spellbook if it is not there already. When you cast animate dead, you can target one additional corpse or pile of bones, creating another zombie or skeleton, as appropriate.\n" +
                    "\n" +
                    "Whenever you create an undead using a necromancy spell, it has additional benefits:\n" +
                    "\n" +
                    "The creature’s hit point maximum is increased by an amount equal to your wizard level.\n" +
                    "The creature adds your proficiency bonus to its weapon damage rolls.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Inured to Undeath", "You have resistance to necrotic damage, and your hit point maximum can’t be reduced. You have spent so much time dealing with undead and the forces that animate them that you have become inured to some of their worst effects.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Command Undead", "As an action, you can choose one undead that you can see within 60 feet of you. That creature must make a Charisma saving throw against your wizard spell save DC. If it succeeds, you can’t use this feature on it again. If it fails, it becomes friendly to you and obeys your commands until you use this feature again.\n" +
                    "\n" +
                    "Intelligent undead are harder to control in this way. If the target has an Intelligence of 8 or higher, it has advantage on the saving throw. If it fails the saving throw and has an Intelligence of 12 or higher, it can repeat the saving throw at the end of every hour until it succeeds and breaks free.", "60ft", -1, 8+iCharac.getProficiencyBonus()+iCharac.getModifier(Enumerations.Attributes.INT), true, Enumerations.ActionType.ACTION));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (classLevel >= 10) {
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.NECROTIC.ordinal()));
        }

        return fettles;
    }
}
