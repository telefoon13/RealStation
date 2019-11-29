package be.mikedhoore.realstation.Helpers.iRail.be;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import be.mikedhoore.realstation.Activities.StationActivity;
import be.mikedhoore.realstation.Models.BoardItem;
import be.mikedhoore.realstation.Models.Station;

public class iRailLiveBoard extends AsyncTask<Station,Void,Void> {

    String data ="";
    ArrayList<BoardItem> boardItems;

    @Override
    protected Void doInBackground(Station... params) {
        try {
            Station station = params[0];
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
            //Log.d("json", jsonArray.toString());
            for (int i = 0 ; i<jsonArray.length(); i++){
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                //Here code to put object to object and add in Arraylist
                BoardItem boardItem = new BoardItem(jsonObject2.getString("delay"),jsonObject2.getString("station"),jsonObject2.getString("time"),jsonObject2.getString("vehicle"),jsonObject2.getString("platform"),jsonObject2.getString("canceled"));
                boardItems = new ArrayList<>();
                boardItems.add(boardItem);
            }
            //Log.d("LIST", boardItems.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //StationActivity.boardItems = boardItems;
        Log.d("boarditem", boardItems.toString());
        for (int i = 0 ; i<boardItems.size(); i++){
            StationActivity.boardItems.set(i,boardItems.get(i));
        }
    }
}
