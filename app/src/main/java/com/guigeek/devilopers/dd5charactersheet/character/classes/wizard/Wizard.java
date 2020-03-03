package com.guigeek.devilopers.dd5charactersheet.character.classes.wizard;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.Skill;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;

import java.util.LinkedList;
import java.util.List;


public class Wizard extends BaseClass {
    static final long serialVersionUID = 2900L;


    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 2 && _archetypes.size() == 0 ? R.array.wizardArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.wizard_bladesinging))) {
            return new Wizard_bladesinging();
        } else if (iName.equals(App.getResString(R.string.wizard_abjuration))) {
            return new Wizard_abjuration();
        } else if (iName.equals(App.getResString(R.string.wizard_conjuration))) {
            return new Wizard_conjuration();
        } else if (iName.equals(App.getResString(R.string.wizard_divination))) {
            return new Wizard_divination();
        } else if (iName.equals(App.getResString(R.string.wizard_enchantment))) {
            return new Wizard_enchantment();
        } else if (iName.equals(App.getResString(R.string.wizard_evocation))) {
            return new Wizard_evocation();
        } else if (iName.equals(App.getResString(R.string.wizard_illusion))) {
            return new Wizard_illusion();
        } else if (iName.equals(App.getResString(R.string.wizard_necromancy))) {
            return new Wizard_necromancy();
        } else if (iName.equals(App.getResString(R.string.wizard_transmutation))) {
            return new Wizard_transmutation();
        } else if (iName.equals(App.getResString(R.string.wizard_war))) {
            return new Wizard_war();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.INT,
                Enumerations.SavingThrows.WIS
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
            {3, 0},
            {4, 0},//lv 5
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0},
            {4, 0},//lv 10
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


    public Wizard(){
        _spellSlots = _spellSlotsOverride;
        _spellsKnown = _spellsKnownOverride;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.INT;
    }

    @Override
    public int getHitDie() {
        return 6;
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_wizard);
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
            levelUp.add("Wizard level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Arcane Recovery");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Wizard level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Spell Mastery");
        }
        if (iNewCharacterLevel == 20) {
            levelUp.add("Wizard level " + iNewCharacterLevel + " benefits:");
            levelUp.add("Gained Signature Spells");
        }

        return levelUp;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter, int classLevel) {
        if (classLevel >= 6 && _archetypes.get(0) instanceof Wizard_bladesinging) {
            return 2;
        }
        return 1;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 1) {
            powers.add(new Power("Arcane Recovery", "Once per day when you finish a short rest, you can choose expended spell slots to recover. The spell slots can have a combined level that is equal to or less than half your wizard level (rounded up), and none of the slots can be 6th level or higher.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            powers.add(new Power("Spell Mastery", "Choose a 1st-level wizard spell and a 2nd-level wizard spell that are in your spellbook. You can cast those spells at their lowest level without expending a spell slot when you have them prepared. If you want to cast either spell at a higher level, you must expend a spell slot as normal.\n" +
                    "\n" +
                    "By spending 8 hours in study, you can exchange one or both of the spells you chose for different spells of the same levels.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 20) {
            powers.add(new Power("Signature Spells", "Choose two 3rd-level wizard spells in your spellbook as your signature spells. You always have these spells prepared, they don’t count against the number of spells you have prepared, and you can cast each of them once at 3rd level without expending a spell slot. When you do so, you can’t do so again until you finish a short or long rest.", "", 2, -1, false, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_mage;
    }

    @Override
    public String[] getClassSkills() {
        return new String[] {
          Enumerations.Skills.ARCANA.toString(),
          Enumerations.Skills.HISTORY.toString(),
          Enumerations.Skills.INSIGHT.toString(),
          Enumerations.Skills.INVESTIHATION.toString(),
          Enumerations.Skills.MEDICINE.toString(),
          Enumerations.Skills.RELIGION.toString(),
        };
    }


    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        LinkedList<Enumerations.Proficiencies> proficiencies = new LinkedList<>();

        proficiencies.add(Enumerations.Proficiencies.DAGGER);
        proficiencies.add(Enumerations.Proficiencies.DART);
        proficiencies.add(Enumerations.Proficiencies.SLING);
        proficiencies.add(Enumerations.Proficiencies.QUARTERSTAFF);
        proficiencies.add(Enumerations.Proficiencies.LIGHT_CROSSBOW);

        return proficiencies;
    }
}
