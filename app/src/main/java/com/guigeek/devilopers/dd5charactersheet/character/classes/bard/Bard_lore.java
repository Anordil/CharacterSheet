package com.guigeek.devilopers.dd5charactersheet.character.classes.bard;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Bard_lore extends BaseArchetype {
    static final long serialVersionUID = 2202L;

    public Bard_lore(){}

    @Override
    public String getName() {
        return App.getResString(R.string.bard_lore);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained proficjency in 3 new skills");
            levelUp.add("Gained Cutting Words");
        }

        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Additional Magical Secrets");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Peerless Skill");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Cutting Words", "When a creature that you can see within 60 feet of you makes an attack roll, an ability check, or a damage roll, you can use your reaction to expend one of your uses of Bardic Inspiration, rolling a Bardic Inspiration die and subtracting the number rolled from the creature’s roll. You can choose to use this feature after the creature makes its roll, but before the DM determines whether the attack roll or ability check succeeds or fails, or before the creature deals its damage. The creature is immune if it can’t hear you or if it’s immune to being charmed.", "60ft", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Additional Magical Secrets", "You learn two spells of your choice from any class. A spell you choose must be of a level you can cast, as shown on the Bard table, or a cantrip. The chosen spells count as bard spells for you but don’t count against the number of bard spells you know.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Peerless Skill", "When you make an ability check, you can expend one use of Bardic Inspiration. Roll a d"
                    + Bard.getInspirationDie(iLevel)
                    + "  and add the number rolled to your ability check. You can choose to do so after you roll the die for the ability check, but before the DM tells you whether you succeed or fail.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
