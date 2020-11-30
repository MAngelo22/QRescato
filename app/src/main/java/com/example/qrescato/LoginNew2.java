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

public class LoginNew2 extends AppCompatActivity {

    Button BtnCrear;
    private UserAppDbHelper mHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);
        BtnCrear = (Button) findViewById(R.id.buttonCrearNew);
        final TextView textName = (TextView) findViewById(R.id.textNewName);
        final TextView textPassword = (TextView) findViewById(R.id.textNewPas);
        final TextView textEmail = (TextView) findViewById(R.id.textNewMail);

        //Aqui hago un onclick escuchando a la funcion de vaciar
        textName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                vaciar(textName);
            }
        });
        textPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                vaciar(textPassword);
            }
        });
        textEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                vaciar(textEmail);
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
        mHelper = new UserAppDbHelper(this);
        final TextView textoNombre = (TextView) findViewById(R.id.textNewName);
        final TextView textoPassword = (TextView) findViewById(R.id.textNewPas);
        final TextView textEmail = (TextView) findViewById(R.id.textNewMail);

        //Instanciamos la base de datos con mHelper, y la hacemos escribible
        //Creamos un "contenedor" que almacenara los valores que usaremos
        //Creamos los campos para rellenar (USuario y contraseña)
        //Insertamos los valores anteriores en TABLE = users, null , y los valores a procesar anteriores(Usuarios y password)
        //Cerramos la base de datos
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues valoresAProcesar = new ContentValues();

        valoresAProcesar.put(UsersAppContract.TaskEntry.NOMBRE_USUARIO ,textoNombre.getText().toString());
        valoresAProcesar.put(UsersAppContract.TaskEntry.PASSWORD_USUARIO, textoPassword.getText().toString());
        valoresAProcesar.put(UsersAppContract.TaskEntry.CORREO, textEmail.getText().toString());

        db.insert(UsersAppContract.TaskEntry.TABLE, null, valoresAProcesar);
        db.close();

        //Reproducimos el Toast
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toastnewusu, null);
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
        Intent cambioUs = new Intent(this, MenuRescato.class);
        startActivity(cambioUs);
    }
}