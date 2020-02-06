package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

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


public class Warlock_patron_undying extends BaseArchetype {
    static final long serialVersionUID = 223L;

    public Warlock_patron_undying(){}


    @Override
    public String getName() {
        return App.getResString(R.string.warlock_patron_undying);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Among the Dead");
            levelUp.add("You know the Spare the dying cantrip");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Defy Death");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Undying Nature");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Indestructible Life");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();
        int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.CHA);

        if (iLevel >= 1) {
            powers.add(new Power("Among the Dead", "You have advantage on saving throws against any disease.\n" +
                    "\n" +
                    "Additionally, undead have difficulty harming you. If an undead targets you directly with an attack or a harmful spell, that creature must make a Wisdom saving throw against your spell save DC (an undead needn’t make the save when it includes you in an area effect, such as the explosion of fireball). On a failed save, the creature must choose a new target or forfeit targeting someone instead of you, potentially wasting the attack or spell. On a successful save, the creature is immune to this effect for 24 hours. An undead is also immune to this effect for 24 hours if you target it with an attack or a harmful spell.", "", -1, dc, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Defy Death", "You can regain hit points equal to 1d8 + your Constitution modifier (minimum of 1 hit point) when you succeed on a death saving throw or when you stabilize a creature with spare the dying.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 10) {
            powers.add(new Power("Undying Nature", "You can hold your breath indefinitely, and you don’t require food, water, or sleep, although you still require rest to reduce exhaustion and still benefit from finishing short and long rests.\n" +
                    "\n" +
                    "In addition, you age at a slower rate. For every 10 years that pass, your body ages only 1 year, and you are immune to being magically aged.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Indestructible Life", "On your turn, you can use a bonus action to regain hit points equal to 1d8 + your warlock level. Additionally, if you put a severed body part of yours back in place when you use this feature, the part reattaches.", "", 1, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }

        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> f = new LinkedList<>();

        if (character._level >= 1) {
            f.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.DISEASE.ordinal()));
        }

        return f;
    }
}
