package com.example.teamproject03.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.teamproject03.model.Food;

public class FoodDatabaseHelper extends SQLiteOpenHelper {
    // Database info
    private static final String DATABASE_NAME = "foodDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_FOODS = "foods";

    // Food Table Columns
    private static final String KEY_FOOD_ID = "id";
    private static final String KEY_FOOD_NAME = "foodName";
    private static final String KEY_FOOD_DUE_DATE = "dueDate";

    public FoodDatabaseHelper(@Nullable Context context, int version)
    { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db ) {
        String CREATE_FOODS_TABLE = "CREATE TABLE " + TABLE_FOODS +
                "(" +
                    KEY_FOOD_ID + " INTEGER PRIMARY KEY," +
                    KEY_FOOD_NAME + " TEXT," +
                    KEY_FOOD_DUE_DATE + "DATE" +
                ")";

        db.execSQL(CREATE_FOODS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOODS);
        onCreate(db);
    }

    public Food selectOne(long insertedId) {
        Food food = null;

        return food;
    }

}
