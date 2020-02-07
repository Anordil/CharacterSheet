package com.guigeek.devilopers.dd5charactersheet.character.classes.wizard;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Wizard_divination extends BaseArchetype {
    static final long serialVersionUID = 2804L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_divination);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Portent");
            levelUp.add("Gained School Savant");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Expert Divination");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained The Third Eye");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Greater Portent");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("School Savant", "The gold and time you spend to copy a spell into your spellbook is halved for spells of your chosen school.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            String name = iLevel >= 14 ? "Greater Portent" : "Portent";
            int dice = iLevel >= 14 ? 3 : 2;
            powers.add(new Power(name, "When you finish a long rest, roll " + dice + " d20s and record the numbers rolled. You can replace any attack roll, saving throw, or ability check made by you or a creature that you can see with one of these foretelling rolls. You must choose to do so before the roll, and you can replace a roll in this way only once per turn.\n" +
                    "\n" +
                    "Each foretelling roll can be used only once. When you finish a long rest, you lose any unused foretelling rolls.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Expert Divination", "When you cast a divination spell of 2nd level or higher using a spell slot, you regain one expended spell slot. The slot you regain must be of a level lower than the spell you cast and can’t be higher than 5th level.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("The Third Eye", "You can use your action to increase your powers of perception. When you do so, choose one of the following benefits, which lasts until you are incapacitated or you take a short or long rest. You can’t use the feature again until you finish a rest.\n" +
                    "\n" +
                    "[Darkvision] You gain darkvision out to a range of 60 feet, as described in chapter 8, “Adventuring.”\n" +
                    "\n" +
                    "[Ethereal Sight] You can see into the Ethereal Plane within 60 feet of you.\n" +
                    "\n" +
                    "[Greater Comprehension] You can read any language.\n" +
                    "\n" +
                    "[See Invisibility] You can see invisible creatures and objects within 10 feet of you that are within line of sight.", "", 1, -1, false, Enumerations.ActionType.ACTION));
        }


        return powers;
    }
}
