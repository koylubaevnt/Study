package com.koylubaevnt.forbiddenchangeorientation;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    static final String ORIENTATION_PORTRAIT = "Портретный режим";
    static final String ORIENTATION_LANDSCAPE = "Альбомный режим";

    private static final String KEY_COUNT = "COUNT";

    // определяем изменение ориентации экрана
    boolean mState;

    private TextView mInfoTextView;
    private int mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button);

        mState = true;
        // установим текст по умолчанию
        mButton.setText(ORIENTATION_LANDSCAPE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mInfoTextView = (TextView) findViewById(R.id.textView);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt(KEY_COUNT, 0);
            mInfoTextView.setText("Я насчитал " + mCount + " ворон");
        }

    }

    public void onClick(View view) {
        if(view.getId() == R.id.buttonCrow) {
            mCount++;
            mInfoTextView.setText("Я насчитал " + mCount + " ворон");
        } else {
            // state FALSE: переключаемся на LANDSCAPE
            if (!mState) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mButton.setText(ORIENTATION_LANDSCAPE);
            }
            // state TRUE: переключаемся на PORTRAIT
            else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mButton.setText(ORIENTATION_PORTRAIT);
            }
            // обновляем state на противоположное значение
            mState = !mState;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Проверяем ориентацию экрана
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(KEY_COUNT, mCount);
    }
}
