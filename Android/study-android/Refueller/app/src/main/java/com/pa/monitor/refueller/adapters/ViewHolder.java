package com.pa.monitor.refueller.adapters;

import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pa.monitor.refueller.R;

/**
 * Created by koylu on 15.09.2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    FrameLayout flFuellingPointNumber;
    TextView tvFuellingPointNumber;
    TextView tvFuellingPointName;
    TextView tvFuellingPointStatus;
    ProgressBar progressBar;

    TextView tvAzsAddress;
    TextView tvAzsWork;

    public ViewHolder(View itemView) {
        super(itemView);
        flFuellingPointNumber = itemView.findViewById(R.id.flFuellingPointNumber);
        tvFuellingPointNumber = itemView.findViewById(R.id.tvFuellingPointNumber);
        tvFuellingPointName = itemView.findViewById(R.id.tvFuellingPointName);
        tvFuellingPointStatus = itemView.findViewById(R.id.tvFuellingPointStatus);
        progressBar = itemView.findViewById(R.id.progressBar);

        tvAzsAddress = itemView.findViewById(R.id.tvAzsAddress);
        tvAzsWork = itemView.findViewById(R.id.tvAzsWork);
    }

}
