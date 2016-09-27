package ggbtech.muralonline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class bootReceiver extends BroadcastReceiver {
    public bootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, bootService.class);
            context.startService(pushIntent);
        }
    }
}
