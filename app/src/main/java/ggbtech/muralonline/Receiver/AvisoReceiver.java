package ggbtech.muralonline.Receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import ggbtech.muralonline.R;
import ggbtech.muralonline.TabbedMainActivity;

public class AvisoReceiver extends BroadcastReceiver {
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences defSharedPreferences;
    Boolean valorVibrar;
    Boolean valorNot;

    public AvisoReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BR","Resposta Service");
        int num = intent.getExtras().getInt("num_avisos");
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorNot = sharedpreferences.edit();
        Log.i("not",sharedpreferences.getString("not",null));
        defSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        valorNot = defSharedPreferences.getBoolean("notifications_new_message",true);
        Log.i("DefPrefs","notificacao"+valorNot);

        if(!(sharedpreferences.getString("not",null) == String.valueOf(num))){
            if(valorNot) {
                gerarNotificacao(context, new Intent(context, TabbedMainActivity.class), "Chegaram Novos Avisos", "Voce possui " + qtd(num), "Clique para visualizar");
                editorNot.putString("not", String.valueOf(num));
                editorNot.commit();
                Log.i("not", "valor do num :" + num);
                Log.i("not", sharedpreferences.getString("not", null));
            }
        }
    }

    public String qtd(int num){
        if (num == 1){
            return num+" novo aviso";
        }
        else {
            return num+" novos avisos";
        }
    }

    public void gerarNotificacao(Context context, Intent intent, CharSequence ticker, CharSequence titulo, CharSequence descricao) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(titulo);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.drawable.icon_sino);
        //builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.postit));
        builder.setContentIntent(p);
        Notification n = builder.build();
        defSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        valorVibrar = defSharedPreferences.getBoolean("notifications_new_message_vibrate",true);
        Log.i("DefPrefs","vibrar"+valorVibrar);
        if(valorVibrar){
            n.vibrate = new long[]{150, 300, 150, 600};
        }
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.icon_sino, n);
        try{
            Log.i("DefPrefs",defSharedPreferences.getString("notifications_new_message_ringtone",RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).toString()));
            Uri som = Uri.parse(defSharedPreferences.getString("notifications_new_message_ringtone",RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).toString()));//RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        }
        catch(Exception e){}
    }
}



