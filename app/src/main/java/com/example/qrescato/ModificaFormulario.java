package com.example.qrescato;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.qrescato.UsersTable.UserAppDbHelper;
import com.example.qrescato.VoluntariosTable.VoluntariosContract;
import com.example.qrescato.VoluntariosTable.VoluntariosDbHelper;

public class ModificaFormulario extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private VoluntariosDbHelper vDbHelper;
    TextView coorX, coorY, PasswordModificar;
    Button BtnGenerar;
    private String texto;

    private UserAppDbHelper mHelper;
    private LocationManager locManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        vDbHelper = new VoluntariosDbHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        PasswordModificar = (TextView) findViewById(R.id.voluntarioPasswordModificar);
        coorX = (TextView) findViewById(R.id.longProtectoraModifcar);
        coorY = (TextView) findViewById(R.id.latProtectoraModificar);
    }

public void Modificar(View view) {

    View parent = (View) view.getParent();
    final TextView nomProtTextView = (TextView) parent.findViewById(R.id.nombreProtectoraModificar);
    final TextView telProtTextView = (TextView) parent.findViewById(R.id.telefonoProtectoraModificar);
    final TextView mailProtTextView = (TextView) parent.findViewById(R.id.mailProtectoraModificar);
    final TextView lattaskTextView = (TextView) parent.findViewById(R.id.latProtectoraModificar);
    final TextView longTextView = (TextView) parent.findViewById(R.id.longProtectoraModifcar);

    AlertDialog.Builder builder = new AlertDialog.Builder(this);

    builder.setTitle("Va a modificar al Voluntario");
    builder.setMessage("¿Desea hacerlo?");
    builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String protectora = String.valueOf(nomProtTextView.getText());
            String Password = String.valueOf(PasswordModificar.getText());
            int telefono = Integer.valueOf("" + telProtTextView.getText());
            String mail = String.valueOf(mailProtTextView.getText());
            float latitud = Float.valueOf("" + lattaskTextView.getText());
            float longitud = Float.valueOf("" + longTextView.getText());
            SQLiteDatabase db = vDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(VoluntariosContract.TaskEntry.NOMBRE_USUARIO, protectora);
            values.put(VoluntariosContract.TaskEntry.PASSWORD_USUARIO, Password);
            values.put(VoluntariosContract.TaskEntry.LONGITUD, longitud);
            values.put(VoluntariosContract.TaskEntry.LATITUD, latitud);
            values.put(VoluntariosContract.TaskEntry.TLFN, telefono);
            values.put(VoluntariosContract.TaskEntry.CORREO, mail);
            db.update(VoluntariosContract.TaskEntry.TABLE,
                    values,
                    VoluntariosContract.TaskEntry.NOMBRE_USUARIO + " = ?",
                    new String[]{texto});
            db.close();

        }
    }).setNegativeButton("Cancelar", null);
    AlertDialog dialog = builder
            .create();
    dialog.show();
}

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, MenuAdmin.class);
        startActivity(cambioUs);
    }
    /*public void Generar(View view) {


        //Generar coordenadas
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

    }*/
}