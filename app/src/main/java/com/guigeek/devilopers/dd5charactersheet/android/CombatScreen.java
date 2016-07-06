package com.guigeek.devilopers.dd5charactersheet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 22/02/2016.
 */
public class CombatScreen extends Fragment {

    protected Character _character;
    TextView viewHPCurrent, viewHPMax, viewHitDiceCurrent, viewHitDiceMax, tvAC, tvAtk, tvDmg;
    TextView viewSpeed, viewInit, spellAtk, spellDD;
    ProgressBar pb;

    List<TextView> spellSlotTextViews;
    List<TextView> powersTextViews;

    public CombatScreen() {}

    public static CombatScreen newInstance(Character iCharac) {
        CombatScreen fragment = new CombatScreen();
        Bundle args = new Bundle();
        args.putSerializable(Constants.CHARACTER, iCharac);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            Serializable data = bundle.getSerializable(Constants.CHARACTER);
            _character = (Character) data;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        Serializable data = bundle.getSerializable(Constants.CHARACTER);
        _character = (Character) data;

        View rootView = inflater.inflate(R.layout.fragment_combat, container, false);
        viewHPCurrent = (TextView)rootView.findViewById(R.id.tvHPCurrent);
        viewHPMax = (TextView)rootView.findViewById(R.id.tvHPMax);
        viewHitDiceCurrent = (TextView)rootView.findViewById(R.id.tvHitDiceCurrent);
        viewHitDiceMax = (TextView)rootView.findViewById(R.id.tvHitDiceMax);
        tvAC = (TextView)rootView.findViewById(R.id.tvArmorClass);
        pb = (ProgressBar)rootView.findViewById(R.id.progressBar);

        tvAtk = (TextView)rootView.findViewById(R.id.tvAtkBonus);
        tvDmg = (TextView)rootView.findViewById(R.id.tvDamage);

        viewSpeed = (TextView)rootView.findViewById(R.id.tvSpeed);
        viewInit = (TextView)rootView.findViewById(R.id.tvInitiative);

        spellAtk = (TextView)rootView.findViewById(R.id.tvSpellAtk);
        spellDD = (TextView)rootView.findViewById(R.id.tvSpellDD);

        spellDD.setText(Integer.toString(8 + _character.getProficiencyBonus() + _character.getModifier(_character._class.getMainSpellAttribute())));
        spellAtk.setText("+" + Integer.toString(_character.getProficiencyBonus() + _character.getModifier(_character._class.getMainSpellAttribute())));



        if (!_character._class.isCaster()) {
            TableRow spellRow = (TableRow)rootView.findViewById(R.id.rowSpell);
            spellRow.setVisibility(View.GONE);
        }

        addButtonListener(rootView);
        createSpellBars(rootView);
        createSpecialPowerBars(rootView, "Class Features", _character.getPowers());
        createSpecialPowerBars(rootView, "Racial Features", _character._race.getRacialFeatures());
        refreshSheet();

        return rootView;
    }

