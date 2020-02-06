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

public class Cleric_grave extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2404L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_grave);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained Circle of Mortality");
            levelUp.add("Gained Eyes of the Grave");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Sentinel at Death’s Door");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Potent Spellcasting");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Keeper of Souls");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Circle of Mortality", "When you would normally roll one or more dice to restore hit points with a spell to a creature at 0 hit points, you instead use the highest number possible for each die.\n" +
                    "\n" +
                    "In addition, you learn the spare the dying cantrip, which doesn’t count against the number of cleric cantrips you know. For you, it has a range of 30 feet, and you can cast it as a bonus action.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Eyes of the Grave", "You gain the ability to occasionally sense the presence of the undead, whose existence is an insult to the natural cycle of life. As an action, you can open your awareness to magically detect undead. Until the end of your next turn, you know the location of any undead within 60 feet of you that isn’t behind total cover and that isn’t protected from divination magic. This sense doesn’t tell you anything about a creature’s capabilities or identity.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (minimum of once). You regain all expended uses when you finish a long rest.", "", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Sentinel at Death’s Door", "As a reaction when you or a creature you can see within 30 feet of you suffers a critical hit, you can turn that hit into a normal hit. Any effects triggered by a critical hit are canceled.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (minimum of once). You regain all expended uses when you finish a long rest.", "30ft", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Potent Spellcasting", "You add your Wisdom modifier to the damage you deal with any cleric cantrip", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Keeper of Souls", "When an enemy you can see dies within 60 feet of you, you or one creature of your choice that is within 60 feet of you regains hit points equal to the enemy’s number of Hit Dice. You can use this feature only if you aren’t incapacitated. Once you use it, you can’t do so again until the start of your next turn.", "60ft", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        return  iLevel >= 2 ? "[Path to the Grave] As an action, you choose one creature you can see within 30 feet of you, cursing it until the end of your next turn. The next time you or an ally of yours hits the cursed creature with an attack, the creature has vulnerability to all of that attack’s damage, and then the curse ends." : "";
    }
}
