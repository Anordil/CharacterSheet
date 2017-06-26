package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.util.Log;

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
 * Created by totou on 14/03/2016.
 */
public class Warlock implements Class, Externalizable {

    @Override
    public int getAC(Character character) {
        int ac = character._equippedArmor.getAC(character);

        if (character._equippedShield != null && character._equippedShield._type == Enumerations.ArmorTypes.SHIELD) {
            ac+= character._equippedShield.getAC(character);
        }

        return ac;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return fettles;
    }

    // Warlock have a single-level spell slot, used for all their spells. Treat it like a Power
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
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // Arcanum spells
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},//lv 15
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1}//ln 20
    };

    public static final long serialVersionUID = 202L;
    protected int _version = 1;


    public Warlock(){}
    public Warlock(Warlock other) {
        _spellSlots = other._spellSlots;
    }

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
        String[] levelUp = new String[100];
        levelUp[0] = "Welcome to Warlock level " + iNewCharacterLevel + "!";

        // Cantrips
        int index = 1;
        if (iNewCharacterLevel == 10) {
            levelUp[index++] = "You now know 4 cantrips.";
        }
        else if (iNewCharacterLevel == 4) {
            levelUp[index++] = "You now know 3 cantrips.";
        }
        else if (iNewCharacterLevel == 1) {
            levelUp[index++] = "You know 2 cantrips.";
        }

        // Spells
        if (iNewCharacterLevel == 1) {
            levelUp[index++] = "You know 2 spells.";
        }
        else if (iNewCharacterLevel <= 9) {
            levelUp[index++] = "You now know " + (iNewCharacterLevel+1) + " spells.";
        }
        else if (iNewCharacterLevel % 2 != 0) {
            float count = iNewCharacterLevel -10;
            levelUp[index++] = "You now know " + (10+(int)(Math.ceil(count/2))) + " spells.";
        }

        // Invocations
        if (iNewCharacterLevel == 2) {
            levelUp[index++] = "You now know 2 invocations.";
        }
        else if (iNewCharacterLevel == 5) {
            levelUp[index++] = "You now know 3 invocations.";
        }
        else if (iNewCharacterLevel == 7) {
            levelUp[index++] = "You now know 4 invocations.";
        }
        else if (iNewCharacterLevel == 9) {
            levelUp[index++] = "You now know 5 invocations.";
        }
        else if (iNewCharacterLevel == 15) {
            levelUp[index++] = "You now know 6 invocations.";
        }
        else if (iNewCharacterLevel == 15) {
            levelUp[index++] = "You now know 7 invocations.";
        }
        else if (iNewCharacterLevel == 18) {
            levelUp[index++] = "You now know 8 invocations.";
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        // Spell slot
        if (iLevel >= 1) {
            int spellLevel = 1;
            int spellSlots = 1;
            int dd = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(getMainSpellAttribute());
            if (iLevel >= 17) {
                spellSlots = 4;
                spellLevel = 5;
            }
            else if (iLevel >= 11) {
                spellSlots = 3;
                spellLevel = 5;
            }
            else if (iLevel >= 9) {
                spellSlots = 2;
                spellLevel = 5;
            }
            else if (iLevel >= 7) {
                spellSlots = 2;
                spellLevel = 4;
            }
            else if (iLevel >= 5) {
                spellSlots = 2;
                spellLevel = 3;
            }
            else if (iLevel >= 3) {
                spellSlots = 2;
                spellLevel = 2;
            }
            else if (iLevel >= 2) {
                spellSlots = 2;
                spellLevel = 1;
            }
            powers.add(new Power("Spell slot", "Consume a slot to cast a spell which level is no more than " + spellLevel + ".\nThe spell is cast as a " + spellLevel + (spellLevel == 1 ? "st" : (spellLevel == 2 ? "nd" : (spellLevel == 3 ? "rd": "th"))) + " level spell.", "Spell", spellSlots, dd, false, Enumerations.ActionType.ACTION));
        }

        if (iLevel >= 1) {
            powers.add(new Power("Awakened Mind", "You can communicate telepathically with any creature you can see within 30 feet of you.", "30ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
        	powers.add(new Power("Entropic Ward", "When a creature makes an attack roll against you, you can use your reaction to impose disadvantage on that roll. ]f the attack misses you, your next attack roll against the creature has advantage if you make it before the end of your next turno", "Melee", -1, -1, true, Enumerations.ActionType.REACTION));
        }

        if (iLevel >= 10) {
    		powers.add(new Power("Thought Shield", "Your thoughts can't be read by telepathy or other means unless you allow it. You also have resistance to psychic damage, and whenever a creature deals psychic damage to you, that creature takes the same amount of damage that you do.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Create Thrall", "You gain the ability to infect a humanoid's mind with the alien magic of your patron. You can use your action to touch an incapacitated humanoid. That creature is then charmed by you until a remove curse spell is cast on it, the charmed condition is removed from it, or you use this feature again. You can communicate telepathically with the charmed creature as long as the two of you are on the same plane of existence.", "Touch", -1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }


    @Override
    public int getIconResource() {
        return R.drawable.ic_warlock;
    }
}
