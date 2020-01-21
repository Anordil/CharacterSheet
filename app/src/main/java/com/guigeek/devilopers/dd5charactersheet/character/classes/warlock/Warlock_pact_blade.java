package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;


public class Warlock_pact_blade extends BaseArchetype {
    static final long serialVersionUID = 215L;

    public Warlock_pact_blade(){}


    @Override
    public String getName() {
        return App.getResString(R.string.warlock_pact_blade);
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();


        if (iLevel >= 3) {
            powers.add(new Power("Pact of the Blade", "Create a pact weapon in your empty hand. You're proficient with it, and it is considered magical." +
                    "\nIt disappears if it's more than 5ft from you for 1mn or more, if you use this feature again, if you dismiss it (no action), or die." +
                    "/n You can transform one magic weapon into a Pact weapon (1h ritual, can be done during short rest).", "Self", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        return powers;
    }
}
