package com.guigeek.devilopers.dd5charactersheet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.io.Serializable;
import java.util.HashSet;

public class SkillsScreen extends Fragment {

    protected Character _character;

    HashSet<String> skillAdv = new HashSet<>(), skillDisadv = new HashSet<>();
    HashSet<String> throwAdv = new HashSet<>(), throwDisadv = new HashSet<>();

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

            for (Fettle fettle : _character.getFettles()) {
                if (fettle._type == Enumerations.FettleType.ABILITY_CHECK_ADVANTAGE) {
                    skillAdv.add(Enumerations.Skills.values()[fettle._describer].toString());
                }
                else if (fettle._type == Enumerations.FettleType.ABILITY_CHECK_DISADVANTAGE) {
                    skillDisadv.add(Enumerations.Skills.values()[fettle._describer].toString());
                }
                else if (fettle._type == Enumerations.FettleType.SAVING_THROW_ADVANTAGE) {
                    throwAdv.add(Enumerations.SavingThrows.values()[fettle._describer].toString());
                }
                else if (fettle._type == Enumerations.FettleType.SAVING_THROW_DISADVANTAGE) {
                    throwDisadv.add(Enumerations.SavingThrows.values()[fettle._describer].toString());
                }
            }
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

        createFettlesBar(ll);

        TableRow rowProficiency = new TableRow(getContext());
        TextView proficiency = new TextView(getContext());
        proficiency.setText("Proficiency bonus: +" + _character.getProficiencyBonus());
        TableRow.LayoutParams paramProficiency = new TableRow.LayoutParams();
        paramProficiency.span = 4;
        paramProficiency.setMargins(0,0,0, 20);
        proficiency.setLayoutParams(paramProficiency);
        rowProficiency.addView(proficiency);
        ll.addView(rowProficiency);


        TableRow rowSave = new TableRow(getContext());
        TextView saves = new TextView(getContext());
        saves.setText("Saving throws");
        TableRow.LayoutParams paramsSaves = new TableRow.LayoutParams();
        paramsSaves.span = 4;
        saves.setLayoutParams(paramsSaves);
        rowSave.addView(saves);
        ll.addView(rowSave);


        for (SavingThrow skill: _character._savingThrows) {
            TableRow row = new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            TextView name = new TextView(getContext()), value = new TextView(getContext());
            name.setPadding(0, 0, 10, 0);
            name.setTextSize(20.0f);
            value.setTextSize(20.0f);
            CheckBox isProficient = new CheckBox(getContext());

            isProficient.setChecked(skill._isProficient);
            isProficient.setOnClickListener(new SavingThrowListener(skill, value));
            isProficient.setScaleX(1.5f);
            isProficient.setScaleY(1.5f);
            name.setText(skill._name);
            value.setTextColor(skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light):
            (skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));
            value.setText((skill._score > 0 ? "+": "") + Integer.toString(skill._score));

            TableRow.LayoutParams paramNameTV = new TableRow.LayoutParams();
            paramNameTV.span = 2;
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
        TableRow.LayoutParams paramsSk = new TableRow.LayoutParams();
        paramsSk.span = 4;
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

            TableRow.LayoutParams valueParam = new TableRow.LayoutParams();
            valueParam.gravity = Gravity.RIGHT;
            value.setLayoutParams(valueParam);


            ImageView advantageIcon = new ImageView(getContext());
            advantageIcon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_dice_advantage));
            boolean hasAdvantage = skillAdv.contains(skill._name);
            boolean hasDisadvantage = skillDisadv.contains(skill._name);

            Log.d("SKILL", skill._name + " adv " + hasAdvantage + " disadv " + hasDisadvantage);
            Log.d("ADV", skillAdv.toString());
            Log.d("DISADV", skillDisadv.toString());

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
        paramsSaves.span = 4;
        paramsSaves.topMargin = 10;
        powerHeader.setLayoutParams(paramsSaves);
        rowPowerHeader.addView(powerHeader);
        ll.addView(rowPowerHeader);

        // Display only relevant effects for this screen
        for (Fettle fettle : _character.getEffectsFromRaceAndClass()) {
            if (fettle._type == Enumerations.FettleType.IMMUNITY ||
                fettle._type == Enumerations.FettleType.ABILITY_CHECK_MODIFIER ||
                fettle._type == Enumerations.FettleType.ABILITY_CHECK_ADVANTAGE ||
                fettle._type == Enumerations.FettleType.ABILITY_CHECK_DISADVANTAGE ||
                fettle._type == Enumerations.FettleType.SAVING_THROW_MODIFIER ||
                fettle._type == Enumerations.FettleType.SAVING_THROW_ADVANTAGE ||
                fettle._type == Enumerations.FettleType.SAVING_THROW_DISADVANTAGE)
            {
                TableRow row = new TableRow(getContext());
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                TextView description = new TextView(getContext());
                boolean isModifierIncluded = (fettle._type == Enumerations.FettleType.SAVING_THROW_MODIFIER && Enumerations.SavingThrows.values()[fettle._describer].isBasicSavingThrow())
                        || fettle._type == Enumerations.FettleType.ABILITY_CHECK_MODIFIER;

                description.setText(fettle.toString() + (isModifierIncluded ? " (included)" : ""));



                TableRow.LayoutParams paramRow = new TableRow.LayoutParams();
                paramRow.span = 4;
                description.setLayoutParams(paramRow);


                row.addView(description);
                ll.addView(row);
            }
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
            _skill.recompute(_character);

            _view.setText((_skill._score > 0 ? "+": "") + Integer.toString(_skill._score));
            _view.setTextColor(_skill._score < 0 ? getResources().getColor(android.R.color.holo_red_light) :
                    (_skill._score > 0 ? getResources().getColor(android.R.color.holo_green_light) : getResources().getColor(android.R.color.darker_gray)));
        }
    }
}
