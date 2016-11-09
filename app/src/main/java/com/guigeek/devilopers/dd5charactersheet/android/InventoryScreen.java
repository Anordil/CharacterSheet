package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class InventoryScreen extends android.support.v4.app.ListFragment {

    protected Character _character;
    EditText etDamageBonus, etGold, etItemsText;
    Spinner spinnerArmor, spinnerWeapon, spinnerWeaponOffHand;

    ArrayAdapter<Enumerations.ArmorTypes> adapterArmor;
    ArrayAdapter<Enumerations.WeaponTypes> adapterWeapon;
    ArrayAdapter<Object> adapterOffHand;


    Button updateBtn, addItemBtn;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =  inflater.inflate(R.layout.fragment_inventory, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        setListAdapter(new ItemAdapter(getContext(), R.layout.list_item, _character._inventory));

//        getListView().setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
        setListViewHeightBasedOnChildren(getListView());

        etDamageBonus = (EditText)root.findViewById(R.id.inWeapDmgBonus);


        spinnerArmor = (Spinner)root.findViewById(R.id.inSpinnerArmor);
        spinnerWeapon = (Spinner)root.findViewById(R.id.inSpinnerWeapon);
        spinnerWeaponOffHand = (Spinner)root.findViewById(R.id.inSpinnerOffHand);


        // Filter out the shield from the list of armors
        LinkedList<Enumerations.ArmorTypes> listOfArmors = new LinkedList<>();
        for (Enumerations.ArmorTypes t : Enumerations.ArmorTypes.values()) {
            if (t != Enumerations.ArmorTypes.SHIELD) {
                listOfArmors.add(t);
            }
        }

        adapterArmor = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listOfArmors);
        adapterArmor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArmor.setAdapter(adapterArmor);
        adapterWeapon = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Enumerations.WeaponTypes.values());
        adapterWeapon.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeapon.setAdapter(adapterWeapon);


        final boolean hasDualWielding = _character.hasFeat("Dual Wielder");
        spinnerWeapon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO: update off hand list
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Build the list of eligible off-hand weapons
        LinkedList<Object> listOfOneHandedWeapons = new LinkedList<>();
        for (Enumerations.WeaponTypes t : Enumerations.WeaponTypes.values()) {
            Weapon totoWeapons = new Weapon(t, 0, null);
            if (totoWeapons._hands == Enumerations.WeaponHandCount.ONE_HANDED || totoWeapons._hands == Enumerations.WeaponHandCount.VERSATILE) {
                if (totoWeapons._weight == Enumerations.WeaponWeightCategory.LIGHT || hasDualWielding) {
                    listOfOneHandedWeapons.add(t);
                }
            }
        }


        // Off hands: only one handed weapons
        adapterOffHand = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listOfOneHandedWeapons);
        adapterOffHand.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeaponOffHand.setAdapter(adapterOffHand);


        etGold = (EditText)root.findViewById(R.id.inGold);
        etItemsText = (EditText)root.findViewById(R.id.inInventory);
        updateBtn = (Button)root.findViewById(R.id.btnUpdate);
        addItemBtn = (Button)root.findViewById(R.id.btnAddItem);

        InventoryListener aListener = new InventoryListener();
        updateBtn.setOnClickListener(aListener);

        InventoryInsertListener aInventoryInsertListener = new InventoryInsertListener();
        addItemBtn.setOnClickListener(aInventoryInsertListener);

        updateContent();
    }

    public void updateContent() {
        etDamageBonus.setText(Integer.toString(_character._dmgBonus));
        etGold.setText(Integer.toString(_character._gold));

        spinnerArmor.setSelection(adapterArmor.getPosition(_character._equippedArmor._type));

        spinnerWeapon.setSelection(adapterWeapon.getPosition(_character._equippedWeapon._type));


        if (_character._equippedShield._type == Enumerations.ArmorTypes.SHIELD) {
            //TODO: init spinner
        }
        else if (_character._offHandWeapon._type != Enumerations.WeaponTypes.UNARMED) {
            //TODO: init spinner
        }
        else {
            //TODO: init spinner
        }

        etItemsText.setText(_character._allItems);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0) {
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ListView.LayoutParams.WRAP_CONTENT));
            }
            view.measure(View.MeasureSpec.makeMeasureSpec(desiredWidth, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;// + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }


    class InventoryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            _character._gold = Integer.parseInt(etGold.getText().toString());
            _character._dmgBonus = Integer.parseInt(etDamageBonus.getText().toString());
            _character._allItems = etItemsText.getText().toString();


            // TODO: update hands and armor


            ((SwipeActivity)getActivity()).save();
        }
    }

    class InventoryInsertListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivityForResult(new Intent(getContext(), CreateItemActivity.class), 0);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.getSerializableExtra(Constants.ITEM) != null) {
            try {
                Externalizable createdItem = (Externalizable) data.getSerializableExtra(Constants.ITEM);
                _character._inventory.add(createdItem);
                setListAdapter(new ItemAdapter(getContext(), R.layout.list_item, _character._inventory));
            } catch (Exception e) {
                Log.d("TOTO", "Item creation failed, ");
                e.printStackTrace();
            }
        }
    }
}
