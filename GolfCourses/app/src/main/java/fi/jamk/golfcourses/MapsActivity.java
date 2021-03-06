package fi.jamk.golfcourses;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private JSONArray golfCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        FetchDataTask task = new FetchDataTask();
        task.execute("http://ptm.fi/jamk/android/golf_courses.json");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // store map object to member variable
        mMap = googleMap;
        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.infowindow,null);
                TextView title = (TextView) view.findViewById(R.id.textView);
                TextView content = (TextView) view.findViewById(R.id.textView2);
                title.setText(marker.getTitle());
                content.setText(marker.getSnippet());
                return view;
            }
        });

        try{
        for (int i=0;i < golfCourses.length();i++) {
            JSONObject gc = golfCourses.getJSONObject(i);

            // add one marker
            LatLng golf = new LatLng(gc.getDouble("lat"), gc.getDouble("lng"));
            // check golf type
            switch(gc.getString("Tyyppi")){
                case "Etu":  mMap.addMarker(new MarkerOptions()
                        .position(golf)
                        .title(gc.getString("Kentta"))
                        .snippet(gc.getString("Osoite")  + "\n" + gc.getString("Puhelin") + "\n" + gc.getString("Sahkoposti") + "\n" + gc.getString("Webbi"))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                );
                    break;
                case "Kulta":   mMap.addMarker(new MarkerOptions()
                        .position(golf)
                        .title(gc.getString("Kentta"))
                        .snippet(gc.getString("Osoite")  + "\n" + gc.getString("Puhelin") + "\n" + gc.getString("Sahkoposti") + "\n" + gc.getString("Webbi"))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                );
                    break;
                case "Kulta/Etu":   mMap.addMarker(new MarkerOptions()
                        .position(golf)
                        .title(gc.getString("Kentta"))
                        .snippet(gc.getString("Osoite")  + "\n" + gc.getString("Puhelin") + "\n" + gc.getString("Sahkoposti") + "\n" + gc.getString("Webbi"))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))

                );
                    break;
                default:    mMap.addMarker(new MarkerOptions()
                        .position(golf)
                        .title(gc.getString("Kentta"))
                        .snippet(gc.getString("Osoite")  + "\n" + gc.getString("Puhelin") + "\n" + gc.getString("Sahkoposti") + "\n" + gc.getString("Webbi"))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                );

            }



        }
        } catch (JSONException e) {
            Log.e("JSON", "Error getting data.");
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(64,26)));


    }

    class FetchDataTask extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            JSONObject json = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                json = new JSONObject(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) urlConnection.disconnect();
            }
            try{
            golfCourses = json.getJSONArray("kentat");
            } catch (JSONException e) {
                Log.e("JSON", "Error getting data.");
            }
            return json;
        }


    }
}
