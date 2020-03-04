package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.text.Html;
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
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Fettle;
import com.guigeek.devilopers.dd5charactersheet.item.Armor;
import com.guigeek.devilopers.dd5charactersheet.item.Consumable;
import com.guigeek.devilopers.dd5charactersheet.item.Item;
import com.guigeek.devilopers.dd5charactersheet.item.Weapon;

import java.io.Externalizable;
import java.util.Collections;
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
        TextView listItemDescription = convertView.findViewById(R.id.listItemDescription);
        TextView listItemFirearmDetails = convertView.findViewById(R.id.listItemFirearmDetails);
        ImageView icon = (ImageView) convertView.findViewById(R.id.listItemIcon);
        TableLayout fettleTable = (TableLayout)convertView.findViewById(R.id.listItemFettleTable);

        LinkedList<Fettle> magicProperties = null;
        listItemFirearmDetails.setVisibility(View.GONE);
        listItemDescription.setVisibility(View.GONE);

        nameTv.setText(item.toString());
        if (item instanceof Weapon) {
            Weapon weapon = (Weapon)item;
            nameTv.setText(Html.fromHtml(weapon.toHtmlString()));
            listItemDescription.setVisibility(View.VISIBLE);
            listItemDescription.setText(weapon.getDescription());
            icon.setImageDrawable(this.getContext().getResources().getDrawable(Weapon.getWeaponIcon(weapon)));
            magicProperties = weapon._magicProperties;

            if (weapon._isFirearm) {
                listItemFirearmDetails.setVisibility(View.VISIBLE);
                listItemFirearmDetails.setText("Reload: " + weapon._reload + ", misfire: " + weapon._misfire);
            }
        }
        else if (item instanceof Armor) {
            Armor armor = (Armor)item;
            nameTv.setText(Html.fromHtml(armor.toHtmlString()));
            listItemDescription.setVisibility(View.VISIBLE);
            listItemDescription.setText(armor.getDescription());
            icon.setImageDrawable(this.getContext().getResources().getDrawable(Armor.getArmorIcon(armor)));
            magicProperties = armor._magicProperties;
        }
        else if (item instanceof Item) {
            Item theItem = (Item)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_ring));
            magicProperties = theItem._magicProperties;
        }
        else if (item instanceof Consumable) {
            Consumable theItem = (Consumable)item;
            icon.setImageDrawable(this.getContext().getResources().getDrawable(R.drawable.ic_potion_ball));
            magicProperties = new LinkedList<>();
            magicProperties.add(new Fettle(Enumerations.FettleType.TEXT_FETTLE, theItem._effect, 0));
        }

        if (magicProperties != null) {
            for (Fettle effect : magicProperties) {
                TableRow aRow = new TableRow(getContext());
                TextView effectDescription = new TextView(getContext());
                effectDescription.setSingleLine(false);
                effectDescription.setMaxLines(10);
                effectDescription.setHorizontalScrollBarEnabled(false);
                effectDescription.setText(effect.toString());
                TableRow.LayoutParams params = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                effectDescription.setLayoutParams(params);
                aRow.addView(effectDescription);
                fettleTable.addView(aRow);
            }
        }


        return convertView;
    }
}
