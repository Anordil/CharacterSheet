package com.guigeek.devilopers.dd5charactersheet.character.classes.monk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.android.FeatsScreen;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BaseArchetype;
import com.guigeek.devilopers.dd5charactersheet.character.classes.sorcerer.Sorcerer;
import com.guigeek.devilopers.dd5charactersheet.character.classes.warlock.Warlock;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;


public class Monk_elements extends BaseArchetype {
    static final long serialVersionUID = 2704L;
    protected LinkedList<Power> _chosenDisciplines;

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        super.writeExternal(objectOutput);
        objectOutput.writeObject(_chosenDisciplines);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        super.readExternal(objectInput);
        _chosenDisciplines = (LinkedList<Power>) objectInput.readObject();
    }

    public Monk_elements(){
        _chosenDisciplines = new LinkedList<>();
    }

    @Override
    public String getName() {
        return App.getResString(R.string.monk_elements);
    }


    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        List<String> levelUp = super.getLevelUpBenefits(iNewCharacterLevel, context);
        if (iNewCharacterLevel == 3) {
            levelUp.add("You gained Elemental Attunement.");
            levelUp.add("You learned a new Discipline. ");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You can spend Ki points to augment spells. ");
        }
        else if (iNewCharacterLevel == 6) {
            levelUp.add("You learned a new Discipline. ");
        }
        else if (iNewCharacterLevel == 11) {
            levelUp.add("You learned a new Discipline. ");
        }
        else if (iNewCharacterLevel == 17) {
            levelUp.add("You learned a new Discipline. ");
        }

        // Get a new Discipline at levels 3, 6, 11 and 17
        if (iNewCharacterLevel == 3 || iNewCharacterLevel == 6 || iNewCharacterLevel == 11 || iNewCharacterLevel == 17) {
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            b.setTitle("Select a new Discipline");

            LinkedList<Power> allDisciplines = new LinkedList<Power>();
            String[] disciplinesNames = context.getResources().getStringArray(R.array.monkDiscNames);
            String[] disciplinesDescriptions = context.getResources().getStringArray(R.array.monkDiscDescriptions);

            alldisc: for (int i = 0; i < disciplinesNames.length; ++i) {
                // Skip already selected disciplines
                for (Power disc : _chosenDisciplines) {
                    if (disc._name.equals(disciplinesNames[i])) {
                        continue alldisc;
                    }
                }

                allDisciplines.add(new Power(disciplinesNames[i], disciplinesDescriptions[i], "Self", -1,-1, false, Enumerations.ActionType.PASSIVE));
            }

            final Object[] featsFiltered = allDisciplines.toArray();

            b.setAdapter(new FeatAdapter(context, R.layout.list_feat, allDisciplines), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Power feat = (Power)featsFiltered[which];
                    _chosenDisciplines.add(new Power(feat._name, feat._description, "", -1,-1, false, Enumerations.ActionType.PASSIVE));
                }
            });

            b.show();
        }
        // Remove extra Disciplines on level down if need be
        int maxSize = iNewCharacterLevel >= 17 ? 4 : iNewCharacterLevel >= 11 ? 3 : iNewCharacterLevel >= 6 ? 2 : iNewCharacterLevel >= 3 ? 1 : 0;
        while (_chosenDisciplines.size() > maxSize) {
            _chosenDisciplines.removeLast();
        }

        return levelUp;
    }

    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        LinkedList<Power> powers = super.getPowers(iLevel, iCharac);
        int maxKi = iLevel >= 17 ? 6 : iLevel >= 13 ? 5 : iLevel >= 9 ? 4 : 3;

        if (iLevel >= 3) {
            String description = "Some elemental disciplines allow you to cast spells. See the Spellcasting section for the general rules of spellcasting. To cast one of these spells, you use its casting time and other rules, but you don’t need to provide material components for it.";
            if (iLevel >= 5) {
                description += "Once you reach 5th level in this class, you can spend additional ki points to increase the level of an elemental discipline spell that you cast, provided that the spell has an enhanced effect at a higher level, as burning hands does. The spell’s level increases by 1 for each additional ki point you spend. For example, if you are a 5th-level monk and use Sweeping Cinder Strike to cast burning hands, you can spend 3 ki points to cast it as a 2nd-level spell (the discipline’s base cost of 2 ki points plus 1).\n" +
                        "\n" +
                        "The maximum number of ki points you can spend to cast a spell in this way (including its base ki point cost and any additional ki points you spend to increase its level) is " + maxKi;
            }
            powers.add(new Power("Casting spells", description, "", -1, -1, true, Enumerations.ActionType.PASSIVE));
            powers.add(new Power("Elemental Attunement", "You can use your action to briefly control elemental forces within 30 feet of you, causing one of the following effects of your choice:\n" +
                    "\n" +
                    "Create a harmless, instantaneous sensory effect related to air, earth, fire, or water, such as a shower of sparks, a puff of wind, a spray of light mist, or a gentle rumbling of stone.\n" +
                    "Instantaneously light or snuff out a candle, a torch, or a small campfire.\n" +
                    "Chill or warm up to 1 pound of nonliving material for up to 1 hour.\n" +
                    "Cause earth, fire, water, or mist that can fit within a 1-foot cube to shape itself into a crude form you designate for 1 minute.", "30ft", -1, -1, true, Enumerations.ActionType.ACTION));
        }

        return powers;
    }
}
