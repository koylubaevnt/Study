package com.koylubaevnt.keyboard;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Button Back
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //openQuitDialog();
        if(backPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!", Toast.LENGTH_LONG).show();
        }
        backPressed = System.currentTimeMillis();
    }

    //Button Home
    @Override
    protected void onUserLeaveHint() {
        Toast.makeText(getApplicationContext(), "Нажата кнопка HOME!", Toast.LENGTH_LONG).show();
        super.onUserLeaveHint();
    }

    //Button Menu
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU) {
            event.startTracking();;
            editText.setText("Key Down");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU) {
            event.startTracking();;
            editText.setText("Long Press");
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(CustomViewDemoActivity.this);
        quitDialog.setTitle("Выход: Вы уверены?");

        quitDialog.setPositiveButton("Таки да!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        quitDialog.show();
    }


}
