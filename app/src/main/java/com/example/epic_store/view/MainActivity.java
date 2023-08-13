package com.example.epic_store.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.epic_store.R;

public class MainActivity extends AppCompatActivity {

    Button onSale;
    Button comingSoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);    //Generates the app bar
        setSupportActionBar(myToolbar);

        onSale=findViewById(R.id.onSale);
        comingSoon=findViewById(R.id.comingSoon);

        onSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, OnSaleActivity.class);
                startActivity(intent);
            }
        });

        comingSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ComingSoonActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // Inflate the Menu
        getMenuInflater().inflate(R.menu.menu,menu);


        return true;
    }
}