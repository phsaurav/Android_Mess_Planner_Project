package com.example.e.a0509;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class dbHandaler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final string DATABASE_NAME = "data.db";
    private static final string TABLE_DATA = "data";
    private static final string COLUMN_ID = "_id";
    private static final string COLUMN_DATANAME = "dataName";

    public dbHandaler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_DATA + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
                + COLUMN_DATANAME + " TEXT " + ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }

    public void addData(Database data)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATANAME, data.get_dataname())
    }
}
