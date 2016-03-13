package com.guigeek.devilopers.dd5charactersheet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;

import java.io.Serializable;

public class InventoryScreen extends Fragment {

    protected Character _character;
    EditText atk, dmg, dice, gold, items;
    CheckBox ranged;
    Button updateBtn;

    public InventoryScreen() {
    }

    public static InventoryScreen newInstance(Character iCharac) {
        InventoryScreen fragment = new InventoryScreen();
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
        View root =  inflater.inflate(R.layout.fragment_inventory, container, false);
        atk = (EditText)root.findViewById(R.id.inWeapAtkBonus);
        dmg = (EditText)root.findViewById(R.id.inWeapDmgBonus);
        dice = (EditText)root.findViewById(R.id.inWeapDice);
        gold = (EditText)root.findViewById(R.id.inGold);
        items = (EditText)root.findViewById(R.id.inInventory);
        ranged = (CheckBox)root.findViewById(R.id.checkBoxRanged);
        updateBtn = (Button)root.findViewById(R.id.btnUpdate);

        InventoryListener aListener = new InventoryListener();
        updateBtn.setOnClickListener(aListener);

        updateContent();

        return root;
    }

    public void updateContent() {
        atk.setText(Integer.toString(_character._atkBonus));
        dmg.setText(Integer.toString(_character._dmgBonus));
        gold.setText(Integer.toString(_character._gold));

        items.setText(_character._allItems);
        dice.setText(_character._weaponDmgDice);

        ranged.setChecked(_character._isWeaponRanged);
    }

    class InventoryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            _character._gold = Integer.parseInt(gold.getText().toString());
            _character._weaponDmgDice = dice.getText().toString();
            _character._atkBonus = Integer.parseInt(atk.getText().toString());
            _character._dmgBonus = Integer.parseInt(dmg.getText().toString());
            _character._allItems = items.getText().toString();
            _character._isWeaponRanged = ranged.isChecked();

            ((SwipeActivity)getActivity()).refreshTabs();
        }
    }
}
