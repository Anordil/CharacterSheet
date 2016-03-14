package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.*;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by totou on 14/03/2016.
 */
public class Warlock implements Class, Serializable {

    private int[][] _spellSlots = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
            {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 5, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 6, 0, 0, 0, 0, 0, 0},//lv 5
            {0, 0, 0, 7, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 8, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 9, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 10, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 10, 0, 0, 0, 0},//lv 10
            {0, 0, 0, 0, 0, 11, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 11, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 12, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 12, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 13, 1, 1, 1, 0},//lv 15
            {0, 0, 0, 0, 0, 13, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 14, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 14, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 15, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 15, 1, 1, 1, 1}//ln 20
    };

    @Override
    public String getName() {
        return App.getResString(R.string.class_warlock);
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public boolean isCaster() {
        return true;
    }

    @Override
    public int[] getSpellSlots(int iCharacterLevel) {
        return _spellSlots[Math.min(20, iCharacterLevel)];
    }

    @Override
    public int getAttacksPerRound(int iCharacterLevel) {
        return 1;
    }

    @Override
    public String[] getLevelUpBenefits(int iNewCharacterLevel) {
        String[] levelUp = new String[1];
        levelUp[0] = "Welcome to level " + iNewCharacterLevel + "!";
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Awakened Mind", "You can communicate telepathically with any creature you can see within 30 feet of you.", "30ft", -1, -1, true));
        }

        if (iLevel >= 6) {
        	powers.add(new Power("Entropic Ward", "When a creature makes an attack roll against you, you can use your reaction to impose disadvantage on that roll. ]f the attack misses you, your next attack roll against the creature has advantage if you make it before the end of your next turno", "Unlimited", -1, -1, true));
        }

        if (iLevel >= 10) {
    		powers.add(new Power("Thought Shield", "Your thoughts can't be read by telepathy or other means unless you allow it. You also have resistance to psychic damage, and whenever a creature deals psychic damage to you, that creature takes the same amount of damage that you do.", "Unlimited", -1, -1, true));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Create Thrall", "You gain the ability to infect a humanoid's mind with the alien magic of your patron. You can use your action to touch an incapacitated humanoid. That creature is then charmed by you until a remove curse spell is cast on it, the charmed condition is removed from it, or you use this feature again. You can communicate telepathically with the charmed creature as long as the two of you are on the same plane of existence.", "Touch", -1, -1, true));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }
}
