package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Class;
import com.guigeek.devilopers.dd5charactersheet.character.classes.artificer.Artificer;
import com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian.Barbarian;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bard.Bard;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bloodhunter.BloodHunter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid;
import com.guigeek.devilopers.dd5charactersheet.character.classes.monk.Monk;
import com.guigeek.devilopers.dd5charactersheet.character.classes.paladin.Paladin;
import com.guigeek.devilopers.dd5charactersheet.character.classes.rogue.Rogue;
import com.guigeek.devilopers.dd5charactersheet.character.classes.sorcerer.Sorcerer;
import com.guigeek.devilopers.dd5charactersheet.character.classes.warlock.Warlock;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard;
import com.guigeek.devilopers.dd5charactersheet.character.classes.fighter.Fighter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.ranger.Ranger;
import com.guigeek.devilopers.dd5charactersheet.character.races.Dragonborn;
import com.guigeek.devilopers.dd5charactersheet.character.races.Elf;
import com.guigeek.devilopers.dd5charactersheet.character.races.Gnome;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfElf;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfOrc;
import com.guigeek.devilopers.dd5charactersheet.character.races.Halfling;
import com.guigeek.devilopers.dd5charactersheet.character.races.Human;
import com.guigeek.devilopers.dd5charactersheet.character.races.Dwarf;
import com.guigeek.devilopers.dd5charactersheet.character.races.Race;
import com.guigeek.devilopers.dd5charactersheet.character.races.Tiefling;

public class CreateCharacter extends AppCompatActivity {

    Button btnCreate;
    Spinner spRace, spClass, spSubRace, spBackground;
    EditText inName;
    TextView attributesHelp, backgroundHelp;

    Race aRace = new Dragonborn();
    Enumerations.Backgrounds aBg = Enumerations.Backgrounds.ACOLYTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        btnCreate = findViewById(R.id.btnCreate);
        spRace = findViewById(R.id.spinnerRace);
        spSubRace = findViewById(R.id.spinnerSubRace);
        spClass = findViewById(R.id.spinnerClass);
        inName = findViewById(R.id.inName);
        spBackground = findViewById(R.id.spinnerBackground);
        backgroundHelp = findViewById(R.id.backgroundHelp);

        attributesHelp = findViewById(R.id.attributesHelp);
        updateStatsBonuses();

