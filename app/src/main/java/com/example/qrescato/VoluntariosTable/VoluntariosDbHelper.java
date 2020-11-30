package com.example.qrescato.VoluntariosTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qrescato.UsersTable.UsersAppContract;

public class VoluntariosDbHelper extends SQLiteOpenHelper {
    public VoluntariosDbHelper(Context context) {
        super(context, UsersAppContract.DB_NAME, null, UsersAppContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + VoluntariosContract.TaskEntry.TABLE + " ( " +
                VoluntariosContract.TaskEntry.NOMBRE_USUARIO + " TEXT NOT NULL PRIMARY KEY , " +
                VoluntariosContract.TaskEntry.PASSWORD_USUARIO + " TEXT NOT NULL , " +
                VoluntariosContract.TaskEntry.CORREO + " TEXT NOT NULL , " +
                VoluntariosContract.TaskEntry.TLFN + " TEXT NOT NULL , " +
                VoluntariosContract.TaskEntry.LONGITUD + " FLOAT NOT NULL , " +
                VoluntariosContract.TaskEntry.LATITUD + " FLOAT NOT NULL);";


        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VoluntariosContract.TaskEntry.TABLE);
        onCreate(db);
    }
}
