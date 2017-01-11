package ggbtech.muralonline.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import ggbtech.muralonline.Services.AtualizarAvisosService;

public class IniciaServicoReciever extends BroadcastReceiver {
    SharedPreferences defSharedPreferences;
    Boolean valorNot;
    public IniciaServicoReciever() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        defSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Log.i("DefPrefs","notificacao"+valorNot);
        if(defSharedPreferences.getBoolean("notifications_new_message",true)){
            Log.i("BR", "Inicia Servico");
            Intent service = new Intent(context,AtualizarAvisosService.class);
            context.startService(service);    
        }
        
    }
}
