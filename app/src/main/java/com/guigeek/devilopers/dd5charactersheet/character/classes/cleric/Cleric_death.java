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

public class Cleric_death extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2402L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_death);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained proficency with Martial weapons");
            levelUp.add("Gained Reaper");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Inescapable Destruction");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Divine Strike");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Divine Strike now deals an extra 2d8");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Improved Reaper");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Reaper", "You learn one necromancy cantrip of your choice from any spell list. When the cleric casts a necromancy cantrip that normally targets only one creature, the spell can instead target two creatures within range and within 5 feet of each other.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Inescapable Destruction", "Necrotic damage dealt by the character’s cleric spells and Channel Divinity options ignores resistance to necrotic damage.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Divine Strike", "Once on each of the cleric’s turns when he or she hits a creature with a weapon attack, the cleric can cause the attack to deal an extra " + (iLevel >= 14 ? 2 : 1) + "d8 necrotic damage to the target.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Improved Reaper", "When the cleric casts a necromancy spell of 1st through 5th level that targets only one creature, the spell can instead target two creatures within range and within 5 feet of each other. If the spell consumes its material components, the cleric must provide them for each target.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        return iLevel >= 2 ? "[Touch of Death] When the cleric hits a creature with a melee attack, the cleric can use Channel Divinity to deal an extra " + (5 + 2*iLevel) + " necrotic damage to the target." : "";
    }
}
