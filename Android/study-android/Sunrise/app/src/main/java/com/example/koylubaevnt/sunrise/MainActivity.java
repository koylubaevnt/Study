package com.example.koylubaevnt.sunrise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView sunImageView = (ImageView) findViewById(R.id.sun);

        Animation sunRiseAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sun_rise);

        sunImageView.startAnimation(sunRiseAnimation);

        ImageView clockImageView = (ImageView) findViewById(R.id.clock);
        Animation clockAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clock_turn);
        clockImageView.startAnimation(clockAnimation);

        ImageView hourImageView = (ImageView) findViewById(R.id.hour_hand);
        Animation hourAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.hour_turn);
        hourImageView.startAnimation(hourAnimation);

    }
}
