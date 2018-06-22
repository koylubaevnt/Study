package com.pa.monitor.refueller.models;

/**
 * Created by KoylubaevNT on 19.09.2017.
 */

public class FuelingPointSubState {

    private boolean isUnavailable;
    private boolean isInoperative;
    private boolean isError;

    public FuelingPointSubState() {}

    public FuelingPointSubState(boolean isUnavailable, boolean isInoperative, boolean isError) {
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

        FuelingPointSubState subState = (FuelingPointSubState) o;

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
        return "FuelingPointSubState{" +
                "isUnavailable=" + isUnavailable +
                ", isInoperative=" + isInoperative +
                ", isError=" + isError +
                '}';
    }
}
