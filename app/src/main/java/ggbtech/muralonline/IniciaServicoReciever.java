package ggbtech.muralonline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class IniciaServicoReciever extends BroadcastReceiver {
    public IniciaServicoReciever() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
       Intent service = new Intent(context,AtualizarAvisosService.class);
        context.startService(service);
    }
}
