package com.example.lr8_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Concert.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_concert";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "concert_title";
    private static final String COLUMN_BAND = "concert_band";
    private static final String COLUMN_DATE = "concert_date";
    private static final String COLUMN_PLACE = "concert_place";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_BAND + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_PLACE+ " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addConcert(String title, String band, String date, String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_BAND, band);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PLACE, place);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Не работает", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Данные успешно добавлены", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String title, String band, String date, String place){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_BAND, band);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PLACE, place);


        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Не работает", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Данные успешно обновлены", Toast.LENGTH_SHORT).show();
        }

    }

}
