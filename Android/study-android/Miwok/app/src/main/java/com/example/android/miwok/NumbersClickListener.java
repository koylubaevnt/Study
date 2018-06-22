package com.example.android.miwok;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by koylu on 22.09.2017.
 */

public class NumbersClickListener implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, NumbersActivity.class);
        context.startActivity(intent);
//        Toast.makeText(view.getContext(),
//                "Open the list of numbers", Toast.LENGTH_SHORT).show();
    }

}
