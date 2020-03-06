package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.NameAndDescription;
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
public class ArrayWithDescriptionAdapter extends ArrayAdapter<NameAndDescription> {

    private List<NameAndDescription> _items;
    private boolean _alwaysShowDescription = false;

    public ArrayWithDescriptionAdapter(Context context, int textViewResourceId, List<NameAndDescription> items) {
        super(context, textViewResourceId, items);
        _items = items;
    }

    public ArrayWithDescriptionAdapter(Context context, int textViewResourceId, List<NameAndDescription> items, boolean alwaysShowDesc) {
        super(context, textViewResourceId, items);
        _alwaysShowDescription = alwaysShowDesc;
        _items = items;
    }

    public View generateView(int position, View convertView, boolean isDropdown) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_name_and_description, null);
        }

        TextView nameTv = convertView.findViewById(R.id.listName);
        TextView descriptionTv = convertView.findViewById(R.id.listDescription);

        NameAndDescription item = _items.get(position);
        nameTv.setText(item.getName());
        descriptionTv.setText(Html.fromHtml(item.getDescription()));

        descriptionTv.setVisibility(isDropdown || _alwaysShowDescription ? View.VISIBLE : View.GONE);

        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return generateView(position, convertView, false);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return generateView(position, convertView, true);
    }
}
