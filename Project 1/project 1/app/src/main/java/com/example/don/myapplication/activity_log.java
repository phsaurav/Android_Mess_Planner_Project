package com.example.don.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class activity_log extends AppCompatActivity {

    ActivityDatabaseHelper aDB;
    Button adelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        adelete = (Button) findViewById(R.id.activity_delete_button);
        ListView listView = (ListView) findViewById(R.id.activity_list_view);

        aDB = new ActivityDatabaseHelper(this);
        SQLiteDatabase db = aDB.getReadableDatabase();

        Cursor cursor = aDB.getListContents(db);

        String spend;
        int value;

        ArrayList<adapter_class> activityList = new ArrayList<>();

        while(cursor.moveToNext())
        {
            spend = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2));
            value = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL3));
            adapter_class person = new adapter_class(spend,value);

            activityList.add(person);
        }

        adapter_Helper adapter = new adapter_Helper(this,R.layout.list_view_adapter,activityList);
        listView.setAdapter(adapter);


        adelete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                boolean test;
                test = aDB.delete();

                if(test){
                    Toast.makeText(activity_log.this,"DELETED :)!!",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(activity_log.this,"Something Wrong :(!!",Toast.LENGTH_LONG).show();
                }

                activity_log.super.onBackPressed();
                return false;
            }
        });
    }
}
