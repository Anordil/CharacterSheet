package com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;


public class Barbarian_zealot extends BaseArchetype {
    static final long serialVersionUID = 2105L;
    public Barbarian_zealot(){}

    @Override
    public String getName() {
        return App.getResString(R.string.barbarian_zealot);
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Divine Fury");
            levelUp.add("You gained Warrior of the Gods");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You gained Fanatical Focus");
        }
        else if (iNewCharacterLevel == 10) {
            levelUp.add("You gained Zealous Presence");
        }
        else if (iNewCharacterLevel == 14) {
            levelUp.add("You gained Rage Beyond Death");
        }
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        if (iLevel >= 3) {
            powers.add(new Power("Divine Fury", "While you’re raging, the first creature you hit on each of your turns with a weapon attack takes extra " + getSpecialDamage(iLevel), "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Warrior of the Gods", "If a spell, such as raise dead, has the sole effect of restoring you to life (but not undeath), the caster doesn’t need material components to cast the spell on you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Fanatical Focus", "If you fail a saving throw while you’re raging, you can reroll it, and you must use the new roll. You can use this ability only once per rage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Zealous Presence", "As a bonus action, you unleash a battle cry infused with divine energy. Up to ten other creatures of your choice within 60 feet of you that can hear you gain advantage on attack rolls and saving throws until the start of your next turn.", "", 1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Rage Beyond Death", "While you’re raging, having 0 hit points doesn’t knock you unconscious. You still must make death saving throws, and you suffer the normal effects of taking damage while at 0 hit points. However, if you would die due to failing death saving throws, you don’t die until your rage ends, and you die then only if you still have 0 hit points.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    private String getSpecialDamage(int iLevel) {
        int dmg = (int)Math.floor(iLevel/2);
        return "1D6" + (dmg > 0 ? " +" + dmg : dmg < 0 ? " " + dmg : "") + " (" + (_chosenStringFeature.equals("Radiant") ? "Radiant" : "Necrotic") + ")";
    }

    @Override
    public int getChoosableFeature(int iLevel) {
        if (iLevel == 3) {
            return R.array.barbarianZealotDamage;
        }
        return -1;
    }
}
