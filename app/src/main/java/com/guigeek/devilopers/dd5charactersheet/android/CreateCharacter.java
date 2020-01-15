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
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.*;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Barbarian_totem;
import com.guigeek.devilopers.dd5charactersheet.character.classes.BloodHunter;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Paladin_vengeance;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Rogue_assassin;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Rogue_swashbuckler;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Sorcerer_base;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Sorcerer_dragon;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Sorcerer_storm;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Sorcerer_wild;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock_base;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock_tome_oldOne;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock_blade_fiend;
import com.guigeek.devilopers.dd5charactersheet.character.races.Dragonborn;
import com.guigeek.devilopers.dd5charactersheet.character.races.Elf;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfElf;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfOrc;
import com.guigeek.devilopers.dd5charactersheet.character.races.Human;
import com.guigeek.devilopers.dd5charactersheet.character.races.MountainDwarf;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CreateCharacter extends AppCompatActivity {

    Button btnCreate;
    Spinner spRace, spClass;
    EditText inName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        spRace = (Spinner) findViewById(R.id.spinnerRace);
        spClass = (Spinner) findViewById(R.id.spinnerClass);
        inName = (EditText)findViewById(R.id.inName);


        ArrayAdapter<CharSequence> adapterRace = ArrayAdapter.createFromResource(this, R.array.races, android.R.layout.simple_spinner_item);
        adapterRace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRace.setAdapter(adapterRace);

        ArrayAdapter<CharSequence> adapterClass = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);
        adapterClass.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClass.setAdapter(adapterClass);

        btnCreate.setOnClickListener(new CreateListener());
    }

    private class CreateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Class aClass = null;
            Race aRace = null;
            String aRaceName = (String)spRace.getSelectedItem();
            String aClassName = (String)spClass.getSelectedItem();

            if (aClassName.equals(App.getResString(R.string.class_barbarian))) {
                aClass = new Barbarian_totem();
            } else if (aClassName.equals(App.getResString(R.string.class_paladin_vengeance))) {
                aClass = new Paladin_vengeance();
            } else if (aClassName.equals(App.getResString(R.string.class_warlock_tome_oldOne))) {
                aClass = new Warlock_tome_oldOne();
            } else if (aClassName.equals(App.getResString(R.string.class_warlock_blade_fiend))) {
                aClass = new Warlock_blade_fiend();
            } else if (aClassName.equals(App.getResString(R.string.class_rogue_assassin))) {
                aClass = new Rogue_assassin();
            } else if (aClassName.equals(App.getResString(R.string.class_rogue_swashbuckler))) {
                aClass = new Rogue_swashbuckler();
            } else if (aClassName.equals(App.getResString(R.string.class_sorcerer_dragon))) {
                aClass = new Sorcerer_dragon();
            } else if (aClassName.equals(App.getResString(R.string.class_sorcerer_wild))) {
                aClass = new Sorcerer_wild();
            } else if (aClassName.equals(App.getResString(R.string.class_sorcerer_storm))) {
                aClass = new Sorcerer_storm();
            } else if (aClassName.equals(App.getResString(R.string.class_blood_hunter))) {
                aClass = new BloodHunter();
            }

            if (aRaceName.equals(App.getResString(R.string.race_half_elf))) {
                aRace = new HalfElf();
            } else if (aRaceName.equals(App.getResString(R.string.race_half_orc))) {
                aRace = new HalfOrc();
            } else if (aRaceName.equals(App.getResString(R.string.race_human))) {
                aRace = new Human();
            } else if (aRaceName.equals(App.getResString(R.string.race_mtn_dwarf))) {
                aRace = new MountainDwarf();
            } else if (aRaceName.equals(App.getResString(R.string.race_elf))) {
                aRace = new Elf();
            } else if (aRaceName.equals(App.getResString(R.string.race_dragonborn))) {
                aRace = new Dragonborn();
            }

            Log.d("Create", "Selected race: " + aRace.getName());
            Log.d("Create", "Selected class: " + aClass.getName());

            handleSubrace(aRace, aClass);
        }
    }

    private void handleSubrace(final Race aRace, final Class aClass) {
        if (aRace instanceof Dragonborn) {
            // Choose ancestry
            AlertDialog.Builder b = new AlertDialog.Builder(CreateCharacter.this);
            b.setTitle("Select a draconic ancestry");

            final String[] allAncestriesArray = getResources().getStringArray(R.array.draconicAncestries);
            List<String> allAncestries = Arrays.asList(allAncestriesArray);

            b.setAdapter(new StringListAdapter(CreateCharacter.this, R.layout.list_string, allAncestries), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    String subrace = allAncestriesArray[which];
                    Log.d("Create", "sub-race: " + subrace);
                    aRace.setSubRace(subrace);
                    handleArchetype(aRace, aClass);
                }
            });

            b.show();
        } else {
            handleArchetype(aRace, aClass);
        }
    }

    private void handleArchetype(final Race aRace, final Class aClass) {
        if (aClass instanceof BloodHunter) {
            // Choose Blood Order
            AlertDialog.Builder b = new AlertDialog.Builder(CreateCharacter.this);
            b.setTitle("Select a Blood Hunter Order");

            final String[] allOptions = getResources().getStringArray(R.array.bloodHunterOrders);
            final List<String> allOptionsList = Arrays.asList(allOptions);

            b.setAdapter(new StringListAdapter(CreateCharacter.this, R.layout.list_string, allOptionsList), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    String selectedOption = allOptions[which];
                    Log.d("Create", "Archetype: " + selectedOption);
                    aClass.setArchetype(selectedOption);
                    createCharacter(aRace, aClass);
                }
            });

            b.show();
        }
        else {
            createCharacter(aRace, aClass);
        }
    }

    private void createCharacter(Race aRace, Class aClass) {
        int[] attributes = {10,10,10,10,10,10};
        Character aHero = new Character(inName.getText().toString(), aClass, aRace, 1, attributes, null, 0);

        Log.d("Create", aHero.toString());

        Intent intent = new Intent(CreateCharacter.this, MainActivity.class);
        intent.putExtra(Constants.CHARACTER, aHero);
        setResult(RESULT_OK, intent);

        List<String> levelUpBoons = aHero._class.getLevelUpBenefits(aHero._level);

        String boons = "";
        for (String s : levelUpBoons) {
            if (s != null && s.length() > 0) {
                boons += s + "\n";
            }
        }

        AlertDialog alertDialog = new AlertDialog.Builder(CreateCharacter.this).create();
        alertDialog.setTitle("Level up");
        alertDialog.setMessage(boons);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.show();
    }
}
