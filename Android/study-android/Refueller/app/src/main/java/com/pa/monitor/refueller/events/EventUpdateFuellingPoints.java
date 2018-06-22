package com.pa.monitor.refueller.events;

import com.pa.monitor.refueller.models.FuellingPoint;

import java.util.List;

/**
 * Created by KoylubaevNT on 17.09.2017.
 */

public class EventUpdateFuellingPoints {
    private List<FuellingPoint> fuellingPoints;

    public EventUpdateFuellingPoints(List<FuellingPoint> fuellingPoints) {
        this.fuellingPoints = fuellingPoints;
    }
    public List<FuellingPoint> getFuellingPoints() {
        return fuellingPoints;
    }
}
