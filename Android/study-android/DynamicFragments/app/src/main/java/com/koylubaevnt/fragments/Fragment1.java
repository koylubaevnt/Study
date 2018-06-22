package com.koylubaevnt.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by KoylubaevNT on 13.07.2017.
 */

public class Fragment1 extends Fragment implements View.OnClickListener{

    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonIndex);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        Button button1 = (Button) view.findViewById(R.id.button1);
        Button button2 = (Button) view.findViewById(R.id.button2);
        Button button3 = (Button) view.findViewById(R.id.button3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        int butonIndex = translateIdToIndex(view.getId());

        OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
        listener.onButtonSelected(butonIndex);

        Toast.makeText(getActivity(), "Вы нажали кнопку: " + String.valueOf(butonIndex), Toast.LENGTH_SHORT).show();
    }

    int translateIdToIndex(int id) {
        int index = -1;
        switch (id) {
            case R.id.button1:
                index = 1;
                break;
            case R.id.button2:
                index = 2;
                break;
            case R.id.button3:
                index = 3;
                break;
        }
        return index;
    }
}
