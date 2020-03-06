package com.guigeek.devilopers.dd5charactersheet.character.classes.paladin;

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

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin_crown extends BaseArchetype {
    static final long serialVersionUID = 229L;

    public Paladin_crown(){}

    @Override
    public String getName() {
        return App.getResString(R.string.paladin_crown);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Channel Divinity.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Divine Allegiance");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Unyielding Spirit");
        }
        else if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Exalted Champion");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "<b>Champion Challenge</b> As a bonus action, you issue a challenge that compels other creatures to do battle with you. Each creature of your choice that you can see within 30 feet of you must make a Wisdom saving throw. On a failed save, a creature can’t willingly move more than 30 feet away from you. This effect ends on the creature if you are incapacitated or die or if the creature is more than 30 feet away from you.\n" +
                    "\n" +
                    "<b>Turn the Tide</b> As a bonus action, you can bolster injured creatures with your Channel Divinity. Each creature of your choice that can hear you within 30 feet of you regains hit points equal to 1d6 + your Charisma modifier (minimum of 1) if it has no more than half of its hit points.", "", 1, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Divine Allegiance", "When a creature within 5 feet of you takes damage, you can use your reaction to magically substitute your own health for that of the target creature, causing that creature not to take the damage. Instead, you take the damage. This damage to you can’t be reduced or prevented in any way.", "5ft", -1, -1, false, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Unyielding Spirit", "You have advantage on saving throws to avoid becoming paralyzed or stunned.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Exalted Champion", "Your presence on the field of battle is an inspiration to those dedicated to your cause. You can use your action to gain the following benefits for 1 hour:\n" +
                    "\n" +
                    "You have resistance to bludgeoning, piercing, and slashing damage from nonmagical weapons.\n" +
                    "Your allies have advantage on death saving throws while within 30 feet of you.\n" +
                    "You have advantage on Wisdom saving throws, as do your allies within 30 feet of you.\n" +
                    "This effect ends early if you are incapacitated or die. Once you use this feature, you can’t use it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (classLevel >= 15) {
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.PARALYZED.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.STUNNED.ordinal()));
        }
        return fettles;
    }
}
