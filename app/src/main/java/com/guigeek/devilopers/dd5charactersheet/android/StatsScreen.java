package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.guigeek.devilopers.dd5charactersheet.character.classes.Barbarian_totem;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Paladin_vengeance;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Rogue_assassin;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock_tome_oldOne;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock_blade_fiend;

import java.io.Serializable;
import java.util.List;

public class StatsScreen extends Fragment {

    protected Character _character;

    Button updateButton, multiclassButton;
    EditText name, level, str, dex, con, intel, wis, cha, levelSecondary;
    TextView bonusSTR, bonusDEX, bonusCON, bonusINT, bonusWIS, bonusCHA, tvSecondaryClass;
    Spinner spSecondaryClass;

    public StatsScreen() {
    }

    public static StatsScreen newInstance(Character iCharac) {
        StatsScreen fragment = new StatsScreen();
        Bundle args = new Bundle();
        args.putSerializable(Constants.CHARACTER, iCharac);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            Serializable data = bundle.getSerializable(Constants.CHARACTER);
            _character = (Character) data;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_stats, container, false);

        // Get the EditText refs
        name = (EditText)root.findViewById(R.id.inName);
        level = (EditText)root.findViewById(R.id.inLevel);
        levelSecondary = (EditText)root.findViewById(R.id.inLevelSecondary);
        str = (EditText)root.findViewById(R.id.inSTR);
        dex = (EditText)root.findViewById(R.id.inDEX);
        con = (EditText)root.findViewById(R.id.inCON);
        intel = (EditText)root.findViewById(R.id.inINT);
        wis = (EditText)root.findViewById(R.id.intWIS);
        cha = (EditText)root.findViewById(R.id.inCHA);
        tvSecondaryClass = (TextView)root.findViewById(R.id.tvSecondaryClass);

        bonusSTR = (TextView)root.findViewById(R.id.bonusSTR);
        bonusDEX = (TextView)root.findViewById(R.id.bonusDEX);
        bonusCON = (TextView)root.findViewById(R.id.bonusCON);
        bonusINT = (TextView)root.findViewById(R.id.bonusINT);
        bonusWIS = (TextView)root.findViewById(R.id.bonusWIS);
        bonusCHA = (TextView)root.findViewById(R.id.bonusCHA);

        multiclassButton = (Button)root.findViewById(R.id.buttonMulticlass);
        spSecondaryClass = (Spinner)root.findViewById(R.id.spinnerClass);

        if (_character._levelSecondaryClass == 0) {
            multiclassButton.setVisibility(View.VISIBLE);
            levelSecondary.setVisibility(View.GONE);
            spSecondaryClass.setVisibility(View.GONE);
            tvSecondaryClass.setVisibility(View.VISIBLE);
        }
        else {
            multiclassButton.setVisibility(View.GONE);
            levelSecondary.setVisibility(View.VISIBLE);
            tvSecondaryClass.setVisibility(View.GONE);
            spSecondaryClass.setVisibility(View.VISIBLE);
            spSecondaryClass.setEnabled(false);
        }


        spSecondaryClass = (Spinner) root.findViewById(R.id.spinnerClass);
        ArrayAdapter<CharSequence> adapterClass = ArrayAdapter.createFromResource(getContext(), R.array.classes, android.R.layout.simple_spinner_item);
        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSecondaryClass.setAdapter(adapterClass);

