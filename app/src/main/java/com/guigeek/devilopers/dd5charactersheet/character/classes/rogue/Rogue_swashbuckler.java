package com.guigeek.devilopers.dd5charactersheet.character.classes.rogue;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;


public class Rogue_swashbuckler extends BaseArchetype {
    static final long serialVersionUID = 2078L;

    public Rogue_swashbuckler(){}


    @Override
    public String getName() {
        return App.getResString(R.string.rogue_swashbuckler);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Fancy Footwork !");
            levelUp.add("Gained Rakish Audacity !");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Panache !");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Elegant Manoeuver !");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Master Dueslist !");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Fancy Footwork", "If you make a melee attack against a creature, during your turn, that creature cannot make OA against you during the rest of your turn.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Rakish Audacity", "You add your CHA to your initiative rolls. You don't need advantage on your attack rolls to benefit from Sneak Attack if no other creature than your target is within 5ft of you.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Panache", "As an action, you can make a Charisma (Persuasion) check contested by a creature's Wisdom (Insight) check. The creature must be able to hear you, and the two of you must share a language. If you succeed on the check and the creature is hostile to you, it has disadvantage on attack rolls against targets other than you and can't make opportunity attacks against targets other than you. This effect lasts for 1 minute, until one of your companions attacks the target or affects it with a spell, or until you and the target are more than 60 feet apart. If you succeed on the check and the creature isn't hostile to you, it is charmed by you for 1 minute. While charmed, it regards you as a friendly acquaintance. This effect ends immediately if you or your companions do anything harmful to it.", "Self", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 13) {
            powers.add(new Power("Elegant Manoeuver", "You can use a bonus action on your turn to gain advantage on the next Dexterity (Acrobatics) or Strength (Athletics) check you make during the same turn.", "Self", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 17) {
            powers.add(new Power("If you miss with an attack roll, you can roll it again with advantage. Once you do so, you can't use this feature again until you finish a short or long rest.", "Desc", "Self", 1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
