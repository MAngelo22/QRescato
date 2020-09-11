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

    Qr1 = findViewById(R.layout.splash_qrastreo);
    QR2 = findViewById(R.layout.splash_qrastreo);

        //  Accion del boton, para cambiar la activity

    Qr1.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            Intent siguiente = new Intent(MainActivity.this, SplashQRastreo.class);
            startActivity(siguiente);
        }
    });

        //  Accion del boton, para cambiar la activity

    QR2.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View v) {

             Intent siguiente = new Intent(MainActivity.this, SplashQRescato.class);
             startActivity(siguiente);
         }
    });

    }
}
