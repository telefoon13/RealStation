package be.mikedhoore.realstation.Helpers.iRail.be;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import be.mikedhoore.realstation.Activities.StationActivity;
import be.mikedhoore.realstation.Models.Station;

public class iRailLiveBoard extends AsyncTask<Station,Void,Void> {

    String data ="";

    @Override
    protected Void doInBackground(Station... params) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        StationActivity.boardItems = new ArrayList<>();
    }
}
