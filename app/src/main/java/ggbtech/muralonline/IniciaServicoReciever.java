package ggbtech.muralonline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
