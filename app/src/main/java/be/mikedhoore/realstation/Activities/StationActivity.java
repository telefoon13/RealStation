package be.mikedhoore.realstation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import be.mikedhoore.realstation.Models.Station;
import be.mikedhoore.realstation.R;

public class StationActivity extends AppCompatActivity {

    private TextView stationName;
    private Station station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        //Get xml data
        stationName = findViewById(R.id.stationName);

        //Get the object out of the intent
        Intent intent = getIntent();
        station = (Station)intent.getSerializableExtra("station");

        //Set xml data
        stationName.setText(station.getName());
    }
}
