package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.LinkedList;

public class InventoryScreen extends android.support.v4.app.ListFragment {

    protected Character _character;
    EditText etDamageBonus, etGold, etItemsText;
    Spinner spinnerArmor, spinnerWeapon, spinnerWeaponOffHand;

    ArrayAdapter<Armor> adapterArmor;
    ArrayAdapter<Weapon> adapterWeapon;
    ArrayAdapter<Externalizable> adapterOffHand;

    Weapon emptyWeaponOffHand = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);


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


    public void initEquipmentSpinners() {
        LinkedList<Armor> listOfArmors = new LinkedList<>();
        LinkedList<Weapon> mainHandItems = new LinkedList<>();

        final LinkedList<Externalizable> offHandItems = new LinkedList<>();
        final LinkedList<Externalizable> offHandItemsForDualWielder = new LinkedList<>();
        final LinkedList<Externalizable> shields = new LinkedList<>();

        // Put inventory equipable items in the relevant spinners
        for (Externalizable item : _character._inventory) {
            if (item instanceof Armor) {
                if (((Armor)item)._type == Enumerations.ArmorTypes.SHIELD) {
                    offHandItems.add(item);
                    offHandItemsForDualWielder.add(item);
                    shields.add(item);
                }
                else {
                    listOfArmors.add((Armor)item);
                }
            }
            else if (item instanceof Weapon) {
                Weapon weapon = (Weapon)item;
                mainHandItems.add(weapon);

                if (weapon._hands != Enumerations.WeaponHandCount.TWO_HANDED) {
                    if (weapon._weight == Enumerations.WeaponWeightCategory.LIGHT) {
                        offHandItems.add(item);
                    }
                    offHandItemsForDualWielder.add(item);
                }
            }
        }
        // Add the no armor/weapon choice to all spinners
        mainHandItems.add(new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null));
        offHandItems.add(emptyWeaponOffHand);
        offHandItemsForDualWielder.add(emptyWeaponOffHand);
        shields.add(emptyWeaponOffHand);
        listOfArmors.add(new Armor(Enumerations.ArmorTypes.NONE, 0, null));


        adapterArmor = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listOfArmors);
        spinnerArmor.setAdapter(adapterArmor);

        adapterWeapon = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mainHandItems);
        spinnerWeapon.setAdapter(adapterWeapon);

        adapterOffHand = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, offHandItems);
        spinnerWeaponOffHand.setAdapter(adapterOffHand);


        // Add selection listeners
        final boolean hasDualWielding = _character.hasFeat("Dual Wielder");
        spinnerWeapon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Weapon weapon = adapterWeapon.getItem(position);
                _character._equippedWeapon = weapon;

                // This might affect the off-hand spinner
                if (weapon._hands == Enumerations.WeaponHandCount.TWO_HANDED) {
                    Log.d("Switch", "Equipped a 2H weapon");
                    // No off hand
                    _character._offHandWeapon = emptyWeaponOffHand;
                    _character._equippedShield = new Armor(Enumerations.ArmorTypes.NONE, 0, null);

                    spinnerWeaponOffHand.setEnabled(false);
                    spinnerWeaponOffHand.setSelection(adapterOffHand.getPosition(emptyWeaponOffHand));
                }
                else {
                    spinnerWeaponOffHand.setEnabled(true);
                    Log.d("Switch", "Equipped a single hand weapon");
                    LinkedList<Externalizable> availableOffHand = new LinkedList<>(), referenceList = null;
                    Externalizable previousSelection = (Externalizable) spinnerWeaponOffHand.getSelectedItem();
                    if (hasDualWielding) {
                        Log.d("Switch", "Light or dual");
                        referenceList = offHandItemsForDualWielder;
                    }
                    else if (weapon._weight == Enumerations.WeaponWeightCategory.LIGHT) {
                        referenceList = offHandItems;
                    }
                    else {
                        referenceList = shields;
                    }

                    for (Externalizable item : referenceList) {
                        if (item != _character._equippedWeapon) {
                            availableOffHand.add(item);
                        }
                    }

                    if (previousSelection == _character._equippedWeapon) {
                        previousSelection = emptyWeaponOffHand;
                    }

                    adapterOffHand = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, availableOffHand);
                    spinnerWeaponOffHand.setAdapter(adapterOffHand);
                    spinnerWeaponOffHand.setSelection(adapterOffHand.getPosition(previousSelection));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinnerWeaponOffHand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Externalizable offHandItem = adapterOffHand.getItem(position);
                if (offHandItem instanceof Armor) {
                    _character._equippedShield = (Armor)offHandItem;
                    _character._offHandWeapon = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);
                } else {
                    _character._equippedShield = new Armor(Enumerations.ArmorTypes.NONE, 0, null);
                    _character._offHandWeapon = (Weapon)offHandItem;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        spinnerArmor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _character._equippedArmor = adapterArmor.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    @Override
    public void onViewCreated(View root, Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);

        setListAdapter(new ItemAdapter(getContext(), R.layout.list_item, _character._inventory));
        registerForContextMenu(getListView());
        setListViewHeightBasedOnChildren(getListView());

        etDamageBonus = (EditText)root.findViewById(R.id.inWeapDmgBonus);


        spinnerArmor = (Spinner)root.findViewById(R.id.inSpinnerArmor);
        spinnerWeapon = (Spinner)root.findViewById(R.id.inSpinnerWeapon);
        spinnerWeaponOffHand = (Spinner)root.findViewById(R.id.inSpinnerOffHand);
        initEquipmentSpinners();


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

        initEquipmentSpinners();
        spinnerArmor.setSelection(adapterArmor.getPosition(_character._equippedArmor));
        spinnerWeapon.setSelection(adapterWeapon.getPosition(_character._equippedWeapon));


        if (_character._equippedShield._type == Enumerations.ArmorTypes.SHIELD) {
            spinnerWeaponOffHand.setSelection(adapterOffHand.getPosition(_character._equippedShield));
        }
        else if (_character._offHandWeapon._type != Enumerations.WeaponTypes.UNARMED) {
            spinnerWeaponOffHand.setSelection(adapterOffHand.getPosition(_character._offHandWeapon));
        }
        else {
            spinnerWeaponOffHand.setSelection(adapterOffHand.getCount() -1);
        }

        if (_character._equippedWeapon._hands == Enumerations.WeaponHandCount.TWO_HANDED) {
            spinnerWeaponOffHand.setEnabled(false);
        }

        _character.refreshFettles();
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
                updateContent();
            } catch (Exception e) {
                Log.d("TOTO", "Item creation failed, ");
                e.printStackTrace();
            }
        }
    }


    // Contextual menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if( getUserVisibleHint() == false )
        {
            return false;
        }

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.menu_delete) {
            _character._inventory.remove(info.position);
            updateContent();
            setListAdapter(new ItemAdapter(getContext(), R.layout.list_item, _character._inventory));
        }
        return true;
    }
    // Contextual menu ends
}
