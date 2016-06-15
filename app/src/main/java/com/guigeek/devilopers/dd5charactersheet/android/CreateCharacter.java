package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.*;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Class;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Barbarian;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Paladin;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Warlock;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfElf;
import com.guigeek.devilopers.dd5charactersheet.character.races.HalfOrc;
import com.guigeek.devilopers.dd5charactersheet.character.races.MountainDwarf;

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
        inName = (EditText)findViewById(R.id.inName);;


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
            switch(spClass.getSelectedItemPosition()) {
                case 0:
                    aClass = new Paladin();
                    break;
                case 1:
                    aClass = new Warlock();
                    break;
                case 2:
                    aClass = new Barbarian();
                    break;
                default:
                    aClass = new Paladin();
                    break;
            }

            switch(spRace.getSelectedItemPosition()) {
                case 0:
                    aRace = new HalfElf();
                    break;
                case 1:
                    aRace = new MountainDwarf();
                    break;
                case 2:
                    aRace = new HalfOrc();
                    break;
                default:
                    aRace = new MountainDwarf();
                    break;
            }

            int[] attributes = {10,10,10,10,10,10};
            Character aHero = new Character(inName.getText().toString(), aClass, aRace, 1, attributes);

            Intent intent = new Intent(CreateCharacter.this, MainActivity.class);
            intent.putExtra(Constants.CHARACTER, aHero);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
