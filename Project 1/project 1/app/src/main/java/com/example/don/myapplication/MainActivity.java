package com.example.don.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton flbtn;
    DatabaseHelper myDB;
    EditText totaltext;
    public static int count,total,pos;
    Button decrease,activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flbtn = (FloatingActionButton) findViewById(R.id.add_user);
        decrease = (Button) findViewById(R.id.minus_tot);
        activity = (Button) findViewById(R.id.activity_log_button_main);




        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, insert.class);
                startActivity(intent);
            }
        }
        );

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, decrease.class);
                startActivity(intent);
            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_log.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {

        totaltext = (EditText) findViewById(R.id.totalText);
        total = 0;
        count = 0;

        ListView listView = (ListView) findViewById(R.id.list_view);
        myDB = new DatabaseHelper(this);

        SQLiteDatabase db = myDB.getReadableDatabase();

        Cursor data = myDB.getListContents(db);

        String name;
        int initial;
        ArrayList<adapter_class> peopleList = new ArrayList<>();

        while(data.moveToNext())
        {
            name = data.getString(data.getColumnIndex(DatabaseHelper.COL2));
            initial = data.getInt(data.getColumnIndex(DatabaseHelper.COL3));
            total += initial;
            count++;
            adapter_class person = new adapter_class(name,initial);

            peopleList.add(person);
        }

        adapter_Helper adapter = new adapter_Helper(this,R.layout.list_view_adapter,peopleList);
        listView.setAdapter(adapter);

        totaltext.setText(String.valueOf(total));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos =(int) l;
                Intent intent = new Intent(MainActivity.this,update.class);
                startActivity(intent);
            }
        });
        super.onResume();
    }


}
