/* used resource
https://www.youtube.com/watch?v=Vcn4OuV4Ixg
 */
package be.mikedhoore.realstation;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
import java.util.List;

public class getIRailData extends AsyncTask<Void,Void,Void> {

    String data ="";
    DataBaseHelper db;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //Open the url and fetch the data
            URL url = new URL("https://api.irail.be/stations/?format=json&lang=en");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //Read every line of the fetched data
            String line = "";
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            //Turn the data into an JSONArray
            JSONArray jsonArray = new JSONArray(data);
            db = MainActivity.db;
            //Temp only 20 stations for testing
            for (int i = 0 ; i<jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                //Here code to put object to db
                Station station = new Station(jsonObject.getDouble("locationX"),jsonObject.getDouble("locationY"),jsonObject.getString("nmbsid"),jsonObject.getString("name"));
                db.addData(station);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
        db = MainActivity.db;
        //Get the list of stations out of the DB
        List<Station> stations = new ArrayList<>();
        stations = db.getListContents();
        //ForEvery entry in the list make a marker and add to map
        for (Station station : stations) {
            LatLng stationPin = new LatLng(station.locationX, station.locationY);
            MainActivity.mMap.addMarker(new MarkerOptions().position(stationPin).title(station.name));
        }

    }
}
