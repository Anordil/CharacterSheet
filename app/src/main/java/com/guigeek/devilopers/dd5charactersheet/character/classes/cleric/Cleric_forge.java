package com.guigeek.devilopers.dd5charactersheet.character.classes.cleric;

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

public class Cleric_forge extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2403L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_forge);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Gained proficiency with heavy armor and smith's tools");
            levelUp.add("Gained Blessing of the Forge");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Soul of the Forge");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Divine Strike");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Divine Strike now deals an extra 2d8");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Saint of Forge and Fire");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Blessing of the Forge", "You gain the ability to imbue magic into a weapon or armor. At the end of a long rest, you can touch one nonmagical object that is a suit of armor or a simple or martial weapon. Until the end of your next long rest or until you die, the object becomes a magic item, granting a +1 bonus to AC if it’s armor or a +1 bonus to attack and damage rolls if it’s a weapon.\n" +
                    "\n" +
                    "Once you use this feature, you can’t use it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Soul of the Forge", "You have resistance to fire damage and +1 AC while wearing heavy armor.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Divine Strike", "Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra " + (iLevel >= 14 ? 2 : 1) + "d8 fire damage to the target.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Saint of Forge and Fire", "You're immune to fire.\nWhile wearing heavy armor, you have resistance to bludgeoning, piercing, and slashing damage from nonmagical attacks.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        return iLevel >= 2 ? "[Artisan's Blessing] You conduct an hour-long ritual that crafts a nonmagical item that must include some metal: a simple or martial weapon, a suit of armor, ten pieces of ammunition, a set of tools, or another metal object (see chapter 5, “Equipment,” in the Player’s Handbook for examples of these items). The creation is completed at the end of the hour, coalescing in an unoccupied space of your choice on a surface within 5 feet of you.\n" +
                "\n" +
                "The thing you create can be something that is worth no more than 100 gp. As part of this ritual, you must lay out metal, which can include coins, with a value equal to the creation. The metal irretrievably coalesces and transforms into the creation at the ritual’s end, magically forming even nonmetal parts of the creation.\n" +
                "\n" +
                "The ritual can create a duplicate of a nonmagical item that contains metal, such as a key, if you possess the original during the ritual." : "";
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (character._level >= 17) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.FIRE.ordinal()));

            if (character._equippedArmor != null && character._equippedArmor.isHeavy()) {
                fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.BLUDGEONING.ordinal()));
                fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.PIERCING.ordinal()));
                fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.SLASHING.ordinal()));
            }
        }
        else if (character._level >= 6) {
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.FIRE.ordinal()));
        }

        return fettles;
    }
}
