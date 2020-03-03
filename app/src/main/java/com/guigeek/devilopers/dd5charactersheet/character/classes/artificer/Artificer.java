package com.guigeek.devilopers.dd5charactersheet.character.classes.artificer;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_abjuration;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_bladesinging;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_conjuration;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_divination;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_enchantment;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_evocation;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_illusion;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_necromancy;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_transmutation;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard_war;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Artificer extends BaseClass {
    static final long serialVersionUID = 2001L;


    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.artificer_archetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.artificer_alchemist))) {
            return new Artificer_alchemist();
        } else if (iName.equals(App.getResString(R.string.artificer_artillerist))) {
            return new Artificer_artillerist();
        } else if (iName.equals(App.getResString(R.string.artificer_battle_smith))) {
            return new Artificer_battle_smith();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.CON,
                Enumerations.SavingThrows.INT
        };
    }


    int[][] _spellSlotsOverride = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0,0,0,0},
            {0, 2, 0, 0, 0, 0, 0,0,0,0}, //character lv 1
            {0, 2, 0, 0, 0, 0, 0,0,0,0},
            {0, 3, 0, 0, 0, 0, 0,0,0,0},
            {0, 3, 0, 0, 0, 0, 0,0,0,0},
            {0, 4, 2, 0, 0, 0, 0,0,0,0},//lv 5
            {0, 4, 2, 0, 0, 0, 0,0,0,0},
            {0, 4, 3, 0, 0, 0, 0,0,0,0},
            {0, 4, 3, 0, 0, 0, 0,0,0,0},
            {0, 4, 3, 2, 0, 0, 0,0,0,0},
            {0, 4, 3, 2, 0, 0, 0,0,0,0},//lv 10
            {0, 4, 3, 3, 0, 0, 0,0,0,0},
            {0, 4, 3, 3, 0, 0, 0,0,0,0},
            {0, 4, 3, 3, 1, 0, 0,0,0,0},
            {0, 4, 3, 3, 1, 0, 0,0,0,0},
            {0, 4, 3, 3, 2, 0, 0,0,0,0},//lv 15
            {0, 4, 3, 3, 2, 0, 0,0,0,0},
            {0, 4, 3, 3, 3, 1, 0,0,0,0},
            {0, 4, 3, 3, 3, 1, 0,0,0,0},
            {0, 4, 3, 3, 3, 2, 0,0,0,0},
            {0, 4, 3, 3, 3, 2, 0,0,0,0}//ln 20
    };

    int[][] _spellsKnownOverride = {
            // cantrips, infusions
            {0, 0},
            {2, 0}, //character lv 1
            {2, 4},
            {2, 4},
            {2, 4},
            {2, 4},//lv 5
            {2, 6},
            {2, 6},
            {2, 6},
            {2, 6},
            {3, 8},//lv 10
            {3, 8},
            {3, 8},
            {3, 8},
            {4, 10},
            {4, 10},//lv 15
            {4, 10},
            {4, 10},
            {4, 12},
            {4, 12},
            {4, 12}//ln 20
    };


    public Artificer(){
        _spellSlots = _spellSlotsOverride;
        _spellsKnown = _spellsKnownOverride;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.INT;
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_artificer);
        return name;
    }

    @Override
    public boolean isCaster() {
        return  true;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 1) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Magical Tinkering");
            levelUp.add("Gained Spellcasting");
            levelUp.add("You know 2 cantrips.");
        }
        if (iNewCharacterLevel == 2) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Infuse Item, learned 4 infusions.");
        }
        if (iNewCharacterLevel == 3) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained The Right Tool for the Job");
        }


        if (iNewCharacterLevel == 6) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Tool Expertise");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Flash of Genius");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Magic Item Adept");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Spell-Storing Item");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Magic Item Savant");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Magic Item Master");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("Artificer level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Soul of Artifice");
        }

        return levelUp;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter, int classLevel) {
        if (classLevel >= 5 && _archetypes.get(0) instanceof Artificer_battle_smith) {
            return 2;
        }
        return 1;
    }

    public int getInfusionsKnown(int iLevel) {
        return _spellsKnownOverride[iLevel][1];
    }

    public int getInfusedItemCount(int iLevel) {
        return iLevel >= 18 ? 6 : iLevel >= 14 ? 5 :  iLevel >= 10 ? 4 : iLevel >= 6 ? 3: iLevel >= 2 ? 2 : 0;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Magical Tinkering", "You learn how to invest a spark of magic into mundane objects. To use this ability, you must have tinker’s tools or other artisan’s tools in hand. You then touch a Tiny nonmagical object as an action and give it one of the following magical properties of your choice:\n" +
                    "\n" +
                    "The object sheds bright light in a 5-foot radius and dim light for an additional 5 feet.\n" +
                    "Whenever tapped by a creature, the object emits a recorded message that can be heard up to 10 feet away. You utter the message when you bestow this property on the object, and the recording can be no more than 6 seconds long.\n" +
                    "The object continuously emits your choice of an odor or a nonverbal sound (wind, waves, chirping, or the like). The chosen phenomenon is perceivable up to 10 feet away.\n" +
                    "A static visual effect appears on one of the object’s surfaces. This effect can be a picture, up to 25 words of text, lines and shapes, or a mixture of these elements, as you like.\n" +
                    "The chosen property lasts indefinitely. As an action, you can touch the object and end the property early.\n" +
                    "\n" +
                    "You can bestow magic on multiple objects, touching one object each time you use this feature, though a single object can only bear one property at a time. The maximum number of objects you can affect with this feature at one time is equal to your Intelligence modifier (minimum of one object). If you try to exceed your maximum, the oldest property immediately ends, and then the new property applies.", "", -1, -1, true, Enumerations.ActionType.ACTION));
            powers.add(new Power("Spellcasting", "You have studied the workings of magic and how to channel it through objects. As a result, you have gained the ability to cast spells. To observers, you don’t appear to be casting spells in a conventional way; you look as if you’re producing wonders using mundane items or outlandish inventions.\n" +
                    "\n" +
                    "Tools Required\n" +
                    "You produce your artificer spell effects through your tools. You must have a spellcasting focus—specifically thieves’ tools or some kind of artisan’s tool—in hand when you cast any spell with this Spellcasting feature. You must be proficient with the tool to use it in this way. See chapter 5, “Equipment,” in the Player’s Handbook for descriptions of these tools.\n" +
                    "\n" +
                    "After you gain the Infuse Item feature at 2nd level, you can also use any item bearing one of your infusions as a spellcasting focus.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 2) {
            powers.add(new Power("Infuse Item", "You gain the ability to imbue mundane items with certain magical infusions. The magic items you create with this feature are effectively prototypes of permanent items." +
                    "\n [Infusions known] " + getInfusionsKnown(iLevel)
                    + "\n [Max nb of active infusions] " + getInfusedItemCount(iLevel)
                    + "\n\n" +
                    "Whenever you finish a long rest, you can touch a non-magical object and imbue it with one of your artificer infusions, turning it into a magic item. An infusion works on only certain kinds of objects, as specified in the infusion’s description. If the item requires attunement, you can attune yourself to it the instant you infuse the item. If you decide to attune to the item later, you must do so using the normal process for attunement (see “Attunement” in chapter 7 of the Dungeon Master’s Guide).\n" +
                    "\n" +
                    "Your infusion remains in an item indefinitely, but when you die, the infusion vanishes after a number of days have passed equal to your Intelligence modifier (minimum of 1 day). The infusion also vanishes if you give up your knowledge of the infusion for another one.\n" +
                    "\n" +
                    "You can infuse more than one nonmagical object at the end of a long rest; the maximum number of objects appears in the Infused Items column of the Artificer table. You must touch each of the objects, and each of your infusions can be in only one object at a time. Moreover, no object can bear more than one of your infusions at a time. If you try to exceed your maximum number of infusions, the oldest infusion immediately ends, and then the new infusion applies.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.addAll(_chosenFeatures);
        }

        if (iLevel >= 3) {
            powers.add(new Power("The Right Tool for the Job", "With tinker’s tools in hand, you can magically create one set of artisan’s tools in an unoccupied space within 5 feet of you. This creation requires 1 hour of uninterrupted work, which can coincide with a short or long rest. Though the product of magic, the tools are nonmagical, and they vanish when you use this feature again.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 6) {
            powers.add(new Power("Tool Expertise", "Your proficiency bonus is doubled for any ability check you make that uses your proficiency with a tool.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 7) {
            powers.add(new Power("Flash of Genius", "When you or another creature you can see within 30 feet of you makes an ability check or a saving throw, you can use your reaction to add your Intelligence modifier to the roll.\n" +
                    "\n" +
                    "You can use this feature a number of times equal to your Intelligence modifier (minimum of once). You regain all expended uses when you finish a long rest.", "30ft", iCharac.getModifier(Enumerations.Attributes.INT), -1, true, Enumerations.ActionType.REACTION));
        }


        if (iLevel >= 11) {
            powers.add(new Power("Spell-Storing Item", "Whenever you finish a long rest, you can touch one simple or martial weapon or one item that you can use as a spellcasting focus, and you store a spell in it, choosing a 1st or 2nd-level spell from the artificer spell list that requires 1 action to cast (you needn’t have it prepared).\n" +
                    "\n" +
                    "While holding the object, a creature can take an action to produce the spell’s effect from it, using your spellcasting ability modifier. If the spell requires concentration, the creature must concentrate. The spell stays in the object until it’s been used a number of times equal to twice your Intelligence modifier (minimum of twice) or until you use this feature again to store a spell in an object.", "", 2*iCharac.getModifier(Enumerations.Attributes.INT), -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 18) {
            powers.add(new Power("Magic Item Master", "You can attune to up to six magic items at once."+
                    "\nYou ignore all class, race, spell, and level requirements on attuning to or using a magic item." +
                    "\nIf you craft a magic item with a rarity of common or uncommon, it takes you a quarter of the normal time, and it costs you half as much of the usual gold.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        else if (iLevel >= 14) {
            powers.add(new Power("Magic Item Savant", "You can attune to up to five magic items at once." +
                    "\nYou ignore all class, race, spell, and level requirements on attuning to or using a magic item." +
                    "\nIf you craft a magic item with a rarity of common or uncommon, it takes you a quarter of the normal time, and it costs you half as much of the usual gold.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        else if (iLevel >= 10) {
            powers.add(new Power("Magic Item Adept", "You can attune to up to four magic items at once." +
                    "\nIf you craft a magic item with a rarity of common or uncommon, it takes you a quarter of the normal time, and it costs you half as much of the usual gold.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        if (iLevel >= 20) {
            powers.add(new Power("Soul of Artifice", "You gain a +1 bonus to all saving throws per magic item you are currently attuned to." +
                    "\nIf you’re reduced to 0 hit points but not killed out-right, you can use your reaction to end one of your artificer infusions, causing you to drop to 1 hit point instead of 0.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_rolling_bomb;
    }

    @Override
    public String[] getClassSkills() {
        return new String[] {
          Enumerations.Skills.ARCANA.toString(),
          Enumerations.Skills.HISTORY.toString(),
          Enumerations.Skills.INVESTIHATION.toString(),
          Enumerations.Skills.MEDICINE.toString(),
          Enumerations.Skills.NATURE.toString(),
          Enumerations.Skills.PERCEPTION.toString(),
          Enumerations.Skills.SLEIGHT_OF_HAND.toString()
        };
    }

    @Override
    public List<Power> getAllClassFeatures(int iClassLevel) {
        return Arrays.asList(infusionsOptions);
    }

    @Override
    public boolean canReplaceFeature(int iClasseLevel) {
        return iClasseLevel > 2;
    }

    Power[] infusionsOptions = new Power[]{
            new Power("[Infusion] Boots of the Winding Path", "Prerequisite: 6th-level artificer\n" +
                    "Item: A pair of boots (requires attunement)\n" +
                    "\n" +
                    "While wearing these boots, a creature can teleport up to 15 feet as a bonus action to an unoccupied space the creature can see. The creature must have occupied that space at some point during the current turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Enhanced Arcane Focus", "Item: A rod, staff, or wand (requires attunement)\n" +
                    "\n" +
                    "While holding this item, a creature gains a +1 bonus to spell attack rolls. In addition, the creature ignores half cover when making a spell attack.\n" +
                    "\n" +
                    "The bonus increases to +2 when you reach 10th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Enhanced Defense", "Item: A suit of armor or a shield\n" +
                    "\n" +
                    "A creature gains a +1 bonus to Armor Class while wearing (armor) or wielding (shield) the infused item.\n" +
                    "\n" +
                    "The bonus increases to +2 when you reach 10th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Enhanced Weapon", "Item: A simple or martial weapon\n" +
                    "\n" +
                    "This magic weapon grants a +1 bonus to attack and damage rolls made with it.\n" +
                    "\n" +
                    "The bonus increases to +2 when you reach 10th level in this class.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Homunculus Servant", "Prerequisite: 6th-level artificer\n" +
                    "Item: A gem worth at least 100 gp or a dragonshard\n" +
                    "\n" +
                    "You learn intricate methods for magically creating a special homunculus that serves you. The item you infuse serves as the creature’s heart, around which the creature’s body instantly forms.\n" +
                    "\n" +
                    "You determine the homunculus’s appearance. Some artificers prefer mechanical-looking birds, whereas some like winged vials or miniature, animate cauldrons.\n" +
                    "\n" +
                    "The homunculus is friendly to you and your companions, and it obeys your commands. See this creature’s game statistics in the Homunculus Servant stat block.\n" +
                    "\n" +
                    "In combat, the homunculus shares your initiative count, but it takes its turn immediately after yours. It can move and use its reaction on its own, but the only action it takes on its turn is the Dodge action, unless you take a bonus action on your turn to command it to take the action in its stat block or the Dash, Disengage, Help, Hide, or Search action.\n" +
                    "\n" +
                    "The homunculus regains 2d6 hit points if the mending spell is cast on it. If it dies, it vanishes, leaving its heart in its space.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Radiant Weapon", "Prerequisite: 6th-level artificer\n" +
                    "Item: A simple or martial weapon (requires attunement)\n" +
                    "\n" +
                    "This magic weapon grants a +1 bonus to attack and damage rolls made with it. While holding it, the wielder can take a bonus action to cause it to shed bright light in a 30-foot radius and dim light for an additional 30 feet. The wielder can extinguish the light as a bonus action.\n" +
                    "\n" +
                    "The weapon has 4 charges. As a reaction immediately after being hit by an attack, the wielder can expend 1 charge and cause the attacker to be blinded until the end of the attacker’s next turn, unless the attacker succeeds on a Constitution saving throw against your spell save DC. The weapon regains 1d4 expended charges daily at dawn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Repeating Shot", "Item: A simple or martial weapon with the ammunition property (requires attunement)\n" +
                    "\n" +
                    "This magic weapon grants a +1 bonus to attack and damage rolls made with it when it’s used to make a ranged attack, and it ignores the loading property if it has it.\n" +
                    "\n" +
                    "If you load no ammunition in the weapon, it produces its own, automatically creating one piece of magic ammunition when you make a ranged attack with it. The ammunition created by the weapon vanishes the instant after it hits or misses a target.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 2", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 2 II", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 2 III", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),

            new Power("[Infusion] Replicate Magic Item - Level 6", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 6 II", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 6 III", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),

            new Power("[Infusion] Replicate Magic Item - Level 10", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 10 II", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 10 III", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),

            new Power("[Infusion] Replicate Magic Item - Level 14", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 14 II", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Replicate Magic Item - Level 14 III", "Using this infusion, you replicate a particular magic item. You can learn this infusion multiple times; each time you do so, choose a magic item that you can make with it, picking from the Replicable Items tables below. A table’s title tells you the level you must be in the class to choose an item from the table.\n" +
                    "\n" +
                    "In the tables, an item’s entry tells you whether the item requires attunement. See the item’s description in the Dungeon Master’s Guide for more information about it, including the type of object required for its making.\n" +
                    "\n" +
                    "If you have Xanathar’s Guide to Everything, you can choose from among the common magic items in that book when you pick a magic item you can replicate with this infusion.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),

            new Power("[Infusion] Repulsion Shield", "Prerequisite: 6th-level artificer\n" +
                    "Item: A shield (requires attunement)\n" +
                    "\n" +
                    "A creature gains a +1 bonus to Armor Class while wielding this shield.\n" +
                    "\n" +
                    "The shield has 4 charges. While holding it, the wielder can use a reaction immediately after being hit by a melee attack to expend 1 of the shield’s charges and push the attacker up to 15 feet away. The shield regains 1d4 expended charges daily at dawn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Resistant Armor", "Prerequisite: 6th-level artificer\n" +
                    "Item: A suit of armor (requires attunement)\n" +
                    "\n" +
                    "While wearing this armor, a creature has resistance to one of the following damage types, which you choose when you infuse the item: acid, cold, fire, force, lightning, necrotic, poison, psychic, radiant, or thunder.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Infusion] Returning Weapon", "Item: A simple or martial weapon with the thrown property\n" +
                    "\n" +
                    "This magic weapon grants a +1 bonus to attack and damage rolls made with it, and it returns to the wielder’s hand immediately after it is used to make a ranged attack.", "", -1, -1, true, Enumerations.ActionType.PASSIVE)
    };

    @Override
    public int nbOfFeatures(int iLevel) {
        return getInfusionsKnown(iLevel);
    }

    @Override
    public int gainedClassFeatures(int classLevel) {
        if (classLevel == 2) {
            return 4;
        }
        else if (classLevel == 6 || classLevel == 10 || classLevel == 14 || classLevel == 18) {
            return 2;
        }

        return 0;
    }

    @Override
    public List<Enumerations.Proficiencies> getArmorProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.ARMOR_LIGHT);
        proficiencies.add(Enumerations.Proficiencies.ARMOR_MEDIUM);
        proficiencies.add(Enumerations.Proficiencies.SHIELD);

        return proficiencies;
    }

    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.WEAPON_SIMPLE);

        return proficiencies;
    }
}
