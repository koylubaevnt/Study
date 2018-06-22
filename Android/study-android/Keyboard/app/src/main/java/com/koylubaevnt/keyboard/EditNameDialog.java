package com.koylubaevnt.keyboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by KoylubaevNT on 11.07.2017.
 */

public class EditNameDialog extends AppCompatDialogFragment {

    private EditText editText;

    public EditNameDialog() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        editText = (EditText) view.findViewById(R.id.txt_yourName);

        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        editText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return view;
    }
}
