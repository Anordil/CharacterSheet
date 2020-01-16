package com.guigeek.devilopers.dd5charactersheet.character;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by Guigeek on 13/03/2016.
 */
public class Power implements Externalizable {

    public static final long serialVersionUID = 11L;
    public int version = 1;
    public String _name;
    public String _range;
    public int _max, _left;
    public int _dd;
    public String _description;
    public boolean _isLongRest;
    public Enumerations.ActionType _useType;

    public Power(){}

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Power) {
            Power other = (Power) obj;
            return other._name.equals(_name);
        }
        return false;
    }

    public Power (String nm, String desc, String rng, int max, int dd, boolean isLR, Enumerations.ActionType useType) {
        _name = nm;
        _range = rng;
        _description = desc;
        _max = max;
        _left = max;
        _dd = dd;
        _isLongRest = isLR;
        _useType = useType;
    }

    public String getUsageString() {
        String result = "";

        switch (_useType) {
            case ACTION:
                result = "Action";
                break;
            case BONUS_ACTION:
                result = "Bonus Action";
                break;
            case REACTION:
                result = "Reaction";
                break;
            case PASSIVE:
                result = "Passive";
                break;
            default:
                result = "Passive";
                break;
        }

        // Range
        if (_range != null && _range.length() > 0) {
            result += " (" + _range + ")";
        }

        return result;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(version);
        oo.writeInt(_max);
        oo.writeInt(_left);
        oo.writeInt(_dd);

        oo.writeObject(_name);
        oo.writeObject(_range);
        oo.writeObject(_description);
        oo.writeObject(_useType);

        oo.writeBoolean(_isLongRest);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int v = oi.readInt();
        version = v;
        if (version >= 1) {
            _max = oi.readInt();
            _left = oi.readInt();
            _dd = oi.readInt();

            _name = (String)oi.readObject();
            _range = (String)oi.readObject();
            _description = (String)oi.readObject();
            _useType = (Enumerations.ActionType)oi.readObject();

            _isLongRest = oi.readBoolean();

        }
    }

}
