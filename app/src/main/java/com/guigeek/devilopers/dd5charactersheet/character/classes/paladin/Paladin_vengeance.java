package com.guigeek.devilopers.dd5charactersheet.character.classes.paladin;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin_vengeance extends BaseArchetype {
    static final long serialVersionUID = 206L;

    public Paladin_vengeance(){}

    @Override
    public String getName() {
        return App.getResString(R.string.paladin_vengeance);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Channel Divinity.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Relentless Avenger");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Soul of Vengeance");
        }
        else if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Avenging Angel");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "[Abjure Enemy] As an action, you present your holy symbol and speak a prayer of denunciation, using your Channel Divinity. Choose one creature within 60 feet of you that you can see. That creature must make a Wisdom saving throw, unless it is immune to being frightened. Fiends and undead have disadvantage on this saving throw.\n" +
                    "\n" +
                    "On a failed save, the creature is frightened for 1 minute or until it takes any damage. While frightened, the creature’s speed is 0, and it can’t benefit from any bonus to its speed.\n" +
                    "\n" +
                    "On a successful save, the creature’s speed is halved for 1 minute or until the creature takes any damage.\n" +
                    "\n" +
                    "[Vow of Enmity] As a bonus action, you can utter a vow of enmity against a creature you can see within 10 feet of you, using your Channel Divinity. You gain advantage on attack rolls against the creature for 1 minute or until it drops to 0 hit points or falls unconscious.", "60ft/10ft", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Relentless avenger", "Opportunity attack enables to move at half speed during reaction. Doesn't trigger OA.", "Melee", -1, -1, false, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Soul of Vengeance", "Creature targeted by Vow on Enmity triggers OA for me when attacking", "Melee", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Avenging Angel", "Using your action, you undergo a transformation. For 1 hour, you gain the following benefits:\n" +
                    "\n" +
                    "- Wings sprout from your back and grant you a flying speed of 60 feet.\n" +
                    "- You emanate an aura of menace in a 30-foot radius. The first time any enemy creature enters the aura or starts its turn there during a battle, the creature must succeed on a Wisdom saving throw or become frightened of you for 1 minute or until it takes any damage. Attack rolls against the frightened creature have advantage.\n" +
                    "Once you use this feature, you can’t use it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
