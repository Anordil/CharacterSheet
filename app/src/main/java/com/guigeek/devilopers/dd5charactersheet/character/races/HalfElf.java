package com.guigeek.devilopers.dd5charactersheet.character.races;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.App;
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

/**
 * Created by totou on 14/03/2016.
 */
public class HalfElf extends BaseRace {

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

    public static final long serialVersionUID = 101L;
    Fettle[] raceBonuses;

    @Override
    public LinkedList<Fettle> getFettles(Character iCharacter) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        fettles.add(new Fettle(Enumerations.FettleType.SAVING_THROW_ADVANTAGE, 0, Enumerations.SavingThrows.CHARM_MAGIC.ordinal()));
        return fettles;
    }

    @Override
    public String getBaseRaceName() {
        return App.getResString(R.string.race_half_elf);
    }

    @Override
    public Fettle[] getAttributeBoost() {
        return raceBonuses;
    }

    @Override
    public String getAttributeBoostDescription() {
        return "Add +1 to two ability scores other than CHA, Darkvision, Fey ancestry";
    }

    public HalfElf(){
        raceBonuses = new Fettle[1];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.CHA);
    }

    @Override
    public LinkedList<Power> getRacialFeatures(Character iCharacter) {
        LinkedList<Power> racialTraits = new LinkedList<>();

        racialTraits.add(new Power("Darkvision", "You can see in dim light within 60 feet of you as if it were bright light, and in darkness as if it were dim light. You can't discern color in darkness, only shades of gray.", "60ft", -1, -1, true, Enumerations.ActionType.PASSIVE));
        racialTraits.add(new Power("Fey Ancestry", "You have advantage on saving throws against being charmed, and magic can't put you to sleep.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));


        return racialTraits;
    }

    @Override
    public void chooseAttributeBoost(final Context context, final Character iCharac) {

        raceBonuses = new Fettle[3];
        raceBonuses[0] = new AttributeAlteration(2, Enumerations.Attributes.CHA);

        // +1 to 2 non-CHA attributes
        final List<String> attributes = new LinkedList<>();
        attributes.add(Enumerations.Attributes.STR.toString());
        attributes.add(Enumerations.Attributes.DEX.toString());
        attributes.add(Enumerations.Attributes.CON.toString());
        attributes.add(Enumerations.Attributes.INT.toString());
        attributes.add(Enumerations.Attributes.WIS.toString());
        AlertDialog.Builder selectionDialog = new AlertDialog.Builder(context);
        selectionDialog.setTitle("Assign a +1 bonus to one attribute");

        selectionDialog.setAdapter(new StringListAdapter(context, R.layout.list_string, attributes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String attributename = attributes.get(which);
                if (attributename.equals(Enumerations.Attributes.STR.toString())) {
                    raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.STR);
                    attributes.remove(0);
                }
                if (attributename.equals(Enumerations.Attributes.DEX.toString())) {
                    raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
                    attributes.remove(1);
                }
                if (attributename.equals(Enumerations.Attributes.CON.toString())) {
                    raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CON);
                    attributes.remove(2);
                }
                if (attributename.equals(Enumerations.Attributes.INT.toString())) {
                    raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.INT);
                    attributes.remove(3);
                }
                if (attributename.equals(Enumerations.Attributes.WIS.toString())) {
                    raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
                    attributes.remove(4);
                }
                if (attributename.equals(Enumerations.Attributes.CHA.toString())) {
                    raceBonuses[1] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
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
                            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.STR);
                        }
                        if (attributename.equals(Enumerations.Attributes.DEX.toString())) {
                            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.DEX);
                        }
                        if (attributename.equals(Enumerations.Attributes.CON.toString())) {
                            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.CON);
                        }
                        if (attributename.equals(Enumerations.Attributes.INT.toString())) {
                            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.INT);
                        }
                        if (attributename.equals(Enumerations.Attributes.WIS.toString())) {
                            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.WIS);
                        }
                        if (attributename.equals(Enumerations.Attributes.CHA.toString())) {
                            raceBonuses[2] = new AttributeAlteration(1, Enumerations.Attributes.CHA);
                        }
                    }
                });
                selectionDialogBis.show();
            }
        });

        selectionDialog.show();
    }
}
