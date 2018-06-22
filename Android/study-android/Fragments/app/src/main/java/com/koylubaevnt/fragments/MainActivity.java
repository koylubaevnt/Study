package com.koylubaevnt.fragments;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements Fragment1.OnSelectedButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment2 fragment2 = (Fragment2) fragmentManager.findFragmentById(R.id.fragment2);

        if(fragment2 == null || !fragment2.isVisible()) {
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            intent.putExtra("buttonIndex", buttonIndex);
            startActivity(intent);
        } else {
            fragment2.setDescription(buttonIndex);
        }
    }
}
