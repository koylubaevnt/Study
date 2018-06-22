package com.pa.monitor.refueller.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pa.monitor.refueller.R;
import com.pa.monitor.refueller.Setting;
import com.pa.monitor.refueller.models.FuelingPointStatus;
import com.pa.monitor.refueller.models.FuellingPoint;

import java.util.Collections;
import java.util.List;

/**
 * Created by koylu on 15.09.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final int ITEM_LAYOUT = R.layout.fuelling_point_item;
    private static final int AZS_INFO_LAYOUT = R.layout.fuelling_point_azs_item;

//    private int count = 0;

    List<FuellingPoint> list = Collections.emptyList();
    Context context;

    public RecyclerViewAdapter(List<FuellingPoint> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder.getItemViewType() == AZS_INFO_LAYOUT) {
            if(Setting.gasStation != null) {
                holder.tvAzsAddress.setText(Setting.gasStation.getAddress());
                holder.tvAzsWork.setText(Setting.gasStation.getWorkTime());
            } else {
                holder.tvAzsAddress.setText("Неизвестно");
                holder.tvAzsWork.setText("Неизвестно");
            }

        } else {
            //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
            FuellingPoint fuellingPoint = list.get(position);
            holder.tvFuellingPointNumber.setText(fuellingPoint.getNumber());
            holder.tvFuellingPointName.setText(fuellingPoint.getName());
            holder.tvFuellingPointStatus.setText(fuellingPoint.getStatus().getName());
            Context context = holder.itemView.getContext().getApplicationContext();
            int color;
            int textNameColor;
            int textStatusColor;
            if (fuellingPoint.getStatus().equals(FuelingPointStatus.WAITING_PAYMENT)) {
                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.circle);

                int textNumberColor;
                if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                    color = context.getResources().getColor(R.color.colorPrimary, context.getTheme());
                    textNumberColor = context.getResources().getColor(R.color.colorWhite, context.getTheme());
                    textStatusColor = context.getResources().getColor(R.color.colorPrimary, context.getTheme());
                    drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                    holder.flFuellingPointNumber.setBackground(drawable);
                } else {
                    color = context.getResources().getColor(R.color.colorPrimary);
                    textNumberColor = context.getResources().getColor(R.color.colorWhite);
                    textStatusColor = context.getResources().getColor(R.color.colorPrimary);
                    drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
                    holder.flFuellingPointNumber.setBackgroundDrawable(drawable);
                }
                holder.tvFuellingPointNumber.setTextColor(textNumberColor);
                holder.tvFuellingPointStatus.setTextColor(textStatusColor);
                holder.progressBar.setProgress(100);
            } else if (fuellingPoint.getStatus().equals(FuelingPointStatus.FUELLING)) {
                textNameColor = context.getResources().getColor(R.color.colorTextSecondary);
                holder.tvFuellingPointNumber.setTextColor(textNameColor);
                holder.tvFuellingPointName.setTextColor(textNameColor);
                holder.tvFuellingPointStatus.setTextColor(textNameColor);
                holder.progressBar.setIndeterminate(true);
                holder.progressBar.setProgress(50);

            } else if (fuellingPoint.getStatus().equals(FuelingPointStatus.CLOSE)) {
                textNameColor = context.getResources().getColor(R.color.colorTextDisabled);
                holder.tvFuellingPointNumber.setTextColor(textNameColor);
                holder.tvFuellingPointName.setTextColor(textNameColor);
                holder.tvFuellingPointStatus.setTextColor(textNameColor);
                holder.progressBar.setBackgroundColor(textNameColor);
            } else {
                textNameColor = context.getResources().getColor(R.color.colorTextPrimary);
                holder.tvFuellingPointNumber.setTextColor(textNameColor);
                holder.tvFuellingPointName.setTextColor(textNameColor);
                holder.tvFuellingPointStatus.setTextColor(textNameColor);
            }
        }
        //animate(holder);

    }

    @Override
    public int getItemCount() {

        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position >= list.size()) {
            return AZS_INFO_LAYOUT;
        } else {
            return ITEM_LAYOUT;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void updateFuellingPoints(List<FuellingPoint> data) {
        final FuellingPointDiffCallback callback = new FuellingPointDiffCallback(this.list, data);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);

        this.list.clear();
        this.list.addAll(data);
        diffResult.dispatchUpdatesTo(this);
    }

}
