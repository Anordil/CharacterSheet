package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Character implements Serializable {

    public Class _class;
    public Race _race;
    public String _name;

    public int _level;
    public LinkedList<Fettle> _fettles;

    @Override
    public String toString() {
        return _name + ", Level " + _level + " " + _race + " " + _class;

    }
}
