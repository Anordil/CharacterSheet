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
import android.widget.ListView;

import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class MainActivity extends ListActivity {


    Character[] _characters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent newIntent = new Intent(getApplicationContext(), CreateCharacter.class);
                startActivityForResult(newIntent, 0);
            }
        });


        getSavedCharacters();
        ListView listView = getListView();
        setListAdapter(new CharacterAdapter(this, R.layout.list_character, _characters));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.CHARACTER, _characters[position]);
                Intent newIntent = new Intent(getApplicationContext(), SwipeActivity.class);
                newIntent.putExtras(bundle);
                startActivity(newIntent);
            }
        });

        registerForContextMenu(getListView());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.getSerializableExtra(Constants.CHARACTER) != null) {
            try {
                Character toto = (Character) data.getSerializableExtra(Constants.CHARACTER);
                FileOutputStream fos = openFileOutput(toto._name.replaceAll(" ", "") + ".ddfcs", Context.MODE_PRIVATE);
                ObjectOutputStream out = new ObjectOutputStream(fos);
                out.writeObject(toto);
                out.flush();
                out.close();
                fos.close();
                Log.d("TOTO", "Creation OK");
                getSavedCharacters();
                setListAdapter(new CharacterAdapter(this, R.layout.list_character, _characters));
            } catch (Exception e) {
                Log.d("TOTO", "Creation failed, ");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getSavedCharacters();
        setListAdapter(new CharacterAdapter(this, R.layout.list_character, _characters));
    }

    private Character[] getSavedCharacters() {
        File aFileDir = getFilesDir();
        File[] allFiles = aFileDir.listFiles();
        Log.d("TOTO", "Get saved chars");

        LinkedList<Character> aList = new LinkedList<Character>();

        for (File file : allFiles) {
            Log.d("TOTO", "Loop");

            if (file.isDirectory()) {
                continue;
            }
            try {
                FileInputStream aFIS = new FileInputStream(file);
                ObjectInputStream aOIS = new ObjectInputStream(aFIS);
                Object aSerializedObject = aOIS.readObject();
                if (aSerializedObject instanceof Character) {
                    aList.add((Character) aSerializedObject);
                }
                Log.d("TOTO", "Loop OK");
            } catch (Exception e) {
                Log.d("TOTO", "Loop fail");
                e.printStackTrace();
                file.delete();
            }
        }

        Character[] result = new Character[aList.size()];
        int i = 0;
        for (Character c : aList) {
            result[i++] = c;
        }

        _characters = result;
        return result;
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
        final Character toDelete = _characters[info.position];

        if (item.getItemId() == R.id.menu_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Delete this character?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                deleteFile(toDelete._name.replaceAll(" ", "") + ".ddfcs");
                                getSavedCharacters();
                                setListAdapter(new CharacterAdapter(MainActivity.this, R.layout.list_character, _characters));
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
