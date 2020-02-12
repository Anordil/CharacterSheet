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
public class FeatAdapter extends ArrayAdapter<Power> {

    private List<Power> _items;

    public FeatAdapter(Context context, int textViewResourceId, List<Power> items) {
        super(context, textViewResourceId, items);
        _items = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_feat, null);
        }
        Power feat = _items.get(position);
        if (feat != null) {
            TextView nameTv = (TextView) convertView.findViewById(R.id.listFeatName);
            TextView descTv = (TextView) convertView.findViewById(R.id.listFeatDesc);


            nameTv.setText(feat._name);
            descTv.setText(feat._description);
        }
        return convertView;
    }
}
