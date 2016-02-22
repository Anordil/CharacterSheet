package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.character.*;

import java.io.Serializable;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin implements com.guigeek.devilopers.dd5charactersheet.character.Class, Serializable {

    private int[][] _spellSlots = {
            // Only 5 levels
            {0, 0, 0, 0, 0}, //lv 1
            {2, 0, 0, 0, 0},
            {3, 0, 0, 0, 0},
            {3, 0, 0, 0, 0},
            {4, 2, 0, 0, 0},//lv 5
            {4, 2, 0, 0, 0},
            {4, 3, 0, 0, 0},
            {4, 3, 0, 0, 0},
            {4, 3, 2, 0, 0},
            {4, 3, 2, 0, 0},//lv 10
            {4, 3, 3, 0, 0},
            {4, 3, 3, 0, 0},
            {4, 3, 3, 1, 0},
            {4, 3, 3, 1, 0},
            {4, 3, 3, 2, 0},//lv 15
            {4, 3, 3, 2, 0},
            {4, 3, 3, 3, 1},
            {4, 3, 3, 3, 1},
            {4, 3, 3, 3, 2},
            {4, 3, 3, 3, 2}//ln 20
    };

    @Override
    public String getName() {
        return App.getResString(R.string.class_paladin);
    }

    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public boolean isCaster() {
        return true;
    }

    @Override
    public int[] getSpellSlots(int iCharacterLevel) {
        return _spellSlots[Math.min(20, iCharacterLevel)];
    }

    @Override
    public int getAttacksPerRound(int iCharacterLevel) {
        return (iCharacterLevel >= 5 ? 2 : 1);
    }

    @Override
    public String[] getLevelUpBenefits(int iNewCharacterLevel) {
        String[] levelUp = new String[1];
        levelUp[0] = "Welcome to level " + iNewCharacterLevel + "!";
        return levelUp;
    }
}
