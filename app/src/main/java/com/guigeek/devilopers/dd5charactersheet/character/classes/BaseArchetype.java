package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseArchetype implements Archetype, Externalizable {
    static final long serialVersionUID = 3000L;
    protected int _version = 2;

    // FEATURES
    protected String _chosenStringFeature = null;
    public List<Power> _chosenFeatures = new LinkedList<>();

    @Override
    public int gainedArchetypeFeatures(int classLevel) {
        return 0;
    }

    @Override
    public List<Power> getAllArchetypeFeatures(int iClassLevel) {
        return null;
    }

    @Override
    public boolean canReplaceFeature(int iClasseLevel) {
        return false;
    }

    @Override
    public void replaceFeature(final Context context, final int classLevel) {
        // Select a Feature to remove
        AlertDialog.Builder removeDialog = new AlertDialog.Builder(context);
        removeDialog.setTitle("Select a " + getName() + " feature to remove");

        removeDialog.setAdapter(new FeatAdapter(context, R.layout.list_feat, _chosenFeatures), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                _chosenFeatures.remove(which);

                // Get a new one to replace
                selectArchetypeFeature(context, classLevel, 1, 1);
            }
        });

        removeDialog.show();
    }

    @Override
    public void selectArchetypeFeature(final Context context, final int classLevel, final int current, final int max) {
        final List<Power> availableFeatures = filterAlreadyChosen(getAllArchetypeFeatures(classLevel));
        AlertDialog.Builder featureSelectionDialog = new AlertDialog.Builder(context);
        featureSelectionDialog.setTitle("Select a " + getName() + " feature" + (max > 1 ? " (" + current + "/" + max + ")" : ""));

        featureSelectionDialog.setAdapter(new FeatAdapter(context, R.layout.list_feat, availableFeatures), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                _chosenFeatures.add(availableFeatures.get(which));

                if (current < max) {
                    selectArchetypeFeature(context, classLevel, current +1, max);
                }
            }
        });

        featureSelectionDialog.show();
    }

    private List<Power> filterAlreadyChosen(List<Power> allFeatures) {
        LinkedList<Power> features = new LinkedList<>();
        mainLoop: for (Power p : allFeatures) {
            for (Power pChosen : _chosenFeatures) {
                if (p._name.equals(pChosen._name)) {
                    continue mainLoop;
                }
            }
            features.add(p);
        }

        return features;
    }

    @Override
    public int nbOfFeatures(int level) {
        return 0;
    }

    @Override
    public void doLevelDown(int oldLevel, int newLevel) {
        while(_chosenFeatures.size() > nbOfFeatures(newLevel)) {
            _chosenFeatures.remove(_chosenFeatures.size() -1);
        }
    }
    // END FEATURES

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeInt(_version);
        objectOutput.writeObject(_chosenStringFeature);
        objectOutput.writeObject(_chosenFeatures);
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        try {
            _version = objectInput.readInt();
            _chosenStringFeature = (String)objectInput.readObject();

            if (_version == 2) {
                _chosenFeatures = (LinkedList<Power>)objectInput.readObject();
            }

        } catch (Exception e) {
            e.printStackTrace();
            _version = 2;
            _chosenStringFeature = null;
        }
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();
        return  fettles;
    }

    @Override
    public LinkedList<Power> getPowers(int iLevel, Character iCharac) {
        return new LinkedList<>();
    }

    @Override
    public List<String> getLevelUpBenefits(int iNewCharacterLevel, Context context) {
        return new LinkedList<>();
    }

    @Override
    public List<Attack> getSpecialClassAttacks(Character iCharacter, int classLevel) {
        return new LinkedList<>();
    }

    @Override
    public int getChoosableFeature(int iLevel) {
        return -1;
    }

    @Override
    public void setArchetypeStringFeature(String iFeature) {
        _chosenStringFeature = iFeature;
    }

    @Override
    public List<Enumerations.Proficiencies> getArmorProficiencies() {
        return new LinkedList<>();
    }

    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        return new LinkedList<>();
    }
}
