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
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import ggbtech.muralonline.R;
import ggbtech.muralonline.TabbedMainActivity;

public class AvisoReceiver extends BroadcastReceiver {
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    public AvisoReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BR","Resposta Service");
        int num = intent.getExtras().getInt("num_avisos");
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorNot = sharedpreferences.edit();

        Log.i("not",sharedpreferences.getString("not",null));

        if(!(sharedpreferences.getString("not",null) == String.valueOf(num))){
            gerarNotificacao(context, new Intent(context, TabbedMainActivity.class), "Chegaram Novos Avisos", "Voce Possui "+qtd(num), "Clique para visualizar");
            editorNot.putString("not",String.valueOf(num));
            editorNot.commit();
            Log.i("not","valor do num :"+num);
            Log.i("not",sharedpreferences.getString("not",null));
        }


    }

    public String qtd(int num){
        if (num == 1){
            return num+" Novo Aviso";
        }
        else {
            return num+" Novos Avisos";
        }
    }

    public void gerarNotificacao(Context context, Intent intent, CharSequence ticker, CharSequence titulo, CharSequence descricao) {
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(titulo);
        builder.setContentText(descricao);
        builder.setSmallIcon(R.drawable.postit);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.postit));
        builder.setContentIntent(p);
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.postit, n);

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        }
        catch(Exception e){}
    }
}



