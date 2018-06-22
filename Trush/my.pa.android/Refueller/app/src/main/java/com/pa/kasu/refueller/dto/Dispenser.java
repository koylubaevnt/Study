package com.pa.kasu.refueller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * ТРК - топливнораздаточная колонка
 */
public class Dispenser implements Serializable {

    /**
     * Идентификатор ТРК
     */
    @JsonProperty(value = "FpId")
    private Long id;
    /**
     * Номер ТРК
     */
    @JsonProperty(value = "FpNum")
    private String number;
    /**
     * Плановый объем
     */
    @JsonProperty(value = "VolumePlan")
    private BigDecimal volumePlan;
    /**
     * Фактический объем
     */
    @JsonProperty(value = "VolumeFact")
    private BigDecimal volumeFact;
    /**
     * Пистолеты на ТРК
     */
    @JsonProperty(value = "Nozzles")
    private List<Nozzle> nozzles;
    /**
     * Состояние ТРК
     */
    @JsonProperty(value = "MainState")
    private DispenserState state;
    /**
     * Подсостояние ТРК
     */
    @JsonProperty(value = "SubState")
    private DispenserSubState subState;
    /**
     * Идентификатор блокровки
     */
    @JsonProperty(value = "LockId")
    private Long lockId;
    /**
     * Признак "Моя ТРК"
     */
    private boolean isMy;

    public Dispenser() {}

    public Dispenser(Long id, String number, DispenserState state, List<Nozzle> nozzles) {
        this.id = id;
        this.number = number;
        this.state = state;
        this.nozzles = nozzles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getVolumePlan() {
        return volumePlan;
    }

    public void setVolumePlan(BigDecimal volumePlan) {
        this.volumePlan = volumePlan;
    }

    public BigDecimal getVolumeFact() {
        return volumeFact;
    }

    public void setVolumeFact(BigDecimal volumeFact) {
        if(this.volumeFact != volumeFact) {
            this.volumeFact = volumeFact;
        }
    }

    public List<Nozzle> getNozzles() {
        return nozzles;
    }

    public void setNozzles(List<Nozzle> nozzles) {
        this.nozzles = nozzles;
    }

    public DispenserState getState() {
        return state;
    }

    public void setState(DispenserState state) {
        //TODO: notifyChange
        if (this.state != state)
        {
            this.state = state;

//            OnPropertyChanged(NameProperty);
//            OnPropertyChanged(FpNameColorProperty);
//            OnPropertyChanged(FpNumProperty);
//            OnPropertyChanged(FpNumColorProperty);
//            OnPropertyChanged(FpNumTextColorProperty);
//            OnPropertyChanged(FpStateProperty);
//            OnPropertyChanged(FpStateColorProperty);
//            OnPropertyChanged(IsFuelStateProperty);
        }
    }

    public DispenserSubState getSubState() {
        return subState;
    }

    public void setSubState(DispenserSubState subState) {
        this.subState = subState;
    }
    
    public Long getLockId() {
        return lockId;
    }

    public void setLockId(Long lockId) {
        if (this.lockId != lockId)
        {
            this.lockId = lockId;
            //OnPropertyChanged(FpNumColorProperty);
        }
    }

    public boolean isBusy() {
        return this.lockId > 0;
    }

    public boolean isMy() {
        return isMy;
    }
    public void setIsMy(boolean isMy) {
        if (this.isMy != isMy) {
            isMy = isMy;
            //OnPropertyChanged(MyFpBorderColorProperty);
            //OnPropertyChanged(MyFpColorProperty);
        }
    }

    public boolean isSomeDataChange(Dispenser other) {
        if (other == null) return false;
        return
                !(this.getVolumePlan().equals(other.getVolumePlan())
                && this.getVolumeFact().equals(other.getVolumeFact())
                && this.getLockId().equals(other.getLockId())
                && this.getState().equals(other.getState())
                && this.getSubState().equals(other.getSubState()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dispenser that = (Dispenser) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Dispenser{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", volumePlan=" + volumePlan +
                ", volumeFact=" + volumeFact +
                ", nozzles=" + nozzles +
                ", state=" + state +
                ", subState=" + subState +
                ", lockId=" + lockId +
                '}';
    }
}
