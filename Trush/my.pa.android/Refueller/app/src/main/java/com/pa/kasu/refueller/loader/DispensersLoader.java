package com.pa.kasu.refueller.loader;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pa.kasu.refueller.MainActivity;
import com.pa.kasu.refueller.dto.Dispenser;
import com.pa.kasu.refueller.server.DLRBasicHttpBinding_IFpService;

import java.util.List;

/**
 * Загрузчик для загрузки данных по ТРК
 */
public class DispensersLoader extends AsyncTaskLoader<Pair<List<Dispenser>, Exception>> {

    private static final String TAG = Dispenser.class.getSimpleName();

    private String serviceAddress = "http://172.30.78.47:8087/";

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        configMapper();
    }
    public DispensersLoader(Context context, Bundle args) {
        super(context);
        if(args != null) {
            this.serviceAddress = args.getString(MainActivity.KEY_SERVER_ADDRESS);
        }
        Log.i(TAG, "DispensersLoader: args=" + args);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        //super.onStartLoading();
    }

    /**
     * Метод выполняющийся в фоновом потоке
     * @return Список ТРК
     */
    @Override
    public Pair<List<Dispenser>, Exception> loadInBackground() {
        Log.i(TAG, "loadInBackground: START");
        DLRBasicHttpBinding_IFpService service = new DLRBasicHttpBinding_IFpService(null, serviceAddress, 10000);

        Pair<List<Dispenser>, Exception> result = null;
        try {
            String data = service.GetFpsState();
            Log.i(TAG, data);
            List<Dispenser> dispensers = mapper.readValue(data, mapper.getTypeFactory().constructCollectionType(List.class, Dispenser.class));
            result = new Pair<>(dispensers, null);
        } catch (Exception e) {
            e.printStackTrace();
            result = new Pair<>(null, e);
        }
        return result;
    }

    @Override
    public void deliverResult(Pair<List<Dispenser>, Exception> data) {
        super.deliverResult(data);
    }

    private static ObjectMapper configMapper() {

        mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY)
                .withGetterVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY)
                .withSetterVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY)
                .withCreatorVisibility(JsonAutoDetect.Visibility.PUBLIC_ONLY));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        return mapper;
    }
}
