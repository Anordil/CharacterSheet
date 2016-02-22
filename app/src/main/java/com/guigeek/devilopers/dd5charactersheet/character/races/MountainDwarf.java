package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.character.AttributeAlteration;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Race;

import java.io.Serializable;

/**
 * Created by ggallani on 19/02/2016.
 */
public class MountainDwarf implements Race, Serializable {
    @Override
    public String getName() {
        return App.getResString(R.string.race_mtn_dwarf);
    }

    @Override
    public int getSpeedInFeet() {
        return 25;
    }

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[1];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.STR);
        return raceBonuses;
    }
}
