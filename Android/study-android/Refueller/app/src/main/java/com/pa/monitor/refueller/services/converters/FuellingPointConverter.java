package com.pa.monitor.refueller.services.converters;

import android.util.JsonReader;

import com.pa.monitor.refueller.models.FuelingPointStatus;
import com.pa.monitor.refueller.models.FuelingPointSubState;
import com.pa.monitor.refueller.models.FuellingPoint;
import com.pa.monitor.refueller.models.Good;
import com.pa.monitor.refueller.models.Nozzle;

import org.json.JSONArray;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by KoylubaevNT on 19.09.2017.
 */

public class FuellingPointConverter {



    public static List<FuellingPoint> jsonToFuelingPoints(String json) throws IOException {
        JsonReader reader = new JsonReader(new StringReader(json));
        try {
            return readFuellingPointsArray(reader);
        } finally {
            reader.close();
        }
    }

    public static String FuellingPointsToJson(List<FuellingPoint> fp) {
        return "{[]}";
    }

    private static List<FuellingPoint> readFuellingPointsArray(JsonReader reader) throws IOException {
        List<FuellingPoint> fuellingPoints = new ArrayList<>();

        reader.beginArray();
        while(reader.hasNext()) {
            fuellingPoints.add(readFuellingPoint(reader));
        }
        reader.endArray();
        return fuellingPoints;
    }

    private static FuellingPoint readFuellingPoint(JsonReader reader) throws IOException {
        Long id = null;
        String name = null;
        String number = null;
        BigDecimal volume = null;
        BigDecimal money = null;
        Long lockId = null;
        FuelingPointStatus status = null;
        FuelingPointSubState subState = null;
        Nozzle nozzle = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String parameterName = reader.nextName();
            if (parameterName.equals("fpId")) {
                id = reader.nextLong();
            } else if (parameterName.equals("fpNum")) {
                String tmp = reader.nextString();
                number = tmp;
                name = "Колонка №" + tmp;
            } else if (parameterName.equals("nozzleId")) {
                nozzle = new Nozzle(reader.nextLong(), null, null);
            } else if (parameterName.equals("mainState")) {
                status = readFuellingStatus(reader);
            } else if (parameterName.equals("subState")) {
                subState = readSubstate(reader);
            } else if (parameterName.equals("volume")) {
                volume = new BigDecimal(reader.nextDouble());
            } else if (parameterName.equals("money")) {
                money = new BigDecimal(reader.nextDouble());
            } else if (parameterName.equals("lockId")) {
                lockId = reader.nextLong();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new FuellingPoint(id, name, number, status, new CopyOnWriteArrayList<Nozzle>(new Nozzle[] { nozzle }), volume, money, lockId, subState);
    }

    private static FuelingPointSubState readSubstate(JsonReader reader) throws IOException {
        reader.beginObject();
        FuelingPointSubState subState = new FuelingPointSubState();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("isUnavailable")) {
                subState.setUnavailable(reader.nextBoolean());
            } else if (name.equals("isInoperative")) {
                subState.setInoperative(reader.nextBoolean());
            } else if (name.equals("isError")) {
                subState.setError(reader.nextBoolean());
            }
        }
        reader.endObject();
        return subState;

    }

    private static FuelingPointStatus readFuellingStatus(JsonReader reader) throws IOException {
        return FuelingPointStatus.valueOf(reader.nextLong());
    }
}
