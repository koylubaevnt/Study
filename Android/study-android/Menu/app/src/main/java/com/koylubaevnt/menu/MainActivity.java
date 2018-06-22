package com.koylubaevnt.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        TextView textView = (TextView) findViewById(R.id.textView);

        switch (id) {
            case R.id.action_cat1:
                item.setChecked(!item.isChecked());
                textView.setText("Вы выбрали кота!");
                return true;
            case R.id.action_cat2:
                item.setChecked(!item.isChecked());
                textView.setText("Вы выбрали кошку!");
                return true;
            case R.id.action_cat3:
                item.setChecked(!item.isChecked());
                textView.setText("Вы выбрали котенка!");
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //Если требуется обновить меню во время работы программы, то использовать данный метод
        return super.onPrepareOptionsMenu(menu);
    }

    public void onSettingsMeuClick(MenuItem item) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Вы выбрали пункт Settings, лучше бы выбрали кота");
    }
}
