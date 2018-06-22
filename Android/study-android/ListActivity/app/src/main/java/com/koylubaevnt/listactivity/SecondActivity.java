package com.koylubaevnt.listactivity;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends ListActivity {


    private String[] mMonthArray = { "Январь", "Февраль", "Котомарт", "Апрель", "Май",
            "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь",
            "Декабрь" };

    private String[] mDayOfWeekArray = new String[] { "Понедельник", "Вторник", "Среда",
            "Четверг", "Котопятница", "Субкота", "Воскресенье" };

    private ArrayAdapter<String> mMonthAdapter, mDayOfWeekAdapter;
    private String mMonth, mDayOfWeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMonthAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mMonthArray);
        mDayOfWeekAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mDayOfWeekArray);

        setListAdapter(mMonthAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if(getListAdapter() == mMonthAdapter) {
            mMonth = (String) l.getItemAtPosition(position);
            setListAdapter(mDayOfWeekAdapter);
            mDayOfWeekAdapter.notifyDataSetChanged();
        } else {
            mDayOfWeek = (String) l.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), mMonth + ":" + mDayOfWeek, Toast.LENGTH_LONG).show();
            setListAdapter(mMonthAdapter);
            mMonthAdapter.notifyDataSetChanged();
        }
    }
}
