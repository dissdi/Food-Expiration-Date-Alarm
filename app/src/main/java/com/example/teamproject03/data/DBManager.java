package com.example.teamproject03.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.teamproject03.model.Food;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context context) { this.context = context; }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Food f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper._ID, f.getName());
        contentValues.put(DatabaseHelper.DUE_DATE, f.getLeftDate());
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public int upgrade(long _id, Food f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.DATABASE_NAME, f.getName());
        contentValues.put(DatabaseHelper.DUE_DATE, f.getLeftDate());
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues,
                DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
}
