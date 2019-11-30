package be.mikedhoore.realstation.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import be.mikedhoore.realstation.Helpers.DataBaseHelper;
import be.mikedhoore.realstation.Helpers.GPSTracker;
import be.mikedhoore.realstation.Helpers.iRail.be.iRailStations;
import be.mikedhoore.realstation.Models.Station;
import be.mikedhoore.realstation.R;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    public static GoogleMap mMap;
    public static DataBaseHelper db;
    private LatLng location = new LatLng(50.842395, 4.322808);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseHelper(this);
        //Clear the DB first
        db.deleteAll();
        //Get all the stations from iRail
        iRailStations process = new iRailStations();
        process.execute();

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
            GPSTracker gps = new GPSTracker((this));
            location = new LatLng(gps.getLatitude(), gps.getLongitude());
        } else {
            requestPermission();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }


    private void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            new AlertDialog.Builder(this)
                    .setTitle(this.getResources().getString(R.string.text_location_permission))
                    .setMessage(this.getResources().getString(R.string.text_location_permission))
                    .setPositiveButton(this.getResources().getString(R.string.ok), new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int witch){
                    ActivityCompat.requestPermissions(MainActivity.this, new  String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                }
            })
                    .setNegativeButton(this.getResources().getString(R.string.nok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
            .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new  String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_FINE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                GPSTracker gps = new GPSTracker((this));
                location = new LatLng(gps.getLatitude(), gps.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            } else {
                Toast.makeText(this, "NOK", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Toast.makeText(this, location.toString(), Toast.LENGTH_SHORT).show();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.setMinZoomPreference(12);

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

    //Add menu
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
