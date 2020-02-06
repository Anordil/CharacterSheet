package com.guigeek.devilopers.dd5charactersheet.character.classes.druid;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;

public class Druid_wildfire extends BaseArchetype {
    static final long serialVersionUID = 2506L;

    @Override
    public String getName() {
        return App.getResString(R.string.druid_wildfire);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Summon Wildfire");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Enhanced Bond");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Flames of Life");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Blazing Endurance");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("Summon Wildfire", "You can summon the primal spirit bound to your soul. As an action, you can expend one use of your Wild Shape feature to summon your wildfire spirit, rather than assuming a beast form.\n" +
                    "\n" +
                    "The spirit appears in an unoccupied space of your choice you can see within 30 feet of you. Each creature within 10 feet of the spirit (other than you) when it appears must succeed on a Dexterity saving throw against your spell save DC or take 2d10 fire damage.\n" +
                    "\n" +
                    "The wildfire spirit is friendly to you and your companions and obeys your commands. See this creature’s game statistics in the wildfire spirit stat block. You determine the spirit’s appearance. Some spirits take the form of a humanoid figure made of gnarled branches covered in flame, while others look like beasts wreathed in fire.\n" +
                    "\n" +
                    "In combat, the wildfire spirit shares your initiative count, but it takes its turn immediately after yours. The only action it takes on its turn is the Dodge action, unless you take a bonus action on your turn to command it to take one of the actions in its stat block or to take the Dash, Disengage, Help, or Hide action.\n" +
                    "\n" +
                    "The wildfire spirit manifests for 1 hour, until it is reduced to 0 hit points, or until you use your Wild Shape again.", "30ft", -1, 8+iCharac.getProficiencyBonus()+iCharac.getModifier(Enumerations.Attributes.WIS), true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Enhanced Bond", "The bond with your wildfire spirit enhances your destructive and restorative spells. Whenever you cast a spell that deals fire damage or restores hit points while your wildfire spirit is summoned, roll a d8, and you gain a bonus to one roll of the spell equal to the number rolled.\n" +
                    "\n" +
                    "In addition, when you cast a spell with a range other than self, the spell can originate from you or your wildfire spirit.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Flames of Life", "You gain the ability to turn death into flames of vitality. When a Small or larger creature that you can see dies within 30 feet of you or your wildfire spirit, you can use your reaction to cause primal flames to spring from the body. When a creature you can see touches these flames, the creature regains hit points or takes fire damage (your choice) equal to 2d10 + your Wisdom modifier. The flames vanish after a creature has touched them or after 1 minute.", "30ft", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Blazing Endurance", "The bond with your wildfire spirit is exceptionally strong, even fatal blows only fuel your defiance. If you drop to 0 hit points and don’t die outright, you drop to 1 hit point instead and gain temporary hit points equal to five times your druid level, and each creature of your choice within 30 feet of you that you can see takes fire damage equal to 2d10 + your druid level.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
