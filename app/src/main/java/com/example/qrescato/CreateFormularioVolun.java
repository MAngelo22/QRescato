package com.example.qrescato;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.qrescato.VoluntariosTable.VoluntariosContract;
import com.example.qrescato.VoluntariosTable.VoluntariosDbHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class CreateFormularioVolun extends AppCompatActivity {

    Button BtnCrear;
    private VoluntariosDbHelper mHelper;
    FusedLocationProviderClient client;
    float latitud;
    float longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_voluntario);
        BtnCrear = (Button) findViewById(R.id.BtnCrearVolun);
        final TextView textName = (TextView) findViewById(R.id.nombreProtectoraModificar);
        final TextView textPassword = (TextView) findViewById(R.id.PasswordCambiar);
        final TextView textTelefono = (TextView) findViewById(R.id.telefonoProtectoraModificar);
        final TextView textEmail = (TextView) findViewById(R.id.mailProtectoraModificar);
        final TextView textLat = (TextView) findViewById(R.id.latProtectoraModificar);
        final TextView textLong = (TextView) findViewById(R.id.longProtectoraModifcar);

        //Aqui hago un onclick escuchando a la funcion de vaciar
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textName);
            }
        });
        textPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textPassword);
            }
        });
        textTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textTelefono);
            }
        });
        textEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textEmail);
            }
        });
        textLong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textLong);
            }
        });
        textLat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textLat);
            }
        });

        BtnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                CrearVoluntario();
            }
        });

        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        // getSupportActionBar().hide();
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    latitud = (float) location.getLatitude();
                    longitud = (float) location.getLongitude();
                }
            }
        });
    }


    public void CrearVoluntario() {
        mHelper = new VoluntariosDbHelper(this);
        final TextView textName = (TextView) findViewById(R.id.nombreProtectoraModificar);
        final TextView textPassword = (TextView) findViewById(R.id.PasswordCambiar);
        final TextView textTelefono = (TextView) findViewById(R.id.telefonoProtectoraModificar);
        final TextView textEmail = (TextView) findViewById(R.id.mailProtectoraModificar);
        final TextView textLat = (TextView) findViewById(R.id.latProtectoraModificar);
        final TextView textLong = (TextView) findViewById(R.id.longProtectoraModifcar);


        //Instanciamos la base de datos con mHelper, y la hacemos escribible
        //Creamos un "contenedor" que almacenara los valores que usaremos
        //Creamos los campos para rellenar (USuario y contraseña)
        //Insertamos los valores anteriores en TABLE = users, null , y los valores a procesar anteriores(Usuarios y password)
        //Cerramos la base de datos
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues valoresAProcesar = new ContentValues();

        valoresAProcesar.put(VoluntariosContract.TaskEntry.NOMBRE_USUARIO ,textName.getText().toString());
        valoresAProcesar.put(VoluntariosContract.TaskEntry.PASSWORD_USUARIO, textPassword.getText().toString());
        valoresAProcesar.put(VoluntariosContract.TaskEntry.TLFN, textTelefono.getText().toString());
        valoresAProcesar.put(VoluntariosContract.TaskEntry.CORREO, textEmail.getText().toString());
        valoresAProcesar.put(VoluntariosContract.TaskEntry.LATITUD, textLat.getText().toString());
        valoresAProcesar.put(VoluntariosContract.TaskEntry.LONGITUD, textLong.getText().toString());


        if (textName.getText()==null ||textTelefono.getText()==null||textPassword.getText()==null||textEmail.getText()==null||textLat.getText()==null||textLong.getText()==null){
            Toast toastUsu = Toast.makeText(this, "Comprueba que los campos no esten vacios", Toast.LENGTH_LONG);
            toastUsu.show();
        }else {
            db.insert(VoluntariosContract.TaskEntry.TABLE, null, valoresAProcesar);
            db.close();

            //Reproducimos el Toast
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.toastnewusu, null);
            Toast toastNewUsu = new Toast (this);
            toastNewUsu.setDuration(Toast.LENGTH_LONG);
            toastNewUsu.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 0);
            toastNewUsu.setView(view);
            toastNewUsu.show();

            Intent cambioUs = new Intent(this, MenuAdmin.class);
            startActivity(cambioUs);
        }
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

    public void generarCoord (View view){
        final TextView textLat = (TextView) findViewById(R.id.latProtectoraModificar);
        final TextView textLong = (TextView) findViewById(R.id.longProtectoraModifcar);
        if (latitud != 0.0f){
            String textLati=Float.toString(latitud);
            textLat.setText(textLati);
        }
        if (longitud != 0.0f){
            String textLongi=Float.toString(longitud);
            textLong.setText(textLongi);
        }
    }
}