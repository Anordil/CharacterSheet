package com.guigeek.devilopers.dd5charactersheet.character.classes.sorcerer;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Sorcerer_divine extends BaseArchetype {
    static final long serialVersionUID = 214L;

    public Sorcerer_divine(){}

    @Override
    public String getName() {
        return App.getResString(R.string.sorcerer_divine);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained Divine Magic");
            levelUp.add("Gained Favored by the Gods");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Empowered Healing");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Otherworldly Wings");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Unearthly Recovery");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();


        if (iLevel >= 1) {
            powers.add(new Power("Divine Magic", "Your link to the divine allows you to learn spells from the cleric class. When your Spellcasting feature lets you learn or replace a sorcerer cantrip or a sorcerer spell of 1st level or higher, you can choose the new spell from the cleric spell list or the sorcerer spell list. You must otherwise obey all the restrictions for selecting the spell, and it becomes a sorcerer spell for you.\n" +
                    "\n" +
                    "In addition, choose an affinity for the source of your divine power: good, evil, law, chaos, or neutrality. You learn an additional spell based on that affinity, as shown below. It is a sorcerer spell for you, but it doesn’t count against your number of sorcerer spells known. If you later replace this spell, you must replace it with a spell from the cleric spell list.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Favored by the Gods", "If you fail a saving throw or miss with an attack roll, you can roll 2d4 and add it to the total, possibly changing the outcome. Once you use this feature, you can’t use it again until you finish a short or long rest.", "", 1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Empowered Healing", "Whenever you or an ally within 5 feet of you rolls dice to determine the number of hit points a spell restores, you can spend 1 sorcery point to reroll any number of those dice once, provided you aren’t incapacitated. You can use this feature only once per turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 14) {
            powers.add(new Power("Otherworldly Wings", "You can use a bonus action to manifest a pair of spectral wings from your back. While the wings are present, you have a flying speed of 30 feet. The wings last until you’re incapacitated, you die, or you dismiss them as a bonus action.\n" +
                    "\n" +
                    "The affinity you chose for your Divine Magic feature determines the appearance of the spectral wings: eagle wings for good or law, bat wings for evil or chaos, and dragonfly wings for neutrality.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
    	}

        if (iLevel >= 18) {
            powers.add(new Power("Unearthly Recovery", "As a bonus action when you have fewer than half of your hit points remaining, you can regain a number of hit points equal to half your hit point maximum.", "", 1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }

        return powers;
    }
}
