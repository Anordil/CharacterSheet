package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Item;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.LinkedList;

public class InventoryScreen extends android.support.v4.app.ListFragment {

    protected Character _character;
    EditText etDamageBonus, etItemsText;
    Spinner spinnerArmor, spinnerWeapon, spinnerWeaponOffHand;
    TextView etGold;

    ArrayAdapter<Armor> adapterArmor;
    ArrayAdapter<Weapon> adapterWeapon;
    ArrayAdapter<Externalizable> adapterOffHand;

    Weapon emptyWeaponOffHand = new Weapon(Enumerations.WeaponTypes.UNARMED, 0, null);


    Button addItemBtn, addGoldBtn, removeGoldBtn;

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
                    // No off hand
                    _character._offHandWeapon = emptyWeaponOffHand;
                    _character._equippedShield = new Armor(Enumerations.ArmorTypes.NONE, 0, null);

                    spinnerWeaponOffHand.setEnabled(false);
                    spinnerWeaponOffHand.setSelection(adapterOffHand.getPosition(emptyWeaponOffHand));
                }
                else {
                    spinnerWeaponOffHand.setEnabled(true);
                    LinkedList<Externalizable> availableOffHand = new LinkedList<>(), referenceList = null;
                    Externalizable previousSelection = (Externalizable) spinnerWeaponOffHand.getSelectedItem();
                    if (hasDualWielding) {
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

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.ITEM, (Externalizable)getListAdapter().getItem(position));
                bundle.putInt(Constants.ITEM_POSITION, position);
                Intent newIntent = new Intent(getActivity().getApplicationContext(), CreateItemActivity.class);
                newIntent.putExtras(bundle);
                startActivityForResult(newIntent, Constants.EDIT_ITEM);
            }
        });

        etDamageBonus = (EditText)root.findViewById(R.id.inWeapDmgBonus);


        spinnerArmor = (Spinner)root.findViewById(R.id.inSpinnerArmor);
        spinnerWeapon = (Spinner)root.findViewById(R.id.inSpinnerWeapon);
        spinnerWeaponOffHand = (Spinner)root.findViewById(R.id.inSpinnerOffHand);
        initEquipmentSpinners();


        etGold = (TextView)root.findViewById(R.id.inGold);
        etItemsText = (EditText)root.findViewById(R.id.inInventory);
        addItemBtn = (Button)root.findViewById(R.id.btnAddItem);
        addGoldBtn = (Button)root.findViewById(R.id.btnAddGold);
        removeGoldBtn = (Button)root.findViewById(R.id.btnRemoveGold);

        InventoryListener aListener = new InventoryListener();
        Button aBtnUpdateInventory = (Button)root.findViewById(R.id.btnUpdateInventory);
        aBtnUpdateInventory.setOnClickListener(aListener);

        InventoryInsertListener aInventoryInsertListener = new InventoryInsertListener();
        addItemBtn.setOnClickListener(aInventoryInsertListener);

        addGoldBtn.setOnClickListener(new InventoryGoldListener(true));
        removeGoldBtn.setOnClickListener(new InventoryGoldListener(false));

        updateContent();
    }

    @Override
    public void onResume() {
        super.onResume();
        setListAdapter(new ItemAdapter(getContext(), R.layout.list_item, _character._inventory));
        initEquipmentSpinners();
        setListViewHeightBasedOnChildren(getListView());
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

    public void setListViewHeightBasedOnChildren(ListView listView) {

        int totalHeight = 0;
        for (Externalizable item : _character._inventory) {
            int itemHeight = 190;

            if (item instanceof Weapon) {
                itemHeight += 70 * ((Weapon)item)._magicProperties.size();
            }
            else if (item instanceof Armor) {
                itemHeight += 70 * ((Armor)item)._magicProperties.size();
            }
            else if (item instanceof Item) {
                itemHeight += 70 * ((Item)item)._magicProperties.size();
            }
            totalHeight += itemHeight;
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        listView.setLayoutParams(params);
    }


    class InventoryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            _character._dmgBonus = Integer.parseInt(etDamageBonus.getText().toString());
            _character._allItems = etItemsText.getText().toString();

            ((SwipeActivity)getActivity()).save();
        }
    }

    class InventoryInsertListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivityForResult(new Intent(getContext(), CreateItemActivity.class), 0);
        }
    }


    class InventoryGoldListener implements View.OnClickListener {

        private boolean isGoldAdded = true;

        public InventoryGoldListener(boolean add) {
            isGoldAdded = add;
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final EditText text = new EditText(getActivity());

            builder.setTitle(isGoldAdded ? "Add gold":"Remove gold").setMessage("Amount to be " + (isGoldAdded ? "added":"removed")).setView(text);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface di, int i) {
                    final String value = text.getText().toString();
                    try {
                        int amount = Integer.parseInt(value);
                        if (isGoldAdded || amount <= _character._gold) {
                            _character._gold += (amount * (isGoldAdded ? 1:-1));
                            etGold.setText(Integer.toString(_character._gold));
                        }
                        else {
                            Toast.makeText(getContext(), "Invalid amount", Toast.LENGTH_SHORT).show();
                            text.setText("");
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(getContext(), "Invalid amount", Toast.LENGTH_SHORT).show();
                        text.setText("");
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface di, int i) {
                }
            });
            builder.create().show();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == Constants.EDIT_ITEM) {
            int position = data.getIntExtra(Constants.ITEM_POSITION, 0);
            Externalizable createdItem = (Externalizable) data.getSerializableExtra(Constants.ITEM);
            _character._inventory.set(position, createdItem);

            setListAdapter(new ItemAdapter(getContext(), R.layout.list_item, _character._inventory));
            updateContent();
            setListViewHeightBasedOnChildren(getListView());
        }
        else if (data != null && data.getSerializableExtra(Constants.ITEM) != null) {
            try {
                Externalizable createdItem = (Externalizable) data.getSerializableExtra(Constants.ITEM);
                _character._inventory.add(createdItem);
                setListAdapter(new ItemAdapter(getContext(), R.layout.list_item, _character._inventory));
                updateContent();
                setListViewHeightBasedOnChildren(getListView());
            } catch (Exception e) {
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
            setListViewHeightBasedOnChildren(getListView());
        }
        return true;
    }
    // Contextual menu ends
}
