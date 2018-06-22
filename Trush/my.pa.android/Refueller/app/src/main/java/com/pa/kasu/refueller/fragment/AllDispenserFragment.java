package com.pa.kasu.refueller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pa.kasu.refueller.dto.Dispenser;

import java.util.List;

public class AllDispenserFragment extends Fragment {

    List<Dispenser> mAllDispencers;

    public AllDispenserFragment(List<Dispenser> dispencers) {
        mAllDispencers = dispencers;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
