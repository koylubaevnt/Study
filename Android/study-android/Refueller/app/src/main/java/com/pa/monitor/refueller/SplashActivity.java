package com.pa.monitor.refueller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pa.monitor.refueller.models.FuelingPointStatus;
import com.pa.monitor.refueller.models.FuellingPoint;
import com.pa.monitor.refueller.models.SingletonData;
import com.pa.monitor.refueller.services.InfoService;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

//    private final ArrayList<FuellingPoint> data = fillWithData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: тут нужно если нет настроек, вывести окно настроек
        //TODO: если настройки есть, то подключиться к сервису и получить данные
        new InfoService();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


//    public ArrayList<FuellingPoint> fillWithData() {
//
//        ArrayList<FuellingPoint> data = new ArrayList<>();
//
//        data.add(new FuellingPoint(1L, "Колонка №1", "1", FuelingPointStatus.FUELLING));
//        data.add(new FuellingPoint(2L, "Колонка №2", "2", FuelingPointStatus.WAITING_PAYMENT));
//        data.add(new FuellingPoint(3L, "Колонка №3", "3", FuelingPointStatus.PAYED));
//        data.add(new FuellingPoint(4L, "Колонка №4", "4", FuelingPointStatus.FREE));
//        data.add(new FuellingPoint(5L, "Колонка №5", "5", FuelingPointStatus.WAITING_PAYMENT));
//        data.add(new FuellingPoint(6L, "Колонка №6", "6", FuelingPointStatus.PAYED));
//        data.add(new FuellingPoint(7L, "Колонка №7", "7", FuelingPointStatus.FUELLING));
//        data.add(new FuellingPoint(8L, "Колонка №8", "8", FuelingPointStatus.FREE));
//        data.add(new FuellingPoint(9L, "Колонка №9", "9", FuelingPointStatus.CLOSE));
//
//        return data;
//    }


}
