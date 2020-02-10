package com.guigeek.devilopers.dd5charactersheet.character.classes.fighter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

public class Fighter_rune extends BaseArchetype {
    static final long serialVersionUID = 2608L;

    protected LinkedList<Power> _chosenFeatures = new LinkedList<>();

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        super.writeExternal(objectOutput);
        objectOutput.writeObject(_chosenFeatures);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        super.readExternal(objectInput);
        _chosenFeatures = (LinkedList<Power>) objectInput.readObject();
    }

    public void doLevelDown(int inewLevel) {
        while(_chosenFeatures.size() > knownRunesCount(inewLevel)) {
            _chosenFeatures.removeLast();
        }
    }

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_rune);
    }


    String[][] _runes = new String[][]{
            new String[] {"[Rune] Haug (Hill Rune)", "This rune’s magic bestows a resilience reminiscent of a hill giant. While wearing or carrying an object inscribed with this rune, you have advantage on saving throws against being poisoned, and you have resistance against poison damage.\n" +
                    "\n" +
                    "In addition, you can invoke the rune as a bonus action, gaining resistance to bludgeoning, piercing, and slashing damage for 1 minute. Once you invoke the rune, you can’t do so again until you finish a short or long rest."},
            new String[] {"[Rune] Ild (Fire Rune)", "This rune’s magic channels the masterful craftsmanship of fire giant smiths. While wearing or carrying an object inscribed with this rune, your proficiency bonus is doubled for any ability check you make that uses your proficiency with a tool.\n" +
                    "\n" +
                    "In addition, when you hit a creature with a weapon attack, you can invoke the rune to summon fiery shackles: the target must succeed on a Strength saving throw or be restrained for 1 minute. While restrained by the shackles, the target takes 2d6 fire damage at the start of each of its turns. The target can repeat the saving throw at the end of each of its turns, banishing the shackles on a success. Once you invoke the rune, you can’t do so again until you finish a short or long rest."},
            new String[] {"[Rune] Ise (Frost Rune)", "This rune’s magic evokes a frost giant’s stoic calm. While wearing or carrying an object inscribed with this rune, you have advantage on Wisdom (Animal Handling) checks and Charisma (Intimidation) checks.\n" +
                    "\n" +
                    "In addition, you can invoke the rune as a bonus action to increase your Strength score by 2 for 10 minutes. This increase can cause your score to exceed 20, but not 30. Once you invoke the rune, you can’t do so again until you finish a short or long rest."},
            new String[] {"[Rune] Skye (Cloud Rune)", "This rune’s magic emulates the deceptiveness of a cloud giant. While wearing or carrying an object inscribed with this rune, you have advantage on Dexterity (Sleight of Hand) checks and Charisma (Deception) checks.\n" +
                    "\n" +
                    "In addition, when you or a creature you can see within 30 feet of you is hit by an attack roll, you can use your reaction to invoke the rune and cause that attack to target a different creature within 30 feet of you (other than the attacker), using the same roll. This magic can transfer the attack regardless of the attack’s range. Once you invoke the rune, you can’t do so again until you finish a short or long rest."},
            new String[] {"[Rune] Stein (Stone Rune)", "This rune’s magic channels the insightfulness of a stone giant. While wearing or carrying an object inscribed with this rune, you have advantage on Wisdom (Insight) checks, and you have darkvision out to a range of 60 feet. If you already have darkvision, its range increases by 30 feet.\n" +
                    "\n" +
                    "In addition, when a creature you can see ends its turn within 30 feet of you, you can use your reaction to invoke the rune and force the creature to make a Wisdom saving throw. Unless the save succeeds, the creature is charmed by you for 1 minute. While charmed in this way, the creature has a speed of 0 and is incapacitated, descending into a dreamy stupor. The effect ends if the charmed creature takes any damage or if someone else uses an action to shake the creature out of its haze. Once you invoke the rune, you can’t do so again until you finish a short or long rest."},
            new String[] {"[Rune] Uvar (Storm Rune)", "Using this rune, you can glimpse the future like a storm giant. While wearing or carrying an object inscribed with this rune, you have advantage on Intelligence (Arcana) checks, and you can’t be surprised as long as you are not incapacitated.\n" +
                    "\n" +
                    "In addition, you can invoke the rune as a bonus action to enter a prophetic state for 1 minute or until you’re incapacitated. Until the state ends, when you or another creature you can see within 60 feet of you makes an attack roll, a saving throw, or an ability check, you can use your reaction to cause the roll to have advantage or disadvantage. Once you invoke the rune, you can’t do so again until you finish a short or long rest."},

    };

    private LinkedList<Power> getAvailableRunes() {
        LinkedList<Power> features = new LinkedList<>();
        manLoop: for (String[] maneuverRow : _runes) {
            for (Power p : _chosenFeatures) {
                if (p._name.equals(maneuverRow[0])) {
                    continue manLoop;
                }
            }

            features.add(new Power(maneuverRow[0], maneuverRow[1], "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        return features;
    }


    @Override
    public List<String> getLevelUpBenefits(final int iNewCharacterLevel, final Context context) {
        final List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gain proficiency with smith’s tools, and you learn to speak, read, and write Giant.");
            levelUp.add("Gained Rune Magic");
            levelUp.add("Gained Giant Might");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Defensive Runes");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Great Stature");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Rune Magic Mastery");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Blessing of the All Father");
        }


        if (iNewCharacterLevel == 3 || iNewCharacterLevel == 7 || iNewCharacterLevel == 10 || iNewCharacterLevel == 15) {
            LinkedList<Power> maneuvers = getAvailableRunes();
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a Rune " + (iNewCharacterLevel == 3 ? " (1/2)" : ""));
            final Object[] featsFiltered = maneuvers.toArray();

            b.setAdapter(new FeatAdapter(context, R.layout.list_feat, maneuvers), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Power feat = (Power)featsFiltered[which];
                    _chosenFeatures.add(feat);

                    // Second one at 3rd level
                    if (iNewCharacterLevel == 3) {
                        LinkedList<Power> maneuvers2 = getAvailableRunes();
                        AlertDialog.Builder b2 = new AlertDialog.Builder(context);
                        b2.setTitle("Select a Rune (2/2)");
                        final Object[] featsFiltered2 = maneuvers2.toArray();

                        b2.setAdapter(new FeatAdapter(context, R.layout.list_feat, maneuvers2), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Power feat = (Power) featsFiltered2[which];
                                _chosenFeatures.add(feat);
                            }
                        });

                        b2.show();
                    }
                }
            });

            b.show();
        }




        return levelUp;
    }

    public static int knownRunesCount(int level) {
        return level >= 15 ? 5 : level >= 10 ? 4 : level >= 7 ? 3 : 2;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Rune Magic", "" +
                    "[Known Runes] " + knownRunesCount(iLevel) +
                    "\n\nYou learn how to use runes to enhance your gear. When you gain this feature, you learn how to inscribe two runes of your choice on weapons, armor, or shields (see “Rune Options”).\n" +
                    "\n" +
                    "Whenever you finish a long rest, you can touch a number of objects equal to the number of runes you know, and you inscribe a different rune onto each of the objects. To be eligible, an object must be a weapon, a suit of armor, or a shield.\n" +
                    "\n" +
                    "Your rune remains on an object until you finish a long rest, and an object can bear only one of your runes.\n" +
                    "\n" +
                    "Each time you gain a level in this class, you can replace one rune you know with a different one.", "", -1, 8+iCharac.getProficiencyBonus()+iCharac.getModifier(Enumerations.Attributes.INT), true, Enumerations.ActionType.PASSIVE));
            powers.addAll(_chosenFeatures);
            powers.add(new Power("Giant Might", "You can imbue yourself with the might of giants. As a bonus action, you magically gain the following benefits, which last for 1 minute:\n" +
                    "\n" +
                    "If you are smaller than Large, you become Large, along with anything you are wearing. If you lack the room to become Large, your size doesn’t change.\n" +
                    "You have advantage on Strength checks and Strength saving throws.\n" +
                    "Your weapon attacks deal an extra " + (iLevel >= 10 ? "1d8" : "1d6") + " damage.", "", 2, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Defensive Runes", "You learn to invoke your rune magic to protect your allies. When another creature you can see within 60 feet of you is hit by an attack roll, you can use your reaction to grant a bonus to the creature’s AC against that attack. The bonus equals 1 + your Intelligence modifier (minimum of +2).", "", -1, -1, true, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Great Stature", "The magic of your runes permanently alters you. When you gain this feature, roll 3d4. You grow a number of inches in height equal to the roll. Moreover, the extra damage you deal with your Giant Might feature increases to 1d8.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Rune Magic Mastery", "You can invoke each rune you know from your Rune Magic feature twice, rather than once, and you regain all expended uses when you finish a short or long rest.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Blessing of the All Father", "You learn how to share your rune magic with your allies. When you use your Giant Might feature, you can choose one willing creature you can see within 60 feet of you. The chosen creature also gains the benefits of your Giant Might feature.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
