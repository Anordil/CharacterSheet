package com.guigeek.devilopers.dd5charactersheet.character.classes.fighter;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Archetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseClass;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_dreams;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_land;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_moon;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_shepherd;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_spores;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid_wildfire;

import java.util.LinkedList;
import java.util.List;


public class Fighter extends BaseClass {
    static final long serialVersionUID = 2600L;


    @Override
    public int getChoosableArchetypes(int iNewLevel) {
        return iNewLevel == 3 && _archetypes.size() == 0 ? R.array.fighterArchetypes : -1;
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        if (iName.equals(App.getResString(R.string.fighter_archer))) {
            return new Fighter_archer();
        } else if (iName.equals(App.getResString(R.string.fighter_battlemaster))) {
            return new Fighter_battlemaster();
        } else if (iName.equals(App.getResString(R.string.fighter_cavalier))) {
            return new Fighter_cavalier();
        } else if (iName.equals(App.getResString(R.string.fighter_champion))) {
            return new Fighter_champion();
        } else if (iName.equals(App.getResString(R.string.fighter_eldritch))) {
            return new Fighter_eldritch();
        } else if (iName.equals(App.getResString(R.string.fighter_psychic))) {
            return new Fighter_psychic();
        } else if (iName.equals(App.getResString(R.string.fighter_purple))) {
            return new Fighter_purple();
        } else if (iName.equals(App.getResString(R.string.fighter_rune))) {
            return new Fighter_rune();
        } else if (iName.equals(App.getResString(R.string.fighter_samurai))) {
            return new Fighter_samurai();
        }
        return null;
    }

    @Override
    public Enumerations.SavingThrows[] getSavingThrowsProficiencies() {
        return new Enumerations.SavingThrows[] {
                Enumerations.SavingThrows.STR,
                Enumerations.SavingThrows.CON
        };
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        return fettles;
    }



    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public String getClassName() {
        String name = App.getResString(R.string.class_fighter);
        return name;
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();
        levelUp.add("Fighter level " + iNewCharacterLevel + " benefits:");

        if (iNewCharacterLevel == 1) {
            levelUp.add("");
        }

        return levelUp;
    }


    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 2) {
            powers.add(new Power("", "", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }


        return powers;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_warrior;
    }
}
