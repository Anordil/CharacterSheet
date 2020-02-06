package com.guigeek.devilopers.dd5charactersheet.character.classes.druid;

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

public class Druid_spores extends BaseArchetype {
    static final long serialVersionUID = 2502L;

    @Override
    public String getName() {
        return App.getResString(R.string.druid_spores);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Halo of Spores");
            levelUp.add("Gained Symbiotic Entity");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Fungal Infestation");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Spreading Spores");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Fungal Body");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();
        int dc = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.WIS);
        if (iLevel >= 2) {

            String dmg = iLevel >= 14 ? "1d10" : iLevel >= 10 ? "1d8": iLevel >= 6 ? "1d6": "1d4";
            powers.add(new Power("Halo of Spores", "When a creature you can see moves into a space within 10 feet of you or starts its turn there, you can use your reaction to deal " + dmg + " necrotic damage to that creature unless it succeeds on a Constitution saving throw against your spell save DC.", "10ft", -1, dc, true, Enumerations.ActionType.REACTION));
            powers.add(new Power("Symbiotic Entity", "As an action, you can expend a use of your Wild Shape feature to awaken your spores, rather than transforming into a beast form, and you gain " + (4*iLevel) + " temporary hit points. While this feature is active, you gain the following benefits:\n" +
                    "\n" +
                    "When you deal your Halo of Spores damage, roll the damage die a second time and add it to the total.\n" +
                    "Your melee weapon attacks deal an extra 1d6 poison damage to any target they hit.\n" +
                    "These benefits last for 10 minutes, until you lose all these temporary hit points, or until you use your Wild Shape again.", "", -1, -1, true, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Fungal Infestation", "Your spores gain the ability to infest a corpse and animate it. If a beast or a humanoid that is Small or Medium dies within 10 feet of you, you can use your reaction to animate it, causing it to stand up immediately with 1 hit point. The creature uses the zombie stat block in the Monster Manual. It remains animate for 1 hour, after which time it collapses and dies.\n" +
                    "\n" +
                    "In combat, the zombie’s turn comes immediately after yours. It obeys your mental commands, and the only action it can take is the Attack action, making one melee attack.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Wisdom modifier (minimum of once), and you regain all expended uses of it when you finish a long rest.", "10ft", iCharac.getModifier(Enumerations.Attributes.WIS), -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Spreading Spores", "You gain the ability to seed an area with deadly spores. As a bonus action while your Symbiotic Entity feature is active, you can hurl spores up to 30 feet away, where they swirl in a 10-foot cube for 1 minute. The spores disappear early if you use this feature again, if you dismiss them as a bonus action, or if your Symbiotic Entity feature is no longer active.\n" +
                    "\n" +
                    "Whenever a creature moves into the cube or starts its turn there, that creature takes your Halo of Spores damage, unless the creature succeeds on a Constitution saving throw against your spell save DC. A creature can take this damage no more than once per turn.\n" +
                    "\n" +
                    "While the cube of spores persists, you can’t use your Halo of Spores reaction.", "30", -1, dc, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Fungal Body", "The fungal spores in your body alter you: you can’t be blinded, deafened, frightened, or poisoned, and any critical hit against you counts as a normal hit instead, unless you’re incapacitated.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        if (character._level >= 14) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.POISONED.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.BLINDED.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.DEAFENED.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.FEAR.ordinal()));
        }

        return fettles;
    }
}
