package com.example.qrescato;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuRastreo extends AppCompatActivity {

    Button BtnVis;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rastreo);

        //BtnVis = findViewById(R.id.buttonvis);
        BtnVis = findViewById(R.layout.camaraqr);

    }

    public void Visita(View view) {

        Intent cambioSp1 = new Intent(MenuRastreo.this, CamaraQR.class);
        startActivity(cambioSp1);
    }

}

