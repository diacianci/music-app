package com.example.diaci.tunami;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.os.Debug;

import java.util.ArrayList;

/**
 * Created by diaci on 4/21/2017.
 */

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tunings.db";

    private static final String TABLE_TUNINGS = "tunings";

    private static final String COLUMN_ID = "id";
    public static final String COLUMN_TUNING_NAME = "tuning_name";
    public static final String COLUMN_HIGH_E_STRING = "high_e_string";
    public static final String COLUMN_B_STRING = "b_string";
    public static final String COLUMN_G_STRING = "g_string";
    public static final String COLUMN_D_STRING = "d_string";
    public static final String COLUMN_A_STRING = "a_string";
    public static final String COLUMN_LOW_E_STRING = "low_e_string";

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TUNINGS_TABLE = "CREATE TABLE " +
                TABLE_TUNINGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TUNING_NAME + " TEXT,"
                + COLUMN_HIGH_E_STRING + " TEXT,"
                + COLUMN_B_STRING + " TEXT,"
                + COLUMN_G_STRING + " TEXT,"
                + COLUMN_D_STRING + " TEXT,"
                + COLUMN_A_STRING + " TEXT,"
                + COLUMN_LOW_E_STRING + " TEXT" + ")";
        db.execSQL(CREATE_TUNINGS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUNINGS);
        onCreate(db);
    }

    // Add new tuning to the database table
    public void addTuning(Tuning tuning)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TUNING_NAME, tuning.getTuningName());
        values.put(COLUMN_HIGH_E_STRING, tuning.getHighEString());
        values.put(COLUMN_B_STRING, tuning.getbString());
        values.put(COLUMN_G_STRING, tuning.getgString());
        values.put(COLUMN_D_STRING, tuning.getdString());
        values.put(COLUMN_A_STRING, tuning.getaString());
        values.put(COLUMN_LOW_E_STRING, tuning.getLowEString());
        db.insert(TABLE_TUNINGS, null, values);
        db.close();
        System.out.println("Inserted row into database");
    }

    // Delete tuning from database table
    public void deleteTuning(String tuningID)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TUNINGS + "WHERE" + COLUMN_ID + " = \"" + tuningID + "\";");
    }

    public Tuning getTuning(String tuningName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TUNINGS, new String[] {
                COLUMN_ID,
                COLUMN_TUNING_NAME,
                COLUMN_HIGH_E_STRING,
                COLUMN_B_STRING,
                COLUMN_G_STRING,
                COLUMN_D_STRING,
                COLUMN_A_STRING,
                COLUMN_LOW_E_STRING },
                COLUMN_TUNING_NAME + "=?",
                new String[] { tuningName },
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();
        Tuning curTuning = new Tuning(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7));

        db.close();

        return curTuning;


    }

    public ArrayList<String> getAllTunings()
    {
        ArrayList<String> allTunings = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        //db.beginTransaction();
        String selectQuery = "SELECT * FROM " + TABLE_TUNINGS;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            while(cursor.moveToNext())
            {
                // Need to get the letter for each string's tuning
//                String currentTuning = cursor.getString(cursor.getColumnIndex(COLUMN_LOW_E_STRING)) + " " +
//                        cursor.getString(cursor.getColumnIndex(COLUMN_A_STRING)) + " " +
//                        cursor.getString(cursor.getColumnIndex(COLUMN_D_STRING)) + " " +
//                        cursor.getString(cursor.getColumnIndex(COLUMN_G_STRING)) + " " +
//                        cursor.getString(cursor.getColumnIndex(COLUMN_B_STRING)) + " " +
//                        cursor.getString(cursor.getColumnIndex(COLUMN_HIGH_E_STRING));
                String currentTuning = cursor.getString(cursor.getColumnIndex(COLUMN_TUNING_NAME));

                allTunings.add(currentTuning);


            }
        }
        //db.endTransaction();
        db.close();

        return allTunings;
    }
}
