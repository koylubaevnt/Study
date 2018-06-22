package com.koylubaevnt.listactivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends ListActivity implements AdapterView.OnItemLongClickListener {

    private final String[] catNamesArray = new String[]
            {"Рыжик", "Барсик", "Мурзик",
            "Мурка", "Васька", "Томасина", "Бобик", "Кристина", "Пушок",
            "Дымка", "Кузя", "Китти", "Барбос", "Масяня", "Симба" };

    private final ArrayList<String> mCatNamesList = new ArrayList<>(Arrays.asList(catNamesArray));
    private ArrayAdapter<String> mAdapter;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //mAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, mCatNamesList);
        mAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, mCatNamesList);

        setListAdapter(mAdapter);

        getListView().setOnItemLongClickListener(this);

//        //alternative onListItemClick
//        ListView listView = findViewById(android.R.id.list);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), "!Вы выбрали " + (i + 1) + " элемент: " + ((TextView) view).getText(), Toast.LENGTH_LONG).show();
//            }
//        });

        mEditText = findViewById(R.id.editText);
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if(i == KeyEvent.KEYCODE_ENTER) {
                        mCatNamesList.add(0, mEditText.getText().toString());
                        mAdapter.notifyDataSetChanged();
                        mEditText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getApplicationContext(), "Вы выбрали " + (position + 1) + " элемент: " + l.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selectItem = adapterView.getItemAtPosition(i).toString();

        mAdapter.remove(selectItem);
        mAdapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(), selectItem + " удален.", Toast.LENGTH_SHORT).show();

        return true;
    }
}
