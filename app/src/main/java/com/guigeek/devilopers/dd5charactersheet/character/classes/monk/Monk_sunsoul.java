package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.content.Context;
import android.net.wifi.aware.WifiAwareSession;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Monk_sunsoul extends BaseArchetype {
    static final long serialVersionUID = 2709L;
    public Monk_sunsoul(){}

    @Override
    public String getName() {
        return App.getResString(R.string.monk_sunsoul);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Radiant Sun Bolt");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Searing Arc Strike");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Searing Sunburst");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You gained Sun Shield");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);
        int dc = 8 + iCharac.getModifier(Enumerations.Attributes.WIS) + iCharac.getProficiencyBonus();
        if (iLevel >= 3) {
            powers.add(new Power("Radiant Sun Bolt", "You gain a new attack option that you can use with the Attack action. This special attack is a ranged spell attack with a range of 30 feet. You are proficient with it, and you add your Dexterity modifier to its attack and damage rolls. Its damage is radiant, and its damage die is a d" + ((Monk)iCharac._class).getMonkDamageDie(iLevel) + ".\n" +
                    "\n" +
                    "When you take the Attack action on your turn and use this special attack as part of it, you can spend 1 ki point to make the special attack twice as a bonus action.\n" +
                    "\n" +
                    "When you gain the Extra Attack feature, this special attack can be used for any of the attacks you make as part of the Attack action.", "30ft", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Searing Arc Strike", "mmediately after you take the Attack action on your turn, you can spend 2 ki points to cast the burning hands spell as a bonus action.\n" +
                    "\n" +
                    "You can spend additional ki points to cast burning hands as a higher-level spell. Each additional ki point you spend increases the spell’s level by 1. The maximum number of ki points (2 plus any additional points) that you can spend on the spell is " + (int)(iLevel/2), "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Searing Sunburst", "As an action, you magically create an orb and hurl it at a point you choose within 150 feet, where it erupts into a sphere of radiant light for a brief but deadly instant.\n" +
                    "\n" +
                    "Each creature in that 20-foot-radius sphere must succeed on a Constitution saving throw or take 2d6 radiant damage. A creature doesn’t need to make the save if the creature is behind total cover that is opaque.\n" +
                    "\n" +
                    "You can increase the sphere’s damage by spending ki points. Each point you spend, to a maximum of 3, increases the damage by 2d6.", "150ft", -1, dc, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 17) {
            int dmg = 5 + iCharac.getModifier(Enumerations.Attributes.WIS);
            powers.add(new Power("Sun Shield", "You shed bright light in a 30-foot radius and dim light for an additional 30 feet. You can extinguish or restore the light as a bonus action.\n" +
                    "\n" +
                    "If a creature hits you with a melee attack while this light shines, you can use your reaction to deal " + dmg + " radiant damage to the creature.", "30 (60)ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
