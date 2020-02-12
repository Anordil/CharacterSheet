package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.android.FeatsScreen;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.sorcerer.Sorcerer;
import com.guigeek.devilopers.dd5charactersheet.character.classes.warlock.Warlock;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Monk_elements extends BaseArchetype {
    static final long serialVersionUID = 2704L;

    @Override
    public String getName() {
        return App.getResString(R.string.monk_elements);
    }


    Power[] disciplines = new Power[] {
            new Power("[Discipline] Breath of Winter",         "(17th level required)\nYou can spend 6 ki points to cast cone of cold.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Clench of the North Wind",         "(6th level required)\nYou can spend 3 ki points to cast hold person.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Eternal Mountain Defense",         "(17th level required)\nYou can spend 5 ki points to cast stoneskin, targeting yourself.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Fangs of the Fire Snake",         "When you use the Attack action on your turn, you can spend 1 ki point to cause tendrils of flame to stretch out from your fists and feet. Your reach with your unarmed strikes increases by 10 feet for that action, as well as the rest of the turn. A hit with such an attack deals fire damage instead of bludgeoning damage, and if you spend 1 ki point when the attack hits, it also deals an extra 1d10 fire damage.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Fist of Four Thunders",         "You can spend 2 ki points to cast thunderwave.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Fist of Unbroken Air",         "You can create a blast of compressed air that strikes like a mighty fist. As an action, you can spend 2 ki points and choose a creature within 30 feet of you. That creature must make a Strength saving throw. On a failed save, the creature takes 3d10 bludgeoning damage, plus an extra 1d10 bludgeoning damage for each additional ki point you spend, and you can push the creature up to 20 feet away from you and knock it prone. On a successful save, the creature takes half as much damage, and you don’t push it or knock it prone.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Flames of the Phoenix",         "(11th level required)\nYou can spend 4 ki points to cast fireball.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Gong of the Summit",         "(6th level required)\nYou can spend 3 ki points to cast shatter.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Mist Stance",         "(11th level required)\nYou can spend 4 ki points to cast gaseous form, targeting yourself. ", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Ride the Wind",         "(11th level required)\nYou can spend 4 ki points to cast fly, targeting yourself.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] River of Hungry Flame",         "(17th level required)\nYou can spend 5 ki points to cast wall of fire.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Rush of the Gale Spirits",         "You can spend 2 ki points to cast gust of wind.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Shape the Flowing River",         "As an action, you can spend 1 ki point to choose an area of ice or water no larger than 30 feet on a side within 120 feet of you. You can change water to ice within the area and vice versa, and you can reshape ice in the area in any manner you choose. You can raise or lower the ice’s elevation, create or fill in a trench, erect or flatten a wall, or form a pillar. The extent of any such changes can’t exceed half the area’s largest dimension. For example, if you affect a 30-foot square, you can create a pillar up to 15 feet high, raise or lower the square’s elevation by up to 15 feet, dig a trench up to 15 feet deep, and so on. You can’t shape the ice to trap or damage a creature in the area.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Sweeping Cinder Strike",         "You can spend 2 ki points to cast burning hands.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Water Whip",         "You can spend 2 ki points as an action to create a whip of water that shoves and pulls a creature to unbalance it. A creature that you can see that is within 30 feet of you must make a Dexterity saving throw. On a failed save, the creature takes 3d10 bludgeoning damage, plus an extra 1d10 bludgeoning damage for each additional ki point you spend, and you can either knock it prone or pull it up to 25 feet closer to you. On a successful save, the creature takes half as much damage, and you don’t pull it or knock it prone.", "", -1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Discipline] Wave of Rolling Earth",         "(17th level required)\nYou can spend 6 ki points to cast wall of stone. (17th Level Required)", "", -1, -1, false, Enumerations.ActionType.PASSIVE)
    };

    @Override
    public int gainedArchetypeFeatures(int classLevel) {
        if (classLevel == 3 || classLevel == 6 || classLevel == 11 || classLevel == 17) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<Power> getAllArchetypeFeatures(int iClassLevel) {
        return Arrays.asList(disciplines);
    }

    @Override
    public boolean canReplaceFeature(int iClasseLevel) {
        return (iClasseLevel == 6 || iClasseLevel == 11 || iClasseLevel == 17);
    }

    @Override
    public int nbOfFeatures(int iLevel) {
        return iLevel >= 17 ? 4 : iLevel >= 11 ? 3 : iLevel >= 6 ? 2 : iLevel >= 3 ? 1 : 0;
    }



    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Elemental Attunement.");
            levelUp.add("You learned a new Discipline. ");
        }
        else if (iNewCharacterLevel == 5) {
            levelUp.add("You can spend Ki points to augment spells cast through Disciplines. ");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);
        int maxKi = iLevel >= 17 ? 6 : iLevel >= 13 ? 5 : iLevel >= 9 ? 4 : 3;

        if (iLevel >= 3) {
            String description = "Some elemental disciplines allow you to cast spells. See the Spellcasting section for the general rules of spellcasting. To cast one of these spells, you use its casting time and other rules, but you don’t need to provide material components for it.";
            if (iLevel >= 5) {
                description += "Once you reach 5th level in this class, you can spend additional ki points to increase the level of an elemental discipline spell that you cast, provided that the spell has an enhanced effect at a higher level, as burning hands does. The spell’s level increases by 1 for each additional ki point you spend. For example, if you are a 5th-level monk and use Sweeping Cinder Strike to cast burning hands, you can spend 3 ki points to cast it as a 2nd-level spell (the discipline’s base cost of 2 ki points plus 1).\n" +
                        "\n" +
                        "The maximum number of ki points you can spend to cast a spell in this way (including its base ki point cost and any additional ki points you spend to increase its level) is " + maxKi;
            }
            powers.add(new Power("Casting spells", description, "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Elemental Attunement", "You can use your action to briefly control elemental forces within 30 feet of you, causing one of the following effects of your choice:\n" +
                    "\n" +
                    "Create a harmless, instantaneous sensory effect related to air, earth, fire, or water, such as a shower of sparks, a puff of wind, a spray of light mist, or a gentle rumbling of stone.\n" +
                    "Instantaneously light or snuff out a candle, a torch, or a small campfire.\n" +
                    "Chill or warm up to 1 pound of nonliving material for up to 1 hour.\n" +
                    "Cause earth, fire, water, or mist that can fit within a 1-foot cube to shape itself into a crude form you designate for 1 minute.", "30ft", -1, -1, true, Enumerations.ActionType.ACTION));
            powers.addAll(_chosenFeatures);
        }

        return powers;
    }
}
