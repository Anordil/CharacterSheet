package com.guigeek.devilopers.dd5charactersheet.android;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.guigeek.devilopers.dd5charactersheet.App;
import com.guigeek.devilopers.dd5charactersheet.R;
import com.guigeek.devilopers.dd5charactersheet.character.Character;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SwipeActivity extends AppCompatActivity {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    protected Character _character;

    private Timer _timer;
    private float _initialBrightness = 1F;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private List<Fragment> fragments = new LinkedList<>();


    @Override
    public void onUserInteraction() {
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = _initialBrightness;
        getWindow().setAttributes(layout);

        try {
            _timer.cancel();
        }
        catch (Exception up){}
        _timer = new Timer();
        _timer.schedule(new DimDisplayTask(), 20000);

        super.onUserInteraction();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        WindowManager.LayoutParams layout = getWindow().getAttributes();
        _initialBrightness = layout.screenBrightness;

        _timer = new Timer();

        Bundle bundle = this.getIntent().getExtras();
        Serializable data = bundle.getSerializable(Constants.CHARACTER);
        _character = (Character) data;

        _character.refreshFettles();

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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Fragment aActivePage = mSectionsPagerAdapter.getItem(position);
                aActivePage.onResume();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

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
            for (Fragment frg : fragments) {
                if (frg instanceof CombatScreen) {
                    ((CombatScreen) frg).refreshSheet();
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
            for (Fragment frg : fragments) {
                if (frg instanceof CombatScreen) {
                    ((CombatScreen) frg).refreshSheet();
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

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
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
                    return "ITEMS";
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

    public class DimDisplayTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    WindowManager.LayoutParams layout = getWindow().getAttributes();
                    layout.screenBrightness = 0F;
                    getWindow().setAttributes(layout);
                }
            });
        }
    }
}
