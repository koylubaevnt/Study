package com.pa.monitor.refueller.models;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by KoylubaevNT on 19.09.2017.
 */

public class SingletonData {

    private static final SingletonData ourInstance = new SingletonData();

    public static SingletonData getInstance() {
        return ourInstance;
    }

    private SingletonData() {
    }

    private List<FuellingPoint> fuellingPoints = new CopyOnWriteArrayList<>();

    public List<FuellingPoint> getFuellingPoints() {
        return fuellingPoints;
    }

    public void setFuellingPoints(List<FuellingPoint> fuellingPoints) {
        this.fuellingPoints.clear();
        this.fuellingPoints.addAll(fuellingPoints);
    }
}
