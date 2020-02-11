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

public class Cleric_war extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2413L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_war);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gain proficiency with martial weapons and heavy armor.");
            levelUp.add("Gained War Priest");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Divine Strike");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Avatar of Battle");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("War Priest", "When you use the Attack action, you can make one weapon attack as a bonus action.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (a minimum of once). You regain all expended uses when you finish a long rest.", "", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("", "", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Divine Strike", "Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra " + (iLevel >= 14 ? 2 : 1) + "d8 psychic damage to the target. ", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Avatar of Battle", "You gain resistance to bludgeoning, piercing, and slashing damage from nonmagical weapons.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        String desc = iLevel >= 2 ? "[Guided Strike] When you make an attack roll, you can use your Channel Divinity to gain a +10 bonus to the roll. You make this choice after you see the roll, but before the DM says whether the attack hits or misses." : "";
        if (iLevel >= 6) {
            desc += "\n\n[War God’s Blessing] When a creature within 30 feet of you makes an attack roll, you can use your reaction to grant that creature a +10 bonus to the roll, using your Channel Divinity. You make this choice after you see the roll, but before the DM says whether the attack hits or misses.";
        }
        return desc;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (classLevel >= 17) {
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.BLUDGEONING.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.PIERCING.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.SLASHING.ordinal()));
        }

        return fettles;
    }
}
