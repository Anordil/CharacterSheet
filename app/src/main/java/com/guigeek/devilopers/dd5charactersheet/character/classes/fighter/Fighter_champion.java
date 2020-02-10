package com.guigeek.devilopers.dd5charactersheet.character.classes.fighter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

public class Fighter_champion extends BaseArchetype {
    static final long serialVersionUID = 2604L;
    protected Power _fightingStyle;

    @Override
    public void writeExternal(ObjectOutput oo) throws IOException {
        super.writeExternal(oo);
        oo.writeObject(_fightingStyle);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException {
        super.readExternal(oi);
        _fightingStyle = (Power)oi.readObject();
    }

    @Override
    public String getName() {
        return App.getResString(R.string.fighter_champion);
    }

    public void doLevelDown(int inewLevel) {
        if (inewLevel < 10) {
            _fightingStyle = null;
        }
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained Improved Critical");
        }
        if (iNewCharacterLevel == 7) {
            levelUp.add("Gained Remarkable Athlete");
        }
        if (iNewCharacterLevel == 10) {
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a new fighting style");

            LinkedList<Power> allStyles = new LinkedList<Power>();
            String[] styleNames = context.getResources().getStringArray(R.array.fighterStyleNames);
            String[] styleDesc = context.getResources().getStringArray(R.array.fighterStyleDesc);

            for (int i = 0; i < styleNames.length; ++i) {
                allStyles.add(new Power(styleNames[i], styleDesc[i], "Self", -1,-1, false, Enumerations.ActionType.PASSIVE));
            }

            final Object[] featsFiltered = allStyles.toArray();

            b.setAdapter(new FeatAdapter(context, R.layout.list_feat, allStyles), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Power feat = (Power)featsFiltered[which];
                    _fightingStyle = new Power(feat._name, feat._description, "", -1,-1, false, Enumerations.ActionType.PASSIVE);
                }
            });

            b.show();
        }
        if (iNewCharacterLevel == 15) {
            levelUp.add("Gained Superior Critical");
        }
        if (iNewCharacterLevel == 18) {
            levelUp.add("Gained Survivor");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            String name = iLevel >= 15 ? "Superior Critical" : "Improved Critical";
            powers.add(new Power(name, "Your weapon attacks score a critical hit on a roll of " + (iLevel >= 15 ? 18 : 19) + "-20.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 7) {
            powers.add(new Power("Remarkable Athlete", "You can add half your proficiency bonus (round up) to any Strength, Dexterity, or Constitution check you make that doesn’t already use your proficiency bonus.\n" +
                    "\n" +
                    "In addition, when you make a running long jump, the distance you can cover increases by a number of feet equal to your Strength modifier.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }
        if (iLevel >= 18) {
            int hp = 5 + iCharac.getModifier(Enumerations.Attributes.CON);
            powers.add(new Power("Survivor", "At the start of each of your turns, you regain " + hp + " hit points if you have no more than half of your hit points left. You don’t gain this benefit if you have 0 hit points.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (_fightingStyle != null) {
            powers.add(_fightingStyle);
        }

        return powers;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character) {
        LinkedList<Fettle> fettles = new LinkedList<>();
        return fettles;
    }
}
