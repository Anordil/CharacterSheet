package com.guigeek.devilopers.dd5charactersheet.character.classes.ranger;

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

public class Ranger_hunter extends BaseArchetype {
    static final long serialVersionUID = 2804L;

    @Override
    public String getName() {
        return App.getResString(R.string.ranger_hunter);
    }



    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Hunter’s Prey");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Defensive Tactics");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Gained Multiattack");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Superior Hunter’s Defense");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Hunter’s Prey", "You gain one of the following features of your choice.\n" +
                    "\n" +
                    "Colossus Slayer\n" +
                    "Your tenacity can wear down the most potent foes. When you hit a creature with a weapon attack, the creature takes an extra 1d8 damage if it’s below its hit point maximum. You can deal this extra damage only once per turn.\n" +
                    "\n" +
                    "Giant Killer\n" +
                    "When a Large or larger creature within 5 feet of you hits or misses you with an attack, you can use your reaction to attack that creature immediately after its attack, provided that you can see the creature.\n" +
                    "\n" +
                    "Horde Breaker\n" +
                    "Once on each of your turns when you make a weapon attack, you can make another attack with the same weapon against a different creature that is within 5 feet of the original target and within range of your weapon.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Defensive Tactics", "You gain one of the following features of your choice.\n" +
                    "\n" +
                    "Escape the Horde\n" +
                    "Opportunity attacks against you are made with disadvantage.\n" +
                    "\n" +
                    "Multiattack Defense\n" +
                    "When a creature hits you with an attack, you gain a +4 bonus to AC against all subsequent attacks made by that creature for the rest of the turn.\n" +
                    "\n" +
                    "Steel Will\n" +
                    "You have advantage on saving throws against being frightened.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Multiattack", "You gain one of the following features of your choice.\n" +
                    "\n" +
                    "Volley\n" +
                    "You can use your action to make a ranged attack against any number of creatures within 10 feet of a point you can see within your weapon’s range. You must have ammunition for each target, as normal, and you make a separate attack roll for each target.\n" +
                    "\n" +
                    "Whirlwind Attack\n" +
                    "You can use your action to make a melee attack against any number of creatures within 5 feet of you, with a separate attack roll for each target.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Superior Hunter’s Defense", "You gain one of the following features of your choice.\n" +
                    "\n" +
                    "Evasion\n" +
                    "When you are subjected to an effect, such as a red dragon’s fiery breath or a lightning bolt spell, that allows you to make a Dexterity saving throw to take only half damage, you instead take no damage if you succeed on the saving throw, and only half damage if you fail.\n" +
                    "\n" +
                    "Stand Against the Tide\n" +
                    "When a hostile creature misses you with a melee attack, you can use your reaction to force that creature to repeat the same attack against another creature (other than itself) of your choice.\n" +
                    "\n" +
                    "Uncanny Dodge\n" +
                    "When an attacker that you can see hits you with an attack, you can use your reaction to halve the attack’s damage against you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
