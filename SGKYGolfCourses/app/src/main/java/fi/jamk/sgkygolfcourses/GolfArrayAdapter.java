package fi.jamk.sgkygolfcourses;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niels on 6/11/2016.
 */

public class GolfArrayAdapter extends RecyclerView.Adapter<GolfArrayAdapter.ViewHolder> {

    // adapter data
    private List<JSONObject> golfCourses;

    // adapter constructor, get data from activity
    public GolfArrayAdapter(List<JSONObject> golfCourses) {
        this.golfCourses = golfCourses;
    }

    // return the size of employeeList (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return golfCourses.size();
    }

    // create a view for this card
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.golf_card, parent, false);
        return new ViewHolder(view);
    }

    // replace the contents of a view (invoked by the layout manager)
    // - get element from employeelist at this position
    // - replace the contents of the view with that element
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        try {
            JSONObject golfCourse = golfCourses.get(position);
            new getImageTask(viewHolder.golfCourseImageView).execute("http://ptm.fi/jamk/android/golfcourses/" + golfCourse.getString("Kuva"));
            viewHolder.golfCourseTitle.setText(golfCourse.getString("Kentta"));
            viewHolder.golfCourseAdress.setText(golfCourse.getString("Osoite"));
            viewHolder.golfCoursePhone.setText(golfCourse.getString("Puhelin"));
            viewHolder.golfCourseEmail.setText(golfCourse.getString("Sahkoposti"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    // view holder class to specify card UI objects
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView golfCourseImageView;
        public TextView golfCourseTitle;
        public TextView golfCourseAdress;
        public TextView golfCoursePhone;
        public TextView golfCourseEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            // get layout ids
            golfCourseImageView = (ImageView) itemView.findViewById(R.id.golfCourseImageView);
            golfCourseTitle = (TextView) itemView.findViewById(R.id.golfCourseTitle);
            golfCourseAdress = (TextView) itemView.findViewById(R.id.golfCourseAddress);
            golfCoursePhone = (TextView) itemView.findViewById(R.id.golfCoursePhone);
            golfCourseEmail = (TextView) itemView.findViewById(R.id.golfCourseEmail);
            // add click listener for a card
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    // get list row data (now String as a phone name)
                    JSONObject golfCourse = golfCourses.get(position);
                    // create an explicit intent
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);


                    // add data to intent

                    try {
                        double latitude = golfCourse.getDouble("lat");
                        double longitude = golfCourse.getDouble("lng");
                        intent.putExtra("lat", latitude)
                                .putExtra("lng",longitude)
                                .putExtra("title", golfCourse.getString("Kentta"))
                                .putExtra("address", golfCourse.getString("Osoite"))
                                .putExtra("phone", golfCourse.getString("Puhelin"))
                                .putExtra("email", golfCourse.getString("Sahkoposti"))
                                .putExtra("website", golfCourse.getString("Webbi"))
                                .putExtra("image", "http://ptm.fi/jamk/android/golfcourses/" + golfCourse.getString("Kuva"))
                                .putExtra("description", golfCourse.getString("Kuvaus"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // start a new activity
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
