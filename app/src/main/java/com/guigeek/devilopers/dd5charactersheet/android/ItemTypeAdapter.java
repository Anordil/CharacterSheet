package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Consumable;
import com.guigeek.devilopers.dd5charactersheet.item.Item;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 25/03/2016.
 */
public class ItemTypeAdapter extends ArrayAdapter<Externalizable> {

    private List<Externalizable> _items;

    public ItemTypeAdapter(Context context, int textViewResourceId, List<Externalizable> items) {
        super(context, textViewResourceId, items);
        _items = items;
    }


    public View createItemView(int position, View convertView, boolean isDropdown) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item, null);
        }
        Object item = _items.get(position);

        TextView nameTv = convertView.findViewById(R.id.listItemName);
        TextView listItemDescription = convertView.findViewById(R.id.listItemDescription);
        TextView listItemFirearmDetails = convertView.findViewById(R.id.listItemFirearmDetails);
        ImageView icon = convertView.findViewById(R.id.listItemIcon);

        listItemFirearmDetails.setVisibility(View.GONE);
        listItemDescription.setVisibility(View.GONE);

        if (item instanceof Weapon) {
            Weapon weapon = (Weapon)item;
            nameTv.setText(weapon._type._name);
            listItemDescription.setVisibility(isDropdown ? View.VISIBLE : View.GONE);
            listItemDescription.setText(weapon.getDescription());
            icon.setImageDrawable(this.getContext().getResources().getDrawable(Weapon.getWeaponIcon(weapon)));

            if (weapon._isFirearm) {
                listItemFirearmDetails.setVisibility(isDropdown ? View.VISIBLE : View.GONE);
                listItemFirearmDetails.setText("Reload: " + weapon._reload + ", misfire: " + weapon._misfire);
            }
        }
        else if (item instanceof Armor) {
            Armor armor = (Armor)item;
            nameTv.setText(armor._type.toString());
            listItemDescription.setVisibility(isDropdown ? View.VISIBLE : View.GONE);
            listItemDescription.setText(armor.getDescription() + (armor._hasStealthDisadvantage ? "\nStealth Checks Disadvantage" : ""));
            icon.setImageDrawable(this.getContext().getResources().getDrawable(Armor.getArmorIcon(armor)));
        }

        icon.setVisibility(isDropdown ? View.VISIBLE : View.GONE);
        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, false);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, true);
    }
}
