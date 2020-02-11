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
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Class;
import com.guigeek.devilopers.dd5charactersheet.character.classes.barbarian.Barbarian;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bard.Bard;
import com.guigeek.devilopers.dd5charactersheet.character.classes.bloodhunter.BloodHunter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.cleric.Cleric;
import com.guigeek.devilopers.dd5charactersheet.character.classes.druid.Druid;
import com.guigeek.devilopers.dd5charactersheet.character.classes.fighter.Fighter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.monk.Monk;
import com.guigeek.devilopers.dd5charactersheet.character.classes.paladin.Paladin;
import com.guigeek.devilopers.dd5charactersheet.character.classes.ranger.Ranger;
import com.guigeek.devilopers.dd5charactersheet.character.classes.rogue.Rogue;
import com.guigeek.devilopers.dd5charactersheet.character.classes.sorcerer.Sorcerer;
import com.guigeek.devilopers.dd5charactersheet.character.classes.warlock.Warlock;
import com.guigeek.devilopers.dd5charactersheet.character.classes.wizard.Wizard;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class StatsScreen extends Fragment {



    protected Character _character;

    Button updateButton, multiclassButton;
    EditText name, level, str, dex, con, intel, wis, cha, levelSecondary;
    TextView bonusSTR, bonusDEX, bonusCON, bonusINT, bonusWIS, bonusCHA, knownCantrips, knownSpells;
    TextView proficiencyBonus, modSTR, modDEX, modCON, modINT, modWIS, modCHA;
    TextView tvClassName, tvSecondaryClassName;

    TableRow rowCantrips, rowSpells;


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
    public void onResume() {
        super.onResume();
        initInputValue();
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

        bonusSTR = root.findViewById(R.id.bonusSTR);
        bonusDEX = root.findViewById(R.id.bonusDEX);
        bonusCON = root.findViewById(R.id.bonusCON);
        bonusINT = root.findViewById(R.id.bonusINT);
        bonusWIS = root.findViewById(R.id.bonusWIS);
        bonusCHA = root.findViewById(R.id.bonusCHA);

        modSTR = root.findViewById(R.id.modSTR);
        modDEX = root.findViewById(R.id.modDEX);
        modCON = root.findViewById(R.id.modCON);
        modINT = root.findViewById(R.id.modINT);
        modWIS = root.findViewById(R.id.modWIS);
        modCHA = root.findViewById(R.id.modCHA);
        proficiencyBonus = root.findViewById(R.id.proficiencyBonus);

        knownCantrips = (TextView)root.findViewById(R.id.knownCantrips);
        knownSpells = (TextView)root.findViewById(R.id.knownSpells);

        rowCantrips = (TableRow)root.findViewById(R.id.rowCantrips);
        rowSpells = (TableRow)root.findViewById(R.id.rowSpells);

        multiclassButton = (Button)root.findViewById(R.id.buttonMulticlass);
        tvClassName = root.findViewById(R.id.tvClassName);
        tvClassName.setText(_character._class.getClassName() + " level");
        tvSecondaryClassName = root.findViewById(R.id.tvSecondaryClassName);

        if (_character._levelSecondaryClass == 0) {
            multiclassButton.setVisibility(View.VISIBLE);
            levelSecondary.setVisibility(View.GONE);
            tvSecondaryClassName.setVisibility(View.GONE);
        }
        else {
            multiclassButton.setVisibility(View.GONE);
            levelSecondary.setVisibility(View.VISIBLE);
            tvSecondaryClassName.setVisibility(View.VISIBLE);
            tvSecondaryClassName.setText(_character._secondaryClass.getClassName() + " level");
        }

        if (_character._class.getSpellsKnown(_character._level)[0] > 0) {
            rowCantrips.setVisibility(View.VISIBLE);
            rowSpells.setVisibility(View.VISIBLE);
            knownCantrips.setText(Integer.toString(_character._class.getSpellsKnown(_character._level)[0]));
            knownSpells.setText(Integer.toString(_character._class.getSpellsKnown(_character._level)[1]));
        }
        else if (_character._secondaryClass != null && _character._secondaryClass.getSpellsKnown(_character._levelSecondaryClass)[0] > 0) {
            rowCantrips.setVisibility(View.VISIBLE);
            rowSpells.setVisibility(View.VISIBLE);
            knownCantrips.setText(Integer.toString(_character._secondaryClass.getSpellsKnown(_character._levelSecondaryClass)[0]));
            knownSpells.setText(Integer.toString(_character._secondaryClass.getSpellsKnown(_character._levelSecondaryClass)[1]));
        }
        else {
            rowCantrips.setVisibility(View.GONE);
            rowSpells.setVisibility(View.GONE);
        }

        multiclassButton = (Button)root.findViewById(R.id.buttonMulticlass);
        multiclassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                b.setTitle("Select a second class");

                final String[] allOptions = getContext().getResources().getStringArray(R.array.classes);
                final List<String> allOptionsList = Arrays.asList(allOptions);

                b.setAdapter(new StringListAdapter(getContext(), R.layout.list_string, allOptionsList), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        multiclassButton.setVisibility(View.GONE);
                        levelSecondary.setVisibility(View.VISIBLE);
                        tvSecondaryClassName.setVisibility(View.VISIBLE);

                        String aClassName = allOptions[which];
                        Class aClass = null;
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
                        }

                        _character._secondaryClass = aClass;
                        _character._levelSecondaryClass = 1;
                        tvSecondaryClassName.setText(_character._secondaryClass.getClassName() + " level");
                        doUpdate();
                    }
                });

                b.show();
            }
        });


        updateButton = (Button)root.findViewById(R.id.btnUpdateStats);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUpdate();
            }
        });

        initInputValue();

        return root;
    }

    private void doUpdate() {
        {
            _character._name = name.getText().toString();

            final int oldLevel = _character._level;
            final int oldLevelSecondary = _character._levelSecondaryClass;
            _character._level = Integer.parseInt(level.getText().toString());
            _character._levelSecondaryClass = Integer.parseInt(levelSecondary.getText().toString());
            _character._attributes[0]  = Integer.parseInt(str.getText().toString());
            _character._attributes[1] = Integer.parseInt(dex.getText().toString());
            _character._attributes[2] = Integer.parseInt(con.getText().toString());
            _character._attributes[3] = Integer.parseInt(intel.getText().toString());
            _character._attributes[4] = Integer.parseInt(wis.getText().toString());
            _character._attributes[5] = Integer.parseInt(cha.getText().toString());

            if (oldLevel != _character._level) {

                if (oldLevel < _character._level) {

                    Runnable displayBenefits = new Runnable() {
                        @Override
                        public void run() {
                            String boons = "";

                            for (int level = oldLevel + 1; level <= _character._level; ++level) {
                                List<String> levelUpBoons = _character._class.getAllLevelUpBenefits(level, getContext());
                                for (String s : levelUpBoons) {
                                    if (s != null && s.length() > 0) {
                                        boons += s + "\n";
                                    }
                                }
                            }

                            // Show level up window
                            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                            alertDialog.setTitle("Leveled up to " + _character._class.getClassName() + " " + _character._level);
                            alertDialog.setMessage(boons);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    };

                    _character._class.doLevelUp(oldLevel, _character._level, getContext(), displayBenefits);


                } else {
                    _character._class.doLevelDown(oldLevel, _character._level);
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Leveled down to " + _character._class.getClassName() + " " + _character._level);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
            // Same for secondary class
            if (oldLevelSecondary != _character._levelSecondaryClass) {
                if (oldLevelSecondary < _character._levelSecondaryClass) {

                    Runnable displayBenefits = new Runnable() {
                        @Override
                        public void run() {
                            String boons = "";

                            for (int level = oldLevelSecondary + 1; level <= _character._levelSecondaryClass; ++level) {
                                List<String> levelUpBoons = _character._secondaryClass.getAllLevelUpBenefits(level, getContext());
                                for (String s : levelUpBoons) {
                                    if (s != null && s.length() > 0) {
                                        boons += s + "\n";
                                    }
                                }
                            }

                            // Show level up window
                            AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                            alertDialog.setTitle("Leveled up to " + _character._secondaryClass.getClassName() + " " + _character._levelSecondaryClass);
                            alertDialog.setMessage(boons);
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    };

                    _character._secondaryClass.doLevelUp(oldLevelSecondary, _character._levelSecondaryClass, getContext(), displayBenefits);


                } else {
                    _character._secondaryClass.doLevelDown(oldLevelSecondary, _character._levelSecondaryClass);
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                    alertDialog.setTitle("Leveled down to " + _character._secondaryClass.getClassName() + " " + _character._levelSecondaryClass);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }


            if (_character._class.getSpellsKnown(_character._level)[0] > 0) {
                rowCantrips.setVisibility(View.VISIBLE);
                rowSpells.setVisibility(View.VISIBLE);
                knownCantrips.setText(Integer.toString(_character._class.getSpellsKnown(_character._level)[0]));
                knownSpells.setText(Integer.toString(_character._class.getSpellsKnown(_character._level)[1]));
            }
            else if (_character._secondaryClass != null && _character._secondaryClass.getSpellsKnown(_character._levelSecondaryClass)[0] > 0) {
                rowCantrips.setVisibility(View.VISIBLE);
                rowSpells.setVisibility(View.VISIBLE);
                knownCantrips.setText(Integer.toString(_character._secondaryClass.getSpellsKnown(_character._levelSecondaryClass)[0]));
                knownSpells.setText(Integer.toString(_character._secondaryClass.getSpellsKnown(_character._levelSecondaryClass)[1]));
            }
            else {
                rowCantrips.setVisibility(View.GONE);
                rowSpells.setVisibility(View.GONE);
            }

            ((SwipeActivity)getActivity()).refreshTabs();
        }
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


        for (Fettle property : _character.getCharacterFettles()) {
            if (property._type == Enumerations.FettleType.ATTRIBUTE_MODIFIER) {
                Enumerations.Attributes attr = Enumerations.Attributes.values()[property._describer];
                switch (attr) {
                    case STR : bonusSTR.setText(property._value + ""); break;
                    case DEX : bonusDEX.setText(property._value + ""); break;
                    case CON : bonusCON.setText(property._value + ""); break;
                    case INT : bonusINT.setText(property._value + ""); break;
                    case WIS : bonusWIS.setText(property._value + ""); break;
                    case CHA : bonusCHA.setText(property._value + ""); break;
                }
            }
        }

        modSTR.setText("" + _character.getModifier(_character._attributes[0] + Integer.parseInt(bonusSTR.getText().toString())));
        modDEX.setText("" + _character.getModifier(_character._attributes[1] + Integer.parseInt(bonusDEX.getText().toString())));
        modCON.setText("" + _character.getModifier(_character._attributes[2] + Integer.parseInt(bonusCON.getText().toString())));
        modINT.setText("" + _character.getModifier(_character._attributes[3] + Integer.parseInt(bonusINT.getText().toString())));
        modWIS.setText("" + _character.getModifier(_character._attributes[4] + Integer.parseInt(bonusWIS.getText().toString())));
        modCHA.setText("" + _character.getModifier(_character._attributes[5] + Integer.parseInt(bonusCHA.getText().toString())));

        proficiencyBonus.setText("+" + _character.getProficiencyBonus());
    }
}
