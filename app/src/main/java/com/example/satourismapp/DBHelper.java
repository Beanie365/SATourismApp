package com.example.satourismapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "satourist.db";
    // User table name
    private static final String TABLE_NAME = "touristattractions";
    // User Table Columns names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PLACE = "place";
    private static final String COLUMN_ATTRACTION = "attraction";
    SQLiteDatabase db;
    PlaceDetails d = new PlaceDetails();
    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PLACE + " TEXT," + COLUMN_ATTRACTION + " TEXT"
             + ")";
    private String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addPlaces(PlaceDetails d){
     db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ATTRACTION, d.getAttraction());
        values.put(COLUMN_PLACE, d.getPlace());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void getPlaceDetails(String place){
        PlaceDetails d = new PlaceDetails();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM touristattractions WHERE place = '" + place + "' LIMIT 1", null);
        int placeIndex = cursor.getColumnIndex("place");
        int attrIndex = cursor.getColumnIndex("attraction");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            d.setAttraction(cursor.getString(attrIndex));
            d.setPlace(cursor.getString(placeIndex));
            Log.d("TAG","place stuff: " +cursor.getString(placeIndex));
            cursor.moveToNext();
        }
        cursor.close();
    }
}
