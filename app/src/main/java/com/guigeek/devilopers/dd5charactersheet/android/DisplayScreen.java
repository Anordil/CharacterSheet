package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.databinding.ActivityDisplayBinding;

import java.io.Serializable;

/**
 * Created by ggallani on 22/02/2016.
 */
public class DisplayScreen extends Activity {

    protected Character _character;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle bundle = this.getIntent().getExtras();
        Serializable data = bundle.getSerializable(Constants.CHARACTER);
        _character = (Character) data;

        ActivityDisplayBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_display);
        binding.setCharacter(_character);

    }
}
