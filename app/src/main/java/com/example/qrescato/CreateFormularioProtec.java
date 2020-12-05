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
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraDbHelper;

public class CreateFormularioProtec extends AppCompatActivity {

    Button BtnCrear;
    private ZonaSeguraDbHelper mHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        BtnCrear = (Button) findViewById(R.id.BtnMod);
        final TextView textName = (TextView) findViewById(R.id.textNewName);
        final TextView textTelefono = (TextView) findViewById(R.id.textNewTlfn);
        final TextView textEmail = (TextView) findViewById(R.id.textNewMail2);
        final TextView textLat = (TextView) findViewById(R.id.textCoordY);
        final TextView textLong = (TextView) findViewById(R.id.textCoordX);

        //Aqui hago un onclick escuchando a la funcion de vaciar
        textName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                vaciar(textName);
            }
        });
        textTelefono.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                vaciar(textTelefono);
            }
        });
        textEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                vaciar(textEmail);
            }
        });
        textLong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                vaciar(textLong);
            }
        });
        textLat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                vaciar(textLat);
            }
        });

        BtnCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                Crear();
            }
        });

       // getSupportActionBar().hide();
    }



    public void Crear() {
        mHelper = new ZonaSeguraDbHelper(this);
        final TextView textName = (TextView) findViewById(R.id.textNewName);
        final TextView textTelefono = (TextView) findViewById(R.id.textNewTlfn);
        final TextView textEmail = (TextView) findViewById(R.id.textNewMail2);
        final TextView textLat = (TextView) findViewById(R.id.textCoordY);
        final TextView textLong = (TextView) findViewById(R.id.textCoordX);

        //Instanciamos la base de datos con mHelper, y la hacemos escribible
        //Creamos un "contenedor" que almacenara los valores que usaremos
        //Creamos los campos para rellenar (USuario y contraseña)
        //Insertamos los valores anteriores en TABLE = users, null , y los valores a procesar anteriores(Usuarios y password)
        //Cerramos la base de datos
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues valoresAProcesar = new ContentValues();

        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.NOMBRE ,textName.getText().toString());
        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.TLFN, textTelefono.getText().toString());
        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.CORREO, textEmail.getText().toString());
        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.LATITUD, textLat.getText().toString());
        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.LONGITUD, textLat.getText().toString());

        db.insert(ZonaSeguraContract.TaskEntry.TABLE, null, valoresAProcesar);
        db.close();

        //Reproducimos el Toast
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toastnewprot, null);
        Toast toastNewUsu = new Toast (this);
        toastNewUsu.setDuration(Toast.LENGTH_LONG);
        toastNewUsu.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 0);
        toastNewUsu.setView(view);
        toastNewUsu.show();

        Intent cambioUs = new Intent(this, MenuAdmin.class);
        startActivity(cambioUs);

    }
        //Aqui ponemos los campos vacios al doble click
    public void vaciar(TextView tv){
        tv.setText("");
    }

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, IniCreate.class);
        startActivity(cambioUs);
    }
}