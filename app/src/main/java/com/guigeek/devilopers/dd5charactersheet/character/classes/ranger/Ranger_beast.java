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

public class Ranger_beast extends BaseArchetype {
    static final long serialVersionUID = 2801L;

    @Override
    public String getName() {
        return App.getResString(R.string.ranger_beast);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Ranger’s Companion");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Exceptional Training");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Gained Bestial Fury");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Share Spells");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Ranger’s Companion", "You gain a beast companion that accompanies you on your adventures and is trained to fight alongside you. Choose a beast that is no larger than Medium and that has a challenge rating of 1/4 or lower. Add your proficiency bonus to the beast’s AC, attack rolls, and damage rolls, as well as to any saving throws and skills it is proficient in. Its hit point maximum equals the hit point number in its stat block or four times your ranger level, whichever is higher. Like any creature, it can spend Hit Dice during a short rest to regain hit points.\n" +
                    "\n" +
                    "The beast obeys your commands as best as it can. It takes its turn on your initiative. On your turn, you can verbally command the beast where to move (no action required by you). You can use your action to verbally command it to take the Attack, Dash, Disengage, or Help action. If you don't issue a command, the beast takes the Dodge action. Once you have the Extra Attack feature, you can make one weapon attack yourself when you command the beast to take the Attack action.\n" +
                    "\n" +
                    "If you are incapacitated or absent, the beast acts on its own, focusing on protecting you and itself. The beast never requires your command to use its reaction, such as when making an opportunity attack.\n" +
                    "\n" +
                    "While traveling through your favored terrain with only the beast, you can move stealthily at a normal pace.\n" +
                    "\n" +
                    "If the beast dies, you can obtain a new companion by spending 8 hours magically bonding with a beast that isn’t hostile to you and that meets the requirements.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Exceptional Training", "On any of your turns when your beast companion doesn’t attack, you can use a bonus action to command the beast to take the Dash, Disengage, or Help action on its turn.\n" +
                    "\n" +
                    "In addition, the beast's attacks now count as magical for the purpose of overcoming resistance and immunity to nonmagical attacks and damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Bestial Fury", "When you command your beast companion to take the Attack action, the beast can make two attacks, or it can take the Multiattack action if it has that action.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("When you cast a spell targeting yourself, you can also affect your beast companion with the spell if the beast is within 30 feet of you.", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
