package com.example.qrescato;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class IniReader extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini_reader);
    }

    //Funcion ir a formularios Voluntarios o Protectoras
    public void ReadVol(View view) {
        Intent cambioUs2 = new Intent(this, VisorUsers.class);
        startActivity(cambioUs2);
    }

    public void ReadProtec(View view) {
        Intent cambioUs1 = new Intent(this, VisorProtec.class);
        startActivity(cambioUs1);
    }

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, MenuAdmin.class);
        startActivity(cambioUs);
    }
}
