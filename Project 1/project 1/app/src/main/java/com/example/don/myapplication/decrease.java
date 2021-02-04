package com.example.don.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.don.myapplication.MainActivity.count;
import static com.example.don.myapplication.MainActivity.total;

public class decrease extends AppCompatActivity {

    Button decrease, dBack,view;
    EditText Amount,spendTo;
    DatabaseHelper myDB;
    ActivityDatabaseHelper aDB;
    int temp,initial,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrease);

        decrease = (Button) findViewById(R.id.decrease_button);
        dBack = (Button) findViewById(R.id.decrease_back_button);
        view = (Button) findViewById(R.id.activity_button);
        Amount = (EditText) findViewById(R.id.decrease_insert);
        spendTo = (EditText) findViewById(R.id.decrease_insert_on);
        myDB = new DatabaseHelper(this);
        aDB = new ActivityDatabaseHelper(this);

        dBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrease.super.onBackPressed();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(decrease.this,activity_log.class);
                startActivity(intent);
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inAmount = Amount.getText().toString();
                String inSpent = spendTo.getText().toString();
                SQLiteDatabase db = myDB.getWritableDatabase();


                if(Integer.parseInt(inAmount) < total) {
                    int value = Integer.parseInt(inAmount);
                    int temp = value / count;
                    String name;
                    int id = 1;
                    boolean test = true;
                    Cursor cursor = myDB.getListContents(db);



                    if(inSpent.length() != 0 && value > 0)
                    {

                        aDB.insert(inSpent,value);
                        Amount.setText("");
                        spendTo.setText("");

                        while (cursor.moveToNext()) {
                            name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2));
                            initial = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL3));
                            test = myDB.update(id,name, initial - temp);
                            id++;
                        }

                    }else{
                        Toast.makeText(decrease.this,"You must put something in both text field :(!!",Toast.LENGTH_LONG).show();
                    }

                    if(test){
                        Toast.makeText(decrease.this,"Money Spent :|!!",Toast.LENGTH_LONG).show();
                    }else
                    {
                        Toast.makeText(decrease.this,"Something Wrong :(!!",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(decrease.this,"You don't have enough money to spend :(!!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
