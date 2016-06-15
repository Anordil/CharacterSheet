package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.*;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by totou on 15/06/2016.
 */
public class Barbarian implements Class, Externalizable {

    int[][] _spellSlots = {
                // spell level 0-9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 5
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 10
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 15
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//ln 20
        };

    public static final long serialVersionUID = 203L;
    protected int _version = 1;

    public Barbarian(){}

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_spellSlots);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        _version = version;
        if (version >= 1) {
            _spellSlots = (int[][])oi.readObject();
        }
    }


    @Override
    public String getName() {
        return App.getResString(R.string.class_barbarian);
    }

    @Override
    public int getHitDie() {
        return 12;
    }

    @Override
    public boolean isCaster() {
        return false;
    }

    @Override
    public int[] getSpellSlots(int iCharacterLevel) {
        return _spellSlots[Math.min(20, iCharacterLevel)];
    }

    @Override
    public int getAttacksPerRound(int iCharacterLevel) {
        return (iCharacterLevel >= 5 ? 2 : 1);
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
        	int max;
        	if (iLevel < 3) {
        		// Level 1 to 2
        		max = 2;
        	} else if (iLevel < 6) {
        		// Level 3 to 5
        		max = 3;
        	} else if (iLevel < 12) {
        		// Level 6 to 11
        		max = 4;
        	} else if (iLevel < 17) {
        		// Level 12 to 16
        		max = 5;
        	} else if (iLevel < 20) {
        		// Level 17 to 19
        		max = 6;
        	} else {
        		// Level 20
        		max = 9999;
        	}
        	int dmg = 2;
        	if (iLevel < 9) {
        		// Level 1 to 8
        		dmg = 2;
        	} else if (iLevel < 16) {
        		// Level 9 to 15
        		dmg = 3;
        	} else {
        		// Level 16+
        		dmg = 4;
        	}
        	String desc = "You have advantage on Strength checks and Strength saving throws. Damage modifier to melee weapon using STR: " + dmg + ". You have resistance to bludgeoning, piercing, and slashing damage. Your rage lasts for 1 minute. It ends early if you are knocked unconscious or if your turn ends and you haven't attacked a hostile creature since your last turn or taken damage since then. You can also end your rage on your turn as a bonus action.";
            int potentialAC = 10 + iCharac.getModifier(Enumerations.Attributes.CON) + iCharac.getModifier(Enumerations.Attributes.DEX);
            powers.add(new Power("Rage", desc, "", max, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Unarmored Defense", "While you are not wearing any armor, your Armor Class equals " + potentialAC + " (10 + your Dexterity modifier + your Constitution modifier). You can use a shield and still gain this benefit", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 2) {
            powers.add(new Power("Reckless Attack", "You can throw aside all concern for defense to attack with fierce desperation. When you make your first attack on your turn, you can decide to attack recklessly. Doing so gives you advantage on melee weapon attack rolls using Strength during this turn, but attack rolls against you have advantage until your next turn", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Danger Sense", "You gain an uncanny sense of when things nearby aren't as they should be, giving you an edge when you dodge away from danger. You have advantage on Dexterity saving throws against effects that you can see, such as traps and spells. To gain this benefit, you can't be blinded, deafened, or incapacitated", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 3) {
            powers.add(new Power("Primal Path", "Path of the Totem Warrior: Spirit Totem: Bear (Resistance to all damage type except psychic)", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 5) {
            powers.add(new Power("Extra Attack", "You can attack twice, instead of once, whenever you take the Attack action on your turn", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Fast Movement", "Your speed increases by 10 feet while you aren't wearing heavy armor", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
        	powers.add(new Power("Feral Instinct", "Your instincts are so honed that you have advantage on initiative rolls. Additionally, if you are surprised at the beginning of combat and aren't incapacitated, you can act normally on your first turn, but only if you enter your rage before doing anything else on that turn", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
        	if (iLevel < 13) {
        		// Level 9 to 12
        		powers.add(new Power("Brutal Critical", "You can roll one additional weapon damage die when determining the extra damage for a critical hit with a melee attack", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        	} else if (iLevel < 17) {
        		// Level 12 to 16
        		powers.add(new Power("Brutal Critical", "You can roll two additional weapon damage dies when determining the extra damage for a critical hit with a melee attack", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        	} else {
        		// Level 17+
        		powers.add(new Power("Brutal Critical", "You can roll three additional weapon damage dies when determining the extra damage for a critical hit with a melee attack", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        	}
        }
        if (iLevel >= 11) {
            powers.add(new Power("Relentless Rage", "Your rage can keep you fighting despite grievous wounds. If you drop to O hit points while you're raging and don't die outright, you can make a DC 10 Constitution saving throw. If you succeed, you drop to 1 hit point inslead. Each time you use this feature after the first, the DC increases by 5. When you finish a short or long rest, the DC resets to 10", "", -1, -1, true,Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Persistent Rage", "Your rage is so fierce that it ends early only if you fall unconscious or if you choose to end it", "", -1, -1, true,Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Indomitable Might", "If your total for a Strength check is less than your Strength score, you can use that score in place of the total", "", -1, -1, true,Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Primal Champion", "You embody the power of the wilds. Your Strength and Constitution scores increase by 4. Your maximum for those scores is now 24.", "", -1, -1, true,Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_barbarian;
    }
}
