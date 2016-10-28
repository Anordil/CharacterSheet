package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SwipeActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    protected com.guigeek.devilopers.dd5charactersheet.character.Character _character;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private List<Fragment> fragments = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        Bundle bundle = this.getIntent().getExtras();
        Serializable data = bundle.getSerializable(Constants.CHARACTER);
        _character = (com.guigeek.devilopers.dd5charactersheet.character.Character) data;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Add tabs
        fragments.add(CombatScreen.newInstance(_character));
        fragments.add(SkillsScreen.newInstance(_character));
        fragments.add(FeatsScreen.newInstance(_character));
        fragments.add(InventoryScreen.newInstance(_character));
        fragments.add(StatsScreen.newInstance(_character));

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setTitle(_character._name);
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
        if (id == R.id.action_save) {
            save();
            return true;
        }
        if (id == R.id.action_shortrest) {
            _character.doShortRest();
            for (Fragment frg: fragments) {
                if (frg instanceof CombatScreen) {
                    ((CombatScreen)frg).refreshSheet();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .detach(frg)
                            .attach(frg)
                            .commit();
                }
            }
            save();
            return true;
        }
        if (id == R.id.action_longrest) {
            _character.doLongRest();
            for (Fragment frg: fragments) {
                if (frg instanceof CombatScreen) {
                    ((CombatScreen)frg).refreshSheet();
                    getSupportFragmentManager()
                            .beginTransaction()
                            .detach(frg)
                            .attach(frg)
                            .commit();
                }
            }
            save();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "COMBAT";
                case 1:
                    return "SKILLS";
                case 2:
                    return "FEATS";
                case 3:
                    return "INVENTORY";
                case 4:
                    return "STATS";
            }
            return null;
        }
    }

    public void refreshTabs() {
        _character.refresh();
        save();
    }

    public void save() {
        try {
            FileOutputStream fos = openFileOutput(_character._name.replaceAll(" ", "") + ".ddfcs", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(_character);
            out.flush();
            out.close();
            fos.close();
            Toast.makeText(App.getContext(), "Character saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(App.getContext(), "Saved failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
