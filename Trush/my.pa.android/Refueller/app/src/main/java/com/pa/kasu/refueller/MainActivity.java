package com.pa.kasu.refueller;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.pa.kasu.refueller.adapter.DispencerAdapter;
import com.pa.kasu.refueller.dto.Dispenser;
import com.pa.kasu.refueller.loader.DispensersLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Pair<List<Dispenser>, Exception>> {

    public final static String KEY_SERVER_ADDRESS = "SERVER_ADDRESS";

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int DISPENSER_LOADER = 0;
    private static final int REQUEST_TIMEOUT = 1000;


    private List<Dispenser> mFuellingPoints = new ArrayList<>();
    private DispencerAdapter mDispenserAdapter;

    /** TextView который отображается, когда данных нет */
    private TextView mTextViewEmptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mFuellingPoints.add(new Dispenser(1L, "1", "1", DispenserState.FREE));
//        mFuellingPoints.add(new Dispenser(2L, "2", "2", DispenserState.FUELLING));
//        mFuellingPoints.add(new Dispenser(3L, "3", "3", DispenserState.CLOSE));
//        mFuellingPoints.add(new Dispenser(4L, "4", "4", DispenserState.PAYED));
//        mFuellingPoints.add(new Dispenser(5L, "5", "5", DispenserState.FUELLING));
//        mFuellingPoints.add(new Dispenser(6L, "6", "6", DispenserState.FREE));
//        mFuellingPoints.add(new Dispenser(7L, "7", "7", DispenserState.FUELLING));
//        mFuellingPoints.add(new Dispenser(8L, "8", "8", DispenserState.FREE));
//        mFuellingPoints.add(new Dispenser(9L, "9", "9", DispenserState.FREE));

        GridView gridView = (GridView) findViewById(R.id.gridView);
        mDispenserAdapter = new DispencerAdapter(MainActivity.this, mFuellingPoints);

        gridView.setAdapter(mDispenserAdapter);

        mTextViewEmptyList = (TextView) findViewById(R.id.tv_empty_grid);
        gridView.setEmptyView(mTextViewEmptyList);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Dispenser dispenser = (Dispenser) mDispenserAdapter.getItem(position);
                Intent intent = new Intent(MainActivity.this, EnterDozeActivity.class);
                intent.putExtra("dispenser", dispenser);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddDispensersActivity.class);
                startActivity(intent);
            }
        });

        // Проверяем соединение
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Инициализируем загрузчик
            getSupportLoaderManager().initLoader(DISPENSER_LOADER, null, this);
        } else {
            mTextViewEmptyList.setText(R.string.label_no_internet_connection);
        }

    }

    /**
     * Cоздает экземпляр и возвращает новый класс Loader для данного идентификатора
     *
     * @param id    Идентификатор загрузчика
     * @param args  Параметры
     * @return      Класс Loader
     */
    @Override
    public Loader<Pair<List<Dispenser>, Exception>> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader: " + id);
        if(id == DISPENSER_LOADER) {
            return new DispensersLoader(this, args);
        }
        return null;
    }

    /**
     * Вызывается, когда созданный ранее загрузчик завершил загрузку.
     * @param loader    Загрузчик
     * @param data      Данные
     */
    @Override
    public void onLoadFinished(final Loader<Pair<List<Dispenser>, Exception>> loader, Pair<List<Dispenser>, Exception> data) {
        Log.i(TAG, "onLoadFinished: data" + data);

        if(data.first != null) {
            mDispenserAdapter.setDispencers(data.first);
        } else {
            mTextViewEmptyList.setText(data.second.getLocalizedMessage());
            Toast.makeText(this, "ERROR: " + data.second + ", " + data.second.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        final int timeout = data.first != null ? REQUEST_TIMEOUT : 30000;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "Thread->Runnable: forceLoad. " + timeout);
                loader.forceLoad();
            }
        }).start();
    }

    /**
     * Вызывается, когда состояние созданного ранее загрузчика сбрасывается, в результате чего его данные теряются.
     * @param loader    Загрузчик
     */
    @Override
    public void onLoaderReset(Loader<Pair<List<Dispenser>, Exception>> loader) {
        Log.i(TAG, "onLoaderReset: " + loader);
    }
}
