package com.koylubaevnt.popupmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(viewClickListener);
        textView.setOnClickListener(viewClickListener);
        imageView.setOnClickListener(viewClickListener);
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showPopupMenu(view);
        }
    };

    private void showPopupMenu(View view) {

        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
        popupMenu.inflate(R.menu.popupmenu);
        // для версии Android 3.0 нужно использовать длинный вариант
        //popupMenu.getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu1:
                        Toast.makeText(getApplicationContext(), "Вы выбрали PopupMenu 1", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.menu2:
                        Toast.makeText(getApplicationContext(), "Вы выбрали PopupMenu 2", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.menu3:
                        Toast.makeText(getApplicationContext(), "Вы выбрали PopupMenu 3", Toast.LENGTH_LONG).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu popupMenu) {
                Toast.makeText(getApplicationContext(), "onDismiss", Toast.LENGTH_LONG).show();
            }
        });

        popupMenu.show();
    }
}
