package com.example.don.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";
    public static final String COL3 = "ITEM2";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME + "(" + COL1
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT NOT NULL, " + COL3 + " INTEGER);";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public boolean update(int id,String item1,int item2) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String where = "id" + "=" + id;

        contentValues.put(COL2,item1);
        contentValues.put(COL3,item2);

        long i = db.update(TABLE_NAME,contentValues,where,null);

        if (i > 0)
            return true;
        else
            return false;

    }

    public boolean insert(String item1, int item2) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,item1);
        contentValues.put(COL3,item2);
        long i = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

        if (i > 0)
            return true;
        else
            return false;
    }

    public Cursor getListContents(SQLiteDatabase db)
    {
        String[] projection = {COL2,COL3};
        Cursor data = db.query(TABLE_NAME,projection,null,null,null,null,null);
        return data;
    }

    public boolean delete(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "id" + "=" + id;
        long i = db.delete(TABLE_NAME,where,null);

        if (i > 0)
            return true;
        else
            return false;
    }

}
