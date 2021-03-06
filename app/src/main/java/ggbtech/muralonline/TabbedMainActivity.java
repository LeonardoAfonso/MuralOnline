package ggbtech.muralonline;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import ggbtech.muralonline.Classes.AtualizarEvent;
import ggbtech.muralonline.Classes.MySingleton;
import ggbtech.muralonline.Settings.SettingsActivity;

public class TabbedMainActivity extends AppCompatActivity {
    private SharedPreferences defSharedPreferences;
    private Context myContext;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_main);
        myContext=getApplicationContext();
        defSharedPreferences = PreferenceManager.getDefaultSharedPreferences(myContext);

        if(defSharedPreferences.getBoolean("notifications_new_message",true)){
            boolean alarmeAtivo = (PendingIntent.getBroadcast(myContext, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

            if(alarmeAtivo){
                //Log.i("Script", "Novo alarme");
                Intent intent = new Intent("ALARME_DISPARADO");
                PendingIntent p = PendingIntent.getBroadcast(myContext, 0, intent, 0);
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.add(Calendar.SECOND, 3);
                AlarmManager alarme = (AlarmManager) myContext.getSystemService(Context.ALARM_SERVICE);
                //Log.i("DefPrefs","Sync time :"+defSharedPreferences.getString("sync_frequency","180"));
                int min = Integer.parseInt(defSharedPreferences.getString("sync_frequency",null))*60000;
                alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), min, p);
                //Log.i("DefPrefs","Sync time milli :"+min);
            }
            else{
                Log.i("Script", "Alarme ja ativo");
                int min = Integer.parseInt(defSharedPreferences.getString("sync_frequency",null))*60000;
                Log.i("DefPrefs","Sync time milli :"+min);
            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        int limit = (mSectionsPagerAdapter.getCount() > 1 ? mSectionsPagerAdapter.getCount() - 1 : 1);
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(limit);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new AtualizarEvent("Hello EventBus!"));
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                super.onTabSelected(tab);
                int position = tab.getPosition();
                if(position != 0){
                    fab.setVisibility(View.GONE);
                }else {
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_main, menu);
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[] { "Avisos", "Avisos Fixos", "Parcerias", "Sobre", };

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 0 : return TabAvisosFragment.newInstance();
                case 1 : return TabAvisosFixosFragment.newInstance();
                case 2 : return TabParceirosFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Avisos";
                case 1:
                    return "Avisos Fixos";
                case 2:
                    return "Parceiros";
            }
            return null;
        }
    }
}
