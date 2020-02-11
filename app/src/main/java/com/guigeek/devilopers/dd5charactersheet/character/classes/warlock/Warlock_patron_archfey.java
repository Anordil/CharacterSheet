package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

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


public class Warlock_patron_archfey extends BaseArchetype {
    static final long serialVersionUID = 220L;

    public Warlock_patron_archfey(){}


    @Override
    public String getName() {
        return App.getResString(R.string.warlock_patron_archfey);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Fey Presence");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Misty Escape");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Beguiling Defenses");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Dark Delirium");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.CHA);

        if (iLevel >= 1) {
            powers.add(new Power("Fey Presence", "As an action, you can cause each creature in a 10-foot cube originating from you to make a Wisdom saving throw against your warlock spell save DC. The creatures that fail their saving throws are all charmed or frightened by you (your choice) until the end of your next turn.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a short or long rest.", "", 1, -1, false, Enumerations.ActionType.ACTION));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Misty Escape", "When you take damage, you can use your reaction to turn invisible and teleport up to 60 feet to an unoccupied space you can see. You remain invisible until the start of your next turn or until you attack or cast a spell.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a short or long rest.", "", 1, -1, false, Enumerations.ActionType.REACTION));
        }

        if (iLevel >= 10) {
            powers.add(new Power("Beguiling Defenses", "You are immune to being charmed, and when another creature attempts to charm you, you can use your reaction to attempt to turn the charm back on that creature. The creature must succeed on a Wisdom saving throw against your warlock spell save DC or be charmed by you for 1 minute or until the creature takes any damage.", "", -1, dc, true, Enumerations.ActionType.REACTION));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Dark Delirium", "As an action, choose a creature that you can see within 60 feet of you. It must make a Wisdom saving throw against your warlock spell save DC. On a failed save, it is charmed or frightened by you (your choice) for 1 minute or until your concentration is broken (as if you are concentrating on a spell). This effect ends early if the creature takes any damage.\n" +
                    "\n" +
                    "Until this illusion ends, the creature thinks it is lost in a misty realm, the appearance of which you choose. The creature can see and hear only itself, you, and the illusion.", "60ft", 1, dc, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> f = new LinkedList<>();

        if (classLevel >= 10) {
            f.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.CHARM_MAGIC.ordinal()));
        }

        return f;
    }
}
