package com.example.qrescato;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrescato.VoluntariosTable.VoluntariosContract;
import com.example.qrescato.VoluntariosTable.VoluntariosDbHelper;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraDbHelper;

import java.util.ArrayList;

public class VisorUsers extends AppCompatActivity{
    private VoluntariosDbHelper vDbHelper;
    private ListView listadoVoluntarios;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        vDbHelper = new VoluntariosDbHelper(this);
        listadoVoluntarios = (ListView) findViewById(R.id.listado_de_voluntarios);

        updateUI();
    }

    public void deleteVoluntario(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.Vol_title);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = vDbHelper.getWritableDatabase();
        db.delete(VoluntariosContract.TaskEntry.TABLE,
                VoluntariosContract.TaskEntry.NOMBRE_USUARIO + " = ?",
                new String[]{task});

        //Toast cuando acabas una tarea.
        LayoutInflater inflater = getLayoutInflater();
        View view2 = inflater.inflate(R.layout.toastdelete, null);
        Toast toastacabar = new Toast (this);
        toastacabar.setDuration(Toast.LENGTH_LONG);
        toastacabar.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM, 0, 0);
        toastacabar.setView(view2);
        toastacabar.show();

        db.close();
        updateUI();
    }


    private void updateUI() {
        ArrayList<String> listaProtectoras = new ArrayList<>();
        SQLiteDatabase db = vDbHelper.getReadableDatabase();
        Cursor cursor = db.query(VoluntariosContract.TaskEntry.TABLE,
                new String[]{VoluntariosContract.TaskEntry.NOMBRE_USUARIO},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(VoluntariosContract.TaskEntry.NOMBRE_USUARIO);
            listaProtectoras.add(cursor.getString(idx));
        }

        if (adapter == null) {
            adapter = new ArrayAdapter<>(this,
                    R.layout.activity_lista_voluntarios,
                    R.id.Vol_title,
                    listaProtectoras);
            listadoVoluntarios.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(listaProtectoras);
            adapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    public void cargarFormuModVolun(View view) {
        TextView VolunNombre = (TextView) findViewById(R.id.Vol_title);
        String volun = String.valueOf(VolunNombre.getText());
        Intent cambioUs = new Intent(this, ModificaFormulario.class);
        cambioUs.putExtra("protectora", volun);
        startActivity(cambioUs);
    }
    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, IniReader.class);
        startActivity(cambioUs);
    }
}
