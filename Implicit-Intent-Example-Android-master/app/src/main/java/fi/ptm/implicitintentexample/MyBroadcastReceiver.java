package fi.ptm.implicitintentexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by PTM on 23/04/15.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyBroadcastReceiver", "onReceive called");
        if (intent.getAction().equals("fi.ptm.intent.action.CUSTOM_BROADCAST")) {
            Log.d("MyBroadcastReceiver", "Got the intent!");
        }
    }

}
