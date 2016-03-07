package com.guigeek.devilopers.dd5charactersheet.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.*;
import com.guigeek.devilopers.dd5charactersheet.character.Character;
import com.guigeek.devilopers.dd5charactersheet.character.classes.Paladin;
import com.guigeek.devilopers.dd5charactersheet.character.races.MountainDwarf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] totoAttr = {10,10,10,10,10,10};
                Character toto = new Character("Toto", new Paladin(), new MountainDwarf(), 1, totoAttr);


                try {
                    FileOutputStream fos = openFileOutput(toto._name.replaceAll(" ", "") + ".ddfcs", Context.MODE_PRIVATE);
                    ObjectOutputStream out = new ObjectOutputStream(fos);
                    out.writeObject(toto);
                    out.flush();
                    out.close();
                    fos.close();
                    Log.d("TOTO", "Export OK");
                } catch (Exception e) {
                    Log.d("TOTO", "Export failed, ");
                    e.printStackTrace();
                }
            }
        });


        final Character[] characters = getSavedCharacters();
        ArrayAdapter<Character> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, characters);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.CHARACTER, characters[position]);
                Intent newIntent = new Intent(getApplicationContext(), DisplayScreen.class);
                newIntent.putExtras(bundle);
                startActivityForResult(newIntent, 0);
            }
        });
    }

    private Character[] getSavedCharacters() {
        File aFileDir = getFilesDir();
        File[] allFiles = aFileDir.listFiles();
        Log.d("TOTO", "Get saved chars");

        LinkedList<Character> aList = new LinkedList<Character>();

        for (File file : allFiles) {
            Log.d("TOTO", "Loop");
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
            }
        }

        Character[] result = new Character[aList.size()];
        int i = 0;
        for (Character c : aList) {
            result[i++] = c;
        }

        return result;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
