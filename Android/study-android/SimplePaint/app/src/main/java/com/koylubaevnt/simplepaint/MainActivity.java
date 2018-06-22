package com.koylubaevnt.simplepaint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Draw2D draw2D = new Draw2D(getApplicationContext());
        setContentView(draw2D);
    }
}
