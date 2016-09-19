package fi.ptm.implicitintentexample;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Created by PTM on 23/04/15.
 */
public class MainActivity extends Activity {

    private final String CUSTOM_INTENT = "fi.ptm.intent.action.CUSTOM";
    private final String CUSTOM_BROADCAST_INTENT = "fi.ptm.intent.action.CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // search from internet
    public void searchInternet(View view) {

        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY,"Pasi Manninen");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"No activity to handle intent.",Toast.LENGTH_LONG).show();
        }
    }

    // open a browser
    public void openBrowser(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://pasimanninen.com"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"No activity to handle intent.",Toast.LENGTH_LONG).show();
        }
    }

    // dial a number
    public void dialNumber(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"No activity to handle intent.",Toast.LENGTH_LONG).show();
        }
    }

    // send SMS message
    public void sendSMS(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
        intent.putExtra("address", "04012345678");
        intent.putExtra("sms_body", "Your message here");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"No activity to handle intent.",Toast.LENGTH_LONG).show();
        }
    }

    // show location on the map
    public void showMap(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=Piippukatu 2, Jyväskylä"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"No activity to handle intent.",Toast.LENGTH_LONG).show();
        }
    }

    // launch activity with custom intent
    public void customIntent(View view) {
        Intent intent = new Intent(CUSTOM_INTENT);
        startActivity(intent);
    }

    // send broadcast message with intent
    public void broadcastIntent(View view) {

        Intent intent = new Intent(CUSTOM_BROADCAST_INTENT);
        sendBroadcast(intent);
    }


}
