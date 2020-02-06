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

public class Cleric_order extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2409L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_order);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained proficiency in Heavy armor.");
            levelUp.add("Gained proficiency in Intimidation or Persuasion.");
            levelUp.add("Gained Voice of Authority");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Embodiment of the Law");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Divine Strike");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Order's Wrath");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Voice of Authority", "If you cast a spell with a spell slot of 1st level or higher and target an ally with the spell, that ally can use their reaction immediately after the spell to make one weapon attack against a creature of your choice that you can see.\n" +
                    "\n" +
                    "If the spell targets more than one ally, you choose the ally who can make the attack.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Embodiment of the Law", "If you cast a spell of the enchantment school using a spell slot of 1st level or higher, you can change the spell’s casting time to 1 bonus action for this casting, provided the spell’s casting time is normally 1 action.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (minimum of once), and you regain all expended uses of it when you finish a long rest.", "", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Divine Strike", "Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra " + (iLevel >= 14 ? 2 : 1) + "d8 psychic damage to the target. ", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Order's Wrath", "If you deal your Divine Strike damage to a creature on your turn, you can curse that creature until the start of your next turn. The next time one of your allies hits the cursed creature with an attack, the target also takes 2d8 psychic damage, and the curse ends. You can curse a creature in this way only once per turn.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        return iLevel >= 2 ? "[Order’s Demand] As an action, you present your holy symbol, and each creature of your choice that can see or hear you within 30 feet of you must succeed on a Wisdom saving throw or be charmed by you until the end of your next turn or until the charmed creature takes any damage. You can also cause any of the charmed creatures to drop what they are holding when they fail the saving throw." : "";
    }
}
