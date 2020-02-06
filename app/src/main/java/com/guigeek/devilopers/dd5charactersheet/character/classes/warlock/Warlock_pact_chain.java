package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;

/**
 * Created by totou on 14/03/2016.
 */
public class Warlock_pact_chain extends BaseArchetype {
    static final long serialVersionUID = 219L;

    @Override
    public String getName() {
        return App.getResString(R.string.warlock_pact_chain);
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Pact of the Chain", "You learn the find familiar spell and can cast it as a ritual. The spell doesn’t count against your number of spells known.\n" +
                    "\n" +
                    "When you cast the spell, you can choose one of the normal forms for your familiar or one of the following special forms: imp, pseudodragon, quasit, or sprite.\n" +
                    "\n" +
                    "Additionally, when you take the Attack action, you can forgo one of your own attacks to allow your familiar to make one attack with its reaction.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
