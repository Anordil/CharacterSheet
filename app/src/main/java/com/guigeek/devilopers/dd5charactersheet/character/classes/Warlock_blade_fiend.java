package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;


public class Warlock_blade_fiend implements Class, Externalizable {

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

    public static final long serialVersionUID = 204L;
    protected int _version = 1;


    public Warlock_blade_fiend(){}
    public Warlock_blade_fiend(Warlock_blade_fiend other) {
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
        return App.getResString(R.string.class_warlock_blade);
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
        return iCharacterLevel >= 5 ? 2:1;
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
            int thp = iLevel + iCharac.getModifier(Enumerations.Attributes.CHA);
            powers.add(new Power("Dark One's Blessing", "Reducing a hostile creature to 0 HP grants " + thp + " temporary HP", "Self", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
        	powers.add(new Power("Dark One's own luck", "Add 1D10 to an ability check or saving throw, possibly after rolling.", "Melee", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 10) {
    		powers.add(new Power("Fiendish Resilience", "After a short or long rest, choose one damage type to gain resistance to.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Hurl through Hell", "When hitting a creature with a melee attack, teleports it to Hell. It reappears at the end of your next turn and is dealt 10D10 psychic damage if it's not a fiend.", "Melee", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        if (iLevel >= 20) {
            powers.add(new Power("Eldritch Master", "Recover all Warlock spell slots.", "", 1, -1, true, Enumerations.ActionType.ACTION));
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
