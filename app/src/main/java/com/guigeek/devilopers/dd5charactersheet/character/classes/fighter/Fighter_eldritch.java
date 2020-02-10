package com.guigeek.devilopers.dd5charactersheet.character.classes.fighter;

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

public class Fighter_eldritch extends BaseArchetype {
    static final long serialVersionUID = 2605L;

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_eldritch);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("You learn 2 wizard cantrips");
            levelUp.add("You learn 3 1st level wizard spells, two of which must be from the Evocation or Abjuration school.");
            levelUp.add("Gained Spellcasting");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained War Magic");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Learned a new cantrip ");
            levelUp.add("Gained Eldritch Strike");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Arcane Charge");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Improved War Magic");
        }

        if (iNewCharacterLevel == 8 || iNewCharacterLevel == 14 || iNewCharacterLevel == 20) {
            levelUp.add("You learn a spell from any school.");
        } else if (iNewCharacterLevel == 4 || iNewCharacterLevel == 7 || iNewCharacterLevel == 10 || iNewCharacterLevel == 11 || iNewCharacterLevel == 13 || iNewCharacterLevel == 16 || iNewCharacterLevel == 19) {
            levelUp.add("You learn a spell from the Abjuration or Evocation school.");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Weapon Bond", "You learn a ritual that creates a magical bond between yourself and one weapon. You perform the ritual over the course of 1 hour, which can be done during a short rest. The weapon must be within your reach throughout the ritual, at the conclusion of which you touch the weapon and forge the bond.\n" +
                    "\n" +
                    "Once you have bonded a weapon to yourself, you can’t be disarmed of that weapon unless you are incapacitated. If it is on the same plane of existence, you can summon that weapon as a bonus action on your turn, causing it to teleport instantly to your hand.\n" +
                    "\n" +
                    "You can have up to two bonded weapons, but can summon only one at a time with your bonus action. If you attempt to bond with a third weapon, you must break the bond with one of the other two.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power((iLevel >= 18 ? "Improved " : "") + "War Magic", "When you use your action to cast a cantrip" + (iLevel >= 18 ? " or spell" : "") + ", you can make one weapon attack as a bonus action.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Eldritch Strike", "When you hit a creature with a weapon attack, that creature has disadvantage on the next saving throw it makes against a spell you cast before the end of your next turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Arcane Charge", "You gain the ability to teleport up to 30 feet to an unoccupied space you can see when you use your Action Surge. You can teleport before or after the additional action.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
