package com.example.qrescato;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrescato.UsersTable.UserAppDbHelper;
import com.example.qrescato.UsersTable.UsersAppContract;

public class IniCreate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini_create);
    }

    //Funcion ir a formularios Voluntarios o Protectoras
    public void formuVol(View view) {
        Intent cambioUs2 = new Intent(this, CreateFormularioVolun.class);
        startActivity(cambioUs2);
    }

    public void formuProtec(View view) {
        Intent cambioUs1 = new Intent(this, CreateFormularioProtec.class);
        startActivity(cambioUs1);
    }

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, MenuAdmin.class);
        startActivity(cambioUs);
    }
}
