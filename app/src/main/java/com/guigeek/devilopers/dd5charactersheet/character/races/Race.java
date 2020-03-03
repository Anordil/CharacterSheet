package com.guigeek.devilopers.dd5charactersheet.character.races;

import android.content.Context;

import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

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

    public void chooseAttributeBoost(final Context context, final Character iCharac);

    List<Enumerations.Proficiencies> getArmorProficiencies();
    List<Enumerations.Proficiencies> getWeaponProficiencies();
}
