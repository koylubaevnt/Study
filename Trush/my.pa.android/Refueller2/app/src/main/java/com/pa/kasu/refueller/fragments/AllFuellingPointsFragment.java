package com.pa.kasu.refueller.fragments;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pa.kasu.refueller.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllFuellingPointsFragment extends Fragment {


    public AllFuellingPointsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fuelling_point_list, container, false);


        return rootView;
    }

}
