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

public class Fighter_psychic extends BaseArchetype {
    static final long serialVersionUID = 2606L;

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_psychic);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Psionic Armament");
            levelUp.add("Gained Telekinetic Hand");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Strength of Mind");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Telekinetic Bulwark");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Agonizing Strikes");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Psychic Dreadnaught");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Psionic Armament", "You can channel your psychic power to magically augment your prowess. When you finish a long rest, choose whether to augment your defenses or your strikes. The chosen benefit lasts until you finish a long rest.\n" +
                    "\n" +
                    "[Augmented Defenses] " +
                    "When you or a creature you can see within 30 feet of you takes damage, you can use your reaction to roll a d" + (iLevel >= 10 ? 12 : 10) + " and reduce the amount of damage taken by the number rolled.\n" +
                    "\n" +
                    "[Augmented Strikes] " +
                    "Once during each of your turns when you hit a creature with a weapon attack, you can also deal " + (iLevel >= 10 ? "1d6" : "1d4") + " psychic damage to that target.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Telekinetic Hand", "You learn the mage hand cantrip. You can cast it without components, and you can make the spectral hand invisible. Intelligence is your spellcasting ability for this spell.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Strength of Mind", "As a bonus action, you can telekinetically lash out at a creature you can see within 20 feet of you. The target must make a Strength saving throw. On a failed save, the target takes force damage equal to 2d6 plus your Intelligence modifier and is telekinetically moved 15 feet directly toward or away from you (your choice). On a successful save, it takes half as much damage and isn’t moved.", "20ft", iCharac.getModifier(Enumerations.Attributes.INT), 8+iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.INT), true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Telekinetic Bulwark", "When you take the Attack action, you can forgo one of your attacks to project a bastion of psionic power in a 10-foot radius around yourself. It lasts for 1 minute or until you’re incapacitated. For the duration, you and your allies in that area gain the benefits of half cover and have advantage on Strength saving throws.\n" +
                    "\n" +
                    "Once you use this feature, you can’t do so again until you finish a long rest or until after you use your Second Wind feature.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Agonizing Strikes", "Your attacks can channel psychic agony. When you hit a creature with a weapon attack, you can also deal 2d10 psychic damage to that target and force it to make a Constitution saving throw. Unless the save succeeds, the target falls prone, and it suffers disadvantage on ability checks until the end of your next turn.", "", iCharac.getModifier(Enumerations.Attributes.INT), 8+iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.INT), true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Psychic Dreadnaught", "Using your reaction when you take damage, you can give yourself the following benefits for 1 minute or until you’re incapacitated:\n" +
                    "\n" +
                    " - At the start of each of your turns, you regain 10 hit points.\n" +
                    " - Your walking speed increases by 10 feet.\n" +
                    " - If you’re prone, you can stand up by spending 5 feet of movement.", "", 1, -1, true, Enumerations.ActionType.REACTION));
        }


        return powers;
    }
}
