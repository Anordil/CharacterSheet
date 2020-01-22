package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.LinkedList;
import java.util.List;


public class Monk_mercy extends BaseArchetype {
    static final long serialVersionUID = 2707L;
    public Monk_mercy(){}

    @Override
    public String getName() {
        return App.getResString(R.string.monk_mercy);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Implements of Mercy");
            levelUp.add("You gained Hands of Harm");
            levelUp.add("You gained Hands of Healing");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Noxious Aura");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Healing Technique (Hand of Healing)");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You gained Hand of Mercy");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);
        int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.WIS);

        if (iLevel >= 3) {
            String healing = "1D" + ((Monk)iCharac._class).getMonkDamageDie(iLevel) + " +" + iCharac.getModifier(Enumerations.Attributes.WIS);

            String handofHealingDesc = "Your mystical touch can mend wounds. As an action, you can spend 1 ki point to touch a creature and restore " + healing +
                "\n\nWhen you use your Flurry of Blows, you can replace one of the unarmed strikes with a use of this feature without spending its ki cost.";
            if (iLevel >= 11) {
                handofHealingDesc += "/n/nHealing Technique: When you restore hit points to a creature using your Hands of Healing, you can also end one disease or a condition from the following list affecting the target: blinded, deafened, paralyzed, or poisoned.";
            }
            powers.add(new Power("Implements of Mercy", "You gain proficiency in the Insight or Medicine skill (your choice), and you gain proficiency with the herbalism kit and the poisoner’s kit.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Hands of Healing", handofHealingDesc, "", -1, -1, true, Enumerations.ActionType.ACTION));
            powers.add(new Power("Hands of Harm", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));

        }
        if (iLevel >= 6) {
            powers.add(new Power("Noxious Aura", "As a bonus action, you spend 1 ki point to turn your ki into an aura of toxic miasma. The aura extends 5 feet from you in every direction, but not through total cover. It lasts for 1 minute, until you’re incapacitated, or you dismiss it (no action required).\n" +
                    "\n" +
                    "While your aura is active, ranged attacks have disadvantage against you. Any other creature that starts its turn in the aura must succeed on a Constitution saving throw or become poisoned until the end of your next turn and take "
                    + Math.max(0, iCharac.getModifier(Enumerations.Attributes.WIS)) + " poison damage.", "5ft", -1, dc, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Hand of Mercy", "As an action, you can touch a creature, expend 4 ki points, and force the creature to make a Constitution saving throw (a creature can willingly fail this save). Unless the save succeeds, the creature enters a state of suspended animation for a number of days equal to your monk level or until you end the effect early (no action required). During this time, the creature is paralyzed, has immunity to all damage, and any curse, disease, or poison affecting it is suspended. The creature appears dead to all outward inspection and to spells used to determine the creature’s status.\n" +
                    "\n" +
                    "You can have only one creature under the effect of this feature at a time.", "Touch", -1, dc, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
