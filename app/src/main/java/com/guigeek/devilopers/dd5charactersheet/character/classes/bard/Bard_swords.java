package com.guigeek.devilopers.dd5charactersheet.character.classes.bard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;


public class Bard_swords extends BaseArchetype {
    static final long serialVersionUID = 2203L;
    protected Power _fightingStyle;

    public Bard_swords(){}

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
        return App.getResString(R.string.bard_swords);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = new LinkedList<>();

        if (iNewCharacterLevel == 3) {
            levelUp.add("Gained proficiency in Medium Armor & Scimitar.");
            levelUp.add("Gained Blade Flourish.");

            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a fighting style");

            LinkedList<Power> allStyles = new LinkedList<Power>();
            String[] styleNames = context.getResources().getStringArray(R.array.bardStyleNames);
            String[] styleDesc = context.getResources().getStringArray(R.array.bardStyleDesc);

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

        if (iNewCharacterLevel == 6) {
            levelUp.add("Gained Extra Attack");
        }
        if (iNewCharacterLevel == 14) {
            levelUp.add("Gained Master’s Flourish");
        }


        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = new LinkedList<>();

        if (iLevel >= 3) {
            powers.add(new Power("Blade Flourish", "Whenever you take the Attack action on your turn, your walking speed increases by 10 feet until the end of the turn, and if a weapon attack that you make as part of this action hits a creature, you can use one of the following Blade Flourish options of your choice. You can use only one Blade Flourish option per turn.\n" +
                    "\n" +
                    "[Defensive Flourish] You can expend one use of your Bardic Inspiration to cause the weapon to deal extra damage to the target you hit. The damage equals the number you roll on the Bardic Inspiration die. You also add the number rolled to your AC until the start of your next turn.\n" +
                    "\n" +
                    "[Slashing Flourish] You can expend one use of your Bardic Inspiration to cause the weapon to deal extra damage to the target you hit and to any other creature of your choice that you can see within 5 feet of you. The damage equals the number you roll on the Bardic Inspiration die.\n" +
                    "\n" +
                    "[Mobile Flourish] You can expend one use of your Bardic Inspiration to cause the weapon to deal extra damage to the target you hit. The damage equals the number you roll on the Bardic Inspiration die. You can also push the target up to 5 feet away from you, plus a number of feet equal to the number you roll on that die. You can then immediately use your reaction to move up to your walking speed to an unoccupied space within 5 feet of the target.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (iLevel >= 14) {
            powers.add(new Power("Master’s Flourish", "Whenever you use a Blade Flourish option, you can roll a d6 and use it instead of expending a Bardic Inspiration die.", "", -1, -1, true, Enumerations.ActionType.PASSIVE));
        }

        if (_fightingStyle != null) {
            powers.add(_fightingStyle);
        }

        return powers;
    }
}
