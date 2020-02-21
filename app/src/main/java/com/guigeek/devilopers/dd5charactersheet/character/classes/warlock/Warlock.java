package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Warlock extends BaseClass {
    static final long serialVersionUID = 214L;


    @Override
    public int gainedClassFeatures(int classLevel) {
        if (classLevel == 2) {
            return 2;
        }
        else if (classLevel == 5 || classLevel == 7 || classLevel == 9 || classLevel == 13 || classLevel == 15 || classLevel == 18) {
            return 1;
        }

        return 0;
    }

    @Override
    public List<Power> getAllClassFeatures(int iClassLevel) {
        return Arrays.asList(invocationsOptions);
    }

    @Override
    public boolean canReplaceFeature(int iClasseLevel) {
        return iClasseLevel > 2;
    }


    Power[] invocationsOptions = new Power[]{
            new Power("[Invocation] Agonizing Blast", "Prerequisite: eldritch blast cantrip\n" +
                    "\n" +
                    "When you cast eldritch blast, add your Charisma modifier to the damage it deals on a hit.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Armor of Shadows", "You can cast mage armor on yourself at will, without expending a spell slot or material components.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Ascendant Step", "Prerequisite: 9th level\n" +
                    "\n" +
                    "You can cast levitate on yourself at will, without expending a spell slot or material components.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Aspect of the Moon", "Prerequisite: Pact of the Tome feature\n" +
                    "\n" +
                    "You no longer need to sleep and can’t be forced to sleep by any means. To gain the benefits of a long rest, you can spend all 8 hours doing light activity, such as reading your Book of Shadows and keeping watch.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Beast Speech", "You can cast speak with animals at will, without expending a spell slot.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Beguiling Influence", "You gain proficiency in the Deception and Persuasion skills.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Bewitching Whispers", "Prerequisite: 7th level\n" +
                    "\n" +
                    "You can cast compulsion once using a warlock spell slot. You can’t do so again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Book of Ancient Secrets", "Prerequisite: Pact of the Tome feature\n" +
                    "\n" +
                    "You can now inscribe magical rituals in your Book of Shadows. Choose two 1st-level spells that have the ritual tag from any class’s spell list (the two needn’t be from the same list). The spells appear in the book and don’t count against the number of spells you know. With your Book of Shadows in hand, you can cast the chosen spells as rituals. You can’t cast the spells except as rituals, unless you’ve learned them by some other means. You can also cast a warlock spell you know as a ritual if it has the ritual tag.\n" +
                    "\n" +
                    "On your adventures, you can add other ritual spells to your Book of Shadows. When you find such a spell, you can add it to the book if the spell’s level is equal to or less than half your warlock level (rounded up) and if you can spare the time to transcribe the spell. For each level of the spell, the transcription process takes 2 hours and costs 50 gp for the rare inks needed to inscribe it.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Chains of Carceri", "Prerequisite: 15th level, Pact of the Chain feature\n" +
                    "\n" +
                    "You can cast hold monster at will — targeting a celestial, fiend, or elemental — without expending a spell slot or material components. You must finish a long rest before you can use this invocation on the same creature again.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Cloak of Flies", "Prerequisite: 5th level\n" +
                    "\n" +
                    "As a bonus action, you can surround yourself with a magical aura that looks like buzzing flies. The aura extends 5 feet from you in every direction, but not through total cover. It lasts until you’re incapacitated or you dismiss it as a bonus action.\n" +
                    "\n" +
                    "The aura grants you advantage on Charisma (Intimidation) checks but disadvantage on all other Charisma checks. Any other creature that starts its turn in the aura takes poison damage equal to your Charisma modifier (minimum of 0 damage).\n" +
                    "\n" +
                    "Once you use this invocation, you can’t use it again until you finish a short or long rest.", "", 1, -1, false, Enumerations.ActionType.BONUS_ACTION),
            new Power("[Invocation] Devil’s Sight", "You can see normally in darkness, both magical and nonmagical, to a distance of 120 feet.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Dreadful Word", "Prerequisite: 7th level\n" +
                    "\n" +
                    "You can cast confusion once using a warlock spell slot. You can’t do so again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Eldritch Sight", "You can cast detect magic at will, without expending a spell slot.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Eldritch Smite", "Prerequisite: 5th level, Pact of the Blade feature\n" +
                    "\n" +
                    "Once per turn when you hit a creature with your pact weapon, you can expend a warlock spell slot to deal an extra 1d8 force damage to the target, plus another 1d8 per level of the spell slot, and you can knock the target prone if it is Huge or smaller.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Eldritch Spear", "Prerequisite: eldritch blast cantrip\n" +
                    "\n" +
                    "When you cast eldritch blast, its range is 300 feet.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Eyes of the Rune Keeper", "You can read all writing.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Fiendish Vigor", "You can cast false life on yourself at will as a 1st-level spell, without expending a spell slot or material components.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Gaze of Two Minds", "You can use your action to touch a willing humanoid and perceive through its senses until the end of your next turn. As long as the creature is on the same plane of existence as you, you can use your action on subsequent turns to maintain this connection, extending the duration until the end of your next turn. While perceiving through the other creature’s senses, you benefit from any special senses possessed by that creature, and you are blinded and deafened to your own surroundings.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Ghostly Gaze", "Prerequisite: 7th level\n" +
                    "\n" +
                    "As an action, you gain the ability to see through solid objects to a range of 30 feet. Within that range, you have darkvision if you don’t already have it. This special sight lasts for 1 minute or until your concentration ends (as if you were concentrating on a spell). During that time, you perceive objects as ghostly, transparent images.\n" +
                    "\n" +
                    "Once you use this invocation, you can’t use it again until you finish a short or long rest.", "", 1, -1, false, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Gift of the Depths", "Prerequisite: 5th level\n" +
                    "\n" +
                    "You can breathe underwater, and you gain a swimming speed equal to your walking speed.\n" +
                    "\n" +
                    "You can also cast water breathing once without expending a spell slot. You regain the ability to do so when you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Gift of the Ever-Living Ones", "Prerequisite: Pact of the Chain feature\n" +
                    "\n" +
                    "Whenever you regain hit points while your familiar is within 100 feet of you, treat any dice rolled to determine the hit points you regain as having rolled their maximum value for you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Grasp of Hadar", "Prerequisite: eldritch blast cantrip\n" +
                    "\n" +
                    "Once on each of your turns when you hit a creature with your eldritch blast, you can move that creature in a straight line 10 feet closer to you.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Improved Pact Weapon", "Prerequisite: Pact of the Blade feature\n" +
                    "\n" +
                    "You can use any weapon you summon with your Pact of the Blade feature as a spellcasting focus for your warlock spells.\n" +
                    "\n" +
                    "In addition, the weapon gains a +1 bonus to its attack and damage rolls, unless it is a magic weapon that already has a bonus to those rolls.\n" +
                    "\n" +
                    "Finally, the weapon you conjure can be a shortbow, longbow, light crossbow, or heavy crossbow.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Lance of Lethargy", "Prerequisite: eldritch blast cantrip\n" +
                    "\n" +
                    "Once on each of your turns when you hit a creature with your eldritch blast, you can reduce that creature’s speed by 10 feet until the end of your next turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Lifedrinker", "Prerequisite: 12th level, Pact of the Blade feature\n" +
                    "\n" +
                    "When you hit a creature with your pact weapon, the creature takes extra necrotic damage equal to your Charisma modifier (minimum 1).", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Maddening Hex", "Prerequisite: 5th level, hex spell or a warlock feature that curses\n" +
                    "\n" +
                    "As a bonus action, you cause a psychic disturbance around the target cursed by your hex spell or by a warlock feature of yours, such as Hexblade’s Curse or Sign of Ill Omen. When you do so, you deal psychic damage to the cursed target and each creature of your choice that you can see within 5 feet of it. The psychic damage equals your Charisma modifier (minimum of 1 damage). To use this invocation, you must be able to see the cursed target, and it must be within 30 feet of you.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION),
            new Power("[Invocation] Mask of Many Faces", "You can cast disguise self at will, without expending a spell slot.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Master of Myriad Forms", "Prerequisite: 15th level\n" +
                    "\n" +
                    "You can cast alter self at will, without expending a spell slot.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Minions of Chaos", "Prerequisite: 9th level\n" +
                    "\n" +
                    "You can cast conjure elemental once using a warlock spell slot. You can’t do so again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Mire the Mind", "Prerequisite: 5th level\n" +
                    "\n" +
                    "You can cast slow once using a warlock spell slot. You can’t do so again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Misty Visions", "You can cast silent image at will, without expending a spell slot or material components.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] One with Shadows", "Prerequisite: 5th level\n" +
                    "\n" +
                    "When you are in an area of dim light or darkness, you can use your action to become invisible until you move or take an action or a reaction.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Otherworldly Leap", "Prerequisite: 9th level\n" +
                    "\n" +
                    "You can cast jump on yourself at will, without expending a spell slot or material components.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Relentless Hex", "Prerequisite: 7th level, hex spell or a warlock feature that curses\n" +
                    "\n" +
                    "Your curse creates a temporary bond between you and your target. As a bonus action, you can magically teleport up to 30 feet to an unoccupied space you can see within 5 feet of the target cursed by your hex spell or by a warlock feature of yours, such as Hexblade’s Curse or Sign of Ill Omen. To teleport in this way, you must be able to see the cursed target.", "", -1, -1, true, Enumerations.ActionType.BONUS_ACTION),
            new Power("[Invocation] Repelling Blast", "Prerequisite: eldritch blast cantrip\n" +
                    "\n" +
                    "When you hit a creature with eldritch blast, you can push the creature up to 10 feet away from you in a straight line.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Sculptor of Flesh", "Prerequisite: 7th level\n" +
                    "\n" +
                    "You can cast polymorph once using a warlock spell slot. You can’t do so again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Shroud of Shadow", "Prerequisite: 15th level\n" +
                    "\n" +
                    "You can cast invisibility at will, without expending a spell slot.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Sign of Ill Omen", "Prerequisite: 5th level\n" +
                    "\n" +
                    "You can cast bestow curse once using a warlock spell slot. You can’t do so again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Thief of Five Fates", "You can cast bane once using a warlock spell slot. You can’t do so again until you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Thirsting Blade", "Prerequisite: 5th level, Pact of the Blade feature\n" +
                    "\n" +
                    "You can attack with your pact weapon twice, instead of once, whenever you take the Attack action on your turn.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Tomb of Levistus", "Prerequisite: 5th level\n" +
                    "\n" +
                    "As a reaction when you take damage, you can entomb yourself in ice, which melts away at the end of your next turn. You gain 10 temporary hit points per warlock level, which take as much of the triggering damage as possible. Immediately after you take the damage, you gain vulnerability to fire damage, your speed is reduced to 0, and you are incapacitated. These effects, including any remaining temporary hit points, all end when the ice melts.\n" +
                    "\n" +
                    "Once you use this invocation, you can’t use it again until you finish a short or long rest.", "", 1, -1, false, Enumerations.ActionType.REACTION),
            new Power("[Invocation] Trickster's Escape", "Prerequisite: 7th level\n" +
                    "\n" +
                    "You can cast freedom of movement once on yourself without expending a spell slot. You regain the ability to do so when you finish a long rest.", "", 1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Visions of Distant Realms", "Prerequisite: 15th level\n" +
                    "\n" +
                    "You can cast arcane eye at will, without expending a spell slot.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Voice of the Chain Master", "Prerequisite: Pact of the Chain feature\n" +
                    "\n" +
                    "You can communicate telepathically with your familiar and perceive through your familiar’s senses as long as you are on the same plane of existence. Additionally, while perceiving through your familiar’s senses, you can also speak through your familiar in your own voice, even if your familiar is normally incapable of speech.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Whispers of the Grave", "Prerequisite: 9th level\n" +
                    "\n" +
                    "You can cast speak with dead at will, without expending a spell slot.", "", -1, -1, true, Enumerations.ActionType.PASSIVE),
            new Power("[Invocation] Witch Sight", "Prerequisite: 15th level\n" +
                    "\n" +
                    "You can see the true form of any shapechanger or creature concealed by illusion or transmutation magic while the creature is within 30 feet of you and within line of sight.", "", -1, -1, true, Enumerations.ActionType.PASSIVE)
    };

    public int nbOfFeatures(int iLevel) {
        return iLevel >= 18 ? 8 : iLevel >= 15 ? 7 : iLevel >= 12 ? 6 : iLevel >= 9 ? 5 : iLevel >= 7 ? 4 : iLevel >= 5 ? 3 : iLevel >= 2 ? 2 : 0;
    }

    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        // Patron at level 1, pact at level 3
        if (iNewLevel == 1) {
            return _archetypes.size() == 0 ? R.array.warlockPatrons : -1;
        }
        return iNewLevel == 3 && _archetypes.size() == 1 ? R.array.warlockPacts : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.warlock_pact_tome))) {
            return new Warlock_pact_tome();
        } else if (iName.equals(App.getResString(R.string.warlock_pact_blade))) {
            return new Warlock_pact_blade();
        } else if (iName.equals(App.getResString(R.string.warlock_pact_chain))) {
            return new Warlock_pact_chain();
        } else if (iName.equals(App.getResString(R.string.warlock_patron_ancient))) {
            return new Warlock_patron_oldOne();
        } else if (iName.equals(App.getResString(R.string.warlock_patron_fiend))) {
            return new Warlock_patron_fiend();
        } else if (iName.equals(App.getResString(R.string.warlock_patron_archfey))) {
            return new Warlock_patron_archfey();
        } else if (iName.equals(App.getResString(R.string.warlock_patron_celestial))) {
            return new Warlock_patron_celestial();
        } else if (iName.equals(App.getResString(R.string.warlock_patron_hexblade))) {
            return new Warlock_patron_hexblade();
        } else if (iName.equals(App.getResString(R.string.warlock_patron_undying))) {
            return new Warlock_patron_undying();
        }
        return null;
    }

    @Override
    public void doLevelDown(int oldLevel, int newLevel) {
        // Only pact can be cleared as the Patron is chosen at level 1
        if (newLevel < 3 && _archetypes.size() > 0) {
            Archetype pact = null;
            for (Archetype arc: _archetypes) {
                if (arc instanceof Warlock_pact_blade || arc instanceof Warlock_pact_tome) {
                    pact = arc;
                    break;
                }
            }

            if (pact != null) {
                _archetypes.remove(pact);
            }
        }

        while(_chosenFeatures.size() > nbOfFeatures(newLevel)) {
            _chosenFeatures.remove(_chosenFeatures.size() -1);
        }
    }


    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.WIS,
                Enumerations.SavingThrows.CHA
        };
    }

    @Override
    public int getAttacksPerRound(Character iCharacter, int classLevel) {
        for (Power feat : _chosenFeatures) {
            if (feat._name.equals("[Invocation] Thirsting Blade")) {
                return 2;
            }
        }
        return 1;
    }



    int[][] _spellSlotsSubclass = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 10
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // Arcanum spells
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},//lv 15
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1}//ln 20
    };

    int[][] _spellsKnownSubclass = {
            // cantrips, spells
            {0, 0},
            {2, 2}, //character lv 1
            {2, 3},
            {2, 4},
            {3, 5},
            {3, 6},//lv 5
            {3, 7},
            {3, 8},
            {3, 9},
            {3, 10},
            {4, 10},//lv 10
            {4, 11},
            {4, 11},
            {4, 12},
            {4, 12},
            {4, 13},//lv 15
            {4, 13},
            {4, 14},
            {4, 14},
            {4, 15},
            {4, 15}//ln 20
    };


    public Warlock(){
        _spellSlots = _spellSlotsSubclass;
        _spellsKnown = _spellsKnownSubclass;
    }


    @Override
    public String getClassName() {
        return App.getResString(R.string.class_warlock);
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public boolean isCaster() {
        return true;
    }

    @Override
    public List<String> getLevelUpBenefits(final int iNewCharacterLevel, final Context context) {
        final List<String> levelUp = new LinkedList<>();
        levelUp.add("Warlock level " + iNewCharacterLevel + " benefits:");

        // Cantrips
        int index = 1;
        if (iNewCharacterLevel == 10) {
            levelUp.add("You now know 4 cantrips.");
        }
        else if (iNewCharacterLevel == 4) {
            levelUp.add("You now know 3 cantrips.");
        }
        else if (iNewCharacterLevel == 1) {
            levelUp.add("You know 2 cantrips.");
        }

        // Spells
        if (iNewCharacterLevel == 1) {
            levelUp.add("You know 2 spells.");
        }
        else if (iNewCharacterLevel <= 9) {
            levelUp.add("You now know " + (iNewCharacterLevel+1) + " spells.");
        }
        else if (iNewCharacterLevel % 2 != 0) {
            float count = iNewCharacterLevel -10;
            levelUp.add("You now know " + (10+(int)(Math.ceil(count/2))) + " spells.");
        }

        // Base-warlock powers
        if (iNewCharacterLevel == 11) {
            levelUp.add("You gained one 6th level Arcanum!");
        }
        if (iNewCharacterLevel == 13) {
            levelUp.add("You gained one 7th level Arcanum!");
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("You gained one 8th level Arcanum!");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("You gained one 9th level Arcanum!");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("You gained Eldritch Master!");
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        // Spell slot
        if (iLevel >= 1) {
            int spellLevel = 1;
            int spellSlots = 1;
            int dd = 8 + iCharac.getProficiencyBonus() + iCharac.getModifier(getMainSpellAttribute());
            if (iLevel >= 17) {
                spellSlots = 4;
                spellLevel = 5;
            }
            else if (iLevel >= 11) {
                spellSlots = 3;
                spellLevel = 5;
            }
            else if (iLevel >= 9) {
                spellSlots = 2;
                spellLevel = 5;
            }
            else if (iLevel >= 7) {
                spellSlots = 2;
                spellLevel = 4;
            }
            else if (iLevel >= 5) {
                spellSlots = 2;
                spellLevel = 3;
            }
            else if (iLevel >= 3) {
                spellSlots = 2;
                spellLevel = 2;
            }
            else if (iLevel >= 2) {
                spellSlots = 2;
                spellLevel = 1;
            }
            powers.add(new Power("Spell slot", "Consume a slot to cast a spell which level is no more than " + spellLevel + ".\nThe spell is cast as a " + spellLevel + (spellLevel == 1 ? "st" : (spellLevel == 2 ? "nd" : (spellLevel == 3 ? "rd": "th"))) + " level spell.", "Spell", spellSlots, dd, false, Enumerations.ActionType.ACTION));
        }

        powers.addAll(_chosenFeatures);


        if (iLevel >= 20) {
            powers.add(new Power("Eldritch Master", "Recover all Warlock spell slots.", "", 1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }


    @Override
    public int getIconResource() {
        return R.drawable.ic_warlock;
    }

    @Override
    public String[] getClassSkills() {
        return new String[] {
                Enumerations.Skills.ARCANA.toString(),
                Enumerations.Skills.DECEPTION.toString(),
                Enumerations.Skills.HISTORY.toString(),
                Enumerations.Skills.INTIMIDATION.toString(),
                Enumerations.Skills.INVESTIHATION.toString(),
                Enumerations.Skills.NATURE.toString(),
                Enumerations.Skills.RELIGION.toString(),
        };
    }
}
