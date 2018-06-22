package com.pa.monitor.refueller.services;

import com.pa.monitor.refueller.events.EventUpdateFuellingPoints;
import com.pa.monitor.refueller.models.FuellingPoint;
import com.pa.monitor.refueller.services.converters.FuellingPointConverter;
import com.pa.monitor.refueller.services.wcf.JNCBasicHttpBinding_IFpService;
import com.pa.monitor.refueller.services.wcf.JNCIServiceEvents;
import com.pa.monitor.refueller.services.wcf.JNCOperationResult;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class InfoService {

    private final Timer timer = new Timer();
    private final JNCBasicHttpBinding_IFpService service;
    private final JNCIServiceEvents event;

    public InfoService() {
        System.out.println("InfoService:  created");
        event = new JNCIServiceEvents() {
            @Override
            public void Starting() {
                System.out.println("InfoService: JNCIServiceEvents.start");
            }

            @Override
            public void Completed(JNCOperationResult result) {
                System.out.println("InfoService: JNCIServiceEvents.Completed=> result=" + result);
                if (result.Result != null && !result.Result.toString().isEmpty())
                    try {
                        List<FuellingPoint> fps = FuellingPointConverter.jsonToFuelingPoints(result.Result.toString());
                        EventBus.getDefault().post(new EventUpdateFuellingPoints(fps));
                        System.out.println("InfoService: JNCIServiceEvents.Completed=> fps=" + fps);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }
        };
        service = new JNCBasicHttpBinding_IFpService(event, "http://172.30.78.47:8087");
        service.GetFpsStateAsync();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("InfoService: timer.run");
                try {
                    service.GetFpsStateAsync();
                    //System.out.println("InfoService: state=" + service.GetFpsState());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10* 1000, 10 * 1000);
    }

//    private void retriveInfo() {
//        try {
//            System.out.println("InfoService: " + service);
//            service.GetFpsStateAsync().execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
