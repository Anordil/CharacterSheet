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

public class Cleric_light extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2407L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_light);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You know the Light cantrip");
            levelUp.add("Gained Warding Flare");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Improved Flare");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Potent Spellcasting");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Corona of Light");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power(iLevel >= 6 ? "Improved Warding Flare" : "Warding Flare",
                    "When you " + (iLevel >= 6 ? "or another creature " : "") + "are attacked by a creature within 30 feet of you that you can see, you can use your reaction to impose disadvantage on the attack roll, causing light to flare before the attacker before it hits or misses. An attacker that can’t be blinded is immune to this feature.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (a minimum of once). You regain all expended uses when you finish a long rest.", "30ft", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Potent Spellcasting", "You add your Wisdom modifier to the damage you deal with any cleric cantrip.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Corona of Light", "You can use your action to activate an aura of sunlight that lasts for 1 minute or until you dismiss it using another action. You emit bright light in a 60-foot radius and dim light 30 feet beyond that. Your enemies in the bright light have disadvantage on saving throws against any spell that deals fire or radiant damage.", "", -1, -1, false, Enumerations.ActionType.ACTION));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        return iLevel >= 2 ? "[Radiance of the Dawn] As an action, you present your holy symbol, and any magical darkness within 30 feet of you is dispelled. Additionally, each hostile creature within 30 feet of you must make a Constitution saving throw. A creature takes radiant damage equal to 2d10 + your cleric level on a failed saving throw, and half as much damage on a successful one. A creature that has total cover from you is not affected." : "";
    }
}
