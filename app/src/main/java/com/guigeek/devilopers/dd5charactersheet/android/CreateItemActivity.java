package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Consumable;
import com.guigeek.devilopers.dd5charactersheet.item.Item;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class CreateItemActivity extends ListActivity {


    Button addProperty, createItem;
    LinkedList<Fettle> magicProperties;

    EditText name, modifier, propertyModifier, consEffect, consCount;
    Spinner spinnerItemCategory, spinnerItemType, spinnerPropertyType, spinnerPropertyDescriber;
    LinearLayout layoutConsumabl, layoutMagicProps;
    int _itemPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        // Magic properties list
        magicProperties = new LinkedList<>();
        ListView listView = getListView();
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, magicProperties));
        registerForContextMenu(getListView());

        // Edit text
        name = (EditText)findViewById(R.id.itemName);
        modifier = (EditText)findViewById(R.id.itemModifier);
        propertyModifier = (EditText)findViewById(R.id.etPropertyModifier);
        consEffect = (EditText)findViewById(R.id.consumableEffect);
        consCount = (EditText)findViewById(R.id.consumableCharges);

        // Buttons
        addProperty = (Button)findViewById(R.id.buttonAddProperty);
        createItem = (Button)findViewById(R.id.buttonCreateItemSave);

        addProperty.setOnClickListener(new AddPropertyListener());
        createItem.setOnClickListener(new CreateItemListener());

        layoutConsumabl = (LinearLayout)findViewById(R.id.layoutConsumable);
        layoutMagicProps = (LinearLayout)findViewById(R.id.layoutMagicProperties);


        // Spinners
        spinnerItemCategory = (Spinner)findViewById(R.id.spinnerItemCategory);
        spinnerItemType = (Spinner)findViewById(R.id.spinnerItemType);
        spinnerPropertyType = (Spinner)findViewById(R.id.spinnerPropertyType);
        spinnerPropertyDescriber = (Spinner)findViewById(R.id.spinnerPropertyDescriber);


        // Init category spinner
        String[] itemCategories = {"Weapon", "Armor", "Worn item", "Consumable"};
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemCategories);
        spinnerItemCategory.setAdapter(adapterCategory);
        spinnerItemCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        spinnerItemType.setVisibility(View.VISIBLE);
                        modifier.setVisibility(View.VISIBLE);
                        layoutConsumabl.setVisibility(View.GONE);
                        layoutMagicProps.setVisibility(View.VISIBLE);
                        spinnerItemType.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.WeaponTypes.values()));
                        break;
                    case 1:
                        spinnerItemType.setVisibility(View.VISIBLE);
                        modifier.setVisibility(View.VISIBLE);
                        layoutConsumabl.setVisibility(View.GONE);
                        layoutMagicProps.setVisibility(View.VISIBLE);
                        spinnerItemType.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.ArmorTypes.values()));
                        break;
                    case 3:
                        spinnerItemType.setVisibility(View.GONE);
                        modifier.setVisibility(View.GONE);
                        layoutConsumabl.setVisibility(View.VISIBLE);
                        layoutMagicProps.setVisibility(View.GONE);
                        break;
                    default:
                        spinnerItemType.setVisibility(View.GONE);
                        modifier.setVisibility(View.GONE);
                        layoutConsumabl.setVisibility(View.GONE);
                        layoutMagicProps.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        // Magic properties - category
        spinnerPropertyType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Enumerations.FettleType.values()));
        spinnerPropertyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Enumerations.FettleType type = (Enumerations.FettleType)spinnerPropertyType.getAdapter().getItem(position);
                spinnerPropertyDescriber.setVisibility(View.VISIBLE);
                propertyModifier.setVisibility(View.VISIBLE);

                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.MATCH_PARENT, 20);
                propertyModifier.setLayoutParams(param);

                switch (type) {
                    case ATTRIBUTE_MODIFIER:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.Attributes.values()));
                        break;
                    case ABILITY_CHECK_ADVANTAGE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.Skills.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case ABILITY_CHECK_DISADVANTAGE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.Skills.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case ABILITY_CHECK_MODIFIER:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.Skills.values()));
                        break;
                    case ARMOR_CLASS_MODIFIER:
                        spinnerPropertyDescriber.setVisibility(View.INVISIBLE);
                        break;
                    case MOVEMENT_SPEED_MODIFIER:
                        spinnerPropertyDescriber.setVisibility(View.INVISIBLE);
                        break;
                    case ATTACK_BONUS_MODIFIER:
                        spinnerPropertyDescriber.setVisibility(View.INVISIBLE);
                        break;
                    case ATTACK_DAMAGE_MODIFIER:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.DamageTypes.values()));
                        break;
                    case DAMAGE_RESISTANCE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.DamageTypes.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case DAMAGE_VULNERABILITY:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.DamageTypes.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case SAVING_THROW_ADVANTAGE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.SavingThrows.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case SAVING_THROW_DISADVANTAGE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.SavingThrows.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case SAVING_THROW_MODIFIER:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.SavingThrows.values()));
                        break;
                    case IMMUNITY:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.Immunities.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case ATTACK_DAMAGE_DICE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.DamageTypes.values()));
                        break;
                    case TEXT_FETTLE:
                        spinnerPropertyDescriber.setVisibility(View.GONE);
                        LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                                0,
                                LinearLayout.LayoutParams.MATCH_PARENT, 60);
                        propertyModifier.setLayoutParams(param2);
                        break;
                    default: break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        // Is this an edit ?
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            Serializable data = bundle.getSerializable(Constants.ITEM);
            if (data != null) {
                _itemPosition = bundle.getInt(Constants.ITEM_POSITION);
                Externalizable item = null;
                if (data instanceof Weapon) {
                    initForWeapon((Weapon)data);
                }
                else if (data instanceof Armor) {
                    initForArmor((Armor)data);
                }
                else if (data instanceof Item) {
                    initForItem((Item)data);
                }
                else if (data instanceof Consumable) {
                    initForConsumable((Consumable) data);
                }
            }

        }
    }

    private void initForWeapon(Weapon item) {
        magicProperties = item._magicProperties;

        spinnerItemType.setVisibility(View.VISIBLE);
        modifier.setVisibility(View.VISIBLE);
        layoutConsumabl.setVisibility(View.GONE);
        layoutMagicProps.setVisibility(View.VISIBLE);


        name.setText(item._name);
        modifier.setText(Integer.toString(item._magicModifier));

        spinnerItemCategory.setSelection(0);
        final int itemType = item._type.ordinal();
        spinnerItemType.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.WeaponTypes.values()));
        spinnerItemType.setSelection(item._type.ordinal(), true);
        spinnerItemType.postDelayed(new Runnable() {
            public void run() {
                spinnerItemType.setSelection(itemType, true);
            }
        }, 100);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, magicProperties));
    }
    private void initForArmor(Armor item) {
        magicProperties = item._magicProperties;
        spinnerItemType.setVisibility(View.VISIBLE);
        modifier.setVisibility(View.VISIBLE);
        layoutConsumabl.setVisibility(View.GONE);
        layoutMagicProps.setVisibility(View.VISIBLE);


        name.setText(item._name);
        modifier.setText(Integer.toString(item._magicModifier));

        spinnerItemCategory.setSelection(1);
        final int itemType = item._type.ordinal();
        spinnerItemType.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, Enumerations.ArmorTypes.values()));
        spinnerItemType.postDelayed(new Runnable() {
            public void run() {
                spinnerItemType.setSelection(itemType, true);
            }
        }, 100);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, magicProperties));
    }
    private void initForItem(Item item) {
        magicProperties = item._magicProperties;

        spinnerItemType.setVisibility(View.GONE);
        modifier.setVisibility(View.GONE);
        layoutConsumabl.setVisibility(View.GONE);
        layoutMagicProps.setVisibility(View.VISIBLE);

        name.setText(item._name);
        modifier.setText("");

        spinnerItemCategory.setSelection(2);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, magicProperties));
    }

    private void initForConsumable(Consumable item) {
        magicProperties = new LinkedList<>();

        spinnerItemType.setVisibility(View.GONE);
        modifier.setVisibility(View.GONE);
        layoutConsumabl.setVisibility(View.VISIBLE);
        layoutMagicProps.setVisibility(View.GONE);

        name.setText(item._name);
        consEffect.setText(item._effect);
        consCount.setText(Integer.toString(item._charges));
        modifier.setText("");

        spinnerItemCategory.setSelection(3);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, magicProperties));
    }


    @Override
    public void onResume() {
        super.onResume();
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, magicProperties));
    }





    // Contextual menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.menu_delete) {
            magicProperties.remove(info.position);
            setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, magicProperties));
        }
        return true;
    }
    // Contextual menu ends



    class AddPropertyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            boolean isInt;
            int valueInt = 0;
            String valueStr = propertyModifier.getText().toString();
            try {
                valueInt = Integer.parseInt(propertyModifier.getText().toString());
                isInt = true;
            }
            catch (Exception up) {
                isInt = false;
            }

            Fettle aAddedProperty;
            if (isInt) {
                aAddedProperty = new Fettle(
                        (Enumerations.FettleType)spinnerPropertyType.getSelectedItem(),
                        valueInt,
                        ((Enum)spinnerPropertyDescriber.getSelectedItem()).ordinal()
                );
            }
            else {
                aAddedProperty = new Fettle(
                        (Enumerations.FettleType)spinnerPropertyType.getSelectedItem(),
                        valueStr,
                        ((Enum)spinnerPropertyDescriber.getSelectedItem()).ordinal()
                );
            }

            magicProperties.add(aAddedProperty);
            setListAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_dropdown_item, magicProperties));
        }
    }

    class CreateItemListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Externalizable newItem = null;
            int modifierValue = modifier.getText().toString().length() > 0 ? Integer.parseInt(modifier.getText().toString()) : 0;
            switch(spinnerItemCategory.getSelectedItemPosition()) {
                case 0:
                    newItem = new Weapon((Enumerations.WeaponTypes) spinnerItemType.getSelectedItem(), modifierValue, magicProperties);
                    ((Weapon)newItem)._name = name.getText().toString();
                    break;
                case 1:
                    newItem = new Armor((Enumerations.ArmorTypes) spinnerItemType.getSelectedItem(), modifierValue, magicProperties);
                    ((Armor)newItem)._name = name.getText().toString();
                    break;
                case 2:
                    newItem = new Item(name.getText().toString(), 0, 0, magicProperties);
                    break;
                case 3:
                    newItem = new Consumable(name.getText().toString(), consEffect.getText().toString(), 0, 0, Integer.parseInt(consCount.getText().toString()));
                    break;
            }

            Intent intent = new Intent(CreateItemActivity.this, MainActivity.class);
            intent.putExtra(Constants.ITEM, newItem);
            intent.putExtra(Constants.ITEM_POSITION, _itemPosition);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
