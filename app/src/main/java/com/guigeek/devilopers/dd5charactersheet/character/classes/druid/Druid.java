package com.guigeek.devilopers.dd5charactersheet.character.classes.druid;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.ClericDomain;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_arcana;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_death;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_forge;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_grave;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_knowledge;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_life;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_light;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_nature;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_order;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_tempest;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_trickery;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_twilight;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric_war;

import java.util.LinkedList;
import java.util.List;


public class Druid extends BaseClass {
    static final long serialVersionUID = 2500L;


    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 2 && _archetypes.size() == 0 ? R.array.druidArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.druid_dreams))) {
            return new Druid_dreams();
        } else if (iName.equals(App.getResString(R.string.druid_land))) {
            return new Druid_land();
        } else if (iName.equals(App.getResString(R.string.druid_spores))) {
            return new Druid_spores();
        } else if (iName.equals(App.getResString(R.string.druid_shepherd))) {
            return new Druid_shepherd();
        } else if (iName.equals(App.getResString(R.string.druid_moon))) {
            return new Druid_moon();
        } else if (iName.equals(App.getResString(R.string.druid_wildfire))) {
            return new Druid_wildfire();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.WIS,
                Enumerations.SavingThrows.INT
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        return fettles;
    }

    int[][] _spellSlotsOverride = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
            {0, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 0, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 2, 0, 0, 0, 0, 0, 0},//lv 5
            {0, 4, 3, 3, 0, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 1, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 2, 0, 0, 0, 0, 0},
            {0, 4, 3, 3, 3, 1, 0, 0, 0, 0},
            {0, 4, 3, 3, 3, 2, 0, 0, 0, 0},//lv 10
            {0, 4, 3, 3, 3, 2, 1, 0, 0, 0},
            {0, 4, 3, 3, 3, 2, 1, 0, 0, 0},
            {0, 4, 3, 3, 3, 2, 1, 1, 0, 0},
            {0, 4, 3, 3, 3, 2, 1, 1, 0, 0},
            {0, 4, 3, 3, 3, 2, 1, 1, 1, 0},//lv 15
            {0, 4, 3, 3, 3, 2, 1, 1, 1, 0},
            {0, 4, 3, 3, 3, 2, 1, 1, 1, 1},
            {0, 4, 3, 3, 3, 3, 1, 1, 1, 1},
            {0, 4, 3, 3, 3, 3, 2, 1, 1, 1},
            {0, 4, 3, 3, 3, 3, 2, 2, 1, 1}//ln 20
    };

    int[][] _spellsKnownOverride = {
            // cantrips, spells
            {2, 0},
            {2, 0}, //character lv 1
            {2, 0},
            {2, 0},
            {3, 0},
            {3, 0},//lv 5
            {3, 0},
            {3, 0},
            {3, 0},
            {3, 0},
            {4, 0},//lv 10
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0},//lv 15
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0}//ln 20
    };


    public Druid(){
        _spellSlots = _spellSlotsOverride;
        _spellsKnown = _spellsKnownOverride;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.WIS;
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_druid);
        return name;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Druid level " + iNewCharacterLevel + " benefits:");

        // Cantrips
        if (iNewCharacterLevel == 1) {
            levelUp.add("You know 2 cantrips.");
        }
        if (iNewCharacterLevel == 4) {
            levelUp.add("You know 3 cantrips.");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You know 4 cantrips.");
        }

        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Wild Shape");
        }
        if (iNewCharacterLevel == 2) {
            levelUp.add("Your Wild Shape improved (CR 1/2, can swim).");
        }
        if (iNewCharacterLevel == 2) {
            levelUp.add("Your Wild Shape improved (CR 1, can fly).");
        }


        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Timeless Body");
            levelUp.add("Gained Beast Spells");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("Gained Archdruid");
        }


        return levelUp;
    }


    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            String shapeDescription = iLevel >= 8 ? " with a max CR of 1":
                    iLevel >= 4 ? " with a max CR of 1/2 and no flying speed.":
                            " with a max CR of 1/4 and no flying or swimming speed.";
            powers.add(new Power("Wild Shape", "You can use your action to magically assume the shape of a beast that you have seen before" + shapeDescription +". You can use this feature twice. You regain expended uses when you finish a short or long rest.\n" +
                    "\n" + "You can remain in wild shape for " + (int)Math.floor(iLevel/2) + " hours. You then revert to your normal form unless you expend another use of this feature. You can revert to your normal form earlier by using a bonus action on your turn. You automatically revert if you fall unconscious, drop to 0 hit points, or die.\n" +
                    "\n" +
                    "While you are transformed, the following rules apply:\n" +
                    "\n" +
                    "Your game statistics are replaced by the statistics of the beast, but you retain your alignment, personality, and Intelligence, Wisdom, and Charisma scores. You also retain all of your skill and saving throw proficiencies, in addition to gaining those of the creature. If the creature has the same proficiency as you and the bonus in its stat block is higher than yours, use the creature’s bonus instead of yours. If the creature has any legendary or lair actions, you can’t use them.\n" +
                    "When you transform, you assume the beast’s hit points and Hit Dice. When you revert to your normal form, you return to the number of hit points you had before you transformed. However, if you revert as a result of dropping to 0 hit points, any excess damage carries over to your normal form. For example, if you take 10 damage in animal form and have only 1 hit point left, you revert and take 9 damage. As long as the excess damage doesn’t reduce your normal form to 0 hit points, you aren’t knocked unconscious.\n" +
                    "You can’t cast spells, and your ability to speak or take any action that requires hands is limited to the capabilities of your beast form. Transforming doesn’t break your concentration on a spell you’ve already cast, however, or prevent you from taking actions that are part of a spell, such as call lightning, that you’ve already cast.\n" +
                    "You retain the benefit of any features from your class, race, or other source and can use them if the new form is physically capable of doing so. However, you can’t use any of your special senses, such as darkvision, unless your new form also has that sense.\n" +
                    "You choose whether your equipment falls to the ground in your space, merges into your new form, or is worn by it. Worn equipment functions as normal, but the DM decides whether it is practical for the new form to wear a piece of equipment, based on the creature’s shape and size. Your equipment doesn’t change size or shape to match the new form, and any equipment that the new form can’t wear must either fall to the ground or merge with it. Equipment that merges with the form has no effect until you leave the form.", "", iLevel >= 20 ? -1 :2,-1, false, Enumerations.ActionType.ACTION));
        }

        if (iLevel >= 18) {
            powers.add(new Power("Timeless Body", "For every 10 years that pass, your body ages only 1 year.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Beast Spells", "You can perform the somatic and verbal components of a druid spell while in a beast shape, but you aren’t able to provide material components.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Archdruid", "You can use your Wild Shape an unlimited number of times.\n" +
                    "\n" +
                    "Additionally, you can ignore the verbal and somatic components of your druid spells, as well as any material components that lack a cost and aren’t consumed by a spell. You gain this benefit in both your normal shape and your beast shape from Wild Shape.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_druid;
    }
}
