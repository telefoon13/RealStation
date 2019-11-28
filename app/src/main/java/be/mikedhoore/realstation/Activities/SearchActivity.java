package be.mikedhoore.realstation.Activities;



import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import be.mikedhoore.realstation.DataBaseHelper;
import be.mikedhoore.realstation.ListAdapter;
import be.mikedhoore.realstation.R;
import be.mikedhoore.realstation.Models.Station;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adpter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        DataBaseHelper db = MainActivity.db;
        //ArrayList<Station> listOfStations = new ArrayList<>();
        //Get the list of stations out of the DB
        List<Station> stations;
        stations = db.getListContents();
        ArrayList<Station> arrayList = new ArrayList<Station>(stations);



        mRecyclerView = (RecyclerView) findViewById(R.id.recylerViewa);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adpter = new ListAdapter(arrayList);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adpter);

    }
}
