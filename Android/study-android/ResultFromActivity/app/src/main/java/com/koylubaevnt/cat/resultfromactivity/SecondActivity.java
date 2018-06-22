package com.koylubaevnt.cat.resultfromactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SecondActivity extends AppCompatActivity {

    public static final String THIEF = "com.koylubaevnt.cat.THIEF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onRadioClick(View view) {
        Intent intent = new Intent();
        RadioButton rb = (RadioButton) view;
        switch (view.getId()) {
            case R.id.radioDog:
                intent.putExtra(THIEF, rb.getText().toString());
                break;
            case R.id.radioCrow:
                intent.putExtra(THIEF, rb.getText().toString());
                break;
            case R.id.radioCat:
                intent.putExtra(THIEF, "Лошадь Пржевальского");
                break;
            default:
                break;
        }

        setResult(RESULT_OK, intent);
        finish();
    }
}
