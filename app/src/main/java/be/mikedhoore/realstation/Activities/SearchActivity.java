package be.mikedhoore.realstation.Activities;



import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.mikedhoore.realstation.Helpers.DataBaseHelper;
import be.mikedhoore.realstation.Adapters.ListAdapter;
import be.mikedhoore.realstation.R;
import be.mikedhoore.realstation.Models.Station;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListAdapter adpter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Station> stationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buildList();
        buildRecyclerView();

    }

    public void buildList(){
        DataBaseHelper db = MainActivity.db;
        //Get the list of stations out of the DB
        List<Station> stations;
        stations = db.getListContents();
        stationArrayList = new ArrayList<>(stations);
    }

    public void buildRecyclerView(){
        mRecyclerView = (RecyclerView) findViewById(R.id.recylerViewa);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adpter = new ListAdapter(stationArrayList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adpter);
        adpter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Station station = stationArrayList.get(position);
                Intent intent = new Intent(SearchActivity.this, StationActivity.class);
                intent.putExtra("station", station);
                startActivity(intent);
            }
        });
    }
}