    private void addButtonListener(View rootView) {

        View.OnClickListener hpList = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHP(v);
            }
        };
        View.OnClickListener hdList = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeHD(v);
            }
        };

        rootView.findViewById(R.id.btnHPMin1).setOnClickListener(hpList);
        rootView.findViewById(R.id.btnHPMin5).setOnClickListener(hpList);
        rootView.findViewById(R.id.btnHPPlus1).setOnClickListener(hpList);
        rootView.findViewById(R.id.btnHPPlus5).setOnClickListener(hpList);

        rootView.findViewById(R.id.btnHDMin1).setOnClickListener(hdList);
        rootView.findViewById(R.id.btnHDPlus1).setOnClickListener(hdList);
    }

    private void createSpellBars(View root) {
        if (_character._class.isCaster()) {
            TableLayout ll = (TableLayout) root.findViewById(R.id.tablelayout);

            // Title
            TableRow rowPowerHeader = new TableRow(getContext());
            TextView powerHeader = new TextView(getContext());
            powerHeader.setText("Spell slots by level");
            powerHeader.setTextSize(20.0f);
            TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
            paramsSaves.span = 7;
            paramsSaves.topMargin = 10;
            powerHeader.setLayoutParams(paramsSaves);
            rowPowerHeader.addView(powerHeader);
            ll.addView(rowPowerHeader);

            spellSlotTextViews = new ArrayList<TextView>();
            for (int i = 1; i <= 9; i++) {

                int maxSpellForSlot = _character._spellSlotsMax[i];
                if (maxSpellForSlot == 0) {
                    break;
                }

                TableRow row = new TableRow(getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                TextView level = new TextView(getContext()), current = new TextView(getContext()), max = new TextView(getContext());
                level.setText(Integer.toString(i));
                current.setText(Integer.toString(_character._spellSlotsCurrent[i]));
                max.setText(Integer.toString(_character._spellSlotsMax[i]));
                current.setTextColor(getResources().getColor(android.R.color.black));
                spellSlotTextViews.add(current);

                Button minus = new Button(getContext()), plus = new Button(getContext());
                minus.setText("-1");
                minus.setTag(i + " -1");
                plus.setText("+1");
                plus.setTag(i + " 1");
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeSpellSlot(v);
                    }
                });
                plus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeSpellSlot(v);
                    }
                });

                TableRow.LayoutParams rowParam = new TableRow.LayoutParams();
                rowParam.span = 2;
                minus.setLayoutParams(rowParam);
                plus.setLayoutParams(rowParam);

                row.addView(level);
                row.addView(current);
                row.addView(max);
                row.addView(minus);
                row.addView(plus);
                ll.addView(row);
            }
        }
    }

    private void createSpecialPowerBars(View root, String title, LinkedList<Power> powers) {
        TableLayout ll = (TableLayout) root.findViewById(R.id.tablelayout);

        if (powers.size() > 0) {
            TableRow rowPowerHeader = new TableRow(getContext());
            TextView powerHeader = new TextView(getContext());
            powerHeader.setText(title);
            powerHeader.setTextSize(20.0f);
            TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
            paramsSaves.span = 7;
            paramsSaves.topMargin = 10;
            powerHeader.setLayoutParams(paramsSaves);
            rowPowerHeader.addView(powerHeader);
            ll.addView(rowPowerHeader);
        }

        for (Power power: powers) {

            TableRow row = new TableRow(getContext());
            TableRow rowDesc = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            rowDesc.setPadding(0, 0, 0, 20);

            TextView name = new TextView(getContext()), current = new TextView(getContext()), max = new TextView(getContext());
            TextView description = new TextView(getContext()), desc = new TextView(getContext()), dd = new TextView(getContext());

            name.setText(power._name);
            name.setTextSize(16.0f);
            name.setTextColor(getResources().getColor(android.R.color.black));
            current.setText(power._left + "");
            current.setTextColor(getResources().getColor(android.R.color.black));
            max.setText(power._max + "");
            description.setText(power.getUsageString());
            desc.setText(power._description);
            dd.setText((power._dd > 0 ? "DD" + power._dd : "") + "");

            TableRow.LayoutParams rowParamName = new TableRow.LayoutParams();
            rowParamName.span = 4;
            name.setLayoutParams(rowParamName);
            TableRow.LayoutParams rowParamNameDesc = new TableRow.LayoutParams();
            rowParamNameDesc.span = 4;
            description.setLayoutParams(rowParamNameDesc);

            row.addView(name);
            row.addView(description);
            row.addView(dd);
            ll.addView(row);

            // Uses?
            if (power._max > 0) {
                Button minus = new Button(getContext()), plus = new Button(getContext());
                minus.setText("-1");
                plus.setText("+1");
                minus.setOnClickListener(new PowerListener(true, power, current));
                plus.setOnClickListener(new PowerListener(false, power, current));

                TableRow.LayoutParams rowParam = new TableRow.LayoutParams();
                rowParam.span = 2;
                minus.setLayoutParams(rowParam);
                plus.setLayoutParams(rowParam);

                TableRow rowUsage= new TableRow(getContext());

                TextView aLongRestShortRestTV = new TextView(getContext());
                aLongRestShortRestTV.setText(power._isLongRest ? "LR" : "SR");

                rowUsage.addView(aLongRestShortRestTV);
                rowUsage.addView(current);
                rowUsage.addView(max);
                rowUsage.addView(minus);
                rowUsage.addView(plus);

                rowUsage.setLayoutParams(lp);
                ll.addView(rowUsage);
            }

            TableRow.LayoutParams rowParamDesc = new TableRow.LayoutParams();
            rowParamDesc.span = 7;
            desc.setLayoutParams(rowParamDesc);
            rowDesc.addView(desc);
            ll.addView(rowDesc);
        }
    }

    public void refreshSheet() {
        pb.setMax(_character._hpMax);
        pb.setProgress(_character._hpCurrent);

        viewHPCurrent.setText(Integer.toString(_character._hpCurrent));
        viewHPMax.setText(Integer.toString(_character._hpMax));

        viewHitDiceMax.setText(Integer.toString(_character._level));
        viewHitDiceCurrent.setText(Integer.toString(_character._hitDice));

        tvAC.setText(_character._armorClass + "");

        int modDex = _character.getModifier(Enumerations.Attributes.DEX);
        int modStr = _character.getModifier(Enumerations.Attributes.STR);

        int dmgBonus = (_character._isWeaponRanged ? modDex : modStr) +  _character._dmgBonus;
        int attackBonus =
                _character.getProficiencyBonus() + _character._atkBonus
                + (_character._isWeaponRanged ? modDex : modStr);

        String damage = _character._weaponDmgDice + (dmgBonus > 0 ? "+":"") + (dmgBonus != 0 ? dmgBonus : "");
        tvDmg.setText(damage);
        tvAtk.setText((attackBonus > 0 ? "+":"") + attackBonus + " x" + _character.getAttacksPerRound());

        viewSpeed.setText(_character._race.getSpeedInFeet() + " ft.");
        int dexBonus = _character.getModifier(Enumerations.Attributes.DEX);
        viewInit.setText((dexBonus > 0 ? "+": "") + dexBonus);
    }

    public void changeHP(View v) {
        int value = Integer.parseInt(v.getTag().toString());
        _character.changeHP(value);
        viewHPCurrent.setText(Integer.toString(_character._hpCurrent));
        pb.setProgress(_character._hpCurrent);
    }

    public void changeHD(View v) {
        int value = Integer.parseInt(v.getTag().toString());
        _character.changeHD(value);
        viewHitDiceCurrent.setText(Integer.toString(_character._hitDice));
    }

    public void changeSpellSlot(View v) {
        String tags = (String)v.getTag();
        String[] tokens = tags.split(" ");
        int level = Integer.parseInt(tokens[0]);
        int value = Integer.parseInt(tokens[1]);

        _character.changeSpellSlot(level, value);
        spellSlotTextViews.get(level -1).setText(Integer.toString(_character._spellSlotsCurrent[level]));
    }


    class PowerListener implements View.OnClickListener {

        boolean isMinus;
        Power power;
        TextView tv;

        public PowerListener(boolean isMin, Power p, TextView view) {
            isMinus = isMin;
            power = p;
            tv = view;
        }

        @Override
        public void onClick(View v) {

            power._left += (isMinus ? -1 : 1);
            power._left = Math.min(power._left, power._max);
            power._left = Math.max(power._left, 0);

            tv.setText(power._left + "");
        }
    }
}
