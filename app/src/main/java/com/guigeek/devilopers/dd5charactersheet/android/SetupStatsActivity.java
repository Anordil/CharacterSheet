package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;



import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class SetupStatsActivity extends AppCompatActivity {

    Button btnCreate;

    EditText inSTR, inDEX, inCON, inINT, inWIS, inCHA;
    TextView bonusSTR, bonusDEX, bonusCON, bonusINT, bonusWIS, bonusCHA;
    TextView totalSTR, totalDEX, totalCON, totalINT, totalWIS, totalCHA;

    Spinner spinnerStatsMethod;
    TextView pointsRemaining;
    Button btnSubSTR, btnAddSTR, btnSubDEX, btnAddDEX, btnSubCON, btnAddCON, btnSubINT, btnAddINT, btnSubWIS, btnAddWIS, btnSubCHA, btnAddCHA;

    Character _character;

    int pointsLeft = 27;

    final HashMap<Enumerations.Attributes, Integer> stats = new HashMap<>();

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
        btnCreate = findViewById(R.id.btnCreate);

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

        spinnerStatsMethod = findViewById(R.id.spinnerStatsMethod);
        ArrayAdapter<CharSequence> adapterPointStyle = ArrayAdapter.createFromResource(this, R.array.point_options, android.R.layout.simple_spinner_item);
        adapterPointStyle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatsMethod.setAdapter(adapterPointStyle);
        spinnerStatsMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    // manual
                    initManual();
                } else {
                    // point buy
                    initPointBuy();
                }

                updateStatsInputFromhashmap();
                setValuesInTotals();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pointsRemaining = findViewById(R.id.pointsRemaining);
        btnSubSTR = findViewById(R.id.btnSubSTR);
        btnAddSTR = findViewById(R.id.btnAddSTR);
        btnSubDEX = findViewById(R.id.btnSubDEX);
        btnAddDEX = findViewById(R.id.btnAddDEX);
        btnSubCON = findViewById(R.id.btnSubCON);
        btnAddCON = findViewById(R.id.btnAddCON);
        btnSubINT = findViewById(R.id.btnSubINT);
        btnAddINT = findViewById(R.id.btnAddINT);
        btnSubWIS = findViewById(R.id.btnSubWIS);
        btnAddWIS = findViewById(R.id.btnAddWIS);
        btnSubCHA = findViewById(R.id.btnSubCHA);
        btnAddCHA = findViewById(R.id.btnAddCHA);
        // Substract points
        btnSubSTR.setOnClickListener(new OnSubstractListener(Enumerations.Attributes.STR));
        btnSubDEX.setOnClickListener(new OnSubstractListener(Enumerations.Attributes.DEX));
        btnSubCON.setOnClickListener(new OnSubstractListener(Enumerations.Attributes.CON));
        btnSubINT.setOnClickListener(new OnSubstractListener(Enumerations.Attributes.INT));
        btnSubWIS.setOnClickListener(new OnSubstractListener(Enumerations.Attributes.WIS));
        btnSubCHA.setOnClickListener(new OnSubstractListener(Enumerations.Attributes.CHA));
        // Add points
        btnAddSTR.setOnClickListener(new OnAddListener(Enumerations.Attributes.STR));
        btnAddDEX.setOnClickListener(new OnAddListener(Enumerations.Attributes.DEX));
        btnAddCON.setOnClickListener(new OnAddListener(Enumerations.Attributes.CON));
        btnAddINT.setOnClickListener(new OnAddListener(Enumerations.Attributes.INT));
        btnAddWIS.setOnClickListener(new OnAddListener(Enumerations.Attributes.WIS));
        btnAddCHA.setOnClickListener(new OnAddListener(Enumerations.Attributes.CHA));

        inSTR.addTextChangedListener(new StatWatcher(inSTR, Enumerations.Attributes.STR));
        inDEX.addTextChangedListener(new StatWatcher(inDEX, Enumerations.Attributes.DEX));
        inCON.addTextChangedListener(new StatWatcher(inCON, Enumerations.Attributes.CON));
        inINT.addTextChangedListener(new StatWatcher(inINT, Enumerations.Attributes.INT));
        inWIS.addTextChangedListener(new StatWatcher(inWIS, Enumerations.Attributes.WIS));
        inCHA.addTextChangedListener(new StatWatcher(inCHA, Enumerations.Attributes.CHA));


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStats();
            }
        });

        initManual();
        updateStatsInputFromhashmap();
        setValuesInTotals();
    }

    private void initManual() {
        btnSubSTR.setVisibility(View.GONE);
        btnAddSTR.setVisibility(View.GONE);
        btnSubDEX.setVisibility(View.GONE);
        btnAddDEX.setVisibility(View.GONE);
        btnSubCON.setVisibility(View.GONE);
        btnAddCON.setVisibility(View.GONE);
        btnSubINT.setVisibility(View.GONE);
        btnAddINT.setVisibility(View.GONE);
        btnSubWIS.setVisibility(View.GONE);
        btnAddWIS.setVisibility(View.GONE);
        btnSubCHA.setVisibility(View.GONE);
        btnAddCHA.setVisibility(View.GONE);
        pointsRemaining.setVisibility(View.GONE);

        inSTR.setEnabled(true);
        inDEX.setEnabled(true);
        inCON.setEnabled(true);
        inINT.setEnabled(true);
        inWIS.setEnabled(true);
        inCHA.setEnabled(true);

        stats.put(Enumerations.Attributes.STR, new Integer(10));
        stats.put(Enumerations.Attributes.DEX, new Integer(10));
        stats.put(Enumerations.Attributes.CON, new Integer(10));
        stats.put(Enumerations.Attributes.INT, new Integer(10));
        stats.put(Enumerations.Attributes.WIS, new Integer(10));
        stats.put(Enumerations.Attributes.CHA, new Integer(10));

        btnCreate.setEnabled(true);
    }

    private void initPointBuy() {
        inSTR.setEnabled(false);
        inDEX.setEnabled(false);
        inCON.setEnabled(false);
        inINT.setEnabled(false);
        inWIS.setEnabled(false);
        inCHA.setEnabled(false);

        pointsLeft = 27;
        btnCreate.setEnabled(false);
        stats.put(Enumerations.Attributes.STR, new Integer(8));
        stats.put(Enumerations.Attributes.DEX, new Integer(8));
        stats.put(Enumerations.Attributes.CON, new Integer(8));
        stats.put(Enumerations.Attributes.INT, new Integer(8));
        stats.put(Enumerations.Attributes.WIS, new Integer(8));
        stats.put(Enumerations.Attributes.CHA, new Integer(8));

        btnSubSTR.setVisibility(View.VISIBLE);
        btnAddSTR.setVisibility(View.VISIBLE);
        btnSubDEX.setVisibility(View.VISIBLE);
        btnAddDEX.setVisibility(View.VISIBLE);
        btnSubCON.setVisibility(View.VISIBLE);
        btnAddCON.setVisibility(View.VISIBLE);
        btnSubINT.setVisibility(View.VISIBLE);
        btnAddINT.setVisibility(View.VISIBLE);
        btnSubWIS.setVisibility(View.VISIBLE);
        btnAddWIS.setVisibility(View.VISIBLE);
        btnSubCHA.setVisibility(View.VISIBLE);
        btnAddCHA.setVisibility(View.VISIBLE);
        pointsRemaining.setVisibility(View.VISIBLE);

        checkDisableSubstract(Enumerations.Attributes.STR);
        checkDisableSubstract(Enumerations.Attributes.DEX);
        checkDisableSubstract(Enumerations.Attributes.CON);
        checkDisableSubstract(Enumerations.Attributes.INT);
        checkDisableSubstract(Enumerations.Attributes.WIS);
        checkDisableSubstract(Enumerations.Attributes.CHA);

        checkDisableAdd(Enumerations.Attributes.STR);
        checkDisableAdd(Enumerations.Attributes.DEX);
        checkDisableAdd(Enumerations.Attributes.CON);
        checkDisableAdd(Enumerations.Attributes.INT);
        checkDisableAdd(Enumerations.Attributes.WIS);
        checkDisableAdd(Enumerations.Attributes.CHA);
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

        updateStatsFromIpnut();
        setValuesInTotals();
    }

    private void updateStatsInputFromhashmap() {
        inSTR.setText(stats.get(Enumerations.Attributes.STR).toString());
        inDEX.setText(stats.get(Enumerations.Attributes.DEX).toString());
        inCON.setText(stats.get(Enumerations.Attributes.CON).toString());
        inINT.setText(stats.get(Enumerations.Attributes.INT).toString());
        inWIS.setText(stats.get(Enumerations.Attributes.WIS).toString());
        inCHA.setText(stats.get(Enumerations.Attributes.CHA).toString());
    }

    private void setValuesInTotals() {
        totalSTR.setText(Integer.toString(stats.get(Enumerations.Attributes.STR) + Integer.parseInt(bonusSTR.getText().toString())));
        totalDEX.setText(Integer.toString(stats.get(Enumerations.Attributes.DEX) + Integer.parseInt(bonusDEX.getText().toString())));
        totalCON.setText(Integer.toString(stats.get(Enumerations.Attributes.CON) + Integer.parseInt(bonusCON.getText().toString())));
        totalINT.setText(Integer.toString(stats.get(Enumerations.Attributes.INT) + Integer.parseInt(bonusINT.getText().toString())));
        totalWIS.setText(Integer.toString(stats.get(Enumerations.Attributes.WIS) + Integer.parseInt(bonusWIS.getText().toString())));
        totalCHA.setText(Integer.toString(stats.get(Enumerations.Attributes.CHA) + Integer.parseInt(bonusCHA.getText().toString())));
    }

    private void updateStatsFromIpnut() {
        stats.clear();
        stats.put(Enumerations.Attributes.STR, inSTR.getText().toString().length() > 0 ? Integer.parseInt(inSTR.getText().toString()) : 0);
        stats.put(Enumerations.Attributes.DEX, inDEX.getText().toString().length() > 0 ? Integer.parseInt(inDEX.getText().toString()) : 0);
        stats.put(Enumerations.Attributes.CON, inCON.getText().toString().length() > 0 ? Integer.parseInt(inCON.getText().toString()) : 0);
        stats.put(Enumerations.Attributes.INT, inINT.getText().toString().length() > 0 ? Integer.parseInt(inINT.getText().toString()) : 0);
        stats.put(Enumerations.Attributes.WIS, inWIS.getText().toString().length() > 0 ? Integer.parseInt(inWIS.getText().toString()) : 0);
        stats.put(Enumerations.Attributes.CHA, inCHA.getText().toString().length() > 0 ? Integer.parseInt(inCHA.getText().toString()) : 0);
    }


    private void setStats() {
        int[] attributes = {
            (bonusSTR.getText().length() > 0 ? Integer.parseInt(bonusSTR.getText().toString()):0) + stats.get(Enumerations.Attributes.STR),
            (bonusDEX.getText().length() > 0 ? Integer.parseInt(bonusDEX.getText().toString()):0) + stats.get(Enumerations.Attributes.DEX),
            (bonusCON.getText().length() > 0 ? Integer.parseInt(bonusCON.getText().toString()):0) + stats.get(Enumerations.Attributes.CON),
            (bonusINT.getText().length() > 0 ? Integer.parseInt(bonusINT.getText().toString()):0) + stats.get(Enumerations.Attributes.INT),
            (bonusWIS.getText().length() > 0 ? Integer.parseInt(bonusWIS.getText().toString()):0) + stats.get(Enumerations.Attributes.WIS),
            (bonusCHA.getText().length() > 0 ? Integer.parseInt(bonusCHA.getText().toString()):0) + stats.get(Enumerations.Attributes.CHA)
        };

        _character._attributes = attributes;

        Intent intent = new Intent(SetupStatsActivity.this, MainActivity.class);
        intent.putExtra(Constants.CHARACTER, _character);
        setResult(RESULT_OK, intent);
        finish();
    }

    private int getUpgradeCost(int value) {
        return value >= 13 ? 2 : 1;
    }

    private int getPointsForDowngrade(int value) {
        return value >= 14 ? 2 : 1;
    }

    private boolean canDowngrade(int value) {
        return value > 8;
    }

    private boolean canUpgrade(int value) {
        return value <= 14 && pointsLeft >= getUpgradeCost(value);
    }

    private void checkDisableSubstract(Enumerations.Attributes attr) {
        switch (attr) {
            case STR:
                btnSubSTR.setEnabled(canDowngrade(stats.get(attr)));
                break;
            case DEX:
                btnSubDEX.setEnabled(canDowngrade(stats.get(attr)));
                break;
            case CON:
                btnSubCON.setEnabled(canDowngrade(stats.get(attr)));
                break;
            case INT:
                btnSubINT.setEnabled(canDowngrade(stats.get(attr)));
                break;
            case WIS:
                btnSubWIS.setEnabled(canDowngrade(stats.get(attr)));
                break;
            case CHA:
                btnSubCHA.setEnabled(canDowngrade(stats.get(attr)));
                break;
        }

    }
    private void checkDisableAdd(Enumerations.Attributes attr) {
        switch (attr) {
            case STR:
                btnAddSTR.setEnabled(canUpgrade(stats.get(attr)));
                break;
            case DEX:
                btnAddDEX.setEnabled(canUpgrade(stats.get(attr)));
                break;
            case CON:
                btnAddCON.setEnabled(canUpgrade(stats.get(attr)));
                break;
            case INT:
                btnAddINT.setEnabled(canUpgrade(stats.get(attr)));
                break;
            case WIS:
                btnAddWIS.setEnabled(canUpgrade(stats.get(attr)));
                break;
            case CHA:
                btnAddCHA.setEnabled(canUpgrade(stats.get(attr)));
                break;
        }
    }

    private void checkAddAndSubstractEnabledState() {
        checkDisableSubstract(Enumerations.Attributes.STR);
        checkDisableSubstract(Enumerations.Attributes.DEX);
        checkDisableSubstract(Enumerations.Attributes.CON);
        checkDisableSubstract(Enumerations.Attributes.INT);
        checkDisableSubstract(Enumerations.Attributes.WIS);
        checkDisableSubstract(Enumerations.Attributes.CHA);
        checkDisableAdd(Enumerations.Attributes.STR);
        checkDisableAdd(Enumerations.Attributes.DEX);
        checkDisableAdd(Enumerations.Attributes.CON);
        checkDisableAdd(Enumerations.Attributes.INT);
        checkDisableAdd(Enumerations.Attributes.WIS);
        checkDisableAdd(Enumerations.Attributes.CHA);
    }

    private class OnSubstractListener implements View.OnClickListener {
        Enumerations.Attributes _attr;
        public OnSubstractListener(Enumerations.Attributes attr) {
            _attr = attr;
        }

        @Override
        public void onClick(View view) {
            int value = stats.get(_attr);
            pointsLeft += getPointsForDowngrade(value);
            pointsRemaining.setText(Integer.toString(pointsLeft));
            stats.put(_attr, new Integer(value -1));
            checkAddAndSubstractEnabledState();
            updateStatsInputFromhashmap();
            setValuesInTotals();

            btnCreate.setEnabled(pointsLeft == 0);
        }

    }
    private class OnAddListener implements View.OnClickListener {
        Enumerations.Attributes _attr;
        public OnAddListener(Enumerations.Attributes attr) {
            _attr = attr;
        }

        @Override
        public void onClick(View view) {
            int value = stats.get(_attr);
            pointsLeft -= getUpgradeCost(value);
            pointsRemaining.setText(Integer.toString(pointsLeft));
            stats.put(_attr, new Integer(value +1));
            checkAddAndSubstractEnabledState();
            updateStatsInputFromhashmap();
            setValuesInTotals();

            btnCreate.setEnabled(pointsLeft == 0);
        }

    }

    private class StatWatcher implements TextWatcher {

        EditText _input;
        Enumerations.Attributes _attr;

        public StatWatcher(EditText input, Enumerations.Attributes attr) {
            _input = input;
            _attr = attr;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (_input.hasFocus()) {
                int value = 0;
                try {
                    value = Integer.parseInt(charSequence.toString());
                } catch (Exception e) {}
                stats.put(_attr, value);
                setValuesInTotals();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
