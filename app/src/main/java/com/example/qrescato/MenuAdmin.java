package com.example.qrescato;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MenuAdmin extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuadmin);
    }

    //La funcion de volver al menu anterior
    public void Create(View view) {
        Intent cambioUs = new Intent(this, IniCreate.class);
        startActivity(cambioUs);
    }

    public void Reader(View view) {
        Intent cambioUs4 = new Intent(this, IniReader.class);
        startActivity(cambioUs4);
    }

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs5 = new Intent(this, MenuRescato.class);
        startActivity(cambioUs5);
    }
}
