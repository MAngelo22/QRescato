package com.example.qrescato;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class MenuRescato extends AppCompatActivity {

    Button Visi;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescato);

        Visi = findViewById(R.layout.activity_maps);

    }

    public void mapa (View view) {

        Intent cambioSp1 = new Intent(MenuRescato.this, MapsActivity.class);
        startActivity(cambioSp1);
    }

}

