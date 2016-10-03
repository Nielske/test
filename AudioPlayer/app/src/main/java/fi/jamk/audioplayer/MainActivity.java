package fi.jamk.audioplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ListView listView;
    private String mediaPath;
    private List<String> songs = new ArrayList<>();
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private LoadSongTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        mediaPath = Environment.getExternalStorageDirectory().getPath() + "/Music/";

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                try{
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(songs.get(position));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
                catch(IOException e){
                    Toast.makeText(getBaseContext(),"Cannot start audio", Toast.LENGTH_SHORT).show();
                }
            }
        });

        task = new LoadSongTask();
        task.execute();

    }

    @Override
    public void onStop(){
        super.onStop();
        if(mediaPlayer.isPlaying()) mediaPlayer.reset();
    }

    private class LoadSongTask extends AsyncTask<Void, String, Void> {

        private List<String> loadedSongs = new ArrayList<String>();

        protected void onPreExecute(){
            Toast.makeText(getApplicationContext(),"Loading...", Toast.LENGTH_LONG).show();
        }

        protected Void doInBackground(Void... url){
            updateSongListRecursive(new File(mediaPath));
            return null;
        }

        public void updateSongListRecursive(File path){
            if(path.isDirectory()){
                for(int i = 0; i< path.listFiles().length;i++){
                    File file = path.listFiles()[i];
                    updateSongListRecursive(file);
                }
            }
            else{
                String name = path.getAbsolutePath();
                publishProgress(name);
                if(name.endsWith(".mp3")){
                    loadedSongs.add(name);
                }
            }
        }


        protected void onPostExecute(Void args){
            ArrayAdapter<String> songList = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, loadedSongs);
            listView.setAdapter(songList);
            songs = loadedSongs;

            Toast.makeText(getApplicationContext(),"Songs=" + songs.size(),Toast.LENGTH_LONG).show();
        }

    }

}
