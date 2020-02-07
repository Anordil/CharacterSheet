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

public class Wizard_conjuration extends BaseArchetype {
    static final long serialVersionUID = 2803L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_conjuration);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Minor Conjuration");
            levelUp.add("Gained School Savant");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Benign Transposition");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Focused Conjuration");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Durable Summons");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("School Savant", "The gold and time you spend to copy a spell into your spellbook is halved for spells of your chosen school.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Minor Conjuration", "You can use your action to conjure up an inanimate object in your hand or on the ground in an unoccupied space that you can see within 10 feet of you. This object can be no larger than 3 feet on a side and weigh no more than 10 pounds, and its form must be that of a nonmagical object that you have seen. The object is visibly magical, radiating dim light out to 5 feet.\n" +
                    "\n" +
                    "The object disappears after 1 hour, when you use this feature again, or if it takes or deals any damage.", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Benign Transposition", "You can use your action to teleport up to 30 feet to an unoccupied space that you can see. Alternatively, you can choose a space within range that is occupied by a Small or Medium creature. If that creature is willing, you both teleport, swapping places.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a long rest or you cast a conjuration spell of 1st level or higher.", "30ft", 1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Focused Conjuration", "While you are concentrating on a conjuration spell, your concentration can’t be broken as a result of taking damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Durable Summons", "Any creature that you summon or create with a conjuration spell has 30 temporary hit points.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
