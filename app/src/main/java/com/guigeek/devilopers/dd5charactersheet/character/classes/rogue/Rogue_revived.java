package com.guigeek.devilopers.dd5charactersheet.character.classes.rogue;

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


public class Rogue_revived extends BaseArchetype {
    static final long serialVersionUID = 2075L;

    public Rogue_revived(){}

    @Override
    public String getName() {
        return App.getResString(R.string.rogue_revived);
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (classLevel >= 3) {
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.DISEASE.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.POISON.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.POISON.ordinal()));
        }

        return fettles;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Tokens of Past Lives");
            levelUp.add("Gained Revived Nature");
            levelUp.add("Gained Bolts from the Grave");
        }

        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Connect with the Dead");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("Gained Audience with Death");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Ethereal Jaunt");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Tokens of Past Lives", "When you finish a long rest, you gain one skill or tool proficiency of your choice. You can replace this proficiency with another when you finish a long rest.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Bolts from the Grave", "Immediately after you use your Cunning Action, you can make a ranged spell attack against a creature within 30 feet of you, provided you haven’t used your Sneak Attack this turn. You are proficient with it, and you add your Dexterity modifier to its attack and damage rolls. A creature hit by this attack takes necrotic damage equal to your Sneak Attack. This uses your Sneak Attack for the turn.", "30ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Revived Nature", "Your newfound connection to death gives you the following benefits:\\n\" +\n" +
                    "                    \"\\n\" +\n" +
                    "                    \"You have advantage on saving throws against disease and being poisoned, and you have resistance to poison damage.\\n\" +\n" +
                    "                    \"You don’t need to eat, drink, or breathe.\\n\" +\n" +
                    "                    \"You don’t need to sleep. When you take a long rest, you must spend at least four hours in an inactive, motionless state, rather than sleeping. In this state, you remain semiconscious, and you can hear as normal.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Connect with the Dead", "You can create a link with a spirit through their corpse. When you do so, you cast the speak with dead spell, without using a spell slot or material components. Intelligence is your spellcasting ability for this spell.\n" +
                    "\n" +
                    "Speaking with the dead in this way temporarily gives you a capability from a past life—you’re unsure whether it’s from your past or the spirit’s. When the spell ends, you gain one random benefit from the Revived Capabilities table (roll 1d3). The benefit lasts until you finish a short or long rest." +
                    "\n[1] You learn how to speak, read, and write one language of your choice." +
                    "\n[2] You gain one skill or tool proficiency of your choice." +
                    "\n[3] You gain proficiency with one saving throw of your choice.", "", 1, -1, false, Enumerations.ActionType.PASSIVE));
                    }
        if (iLevel >= 13) {
            powers.add(new Power("Audience with Death", "When at death’s door, you can converse with the powers of death. You have advantage on death saving throws, and whenever you make a death saving throw, your spirit can ask an entity of death a question that can be answered with “yes,” “no,” or “unknown.” The entity answers truthfully, using the knowledge of all those who have died.\n" +
                    "\n" +
                    "In addition, whenever you have 0 hit points and are healed or stabilized, you can change any of your personal characteristics: personality trait, ideal, bond, or flaw.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Ethereal Jaunt", "Like a ghost, you have the ability to slip in and out of the Ethereal Plane. You can now use your Cunning Action to teleport to an unoccupied space within 30 feet of you. You don’t need to see that space to teleport to it, but your teleportation fails, wasting your bonus action, if you attempt to teleport through magical force that is Medium or larger, such as a wall of force. If you appear in a space occupied by another creature or filled by an object, you are immediately shunted to the nearest unoccupied space that you can occupy and take force damage equal to twice the number of feet you are shunted.", "30ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
