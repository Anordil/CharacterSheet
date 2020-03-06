package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.NameAndDescription;
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

import java.util.LinkedList;
import java.util.List;

public class CreateCharacter extends AppCompatActivity {

    Button btnCreate;
    Spinner spRace, spClass, spSubRace, spBackground;
    EditText inName;
    TextView attributesHelp, backgroundHelp, classDescriptionv;
    ImageView classIcon;

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
        classDescriptionv = findViewById(R.id.classDescriptionv);
        classIcon = findViewById(R.id.classIcon);

        attributesHelp = findViewById(R.id.attributesHelp);
        updateRaceDescription();

        List<NameAndDescription> backgrounds = new LinkedList<>();
        for (Enumerations.Backgrounds type: Enumerations.Backgrounds.values()) {
            backgrounds.add(type);
        }

        ArrayWithDescriptionAdapter adapterBackground = new ArrayWithDescriptionAdapter(this, R.layout.list_name_and_description, backgrounds);
        adapterBackground.setDropDownViewResource(R.layout.list_name_and_description);
        spBackground.setAdapter(adapterBackground);
        spBackground.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                aBg = Enumerations.Backgrounds.values()[i];
                updateBgHelp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
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

                updateRaceDescription();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spSubRace.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String aSubRaceName = (String)spSubRace.getSelectedItem();
                aRace.setSubRace(aSubRaceName);
                updateRaceDescription();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        List<NameAndDescription> allClasses = new LinkedList<>();
        allClasses.add(new Artificer());
        allClasses.add(new Barbarian());
        allClasses.add(new Bard());
        allClasses.add(new BloodHunter());
        allClasses.add(new Cleric());
        allClasses.add(new Druid());
        allClasses.add(new Fighter());
        allClasses.add(new Monk());
        allClasses.add(new Paladin());
        allClasses.add(new Ranger());
        allClasses.add(new Rogue());
        allClasses.add(new Sorcerer());
        allClasses.add(new Warlock());
        allClasses.add(new Wizard());

        ArrayWithDescriptionAdapter adapterClass = new ArrayWithDescriptionAdapter(this, R.layout.list_name_and_description, allClasses);
        adapterClass.setDropDownViewResource(R.layout.list_name_and_description);
        spClass.setAdapter(adapterClass);

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Class aClass = (Class) spClass.getSelectedItem();
                classDescriptionv.setText(Html.fromHtml((aClass.getDescription())));

                classIcon.setImageDrawable(getResources().getDrawable(aClass.getIconResource()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCreate.setOnClickListener(new CreateListener());
        updateBgHelp();
    }

    private void updateBgHelp() {
        backgroundHelp.setText(Html.fromHtml("<b>Skill proficiencies:</b> " + aBg._firstSkill.toString() + ", " + aBg._secondSkill.toString()));
    }

    private void updateRaceDescription() {
        attributesHelp.setText(Html.fromHtml(aRace.getAttributeBoostDescription() +
                (aRace.getArmorProficiencies().isEmpty() ? "" : "<br><b>Armor proficiencies:</b> " + TextUtils.join(", ", aRace.getArmorProficiencies())) +
                (aRace.getWeaponProficiencies().isEmpty() ? "" : "<br><b>Weapon proficiencies:</b> " + TextUtils.join(", ", aRace.getWeaponProficiencies())))
        );
    }

    private class CreateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Class aClass = (Class) spClass.getSelectedItem();

            Log.d("Create", "Selected race: " + aRace.getBaseRaceName());
            Log.d("Create", "Selected class: " + aClass.getQualifiedClassName());

            createCharacter(aRace, aClass);
        }
    }

    private void createCharacter(Race aRace, Class aClass) {
        int[] attributes = {10, 10, 10, 10, 10, 10};

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
