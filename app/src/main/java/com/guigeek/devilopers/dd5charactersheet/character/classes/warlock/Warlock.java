package com.guigeek.devilopers.dd5charactersheet.character.classes.warlock;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;


public class Warlock extends BaseClass {
    static final long serialVersionUID = 214L;

    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        // Patron at level 1, pact at level 3
        if (iNewLevel == 1) {
            return _archetypes.size() == 0 ? R.array.warlockPatrons : -1;
        }
        return iNewLevel >= 3 && _archetypes.size() == 1 ? R.array.warlockPacts : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.warlock_pact_tome))) {
            return new Warlock_pact_tome();
        } else if (iName.equals(App.getResString(R.string.warlock_pact_blade))) {
            return new Warlock_pact_blade();
        } else if (iName.equals(App.getResString(R.string.warlock_patron_ancient))) {
            return new Warlock_patron_oldOne();
        } else if (iName.equals(App.getResString(R.string.warlock_patron_fiend))) {
            return new Warlock_patron_fiend();
        }
        return null;
    }

    @Override
    public void clearArchetypesOnLevelDown(int iNewlevel) {
        // Only pact can be cleared as the Patron is chosen at level 1
        if (iNewlevel < 3 && _archetypes.size() > 0) {
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
    }


    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.WIS,
                Enumerations.SavingThrows.CHA
        };
    }

    @Override
    public int getAttacksPerRound(Character iCharacter) {
        boolean hasThirstingBlade = false;
        for (Power feat : iCharacter.getFeats()) {
            if (feat._name.contains("Thirsting Blade")) {
                hasThirstingBlade = true;
                break;
            }
        }
        return hasThirstingBlade ? 2:1;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return fettles;
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
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        final List<String> levelUp = new LinkedList<>();
        levelUp.add("Welcome to Warlock level " + iNewCharacterLevel + "!");

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

        // Invocations
        if (iNewCharacterLevel == 2) {
            levelUp.add("You now know 2 invocations.");
        }
        else if (iNewCharacterLevel == 5) {
            levelUp.add("You now know 3 invocations.");
        }
        else if (iNewCharacterLevel == 7) {
            levelUp.add("You now know 4 invocations.");
        }
        else if (iNewCharacterLevel == 9) {
            levelUp.add("You now know 5 invocations.");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You now know 6 invocations.");
        }
        else if (iNewCharacterLevel == 15) {
            levelUp.add("You now know 7 invocations.");
        }
        else if (iNewCharacterLevel == 18) {
            levelUp.add("You now know 8 invocations.");
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
}
