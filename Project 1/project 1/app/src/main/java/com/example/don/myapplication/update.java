package com.example.don.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.don.myapplication.MainActivity.pos;

public class update extends AppCompatActivity {

    Button add,remove, uBack,uName,delete;
    EditText uAmount;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        add = (Button) findViewById(R.id.add_button);
        remove = (Button) findViewById(R.id.remove_button);
        uBack = (Button) findViewById(R.id.update_back_button);
        uAmount = (EditText) findViewById(R.id.update_insert);
        myDB = new DatabaseHelper(this);
        uName = (Button) findViewById(R.id.update_name);
        delete = (Button) findViewById(R.id.delete_button);

        SQLiteDatabase db = myDB.getWritableDatabase();
        Cursor cursor = myDB.getListContents(db);
        String name;

        for (int id = 1;cursor.moveToNext();id++) {
            if(id == pos+1) {
                name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2));
                uName.setText(name);
                cursor.moveToFirst();
                break;
            }

        }

        uBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update.super.onBackPressed();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inuAmount = uAmount.getText().toString();
                SQLiteDatabase db = myDB.getWritableDatabase();
                int inAmount = Integer.parseInt(inuAmount);
                Cursor cursor = myDB.getListContents(db);
                String name;
                int initial;
                boolean test = true;
                for (int id = 1;cursor.moveToNext();id++) {
                    if(id == pos+1) {
                        name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2));
                        initial = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL3));
                        test = myDB.update(id,name, initial + inAmount);
                        break;
                    }

                }


                if(test){
                    Toast.makeText(update.this,"Added :)!!",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(update.this,"Something Wrong :(!!",Toast.LENGTH_LONG).show();
                }

            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inuAmount = uAmount.getText().toString();
                SQLiteDatabase db = myDB.getWritableDatabase();
                int inAmount = Integer.parseInt(inuAmount);
                Cursor cursor = myDB.getListContents(db);
                String name;
                int initial;
                boolean test = true;
                for (int id = 1;cursor.moveToNext();id++) {
                    if(id == pos+1) {
                        name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2));
                        initial = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL3));
                        test = myDB.update(id,name, initial - inAmount);
                        break;
                    }

                }


                if(test){
                    Toast.makeText(update.this,"Updated :)!!",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(update.this,"Something Wrong :(!!",Toast.LENGTH_LONG).show();
                }

            }
        });

        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SQLiteDatabase db = myDB.getWritableDatabase();
                Cursor cursor = myDB.getListContents(db);
                boolean test = true;
                int row = pos;
                boolean flag = false;
                String name;
                int inAmount,id;

                for (id = 1;cursor.moveToNext();id++) {
                    if(flag)
                    {
                        name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2));
                        inAmount = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL3));
                        myDB.update(id-1,name,inAmount);
                    }
                    if(id == pos+1) {
                        flag = true;

                    }

                }

                test = myDB.delete(id-1);
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + myDB.TABLE_NAME + "'");

                if(test){
                    Toast.makeText(update.this,"DELETED :)!!",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(update.this,"Something Wrong :(!!",Toast.LENGTH_LONG).show();
                }

                update.super.onBackPressed();

                return false;
            }
        });


    }
}
