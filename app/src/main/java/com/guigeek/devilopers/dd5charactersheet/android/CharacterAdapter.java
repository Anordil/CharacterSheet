package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;

/**
 * Created by ggallani on 25/03/2016.
 */
public class CharacterAdapter extends ArrayAdapter<Character> {

    private Character[] _items;

    public CharacterAdapter(Context context, int textViewResourceId, Character[] items) {
        super(context, textViewResourceId, items);
        _items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_character, null);
        }
        Character character = _items[position];
        if (character != null) {
            TextView nameTv = (TextView) convertView.findViewById(R.id.listCharName);
            TextView levelTv = (TextView) convertView.findViewById(R.id.listCharLevel);
            TextView classTv = (TextView) convertView.findViewById(R.id.listCharClass);
            TextView raceTv = (TextView) convertView.findViewById(R.id.listCharRace);
            ImageView icon = (ImageView) convertView.findViewById(R.id.listCharClassIson);


            nameTv.setText(character._name);
            levelTv.setText(Integer.toString(character._level) + (character._secondaryClass != null ? ("/" + Integer.toString(character._levelSecondaryClass)) : ""));
            classTv.setText(character._class.getQualifiedClassName() + (character._secondaryClass != null ? ("/" + character._secondaryClass.getQualifiedClassName()) : ""));
            raceTv.setText(character._race.getName());

            if (icon != null) {
                icon.setImageDrawable(this.getContext().getResources().getDrawable(character._class.getIconResource()));
            }
        }
        return convertView;
    }
}
