package com.koylubaevnt.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast toast = Toast.makeText(getApplicationContext(),
                "Пора покормить кота!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClick(View view) {
        Toast toast;
        switch (view.getId()) {
            case R.id.buttonToast:
                toast = Toast.makeText(getApplicationContext(), "Пора покормить кота!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            case R.id.buttonToastContent:
                toast = Toast.makeText(getApplicationContext(), "Пора покормить кота!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);

                LinearLayout toastContainer = (LinearLayout) toast.getView();
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setImageResource(R.drawable.toast_img);
                toastContainer.addView(imageView, 0);

                toast.show();
                break;
            case R.id.buttonToastContentLayout:
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_layout, (ViewGroup) findViewById(R.id.toast_layout));
                toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
                break;
            default:
                break;
        }

    }
}
