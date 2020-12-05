package com.example.qrescato;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class IniDelete extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini_delete);
    }

    //Funcion ir a formularios Voluntarios o Protectoras
    public void DelmuVol(View view) {
        Intent cambioUs2 = new Intent(this, DeleteUsers.class);
        startActivity(cambioUs2);
    }

    public void DelProtec(View view) {
        Intent cambioUs1 = new Intent(this, DeleteProtec.class);
        startActivity(cambioUs1);
    }

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, MenuAdmin.class);
        startActivity(cambioUs);
    }
}
