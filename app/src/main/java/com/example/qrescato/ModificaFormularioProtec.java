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

public class ModificaFormularioProtec extends AppCompatActivity {

    Button BtnCrear;
    private UserAppDbHelper mHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_protec);

        BtnCrear = (Button) findViewById(R.id.BtnMod);
        final TextView textName = (TextView) findViewById(R.id.textNewName);
        final TextView textPassword = (TextView) findViewById(R.id.textNewPas);
        final TextView textEmail = (TextView) findViewById(R.id.textNewTlfn);

        //Aqui hago un onclick escuchando a la funcion de vaciar

    }

    public void Modificar(){
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toastmodprot, null);
        Toast toastNewUsu = new Toast (this);
        toastNewUsu.setDuration(Toast.LENGTH_LONG);
        toastNewUsu.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 0);
        toastNewUsu.setView(view);
        toastNewUsu.show();
    }

        //Aqui ponemos los campos vacios al doble click
    public void vaciar(TextView tv){
        tv.setText("");
    }


    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, IniMod.class);
        startActivity(cambioUs);
    }

}