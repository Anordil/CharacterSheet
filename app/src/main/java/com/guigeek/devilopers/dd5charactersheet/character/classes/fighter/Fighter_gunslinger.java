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

public class Fighter_gunslinger extends BaseArchetype {
    static final long serialVersionUID = 2610L;

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_gunslinger);
    }

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

    String[][] maneuvers = new String[][]{
            new String[] {"[Trick Shot] Bullying Shot", "You can use the powerful blast and thundering sound of your firearm to shake the resolve of a creature. You can expend one grit point while making a Charisma (Intimidation) check to gain advantage on the roll."},
            new String[] {"[Trick Shot] Dazing Shot", "When you make a firearm attack against a creature, you can expend one grit point to attempt to dizzy your opponent. On a hit, the creature suffers normal damage and must make a Constitution saving throw or suffer disadvantage on attacks until the end of their next turn."},
            new String[] {"[Trick Shot] Deadeye Shot", "When you make a firearm attack against a creature, you can expend one grit point to gain advantage on the attack roll."},
            new String[] {"[Trick Shot] Disarming Shot", "When you make a firearm attack against a creature, you can expend one grit point to attempt to shoot an object from their hands. On a hit, the creature suffers normal damage and must succeed on a Strength saving throw or drop 1 held object of your choice and have that object be pushed 10 feet away from you."},
            new String[] {"[Trick Shot] Forceful Shot", "When you make a firearm attack against a creature, you can expend one grit point to attempt to trip them up and force them back. On a hit, the creature suffers normal damage and must succeed on a Strength saving throw or be pushed 15 feet away from you."},
            new String[] {"[Trick Shot] Piercing Shot", "When you make a firearm attack against a creature, you can expend one grit point to attempt to fire through multiple opponents. The initial attack gains a +1 to the firearm’s misfire score. On a hit, the creature suffers normal damage and you make an attack roll with disadvantage against every creature in a line directly behind the target within your first range increment. Only the initial attack can misfire."},
            new String[] {"[Trick Shot] Violent Shot", "When you make a firearm attack against a creature, you can expend one or more grit points to enhance the volatility of the attack. For each grit point expended, the attack gains a +2 to the firearm’s misfire score. If the attack hits, you can roll one additional weapon damage die per grit point spent when determining the damage."},
            new String[] {"[Trick Shot] Winging Shot", "When you make a firearm attack against a creature, you can expend one grit point to attempt to topple a moving target. On a hit, the creature suffers normal damage and must make a Strength saving throw or be knocked prone."}
    };

    private LinkedList<Power> getAvailabletrickshots() {
        LinkedList<Power> features = new LinkedList<>();
        manLoop: for (String[] maneuverRow : maneuvers) {
            for (Power p : _chosenFeatures) {
                if (p._name.equals(maneuverRow[0])) {
                    continue manLoop;
                }
            }

            features.add(new Power(maneuverRow[0], maneuverRow[1], "", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        return features;
    }

    public int nbOfFeatures(int iLevel) {
        return iLevel >= 18 ? 6 : iLevel >= 15 ? 5 : iLevel >= 10 ? 4 : iLevel >= 7 ? 3: iLevel >= 3 ? 2 : 0;
    }

    public void doLevelDown(int inewLevel) {
        while(_chosenFeatures.size() > nbOfFeatures(inewLevel)) {
            _chosenFeatures.removeLast();
        }
    }


    @Override
    public List<String> getLevelUpBenefits(final int iNewCharacterLevel, final Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("You gain proficiency in firearms.");
            levelUp.add("Gained Gunsmith");
            levelUp.add("Gained Adept Marksman");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Quickdraw");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Rapid Repair");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Lightning Reload");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Vicious Intent");
            levelUp.add("Gained Hemorrhaging Critical");
        }


        if (iNewCharacterLevel == 3 || iNewCharacterLevel == 7 || iNewCharacterLevel == 10 || iNewCharacterLevel == 15 || iNewCharacterLevel == 18) {
            LinkedList<Power> maneuvers = getAvailabletrickshots();
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a Trick shot" + (iNewCharacterLevel == 3 ? " (1/2)" : ""));
            final Object[] featsFiltered = maneuvers.toArray();

            b.setAdapter(new FeatAdapter(context, R.layout.list_feat, maneuvers), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Power feat = (Power)featsFiltered[which];
                    _chosenFeatures.add(feat);

                    // Second one
                    if (iNewCharacterLevel == 3) {
                        LinkedList<Power> maneuvers2 = getAvailabletrickshots();
                        AlertDialog.Builder b2 = new AlertDialog.Builder(context);
                        b2.setTitle("Select a Trick shot (2/2)");
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

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Gunsmith", "Upon choosing this archetype at 3rd level, you gain proficiency with Tinker’s Tools. You may use them to craft ammunition at half the cost, repair damaged firearms, or even draft and create new ones (DM’s discretion). Some extremely experimental and intricate firearms are only available through crafting.\n" +
                    "\n" +
                    "Firearm Properties\n" +
                    "Firearms are a new and volatile technology, and as such bring their own unique set of weapon properties. Some properties are followed by a number, and this number signifies an element of that property (outlined below). These properties replace the optional ones presented in the Dungeon Master’s Guide. Firearms are ranged weapons.\n" +
                    "\n" +
                    "[Reload] The weapon can be fired a number of times equal to its Reload score before you must spend 1 attack or 1 action to reload. You must have one free hand to reload a firearm.\n" +
                    "\n" +
                    "[Misfire] Whenever you make an attack roll with a firearm, and the dice roll is equal to or lower than the weapon’s Misfire score, the weapon misfires. The attack misses, and the weapon cannot be used again until you spend an action to try and repair it. To repair your firearm, you must make a successful Tinker’s Tools check (DC equal to 8 + misfire score). If your check fails, the weapon is broken and must be mended out of combat at a quarter of the cost of the firearm. Creatures who use a firearm without being proficient increase the weapon’s misfire score by 1.\n" +
                    "\n" +
                    "[Explosive] Upon a hit, everything within 5 ft of the target must make a Dexterity saving throw (DC equal to 8 + your proficiency bonus + your Dexterity modifier) or suffer 1d8 fire damage. If the weapon misses, the ammunition fails to detonate, or bounces away harmlessly before doing so.\n" +
                    "\n" +
                    "[Ammunition] " +
                    "All firearms require ammunition to make an attack, and due to their rare nature, ammunition may be near impossible to find or purchase. However, if materials are gathered, you can craft ammunition yourself using your Tinker’s Tools at half the cost. Each firearm uses its own unique ammunition and is generally sold or crafted in batches listed below next to the price.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));

            powers.add(new Power("Grit", "You gain a number of grit points equal to your Wisdom modifier (minimum of 1). You regain 1 expended grit point each time you roll a 20 on the d20 roll for an attack with a firearm, or deal a killing blow with a firearm to a creature of significant threat (DM’s discretion). You regain all expended grit points after a short or long rest." , "", Math.max(1, iCharac.getModifier(Enumerations.Attributes.WIS)), -1, false, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Adept Marksman", "You learn two trick shots of your choice, which are detailed under “Trick Shots” below. Many maneuvers enhance an attack in some way. Each use of a trick shot must be declared before the attack roll is made. You can use only one trick shot per attack."
                     , "", -1, 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.DEX), false, Enumerations.ActionType.PASSIVE));
            powers.addAll(_chosenFeatures);
        }
        if (iLevel >= 7) {
            powers.add(new Power("Quickdraw", "You add your proficiency bonus to your initiative. You can also stow a firearm, then draw another firearm as a single object interaction on your turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Rapid Repair", "You can spend a grit point to attempt to repair a misfired (but not broken) firearm as a bonus action.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Lightning Reload", "You can reload any firearm as a bonus action.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Vicious Intent", "Your firearm attacks score a critical hit on a roll of 19-20, and you regain a grit point on a roll of 19 or 20 on a d20 attack roll.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Hemorrhaging Critical", "Whenever you score a critical hit on an attack with a firearm, the target additionally suffers half of the damage from the attack at the end of its next turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }
}
