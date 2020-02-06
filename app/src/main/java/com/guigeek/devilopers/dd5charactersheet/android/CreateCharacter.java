package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.guigeek.devilopers.dd5charactersheet.character.*;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Class;
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

import java.util.HashMap;
import java.util.List;

public class CreateCharacter extends AppCompatActivity {

    Button btnCreate, btnDone;
    Spinner spRace, spClass, spSubRace;
    EditText inName;

    EditText inSTR, inDEX, inCON, inINT, inWIS, inCHA;
    TextView bonusSTR, bonusDEX, bonusCON, bonusINT, bonusWIS, bonusCHA;
    TextView attributesHelp;

    Race aRace = new Dragonborn();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnDone = (Button) findViewById(R.id.btnDone);
        spRace = (Spinner) findViewById(R.id.spinnerRace);
        spSubRace = (Spinner) findViewById(R.id.spinnerSubRace);
        spClass = (Spinner) findViewById(R.id.spinnerClass);
        inName = (EditText)findViewById(R.id.inName);

        inSTR = findViewById(R.id.inSTR);
        inDEX = findViewById(R.id.inDEX);
        inCON = findViewById(R.id.inCON);
        inINT = findViewById(R.id.inINT);
        inWIS = findViewById(R.id.inWIS);
        inCHA = findViewById(R.id.inCHA);

        bonusSTR = findViewById(R.id.bonusSTR);
        bonusDEX = findViewById(R.id.bonusDEX);
        bonusCON = findViewById(R.id.bonusCON);
        bonusINT = findViewById(R.id.bonusINT);
        bonusWIS = findViewById(R.id.bonusWIS);
        bonusCHA = findViewById(R.id.bonusCHA);
        attributesHelp = findViewById(R.id.attributesHelp);
        updateStatsBonuses();


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
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateStatsBonuses() {
        HashMap<Integer, String> boosts = new HashMap<>();
        boosts.put(Enumerations.Attributes.STR.ordinal(), "");
        boosts.put(Enumerations.Attributes.DEX.ordinal(), "");
        boosts.put(Enumerations.Attributes.CON.ordinal(), "");
        boosts.put(Enumerations.Attributes.INT.ordinal(), "");
        boosts.put(Enumerations.Attributes.WIS.ordinal(), "");
        boosts.put(Enumerations.Attributes.CHA.ordinal(), "");

        for (Fettle boost: aRace.getAttributeBoost()) {
            boosts.put(boost._describer, "+" + boost._value);
        }

        bonusSTR.setText(boosts.get(Enumerations.Attributes.STR.ordinal()));
        bonusDEX.setText(boosts.get(Enumerations.Attributes.DEX.ordinal()));
        bonusCON.setText(boosts.get(Enumerations.Attributes.CON.ordinal()));
        bonusINT.setText(boosts.get(Enumerations.Attributes.INT.ordinal()));
        bonusWIS.setText(boosts.get(Enumerations.Attributes.WIS.ordinal()));
        bonusCHA.setText(boosts.get(Enumerations.Attributes.CHA.ordinal()));

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
            }

            Log.d("Create", "Selected race: " + aRace.getBaseRaceName());
            Log.d("Create", "Selected class: " + aClass.getQualifiedClassName());

            createCharacter(aRace, aClass);
        }
    }

    private void createCharacter(Race aRace, Class aClass) {
        int[] attributes = {
            (bonusSTR.getText().length() > 0 ? Integer.parseInt(bonusSTR.getText().toString()):0) + Integer.parseInt(inSTR.getText().toString()),
            (bonusDEX.getText().length() > 0 ? Integer.parseInt(bonusDEX.getText().toString()):0) + Integer.parseInt(inDEX.getText().toString()),
            (bonusCON.getText().length() > 0 ? Integer.parseInt(bonusCON.getText().toString()):0) + Integer.parseInt(inCON.getText().toString()),
            (bonusINT.getText().length() > 0 ? Integer.parseInt(bonusINT.getText().toString()):0) + Integer.parseInt(inINT.getText().toString()),
            (bonusWIS.getText().length() > 0 ? Integer.parseInt(bonusWIS.getText().toString()):0) + Integer.parseInt(inWIS.getText().toString()),
            (bonusCHA.getText().length() > 0 ? Integer.parseInt(bonusCHA.getText().toString()):0) + Integer.parseInt(inCHA.getText().toString())};
        final Character aHero = new Character(inName.getText().toString(), aClass, aRace, 1, attributes, null, 0);

        Log.d("Create", aHero.toString());

        Intent intent = new Intent(CreateCharacter.this, MainActivity.class);
        intent.putExtra(Constants.CHARACTER, aHero);
        setResult(RESULT_OK, intent);

        List<String> levelUpBoons = aHero._class.getAllLevelUpBenefits(1, CreateCharacter.this);

        Runnable displayBenefits = new Runnable() {
            @Override
            public void run() {
                String boons = "";

                for (int level = 1; level <= aHero._level; ++level) {
                    List<String> levelUpBoons = aHero._class.getAllLevelUpBenefits(level, CreateCharacter.this);
                    for (String s : levelUpBoons) {
                        if (s != null && s.length() > 0) {
                            boons += s + "\n";
                        }
                    }
                }

                // Show level up window
                AlertDialog alertDialog = new AlertDialog.Builder(CreateCharacter.this).create();
                alertDialog.setTitle("Leveled up to " + aHero._class.getClassName() + " " + aHero._level);
                alertDialog.setMessage(boons);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                findViewById(R.id.createCharacterLayout).setVisibility(View.GONE);
                                btnDone.setVisibility(View.VISIBLE);
                            }
                        });
                alertDialog.show();
            }
        };

        aHero._class.doLevelUp(0, aHero._level, CreateCharacter.this, displayBenefits);
    }
}
