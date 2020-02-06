package com.guigeek.devilopers.dd5charactersheet.character.classes.cleric;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Cleric_knowledge extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2405L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_knowledge);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You learn two languages of your choice. You also become proficient in your choice of two of the following skills: Arcana, History, Nature, or Religion.\n" +
                    "\n" +
                    "Your proficiency bonus is doubled for any ability check you make that uses either of those skills.");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Potent Spellcasting");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Visions of the Past");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 8) {
            powers.add(new Power("Potent Spellcasting", "You add your Wisdom modifier to the damage you deal with any cleric cantrip.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Visions of the Past", "You can call up visions of the past that relate to an object you hold or your immediate surroundings. You spend at least 1 minute in meditation and prayer, then receive dreamlike, shadowy glimpses of recent events. You can meditate in this way for a number of minutes equal to your Wisdom score and must maintain concentration during that time, as if you were casting a spell.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a short or long rest." +
                    "\n[Object Reading] Holding an object as you meditate, you can see visions of the object’s previous owner. After meditating for 1 minute, you learn how the owner acquired and lost the object, as well as the most recent significant event involving the object and that owner. If the object was owned by another creature in the recent past (within a number of days equal to your Wisdom score), you can spend 1 additional minute for each owner to learn the same information about that creature.\n" +
                    "\n" +
                    "[Area Reading] As you meditate, you see visions of recent events in your immediate vicinity (a room, street, tunnel, clearing, or the like, up to a 50-foot cube), going back a number of days equal to your Wisdom score. For each minute you meditate, you learn about one significant event, beginning with the most recent. Significant events typically involve powerful emotions, such as battles and betrayals, marriages and murders, births and funerals. However, they might also include more mundane events that are nevertheless important in your current situation.", "", 1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        String desc = "";
        if (iLevel >= 2) {
            desc += "[Knowledge of the Ages] As an action, you choose one skill or tool. For 10 minutes, you have proficiency with the chosen skill or tool.";
        }
        if (iLevel >= 6) {
            desc += "\n\n[Read Thoughts] As an action, choose one creature that you can see within 60 feet of you. That creature must make a Wisdom saving throw. If the creature succeeds on the saving throw, you can’t use this feature on it again until you finish a long rest.\n" +
                    "\n" +
                    "If the creature fails its save, you can read its surface thoughts (those foremost in its mind, reflecting its current emotions and what it is actively thinking about) when it is within 60 feet of you. This effect lasts for 1 minute.\n" +
                    "\n" +
                    "During that time, you can use your action to end this effect and cast the suggestion spell on the creature without expending a spell slot. The target automatically fails its saving throw against the spell.";
        }
        return desc;
    }
}
