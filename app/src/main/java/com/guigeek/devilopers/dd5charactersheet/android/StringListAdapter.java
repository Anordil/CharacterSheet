package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ggallani on 25/03/2016.
 */
public class StringListAdapter extends ArrayAdapter<String> {

    private List<String> _items;

    public StringListAdapter(Context context, int textViewResourceId, List<String> items) {
        super(context, textViewResourceId, items);
        _items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_string, null);
        }
        String item = _items.get(position);
        TextView textView = (TextView) convertView.findViewById(R.id.listString);
        if (textView != null) {
            textView.setText(item);
        }
        return convertView;
    }
}
