package com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;


public class Barbarian_ancestral extends BaseArchetype {
    static final long serialVersionUID = 2101L;
    public Barbarian_ancestral(){}

    @Override
    public String getName() {
        return App.getResString(R.string.barbarian_ancestral);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Ancestral Protector");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Spirit shield");
            levelUp.add("Spirit Shield is now 2d6");
        }
        else if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Consult the Spirits");
            levelUp.add("Spirit Shield is now 3d6");
        }
        else if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Vengeful Ancestors");
            levelUp.add("Spirit Shield is now 4d6");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            powers.add(new Power("Ancestral Protector", "While you’re raging, the first creature you hit with an attack on your turn becomes the target of the warriors, which hinder its attacks. Until the start of your next turn, that target has disadvantage on any attack roll that isn’t against you, and when the target hits a creature other than you with an attack, that creature has resistance to the damage dealt by the attack.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Spirit Shield", "If you are raging and another creature you can see within 30 feet of you takes damage, you can use your reaction to reduce that damage by" + getSpiritShieldDie(iLevel), "30 ft", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Consult the Spirits", "You cast the augury or clairvoyance spell, without using a spell slot or material components. Rather than creating a spherical sensor, this use of clairvoyance invisibly summons one of your ancestral spirits to the chosen location. Wisdom is your spellcasting ability for these spells.", "", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Vengeful Ancestors", "When you use your Spirit Shield to reduce the damage of an attack, the attacker takes an amount of force damage equal to the damage that your Spirit Shield prevents.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    public String getSpiritShieldDie(int iLevel) {
        if (iLevel >= 14) return "4d6";
        if (iLevel >= 10) return "3d6";
        else return "2d6";
    }
}
