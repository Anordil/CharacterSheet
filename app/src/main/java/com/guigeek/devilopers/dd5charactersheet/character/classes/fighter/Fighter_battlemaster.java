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

public class Fighter_battlemaster extends BaseArchetype {
    static final long serialVersionUID = 2602L;

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

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_battlemaster);
    }

    public static int getSuperiorityDiceValue(int level) {
        return level >= 12 ? 12 : level >= 10 ? 10 : 8;
    }
    public static int getSuperiorityDiceCount(int level) {
        return level >= 15 ? 6 : level >= 7 ? 5 : 4;
    }

    public static String getSuperiorityDice(int level) {
        return getSuperiorityDiceCount(level) + "d" + getSuperiorityDiceValue(level);
    }

    String[][] maneuvers = new String[][]{
            new String[] {"[Maneuver] Commander’s Strike", "When you take the Attack action on your turn, you can forgo one of your attacks and use a bonus action to direct one of your companions to strike. When you do so, choose a friendly creature who can see or hear you and expend one superiority die. That creature can immediately use its reaction to make one weapon attack, adding the superiority die to the attack’s damage roll."},
            new String[] {"[Maneuver] Disarming Attack", "When you hit a creature with a weapon attack, you can expend one superiority die to attempt to disarm the target, forcing it to drop one item of your choice that it’s holding. You add the superiority die to the attack’s damage roll, and the target must make a Strength saving throw. On a failed save, it drops the object you choose. The object lands at its feet."},
            new String[] {"[Maneuver] Distracting Strike", "When you hit a creature with a weapon attack, you can expend one superiority die to distract the creature, giving your allies an opening. You add the superiority die to the attack’s damage roll. The next attack roll against the target by an attacker other than you has advantage if the attack is made before the start of your next turn."},
            new String[] {"[Maneuver] Evasive Footwork", "When you move, you can expend one superiority die, rolling the die and adding the number rolled to your AC until you stop moving."},
            new String[] {"[Maneuver] Feinting Attack", "You can expend one superiority die and use a bonus action on your turn to feint, choosing one creature within 5 feet of you as your target. You have advantage on your next attack roll against that creature this turn. If that attack hits, add the superiority die to the attack’s damage roll."},
            new String[] {"[Maneuver] Goading Attack", "When you hit a creature with a weapon attack, you can expend one superiority die to attempt to goad the target into attacking you. You add the superiority die to the attack’s damage roll, and the target must make a Wisdom saving throw. On a failed save, the target has disadvantage on all attack rolls against targets other than you until the end of your next turn."},
            new String[] {"[Maneuver] Lunging Attack", "When you make a melee weapon attack on your turn, you can expend one superiority die to increase your reach for that attack by 5 feet. If you hit, you add the superiority die to the attack’s damage roll."},
            new String[] {"[Maneuver] Maneuvering Attack", "When you hit a creature with a weapon attack, you can expend one superiority die to maneuver one of your comrades into a more advantageous position. You add the superiority die to the attack’s damage roll, and you choose a friendly creature who can see or hear you. That creature can use its reaction to move up to half its speed without provoking opportunity attacks from the target of your attack."},
            new String[] {"[Maneuver] Menacing Attack", "When you hit a creature with a weapon attack, you can expend one superiority die to attempt to frighten the target. You add the superiority die to the attack’s damage roll, and the target must make a Wisdom saving throw. On a failed save, it is frightened of you until the end of your next turn."},
            new String[] {"[Maneuver] Parry", "When another creature damages you with a melee attack, you can use your reaction and expend one superiority die to reduce the damage by the number you roll on your superiority die + your Dexterity modifier."},
            new String[] {"[Maneuver] Precision Attack", "When you make a weapon attack roll against a creature, you can expend one superiority die to add it to the roll. You can use this maneuver before or after making the attack roll, but before any effects of the attack are applied."},
            new String[] {"[Maneuver] Pushing Attack", "When you hit a creature with a weapon attack, you can expend one superiority die to attempt to drive the target back. You add the superiority die to the attack’s damage roll, and if the target is Large or smaller, it must make a Strength saving throw. On a failed save, you push the target up to 15 feet away from you."},
            new String[] {"[Maneuver] Rally", "On your turn, you can use a bonus action and expend one superiority die to bolster the resolve of one of your companions. When you do so, choose a friendly creature who can see or hear you. That creature gains temporary hit points equal to the superiority die roll + your Charisma modifier."},
            new String[] {"[Maneuver] Riposte", "When a creature misses you with a melee attack, you can use your reaction and expend one superiority die to make a melee weapon attack against the creature. If you hit, you add the superiority die to the attack’s damage roll."},
            new String[] {"[Maneuver] Sweeping Attack", "When you hit a creature with a melee weapon attack, you can expend one superiority die to attempt to damage another creature with the same attack. Choose another creature within 5 feet of the original target and within your reach. If the original attack roll would hit the second creature, it takes damage equal to the number you roll on your superiority die. The damage is of the same type dealt by the original attack."},
            new String[] {"[Maneuver] Trip Attack", "When you hit a creature with a weapon attack, you can expend one superiority die to attempt to knock the target down. You add the superiority die to the attack’s damage roll, and if the target is Large or smaller, it must make a Strength saving throw. On a failed save, you knock the target prone."}
    };

    private LinkedList<Power> getAvailableManeuvers() {
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
        return iLevel >= 15 ? 9 : iLevel >= 10 ? 7 : iLevel >= 7 ? 5: iLevel >= 3 ? 3 : 0;
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
            levelUp.add("Gained Combat Superiority");
            levelUp.add("Gained proficiency with one Artisan's tool.");
        }

        // Maneuvers
        if (iNewCharacterLevel == 3 || iNewCharacterLevel == 7 || iNewCharacterLevel == 10 || iNewCharacterLevel == 15) {
            LinkedList<Power> maneuvers = getAvailableManeuvers();
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a Maneuver (1/" + (iNewCharacterLevel == 3 ? 3 : 2) + ")");
            final Object[] featsFiltered = maneuvers.toArray();

            b.setAdapter(new FeatAdapter(context, R.layout.list_feat, maneuvers), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Power feat = (Power)featsFiltered[which];
                    _chosenFeatures.add(feat);

                    // Second one
                    LinkedList<Power> maneuvers2 = getAvailableManeuvers();
                    AlertDialog.Builder b2 = new AlertDialog.Builder(context);
                    b2.setTitle("Select a Maneuver (2/" + (iNewCharacterLevel == 3 ? 3 : 2) + ")");
                    final Object[] featsFiltered2= maneuvers2.toArray();

                    b2.setAdapter(new FeatAdapter(context, R.layout.list_feat, maneuvers2), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Power feat = (Power) featsFiltered2[which];
                            _chosenFeatures.add(feat);

                            // 3rd maneuver -> level 3 only
                            if (iNewCharacterLevel == 3) {
                                LinkedList<Power> maneuvers3 = getAvailableManeuvers();
                                AlertDialog.Builder b3 = new AlertDialog.Builder(context);
                                b3.setTitle("Select a Maneuver (3/3)");
                                final Object[] featsFiltered3 = maneuvers3.toArray();

                                b3.setAdapter(new FeatAdapter(context, R.layout.list_feat, maneuvers3), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Power feat = (Power) featsFiltered3[which];
                                        _chosenFeatures.add(feat);
                                    }
                                });

                                b3.show();
                            }
                        }
                    });

                    b2.show();
                }
            });

            b.show();
        }

        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Know Your Enemy");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Relentless");
        }


        if (iNewCharacterLevel == 10 || iNewCharacterLevel == 18) {
            levelUp.add("Your superiority dice improved.");
        }
        if (iNewCharacterLevel == 7 || iNewCharacterLevel == 15) {
            levelUp.add("Gained another superiority dice.");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Combat Superiority", "You learn maneuvers that are fueled by special dice called superiority dice. Your superiority dice pool is " + getSuperiorityDice(iLevel) + ".", "", getSuperiorityDiceCount(iLevel), 8 + iCharac.getProficiencyBonus() + Math.max(iCharac.getModifier(Enumerations.Attributes.STR), iCharac.getModifier(Enumerations.Attributes.DEX)), true, Enumerations.ActionType.PASSIVE));
            powers.addAll(_chosenFeatures);
        }
        if (iLevel >= 7) {
            powers.add(new Power("Know Your Enemy", "If you spend at least 1 minute observing or interacting with another creature outside combat, you can learn certain information about its capabilities compared to your own. The DM tells you if the creature is your equal, superior, or inferior in regard to two of the following characteristics of your choice:\n" +
                    "\n" +
                    "Strength score\n" +
                    "Dexterity score\n" +
                    "Constitution score\n" +
                    "Armor Class\n" +
                    "Current hit points\n" +
                    "Total class levels (if any)\n" +
                    "Fighter class levels (if any)", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Relentless", "When you roll initiative and have no superiority dice remaining, you regain one superiority die.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
