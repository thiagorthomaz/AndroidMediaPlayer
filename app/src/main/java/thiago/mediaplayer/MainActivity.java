package thiago.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMusicList();

        MusicAdpter musicAdpter = new MusicAdpter(this, getMusicList());
        ListView listView = (ListView) findViewById(R.id.musicList);
        listView.setAdapter(musicAdpter);

    }

    private ArrayList<Music> getMusicList() {

        ArrayList<Music> songs = new ArrayList<Music>();

        Field[] fields = R.raw.class.getFields();

        for (int i = 1; i <fields.length; i++) {
            int resourceID = this.getResources().getIdentifier(fields[i].getName(), "raw", this.getPackageName());
            Music song_name = new Music(fields[i].getName(), resourceID);
            songs.add(song_name);
        }

        return songs;

    }

}
