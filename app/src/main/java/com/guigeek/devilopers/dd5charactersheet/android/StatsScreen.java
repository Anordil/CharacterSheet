package com.guigeek.devilopers.dd5charactersheet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;

import java.io.Serializable;

public class StatsScreen extends Fragment {

    protected Character _character;

    Button updateButton;
    EditText name, level, str, dex, con, intel, wis, cha;
    TextView bonusSTR, bonusDEX, bonusCON, bonusINT, bonusWIS, bonusCHA;

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
        str = (EditText)root.findViewById(R.id.inSTR);
        dex = (EditText)root.findViewById(R.id.inDEX);
        con = (EditText)root.findViewById(R.id.inCON);
        intel = (EditText)root.findViewById(R.id.inINT);
        wis = (EditText)root.findViewById(R.id.intWIS);
        cha = (EditText)root.findViewById(R.id.inCHA);

        bonusSTR = (TextView)root.findViewById(R.id.bonusSTR);
        bonusDEX = (TextView)root.findViewById(R.id.bonusDEX);
        bonusCON = (TextView)root.findViewById(R.id.bonusCON);
        bonusINT = (TextView)root.findViewById(R.id.bonusINT);
        bonusWIS = (TextView)root.findViewById(R.id.bonusWIS);
        bonusCHA = (TextView)root.findViewById(R.id.bonusCHA);

        updateButton = (Button)root.findViewById(R.id.btnUpdateStats);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _character._name = name.getText().toString();

                int oldLevel = _character._level;
                _character._level = Integer.parseInt(level.getText().toString());
                _character._attributes[0]  = Integer.parseInt(str.getText().toString());
                _character._attributes[1] = Integer.parseInt(dex.getText().toString());
                _character._attributes[2] = Integer.parseInt(con.getText().toString());
                _character._attributes[3] = Integer.parseInt(intel.getText().toString());
                _character._attributes[4] = Integer.parseInt(wis.getText().toString());
                _character._attributes[5] = Integer.parseInt(cha.getText().toString());

                if (oldLevel != _character._level) {
                    _character.doLongRest();
                }

                ((SwipeActivity)getActivity()).refreshTabs();
            }
        });

        initInputValue();

        return root;
    }

    private void initInputValue() {
        name.setText(_character._name);
        level.setText(_character._level + "");
        str.setText(_character._attributes[0] + "");
        dex.setText(_character._attributes[1] + "");
        con.setText(_character._attributes[2] + "");
        intel.setText(_character._attributes[3] + "");
        wis.setText(_character._attributes[4] + "");
        cha.setText(_character._attributes[5] + "");


        for (Fettle property : _character.getFettles()) {
            if (property._type == Enumerations.FettleType.ATTRIBUTE_MODIFIER) {
                Enumerations.Attributes attr = Enumerations.Attributes.values()[property._describer];
                switch (attr) {
                    case STR : bonusSTR.setText(property._value + ""); break;
                    case DEX : bonusDEX.setText(property._value + ""); break;
                    case CON : bonusCON.setText(property._value + ""); break;
                    case INT : bonusINT.setText(property._value + ""); break;
                    case WIS : bonusWIS.setText(property._value + ""); break;
                    case CHA : bonusSTR.setText(property._value + ""); break;
                }
            }
        }
    }
}
