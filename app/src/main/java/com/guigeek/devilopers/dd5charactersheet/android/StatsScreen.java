package com.guigeek.devilopers.dd5charactersheet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;

import java.io.Serializable;

public class StatsScreen extends Fragment {

    protected Character _character;

    Button updateButton;
    EditText name, level, armor, str, dex, con, intel, wis, cha;

    public StatsScreen() {
    }

    public static StatsScreen newInstance(Character iCharac) {
        StatsScreen fragment = new StatsScreen();
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
        View root = inflater.inflate(R.layout.fragment_stats, container, false);

        // Get the EditText refs
        name = (EditText)root.findViewById(R.id.inName);
        level = (EditText)root.findViewById(R.id.inLevel);
        armor = (EditText)root.findViewById(R.id.inAC);
        str = (EditText)root.findViewById(R.id.inSTR);
        dex = (EditText)root.findViewById(R.id.inDEX);
        con = (EditText)root.findViewById(R.id.inCON);
        intel = (EditText)root.findViewById(R.id.inINT);
        wis = (EditText)root.findViewById(R.id.intWIS);
        cha = (EditText)root.findViewById(R.id.inCHA);

        updateButton = (Button)root.findViewById(R.id.btnUpdateStats);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _character._name = name.getText().toString();
                _character._level = Integer.parseInt(level.getText().toString());
                _character._armorClass = Integer.parseInt(armor.getText().toString());
                _character._attributes[0]  = Integer.parseInt(str.getText().toString());
                _character._attributes[1] = Integer.parseInt(dex.getText().toString());
                _character._attributes[2] = Integer.parseInt(con.getText().toString());
                _character._attributes[3] = Integer.parseInt(intel.getText().toString());
                _character._attributes[4] = Integer.parseInt(wis.getText().toString());
                _character._attributes[5] = Integer.parseInt(cha.getText().toString());

                ((SwipeActivity)getActivity()).refreshTabs();
            }
        });

        initInputValue();

        return root;
    }

    private void initInputValue() {
        name.setText(_character._name);
        level.setText(_character._level + "");
        armor.setText(_character._armorClass + "");
        str.setText(_character._attributes[0] + "");
        dex.setText(_character._attributes[1] + "");
        con.setText(_character._attributes[2] + "");
        intel.setText(_character._attributes[3] + "");
        wis.setText(_character._attributes[4] + "");
        cha.setText(_character._attributes[5] + "");
    }
}
