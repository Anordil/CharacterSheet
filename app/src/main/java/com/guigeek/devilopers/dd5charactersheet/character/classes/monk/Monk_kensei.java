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


public class Monk_kensei extends BaseArchetype {
    static final long serialVersionUID = 2706L;
    public Monk_kensei(){}

    @Override
    public String getName() {
        return App.getResString(R.string.monk_kensei);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Path of the Kensei (2 weapons)");
            levelUp.add("You gained proficiency in calligrapher's or painter's supplies.");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained One with the Blade");
            levelUp.add("Choose a 3rd Kensei weapon");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Sharpen the Blade");
            levelUp.add("Choose a 4th Kensei weapon");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You gained Unerring Accuracy");
            levelUp.add("Choose a 5th Kensei weapon");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            int kenseiWeapons = iLevel >= 17 ? 5 : iLevel >= 11 ? 4 : iLevel >= 6 ? 3 : 2;
            powers.add(new Power("Path of the Kensei",
                    "[Kensei Weapon] Choose " + kenseiWeapons + " types of weapons to be your kensei weapons: one melee weapon and one ranged weapon. Each of these weapons can be any simple or martial weapon that lacks the heavy and special properties. The longbow is also a valid choice. You gain proficiency with these weapons if you don’t already have it. Weapons of the chosen types are monk weapons for you. Many of this tradition’s features work only with your kensei weapons." +
                            "\n\n[Agile Parry] If you make an unarmed strike as part of the Attack action on your turn and are holding a kensei weapon, you can use it to defend yourself if it is a melee weapon. You gain a +2 bonus to AC until the start of your next turn, while the weapon is in your hand and you aren’t incapacitated." +
                            "\n\n[Kensei's Shot] You can use a bonus action on your turn to make your ranged attacks with a kensei weapon more deadly. When you do so, any target you hit with a ranged attack using a kensei weapon takes an extra 1d4 damage of the weapon’s type. You retain this benefit until the end of the current turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            String extraDmg = "1D" + ((Monk)iCharac._class).getMonkDamageDie(iLevel);
            powers.add(new Power("One With the Blade",
                    "[Magic Kensei Weapons] Your attacks with your kensei weapons count as magical for the purpose of overcoming resistance and immunity to nonmagical attacks and damage." +
                            "\n\n[Deft Strike] When you hit a target with a kensei weapon, you can spend 1 ki point to cause the weapon to deal extra " + extraDmg + " damage to the target. You can use this feature only once on each of your turns.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 11) {
            powers.add(new Power("Sharpen the Blade", "As a bonus action, you can expend up to 3 ki points to grant one kensei weapon you touch a bonus to attack and damage rolls when you attack with it. The bonus equals the number of ki points you spent. This bonus lasts for 1 minute or until you use this feature again. This feature has no effect on a magic weapon that already has a bonus to attack and damage rolls.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Unerring Accuracy", "If you miss with an attack roll using a monk weapon on your turn, you can reroll it. You can use this feature only once on each of your turns.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
