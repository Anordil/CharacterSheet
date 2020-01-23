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


public class Monk_death extends BaseArchetype {
    static final long serialVersionUID = 2702L;
    public Monk_death(){}

    @Override
    public String getName() {
        return App.getResString(R.string.monk_death);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Touch of Death");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Hour of Reaping");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Mastery of Death");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You gained Touch of the Long Death");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);
        int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.WIS);
        if (iLevel >= 3) {
            int hp = Math.max(1, iLevel + iCharac.getModifier(Enumerations.Attributes.WIS));
            powers.add(new Power("Touch of Death", "When you reduce a creature within 5 feet of you to 0 hit points, you gain " + hp + " temporary hit points.", "5 ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Hour of Reaping", "When you take this action, each creature within 30 feet of you that can see you must succeed on a Wisdom saving throw or be frightened of you until the end of your next turn.", "30 ft", -1, dc, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Mastery of Death", "When you are reduced to 0 hit points, you can expend 1 ki point (no action required) to have 1 hit point instead.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Touch of the Long Death", "As an action, you touch one creature within 5 feet of you, and you expend 1 to 10 ki points. The target must make a Constitution saving throw, and it takes 2d10 necrotic damage per ki point spent on a failed save, or half as much damage on a successful one.", "5ft", -1, dc, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
