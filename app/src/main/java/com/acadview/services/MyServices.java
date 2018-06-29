package com.acadview.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyServices extends Service {

    MediaPlayer mediaPlayer;
    int currentPos;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this,R.raw.song);
        mediaPlayer.setLooping(true);
        Toast.makeText(this,"service Create",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getStringExtra("order").equalsIgnoreCase("play")) {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);

        }
        else if(intent.getStringExtra("order").equals("pause")){
            currentPos=mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
        else if(intent.getStringExtra("order").equalsIgnoreCase("resume")){
            mediaPlayer.seekTo(currentPos);
            mediaPlayer.start();
        }
        return START_STICKY;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
    }
}
