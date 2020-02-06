package com.guigeek.devilopers.dd5charactersheet.character.classes.druid;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Druid_shepherd extends BaseArchetype {
    static final long serialVersionUID = 2505L;

    @Override
    public String getName() {
        return App.getResString(R.string.druid_shepherd);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Speech of the Woods");
            levelUp.add("Gained Spirit Totem");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Mighty Summoner");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Guardian Spirit");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Faithful Summons");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Speech of the Woods", "You learn to speak, read, and write Sylvan. In addition, beasts can understand your speech, and you gain the ability to decipher their noises and motions. Most beasts lack the intelligence to convey or understand sophisticated concepts, but a friendly beast could relay what it has seen or heard in the recent past. This ability doesn’t grant you friendship with beasts, though you can combine this ability with gifts to curry favor with them as you would with any nonplayer character.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Spirit Totem", "As a bonus action, you can magically summon an incorporeal spirit to a point you can see within 60 feet of you. The spirit creates an aura in a 30-foot radius around that point. It counts as neither a creature nor an object, though it has the spectral appearance of the creature it represents.\n" +
                    "\n" +
                    "As a bonus action, you can move the spirit up to 60 feet to a point you can see.\n" +
                    "\n" +
                    "The spirit persists for 1 minute or until you’re incapacitated. Once you use this feature, you can’t use it again until you finish a short or long rest.\n" +
                    "\n" +
                    "The effect of the spirit’s aura depends on the type of spirit you summon from the options below.\n" +
                    "\n" +
                    "[Bear Spirit] The bear spirit grants you and your allies its might and endurance. Each creature of your choice in the aura when the spirit appears gains temporary hit points equal to 5 + your druid level. In addition, you and your allies gain advantage on Strength checks and Strength saving throws while in the aura.\n" +
                    "\n" +
                    "[Hawk Spirit] The hawk spirit is a consummate hunter, aiding you and your allies with its keen sight. When a creature makes an attack roll against a target in the spirit’s aura, you can use your reaction to grant advantage to that attack roll. In addition, you and your allies have advantage on Wisdom (Perception) checks while in the aura.\n" +
                    "\n" +
                    "[Unicorn Spirit] The unicorn spirit lends its protection to those nearby. You and your allies gain advantage on all ability checks made to detect creatures in the spirit’s aura. In addition, if you cast a spell using a spell slot that restores hit points to any creature inside or outside the aura, each creature of your choice in the aura also regains hit points equal to your druid level.", "60ft", 1, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Mighty Summoner", "Any beast or fey summoned or created by a spell that you cast gains the following benefits:\n" +
                    "\n" +
                    "The creature appears with more hit points than normal: 2 extra hit points per Hit Die it has.\n" +
                    "The damage from its natural weapons is considered magical for the purpose of overcoming immunity and resistance to nonmagical attacks and damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Guardian Spirit", "When a beast or fey that you summoned or created with a spell ends its turn in your Spirit Totem aura, that creature regains a number of hit points equal to half your druid level.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Faithful Summons", "If you are reduced to 0 hit points or are incapacitated against your will, you can immediately gain the benefits of conjure animals as if it were cast using a 9th-level spell slot. It summons four beasts of your choice that are challenge rating 2 or lower. The conjured beasts appear within 20 feet of you. If they receive no commands from you, they protect you from harm and attack your foes. The spell lasts for 1 hour, requiring no concentration, or until you dismiss it (no action required).", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
