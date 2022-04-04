package ru.mirea.msv.mireaproject;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class PlayerService extends Service {
    private MediaPlayer mediaPlayer;
    private String path;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        if (intent.hasExtra("a"))
            path = intent.getExtras().getString("a");
        else
            path = null;
        if (path != null) {
            mediaPlayer = MediaPlayer.create(this, Uri.parse(path));
        }
        else
            mediaPlayer = MediaPlayer.create(this, R.raw.track001);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
