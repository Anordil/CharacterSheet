package com.guigeek.devilopers.dd5charactersheet.character.races;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.character.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by ggallani on 19/02/2016.
 */
public class MountainDwarf implements Race, Externalizable {
    public static final long serialVersionUID = 102L;
    int _version = 2;


    @Override
    public LinkedList<Fettle> getFettles() {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        fettles.add(new Fettle(Enumerations.FettleType.DAMAGE_RESISTANCE, 0, Enumerations.DamageTypes.POISON.ordinal()));
        fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.POISON.ordinal()));
        return fettles;
    }

    @Override
    public String getName() {
        return App.getResString(R.string.race_mtn_dwarf);
    }

    @Override
    public int getSpeedInFeet() {
        return 25;
    }

    public MountainDwarf(){}

    @Override
    public Fettle[] getAttributeBoost() {
        Fettle[] raceBonuses = new Fettle[1];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.STR);
        return raceBonuses;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        int version = oi.readInt();
        _version = version;
    }

    @Override
    public LinkedList<Power> getRacialFeatures() {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power("Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can't discern color in darkness, only shades of gray.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Dwarven Resilience", "You have advantage on saving throws against poison, and you have resistance against poison damage.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Stonecutting", "Whenever you make an Intelligence (History) check related to the origin of stonework, you are considered proficient in the History skill and add double your proficiency bonus to the check, instead of your normal proficiency bonus.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));


        return racialTraits;
    }
}
