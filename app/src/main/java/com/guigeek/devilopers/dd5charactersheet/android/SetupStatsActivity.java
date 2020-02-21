package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.Skill;
import com.guigeek.devilopers.dd5charactersheet.character.races.Human;


import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SetupStatsActivity extends AppCompatActivity {

    Button btnCreate;

    EditText inSTR, inDEX, inCON, inINT, inWIS, inCHA;
    TextView bonusSTR, bonusDEX, bonusCON, bonusINT, bonusWIS, bonusCHA;
    TextView totalSTR, totalDEX, totalCON, totalINT, totalWIS, totalCHA;

    Character _character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_stats);

        // Show level features for the new character
        Bundle bundle = this.getIntent().getExtras();
        Serializable data = bundle.getSerializable(Constants.CHARACTER);
        _character = (Character) data;

        Runnable displayBenefits = new Runnable() {
            @Override
            public void run() {
                String boons = "";

                for (int level = 1; level <= _character._level; ++level) {
                    List<String> levelUpBoons = _character._class.getAllLevelUpBenefits(level, SetupStatsActivity.this);
                    for (String s : levelUpBoons) {
                        if (s != null && s.length() > 0) {
                            boons += s + "\n";
                        }
                    }
                }

                // Show level up window
                AlertDialog alertDialog = new AlertDialog.Builder(SetupStatsActivity.this).create();
                alertDialog.setTitle("Leveled up to " + _character._class.getClassName() + " " + _character._level);
                alertDialog.setMessage(boons);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                // Finally, choose Class skills
                                _character._class.selectSkills(SetupStatsActivity.this, _character);
                                updateStatsBonuses();
                            }
                        });
                alertDialog.show();
            }
        };
        _character._class.doLevelUp(0, _character._level, SetupStatsActivity.this, displayBenefits);

        // Half elf or Human variant: attribute boost to choose
        _character._race.chooseAttributeBoost(SetupStatsActivity.this, _character);


        // UI components
        btnCreate = (Button) findViewById(R.id.btnCreate);

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

        totalSTR = findViewById(R.id.totalSTR);
        totalDEX = findViewById(R.id.totalDEX);
        totalCON = findViewById(R.id.totalCON);
        totalINT = findViewById(R.id.totalINT);
        totalWIS = findViewById(R.id.totalWIS);
        totalCHA = findViewById(R.id.totalCHA);

        TextWatcher textListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                updateStatsTotal();
            }
        };
        inSTR.addTextChangedListener(textListener);
        inDEX.addTextChangedListener(textListener);
        inCON.addTextChangedListener(textListener);
        inINT.addTextChangedListener(textListener);
        inWIS.addTextChangedListener(textListener);
        inCHA.addTextChangedListener(textListener);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStats();
            }
        });
    }

    private void updateStatsBonuses() {
        HashMap<Integer, String> boosts = new HashMap<>();
        boosts.put(Enumerations.Attributes.STR.ordinal(), "0");
        boosts.put(Enumerations.Attributes.DEX.ordinal(), "0");
        boosts.put(Enumerations.Attributes.CON.ordinal(), "0");
        boosts.put(Enumerations.Attributes.INT.ordinal(), "0");
        boosts.put(Enumerations.Attributes.WIS.ordinal(), "0");
        boosts.put(Enumerations.Attributes.CHA.ordinal(), "0");

        for (Fettle boost: _character._race.getAttributeBoost()) {
            boosts.put(boost._describer, "+" + boost._value);
        }

        bonusSTR.setText(boosts.get(Enumerations.Attributes.STR.ordinal()));
        bonusDEX.setText(boosts.get(Enumerations.Attributes.DEX.ordinal()));
        bonusCON.setText(boosts.get(Enumerations.Attributes.CON.ordinal()));
        bonusINT.setText(boosts.get(Enumerations.Attributes.INT.ordinal()));
        bonusWIS.setText(boosts.get(Enumerations.Attributes.WIS.ordinal()));
        bonusCHA.setText(boosts.get(Enumerations.Attributes.CHA.ordinal()));

        updateStatsTotal();
    }

    private void updateStatsTotal() {
        totalSTR.setText("" + (Integer.parseInt(inSTR.getText().toString()) + Integer.parseInt(bonusSTR.getText().toString())));
        totalDEX.setText("" + (Integer.parseInt(inDEX.getText().toString()) + Integer.parseInt(bonusDEX.getText().toString())));
        totalCON.setText("" + (Integer.parseInt(inCON.getText().toString()) + Integer.parseInt(bonusCON.getText().toString())));
        totalINT.setText("" + (Integer.parseInt(inINT.getText().toString()) + Integer.parseInt(bonusINT.getText().toString())));
        totalWIS.setText("" + (Integer.parseInt(inWIS.getText().toString()) + Integer.parseInt(bonusWIS.getText().toString())));
        totalCHA.setText("" + (Integer.parseInt(inCHA.getText().toString()) + Integer.parseInt(bonusCHA.getText().toString())));
    }


    private void setStats() {
        int[] attributes = {
            (bonusSTR.getText().length() > 0 ? Integer.parseInt(bonusSTR.getText().toString()):0) + Integer.parseInt(inSTR.getText().toString()),
            (bonusDEX.getText().length() > 0 ? Integer.parseInt(bonusDEX.getText().toString()):0) + Integer.parseInt(inDEX.getText().toString()),
            (bonusCON.getText().length() > 0 ? Integer.parseInt(bonusCON.getText().toString()):0) + Integer.parseInt(inCON.getText().toString()),
            (bonusINT.getText().length() > 0 ? Integer.parseInt(bonusINT.getText().toString()):0) + Integer.parseInt(inINT.getText().toString()),
            (bonusWIS.getText().length() > 0 ? Integer.parseInt(bonusWIS.getText().toString()):0) + Integer.parseInt(inWIS.getText().toString()),
            (bonusCHA.getText().length() > 0 ? Integer.parseInt(bonusCHA.getText().toString()):0) + Integer.parseInt(inCHA.getText().toString())};

        _character._attributes = attributes;

        Intent intent = new Intent(SetupStatsActivity.this, MainActivity.class);
        intent.putExtra(Constants.CHARACTER, _character);
        setResult(RESULT_OK, intent);
        finish();
    }
}
