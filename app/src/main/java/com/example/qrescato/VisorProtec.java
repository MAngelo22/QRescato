package com.example.qrescato;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrescato.ZonaSeguraTable.ZonaSeguraContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraDbHelper;

import java.util.ArrayList;
import java.util.List;

public class VisorProtec extends AppCompatActivity{
        private ZonaSeguraDbHelper zDbHelper;
        private ListView listadoProtectoras;
        private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_protec);

        zDbHelper = new ZonaSeguraDbHelper(this);
        listadoProtectoras = (ListView) findViewById(R.id.listado_de_protectoras);

        updateUI();
    }

    public void deleteProtectora(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.protec_title);
        String task = String.valueOf(taskTextView.getText());
        SQLiteDatabase db = zDbHelper.getWritableDatabase();
        db.delete(ZonaSeguraContract.TaskEntry.TABLE,
                ZonaSeguraContract.TaskEntry.NOMBRE + " = ?",
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
        SQLiteDatabase db = zDbHelper.getReadableDatabase();
        Cursor cursor = db.query(ZonaSeguraContract.TaskEntry.TABLE,
                new String[]{ZonaSeguraContract.TaskEntry.NOMBRE, ZonaSeguraContract.TaskEntry.TLFN},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(ZonaSeguraContract.TaskEntry.NOMBRE);
            listaProtectoras.add(cursor.getString(idx));
        }

        if (adapter == null) {
            adapter = new ArrayAdapter<>(this,
                    R.layout.activity_lista_protectoras,
                    R.id.protec_title,
                    listaProtectoras);
            listadoProtectoras.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(listaProtectoras);
            adapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }

    public void cargarFormuModProtec(View view) {
        TextView protectoraNombre = (TextView) findViewById(R.id.protec_title);
        String protec = String.valueOf(protectoraNombre.getText());
        Intent cambioUs = new Intent(this, ModificaFormularioProtec.class);
        cambioUs.putExtra("protectora", protec);
        startActivity(cambioUs);
    }

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, IniReader.class);
        startActivity(cambioUs);
    }
}
