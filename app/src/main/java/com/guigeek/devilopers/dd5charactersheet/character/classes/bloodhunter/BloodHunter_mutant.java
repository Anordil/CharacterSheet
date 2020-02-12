package com.guigeek.devilopers.dd5charactersheet.character.classes.bloodhunter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.android.StringListAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BloodHunter_mutant extends BaseArchetype {
    static final long serialVersionUID = 2302L;

    @Override
    public String getName() {
        String name =  App.getResString(R.string.bloodhunter_mutant);
        if (_chosenStringFeature != null) {
            name += " - " + _chosenStringFeature;
        }
        return name;
    }


    Power[] mutagens = new Power[] {
            new Power("Aether","Prerequisite: 11th level\n" +
                    "\n" +
                    "You gain a flying speed of 20 feet.\n" +
                    "\n" +
                    "Side effect: You have disadvantage on all Strength and Dexterity ability checks.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Celerity","Your Dexterity score increases by an amount equal to your mutation score, as does your Dexterity maximum.\n" +
                    "\n" +
                    "Side effect: Your Wisdom score decreases by amount equal to your mutation score.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Conversant","You gain advantage on Intelligence ability checks.\n" +
                    "\n" +
                    "Side effect: You have disadvantage on Charisma ability checks.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Cruelty","Prerequisite: 11th level\n" +
                    "\n" +
                    "You can make a single weapon attack as a bonus action on each of your turns.\n" +
                    "\n" +
                    "Side effect: You have disadvantage on all Intelligence, Wisdom, and Charisma saving throws.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Impermeable","You gain resistance to piercing damage.\n" +
                    "\n" +
                    "Side effect: You gain vulnerability to slashing damage.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Mobility","You gain immunity to the grappled and restrained conditions. At 11th level, you also are immune to the paralyzed condition.  \n" +
                    "\n" +
                    "Side effect: You gain a penalty to initiative equal to 2 times your mutation score.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Nighteye","You gain darkvision for up to 60 feet. If you already have darkvision, this increases its range by 60 additional feet.\n" +
                    "\n" +
                    "Side effect: You gain sunlight sensitivity:\n" +
                    "\n" +
                    "You have disadvantage on attack rolls and on Wisdom (Perception) checks that rely on sight when you, the target of your attack, or whatever you are trying to perceive is in direct sunlight.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Potency","Your Strength score increases by an amount equal to your mutation score, as does your Strength maximum.\n" +
                    "\n" +
                    "Side effect: You have disadvantage on all Dexterity saving throws.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Precision","Prerequisite: 11th level\n" +
                    "\n" +
                    "Your weapon attacks score a critical hit on a roll of 19-20.\n" +
                    "\n" +
                    "Side effect: All healing you receive is halved.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Rapidity","Your speed increases by 15 feet. At 15th level, your speed increases by 20 feet instead.\n" +
                    "\n" +
                    "Side effect:  You have disadvantage on Dexterity ability checks.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Reconstruction","Prerequisite: 7th level\n" +
                    "\n" +
                    "While conscious and in combat, you regenerate hit points equal to 2 times your mutation score at the start of your turn as long as you are above 0 hit points.\n" +
                    "\n" +
                    "Side effect: Your speed decreases by 10 ft.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Sagacity","Your Wisdom score increases by an amount equal to your mutation score, as does your Wisdom maximum.\n" +
                    "\n" +
                    "Side effect: Your armor class is reduced by an amount equal to your mutation score.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Shielded","You gain resistance to slashing damage.\n" +
                    "\n" +
                    "Side effect: You gain vulnerability to bludgeoning damage.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Unbreakable","You gain resistance to bludgeoning damage.\n" +
                    "\n" +
                    "Side effect: You gain vulnerability to piercing damage.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("Wariness","You gain a bonus to initiative equal to 2 times your mutation score.\n" +
                    "\n" +
                    "Side effect: You have disadvantage on Wisdom (Perception) checks.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
    };

    @Override
    public int gainedArchetypeFeatures(int classLevel) {
        if (classLevel == 3) {
            return 3;
        } else if (classLevel == 7 || classLevel == 11 || classLevel == 15 || classLevel == 18) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Power> getAllArchetypeFeatures(int iClassLevel) {
        return Arrays.asList(mutagens);
    }

    @Override
    public boolean canReplaceFeature(int iClasseLevel) {
        return (iClasseLevel == 7 || iClasseLevel == 11 || iClasseLevel == 15 || iClasseLevel == 18);
    }

    @Override
    public int nbOfFeatures(int iLevel) {
        return iLevel >= 18 ? 7 : iLevel >= 15 ? 6 : iLevel >= 11 ? 5 : iLevel >= 7 ? 4 : iLevel >= 3 ? 3 : 0;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Mutagen Craft");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You gained Advanced Mutagen Craft");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You gained Strange Metabolism");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You can now create 3 mutagens/rest ");
            levelUp.add("You gained Robust Physiology");
        }
        else if (iNewCharacterLevel == 18) {
            levelUp.add("You gained Exalted Mutation");
            selectPermanentMutagen(context);
        }

        return levelUp;
    }

    @Override
    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();
        if (iLevel >= 3) {
            int mutagenCount = iLevel >= 15 ? 3 : 2;
            powers.add(new Power(iLevel >= 7 ? "Advanced Mutagen Craft": "Mutagen Craft","You can take a short rest to concoct " +
                    (iLevel >= 7 ? mutagenCount + " mutagens. Mutagens must be different formulas, and can be ingested with overlapping effects that last until you finish your next short or long rest (or use an action to flush them). Each mutagen still takes a separate bonus action to imbibe." : "a single mutagen. Consuming a single mutagen requires a bonus action, and the effects (including side effects) last until you complete a short or long rest, or spend an action to focus and flush the toxins from your system.") +
                    "\n" +
                    "\n" +
                    "Mutagens are designed for your biology. They have no effect on large or larger creatures, and only impart the side effects on other medium or smaller creatures that drink the entire mutagen. They are also unstable by nature, losing their potency over time and becoming inert if not swallowed before you finish your next short or long rest.\n" +
                    "\n" +
                    "Your body will begin to better utilize the toxins you instill it with as you grow in power and experience. These advancing mutations may be signified by a Mutation score.\n" +
                    "\n" +
                    "[Mutation Score] = " + (int)Math.ceil((double)(iLevel)/4), "", -1, -1, false, Enumerations.ActionType.PASSIVE));
            powers.addAll(_chosenFeatures);
        }
        if (iLevel >= 11) {
            powers.add(new Power("Strange Metabolism","You can use a bonus action to instill a burst of adrenaline to temporarily resist the negative effects of a mutagen. You can choose to ignore the side effect of a mutagen affecting you for 1 minute.\n" +
                    "\n" +
                    "Once you use this feature, you must finish a short or long rest before you can use it again.", "", 1, -1, false, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Robust Physiology","You gain immunity to poison damage and the poisoned condition.", "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Exalted Mutation","Choose one of your known mutagen formulas. You gain the benefits and side effects of this mutagen permanently, at all times. You cannot change this choice of formula after this feature is acquired." +
                    "\n\nChosen mutagen: " + _chosenStringFeature, "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }
        return powers;
    }

    protected void selectPermanentMutagen(Context context) {
        AlertDialog.Builder b = new AlertDialog.Builder(context);
        b.setTitle("Select a Mutagen to always be active");

        final List<Power> choices = new LinkedList<>();
        for (Power mutagen: _chosenFeatures) {
            choices.add(new Power(mutagen._name, mutagen._description, "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        b.setAdapter(new FeatAdapter(context, R.layout.list_feat, choices), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                setArchetypeStringFeature(choices.get(which)._name);
            }
        });

        b.show();
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int level) {
        LinkedList<Fettle> fettles = new LinkedList<>();

        if (level >= 15) {
            fettles.add(new Fettle(Enumerations.FettleType.IMMUNITY, 0, Enumerations.DamageTypes.POISON.ordinal()));
        }

        return  fettles;
    }
}
