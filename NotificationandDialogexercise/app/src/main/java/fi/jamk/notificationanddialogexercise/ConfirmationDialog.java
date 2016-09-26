package fi.jamk.notificationanddialogexercise;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by niels on 26/09/2016.
 */

public class ConfirmationDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class to create a Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.title)
                    .setMessage(R.string.dialog_message)
                    .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //show notification
                            EditText t1 = (EditText) getActivity().findViewById(R.id.editText);
                            EditText t2 = (EditText) getActivity().findViewById(R.id.editText2);
                            String text = t1.getText().toString() + " " + t2.getText().toString() + " has been added";
                            createNotification(text);
                        }
                    })
                    .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            Toast.makeText(getActivity(), "Member not added", Toast.LENGTH_SHORT).show();
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }

    public void createNotification(String text) {
        // create a new notification
        Notification notification  = new Notification.Builder(getActivity())
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Member added")
                .setContentText(text)
                .setSmallIcon(R.drawable.ptm)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PRIVATE).build();
        // connect notification manager
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);


        notificationManager.notify(0, notification);
    }

}
