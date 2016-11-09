package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Item;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 25/03/2016.
 */
public class ItemAdapter extends ArrayAdapter<Externalizable> {

    private List<Externalizable> _items;

    public ItemAdapter(Context context, int textViewResourceId, List<Externalizable> items) {
        super(context, textViewResourceId, items);
        _items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item, null);
        }
        Object item = _items.get(position);

        TextView nameTv = (TextView) convertView.findViewById(R.id.listItemName);
        ImageView icon = (ImageView) convertView.findViewById(R.id.listItemIcon);
        TableLayout fettleTable = (TableLayout)convertView.findViewById(R.id.listItemFettleTable);

        LinkedList<Fettle> magicProperties = null;

        nameTv.setText(item.toString());
        if (item instanceof Weapon) {
            Weapon weapon = (Weapon)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_fire_axe));
            magicProperties = weapon._magicProperties;
        }
        else if (item instanceof Armor) {
            Armor armor = (Armor)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_armor_vest));
            magicProperties = armor._magicProperties;
        }
        else if (item instanceof Item) {
            Item theItem = (Item)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_ring));
            magicProperties = theItem._magicProperties;
        }


        if (magicProperties != null) {
            for (Fettle effect : magicProperties) {
                TableRow aRow = new TableRow(getContext());
                TextView effectDescription = new TextView(getContext());
                effectDescription.setText(effect.toString());
                aRow.addView(effectDescription);
                fettleTable.addView(aRow);
            }
        }
        return convertView;
    }
}
