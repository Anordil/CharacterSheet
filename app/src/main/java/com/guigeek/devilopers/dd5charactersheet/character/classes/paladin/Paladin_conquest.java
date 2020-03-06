package com.guigeek.devilopers.dd5charactersheet.character.classes.paladin;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin_conquest extends BaseArchetype {
    static final long serialVersionUID = 227L;

    public Paladin_conquest(){}

    @Override
    public String getName() {
        return App.getResString(R.string.paladin_conquest);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Channel Divinity.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Aura of Conquest");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Scornful Rebuke");
        }
        else if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Invincible Conqueror");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "<b>Conquering Presence</b> You can use your Channel Divinity to exude a terrifying presence. As an action, you force each creature of your choice that you can see within 30 feet of you to make a Wisdom saving throw. On a failed save, a creature becomes frightened of you for 1 minute. The frightened creature can repeat this saving throw at the end of each of its turns, ending the effect on itself on a success.<br>" +
                    "<b>Guided Strike</b> You can use your Channel Divinity to strike with supernatural accuracy. When you make an attack roll, you can use your Channel Divinity to gain a +10 bonus to the roll. You make this choice after you see the roll, but before the DM says whether the attack hits or misses.", "30ft", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 7) {
            String range = iLevel >= 18 ? "30ft" : "10ft";
            powers.add(new Power("You constantly emanate a menacing aura while you’re not incapacitated. The aura extends " + range + " from you in every direction, but not through total cover.\n" +
                    "\n" +
                    "If a creature is frightened of you, its speed is reduced to 0 while in the aura, and that creature takes " + (iLevel/2) + " psychic damages if it starts its turn there.", "", range, -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Scornful Rebuke", "Whenever a creature hits you with an attack, that creature takes " + iCharac.getModifier(Enumerations.Attributes.CHA) + " psychic damages if you’re not incapacitated.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Invincible Conqueror", "As an action, you can magically become an avatar of conquest, gaining the following benefits for 1 minute:\n" +
                    "\n" +
                    "You have resistance to all damage.\n" +
                    "When you take the Attack action on your turn, you can make one additional attack as part of that action.\n" +
                    "Your melee weapon attacks score a critical hit on a roll of 19 or 20 on the d20.\n" +
                    "Once you use this feature, you can’t use it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
