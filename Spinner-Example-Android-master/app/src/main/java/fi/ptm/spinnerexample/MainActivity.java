package fi.ptm.spinnerexample;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 *
 * @author PTM
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find spinners
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.emails_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
    }

    public void buttonClicked(View view) {
        EditText ed1 = (EditText) findViewById(R.id.editText1);
        String email1 = ed1.getText().toString();
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        String email1type = spinner1.getSelectedItem().toString();

        EditText ed2 = (EditText) findViewById(R.id.editText2);
        String email2 = ed2.getText().toString();
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        String email2type = spinner2.getSelectedItem().toString();

        TextView textView = (TextView) findViewById(R.id.textView_save);
        textView.setText(email1 + " " + email1type + "\n" + email2 + " " + email2type);
    }

}
