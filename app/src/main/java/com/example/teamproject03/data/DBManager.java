package com.example.teamproject03.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.teamproject03.model.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    UUIDgeneration uuid = new UUIDgeneration();
    public DBManager(Context context) {
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Food f) {
        f.setID(uuid.getUUID());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.UUID, f.getID());
        contentValues.put(DBHelper.DUE_DATE, f.getLeftDate());
        contentValues.put(DBHelper.STORAGE_TYPE, f.getStorageType());
        database.insert(DBHelper.TABLE_NAME, null, contentValues);
    }

    public int upgrade(String _id, Food f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DATABASE_NAME, f.getName());
        contentValues.put(DBHelper.DUE_DATE, f.getLeftDate());
        int i = database.update(DBHelper.TABLE_NAME, contentValues,
                DBHelper._ID + " = " + _id, null);
        return i;
    }

    public List<Food> getFoodList() {
        ArrayList<Food> list = new ArrayList<Food>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("foods", new String[]{"_id", "uuid", "foodName", "dueDate", "storageType"},
                null, null, null, null, "dueDate");
        while(cursor.moveToNext()) {
            Food food = null;
            String uuid = cursor.getString(1);
            String name = cursor.getString(2);
            String dueDate = cursor.getString(3);
            food = new Food(uuid, name, dueDate);
            list.add(food);
        }
        return list;
    }

    public void delete(String _id) {
        database.delete(DBHelper.TABLE_NAME, DBHelper._ID + "=" + _id, null);
    }
}

