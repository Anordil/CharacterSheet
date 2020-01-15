package com.guigeek.devilopers.dd5charactersheet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.character.SavingThrow;
import com.guigeek.devilopers.dd5charactersheet.character.Skill;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Rogue;

import java.io.Serializable;
import java.util.HashSet;

public class SkillsScreen extends Fragment {

    protected Character _character;

    HashSet<String> skillAdv = new HashSet<>(), skillDisadv = new HashSet<>();
    HashSet<String> throwAdv = new HashSet<>(), throwDisadv = new HashSet<>();
    HashSet<String> skillProficiency = new HashSet<>();
    HashSet<String> throwProficiency = new HashSet<>();

    TableLayout ll;

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
        ll = (TableLayout) root.findViewById(R.id.table_skills);
        initSkills();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        initSkills();
    }

    public void initSkills() {
        _character.recomputeSkills();
        _character.recomputeSavingThrows();

        skillAdv.clear();
        skillDisadv.clear();
        skillProficiency.clear();
        throwAdv.clear();
        throwDisadv.clear();
        throwProficiency.clear();
        for (Fettle fettle : _character.getFettles()) {
            if (fettle._type == Enumerations.FettleType.ABILITY_CHECK_ADVANTAGE) {
                skillAdv.add(Enumerations.Skills.values()[fettle._describer].toString());
            }
            else if (fettle._type == Enumerations.FettleType.ABILITY_CHECK_DISADVANTAGE) {
                skillDisadv.add(Enumerations.Skills.values()[fettle._describer].toString());
            }
            else if (fettle._type == Enumerations.FettleType.ABILITY_PROFICIENCY) {
                skillProficiency.add(Enumerations.Skills.values()[fettle._describer].toString());
            }
            else if (fettle._type == Enumerations.FettleType.SAVING_THROW_ADVANTAGE) {
                throwAdv.add(Enumerations.SavingThrows.values()[fettle._describer].toString());
            }
            else if (fettle._type == Enumerations.FettleType.SAVING_THROW_DISADVANTAGE) {
                throwDisadv.add(Enumerations.SavingThrows.values()[fettle._describer].toString());
            }
            else if (fettle._type == Enumerations.FettleType.SAVING_THROW_PROFICIENCY) {
                throwProficiency.add(Enumerations.SavingThrows.values()[fettle._describer].toString());
            }
        }

        ll.removeAllViews();

        createFettlesBar(ll);

        TableRow rowProficiency = new TableRow(getContext());
        rowProficiency.setPadding(10, 0, 10, 0);
        TextView proficiency = new TextView(getContext());
        proficiency.setText("Proficiency bonus: +" + _character.getProficiencyBonus());
        TableRow.LayoutParams paramProficiency = new TableRow.LayoutParams();
        paramProficiency.span = 6;
        paramProficiency.weight = 1;
        paramProficiency.setMargins(0,0,0, 20);
        proficiency.setLayoutParams(paramProficiency);
        rowProficiency.addView(proficiency);
        ll.addView(rowProficiency);


        TableRow rowSave = new TableRow(getContext());
        TextView saves = new TextView(getContext());
        rowSave.setPadding(10, 0, 10, 0);
        saves.setText("Saving throws");
        TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
        paramsSaves.span = 6;
        paramsSaves.weight = 1;
        saves.setLayoutParams(paramsSaves);
        rowSave.addView(saves);
        ll.addView(rowSave);


        int index = 0;
        for (SavingThrow skill: _character._savingThrows) {
            TableRow row = new TableRow(getContext());

            if (index %2 == 0) {
                row.setBackgroundColor(getResources().getColor(R.color.colorEvenBg));
            }
            index++;

            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(lp);
            row.setPadding(10, 0, 10, 0);
            row.setGravity(Gravity.CENTER_VERTICAL);

            TextView name = new TextView(getContext()), value = new TextView(getContext());
            name.setTextSize(15.0f);
            value.setTextSize(15.0f);

            TableRow.LayoutParams paramCB = new TableRow.LayoutParams();
            paramCB.span = 2;
            paramCB.bottomMargin = -10;
            paramCB.topMargin = -10;
            CheckBox isProficient = new CheckBox(getContext());
            boolean isProficientFromFettle = throwProficiency.contains(skill._name);
            isProficient.setChecked(skill._isProficient || isProficientFromFettle);
            isProficient.setOnClickListener(new SavingThrowListener(skill, value));
            isProficient.setScaleX(1.0f);
            isProficient.setScaleY(1.0f);
            isProficient.setLayoutParams(paramCB);
            isProficient.setEnabled(!isProficientFromFettle);

            name.setText(skill._name);
            name.setGravity(Gravity.CENTER_VERTICAL);
            value.setTextColor(skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light):
            (skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));
            value.setText((skill._score > 0 ? "+": "") + Integer.toString(skill._score));

            TableRow.LayoutParams paramNameTV = new TableRow.LayoutParams();
            paramNameTV.span = 2;
            paramNameTV.weight = 1;
            name.setLayoutParams(paramNameTV);

            TableRow.LayoutParams valueParam = new TableRow.LayoutParams();
            valueParam.gravity = Gravity.RIGHT;
            value.setLayoutParams(valueParam);

            ImageView advantageIcon = new ImageView(getContext());
            advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_dice_advantage));
            boolean hasAdvantage = throwAdv.contains(skill._name);
            boolean hasDisadvantage = throwDisadv.contains(skill._name);

            if (hasAdvantage && hasDisadvantage) {
                advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_perspective_dice_six_faces_one));
            }
            else if (hasAdvantage) {
                advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_dice_advantage));
            }
            else if (hasDisadvantage) {
                advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_dice_disadvantage));
            }
            else {
                advantageIcon.setVisibility(View.INVISIBLE);
            }

            row.addView(isProficient);
            row.addView(name);
            row.addView(advantageIcon);
            row.addView(value);
            ll.addView(row);
        }


        TableRow rowSk = new TableRow(getContext());
        TextView skills = new TextView(getContext());
        skills.setText("Skills");
        rowSk.setPadding(10, 0, 10, 0);
        TableRow.LayoutParams paramsSk = new TableRow.LayoutParams();
        paramsSk.span = 6;
        paramsSk.weight = 1;
        paramsSk.topMargin = 10;
        skills.setLayoutParams(paramsSk);
        rowSk.addView(skills);
        ll.addView(rowSk);


        index = 0;
        for (Skill skill: _character._skills) {
            TableRow row = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            row.setPadding(10, 0, 10, 0);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setLayoutParams(lp);

            if (index %2 == 0) {
                row.setBackgroundColor(getResources().getColor(R.color.colorEvenBg));
            }
            index++;

            TextView name = new TextView(getContext()), attr = new TextView(getContext()), value = new TextView(getContext());
            name.setTextSize(15.0f);
            attr.setTextSize(12.0f);
            value.setTextSize(15.0f);
            value.setTextColor(skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light) :
                    (skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));

            TableRow.LayoutParams dpParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            dpParams.leftMargin = 0;
            dpParams.bottomMargin = -10;
            dpParams.topMargin = -10;

            CheckBox isProficient = new CheckBox(getContext());
            boolean isProficientFromFettle = skillProficiency.contains(skill._name);
            isProficient.setChecked(skill._isProficient || isProficientFromFettle);
            isProficient.setScaleX(1.0f);
            isProficient.setScaleY(1.0f);
            isProficient.setLayoutParams(dpParams);
            isProficient.setEnabled(!isProficientFromFettle);


            CheckBox isDoublyProficient = new CheckBox(getContext());
            isDoublyProficient.setEnabled(skill._isProficient);
            isDoublyProficient.setChecked(skill._isDoublyProficient);
            isDoublyProficient.setScaleX(1.0f);
            isDoublyProficient.setScaleY(1.0f);
            isDoublyProficient.setLayoutParams(dpParams);

            isDoublyProficient.setOnClickListener(new SavingThrowDoubleProficiencyListener(skill, value));
            isProficient.setOnClickListener(new SkillListener(skill, value, isDoublyProficient));

            if (!(_character._class instanceof Rogue) && !(_character._secondaryClass != null && _character._secondaryClass instanceof Rogue)) {
                isDoublyProficient.setVisibility(View.INVISIBLE);
            }

            name.setText(skill._name);
            attr.setText(skill._attribute.getShortName());
            value.setText((skill._score > 0 ? "+": "") + Integer.toString(skill._score));
            value.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);

            TableRow.LayoutParams nameParam = new TableRow.LayoutParams();
            nameParam.weight = 1;
            name.setLayoutParams(nameParam);


            ImageView advantageIcon = new ImageView(getContext());
            advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_dice_advantage));
            boolean hasAdvantage = skillAdv.contains(skill._name);
            boolean hasDisadvantage = skillDisadv.contains(skill._name);

            if (hasAdvantage && hasDisadvantage) {
                advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_perspective_dice_six_faces_one));
            }
            else if (hasAdvantage) {
                advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_dice_advantage));
            }
            else if (hasDisadvantage) {
                advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_dice_disadvantage));
            }
            else {
                advantageIcon.setVisibility(View.INVISIBLE);
            }

            TableRow.LayoutParams imgParam = new TableRow.LayoutParams();
            imgParam.height = 50;
            advantageIcon.setLayoutParams(imgParam);

            row.addView(isProficient);
            row.addView(isDoublyProficient);
            row.addView(name);
            row.addView(attr);
            row.addView(advantageIcon);
            row.addView(value);
            ll.addView(row);
        }
    }


    private void createFettlesBar(TableLayout ll) {
        // Title
        TableRow rowPowerHeader = new TableRow(getContext());
        TextView powerHeader = new TextView(getContext());
        powerHeader.setText("Passive effects");
        powerHeader.setTextSize(20.0f);
        TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
        paramsSaves.span = 6;
        paramsSaves.weight = 1;
        paramsSaves.topMargin = 10;
        powerHeader.setLayoutParams(paramsSaves);
        rowPowerHeader.addView(powerHeader);
        rowPowerHeader.setPadding(10, 0, 10, 0);
        ll.addView(rowPowerHeader);

        // Display only relevant effects for this screen
        for (Fettle fettle : _character.getFettles()) {
            if (fettle._type == Enumerations.FettleType.IMMUNITY ||
                fettle._type == Enumerations.FettleType.ABILITY_CHECK_MODIFIER ||
                fettle._type == Enumerations.FettleType.ABILITY_CHECK_ADVANTAGE ||
                fettle._type == Enumerations.FettleType.ABILITY_CHECK_DISADVANTAGE ||
                fettle._type == Enumerations.FettleType.SAVING_THROW_MODIFIER ||
                fettle._type == Enumerations.FettleType.SAVING_THROW_ADVANTAGE ||
                fettle._type == Enumerations.FettleType.SAVING_THROW_DISADVANTAGE)
            {
                TableRow row = new TableRow(getContext());
                row.setPadding(10, 0, 10, 0);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
                row.setLayoutParams(lp);

                TextView description = new TextView(getContext());
                boolean isModifierIncluded = (fettle._type == Enumerations.FettleType.SAVING_THROW_MODIFIER && Enumerations.SavingThrows.values()[fettle._describer].isBasicSavingThrow())
                        || fettle._type == Enumerations.FettleType.ABILITY_CHECK_MODIFIER;

                description.setText(fettle.toString() + (isModifierIncluded ? " (included)" : ""));



                TableRow.LayoutParams paramRow = new TableRow.LayoutParams();
                paramRow.span = 6;
                paramRow.weight = 1;
                description.setLayoutParams(paramRow);


                row.addView(description);
                ll.addView(row);
            }
        }
    }

    class SkillListener implements View.OnClickListener {
        Skill _skill;
        TextView _view;
        CheckBox _doubleProficiency;

        public SkillListener(Skill sk, TextView v, CheckBox cbDoubleProficiency) {
            _skill = sk;
            _view = v;
            _doubleProficiency = cbDoubleProficiency;
        }

        @Override
        public void onClick(View v) {
            _skill._isProficient = !_skill._isProficient;

            if (_skill._isProficient) {
                _doubleProficiency.setEnabled(true);
            }
            else {
                _doubleProficiency.setEnabled(false);
                _doubleProficiency.setChecked(false);
            }

            _skill.recompute(_character);
            _view.setText((_skill._score > 0 ? "+": "") + Integer.toString(_skill._score));
            _view.setTextColor(_skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light) :
                    (_skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));


        }
    }


    class SavingThrowListener implements View.OnClickListener {
        SavingThrow _skill;
        TextView _view;

        public SavingThrowListener(SavingThrow sk, TextView v) {
            _skill = sk;
            _view = v;
        }

        @Override
        public void onClick(View v) {
            _skill._isProficient = !_skill._isProficient;

            if (!_skill._isProficient) {
                _skill._isDoublyProficient = false;
            }

            _skill.recompute(_character);

            _view.setText((_skill._score > 0 ? "+": "") + Integer.toString(_skill._score));
            _view.setTextColor(_skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light) :
                    (_skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));
        }
    }

    class SavingThrowDoubleProficiencyListener implements View.OnClickListener {

        Skill _skill;
        TextView _view;

        public SavingThrowDoubleProficiencyListener(Skill sk, TextView v) {
            _skill = sk;
            _view = v;
        }

        @Override
        public void onClick(View v) {
            _skill._isDoublyProficient = !_skill._isDoublyProficient;
            _skill.recompute(_character);

            _view.setText((_skill._score > 0 ? "+": "") + Integer.toString(_skill._score));
            _view.setTextColor(_skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light) :
                    (_skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));
        }
    }
}
