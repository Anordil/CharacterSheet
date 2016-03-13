package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Serializable;

/**
 * Created by Guigeek on 13/03/2016.
 */
public class Power implements Serializable {

    public String _name;
    public String _range;
    public int _max, _left;
    public int _dd;
    public String _description;
    public boolean _isLongRest;

    public Power (String nm, String desc, String rng, int max, int dd, boolean isLR) {
        _name = nm;
        _range = rng;
        _description = desc;
        _max = max;
        _left = max;
        _dd = dd;
        _isLongRest = isLR;
    }

}
