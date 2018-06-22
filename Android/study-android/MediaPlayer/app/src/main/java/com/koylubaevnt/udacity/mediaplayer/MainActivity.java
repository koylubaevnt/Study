package com.koylubaevnt.udacity.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.numb);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(getApplicationContext(), "I'm done!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onStartClick(View view) {
        mediaPlayer.start();
    }

    public void onPauseClick(View view) {
        mediaPlayer.pause();
    }

    public void onStopClick(View view) {
        mediaPlayer.stop();
    }
}
