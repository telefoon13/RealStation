package be.mikedhoore.realstation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import be.mikedhoore.realstation.Adapters.BoardAdapter;
import be.mikedhoore.realstation.Models.BoardItem;
import be.mikedhoore.realstation.Models.Station;
import be.mikedhoore.realstation.R;

public class StationActivity extends AppCompatActivity {

    private TextView nmbsId;
    private TextView location;
    private Station station;

    private RecyclerView mRecyclerView;
    private BoardAdapter adpter;
    private RecyclerView.LayoutManager layoutManager;
    public  ArrayList<BoardItem> boardItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Network issues
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        //Get xml data
        nmbsId = findViewById(R.id.nmbsId);
        location = findViewById(R.id.location);
        //Get the object out of the intent
        Intent intent = getIntent();
        station = (Station)intent.getSerializableExtra("station");
        //Set xml data
        getSupportActionBar().setTitle(station.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //stationName.setText(station.getName());
        nmbsId.setText(station.getNmbsId());
        String loc = this.getString(R.string.latitude) + " : " + station.getLocationX() + "\n" + this.getString(R.string.longitude) + " : " + station.getLocationY();
        location.setText(loc);


        getLiveData();

        if(boardItems.size() > 0) {
            mRecyclerView = findViewById(R.id.recylerViewBoard);
            mRecyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(this);
            adpter = new BoardAdapter(boardItems, this);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(adpter);
        } else {
            Toast.makeText(this, this.getResources().getString(R.string.noBoard), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getLiveData(){
        String data = "";
        try {
            //Log.d("Station ID", station.getNmbsId());
            //Open the url and fetch the data
            URL url = new URL("https://api.irail.be/liveboard/?format=json&lang=nl&id="+station.getNmbsId());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //Read every line of the fetched data
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
                //Log.d("Line", line);
            }
            //Turn the data into an JSONArray
            JSONObject jsonObject = new JSONObject(data);
            JSONObject jsonObject1 =jsonObject.getJSONObject("departures");
            JSONArray jsonArray = jsonObject1.getJSONArray("departure");
            //boardItems = new ArrayList<>();
            //Log.d("json", jsonArray.toString());
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                    //Here code to put object to object and add in Arraylist
                    BoardItem boardItem = new BoardItem(jsonObject2.getString("delay"), jsonObject2.getString("station"), jsonObject2.getString("time"), jsonObject2.getString("vehicle"), jsonObject2.getString("platform"), jsonObject2.getString("canceled"));
                    boardItems.add(boardItem);
                    Log.d("ITEM", jsonObject2.getString("platform"));
                }
            }
            //Log.d("LIST", boardItems.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
