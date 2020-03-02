package com.guigeek.devilopers.dd5charactersheet.character.races;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.StringListAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.AttributeAlteration;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

public class Human extends BaseRace {

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);

        oo.writeObject(raceBonuses);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        if (_version >= 2) {
            raceBonuses = (Fettle[])oi.readObject();
        }
    }

    public static final long serialVersionUID = 104L;
    Fettle[] raceBonuses;

    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return fettles;
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_human);
    }

    @Override
    public int getSubraceArrayId() {
        return R.array.humanSubRaces;
    }

    @Override
    public String getAttributeBoostDescription() {
        if (_subRace.equals("Variant")) {
            return "Add +1 to two ability scores, gain a feat & an extra skill proficiency";
        }
        return "+1 to all attributes";
    }

    @Override
    public void setSubRace(String iSubRace) {
        super.setSubRace(iSubRace);

        if (_subRace.equals("Standard")) {
            raceBonuses = new Fettle[6];
            raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.STR);
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.CON);
            raceBonuses[3] = new AttributeAlteration(1, Enumerations.Attributes.INT);
            raceBonuses[4] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
            raceBonuses[5] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
        }
        else {
            raceBonuses = new Fettle[2];
        }
    }

    @Override
    public Fettle[] getAttributeBoost() {
        return raceBonuses;
    }

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();
        return racialTraits;
    }

    public Human(){}

    @Override
    public void chooseAttributeBoost(final Context context, final Character iCharac) {

        if (_subRace.equals("Standard")) {
            raceBonuses = new Fettle[6];
            raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.STR);
            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.CON);
            raceBonuses[3] = new AttributeAlteration(1, Enumerations.Attributes.INT);
            raceBonuses[4] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
            raceBonuses[5] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
            return;
        }

        raceBonuses = new Fettle[2];
        // +1 to 2 attributes
        final List<String> attributes = new LinkedList<>();
        attributes.add(Enumerations.Attributes.STR.toString());
        attributes.add(Enumerations.Attributes.DEX.toString());
        attributes.add(Enumerations.Attributes.CON.toString());
        attributes.add(Enumerations.Attributes.INT.toString());
        attributes.add(Enumerations.Attributes.WIS.toString());
        attributes.add(Enumerations.Attributes.CHA.toString());
        AlertDialog.Builder selectionDialog = new AlertDialog.Builder(context);
        selectionDialog.setTitle("Assign a +1 bonus to one attribute");

        selectionDialog.setAdapter(new StringListAdapter(context, R.layout.list_string, attributes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String attributename = attributes.get(which);
                if (attributename.equals(Enumerations.Attributes.STR.toString())) {
                    raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.STR);
                    attributes.remove(0);
                }
                if (attributename.equals(Enumerations.Attributes.DEX.toString())) {
                    raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
                    attributes.remove(1);
                }
                if (attributename.equals(Enumerations.Attributes.CON.toString())) {
                    raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.CON);
                    attributes.remove(2);
                }
                if (attributename.equals(Enumerations.Attributes.INT.toString())) {
                    raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.INT);
                    attributes.remove(3);
                }
                if (attributename.equals(Enumerations.Attributes.WIS.toString())) {
                    raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
                    attributes.remove(4);
                }
                if (attributename.equals(Enumerations.Attributes.CHA.toString())) {
                    raceBonuses[0] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
                    attributes.remove(5);
                }

                // Choose #2
                AlertDialog.Builder selectionDialogBis = new AlertDialog.Builder(context);
                selectionDialogBis.setTitle("Assign a +1 bonus to one other attribute");

                selectionDialogBis.setAdapter(new StringListAdapter(context, R.layout.list_string, attributes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String attributename = attributes.get(which);
                        if (attributename.equals(Enumerations.Attributes.STR.toString())) {
                            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.STR);
                        }
                        if (attributename.equals(Enumerations.Attributes.DEX.toString())) {
                            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
                        }
                        if (attributename.equals(Enumerations.Attributes.CON.toString())) {
                            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CON);
                        }
                        if (attributename.equals(Enumerations.Attributes.INT.toString())) {
                            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.INT);
                        }
                        if (attributename.equals(Enumerations.Attributes.WIS.toString())) {
                            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
                        }
                        if (attributename.equals(Enumerations.Attributes.CHA.toString())) {
                            raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
                        }
                    }
                });
                selectionDialogBis.show();
            }
        });

        selectionDialog.show();
    }
}
