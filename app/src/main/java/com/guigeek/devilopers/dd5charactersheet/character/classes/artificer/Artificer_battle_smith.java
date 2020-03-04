package com.guigeek.devilopers.dd5charactersheet.character.classes.artificer;

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

public class Artificer_battle_smith extends BaseArchetype {
    static final long serialVersionUID = 2004L;

    @Override
    public String getName() {
        return App.getResString(R.string.artificer_battle_smith);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained proficiency with smith’s tools. If you already have this proficiency, you gain proficiency with one other type of artisan’s tools of your choice.");
            levelUp.add("You gain proficiency with martial weapons.");
            levelUp.add("Gained Battle Ready");
            levelUp.add("Gained Steel Defender");
        }
        if (iNewCharacterLevel == 5) {
            levelUp.add("Gained Extra Attack");
        }
        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Arcane Jolt");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Improved Defender");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();


        if (iLevel >= 3) {
            powers.add(new Power("Battle Ready", "When you attack with a magic weapon, you can use your Intelligence modifier, instead of Strength or Dexterity modifier, for the attack and damage rolls.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Steel Defender", "Your tinkering has borne you a faithful companion, a steel defender. It is friendly to you and your companions, and it obeys your commands. See this creature’s game statistics in the steel defender stat block. You determine the creature’s appearance and whether it has two legs or four; your choice has no effect on its game statistics.\n" +
                    "\n" +
                    "In combat, the steel defender shares your initiative count, but it takes its turn immediately after yours. It can move and use its reaction on its own, but the only action it takes on its turn is the Dodge action, unless you take a bonus action on your turn to command it to take one of the actions in its stat block or the Dash, Disengage, Help, Hide, or Search action.\n" +
                    "\n" +
                    "If the mending spell is cast on it, it regains 2d6 hit points. If it has died within the last hour, you can use your smith’s tools as an action to revive it, provided you are within 5 feet of it and you expend a spell slot of 1st level or higher. The steel defender returns to life after 1 minute with all its hit points restored.\n" +
                    "\n" +
                    "At the end of a long rest, you can create a new steel defender if you have your smith’s tools with you. If you already have a steel defender from this feature, the first one immediately perishes.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Arcane Jolt", "When either you hit a target with a magic weapon attack or your steel defender hits a target, you can channel magical energy through the strike to create one of the following effects:\n" +
                    "\n" +
                    "The target takes an extra " + (iLevel >= 15 ? 4 : 2) + "d6 force damage.\n" +
                    "Choose one creature or object you can see within 30 feet of the target. Healing energy flows into the chosen recipient, restoring " + (iLevel >= 15 ? 4 : 2) + "d6 hit points to it.\n" +
                    "You can use this energy a number of times equal to your Intelligence modifier (minimum of once), but you can do so no more than once on a turn. You regain all expended uses when you finish a long rest.", "", iCharac.getModifier(Enumerations.Attributes.INT), -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Improved Defender", "Your Arcane Jolt and steel defender become more powerful:\n" +
                    "\n" +
                    "The extra damage and the healing of your Arcane Jolt both increase to 4d6.\n" +
                    "Your steel defender gains a +2 bonus to Armor Class.\n" +
                    "Whenever your steel defender uses its Deflect Attack, the attacker takes force damage equal to 1d4 + your Intelligence modifier.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        return fettles;
    }

    @Override
    public String getDescription() {
        return "Armies require protection, and someone has to put things back together if defenses fail. A combination  of protector and medic, a Battle Smith is an expert at defending others and repairing both material and personnel. To aid in their work, Battle Smiths are usually accompanied by a steel defender, a protective companion of their own creation. Many soldiers tell stories of nearly dying before being saved by a Battle Smith and a steel defender.";
    }
}
