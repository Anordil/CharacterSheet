package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Warlock_patron_hexblade extends BaseArchetype {
    static final long serialVersionUID = 222L;

    public Warlock_patron_hexblade(){}


    @Override
    public String getName() {
        return App.getResString(R.string.warlock_patron_hexblade);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Hexblade’s Curse");
            levelUp.add("You gain proficiency with medium armor, shields, and martial weapons.");
            levelUp.add("You gained Hex Warrior");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Accursed Specter");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Armor of Hexes");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Master of Hexes");
        }

        return levelUp;
    }

    @Override
    public List<Enumerations.Proficiencies> getArmorProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.ARMOR_MEDIUM);
        proficiencies.add(Enumerations.Proficiencies.SHIELD);

        return proficiencies;
    }

    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.WEAPON_MARTIAL);

        return proficiencies;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Hexblade’s Curse", "As a bonus action, choose one creature you can see within 30 feet of you. The target is cursed for 1 minute. The curse ends early if the target dies, you die, or you are incapacitated. Until the curse ends, you gain the following benefits:\n" +
                    "\n" +
                    "You gain a bonus to damage rolls against the cursed target. The bonus equals your proficiency bonus.\n" +
                    "Any attack roll you make against the cursed target is a critical hit on a roll of 19 or 20 on the d20.\n" +
                    "If the cursed target dies, you regain hit points equal to your warlock level + your Charisma modifier (minimum of 1 hit point).", "30ft", 1, -1, false, Enumerations.ActionType.BONUS_ACTION));
            powers.add(new Power("Hex Warrior", "Whenever you finish a long rest, you can touch one weapon that you are proficient with and that lacks the two-handed property. When you attack with that weapon, you can use your Charisma modifier, instead of Strength or Dexterity, for the attack and damage rolls. This benefit lasts until you finish a long rest. If you later gain the Pact of the Blade feature, this benefit extends to every pact weapon you conjure with that feature, no matter the weapon’s type.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Accursed Specter", "When you slay a humanoid, you can cause its spirit to rise from its corpse as a specter, the statistics for which are in the Monster Manual. When the specter appears, it gains temporary hit points equal to half your warlock level. Roll initiative for the specter, which has its own turns. It obeys your verbal commands, and it gains a special bonus to its attack rolls equal to your Charisma modifier (minimum of +0).\n" +
                    "\n" +
                    "The specter remains in your service until the end of your next long rest, at which point it vanishes to the afterlife.\n" +
                    "\n" +
                    "Once you bind a specter with this feature, you can’t use the feature again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 10) {
            powers.add(new Power("Armor of Hexes", "If the target cursed by your Hexblade’s Curse hits you with an attack roll, you can use your reaction to roll a d6. On a 4 or higher, the attack instead misses you, regardless of its roll.", "", -1, -1, true, Enumerations.ActionType.REACTION));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Master of Hexes", "When the creature cursed by your Hexblade’s Curse dies, you can apply the curse to a different creature you can see within 30 feet of you, provided you aren’t incapacitated. When you apply the curse in this way, you don’t regain hit points from the death of the previously cursed creature.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
