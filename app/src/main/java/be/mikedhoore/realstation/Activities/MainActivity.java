package be.mikedhoore.realstation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import be.mikedhoore.realstation.Helpers.DataBaseHelper;
import be.mikedhoore.realstation.Helpers.iRail.be.iRailStations;
import be.mikedhoore.realstation.Models.Station;
import be.mikedhoore.realstation.R;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    public static DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
        db = new DataBaseHelper(this);
        //Clear the DB first
        db.deleteAll();
        //Get all the stations from iRail
        iRailStations process = new iRailStations();
        process.execute();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng ehb = new LatLng(50.842395, 4.322808);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ehb));
        mMap.setMinZoomPreference(7);

        //Used source : https://stackoverflow.com/questions/47643568/google-marker-open-activity-when-marker-is-clicked
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Station station = (Station) marker.getTag();
                Intent intent = new Intent(MainActivity.this, StationActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
                return false;
            }
        });
    }

    //Menu used resource : https://www.youtube.com/watch?v=h47CqbmhdAs
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.navigation_home) {
            Toast.makeText(this, this.getResources().getString(R.string.alreadyHere), Toast.LENGTH_SHORT).show();
        }
        if (id == R.id.navigation_search) {
            startActivity(new Intent(MainActivity.this, SearchActivity.class));
        }
        if (id == R.id.navigation_plan) {
            startActivity(new Intent(MainActivity.this, PlanActivity.class));
        }
        if (id == R.id.navigation_info) {
            startActivity(new Intent(MainActivity.this, InfoActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


}
