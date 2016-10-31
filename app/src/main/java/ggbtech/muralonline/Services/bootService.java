package ggbtech.muralonline.Services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

public class bootService extends IntentService {
    SharedPreferences defSharedPreferences;
    public bootService() {
        super("bootService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        defSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);
        if(alarmeAtivo){
            Log.i("Script", "Novo alarme");
            Intent it = new Intent("ALARME_DISPARADO");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, it, 0);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 3);
            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            Log.i("DefPrefs","Sync time :"+defSharedPreferences.getString("sync_frequency","180"));
            int min = Integer.parseInt(defSharedPreferences.getString("sync_frequency","180"))*60000;
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), min, p);
            Log.i("DefPrefs","Sync time milli :"+min);
        }
        else{
            Log.i("Script", "Alarme ja ativo");
            int min = Integer.parseInt(defSharedPreferences.getString("sync_frequency",null))*60000;
            Log.i("DefPrefs","Sync time milli :"+min);
        }

    }
}
