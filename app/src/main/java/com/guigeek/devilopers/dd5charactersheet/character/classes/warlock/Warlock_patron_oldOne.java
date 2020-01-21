package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by totou on 14/03/2016.
 */
public class Warlock_patron_oldOne extends BaseArchetype {
    static final long serialVersionUID = 218L;

    public Warlock_patron_oldOne(){}

    @Override
    public String getName() {
        return App.getResString(R.string.warlock_patron_ancient);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gained Awakened Mind!");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Entropic Ward!");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Thought Shield!");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Create Thrall!");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Awakened Mind", "You can communicate telepathically with any creature you can see within 30 feet of you.", "30ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
        	powers.add(new Power("Entropic Ward", "When a creature makes an attack roll against you, you can use your reaction to impose disadvantage on that roll. ]f the attack misses you, your next attack roll against the creature has advantage if you make it before the end of your next turno", "Melee", -1, -1, true, Enumerations.ActionType.REACTION));
        }

        if (iLevel >= 10) {
    		powers.add(new Power("Thought Shield", "Your thoughts can't be read by telepathy or other means unless you allow it. You also have resistance to psychic damage, and whenever a creature deals psychic damage to you, that creature takes the same amount of damage that you do.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
    	}

        if (iLevel >= 14) {
            powers.add(new Power("Create Thrall", "You gain the ability to infect a humanoid's mind with the alien magic of your patron. You can use your action to touch an incapacitated humanoid. That creature is then charmed by you until a remove curse spell is cast on it, the charmed condition is removed from it, or you use this feature again. You can communicate telepathically with the charmed creature as long as the two of you are on the same plane of existence.", "Touch", -1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
