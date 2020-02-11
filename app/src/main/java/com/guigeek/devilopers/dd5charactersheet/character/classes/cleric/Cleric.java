package com.guigeek.devilopers.dd5charactersheet.character.classes.cleric;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bard.Bard_glamour;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bard.Bard_lore;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bard.Bard_swords;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bard.Bard_valor;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bard.Bard_whispers;

import java.util.LinkedList;
import java.util.List;


public class Cleric extends BaseClass {
    static final long serialVersionUID = 2400L;


    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 1 && _archetypes.size() == 0 ? R.array.clericArchetypes : -1;
    }

    @Override
    public boolean isCaster() {
        return  true;
    }

    @Override
    public void doLevelDown(int oldLevel, int newLevel) {
        // Nothing to clear for Cleric since archetype is chosen at level 1
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.cleric_arcana))) {
            return new Cleric_arcana();
        } else if (iName.equals(App.getResString(R.string.cleric_death))) {
            return new Cleric_death();
        } else if (iName.equals(App.getResString(R.string.cleric_forge))) {
            return new Cleric_forge();
        } else if (iName.equals(App.getResString(R.string.cleric_life))) {
            return new Cleric_life();
        } else if (iName.equals(App.getResString(R.string.cleric_light))) {
            return new Cleric_light();
        } else if (iName.equals(App.getResString(R.string.cleric_grave))) {
            return new Cleric_grave();
        } else if (iName.equals(App.getResString(R.string.cleric_knowledge))) {
            return new Cleric_knowledge();
        } else if (iName.equals(App.getResString(R.string.cleric_nature))) {
            return new Cleric_nature();
        } else if (iName.equals(App.getResString(R.string.cleric_order))) {
            return new Cleric_order();
        } else if (iName.equals(App.getResString(R.string.cleric_tempest))) {
            return new Cleric_tempest();
        } else if (iName.equals(App.getResString(R.string.cleric_trickery))) {
            return new Cleric_trickery();
        } else if (iName.equals(App.getResString(R.string.cleric_twilight))) {
            return new Cleric_twilight();
        } else if (iName.equals(App.getResString(R.string.cleric_war))) {
            return new Cleric_war();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.WIS,
                Enumerations.SavingThrows.CHA
        };
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
            {0, 0},
            {3, 0}, //character lv 1
            {3, 0},
            {3, 0},
            {4, 0},
            {4, 0},//lv 5
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0},
            {5, 0},//lv 10
            {5, 0},
            {5, 0},
            {5, 0},
            {5, 0},
            {5, 0},//lv 15
            {5, 0},
            {5, 0},
            {5, 0},
            {5, 0},
            {5, 0}//ln 20
    };


    public Cleric(){
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
        String name = App.getResString(R.string.class_cleric);
        return name;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Cleric level " + iNewCharacterLevel + " benefits:");

        // Cantrips
        if (iNewCharacterLevel == 1) {
            levelUp.add("You know 3 cantrips.");
        }
        if (iNewCharacterLevel == 4) {
            levelUp.add("You know 4 cantrips.");
        }
        if (iNewCharacterLevel == 10) {
            levelUp.add("You know 5 cantrips.");
        }

        // Channel Divinity
        if (iNewCharacterLevel == 2) {
            levelUp.add("Gained Channel Divinity.");
        }
        if (iNewCharacterLevel == 6) {
            levelUp.add("You can use Channel Divinity 2x/rest.");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("You can use Channel Divinity 3x/rest.");
        }

        // Destroy Undead CR
        if (iNewCharacterLevel == 5) {
            levelUp.add("You gained Destroy Undead (CR 1/2).");
        }
        if (iNewCharacterLevel == 8) {
            levelUp.add("Destroy Undead is now CR 1.");
        }
        if (iNewCharacterLevel == 11) {
            levelUp.add("Destroy Undead is now CR 2.");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Destroy Undead is now CR 3.");
        }
        if (iNewCharacterLevel == 17) {
            levelUp.add("Destroy Undead is now CR 4.");
        }


        // Divine Intervention
        if (iNewCharacterLevel == 10) {
            levelUp.add("Gained Divine Intervention.");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("Your Divine Intervention improved.");
        }


        return levelUp;
    }

    public static int getChannelDivinityUses(int level) {
        return level >= 18 ? 3 : level >= 6 ? 2 : 1;
    }

    public static String getDestroyUndeadCR(int level) {
        return level >= 17 ? "4" : level >= 14 ? "3" : level >= 11 ? "2" : level >= 8 ? "1" : "1/2";
    }


    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            String description = "[Turn Undead] As an action, you present your holy symbol and speak a prayer censuring the undead. Each undead that can see or hear you within 30 feet of you must make a Wisdom saving throw. If the creature fails its saving throw, it is turned for 1 minute or until it takes any damage.\n" +
                    "\n" +
                    "A turned creature must spend its turns trying to move as far away from you as it can, and it can’t willingly move to a space within 30 feet of you. It also can’t take reactions. For its action, it can use only the Dash action or try to escape from an effect that prevents it from moving. If there’s nowhere to move, the creature can use the Dodge action.";
            description += "\n\n" + (_archetypes.size() > 0 ? ((ClericDomain)_archetypes.get(0)).getChannelDivinityEffects(iLevel) : "");
            powers.add(new Power("Channel Divinity", description, "", getChannelDivinityUses(iLevel),
                    8 + iCharac.getProficiencyBonus() + iCharac.getModifier(Enumerations.Attributes.WIS), false, Enumerations.ActionType.ACTION));
        }

        if (iLevel >= 5) {
            powers.add(new Power("Destroy Undead", "When an undead fails its saving throw against your Turn Undead feature, the creature is instantly destroyed " +
                    "if its challenge rating is at or below " + getDestroyUndeadCR(iLevel), "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 10) {
            String desc = "You can call on your deity to intervene on your behalf when your need is great.\n" +
                    "\n" +
                    "Imploring your deity’s aid requires you to use your action. Describe the assistance you seek, and roll percentile dice. If you roll a number equal to or lower than your cleric level, your deity intervenes. The DM chooses the nature of the intervention; the effect of any cleric spell or cleric domain spell would be appropriate.\n" +
                    "\n" +
                    "If your deity intervenes, you can’t use this feature again for 7 days. Otherwise, you can use it again after you finish a long rest.";
            if (iLevel >= 20) {
                desc += "\n\n At 20th level, your call for intervention succeeds automatically, no roll required.";
            }
            powers.add(new Power("Divine Intervention", desc, "", 1, -1, true, Enumerations.ActionType.ACTION));
        }



        return powers;
    }

    @Override
    public int getAC(Character character) {
        int ac = character._equippedArmor == null ? 10 : character._equippedArmor.getAC(character);

        if (character._equippedShield != null && character._equippedShield._type == Enumerations.ArmorTypes.SHIELD) {
            ac+= character._equippedShield.getAC(character);
        }

        if (character._level >= 6 && character._class.getArchetype(0) instanceof Cleric_forge) {
            if (character._equippedArmor != null && character._equippedArmor.isHeavy()) {
                ac += 1;
            }
        }
        return ac;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_priest;
    }
}
