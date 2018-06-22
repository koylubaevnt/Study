package com.pa.kasu.refueller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.pa.kasu.refueller.dto.Dispenser;

public class EnterDozeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_doze);

        Intent intent = getIntent();
        if(intent.hasExtra("dispenser")) {
            Dispenser dispenser = (Dispenser) intent.getSerializableExtra("dispenser");
            Toast.makeText(this, "You selected: " + dispenser.toString(), Toast.LENGTH_LONG).show();

            setTitle(getString(R.string.dispenser_num) + " " + dispenser.getNumber());

            int countNozzle = dispenser.getNozzles().size();

        } else {
            Toast.makeText(this, "Intent doesn't has extra object dispencer", Toast.LENGTH_LONG).show();
        }
        //TODO: На самом деле тут все должно генерировтаься автоматически!!! т.к, мы не знаем какие топлива на колонке заранее...
    }
}
