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
 * Created by ggallani on 19/02/2016.
 */
public class Paladin implements Class, Externalizable {

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

        int level = character._level;
        if (level >= 3) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.DISEASES.ordinal()));
        }
        if (level >= 6) {
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_MODIFIER, character.getModifier(Enumerations.Attributes.CHA), Enumerations.SavingThrows.ALL.ordinal()));
        }
        if (level >= 10) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.FEAR.ordinal()));
        }
        if (level >= 11) {
            fettles.add(new Fettle(Enumerations.FettleType.ATTACK_DAMAGE_DICE, "1D8", Enumerations.DamageTypes.RADIANT.ordinal()));
        }

        return fettles;
    }

    int[][] _spellSlots = {
                // spell level 0-9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
                {0, 2, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 2, 0, 0, 0, 0, 0, 0, 0},//lv 5
                {0, 4, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 2, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 2, 0, 0, 0, 0, 0, 0},//lv 10
                {0, 4, 3, 3, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 0, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 1, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 1, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 2, 0, 0, 0, 0, 0},//lv 15
                {0, 4, 3, 3, 2, 0, 0, 0, 0, 0},
                {0, 4, 3, 3, 3, 1, 0, 0, 0, 0},
                {0, 4, 3, 3, 3, 1, 0, 0, 0, 0},
                {0, 4, 3, 3, 3, 2, 0, 0, 0, 0},
                {0, 4, 3, 3, 3, 2, 0, 0, 0, 0}//ln 20
        };

    public static final long serialVersionUID = 201L;
    protected int _version = 1;

    public Paladin(){}
    public Paladin(Paladin other) {
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
        return App.getResString(R.string.class_paladin);
    }

    @Override
    public int getHitDie() {
        return 10;
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
            powers.add(new Power("Divine Sense", "Until the end of your next turn, you know the location of any celestial, fiend, or undead within 60 feet of you that is not behind total cover. You know the type (celestial, fiend, or undead) of any being whose presence you sense, but not its identity. Within the same radius, you also detect the presence of any place or object that has been consecrated or desecrated, as with the hallow spell.", "60ft", 1 + iCharac.getModifier(Enumerations.Attributes.CHA), -1, true, Enumerations.ActionType.ACTION));
            powers.add(new Power("Lay on hands", "Heal HP. 5hp to remove poison/disease.", "Melee", 5*iLevel, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 2) {
            powers.add(new Power("Divine Smite", "Expend spell slot to add (1 + spell level)D8 radiant damage to the next melee attack.", "60ft", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 3) {
            powers.add(new Power("Divine Health", "Immune to diseases", "Self", -1, -1, true,Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            if (iLevel >= 18) {
                powers.add(new Power("Aura of Protection", "Allies within 30ft gain +CHA to their saving throws", "30ft", -1, -1, true,Enumerations.ActionType.PASSIVE));
            }
            else {
                powers.add(new Power("Aura of Protection", "Allies within 10ft gain +CHA to their saving throws", "10ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        }
        if (iLevel >= 10) {
            if (iLevel >= 18) {
                powers.add(new Power("Aura of Courage", "Allies within 30ft are immune to fear", "30ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            else {
                powers.add(new Power("Aura of Courage", "Allies within 10ft are immune to fear", "10ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        }
        if (iLevel >= 11) {
            powers.add(new Power("Improved Divine Smite", "+1D8 radiant damage on melee hits.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Cleansing touch", "Remove spell on self/ally", "Melee", iCharac.getModifier(Enumerations.Attributes.CHA), -1, true,Enumerations.ActionType.ACTION));
        }

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "One creature within 60ft must make a wisdom saving throw. Undead/fiends have disadvantage. On a failed save, it is freightened and its speed is 0 for 1mn or until it takes damage.\nOr target a creature within 10ft: you have advantage on attack rolls against it for 1mn.", "60ft/10ft", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Relentless avenger", "Opportunity attack enables to move at half speed during reaction. Doesn't trigger OA.", "Melee", -1, -1, false, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Soul of Vengeance", "Creature targeted by Vow on Enmity triggers OA for me when attacking", "Melee", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_paladin;
    }
}
