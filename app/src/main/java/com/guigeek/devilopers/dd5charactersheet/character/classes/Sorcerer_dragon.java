package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;


public class Sorcerer_dragon extends BaseArchetype {
    static final long serialVersionUID = 211L;

    public Sorcerer_dragon(){}


    @Override
    public String getName() {
        return App.getResString(R.string.sorcerer_dragon);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Draconic Resilience!");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Elemental Affinity!");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Dragon Wings!");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("You gained Draconic Presence!");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();


        if (iLevel >= 1) {
            powers.add(new Power("Draconic Resilience", "+1 HP/level. Your AC is 13+DEX if you aren't wearing armor.", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
        	powers.add(new Power("Elemental Affinity", "When you cast a spell dealing damage type associated with your draconic ancestry, add " + iCharac.getModifier(Enumerations.Attributes.CHA) + " to the that damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 14) {
    		powers.add(new Power("Dragon Wings", "Gain a flying speed of " + iCharac._race.getSpeedInFeet(), "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
    	}

        if (iLevel >= 18) {
            powers.add(new Power("Draconic Presence", "Spend 5 Sorcery points to create a 60ft aura of Awe or Fear, for 1mn (concentration). On a failed Wisdom saving throw, creatures are frightened or charmed until the aura ends. A creature that succeeds on the throw is immune to that aura for 24h.", "Melee", -1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
