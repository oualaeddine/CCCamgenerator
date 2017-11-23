package berrdevbox.com.cccamgenerator;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class BootService extends BroadcastReceiver {
    private static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    private static final String ACTION2 = "android.intent.action.USER_PRESENT",
            ACTION3 = "android.intent.action.SCREEN_ON";
    private final String Tag = "BootService";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(ACTION) || intent.getAction().equalsIgnoreCase(ACTION2) || intent.getAction().equalsIgnoreCase(ACTION3)) {
            {
                startApp(context);
            }
        }
    }

    private void startApp(Context context) {
        context = context.getApplicationContext();
        Intent intent = new Intent(context, AdService.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startService(intent);
    }
}