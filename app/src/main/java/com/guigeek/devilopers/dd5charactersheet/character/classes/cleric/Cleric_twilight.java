package com.guigeek.devilopers.dd5charactersheet.character.classes.cleric;

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

public class Cleric_twilight extends BaseArchetype implements ClericDomain {
    static final long serialVersionUID = 2412L;

    @Override
    public String getName() {
        return App.getResString(R.string.cleric_twilight);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("You gain proficiency with martial weapons and heavy armor.");
            levelUp.add("Gained Eyes of Night");
            levelUp.add("Gained Vigilant Blessing");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained a Domain effect for Channel Divinity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Steps of the Brave");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Gained Divine Strike");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Gained Midnight Shroud");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Eyes of Night", "You have darkvision with no maximum range; you can see in dim light as if it were bright light and in darkness as if it were dim light.\n" +
                    "\n" +
                    "As an action, you can magically give the benefit of this feature to any number of creatures you can see within 10 feet of you. The shared benefit lasts for 10 minutes. You can extend this benefit a number of times equal to your Wisdom modifier (a minimum of once), and you regain all expended uses when you finish a long rest.", "10ft", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.ACTION));
            powers.add(new Power("Vigilant Blessing", "As an action, you give one creature you touch (including possibly yourself) advantage on the next initiative roll the creature makes. This benefit ends immediately after the roll or if you use this feature again.", "", -1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Steps of the Brave", "You draw strength from your connection to twilight and find yourself at home within its dark embrace, gaining two benefits:\n" +
                    "\n" +
                    "You have advantage on saving throws against being frightened.\n" +
                    "If you are in dim light or darkness, you can use a bonus action to magically give yourself a flying speed equal to your walking speed until the end of your next turn.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 8) {
            powers.add(new Power("Divine Strike", "Once on each of your turns when you hit a creature with a weapon attack, you can cause the attack to deal an extra " + (iLevel >= 14 ? 2 : 1) + "d8 psychic damage to the target. ", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 17) {
            powers.add(new Power("Midnight Shroud", "Whenever you cast the darkness spell using a spell slot, you can choose a number of creatures that you can see (including yourself) equal to your Wisdom modifier (minimum of one). The chosen creatures can see through the darkness.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public String getChannelDivinityEffects(int iLevel) {
        return iLevel >= 2 ? "[Twilight Sanctuary] As an action, you present your holy symbol, and a sphere of twilight emanates from you. The sphere is centered on you, has a 30-foot radius, and is filled with dim light. The sphere moves with you, and it lasts for 1 minute or until you are incapacitated or die. Whenever a creature (including you) ends its turn in the sphere, you can grant that creature one of these benefits:\n" +
                "\n" +
                "Give it 1d8 temporary hit points.\n" +
                "End one effect causing it to be charmed or frightened." : "";
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        if (classLevel >= 6) {
            fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.FEAR.ordinal()));
        }
        return fettles;
    }
}
