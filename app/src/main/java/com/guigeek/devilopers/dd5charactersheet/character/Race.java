package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Serializable;

/**
 * Created by ggallani on 19/02/2016.
 */
public interface Race {

    public String getName();
    public int getSpeedInFeet();

    public Fettle[] getAttributeBoost();
}
