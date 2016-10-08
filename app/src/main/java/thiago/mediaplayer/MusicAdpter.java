package thiago.mediaplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by thiago on 08/10/16.
 */
public class MusicAdpter extends ArrayAdapter<Music>{

    MediaPlayer mediaPlayer;

    public MusicAdpter(Context context, ArrayList<Music> musics) {
        super(context, 0, musics);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listMusicItem = convertView;

        if (listMusicItem == null) {
            listMusicItem = LayoutInflater.from(getContext()).inflate(R.layout.music_item, parent, false);
        }

        final Music currentMusic = getItem(position);
        mediaPlayer = MediaPlayer.create(getContext(), currentMusic.getRaw());

        Button btn_resume = (Button) listMusicItem.findViewById(R.id.btn_resume);
        Button btn_pause = (Button) listMusicItem.findViewById(R.id.btn_pause);
        TextView music_name = (TextView) listMusicItem.findViewById(R.id.music_name);
        music_name.setText(currentMusic.getName());

        music_name.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                AssetFileDescriptor afd = getContext().getResources().openRawResourceFd(currentMusic.getRaw());

                try {

                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    afd.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        btn_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        return listMusicItem;
    }
}
