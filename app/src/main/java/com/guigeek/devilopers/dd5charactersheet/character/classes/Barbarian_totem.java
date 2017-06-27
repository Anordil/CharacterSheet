package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by totou on 15/06/2016.
 */
public class Barbarian_totem extends Barbarian_base implements Externalizable {


    public static final long serialVersionUID = 203L;
    protected int _version = 1;

    public Barbarian_totem(){}

    public Barbarian_totem(Barbarian_totem other) {
    }

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_spellSlots);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        _version = version;
        if (version >= 1) {
            _spellSlots = (int[][])oi.readObject();
        }
    }


    @Override
    public String getName() {
        return App.getResString(R.string.class_barbarian);
    }



    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel);
        // TODO: add gained powers from spec
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // TODO: shit, you have 27 options for 'subclasses' as you may choose a different totem each time. Gotta think it through, not gonna implement 27 subclasses
        if (iLevel >= 3) {
            powers.add(new Power("Totem Spirit", "Path of the Totem Warrior: Spirit Totem: Bear (Resistance to all damage type except psychic)", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 6) {
            powers.add(new Power("Aspect of the Best", "Path of the Totem Warrior: Aspect of the Beast: Eagle (You gain the eyesight of an eagle. You can see up to 1 mile away with no difficulty, able to discern even fine details as though looking at something no more than 100 feet away from you. Additionally, dim light doesn't impose disadvantage on your Wisdom (Perception) checks.)", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 10) {
            powers.add(new Power("Spirit Walker", "You can cast Commune with Nature as a ritual.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 14) {
            powers.add(new Power("Totemic Attunement", "See book.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }

    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }

    @Override
    public int getIconResource() {
        return R.drawable.ic_barbarian;
    }
}
