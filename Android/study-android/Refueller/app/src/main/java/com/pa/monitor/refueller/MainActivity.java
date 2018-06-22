package com.pa.monitor.refueller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.pa.monitor.refueller.adapters.RecyclerViewAdapter;
import com.pa.monitor.refueller.events.EventUpdateFuellingPoints;
import com.pa.monitor.refueller.models.FuellingPoint;
import com.pa.monitor.refueller.models.GasStation;
import com.pa.monitor.refueller.models.SingletonData;
import com.pa.monitor.refueller.services.InfoService;
/*
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
*/

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT = R.layout.fuelling_points_layout;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        EventBus.getDefault().register(this);

        final List<FuellingPoint> data = new ArrayList<>();

        Setting.gasStation = new GasStation(1L, "АЗС №315", "Пермь, Циолковского, 5", "24 часа", 50, 50);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvFuellingPoint);
        adapter = new RecyclerViewAdapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Intent serviceIntent = new Intent(MainActivity.this , MyDataService.class);
//        startService(serviceIntent);

    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
       // EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        //EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventFromService(EventUpdateFuellingPoints event){
        adapter.updateFuellingPoints(event.getFuellingPoints());
        //Toast.makeText(this, "You receive new config: " + event.getFuellingPoints(), Toast.LENGTH_LONG).show();
    }

}
