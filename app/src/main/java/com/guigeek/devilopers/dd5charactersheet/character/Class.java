package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Serializable;

/**
 * Created by ggallani on 19/02/2016.
 */
public interface Class  {

    public String getName();
    public int getHitDie();

    public boolean isCaster();
    public int[] getSpellSlots(int iCharacterLevel);
    public int getAttacksPerRound(int iCharacterLevel);

    public String[] getLevelUpBenefits(int iNewCharacterLevel);
}
