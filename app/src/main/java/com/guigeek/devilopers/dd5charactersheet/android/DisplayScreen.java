package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Paladin;
import com.guigeek.devilopers.dd5charactersheet.character.races.MountainDwarf;
import com.guigeek.devilopers.dd5charactersheet.databinding.ActivityDisplayBinding;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by ggallani on 22/02/2016.
 */
public class DisplayScreen extends Activity {

    protected Character _character;
    TextView viewHPCurrent, viewHitDiceCurrent, viewSpellSplot1;
    ProgressBar pb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle bundle = this.getIntent().getExtras();
        Serializable data = bundle.getSerializable(Constants.CHARACTER);
//        _character = (Character) data;
        int[] attributes = {16,10,16,10,10,14};
        _character = new Character("Thorvil", new Paladin(), new MountainDwarf(), 3, attributes);


        ActivityDisplayBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_display);
        binding.setCharacter(_character);

        viewHPCurrent = (TextView)findViewById(R.id.tvHPCurrent);
        viewHitDiceCurrent = (TextView)findViewById(R.id.tvHitDiceCurrent);
        viewSpellSplot1 = (TextView)findViewById(R.id.tvSlotCurrent1);
        pb = (ProgressBar)findViewById(R.id.progressBar);
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
        viewSpellSplot1.setText(Integer.toString(_character._spellSlotsCurrent[level]));
    }


    public void save(View v) {
        try {
            FileOutputStream fos = openFileOutput(_character._name.replaceAll(" ", "") + ".ddfcs", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(_character);
            out.flush();
            out.close();
            fos.close();
            Log.d("TOTO", "Export OK");
        } catch (Exception e) {
            Log.d("TOTO", "Export failed, ");
            e.printStackTrace();
        }
    }

}
