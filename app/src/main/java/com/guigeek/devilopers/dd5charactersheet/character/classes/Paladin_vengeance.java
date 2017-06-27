package com.guigeek.devilopers.dd5charactersheet.character.classes;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 19/02/2016.
 */
public class Paladin_vengeance extends Paladin_base implements Externalizable {


    public static final long serialVersionUID = 201L;
    protected int _version = 1;

    public Paladin_vengeance(){}
    public Paladin_vengeance(Paladin_vengeance other) {
        _spellSlots = other._spellSlots;
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
        return App.getResString(R.string.class_paladin);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel);

        //TODO: add gained powers
        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);

        // Vengeance
        if (iLevel >= 3) {
            powers.add(new Power("Channel Divinity", "One creature within 60ft must make a wisdom saving throw. Undead/fiends have disadvantage. On a failed save, it is freightened and its speed is 0 for 1mn or until it takes damage.\nOr target a creature within 10ft: you have advantage on attack rolls against it for 1mn.", "60ft/10ft", 1, -1, false, Enumerations.ActionType.ACTION));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Relentless avenger", "Opportunity attack enables to move at half speed during reaction. Doesn't trigger OA.", "Melee", -1, -1, false, Enumerations.ActionType.REACTION));
        }
        if (iLevel >= 15) {
            powers.add(new Power("Soul of Vengeance", "Creature targeted by Vow on Enmity triggers OA for me when attacking", "Melee", -1, -1, false, Enumerations.ActionType.PASSIVE));
        }

        return powers;
    }
}
