package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.Enumerations;
import com.guigeek.devilopers.dd5charactersheet.character.Power;
import com.guigeek.devilopers.dd5charactersheet.character.Skill;

import java.io.Serializable;
import java.util.LinkedList;

public class FeatsScreen extends android.support.v4.app.ListFragment {

    protected Character _character;

    public FeatsScreen() {
    }

    public static FeatsScreen newInstance(Character iCharac) {
        FeatsScreen fragment = new FeatsScreen();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_feats, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFeats(view);
    }

    public void initFeats(View root) {
        ListView listView = getListView();
        setListAdapter(new FeatAdapter(getContext(), R.layout.list_feat, _character.getFeats()));

        FloatingActionButton addFeat = (FloatingActionButton)root.findViewById(R.id.btnAddFeat);
        addFeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder b = new AlertDialog.Builder(FeatsScreen.this.getActivity());
                b.setTitle("Select a feat to add");

                LinkedList<Power> allFeats = new LinkedList<Power>();
                final String[] featNames = getResources().getStringArray(R.array.featsName);
                final String[] featDescriptions = getResources().getStringArray(R.array.featsDescription);
                for (int i = 0; i < featNames.length; ++i) {
                    allFeats.add(new Power(featNames[i], featDescriptions[i], "Self", -1,-1, false, Enumerations.ActionType.PASSIVE));
                }

                b.setAdapter(new FeatAdapter(getContext(), R.layout.list_feat, allFeats), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        _character._feats.add(new Power(featNames[which], featDescriptions[which], "Self", -1,-1, false, Enumerations.ActionType.PASSIVE));
                        setListAdapter(new FeatAdapter(getContext(), R.layout.list_feat, _character.getFeats()));
                    }
                });

                b.show();


            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.list_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int toDelete = (info.position);

        if (item.getItemId() == R.id.menu_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
            builder.setMessage("Remove this feat?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                _character._feats.remove(toDelete);
                                setListAdapter(new FeatAdapter(getContext(), R.layout.list_feat, _character.getFeats()));
                            } catch (Exception x) {
                                System.err.format("Delete failed");
                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();



        }
        return true;
    }
    // Contextual menu ends
}
