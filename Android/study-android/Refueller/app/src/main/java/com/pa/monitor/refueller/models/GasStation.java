package com.pa.monitor.refueller.models;

import java.io.Serializable;

/**
 * Created by koylu on 15.09.2017.
 */
public class GasStation implements Serializable {

    private Long id;
    private String name;
    private String address;
    private String workTime;
    private double latitude;
    private double longitude;

    public GasStation() {}

    public GasStation(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public GasStation(Long id, String name, String address, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GasStation(Long id, String name, String address, String workTime, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.workTime = workTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    @Override
    public String toString() {
        return "GasStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", workTime=" + workTime +
                '}';
    }
}
