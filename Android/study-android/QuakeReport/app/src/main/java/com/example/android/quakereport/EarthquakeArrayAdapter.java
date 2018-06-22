package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.R.attr.format;
import static com.example.android.quakereport.R.id.date;

/**
 * Created by KoylubaevNT on 25.09.2017.
 */

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeArrayAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null) {
            listView = LayoutInflater.from(getContext())
                    .inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake earthquake = getItem(position);

        Date dateObject = new Date(earthquake.getTimeInMilliseconds());

        TextView magnitudeTextView = (TextView) listView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(formatMagnitude(earthquake.getMagnitude()));

        String place = earthquake.getPlace();
        String offset;
        String location;
        if(place.contains(LOCATION_SEPARATOR)) {
            String[] arr = place.split(LOCATION_SEPARATOR);
            offset = arr[0] + LOCATION_SEPARATOR;
            location = arr[1];
        } else {
            offset = getContext().getString(R.string.near_the);
            location = place;
        }
        TextView offsetTextView = (TextView) listView.findViewById(R.id.offset);
        offsetTextView.setText(offset);

        TextView placeTextView = (TextView) listView.findViewById(R.id.place);
        placeTextView.setText(location);

        TextView dateTextView = (TextView) listView.findViewById(date);
        dateTextView.setText(formatDate(dateObject));
        TextView timeTextView = (TextView) listView.findViewById(R.id.time);
        timeTextView.setText(formatTime(dateObject));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);

        return listView;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }

    private String formatTime(Date date) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(date);
    }

    private String formatMagnitude(Double mag) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(mag);
    }

    private int getMagnitudeColor(double mag) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(mag);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
