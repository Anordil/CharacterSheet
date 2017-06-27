package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.*;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Barbarian_totem;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Paladin_vengeance;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Rogue_assassin;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Rogue_swashbuckler;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock_tome_oldOne;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock_blade_fiend;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfElf;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfOrc;
import com.guigeek.devilopers.dd5charactersheet.character.races.Human;
import com.guigeek.devilopers.dd5charactersheet.character.races.MountainDwarf;

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

            Log.d("Create", "Selected race: " + spRace.getSelectedItemPosition());
            Log.d("Create", "Selected class: " + spClass.getSelectedItemPosition());

            switch(spClass.getSelectedItemPosition()) {
                case 0:
                    aClass = new Barbarian_totem();
                    break;
                case 1:
                    aClass = new Paladin_vengeance();
                    break;
                case 2:
                    aClass = new Warlock_tome_oldOne();
                    break;
                case 3:
                    aClass = new Warlock_blade_fiend();
                    break;
                case 4:
                    aClass = new Rogue_assassin();
                    break;
                case 5:
                    aClass = new Rogue_swashbuckler();
                    break;
                default:
                    aClass = new Paladin_vengeance();
                    break;
            }

            switch(spRace.getSelectedItemPosition()) {
                case 0:
                    aRace = new HalfElf();
                    break;
                case 1:
                    aRace = new HalfOrc();
                    break;
                case 2:
                    aRace = new Human();
                    break;
                case 3:
                    aRace = new MountainDwarf();
                    break;
                default:
                    aRace = new MountainDwarf();
                    break;
            }

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
}
