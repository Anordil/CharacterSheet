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

public class Fighter_archer extends BaseArchetype {
    static final long serialVersionUID = 2601L;
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
        return App.getResString(R.string.fighter_archer);
    }

    private boolean hasArcaneShot(String name) {
        for (Power p : _chosenFeatures) {
            if (p._name.equals(name)) {
                return true;
            }
        }
        return false;
    }


    private LinkedList<Power> getArcaneShotOptions(int iLevel) {
        LinkedList<Power> features = new LinkedList<>();
        if (iLevel == 3 || iLevel == 7 || iLevel == 10 || iLevel == 15 || iLevel == 18) {
            if (!hasArcaneShot("[Arcane Shot] Banishing Arrow")) {
                features.add(new Power("[Arcane Shot] Banishing Arrow", "You use abjuration magic to try to temporarily banish your target to a harmless location in the Feywild. The creature hit by the arrow must also succeed on a Charisma saving throw or be banished. While banished in this way, the target’s speed is 0, and it is incapacitated. At the end of its next turn, the target reappears in the space it vacated or in the nearest unoccupied space if that space is occupied.\n" +
                        "\n" +
                        "After you reach 18th level in this class, a target also takes 2d6 force damage when the arrow hits it.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (!hasArcaneShot("[Arcane Shot] Beguiling Arrow")) {
                features.add(new Power("[Arcane Shot] Beguiling Arrow", "Your enchantment magic causes this arrow to temporarily beguile its target. The creature hit by the arrow takes an extra 2d6 psychic damage, and choose one of your allies within 30 feet of the target. The target must succeed on a Wisdom saving throw, or it is charmed by the chosen ally until the start of your next turn. This effect ends early if the chosen ally attacks the charmed target, deals damage to it, or forces it to make a saving throw.\n" +
                        "\n" +
                        "The psychic damage increases to 4d6 when you reach 18th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (!hasArcaneShot("[Arcane Shot] Bursting Arrow")) {
                features.add(new Power("[Arcane Shot] Bursting Arrow", "You imbue your arrow with force energy drawn from the school of evocation. The energy detonates after your attack. Immediately after the arrow hits the creature, the target and all other creatures within 10 feet of it take 2d6 force damage each.\n" +
                        "\n" +
                        "The force damage increases to 4d6 when you reach 18th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (!hasArcaneShot("[Arcane Shot] Enfeebling Arrow")) {
                features.add(new Power("[Arcane Shot] Enfeebling Arrow", "You weave necromantic magic into your arrow. The creature hit by the arrow takes an extra 2d6 necrotic damage. The target must also succeed on a Constitution saving throw, or the damage dealt by its weapon attacks is halved until the start of your next turn.\n" +
                        "\n" +
                        "The necrotic damage increases to 4d6 when you reach 18th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (!hasArcaneShot("[Arcane Shot] Grasping Arrow")) {
                features.add(new Power("[Arcane Shot] Grasping Arrow", "When this arrow strikes its target, conjuration magic creates grasping, poisonous brambles, which wrap around the target. The creature hit by the arrow takes an extra 2d6 poison damage, its speed is reduced by 10 feet, and it takes 2d6 slashing damage the first time on each turn it moves 1 foot or more without teleporting. The target or any creature that can reach it can use its action to remove the brambles with a successful Strength (Athletics) check against your Arcane Shot save DC. Otherwise, the brambles last for 1 minute or until you use this option again.\n" +
                        "\n" +
                        "The poison damage and slashing damage both increase to 4d6 when you reach 18th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (!hasArcaneShot("[Arcane Shot] Piercing Arrow")) {
                features.add(new Power("[Arcane Shot] Piercing Arrow", "You use transmutation magic to give your arrow an ethereal quality. When you use this option, you don’t make an attack roll for the attack. Instead, the arrow shoots forward in a line, which is 1 foot wide and 30 feet long, before disappearing. The arrow passes harmlessly through objects, ignoring cover. Each creature in that line must make a Dexterity saving throw. On a failed save, a creature takes damage as if it were hit by the arrow, plus an extra 1d6 piercing damage. On a successful save, a target takes half as much damage.\n" +
                        "\n" +
                        "The piercing damage increases to 2d6 when you reach 18th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (!hasArcaneShot("[Arcane Shot] Seeking Arrow")) {
                features.add(new Power("[Arcane Shot] Seeking Arrow", "Using divination magic, you grant your arrow the ability to seek out a target. When you use this option, you don’t make an attack roll for the attack. Instead, choose one creature you have seen in the past minute. The arrow flies toward that creature, moving around corners if necessary and ignoring three-quarters cover and half cover. If the target is within the weapon’s range and there is a path large enough for the arrow to travel to the target, the target must make a Dexterity saving throw. Otherwise, the arrow disappears after traveling as far as it can. On a failed save, the target takes damage as if it were hit by the arrow, plus an extra 1d6 force damage, and you learn the target’s current location. On a successful save, the target takes half as much damage, and you don’t learn its location.\n" +
                        "\n" +
                        "The force damage increases to 2d6 when you reach 18th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
            if (!hasArcaneShot("[Arcane Shot] Shadow Arrow")) {
                features.add(new Power("[Arcane Shot] Shadow Arrow", "You weave illusion magic into your arrow, causing it to occlude your foe’s vision with shadows. The creature hit by the arrow takes an extra 2d6 psychic damage, and it must succeed on a Wisdom saving throw or be unable to see anything farther than 5 feet away until the start of your next turn.\n" +
                        "\n" +
                        "The psychic damage increases to 4d6 when you reach 18th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            }
        }


        return features;
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("You choose to gain proficiency in either the Arcana or the Nature skill, and you choose to learn either the prestidigitation or the druidcraft cantrip.");
            levelUp.add("Gained Arcane Shot");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Magic Arrow");
            levelUp.add("Gained Curving Shot");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Ever-Ready Shot");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Your Arcane Shots deal more damage!");
        }


        // Arcane shot options
        LinkedList<Power> features = getArcaneShotOptions(iNewCharacterLevel);
        final int level = iNewCharacterLevel;
        final Context c = context;
        if (features != null && !features.isEmpty()) {
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a new Arcane Shot" + (iNewCharacterLevel == 3 ? " (1/2)" : ""));

            final Object[] featsFiltered = features.toArray();

            b.setAdapter(new FeatAdapter(context, R.layout.list_feat, features), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Power feat = (Power)featsFiltered[which];
                    _chosenFeatures.add(feat);

                    if (level == 3) {
                        // 2nd option
                        AlertDialog.Builder bb = new AlertDialog.Builder(c);
                        LinkedList<Power> features2 = getArcaneShotOptions(level);
                        bb.setTitle("Select a new Arcane Shot (2/2)");
                        bb.setAdapter(new FeatAdapter(c, R.layout.list_feat, features2), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Power feat = (Power)featsFiltered[which];
                                _chosenFeatures.add(feat);
                            }
                        });
                        bb.show();
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
            powers.add(new Power("Arcane Shot", "Once per turn when you fire an arrow from a shortbow or longbow as part of the Attack action, you can apply one of your Arcane Shot options to that arrow. You decide to use the option when the arrow hits a creature, unless the option doesn’t involve an attack roll.", "", 2, 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.INT), false, Enumerations.ActionType.PASSIVE));
            powers.addAll(_chosenFeatures);
        }
        if (iLevel >= 7) {
            powers.add(new Power("Magic Arrow", "Whenever you fire a nonmagical arrow from a shortbow or longbow, you can make it magical for the purpose of overcoming resistance and immunity to nonmagical attacks and damage. The magic fades from the arrow immediately after it hits or misses its target.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Curving Shot", "When you make an attack roll with a magic arrow and miss, you can use a bonus action to reroll the attack roll against a different target within 60 feet of the original target.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Ever-Ready Shot", "If you roll initiative and have no uses of Arcane Shot remaining, you regain one use of it.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }



        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }

    public int nbOfFeatures(int iLevel) {
        return iLevel >= 18 ? 6 : iLevel >= 15 ? 5 : iLevel >= 10 ? 4 : iLevel >= 7 ? 3 : iLevel >= 3 ? 2 : 0;
    }

    public void doLevelDown(int inewLevel) {
        while(_chosenFeatures.size() > nbOfFeatures(inewLevel)) {
            _chosenFeatures.removeLast();
        }
    }
}
