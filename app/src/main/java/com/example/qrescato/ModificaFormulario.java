package com.example.qrescato;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.qrescato.UsersTable.UserAppDbHelper;

public class ModificaFormulario extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{


    TextView coorX, coorY;
    Button BtnGenerar;

    private UserAppDbHelper mHelper;
    private LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        coorX = (TextView) findViewById(R.id.longProtectoraModifcar);
        coorY = (TextView) findViewById(R.id.latProtectoraModificar);
    }

public void Modificar(){
    LayoutInflater inflater = getLayoutInflater();
    View view = inflater.inflate(R.layout.toastmodusu, null);
    Toast toastNewUsu = new Toast (this);
    toastNewUsu.setDuration(Toast.LENGTH_LONG);
    toastNewUsu.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 0);
    toastNewUsu.setView(view);
    toastNewUsu.show();
}

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, MenuAdmin.class);
        startActivity(cambioUs);
    }
    public void Generar(View view) {


     /*   //Generar coordenadas
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            coorY.setText("No se han definido los permisos necesarios.");
            coorX.setText("No se encuentra, su posición");
        }else{
            locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            coorY.setText(String.valueOf(loc.getLatitude()));
            coorX.setText(String.valueOf(loc.getLongitude()));
        }
     */
    }
}