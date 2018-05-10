package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by grzegorzwilusz on 5/10/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param earthquakes objects to represent in the ListView.
     */
    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.earthquake_list_item, parent, false);
        }

        final Earthquake currentEarthquake = getItem(position);

        TextView earthQuakeMagnitudeTextView = convertView.findViewById(R.id.magnitude_text_view);
        TextView earthQuakeLocationTextView = convertView.findViewById(R.id.location_text_view);
        TextView earthQuakeDateTextView = convertView.findViewById(R.id.date_text_view);

        earthQuakeMagnitudeTextView.setText(currentEarthquake.getmMagnitude());
        earthQuakeLocationTextView.setText(currentEarthquake.getmLocation());
        earthQuakeDateTextView.setText(currentEarthquake.getmDate());

        return convertView;
    }
}
