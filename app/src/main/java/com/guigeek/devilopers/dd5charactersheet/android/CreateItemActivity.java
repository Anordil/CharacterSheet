package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Item;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class CreateItemActivity extends ListActivity {


    Button addProperty, createItem;
    LinkedList<Fettle> magicProperties;

    EditText name, modifier, propertyModifier;
    Spinner spinnerItemCategory, spinnerItemType, spinnerPropertyType, spinnerPropertyDescriber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        // Magic properties list
        magicProperties = new LinkedList<>();
        ListView listView = getListView();
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, magicProperties));
        registerForContextMenu(getListView());

        // Edit text
        name = (EditText)findViewById(R.id.itemName);
        modifier = (EditText)findViewById(R.id.itemModifier);
        propertyModifier = (EditText)findViewById(R.id.etPropertyModifier);

        // Buttons
        addProperty = (Button)findViewById(R.id.buttonAddProperty);
        createItem = (Button)findViewById(R.id.buttonCreateItemSave);

        addProperty.setOnClickListener(new AddPropertyListener());
        createItem.setOnClickListener(new CreateItemListener());


        // Spinners
        spinnerItemCategory = (Spinner)findViewById(R.id.spinnerItemCategory);
        spinnerItemType = (Spinner)findViewById(R.id.spinnerItemType);
        spinnerPropertyType = (Spinner)findViewById(R.id.spinnerPropertyType);
        spinnerPropertyDescriber = (Spinner)findViewById(R.id.spinnerPropertyDescriber);


        // Init category spinner
        String[] itemCategories = {"Weapon", "Armor", "Worn item"};
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, itemCategories);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemCategory.setAdapter(adapterCategory);
        spinnerItemCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        spinnerItemType.setVisibility(View.VISIBLE);
                        modifier.setVisibility(View.VISIBLE);
                        spinnerItemType.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.WeaponTypes.values()));
                        break;
                    case 1:
                        spinnerItemType.setVisibility(View.VISIBLE);
                        modifier.setVisibility(View.VISIBLE);
                        spinnerItemType.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.ArmorTypes.values()));
                        break;
                    default:
                        spinnerItemType.setVisibility(View.GONE);
                        modifier.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        // Magic properties - category
        spinnerPropertyType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Enumerations.FettleType.values()));
        spinnerPropertyType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Enumerations.FettleType type = (Enumerations.FettleType)spinnerPropertyType.getAdapter().getItem(position);
                spinnerPropertyDescriber.setVisibility(View.VISIBLE);
                propertyModifier.setVisibility(View.VISIBLE);

                switch (type) {
                    case ATTRIBUTE_MODIFIER:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.Attributes.values()));
                        break;
                    case ABILITY_CHECK_ADVANTAGE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.Skills.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case ABILITY_CHECK_DISADVANTAGE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.Skills.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case ABILITY_CHECK_MODIFIER:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.Skills.values()));
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
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.DamageTypes.values()));
                        break;
                    case DAMAGE_RESISTANCE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.DamageTypes.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case DAMAGE_VULNERABILITY:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.DamageTypes.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case SAVING_THROW_ADVANTAGE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.SavingThrows.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case SAVING_THROW_DISADVANTAGE:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.SavingThrows.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    case SAVING_THROW_MODIFIER:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.SavingThrows.values()));
                        break;
                    case IMMUNITY:
                        spinnerPropertyDescriber.setAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, Enumerations.Immunities.values()));
                        propertyModifier.setVisibility(View.INVISIBLE);
                        break;
                    default: break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


    }


    @Override
    public void onResume() {
        super.onResume();
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, magicProperties));
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
            setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, magicProperties));
        }
        return true;
    }
    // Contextual menu ends



    class AddPropertyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Fettle aAddedProperty = new Fettle(
                    (Enumerations.FettleType)spinnerPropertyType.getSelectedItem(),
                    propertyModifier.getText().toString().length() > 0 ? Integer.parseInt(propertyModifier.getText().toString()) : 0,
                    ((Enum)spinnerPropertyDescriber.getSelectedItem()).ordinal()
            );
            magicProperties.add(aAddedProperty);
            setListAdapter(new ArrayAdapter<>(CreateItemActivity.this, android.R.layout.simple_spinner_item, magicProperties));
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
                default:
                    newItem = new Item(name.getText().toString(), 0, 0, magicProperties);
                    break;
            }

            Intent intent = new Intent(CreateItemActivity.this, MainActivity.class);
            intent.putExtra(Constants.ITEM, newItem);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
