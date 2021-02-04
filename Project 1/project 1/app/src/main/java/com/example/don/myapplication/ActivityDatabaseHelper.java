package com.example.don.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ActivityDatabaseHelper extends SQLiteOpenHelper {

    public static final String ADATABASE_NAME = "activitylist.db";
    public static final String ATABLE_NAME = "activitylist_data";
    public static final String ACOL1 = "ID";
    public static final String ACOL2 = "ITEM1";
    public static final String ACOL3 = "ITEM2";

    public ActivityDatabaseHelper(Context context)
    {
        super(context,ADATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "create table " + ATABLE_NAME + "(" + ACOL1
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ACOL2 + " TEXT NOT NULL, " + ACOL3 + " INTEGER);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ATABLE_NAME);
    }

    public boolean insert(String item1, int item2) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACOL2,item1);
        contentValues.put(ACOL3,item2);
        long i = db.insert(ActivityDatabaseHelper.ATABLE_NAME, null, contentValues);

        if (i > 0)
            return true;
        else
            return false;
    }

    public Cursor getListContents(SQLiteDatabase db)
    {
        String[] projection = {ACOL2,ACOL3};
        Cursor data = db.query(ATABLE_NAME,projection,null,null,null,null,null);
        return data;
    }

    public boolean delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ ATABLE_NAME);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + ATABLE_NAME + "'");
        db.close();
        return true;
    }
}
