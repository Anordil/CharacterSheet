package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;

/**
 * Created by ggallani on 19/02/2016.
 */
public interface Race {

    public String getBaseRaceName();
    public String getName();
    public int getSpeedInFeet();

    public LinkedList<Power> getRacialFeatures(Character iCharacter);

    public Fettle[] getAttributeBoost();

    public LinkedList<Fettle> getFettles(Character iCharacter);

    public void setSubRace(String iSubRace);
    public String getSubRace();
    public int getSubraceArrayId();

    public String getAttributeBoostDescription();
}
