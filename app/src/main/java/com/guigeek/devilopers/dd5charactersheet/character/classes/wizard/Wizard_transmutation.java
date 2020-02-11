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

public class Wizard_transmutation extends BaseArchetype {
    static final long serialVersionUID = 2809L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_transmutation);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Minor Alchemy");
            levelUp.add("Gained School Savant");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Transmuter’s Stone");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Shapechanger");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Master Transmuter");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("School Savant", "The gold and time you spend to copy a spell into your spellbook is halved for spells of your chosen school.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Minor Alchemy", "You can temporarily alter the physical properties of one nonmagical object, changing it from one substance into another. You perform a special alchemical procedure on one object composed entirely of wood, stone (but not a gemstone), iron, copper, or silver, transforming it into a different one of those materials. For each 10 minutes you spend performing the procedure, you can transform up to 1 cubic foot of material. After 1 hour, or until you lose your concentration (as if you were concentrating on a spell), the material reverts to its original substance.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Transmuter’s Stone", "You can spend 8 hours creating a transmuter’s stone that stores transmutation magic. You can benefit from the stone yourself or give it to another creature. A creature gains a benefit of your choice as long as the stone is in the creature’s possession. When you create the stone, choose the benefit from the following options:\n" +
                    "\n" +
                    "Darkvision out to a range of 60 feet\n" +
                    "An increase to speed of 10 feet while the creature is unencumbered\n" +
                    "Proficiency in Constitution saving throws\n" +
                    "Resistance to acid, cold, fire, lightning, or thunder damage (your choice whenever you choose this benefit)\n" +
                    "Each time you cast a transmutation spell of 1st level or higher, you can change the effect of your stone if the stone is on your person.\n" +
                    "\n" +
                    "If you create a new transmuter’s stone, the previous one ceases to function.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Shapechanger", "You add the polymorph spell to your spellbook, if it is not there already. You can cast polymorph without expending a spell slot. When you do so, you can target only yourself and transform into a beast whose challenge rating is 1 or lower.\n" +
                    "\n" +
                    "Once you cast polymorph in this way, you can’t do so again until you finish a short or long rest, though you can still cast it normally using an available spell slot.", "", 1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Master Transmuter", "You can use your action to consume the reserve of transmutation magic stored within your transmuter’s stone in a single burst. When you do so, choose one of the following effects. Your transmuter’s stone is destroyed and can’t be remade until you finish a long rest.\n" +
                    "\n" +
                    "[Major Transformation] You can transmute one nonmagical object—no larger than a 5-foot cube—into another nonmagical object of similar size and mass and of equal or lesser value. You must spend 10 minutes handling the object to transform it.\n" +
                    "\n" +
                    "[Panacea] You remove all curses, diseases, and poisons affecting a creature that you touch with the transmuter’s stone. The creature also regains all its hit points.\n" +
                    "\n" +
                    "[Restore Life] You cast the raise dead spell on a creature you touch with the transmuter’s stone, without expending a spell slot or needing to have the spell in your spellbook.\n" +
                    "\n" +
                    "[Restore Youth] You touch the transmuter’s stone to a willing creature, and that creature’s apparent age is reduced by 3d10 years, to a minimum of 13 years. This effect doesn’t extend the creature’s lifespan.", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }


        return powers;
    }

}
