package com.example.qrescato;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.qrescato.UsersTable.UserAppDbHelper;
import com.example.qrescato.UsersTable.UsersAppContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraDbHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsPersonalizado extends AppCompatActivity{

    private GoogleMap mMap;

    private LocationManager locationmanager;
    private LocationListener locationListener;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_personalizado);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MapsPersonalizado.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MapsPersonalizado.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
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
                if(location != null ){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {

                            LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latlng).title("Nombre usuario");

                            //Marcadores de Protectoras
                           /* LatLng latlng1 = new LatLng(40.387374,-3.728961);
                            MarkerOptions Protectora1 = new MarkerOptions().position(latlng1).title("Zarpas y Colmillos \n 648171717\n contacto@zarpasycolmillos.es");
                            LatLng latlng2 = new LatLng(40.3810592,-3.7326006);
                            MarkerOptions Protectora2 = new MarkerOptions().position(latlng2).title("PROA \n 91 547 19 92");
                            LatLng latlng3 = new LatLng(40.464177,-3.717193);
                            MarkerOptions Protectora3 = new MarkerOptions().position(latlng3).title("Sociedad Protectora de Animales y Plantas de Madrid \n 91 311 91 33");
                            LatLng latlng4 = new LatLng(40.674798,-3.4748668);
                            MarkerOptions Protectora4 = new MarkerOptions().position(latlng4).title("ANAA Protectora de animales\n 91 667 20 36\n anaa@anaaweb.org");
                            LatLng latlng5 = new LatLng(40.642523,-3.174118);
                            MarkerOptions Protectora5 = new MarkerOptions().position(latlng5).title("La camada\n 666 777 51 41\n lacamadaguada@hotmail.com");
                            LatLng latlng6 = new LatLng(40.540471,-3.397807);
                            MarkerOptions Protectora6 = new MarkerOptions().position(latlng6).title("ALBA\n 609 29 19 30\n");*/

                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,100));
                            googleMap.addMarker(options);
                            cargarMapaZonasSeguras(googleMap);
                            /*googleMap.addMarker(Protectora1);
                            googleMap.addMarker(Protectora2);
                            googleMap.addMarker(Protectora3);
                            googleMap.addMarker(Protectora4);
                            googleMap.addMarker(Protectora5);
                            googleMap.addMarker(Protectora6);*/                       }
                    });
                }
            }
        });
    }

    private void cargarMapaZonasSeguras(GoogleMap googleMap){
        ZonaSeguraDbHelper mHelper = new ZonaSeguraDbHelper(this);

        SQLiteDatabase db2 = mHelper.getReadableDatabase();
        String[] args = new String[]{};
        Cursor cursor = db2.rawQuery("Select * from zonasegura" , null);

        while (cursor.moveToNext()) {

            String nombreProtectora = cursor.getString(
                    (Integer) cursor.getColumnIndex(ZonaSeguraContract.TaskEntry.NOMBRE)
            );
            String mailProtectora = cursor.getString(
                    (Integer) cursor.getColumnIndex(ZonaSeguraContract.TaskEntry.CORREO)
            );
            Float latitudProtectora = cursor.getFloat(
                    (Integer) cursor.getColumnIndex(ZonaSeguraContract.TaskEntry.LATITUD)
            );
            Float longitudProtectora = cursor.getFloat(
                    (Integer) cursor.getColumnIndex(ZonaSeguraContract.TaskEntry.LONGITUD)
            );
            int telefonoProtectora = cursor.getInt(
                    (Integer) cursor.getColumnIndex(ZonaSeguraContract.TaskEntry.TLFN)
            );

            LatLng latlng = new LatLng(latitudProtectora, longitudProtectora);
            MarkerOptions Protectora = new MarkerOptions().position(latlng).title(nombreProtectora +" \n " + telefonoProtectora + "\n " +  mailProtectora);
            googleMap.addMarker(Protectora);
        }
        cursor.close();
        db2.close();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 44){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
        }
    }
}