        multiclassButton = (Button)root.findViewById(R.id.buttonMulticlass);
        multiclassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiclassButton.setVisibility(View.GONE);
                levelSecondary.setVisibility(View.VISIBLE);
                tvSecondaryClass.setVisibility(View.GONE);
                spSecondaryClass.setVisibility(View.VISIBLE);
            }
        });

        spSecondaryClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                multiclassButton.setVisibility(View.GONE);
                levelSecondary.setVisibility(View.VISIBLE);
                switch(spSecondaryClass.getSelectedItemPosition()) {
                    case 0:
                        _character._secondaryClass = new Barbarian_totem();
                        break;
                    case 1:
                        _character._secondaryClass = new Paladin_vengeance();
                        break;
                    case 2:
                        _character._secondaryClass = new Warlock_tome_oldOne();
                        break;
                    case 3:
                        _character._secondaryClass = new Warlock_blade_fiend();
                        break;
                    case 4:
                        _character._secondaryClass = new Rogue_assassin();
                        break;
                    default:
                        _character._secondaryClass = new Paladin_vengeance();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("TOTO", "Nothing selected");
            }
        });


        updateButton = (Button)root.findViewById(R.id.btnUpdateStats);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _character._name = name.getText().toString();

                int oldLevel = _character._level;
                int oldLevelSecondary = _character._levelSecondaryClass;
                _character._level = Integer.parseInt(level.getText().toString());
                _character._levelSecondaryClass = Integer.parseInt(levelSecondary.getText().toString());
                _character._attributes[0]  = Integer.parseInt(str.getText().toString());
                _character._attributes[1] = Integer.parseInt(dex.getText().toString());
                _character._attributes[2] = Integer.parseInt(con.getText().toString());
                _character._attributes[3] = Integer.parseInt(intel.getText().toString());
                _character._attributes[4] = Integer.parseInt(wis.getText().toString());
                _character._attributes[5] = Integer.parseInt(cha.getText().toString());

                if (oldLevel != _character._level) {
                    _character.doLongRest();

                    // Show level up window
                    List<String> levelUpBoons = _character._class.getLevelUpBenefits(_character._level);

                    String boons = "";
                    for (String s : levelUpBoons) {
                        if (s != null && s.length() > 0) {
                            boons += s + "\n";
                        }
                    }

                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Level up");
                    alertDialog.setMessage(boons);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    _character.refresh();
                }
                if (oldLevelSecondary != _character._levelSecondaryClass) {
                    Log.d("Level compare", oldLevelSecondary + " VS " + _character._levelSecondaryClass);
                    _character.doLongRest();

                    // Show level up window
                    List<String> levelUpBoons = _character._secondaryClass.getLevelUpBenefits(_character._levelSecondaryClass);

                    String boons = "";
                    for (String s : levelUpBoons) {
                        if (s != null && s.length() > 0) {
                            boons += s + "\n";
                        }
                    }

                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Level up");
                    alertDialog.setMessage(boons);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    _character.refresh();
                }

                ((SwipeActivity)getActivity()).refreshTabs();
            }
        });

        initInputValue();

        return root;
    }

    private void initInputValue() {
        name.setText(_character._name);
        level.setText(_character._level + "");
        levelSecondary.setText(_character._levelSecondaryClass + "");
        str.setText(_character._attributes[0] + "");
        dex.setText(_character._attributes[1] + "");
        con.setText(_character._attributes[2] + "");
        intel.setText(_character._attributes[3] + "");
        wis.setText(_character._attributes[4] + "");
        cha.setText(_character._attributes[5] + "");


        if (_character._secondaryClass != null) {
            if (_character._secondaryClass instanceof Barbarian_totem) spSecondaryClass.setSelection(0);
            if (_character._secondaryClass instanceof Paladin_vengeance) spSecondaryClass.setSelection(1);
            if (_character._secondaryClass instanceof Warlock_tome_oldOne) spSecondaryClass.setSelection(2);
            if (_character._secondaryClass instanceof Warlock_blade_fiend) spSecondaryClass.setSelection(3);
            if (_character._secondaryClass instanceof Rogue_assassin) spSecondaryClass.setSelection(4);
        }


        for (Fettle property : _character.getFettles()) {
            if (property._type == Enumerations.FettleType.ATTRIBUTE_MODIFIER) {
                Enumerations.Attributes attr = Enumerations.Attributes.values()[property._describer];
                switch (attr) {
                    case STR : bonusSTR.setText(property._value + ""); break;
                    case DEX : bonusDEX.setText(property._value + ""); break;
                    case CON : bonusCON.setText(property._value + ""); break;
                    case INT : bonusINT.setText(property._value + ""); break;
                    case WIS : bonusWIS.setText(property._value + ""); break;
                    case CHA : bonusSTR.setText(property._value + ""); break;
                }
            }
        }
    }
}
