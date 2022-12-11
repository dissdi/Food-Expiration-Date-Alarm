package com.example.teamproject03.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database info
    public static final String DATABASE_NAME = "foodDatabase";
    public static final int DATABASE_VERSION = 1;

    // Table name
    public static final String TABLE_NAME = "foods";

    // Food Table Columns
    public static final String _ID = "id";
    public static final String NAME = "foodName";
    public static final String DUE_DATE = "dueDate";
    public static final String STORAGE_TYPE = "storageType";

    // Constructor
    public DatabaseHelper(@Nullable Context context)
    { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db ) {
        String CREATE_FOODS_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" +
                    _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL," +
                    DUE_DATE + "DATE" +
                    STORAGE_TYPE + "TEXT NOT NULL" +
                ")";

        db.execSQL(CREATE_FOODS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
