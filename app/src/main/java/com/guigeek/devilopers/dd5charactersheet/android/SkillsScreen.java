package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Skill;

import java.io.Serializable;

public class SkillsScreen extends Fragment {

    protected Character _character;

    public SkillsScreen() {
    }

    public static SkillsScreen newInstance(Character iCharac) {
        SkillsScreen fragment = new SkillsScreen();
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
        View root = inflater.inflate(R.layout.fragment_skills, container, false);
        initSkills(root);
        return root;
    }

    public void initSkills(View root) {
        TableLayout ll = (TableLayout) root.findViewById(R.id.table_skills);
        _character.recomputeSkills();
        _character.recomputeSavingThrows();

        TableRow rowSave = new TableRow(getContext());
        TextView saves = new TextView(getContext());
        saves.setText("Saving throws");
        TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
        paramsSaves.span = 3;
        saves.setLayoutParams(paramsSaves);
        rowSave.addView(saves);
        ll.addView(rowSave);


        for (Skill skill: _character._savingThrows) {
            TableRow row = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView name = new TextView(getContext()), value = new TextView(getContext());
            name.setPadding(0, 0, 10, 0);
            name.setTextSize(20.0f);
            value.setTextSize(20.0f);
            CheckBox isProficient = new CheckBox(getContext());

            isProficient.setChecked(skill._isProficient);
            isProficient.setOnClickListener(new SkillListener(skill, value));
            isProficient.setScaleX(1.5f);
            isProficient.setScaleY(1.5f);
            name.setText(skill._name);
            value.setTextColor(skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light):
            (skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));
            value.setText((skill._score > 0 ? "+": "") + Integer.toString(skill._score));

            TableRow.LayoutParams paramNameTV = new TableRow.LayoutParams();
            paramNameTV.span = 2;
            name.setLayoutParams(paramNameTV);

            TableRow.LayoutParams rowParam = new TableRow.LayoutParams();

            row.addView(isProficient);
            row.addView(name);
            row.addView(value);
            ll.addView(row);
        }


        TableRow rowSk = new TableRow(getContext());
        TextView skills = new TextView(getContext());
        skills.setText("Skills");
        TableRow.LayoutParams paramsSk = new TableRow.LayoutParams();
        paramsSk.span = 3;
        paramsSk.topMargin = 10;
        skills.setLayoutParams(paramsSk);
        rowSk.addView(skills);
        ll.addView(rowSk);


        for (Skill skill: _character._skills) {
            TableRow row = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView name = new TextView(getContext()), attr = new TextView(getContext()), value = new TextView(getContext());
            name.setPadding(0, 0, 10, 0);
            attr.setPadding(0, 0, 10, 0);
            name.setTextSize(20.0f);
            attr.setTextSize(15.0f);
            value.setTextSize(20.0f);
            value.setTextColor(skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light) :
                    (skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));
            CheckBox isProficient = new CheckBox(getContext());

            isProficient.setChecked(skill._isProficient);
            isProficient.setOnClickListener(new SkillListener(skill, value));
            isProficient.setScaleX(1.5f);
            isProficient.setScaleY(1.5f);
            name.setText(skill._name);
            attr.setText(skill._attribute.toString());
            value.setText((skill._score > 0 ? "+": "") + Integer.toString(skill._score));

            TableRow.LayoutParams rowParam = new TableRow.LayoutParams();

            row.addView(isProficient);
            row.addView(name);
            row.addView(attr);
            row.addView(value);
            ll.addView(row);
        }
    }

    class SkillListener implements View.OnClickListener {
        Skill _skill;
        TextView _view;

        public SkillListener(Skill sk, TextView v) {
            _skill = sk;
            _view = v;
        }

        @Override
        public void onClick(View v) {
            _skill._isProficient = !_skill._isProficient;
            _skill.recompute(_character);

            _view.setText((_skill._score > 0 ? "+": "") + Integer.toString(_skill._score));
            _view.setTextColor(_skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light) :
                    (_skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));
        }
    }
}