        final String[] backgrounds = new String[Enumerations.Backgrounds.values().length];
        int index = 0;
        for (Enumerations.Backgrounds bg : Enumerations.Backgrounds.values()) {
            backgrounds[index++] = bg._name;
        }
        spBackground.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, backgrounds));
        spBackground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aBg = Enumerations.Backgrounds.values()[i];
                updateBgHelp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<CharSequence> adapterRace = ArrayAdapter.createFromResource(this, R.array.races, android.R.layout.simple_spinner_item);
        adapterRace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRace.setAdapter(adapterRace);

        spRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String aRaceName = (String)spRace.getSelectedItem();
                if (aRaceName.equals(App.getResString(R.string.race_half_elf))) {
                    aRace = new HalfElf();
                } else if (aRaceName.equals(App.getResString(R.string.race_half_orc))) {
                    aRace = new HalfOrc();
                } else if (aRaceName.equals(App.getResString(R.string.race_human))) {
                    aRace = new Human();
                } else if (aRaceName.equals(App.getResString(R.string.race_dwarf))) {
                    aRace = new Dwarf();
                } else if (aRaceName.equals(App.getResString(R.string.race_elf))) {
                    aRace = new Elf();
                } else if (aRaceName.equals(App.getResString(R.string.race_dragonborn))) {
                    aRace = new Dragonborn();
                } else if (aRaceName.equals(App.getResString(R.string.race_tiefling))) {
                    aRace = new Tiefling();
                } else if (aRaceName.equals(App.getResString(R.string.race_gnome))) {
                    aRace = new Gnome();
                } else if (aRaceName.equals(App.getResString(R.string.race_halfling))) {
                    aRace = new Halfling();
                }

                // Handle subraces
                int subRacesArrayId = aRace.getSubraceArrayId();
                if (subRacesArrayId == -1) {
                    aRace.setSubRace(null);
                    spSubRace.setVisibility(View.INVISIBLE);
                }
                else {
                    spSubRace.setVisibility(View.VISIBLE);
                    ArrayAdapter<CharSequence> adapterSubRace = ArrayAdapter.createFromResource(CreateCharacter.this, subRacesArrayId, android.R.layout.simple_spinner_item);
                    adapterSubRace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spSubRace.setAdapter(adapterSubRace);
                    aRace.setSubRace(getResources().getStringArray(subRacesArrayId)[0]);
                }

                updateStatsBonuses();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spSubRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String aSubRaceName = (String)spSubRace.getSelectedItem();
                aRace.setSubRace(aSubRaceName);
                updateStatsBonuses();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ArrayAdapter<CharSequence> adapterClass = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);
        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClass.setAdapter(adapterClass);

        btnCreate.setOnClickListener(new CreateListener());
        updateBgHelp();
    }

    private void updateBgHelp() {
        backgroundHelp.setText("Skill proficiencies: " + aBg._firstSkill.toString() + ", " + aBg._secondSkill.toString());
    }

    private void updateStatsBonuses() {
        attributesHelp.setText(aRace.getAttributeBoostDescription());
    }

    private class CreateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Class aClass = null;
            String aClassName = (String)spClass.getSelectedItem();
            if (aClassName.equals(App.getResString(R.string.class_barbarian))) {
                aClass = new Barbarian();
            } else if (aClassName.equals(App.getResString(R.string.class_paladin))) {
                aClass = new Paladin();
            } else if (aClassName.equals(App.getResString(R.string.class_warlock))) {
                aClass = new Warlock();
            } else if (aClassName.equals(App.getResString(R.string.class_rogue))) {
                aClass = new Rogue();
            } else if (aClassName.equals(App.getResString(R.string.class_sorcerer))) {
                aClass = new Sorcerer();
            } else if (aClassName.equals(App.getResString(R.string.class_blood_hunter))) {
                aClass = new BloodHunter();
            } else if (aClassName.equals(App.getResString(R.string.class_monk))) {
                aClass = new Monk();
            } else if (aClassName.equals(App.getResString(R.string.class_bard))) {
                aClass = new Bard();
            } else if (aClassName.equals(App.getResString(R.string.class_cleric))) {
                aClass = new Cleric();
            } else if (aClassName.equals(App.getResString(R.string.class_druid))) {
                aClass = new Druid();
            } else if (aClassName.equals(App.getResString(R.string.class_wizard))) {
                aClass = new Wizard();
            } else if (aClassName.equals(App.getResString(R.string.class_fighter))) {
                aClass = new Fighter();
            } else if (aClassName.equals(App.getResString(R.string.class_ranger))) {
                aClass = new Ranger();
            } else if (aClassName.equals(App.getResString(R.string.class_artificer))) {
                aClass = new Artificer();
            }

            Log.d("Create", "Selected race: " + aRace.getBaseRaceName());
            Log.d("Create", "Selected class: " + aClass.getQualifiedClassName());

            createCharacter(aRace, aClass);
        }
    }

    private void createCharacter(Race aRace, Class aClass) {
        int[] attributes = {10, 10, 10, 10, 10, 10};
        String selectedBackground = (String)spBackground.getSelectedItem();

        final Character aHero = new Character(inName.getText().toString(), aClass, aRace, 1, attributes, null, 0, aBg);
        aHero.addSkillProficiency(aBg._firstSkill.toString());
        aHero.addSkillProficiency(aBg._secondSkill.toString());

        Log.d("Create", aHero.toString());

        Intent intent = new Intent(CreateCharacter.this, SetupStatsActivity.class);
        intent.putExtra(Constants.CHARACTER, aHero);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Next step finished, just go back to previous activity
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = new Intent(CreateCharacter.this, SetupStatsActivity.class);
        Character aHero = null;
        if (data != null && data.getSerializableExtra(Constants.CHARACTER) != null) {
            try {
                aHero = (Character) data.getSerializableExtra(Constants.CHARACTER);
            } catch (Exception e) {
                Log.d("TOTO", "Creation failed at some point.");
                e.printStackTrace();
            }
        }
        intent.putExtra(Constants.CHARACTER, aHero);
        setResult(RESULT_OK, intent);
        finish();
    }
}
