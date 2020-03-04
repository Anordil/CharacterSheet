package com.guigeek.devilopers.dd5charactersheet.character.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.guigeek.devilopers.dd5charactersheet.NameAndDescription;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.android.ArrayWithDescriptionAdapter;
import com.guigeek.devilopers.dd5charactersheet.android.FeatAdapter;
import com.guigeek.devilopers.dd5charactersheet.android.StringListAdapter;
import com.guigeek.devilopers.dd5charactersheet.character.Attack;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.Skill;
import com.guigeek.devilopers.dd5charactersheet.character.races.Human;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by totou on 15/06/2016.
 */
public abstract class BaseClass implements Class, Externalizable {
    static final long serialVersionUID = 2000L;

    protected int _version = 2;
    protected LinkedList<Archetype> _archetypes = new LinkedList<>();

    public Archetype getArchetype(int index) {
        return _archetypes.get(index);
    }

    public int getChoosableArchetypes(int iNewlevel) {
        return -1;
    }


    // FEATURES
    public List<Power> _chosenFeatures = new LinkedList<>();

    @Override
    public int gainedClassFeatures(int classLevel) {
        return 0;
    }

    @Override
    public List<Power> getAllClassFeatures(int iClassLevel) {
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
        removeDialog.setTitle("Select a " + getClassName() + " feature to remove");

        removeDialog.setAdapter(new FeatAdapter(context, R.layout.list_feat, _chosenFeatures), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                _chosenFeatures.remove(which);

                // Get a new one to replace
                selectClassFeature(context, classLevel, 1, 1);
            }
        });

        removeDialog.show();
    }

    @Override
    public String getFeatureName() {
        return getClassName() + " feature";
    }

    @Override
    public void selectClassFeature(final Context context, final int classLevel, final int current, final int max) {
        final List<Power> availableFeatures = filterAlreadyChosen(getAllClassFeatures(classLevel));
        AlertDialog.Builder featureSelectionDialog = new AlertDialog.Builder(context);
        featureSelectionDialog.setTitle("Select a " + getFeatureName() + (max > 1 ? " (" + current + "/" + max + ")" : ""));

        featureSelectionDialog.setAdapter(new FeatAdapter(context, R.layout.list_feat, availableFeatures), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                _chosenFeatures.add(availableFeatures.get(which));

                if (current < max) {
                    selectClassFeature(context, classLevel, current +1, max);
                }
            }
        });

        featureSelectionDialog.show();
    }

    private List<Power> filterAlreadyChosen(List<Power> allClassFeatures) {
        LinkedList<Power> features = new LinkedList<>();
        mainLoop: for (Power p : allClassFeatures) {
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
    public int nbOfFeatures(int iLevel) {
        return 0;
    }
    // END FEATURES


    protected int[][] _spellSlots = {
            // spell level 0-9
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //character lv 1
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 5
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 10
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//lv 15
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//ln 20
    };

    protected int[][] _spellsKnown = {
            // cantrips, spells
            {0, 0},
            {0, 0}, //character lv 1
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},//lv 5
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},//lv 10
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},//lv 15
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0}//ln 20
    };

    @Override
    public int[] getSpellSlots(int iCharacterLevel) {
        return _spellSlots[Math.min(20, iCharacterLevel)];
    }

    @Override
    public boolean isCaster() {
        return false;
    }

    @Override
    public int[] getSpellsKnown(int iCharacterLevel) {
        return _spellsKnown[Math.min(20, iCharacterLevel)];
    }

    @Override
    public int getAC(Character character) {
        int ac = character._equippedArmor == null ? 10 : character._equippedArmor.getAC(character);

        if (character._equippedShield != null && character._equippedShield._type == Enumerations.ArmorTypes.SHIELD) {
            ac+= character._equippedShield.getAC(character);
        }

        return ac;
    }

    @Override
    public Enumerations.Attributes getMainSpellAttribute() {
        return Enumerations.Attributes.CHA;
    }

    @Override
    public void addArchetype(final Archetype iArchetype, final int iNewCharacterLevel, final Context context) {
        if (_archetypes == null) {
            _archetypes = new LinkedList<>();
        }
        _archetypes.add(iArchetype);
    }

    @Override
    public void doLevelDown(int oldLevel, int newLevel) {
        if (newLevel < 3) {
            _archetypes.clear();
        }

        while(_chosenFeatures.size() > nbOfFeatures(newLevel)) {
            _chosenFeatures.remove(_chosenFeatures.size() -1);
        }

        for (Archetype arc: _archetypes) {
            arc.doLevelDown(oldLevel, newLevel);
        }
    }

    @Override
    public String getQualifiedClassName() {
        String name = getClassName();

        if (_archetypes != null && _archetypes.size() > 0) {
            name += " (";
            for (int i = 0; i < _archetypes.size(); ++i) {
                name += _archetypes.get(i).getName();
                if (i < _archetypes.size() -1) {
                    name += ", ";
                }
            }
            name += ")";
        }
        return name;
    }

    @Override
    public int getAttacksPerRound(Character iCharacter, int classLevel) {
        return 1;
    }

    @Override
    public List<String> getAllLevelUpBenefits(final int iNewCharacterLevel, final Context context) {
        // Get level up perks from the class
        List<String> allItems = getLevelUpBenefits(iNewCharacterLevel, context);

        // New Class feature gained?
        if (gainedClassFeatures(iNewCharacterLevel) > 0) {
            selectClassFeature(context, iNewCharacterLevel, 1, gainedClassFeatures(iNewCharacterLevel));
        }

        // Feature replacement available? Display first --> code runs 2nd
        if (canReplaceFeature(iNewCharacterLevel)) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            dialog.dismiss();
                            replaceFeature(context, iNewCharacterLevel);

                        case DialogInterface.BUTTON_NEGATIVE:
                            //No button clicked -> nothing to do
                            dialog.dismiss();
                    }
                }
            };

            AlertDialog.Builder yesNoDialog = new AlertDialog.Builder(context);
            yesNoDialog.setMessage("Do you want to replace a " + getFeatureName() + "?")
                    .setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener)
                    .show();
        }


        for (final Archetype arc: _archetypes) {
            // Archetype level up perks
            allItems.addAll(arc.getLevelUpBenefits(iNewCharacterLevel, context));

            // New Archetype Power feature gained?
            if (arc.gainedArchetypeFeatures(iNewCharacterLevel) > 0) {
                arc.selectArchetypeFeature(context, iNewCharacterLevel, 1, arc.gainedArchetypeFeatures(iNewCharacterLevel));
            }
            // Archetype Power feature replacement?
            if (arc.canReplaceFeature(iNewCharacterLevel)) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                dialog.dismiss();
                                arc.replaceFeature(context, iNewCharacterLevel);

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked -> nothing to do
                                dialog.dismiss();
                        }
                    }
                };

                AlertDialog.Builder yesNoDialog = new AlertDialog.Builder(context);
                yesNoDialog.setMessage("Do you want to replace a " + arc.getFeatureName() + "?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener)
                        .show();
            }

            // String-like feature for Archetype?
            int choosableFeatureArray = arc.getChoosableFeature(iNewCharacterLevel);
            if (choosableFeatureArray != -1) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Select a " + arc.getName() + " feature");

                final String[] allOptions = context.getResources().getStringArray(choosableFeatureArray);
                final List<String> allOptionsList = Arrays.asList(allOptions);

                b.setAdapter(new StringListAdapter(context, R.layout.list_string, allOptionsList), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        String selectedOption = allOptions[which];
                        arc.setArchetypeStringFeature(selectedOption);
                    }
                });

                b.show();
            }
        }

        return allItems;
    }

    @Override
    public void doLevelUp(int oldLevel, int newLevel, final Context context, final Runnable showBenefits) {
        final int[] openedDialogs = {0};
        for (int i = oldLevel +1; i <= newLevel; ++i) {
            // New archetype may be added for this level
            int choosableArchetypesArray = getChoosableArchetypes(i);
            if (choosableArchetypesArray != -1) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Select a sub class");
                openedDialogs[0]++;

                final String[] allOptions = context.getResources().getStringArray(choosableArchetypesArray);
                final List<NameAndDescription> allOptionsList = new LinkedList<>();
                for (String archetypeName: allOptions) {
                    allOptionsList.add(getArchetypeByName(archetypeName));
                }

                final int level = i;

                b.setAdapter(new ArrayWithDescriptionAdapter(context, R.layout.list_name_and_description, allOptionsList, true), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        openedDialogs[0]--;
                        String selectedOption = allOptions[which];
                        addArchetype(getArchetypeByName(selectedOption), level, context);

                        if (openedDialogs[0] == 0 && showBenefits != null) {
                            showBenefits.run();
                        }
                    }
                });

                b.show();
            }
        }

        if (openedDialogs[0] == 0 && showBenefits != null) {
            showBenefits.run();
        }
    }

    @Override
    public Archetype getArchetypeByName(String iName) {
        return null;
    }

    @Override
    public LinkedList<Power> getAllPowers(int iLevel, Character iCharac) {
        LinkedList<Power> allItems = getPowers(iLevel, iCharac);
        if (_archetypes != null) {
            for (Archetype arc: _archetypes) {
                allItems.addAll(arc.getPowers(iLevel, iCharac));
            }
        }

        return allItems;
    }

    @Override
    public LinkedList<Fettle> getFettles(Character character, int classLevel) {
        LinkedList<Fettle> fettles = new LinkedList<Fettle>();

        return fettles;
    }

    @Override
    public LinkedList<Fettle> getAllFettles(Character character, int classLevel) {
        LinkedList<Fettle> allItems = getFettles(character, classLevel);
        if (_archetypes != null) {
            for (Archetype arc: _archetypes) {
                allItems.addAll(arc.getFettles(character, classLevel));
            }
        }

        return allItems;
    }


    @Override
    public void writeExternal(ObjectOutput oo) throws IOException
    {
        oo.writeInt(_version);
        oo.writeObject(_archetypes);
        oo.writeObject(_spellSlots);
        oo.writeObject(_spellsKnown);
        oo.writeObject(_chosenFeatures);
    }

    @Override
    public void readExternal(ObjectInput oi) throws IOException, ClassNotFoundException
    {
        int version = oi.readInt();
        _version = version;
        if (version >= 1) {
            _archetypes = (LinkedList<Archetype>) oi.readObject();
            _spellSlots = (int[][])oi.readObject();
            _spellsKnown = (int[][])oi.readObject();
        }
        if (version >= 2) {
            _chosenFeatures = (LinkedList<Power>) oi.readObject();
        }
    }

    @Override
    public List<Attack> getSpecialClassAttacks(Character iCharacter, int classLevel) {
        return new LinkedList<>();
    }

    @Override
    public List<Attack> getAllSpecialClassAttacks(Character iCharacter, int classLevel) {
        List<Attack> allAttacks = getSpecialClassAttacks(iCharacter, classLevel);

        for (Archetype arc: _archetypes) {
            allAttacks.addAll(arc.getSpecialClassAttacks(iCharacter, classLevel));
        }
        return allAttacks;
    }

    @Override
    public int getClassSkillCount() {
        return 2;
    }

    @Override
    public void selectSkills(final Context context, Character iCharac) {
        selectClassSkill(context, iCharac, 1, iCharac._class.getClassSkillCount());
    }

    private void selectClassSkill(final Context context, final Character iCharac, final int current, final int max) {
        final List<String> availableSkills = filterSkills(iCharac, iCharac._class.getClassSkills());
        AlertDialog.Builder skillSelectionDialog = new AlertDialog.Builder(context);
        skillSelectionDialog.setTitle("Select a " + getClassName() + " skill proficiency" + (max > 1 ? " (" + current + "/" + max + ")" : ""));

        skillSelectionDialog.setAdapter(new StringListAdapter(context, R.layout.list_string, availableSkills), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String skillName = availableSkills.get(which);
                iCharac.addSkillProficiency(skillName);

                if (current < max) {
                    selectClassSkill(context, iCharac, current +1, max);
                } else if (current == max && iCharac._race.getSubRace() != null && iCharac._race.getSubRace().equals("Variant")) {
                    // Human variant: extra Skill
                    String[] allSkills = new String[] {
                            Enumerations.Skills.ANIMAL_HANDLING.toString(),
                            Enumerations.Skills.ACROBATICS.toString(),
                            Enumerations.Skills.ARCANA.toString(),
                            Enumerations.Skills.ATHLETICS.toString(),
                            Enumerations.Skills.DECEPTION.toString(),
                            Enumerations.Skills.HISTORY.toString(),
                            Enumerations.Skills.INSIGHT.toString(),
                            Enumerations.Skills.INTIMIDATION.toString(),
                            Enumerations.Skills.INVESTIHATION.toString(),
                            Enumerations.Skills.NATURE.toString(),
                            Enumerations.Skills.MEDICINE.toString(),
                            Enumerations.Skills.PERCEPTION.toString(),
                            Enumerations.Skills.PERFORMANCE.toString(),
                            Enumerations.Skills.PERSUASION.toString(),
                            Enumerations.Skills.RELIGION.toString(),
                            Enumerations.Skills.SLEIGHT_OF_HAND.toString(),
                            Enumerations.Skills.STEALTH.toString(),
                            Enumerations.Skills.SURVIVAL.toString(),
                    };
                    final List<String> availableHumanSkills = filterSkills(iCharac, allSkills);
                    AlertDialog.Builder skillSelectionDialogHuman = new AlertDialog.Builder(context);
                    skillSelectionDialogHuman.setTitle("Select a bonus skill (Human Variant)");

                    skillSelectionDialogHuman.setAdapter(new StringListAdapter(context, R.layout.list_string, availableHumanSkills), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            String skillName = availableHumanSkills.get(which);
                            iCharac.addSkillProficiency(skillName);
                        }
                    });
                    skillSelectionDialogHuman.show();
                }
            }
        });

        skillSelectionDialog.show();
    }

    private List<String> filterSkills(Character iCharac, String[] skillsToFilter) {
        LinkedList<String> availableSkills = new LinkedList<>();

        for (String skillName : skillsToFilter) {
            for (Skill skill: iCharac._skills) {
                if (skill._name.equals(skillName)) {
                    if (!skill._isProficient) {
                        availableSkills.add(skillName);
                    }
                }
            }
        }

        return availableSkills;
    }

    @Override
    public List<Enumerations.Proficiencies> getAllArmorProficiencies() {
        List<Enumerations.Proficiencies> proficiencies = getArmorProficiencies();

        for (Archetype arc: _archetypes) {
            proficiencies.addAll(arc.getArmorProficiencies());
        }

        return proficiencies;
    }

    @Override
    public List<Enumerations.Proficiencies> getAllWeaponProficiencies() {
        List<Enumerations.Proficiencies> proficiencies = getWeaponProficiencies();

        for (Archetype arc: _archetypes) {
            proficiencies.addAll(arc.getWeaponProficiencies());
        }

        return proficiencies;
    }

    @Override
    public List<Enumerations.Proficiencies> getArmorProficiencies() {
        return new LinkedList<>();
    }

    @Override
    public List<Enumerations.Proficiencies> getWeaponProficiencies() {
        return new LinkedList<>();
    }

    @Override
    public String getName() {
        return getClassName();
    }

    @Override
    public String getDescription() {
        String description = "Hit die: D" + getHitDie() + "\n";
        if (isCaster()) {
            description += "Spell caster (" + getMainSpellAttribute().getShortName().toUpperCase() + ")\n";
        }
        description += "Proficiencies\n";
        description += "  Armor: " + (getArmorProficiencies().isEmpty() ? "None" : TextUtils.join(", ",getArmorProficiencies())) + "\n";
        description += "  Weapons: " + (getWeaponProficiencies().isEmpty() ? "None" : TextUtils.join(", ",getWeaponProficiencies())) + "\n";
        description += "  Skills: choose " + getClassSkillCount() + " from " + TextUtils.join(", ", getClassSkills()) + "\n";
        description += "  Saving throws: " + TextUtils.join(", ", getSavingThrowsProficiencies()) ;

        return description;
    }
}
