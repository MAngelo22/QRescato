package com.example.qrescato;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        setContentView(R.layout.activity_reader_protec);

        zDbHelper = new ZonaSeguraDbHelper(this);
        listadoProtectoras = (ListView) findViewById(R.id.listadoProtectora);

        updateUI();
    }

   private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = zDbHelper.getReadableDatabase();
        Cursor cursor = db.query(ZonaSeguraContract.TaskEntry.TABLE,
                new String[]{ZonaSeguraContract.TaskEntry.NOMBRE, ZonaSeguraContract.TaskEntry.TLFN},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idx = cursor.getColumnIndex(ZonaSeguraContract.TaskEntry.NOMBRE);
            taskList.add(cursor.getString(idx));
        }

        if (zDbHelper == null) {
            zDbHelper = new ArrayAdapter<>(this,
                    R.layout.VISTA_GENERAL_LISTADO,
                    R.id.REPEAT_DE_LAS_PROTECTORAS,
                    taskList);
            listadoProtectoras.setAdapter(adapter);
        } else {
            adapter.clear();
            adapter.addAll(taskList);
            adapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }



    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, IniReader.class);
        startActivity(cambioUs);
    }
}
