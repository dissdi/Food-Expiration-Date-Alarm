package com.example.teamproject03.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.teamproject03.model.Food;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context context) { this.context = context; }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Food f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper._ID, f.getName());
        contentValues.put(DBHelper.DUE_DATE, f.getLeftDate());
        database.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

    public int upgrade(long _id, Food f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DATABASE_NAME, f.getName());
        contentValues.put(DBHelper.DUE_DATE, f.getLeftDate());
        int i = database.update(DBHelper.TABLE_NAME, contentValues,
                DBHelper._ID + " = " + _id, null);
        return i;
    }

    public List<Food> getFoodList() {
        List<Food> list = new ArrayList<Food>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("foods", new String[]{"_id", "name", "due_date"},
                null, null, null, null, "due_date");
        while(cursor.moveToNext()) {
            Food food = null;
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String dueDate = cursor.getString(2);
            food = new Food(id, name, dueDate);
            list.add(food);
        }
        return list;
    }

    public void delete(long _id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null);
    }
}
