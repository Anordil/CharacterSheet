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


public class Bard_whispers extends BaseArchetype {
    static final long serialVersionUID = 2204L;

    public Bard_whispers(){}

    @Override
    public String getName() {
        return App.getResString(R.string.bard_whispers);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Psychic Blades");
            levelUp.add("Gained Words of Terror");
        }

        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Mantle of Whispers");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Shadow Lore");
        }


        if (iNewCharacterLevel == 5 || iNewCharacterLevel == 10 || iNewCharacterLevel == 15) {
            levelUp.add("Psychic blade damage increased.");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            int psychicDice = iLevel >= 15 ? 8 : iLevel >= 10 ? 5 : iLevel >= 5 ? 3 : 2;
            powers.add(new Power("Psychic Blades", "You gain the ability to make your weapon attacks magically toxic to a creature’s mind.\n" +
                    "\n" +
                    "When you hit a creature with a weapon attack, you can expend one use of your Bardic Inspiration to deal an extra " + psychicDice + "d6 psychic damage to that target. You can do so only once per round on your turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Words of Terror", "If you speak to a humanoid alone for at least 1 minute, you can attempt to seed paranoia in its mind. At the end of the conversation, the target must succeed on a Wisdom saving throw against your spell save DC or be frightened of you or another creature of your choice. The target is frightened in this way for 1 hour, until it is attacked or damaged, or until it witnesses its allies being attacked or damaged.\n" +
                    "\n" +
                    "If the target succeeds on its saving throw, the target has no hint that you tried to frighten it.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a short or long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Mantle of Whispers", "When a humanoid dies within 30 feet of you, you can magically capture its shadow using your reaction. You retain this shadow until you use it or you finish a long rest.\n" +
                    "\n" +
                    "You can use the shadow as an action. When you do so, it vanishes, magically transforming into a disguise that appears on you. You now look like the dead person, but healthy and alive. This disguise lasts for 1 hour or until you end it as a bonus action.\n" +
                    "\n" +
                    "While you’re in the disguise, you gain access to all information that the humanoid would freely share with a casual acquaintance. Such information includes general details on its background and personal life, but doesn’t include secrets. The information is enough that you can pass yourself off as the person by drawing on its memories.\n" +
                    "\n" +
                    "Another creature can see through this disguise by succeeding on a Wisdom (Insight) check contested by your Charisma (Deception) check. You gain a +5 bonus to your check.\n" +
                    "\n" +
                    "Once you capture a shadow with this feature, you can’t capture another one with it until you finish a short or long rest.", "", 1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Shadow Lore", "As an action, you magically whisper a phrase that only one creature of your choice within 30 feet of you can hear. The target must make a Wisdom saving throw against your spell save DC. It automatically succeeds if it doesn’t share a language with you or if it can’t hear you. On a successful saving throw, your whisper sounds like unintelligible mumbling and has no effect.\n" +
                    "\n" +
                    "On a failed saving throw, the target is charmed by you for the next 8 hours or until you or your allies attack it, damage it, or force it to make a saving throw. It interprets the whispers as a description of its most mortifying secret. You gain no knowledge of this secret, but the target is convinced you know it.\n" +
                    "\n" +
                    "The charmed creature obeys your commands for fear that you will reveal its secret. It won’t risk its life for you or fight for you, unless it was already inclined to do so. It grants you favors and gifts it would offer to a close friend.\n" +
                    "\n" +
                    "When the effect ends, the creature has no understanding of why it held you in such fear.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
