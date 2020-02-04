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


public class Bard_glamour extends BaseArchetype {
    static final long serialVersionUID = 2201L;

    public Bard_glamour(){}

    @Override
    public String getName() {
        return App.getResString(R.string.bard_glamour);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Mantle of Inspiration");
            levelUp.add("Gained Enthralling Performance");
        }

        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Mantle of Majesty");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Unbreakable Majesty");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            int hp = iLevel >= 15 ? 14 : iLevel >= 10 ? 11 : iLevel >= 5 ? 8 :5;
            powers.add(new Power("Mantle of Inspiration", "As a bonus action, you can expend one use of your Bardic Inspiration to grant yourself a wondrous appearance. When you do so, choose a number of creatures you can see and that can see you within 60 feet of you, up to a number equal to your Charisma modifier (minimum of one). Each of them gains " + hp + " temporary hit points. When a creature gains these temporary hit points, it can immediately use its reaction to move up to its speed, without provoking opportunity attacks.", "60ft", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
            powers.add(new Power("Enthralling Performance", "If you perform for at least 1 minute, you can attempt to inspire wonder in your audience by singing, reciting a poem, or dancing. At the end of the performance, choose a number of humanoids within 60 feet of you who watched and listened to all of it, up to a number equal to your Charisma modifier (minimum of one). Each target must succeed on a Wisdom saving throw against your spell save DC or be charmed by you. While charmed in this way, the target idolizes you, it speaks glowingly of you to anyone who talks to it, and it hinders anyone who opposes you, although it avoids violence unless it was already inclined to fight on your behalf. This effect ends on a target after 1 hour, if it takes any damage, if you attack it, or if it witnesses you attacking or damaging any of its allies.\n" +
                    "\n" +
                    "If a target succeeds on its saving throw, the target has no hint that you tried to charm it.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a short or long rest.", "", 1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Mantle of Majesty", "As a bonus action, you cast command, without expending a spell slot, and you take on an appearance of unearthly beauty for 1 minute or until your concentration ends (as if you were concentrating on a spell). During this time, you can cast command as a bonus action on each of your turns, without expending a spell slot.\n" +
                    "\n" +
                    "Any creature charmed by you automatically fails its saving throw against the command you cast with this feature.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Unbreakable Majesty", "Rour appearance permanently gains an otherworldly aspect that makes you look more lovely and fierce.\n" +
                    "\n" +
                    "In addition, as a bonus action, you can assume a magically majestic presence for 1 minute or until you are incapacitated. For the duration, whenever any creature tries to attack you for the first time on a turn, the attacker must make a Charisma saving throw against your spell save DC. On a failed save, it can’t attack you on this turn, and it must choose a new target for its attack or the attack is wasted. On a successful save, it can attack you on this turn, but it has disadvantage on any saving throw it makes against your spells on your next turn.\n" +
                    "\n" +
                    "Once you assume this majestic presence, you can’t do so again until you finish a short or long rest.", "", 1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }

        return powers;
    }
}
