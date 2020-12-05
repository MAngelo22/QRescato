package com.example.qrescato;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrescato.UsersTable.UserAppDbHelper;
import com.example.qrescato.UsersTable.UsersAppContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraDbHelper;
import com.google.android.material.textfield.TextInputEditText;

public class DeleteProtec extends AppCompatActivity implements Animation.AnimationListener {

    private Button BtnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        BtnBorrar = (Button) findViewById(R.id.BtnDel);
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

        BtnBorrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vista){
                borrar();
            }
        });


        //Aqui hago un onclick escuchando a la funcion de vaciar
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textName);
            }
        });

        //getSupportActionBar().hide();
    }

    public void vaciar(TextView tv) {
        tv.setText("");
    }

    public void borrar() {
        //Vacia las cajas de texto
        TextInputEditText usuario = (TextInputEditText) findViewById(R.id.textName);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.textPas);

        ZonaSeguraDbHelper mHelper = new ZonaSeguraDbHelper(this);
        mHelper = new ZonaSeguraDbHelper(this);
        final TextView textName = (TextView) findViewById(R.id.textNewName);
        final TextView textTelefono = (TextView) findViewById(R.id.textNewTlfn);
        final TextView textEmail = (TextView) findViewById(R.id.textNewMail2);
        final TextView textLat = (TextView) findViewById(R.id.textCoordY);
        final TextView textLong = (TextView) findViewById(R.id.textCoordX);

        //Instanciamos la base de datos con mHelper, y la hacemos Borrar
        //Creamos un "contenedor" que almacenara los valores que usaremos
        //Creamos los campos para rellenar (USuario y contraseña)
        //Borraremos los valores anteriores en TABLE = users, null , y los valores a procesar anteriores(Usuarios y password)
        //Cerramos la base de datos
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues valoresAProcesar = new ContentValues();

        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.NOMBRE, textName.getText().toString());
        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.TLFN, textTelefono.getText().toString());
        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.CORREO, textEmail.getText().toString());
        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.LATITUD, textLat.getText().toString());
        valoresAProcesar.put(ZonaSeguraContract.TaskEntry.LONGITUD, textLat.getText().toString());

        //db.delete(ZonaSeguraContract.TaskEntry.TABLE,null, valoresAProcesar);
        db.close();

        //Reproducimos el Toast
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.toastdelete, null);
        Toast toastNewUsu = new Toast(this);
        toastNewUsu.setDuration(Toast.LENGTH_LONG);
        toastNewUsu.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        toastNewUsu.setView(view);
        toastNewUsu.show();

        Intent cambioUs = new Intent(this, IniDelete.class);
        startActivity(cambioUs);

    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, IniDelete.class);
        startActivity(cambioUs);
    }
}
