package com.guigeek.devilopers.dd5charactersheet.character.classes.cleric;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Cleric_arcana extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2401L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_arcana);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained proficiency in Arcana.");
            levelUp.add("You learn two Wizard cantrips.");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Spell Breaker");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Potent Spellcasting");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Arcane Mastery");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 6) {
            powers.add(new Power("Spell Breaker", "When you restore hit points to an ally with a spell of 1st level or higher, you can also end one spell of your choice on that creature. The level of the spell you end must be equal to or lower than the level of the spell slot you use to cast the healing spell.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Potent Spellcasting", "You add your Wisdom modifier to the damage you deal with any cleric cantrip.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Arcane Mastery", "You choose four spells from the wizard spell list, one from each of the following levels: 6th, 7th, 8th, and 9th. You add them to your list of domain spells. Like your other domain spells, they are always prepared and count as cleric spells for you.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        return iLevel >= 5 ? "[Improved Arcane Abjuration] As an action, you present your holy symbol, and one celestial, elemental, fey, or fiend of your choice that is within 30 feet of you must make a Wisdom saving throw, provided that the creature can see or hear you. If the creature fails its saving throw, it is turned for 1 minute or until it takes any damage.\n" +
                "\n" +
                "A turned creature must spend its turns trying to move as far away from you as it can, and it can’t willingly end its move in a space within 30 feet of you. It also can’t take reactions. For its action, it can only use the Dash action or try to escape from an effect that prevents it from moving. If there’s nowhere to move, the creature can use the Dodge action.\n" +
                "\n" +
                "After you reach 5th level, when a creature fails its saving throw against your Arcane Abjuration feature, the creature is banished for 1 minute (as in the banishment spell, no concentration required) if it isn’t on its plane of origin and its challenge rating is at or below a certain threshold, as shown on the Arcane Banishment table." :
                iLevel >= 2 ? "[Arcane Abjuration] As an action, you present your holy symbol, and one celestial, elemental, fey, or fiend of your choice that is within 30 feet of you must make a Wisdom saving throw, provided that the creature can see or hear you. If the creature fails its saving throw, it is turned for 1 minute or until it takes any damage.\n" +
                        "\n" +
                        "A turned creature must spend its turns trying to move as far away from you as it can, and it can’t willingly end its move in a space within 30 feet of you. It also can’t take reactions. For its action, it can only use the Dash action or try to escape from an effect that prevents it from moving. If there’s nowhere to move, the creature can use the Dodge action." :
                        "";
    }
}
