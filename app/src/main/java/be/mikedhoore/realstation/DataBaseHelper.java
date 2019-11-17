/*
Used resource : https://www.youtube.com/watch?v=SK98ayjhk1E
* */
package be.mikedhoore.realstation;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "RealStation.db";
    public static final String TABLE_NAME = "Stations";
    public static final String COL1 = "id";
    public static final String COL2 = "locationX";
    public static final String COL3 = "locationY";
    public static final String COL4 = "nmbsId";
    public static final String COL5 = "name";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable =
                        "CREATE TABLE " + TABLE_NAME +
                        "(" +
                        COL1 +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL2 + " DECIMAL, " +
                        COL3 + " DECIMAL, " +
                        COL4 + " TEXT, " +
                        COL5 + " TEXT" +
                        ")";
        db.execSQL(creatTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addData(Station station){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, station.locationX);
        contentValues.put(COL3, station.locationY);
        contentValues.put(COL4, station.nmbsId);
        contentValues.put(COL5, station.name);
        long result = db.insert(TABLE_NAME, null , contentValues);
        if (result == -1){
            Log.d("FAULT", "Station niet toegevoegd" + contentValues);
            return false;
        } else {
            Log.d("OK", "Toegevoegd / " + station.name);
            return true;
        }
    }

    //Function to get a list of al stations in DB
    public List<Station> getListContents() {
        //Prepare a empty list to put the stations in to return
        List<Station> stationList = new ArrayList<>();
        //Open the DB
        SQLiteDatabase db = this.getWritableDatabase();
        //Do select statment om the table
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        //Get the ColumindexInts
        int iCOL1 = data.getColumnIndex(COL1);
        int iCOL2 = data.getColumnIndex(COL2);
        int iCOL3 = data.getColumnIndex(COL3);
        int iCOL4 = data.getColumnIndex(COL4);
        int iCOL5 = data.getColumnIndex(COL5);
        //For every entry found in the DB
        try{
            while (data.moveToNext()){
//Get the values
                int id = data.getInt(iCOL1);
                Double locationX = data.getDouble(iCOL2);
                Double locationY = data.getDouble(iCOL3);
                String nmbsId = data.getString(iCOL4);
                String name = data.getString(iCOL5);
                //Add to the List to return
                Station station = new Station(id, locationX, locationY, nmbsId, name);
                stationList.add(station);
                Log.d("Added to list", station.id + " / " + station.name);
            }
        } finally {
            data.close();
        }

        /*for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()){
            //Get the values
            int id = data.getInt(iCOL1);
            Double locationX = data.getDouble(iCOL2);
            Double locationY = data.getDouble(iCOL3);
            String nmbsId = data.getString(iCOL4);
            String name = data.getString(iCOL5);
            //Add to the List to return
            Station station = new Station(id, locationX, locationY, nmbsId, name);
            stationList.add(station);
            Log.d("Added to list", station.id + " / " + station.name);
        }*/
        //Return the list
        return stationList;
    }
}
