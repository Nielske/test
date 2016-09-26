package fi.jamk.notificationanddialogexercise;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.R.attr.visibility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void confirmationDialog(View view) {
        ConfirmationDialog cDialog = new ConfirmationDialog();
        cDialog.show(getFragmentManager(), "Add");

    }

}
