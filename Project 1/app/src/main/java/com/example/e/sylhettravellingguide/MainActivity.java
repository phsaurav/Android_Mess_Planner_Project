package com.example.e.sylhettravellingguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button jaflong;
    public Button ratargul;
    public Button lalakhal;
    public Button bisnakandi;
    public Button shahjalal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jaflong = (Button) findViewById(R.id.jaflong);
        ratargul = (Button) findViewById(R.id.ratargul);
        lalakhal = (Button) findViewById(R.id.lalakhal);
        bisnakandi = (Button) findViewById(R.id.bisnakandi);
        shahjalal = (Button) findViewById(R.id.shahjalalMajar);

        jaflong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openJaflong();
            }
        });

        ratargul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRatargul();
            }
        });

        lalakhal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLalakhal();
            }
        });

        bisnakandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBisnakandi();
            }
        });
        shahjalal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShahjalalMajar();
            }
        });
    }

    private void openShahjalalMajar() {
        Intent intent = new Intent(this,Shahjalal.class);
        startActivity(intent);
    }

    private void openBisnakandi() {
        Intent intent = new Intent(this,Bisnakandi.class);
        startActivity(intent);
    }

    private void openLalakhal() {
        Intent intent = new Intent(this,Lalakhal.class);
        startActivity(intent);
    }

    private void openRatargul() {
        Intent intent = new Intent(this,RatarGul.class);
        startActivity(intent);
    }

    private void openJaflong() {

        Intent intent = new Intent(this,Jaflong.class);
        startActivity(intent);
    }
}
