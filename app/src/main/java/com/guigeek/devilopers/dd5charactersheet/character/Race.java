package com.guigeek.devilopers.dd5charactersheet.character;

import java.util.LinkedList;

/**
 * Created by ggallani on 19/02/2016.
 */
public interface Race {

    public String getName();
    public int getSpeedInFeet();

    public LinkedList<Power> getRacialFeatures(Character iCharacter);

    public Fettle[] getAttributeBoost();

    public LinkedList<Fettle> getFettles(Character iCharacter);

    public void setSubRace(String iSubRace);

    public String getAttributeBoostDescription();
}
