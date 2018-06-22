package com.koylubaevnt.keyboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by KoylubaevNT on 11.07.2017.
 */

public class TestActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private EditText mEditSearch;
    private EditText mEditGo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_activity);

        mEditSearch = (EditText) findViewById(R.id.editSearch);
        mEditSearch.setOnEditorActionListener(this);

        mEditGo = (EditText) findViewById(R.id.editGo);
        mEditGo.setOnEditorActionListener(this);

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(i == EditorInfo.IME_ACTION_SEARCH) {
            if(!mEditSearch.getText().toString().equals("cat")) {
                Toast.makeText(getApplicationContext(), "Не буду ничего искать!", Toast.LENGTH_LONG).show();
            }
            return true;
        }
        if(i == EditorInfo.IME_ACTION_GO) {
            return true;
        }
        return false;
    }
}
