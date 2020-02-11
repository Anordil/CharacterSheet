package com.guigeek.devilopers.dd5charactersheet.character.classes.ranger;

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

public class Ranger_slayer extends BaseArchetype {
    static final long serialVersionUID = 2805L;

    @Override
    public String getName() {
        return App.getResString(R.string.ranger_slayer);
    }



    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Hunter’s Sense");
            levelUp.add("Gained Slayer’s Prey");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Supernatural Defense");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Gained Magic-User’s Nemesis");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Slayer’s Counter");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.WIS);

        if (iLevel >= 3) {
            powers.add(new Power("Hunter’s Sense", "As an action, choose one creature you can see within 60 feet of you. You immediately learn whether the creature has any damage immunities, resistances, or vulnerabilities and what they are. If the creature is hidden from divination magic, you sense that it has no damage immunities, resistances, or vulnerabilities.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (minimum of once). You regain all expended uses of it when you finish a long rest.", "", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.ACTION));
            powers.add(new Power("Slayer’s Prey", "As a bonus action, you designate one creature you can see within 60 feet of you as the target of this feature. The first time each turn that you hit that target with a weapon attack, it takes an extra 1d6 damage from the weapon.\n" +
                    "\n" +
                    "This benefit lasts until you finish a short or long rest. It ends early if you designate a different creature.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Supernatural Defense", "Whenever the target of your Slayer’s Prey forces you to make a saving throw and whenever you make an ability check to escape that target’s grapple, add 1d6 to your roll.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Magic-User’s Nemesis", "When you see a creature casting a spell or teleporting within 60 feet of you, you can use your reaction to try to magically foil it. The creature must succeed on a Wisdom saving throw against your spell save DC, or its spell or teleport fails and is wasted.", "60ft", 1, dc, false, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Slayer’s Counter", "If the target of your Slayer’s Prey forces you to make a saving throw, you can use your reaction to make one weapon attack against the quarry. You make this attack immediately before making the saving throw. If your attack hits, your save automatically succeeds, in addition to the attack’s normal effects.", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }


        return powers;
    }
}
