package com.koylubaevnt.themesandstyles;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        AlertDialog.Builder builder;
        switch (view.getId()) {
            case R.id.buttonAbout:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonDialogStandard:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Dialog");
                builder.setMessage("Покормить кота?");
                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);
                builder.show();
                break;
            case R.id.buttonDialogStyle:
                builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Dialog");
                builder.setMessage("Покормить кота?");
                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);
                builder.show();
                break;
            default:
                break;
        }
    }
}
