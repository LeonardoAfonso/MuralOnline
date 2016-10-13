package ggbtech.muralonline.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ggbtech.muralonline.Services.AtualizarAvisosService;

public class IniciaServicoReciever extends BroadcastReceiver {
    public IniciaServicoReciever() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("BR", "Inicia Servico");
       Intent service = new Intent(context,AtualizarAvisosService.class);
        context.startService(service);
    }
}
