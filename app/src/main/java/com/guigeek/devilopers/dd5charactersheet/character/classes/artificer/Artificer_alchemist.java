package com.guigeek.devilopers.dd5charactersheet.character.classes.artificer;

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

public class Artificer_alchemist extends BaseArchetype {
    static final long serialVersionUID = 2002L;

    @Override
    public String getName() {
        return App.getResString(R.string.artificer_alchemist);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained proficiency with alchemist’s supplies. If you already have this proficiency, you gain proficiency with one other type of artisan’s tools of your choice.");
            levelUp.add("Gained Experimental Elixir");
        }
        if (iNewCharacterLevel == 5) {
            levelUp.add("Gained Alchemical Savant");
        }
        if (iNewCharacterLevel == 9) {
            levelUp.add("Gained Restorative Reagents");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Chemical Mastery");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();


        if (iLevel >= 3) {
            powers.add(new Power("Experimental Elixir", "Whenever you finish a long rest, you can magically produce an experimental elixir in an empty flask you touch. Roll on the Experimental Elixir table for the elixir’s effect, which is triggered when someone drinks the elixir. As an action, a creature can drink the elixir or administer it to an incapacitated creature.\n" +
                    "\n" +
                    "Creating an experimental elixir requires you to have alchemist’s supplies on your person, and any elixir you create with this feature lasts until it is drunk or until the end of your next long rest.\n" +
                    "\n" +
                    "When you reach certain levels in this class, you can make more elixirs at the end of a long rest: two at 6th level and three at 15th level. Roll for each elixir’s effect separately. Each elixir requires its own flask.\n" +
                    "\n" +
                    "You can create additional experimental elixirs by expending a spell slot of 1st level or higher for each one. When you do so, you use your action to create the elixir in an empty flask you touch, and you choose the elixir’s effect from the Experimental Elixir table.\n\n" +
                    "[1] Healing. The drinker regains a number of hit points equal to 2d4 + your Intelligence modifier.\n" +
                    "[2] Swiftness. The drinker’s walking speed increases by 10 feet for 1 hour.\n" +
                    "[3] Resilience. The drinker gains a +1 bonus to AC for 10 minutes.\n" +
                    "[4] Boldness. The drinker can roll a d4 and add the number rolled to every attack roll and saving throw they make for the next minute.\n" +
                    "[5] Flight. The drinker gains a flying speed of 10 feet for 10 minutes.\n" +
                    "[6] Transformation. The drinker’s body is transformed as if by the alter self spell. The drinker determines the transformation caused by the spell, the effects of which last for 10 minutes.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 5) {
            powers.add(new Power("Alchemical Savant", "Whenever you cast a spell using your alchemist’s supplies as the spellcasting focus, you gain a bonus to one roll of the spell. That roll must restore hit points or be a damage roll that deals acid, fire, necrotic, or poison damage, and the bonus equals your Intelligence modifier (minimum of +1).", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 9) {
            powers.add(new Power("Restorative Reagents", "Whenever a creature drinks an experimental elixir you created, the creature gains temporary hit points equal to 2d6 + your Intelligence modifier (minimum of 1 temporary hit point).\nYou can cast lesser restoration without expending a spell slot and without preparing the spell, provided you use alchemist’s supplies as the spellcasting focus. You can do so a number of times equal to your Intelligence modifier (minimum of once), and you regain all expended uses when you finish a long rest.", "", iCharac.getModifier(Enumerations.Attributes.INT), -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Chemical Mastery", "You gain resistance to acid damage and poison damage, and you are immune to the poisoned condition.\n" +
                    "You can cast greater restoration and heal without expending a spell slot, without preparing the spell, and without material components, provided you use alchemist’s supplies as the spellcasting focus. Once you cast either spell with this feature, you can’t cast that spell with it again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (classLevel >= 15) {
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.ACID.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.POISON.ordinal()));
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.Immunities.POISONED.ordinal()));
        }

        return fettles;
    }

    @Override
    public String getDescription() {
        return "An Alchemist is an expert at combining reagents to produce mystical effects. Alchemists use their creations to give life and to leech it away. Alchemy is the oldest of artificer traditions, and its versatility has long been valued during times of war and peace.";
    }
}
