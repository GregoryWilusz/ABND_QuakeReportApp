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
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by grzegorzwilusz on 5/10/18.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructor
     *
     * @param context     The current context.
     * @param earthquakes objects to represent in the ListView.
     */
    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.earthquake_list_item, parent, false);
        }

        final Earthquake currentEarthquake = getItem(position);

        TextView earthQuakeMagnitudeTextView = convertView.findViewById(R.id.magnitude_text_view);
        TextView earthQuakeLocationOffsetTextView = convertView.findViewById(R.id.location_offset_text_view);
        TextView earthQuakePrimaryLocationTextView = convertView.findViewById(R.id.primary_location_text_view);
        TextView earthQuakeDateTextView = convertView.findViewById(R.id.date_text_view);
        TextView earthQuakeTimeTextView = convertView.findViewById(R.id.time_text_view);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) earthQuakeMagnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String formattedMagnitude = formatMagnitude(currentEarthquake.getMagnitude());
        earthQuakeMagnitudeTextView.setText(formattedMagnitude);

        String originalLocation = currentEarthquake.getLocation();
        String locationOffset;
        String primaryLocation;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        earthQuakeLocationOffsetTextView.setText(locationOffset);
        earthQuakePrimaryLocationTextView.setText(primaryLocation);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        String formattedDate = formatDate(dateObject);
        earthQuakeDateTextView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        earthQuakeTimeTextView.setText(formattedTime);

        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
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

    private String formatMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        return timeFormat.format(dateObject);
    }
}
