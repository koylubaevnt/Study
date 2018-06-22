package com.pa.monitor.refueller.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by koylu on 15.09.2017.
 */
public class FuellingPoint  implements Serializable {

    private Long id;
    private String name;
    private String number;
    private FuelingPointStatus status;
    private List<Nozzle> nozzles;
    private BigDecimal volume;
    private BigDecimal money;
    private Long lockId;
    private FuelingPointSubState subState;

    public FuellingPoint() {}

    public FuellingPoint(Long id, String name, String number, FuelingPointStatus status) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.status = status;
    }

    public FuellingPoint(Long id, String name, String number, FuelingPointStatus status, List<Nozzle> nozzles) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.status = status;
        this.nozzles = nozzles;
    }

    public FuellingPoint(Long id, String name, String number, FuelingPointStatus status, List<Nozzle> nozzles, BigDecimal volume, BigDecimal money, Long lockId, FuelingPointSubState subState) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.status = status;
        this.nozzles = nozzles;
        this.volume = volume;
        this.money = money;
        this.lockId = lockId;
        this.subState = subState;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public FuelingPointStatus getStatus() {
        return status;
    }

    public void setStatus(FuelingPointStatus status) {
        this.status = status;
    }

    public void setStatus(Long statusId) {
        this.status = FuelingPointStatus.valueOf(id);
    }

    public List<Nozzle> getNozzles() {
        return nozzles;
    }

    public void setNozzles(List<Nozzle> nozzles) {
        this.nozzles = nozzles;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getLockId() {
        return lockId;
    }

    public void setLockId(Long lockId) {
        this.lockId = lockId;
    }

    public FuelingPointSubState getSubState() {
        return subState;
    }

    public void setSubState(FuelingPointSubState subState) {
        this.subState = subState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FuellingPoint that = (FuellingPoint) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "FuellingPoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", status=" + status +
                ", nozzles=" + nozzles +
                ", volume=" + volume +
                ", money=" + money +
                ", lockId=" + lockId +
                ", subState=" + subState +
                '}';
    }
}
