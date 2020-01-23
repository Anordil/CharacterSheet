package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Monk_hand extends BaseArchetype {
    static final long serialVersionUID = 2705L;
    public Monk_hand(){}

    @Override
    public String getName() {
        return App.getResString(R.string.monk_hand);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Open Hand Technique");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Wholeness of Body");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Tranquility");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You gained Quivering Palm");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);
        int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.WIS);
        if (iLevel >= 3) {
            powers.add(new Power("Open Hand Technique", "Whenever you hit a creature with one of the attacks granted by your Flurry of Blows, you can impose one of the following effects on that target:\n" +
                    "\n" +
                    "It must succeed on a Dexterity saving throw or be knocked prone.\n" +
                    "It must make a Strength saving throw. If it fails, you can push it up to 15 feet away from you.\n" +
                    "It can’t take reactions until the end of your next turn.", "", -1, dc, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            int hp = 3*iLevel;
            powers.add(new Power("Wholeness of Body", "As an action, you can regain " + hp +" hit points.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Tranquility", "At the end of a long rest, you gain the effect of a sanctuary spell that lasts until the start of your next long rest (the spell can end early as normal).", "", -1, dc, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {

            powers.add(new Power("Quivering Palm", "You gain the ability to set up lethal vibrations in someone’s body. When you hit a creature with an unarmed strike, you can spend 3 ki points to start these imperceptible vibrations, which last for " + iLevel + " days. The vibrations are harmless unless you use your action to end them. To do so, you and the target must be on the same plane of existence. When you use this action, the creature must make a Constitution saving throw. If it fails, it is reduced to 0 hit points. If it succeeds, it takes 10d10 necrotic damage.\n\nYou can have only one creature under the effect of this feature at a time. You can choose to end the vibrations harmlessly without using an action.", "", -1, dc, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
