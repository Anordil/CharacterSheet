package com.guigeek.devilopers.dd5charactersheet.character.classes.paladin;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin_oathbreaker extends BaseArchetype {
    static final long serialVersionUID = 226L;

    public Paladin_oathbreaker(){}

    @Override
    public String getName() {
        return App.getResString(R.string.paladin_oathbreaker);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Channel Divinity.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Aura of Hate");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You gained Supernatural Resistance");
        }
        else if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Dread Lord");
        }

        return levelUp;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> perks = new LinkedList<>();

        if (character._level >= 7) {
            perks.add(new Fettle(Enumerations.FettleType.ATTACK_BONUS_MODIFIER, Math.max(1, character.getModifier(Enumerations.Attributes.CHA)), 0));
        }
        if (character._level >= 15) {
            perks.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.BLUDGEONING.ordinal()));
            perks.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.PIERCING.ordinal()));
            perks.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.SLASHING.ordinal()));
        }

        return perks;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "Control Undead. As an action, the paladin targets one undead creature he or she can see within 30 feet of him or her. The target must make a Wisdom saving throw. On a failed save, the target must obey the paladin’s commands for the next 24 hours, or until the paladin uses this Channel Divinity option again. An undead whose challenge rating is equal to or greater than the paladin’s level is immune to this effect.\n" +
                    "\n" +
                    "Dreadful Aspect. As an action, the paladin channels the darkest emotions and focuses them into a burst of magical menace. Each creature of the paladin’s choice within 30 feet of the paladin must make a Wisdom saving throw if it can see the paladin. On a failed save, the target is frightened of the paladin for 1 minute. If a creature frightened by this effect ends its turn more than 30 feet away from the paladin, it can attempt another Wisdom saving throw to end the effect on it.", "", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 7) {
            String range = iLevel >= 18 ? "30ft" : "10ft";
            powers.add(new Power("Aura of Hate", "You and any fiends and undead within " + range + " feet of the paladin, gains a bonus to melee weapon damage rolls equal to +" + Math.max(1, iCharac.getModifier(Enumerations.Attributes.CHA)) + ". A creature can benefit from this feature from only one paladin at a time.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Supernatural Resistance", "Resistance to bludgeoning, piercing and slashing damage from nonmagical weapons.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Dread Lord", "The paladin can, as an action, surround himself or herself with an aura of gloom that lasts for 1 minute. The aura reduces any bright light in a 30-foot radius around the paladin to dim light. Whenever an enemy that is frightened by the paladin starts its turn in the aura, it takes 4d10 psychic damage. Additionally, the paladin and creatures he or she chooses in the aura are draped in deeper shadow. Creatures that rely on sight have disadvantage on attack rolls against creatures draped in this shadow.\n" +
                    "\n" +
                    "While the aura lasts, the paladin can use a bonus action on his or her turn to cause the shadows in the aura to attack one creature. The paladin makes a melee spell attack against the target. If the attack hits, the target takes necrotic damage equal to 3d10 + the paladin’s Charisma modifier.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
