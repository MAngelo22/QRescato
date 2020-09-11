package com.example.qrescato;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Qr1, QR2;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

    Qr1 = findViewById(R.layout.splash_qrastreo);
    QR2 = findViewById(R.layout.splash_qrastreo);

        /*  Accion del boton, para cambiar la activity */


    }

    public void onClick1(View view) {

        Intent cambioSp1 = new Intent(MainActivity.this, SplashQRastreo.class);
        startActivity(cambioSp1);
    }

    //  Accion del boton, para cambiar la activity

    public void onClick2(View view) {

        Intent cambioSp2 = new Intent(MainActivity.this, SplashQRescato.class);
        startActivity(cambioSp2);
    }
}
