package fi.jamk.sgkygolfcourses;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by niels on 6/11/2016.
 */

public class DetailActivity extends AppCompatActivity {

    private Intent intent;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        // get intent which has used to open this activity
        intent = getIntent();
        // get data from intent
        bundle = intent.getExtras();
        myToolbar.setTitle("SGKYGolfCourses:" + bundle.getString("title"));
        // update text and image views to show data
        TextView address = (TextView) findViewById(R.id.address);
        address.setText(bundle.getString("address"));
        TextView phone = (TextView) findViewById(R.id.phone);
        phone.setText(bundle.getString("phone"));
        TextView email = (TextView) findViewById(R.id.mail);
        email.setText(bundle.getString("email"));
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(bundle.getString("description"));
        TextView website = (TextView) findViewById(R.id.website);
        website.setText(bundle.getString("website"));
        TextView map = (TextView) findViewById(R.id.map);
        map.setText("Map");
        ImageView imageView = (ImageView) findViewById(R.id.image);

        new getImageTask(imageView).execute(bundle.getString("image"));


    }

    private class getImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView image;

        public getImageTask(ImageView image) {
            this.image = image;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {

            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            image.setImageBitmap(result);
        }
    }

    public void map(View v)
    {

        Intent i = new Intent(Intent.ACTION_VIEW);
        double lat = bundle.getDouble("lat");
        double lng = bundle.getDouble("lng");
        i.setData(Uri.parse("geo:"+lat+","+lng));
        startActivity(i);
    }


}
