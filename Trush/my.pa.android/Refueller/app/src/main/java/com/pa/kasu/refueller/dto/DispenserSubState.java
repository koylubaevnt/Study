package com.pa.kasu.refueller.dto;

import java.io.Serializable;

/**
 * Подсостояние ТРК
 */
public class DispenserSubState implements Serializable {

    private boolean isUnavailable;
    private boolean isInoperative;
    private boolean isError;

    public DispenserSubState() {}

    public DispenserSubState(boolean isUnavailable, boolean isInoperative, boolean isError) {
        this.isUnavailable = isUnavailable;
        this.isInoperative = isInoperative;
        this.isError = isError;
    }

    public boolean isUnavailable() {
        return isUnavailable;
    }

    public void setUnavailable(boolean unavailable) {
        isUnavailable = unavailable;
    }

    public boolean isInoperative() {
        return isInoperative;
    }

    public void setInoperative(boolean inoperative) {
        isInoperative = inoperative;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DispenserSubState subState = (DispenserSubState) o;

        if (isUnavailable != subState.isUnavailable) return false;
        if (isInoperative != subState.isInoperative) return false;
        return isError == subState.isError;

    }

    @Override
    public int hashCode() {
        int result = (isUnavailable ? 1 : 0);
        result = 31 * result + (isInoperative ? 1 : 0);
        result = 31 * result + (isError ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DispenserSubState{" +
                "isUnavailable=" + isUnavailable +
                ", isInoperative=" + isInoperative +
                ", isError=" + isError +
                '}';
    }
}
