package com.guigeek.devilopers.dd5charactersheet.character.classes.wizard;

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

public class Wizard_evocation extends BaseArchetype {
    static final long serialVersionUID = 2906L;

    @Override
    public String getName() {
        return App.getResString(R.string.wizard_evocation);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Sculpt Spells");
            levelUp.add("Gained School Savant");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Potent Cantrip");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Empowered Evocation");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Overchannel");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("School Savant", "The gold and time you spend to copy a spell into your spellbook is halved for spells of your chosen school.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Sculpt Spells", "Wou can create pockets of relative safety within the effects of your evocation spells. When you cast an evocation spell that affects other creatures that you can see, you can choose a number of them equal to 1 + the spell’s level. The chosen creatures automatically succeed on their saving throws against the spell, and they take no damage if they would normally take half damage on a successful save.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Potent Cantrip", "Your damaging cantrips affect even creatures that avoid the brunt of the effect. When a creature succeeds on a saving throw against your cantrip, the creature takes half the cantrip’s damage (if any) but suffers no additional effect from the cantrip.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Empowered Evocation", "You can add your Intelligence modifier to one damage roll of any wizard evocation spell you cast.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Overchannel", "When you cast a wizard spell of 1st through 5th level that deals damage, you can deal maximum damage with that spell.\n" +
                    "\n" +
                    "The first time you do so, you suffer no adverse effect. If you use this feature again before you finish a long rest, you take 2d12 necrotic damage for each level of the spell, immediately after you cast it. Each time you use this feature again before finishing a long rest, the necrotic damage per spell level increases by 1d12. This damage ignores resistance and immunity.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
