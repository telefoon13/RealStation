package be.mikedhoore.realstation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.mikedhoore.realstation.Adapters.ListAdapter;
import be.mikedhoore.realstation.Helpers.iRail.be.iRailLiveBoard;
import be.mikedhoore.realstation.Models.BoardItem;
import be.mikedhoore.realstation.Models.Station;
import be.mikedhoore.realstation.R;

public class StationActivity extends AppCompatActivity {

    private TextView stationName;
    private Station station;
    private RecyclerView mRecyclerView;
    private ListAdapter adpter;
    private RecyclerView.LayoutManager layoutManager;
    public static ArrayList<BoardItem> boardItems;

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

        //Do the http request and get the board filled
        iRailLiveBoard process = new iRailLiveBoard();
        process.execute(station);

        boardItems = new ArrayList<>();
        //stationName.setText(boardItems.get(1).getEndStation());
        for (int i = 0 ; i<boardItems.size(); i++){
            Log.d("TEST",boardItems.toString());
        }
    }


}
