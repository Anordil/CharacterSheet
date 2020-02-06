package com.guigeek.devilopers.dd5charactersheet.character.classes.druid;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.ClericDomain;

import java.util.LinkedList;
import java.util.List;

public class Druid_dreams extends BaseArchetype {
    static final long serialVersionUID = 2501L;

    @Override
    public String getName() {
        return App.getResString(R.string.druid_dreams);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Balm of the Summer Court");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Hearth of Moonlight and Shadow");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Hidden Paths");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Walker in Dreams");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Balm of the Summer Court", "You have a pool of fey energy represented by a number of d6s equal to your druid level.\n" +
                    "\n" +
                    "As a bonus action, you can choose one creature you can see within 120 feet of you and spend a number of those dice equal to half your druid level or less. Roll the spent dice and add them together. The target regains a number of hit points equal to the total. The target also gains 1 temporary hit point per die spent.\n" +
                    "\n" +
                    "You regain all expended dice when you finish a long rest.", "", iLevel, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Hearth of Moonlight and Shadow", "During a short or long rest, you can invoke the shadowy power of the Gloaming Court to help guard your respite. At the start of the rest, you touch a point in space, and an invisible, 30-foot-radius sphere of magic appears, centered on that point. Total cover blocks the sphere.\n" +
                    "\n" +
                    "While within the sphere, you and your allies gain a +5 bonus to Dexterity (Stealth) and Wisdom (Perception) checks, and any light from open flames in the sphere (a campfire, torches, or the like) isn’t visible outside it.\n" +
                    "\n" +
                    "The sphere vanishes at the end of the rest or when you leave the sphere.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Hidden Paths", "As a bonus action on your turn, you can teleport up to 60 feet to an unoccupied space you can see. Alternatively, you can use your action to teleport one willing creature you touch up to 30 feet to an unoccupied space you can see.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (minimum of once), and you regain all expended uses of it when you finish a long rest.", "60ft", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Walker in Dreams", "When you finish a short rest, you can cast one of the following spells, without expending a spell slot or requiring material components: dream (with you as the messenger), scrying , or teleportation circle.\n" +
                    "\n" +
                    "This use of teleportation circle  is special. Rather than opening a portal to a permanent teleportation circle, it opens a portal to the last location where you finished a long rest on your current plane of existence. If you haven’t taken a long rest on your current plane, the spell fails but isn’t wasted.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
