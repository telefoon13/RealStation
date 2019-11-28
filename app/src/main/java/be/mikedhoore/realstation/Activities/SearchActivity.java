package be.mikedhoore.realstation.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import be.mikedhoore.realstation.DataBaseHelper;
import be.mikedhoore.realstation.R;
import be.mikedhoore.realstation.Models.Station;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
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
        //

    }
}
