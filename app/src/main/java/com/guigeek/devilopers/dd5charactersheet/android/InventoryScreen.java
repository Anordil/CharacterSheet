package com.guigeek.devilopers.dd5charactersheet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Serializable;

public class InventoryScreen extends Fragment {

    protected Character _character;
    EditText dmg, gold, items, magicModArmor, magicModWeapon;
    Spinner spinnerArmor, spinnerWeapon;
    CheckBox hasShield;

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

        dmg = (EditText)root.findViewById(R.id.inWeapDmgBonus);
        magicModArmor = (EditText)root.findViewById(R.id.inArmorMagicModifier);
        magicModWeapon = (EditText)root.findViewById(R.id.inWeaponMagicModifier);

        spinnerArmor = (Spinner)root.findViewById(R.id.inSpinnerArmor);
        spinnerWeapon = (Spinner)root.findViewById(R.id.inSpinnerWeapon);

        ArrayAdapter<Enumerations.ArmorTypes> adapterArmor = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Enumerations.ArmorTypes.values());
        adapterArmor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArmor.setAdapter(adapterArmor);
        ArrayAdapter<Enumerations.WeaponTypes> adapterWeapon = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Enumerations.WeaponTypes.values());
        adapterWeapon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeapon.setAdapter(adapterWeapon);

        hasShield = (CheckBox)root.findViewById(R.id.inHasShield);


        gold = (EditText)root.findViewById(R.id.inGold);
        items = (EditText)root.findViewById(R.id.inInventory);
        updateBtn = (Button)root.findViewById(R.id.btnUpdate);

        InventoryListener aListener = new InventoryListener();
        updateBtn.setOnClickListener(aListener);

        updateContent();

        return root;
    }

    public void updateContent() {
        dmg.setText(Integer.toString(_character._dmgBonus));
        gold.setText(Integer.toString(_character._gold));

        spinnerArmor.setSelection(_character._equippedArmor._type.ordinal());
        magicModArmor.setText(Integer.toString(_character._equippedArmor._magicModifier));

        spinnerWeapon.setSelection(_character._equippedWeapon._type.ordinal());
        magicModWeapon.setText(Integer.toString(_character._equippedWeapon._magicModifier));

        hasShield.setChecked(_character._equippedShield._type == Enumerations.ArmorTypes.SHIELD);

        items.setText(_character._allItems);
    }

    class InventoryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            _character._gold = Integer.parseInt(gold.getText().toString());
            _character._dmgBonus = Integer.parseInt(dmg.getText().toString());
            _character._allItems = items.getText().toString();

            _character._equippedArmor = new Armor((Enumerations.ArmorTypes) spinnerArmor.getSelectedItem(), Integer.parseInt(magicModArmor.getText().toString()), null);
            _character._equippedWeapon = new Weapon((Enumerations.WeaponTypes) spinnerWeapon.getSelectedItem(), Integer.parseInt(magicModWeapon.getText().toString()), null);

            if (hasShield.isChecked()) {
                _character._equippedShield = new Armor(Enumerations.ArmorTypes.SHIELD, 0, null);
            }
            else {
                _character._equippedShield = new Armor(Enumerations.ArmorTypes.NONE, 0, null);
            }

            ((SwipeActivity)getActivity()).save();
        }
    }
}
