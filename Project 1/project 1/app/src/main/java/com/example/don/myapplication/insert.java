package com.example.don.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert extends AppCompatActivity {

    DatabaseHelper myDB;
    Button Add,Back;
    EditText Name, Initial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Name = (EditText) findViewById(R.id.name_insert);
        Initial = (EditText) findViewById(R.id.initial_insert);
        Add = (Button) findViewById(R.id.add_button);
        Back = (Button) findViewById(R.id.back_button);
        myDB = new DatabaseHelper(this);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert.super.onBackPressed();
            }
        });


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inName = Name.getText().toString();
                String value = Initial.getText().toString();

                if(Name.length() != 0  && value.length() != 0)
                {
                    int inInitial = Integer.parseInt(value);
                    AddData(inName,inInitial);
                    Name.setText("");
                    Initial.setText("");
                }else{
                    Toast.makeText(insert.this,"You must put something in both text field :(!!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void AddData(String inName, int inInitial)
    {
        boolean insertData = myDB.insert(inName,inInitial);

        if(insertData){
            Toast.makeText(insert.this,"Successfully Entered :)!!",Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(insert.this,"Something Wrong :(!!",Toast.LENGTH_LONG).show();
        }
    }

}

