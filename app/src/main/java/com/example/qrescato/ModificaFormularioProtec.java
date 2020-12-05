package com.example.qrescato;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrescato.UsersTable.UserAppDbHelper;
import com.example.qrescato.UsersTable.UsersAppContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraDbHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ModificaFormularioProtec extends AppCompatActivity {

    private ZonaSeguraDbHelper zDbHelper;
    private ListView listadoProtectoras;
    private ArrayAdapter<String> adapter;
    private String texto;

    private UserAppDbHelper mHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        zDbHelper = new ZonaSeguraDbHelper(this);
        Intent datoCargado = getIntent();
        texto = datoCargado.getExtras().getString("protectora");
        System.out.println("1111111111111111111111111111111111111111111111111111111111" + texto);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_protec);
    }

    public void modificarProtectora(View view) {
        View parent = (View) view.getParent();
        final TextView nomProtTextView = (TextView) parent.findViewById(R.id.nombreProtectoraModificar);
        final TextView telProtTextView = (TextView) parent.findViewById(R.id.telefonoProtectoraModificar);
        final TextView mailProtTextView = (TextView) parent.findViewById(R.id.mailProtectoraModificar);
        final TextView lattaskTextView = (TextView) parent.findViewById(R.id.latProtectoraModificar);
        final TextView longTextView = (TextView) parent.findViewById(R.id.longProtectoraModifcar);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Va a modificar la protectora");
        builder.setMessage("¿Desea hacerlo?");
        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String protectora = String.valueOf(nomProtTextView.getText());
                int telefono = Integer.valueOf( "" + telProtTextView.getText());
                String mail = String.valueOf(mailProtTextView.getText());
                float latitud = Float.valueOf("" + lattaskTextView.getText());
                float longitud = Float.valueOf("" + longTextView.getText());
                SQLiteDatabase db = zDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(ZonaSeguraContract.TaskEntry.NOMBRE, protectora);
                values.put(ZonaSeguraContract.TaskEntry.LONGITUD, longitud);
                values.put(ZonaSeguraContract.TaskEntry.LATITUD, latitud);
                values.put(ZonaSeguraContract.TaskEntry.TLFN, telefono);
                values.put(ZonaSeguraContract.TaskEntry.CORREO, mail);
                db.update(ZonaSeguraContract.TaskEntry.TABLE,
                        values,
                        ZonaSeguraContract.TaskEntry.NOMBRE + " = ?",
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

}