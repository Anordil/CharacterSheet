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
import java.util.List;


public class Rogue_assassin extends Rogue_base{
    static final long serialVersionUID = 203L;

    public Rogue_assassin(){}

    @Override
    public String getName() {
        return App.getResString(R.string.class_rogue_assassin);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel);

        if (iNewCharacterLevel == 3) {
            levelUp.add("You are now proficient with the Disguise and Poison kits.");
            levelUp.add("Gained Assassinate !");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Infiltration Expertise !");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Impostor !");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Death strike !");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            powers.add(new Power("Assassinate", "You have advantage on attack rolls against any creature that hasn't taken a turn in combat yet. In addition, any hit against a surprised creature is critical.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Infiltration Expertise", "You can unfailingly create false identities for yourself.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 13) {
            powers.add(new Power("Impostor", "You can mimic a person's speech, writing and behavior.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Death Strike", "When you attack and hit a surprised creature, it must make a CON saving throw of DC " + (8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.DEX)) + ". On a failed save, it takes double damage.", "Melee", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
