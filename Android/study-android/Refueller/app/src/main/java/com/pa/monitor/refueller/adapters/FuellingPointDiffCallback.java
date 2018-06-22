package com.pa.monitor.refueller.adapters;

import android.support.v7.util.DiffUtil;

import com.pa.monitor.refueller.models.FuellingPoint;

import java.util.List;

/**
 * Created by KoylubaevNT on 19.09.2017.
 */

public class FuellingPointDiffCallback extends DiffUtil.Callback {

    private final List<FuellingPoint> oldFuellingPoints;
    private final List<FuellingPoint> newFuellingPoints;

    public FuellingPointDiffCallback(List<FuellingPoint> oldFuellingPoints, List<FuellingPoint> newFuellingPoints) {
        this.oldFuellingPoints = oldFuellingPoints;
        this.newFuellingPoints = newFuellingPoints;
    }

    @Override
    public int getOldListSize() {
        return oldFuellingPoints.size();// + 1;
    }

    @Override
    public int getNewListSize() {
        return newFuellingPoints.size();// + 1;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        System.out.println("Diff: " + oldItemPosition + ", " + newItemPosition + ", " + oldFuellingPoints.size() + ", " + newFuellingPoints.size());
//        if(oldItemPosition == oldFuellingPoints.size() && newItemPosition == newFuellingPoints.size()
//                && oldItemPosition == newItemPosition) {
//            System.out.println("Diff: true");
//            return true;
//        }
        System.out.println("Diff: same=" + (oldFuellingPoints.get(oldItemPosition).getId().equals(newFuellingPoints.get(newItemPosition).getId())));
        return oldFuellingPoints.get(oldItemPosition).getId().equals(newFuellingPoints.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//        if(oldItemPosition == oldFuellingPoints.size() && newItemPosition == newFuellingPoints.size()
//                && oldItemPosition == newItemPosition) {
//            System.out.println("Diff: content true");
//            return true;
//        }
//        if(oldItemPosition < oldFuellingPoints.size() || newItemPosition < newFuellingPoints.size() ) {
//            System.out.println("Diff: content false");
//            return false;
//        }
        FuellingPoint oldFP = oldFuellingPoints.get(oldItemPosition);
        FuellingPoint newFP = newFuellingPoints.get(newItemPosition);

        System.out.println("Diff: content " + oldItemPosition + ", " + newItemPosition + ",same=" + (oldFP.getStatus().equals(newFP.getStatus()) && oldFP.getSubState().equals(newFP.getSubState()) &&
                oldFP.getLockId().equals(newFP.getLockId()) && oldFP.getMoney().equals(newFP.getMoney()) &&
                oldFP.getVolume().equals(newFP.getVolume())) + ", status:" +

        oldFP.getStatus().equals(newFP.getStatus()) + ", subState:" + oldFP.getSubState().equals(newFP.getSubState()) + ", lockId:" +
                oldFP.getLockId().equals(newFP.getLockId()) + ", money:" + oldFP.getMoney().equals(newFP.getMoney()) + ", volume:" +
                oldFP.getVolume().equals(newFP.getVolume())
        );

        return oldFP.getStatus().equals(newFP.getStatus()) && oldFP.getSubState().equals(newFP.getSubState()) &&
                oldFP.getLockId().equals(newFP.getLockId()) && oldFP.getMoney().equals(newFP.getMoney()) &&
                oldFP.getVolume().equals(newFP.getVolume());
    }
}
