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

public class Wizard_war extends BaseArchetype {
    static final long serialVersionUID = 2810L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_war);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Arcane Deflection");
            levelUp.add("Gained Tactical Wit");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Power Surge");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Durable Magic");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Deflecting Shroud");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Arcane Deflection", "When you are hit by an attack or you fail a saving throw, you can use your reaction to gain a +2 bonus to your AC against that attack or a +4 bonus to that saving throw.\n" +
                    "\n" +
                    "When you use this feature, you can’t cast spells other than cantrips until the end of your next turn.", "", -1, -1, true, Enumerations.ActionType.REACTION));
            powers.add(new Power("Tactical Wit", "You can give yourself a bonus to your initiative rolls equal to your Intelligence modifier.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Deflecting Shroud", "When you use your Arcane Deflection feature, you can cause magical energy to arc from you. Up to three creatures of your choice that you can see within 60 feet of you each take force damage equal to half your wizard level.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Power Surge", "You can store magical energy within yourself to later empower your damaging spells. In its stored form, this energy is called a power surge.\n" +
                    "\n" +
                    "You can store a maximum number of power surges equal to your Intelligence modifier (minimum of one). Whenever you finish a long rest, your number of power surges resets to one. Whenever you successfully end a spell with dispel magic or counterspell, you gain one power surge, as you steal magic from the spell you foiled. If you end a short rest with no power surges, you gain one power surge.\n" +
                    "\n" +
                    "Once per turn when you deal damage to a creature or object with a wizard spell, you can spend one power surge to deal extra force damage to that target. The extra damage equals half your wizard level.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Durable Magic", "While you maintain concentration on a spell, you have a +2 bonus to AC and all saving throws.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
