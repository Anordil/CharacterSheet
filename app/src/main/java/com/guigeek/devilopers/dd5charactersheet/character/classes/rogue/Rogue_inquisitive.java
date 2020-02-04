package com.guigeek.devilopers.dd5charactersheet.character.classes.rogue;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Rogue_inquisitive extends BaseArchetype {
    static final long serialVersionUID = 2073L;

    public Rogue_inquisitive(){}

    @Override
    public String getName() {
        return App.getResString(R.string.rogue_inquisitive);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Ear for Deceit");
            levelUp.add("Gained Eye for Detail");
            levelUp.add("Gained Insightful Fighting");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Steady Eye");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Unerring Eye");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Eye for Weakness");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Ear for Deceit", "Whenever you make a Wisdom (Insight) check to determine whether a creature is lying, treat a roll of 7 or lower on the d20 as an 8.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Eye for Detail", "You can use a bonus action to make a Wisdom (Perception) check to spot a hidden creature or object or to make an Intelligence (Investigation) check to uncover or decipher clues.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
            powers.add(new Power("Insightful Fighting", "You can make a Wisdom (Insight) check against a creature you can see that isn’t incapacitated, contested by the target’s Charisma (Deception) check. If you succeed, you can use your Sneak Attack against that target even if you don’t have advantage on the attack roll, but not if you have disadvantage on it.\n" +
                    "\n" +
                    "This benefit lasts for 1 minute or until you successfully use this feature against a different target.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Steady Eye", "You have advantage on any Wisdom (Perception) or Intelligence (Investigation) check if you move no more than half your speed on the same turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 13) {
            int uses = Math.max(1, iCharac.getModifier(Enumerations.Attributes.WIS));
            powers.add(new Power("Unerring Eye", "You sense the presence of illusions, shapechangers not in their original form, and other magic designed to deceive the senses within 30 feet of you, provided you aren’t blinded or deafened. You sense that an effect is attempting to trick you, but you gain no insight into what is hidden or into its true nature.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (minimum of once), and you regain all expended uses of it when you finish a long rest.", "", uses, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Eye for Weakness", "While your Insightful Fighting feature applies to a creature, your Sneak Attack damage against that creature increases by 3d6.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
