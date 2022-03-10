package com.example.didyoufeelit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Displays the perceived strength of a single earthquake event based on responses from people who
 * felt the earthquake.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * URL for earthquake data from the USGS dataset
     */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Perform the HTTP request for earthquake data and process the response.

        EarthquakeAsync task = new EarthquakeAsync();
        task.execute(USGS_REQUEST_URL);
        // Update the information displayed to the user.
        //updateUi(earthquake);
    }

    /**
     * Update the UI with the given earthquake information.
     */
    private void updateUi(Event earthquake) {
        TextView titleTextView = findViewById(R.id.title);
        titleTextView.setText(earthquake.title);

        TextView tsunamiTextView = findViewById(R.id.number_of_people);
        tsunamiTextView.setText(earthquake.numOfPeople + getString(R.string.num_people_felt_it));

        TextView magnitudeTextView = findViewById(R.id.perceived_magnitude);
        magnitudeTextView.setText(earthquake.perceivedStrength);
    }

    public class EarthquakeAsync extends AsyncTask<String, Void, Event> {

        @Override
        protected Event doInBackground(String... url) {
            return Utils.fetchEarthquakeData(url[0]);
        }

        @Override
        protected void onPostExecute(Event result) {
            updateUi(result);
        }
    }

}