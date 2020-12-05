package com.example.qrescato.UsersTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qrescato.UsersTable.UsersAppContract;
import com.example.qrescato.VoluntariosTable.VoluntariosContract;
import com.example.qrescato.ZonaSeguraTable.ZonaSeguraContract;


public class UserAppDbHelper extends SQLiteOpenHelper{
        public UserAppDbHelper(Context context) {
            super(context, UsersAppContract.DB_NAME, null, UsersAppContract.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE IF NOT EXISTS " + UsersAppContract.TaskEntry.TABLE + " ( " +
                    UsersAppContract.TaskEntry.NOMBRE_USUARIO+ " TEXT NOT NULL PRIMARY KEY, " +
                    UsersAppContract.TaskEntry.PASSWORD_USUARIO + " TEXT NOT NULL, " +
                    UsersAppContract.TaskEntry.CORREO  + " TEXT NOT NULL);";

            String createTableZonaSegura = "CREATE TABLE IF NOT EXISTS " + ZonaSeguraContract.TaskEntry.TABLE + " ( " +
                    ZonaSeguraContract.TaskEntry.NOMBRE + " TEXT NOT NULL PRIMARY KEY, "+
                    ZonaSeguraContract.TaskEntry.CORREO + " TEXT NOT NULL, "+
                    ZonaSeguraContract.TaskEntry.LONGITUD + " FLOAT NOT NULL, "+
                    ZonaSeguraContract.TaskEntry.LATITUD + " FLOAT NOT NULL, "+
                    ZonaSeguraContract.TaskEntry.TLFN + " NUMBER NOT NULL);";

            String createTableVoluntarios ="CREATE TABLE IF NOT EXISTS " + VoluntariosContract.TaskEntry.TABLE + " ( " +
                    VoluntariosContract.TaskEntry.NOMBRE_USUARIO + " TEXT NOT NULL PRIMARY KEY , " +
                    VoluntariosContract.TaskEntry.PASSWORD_USUARIO + " TEXT NOT NULL , " +
                    VoluntariosContract.TaskEntry.CORREO + " TEXT NOT NULL , " +
                    VoluntariosContract.TaskEntry.TLFN + " TEXT NOT NULL , " +
                    VoluntariosContract.TaskEntry.LONGITUD + " FLOAT NOT NULL , " +
                    VoluntariosContract.TaskEntry.LATITUD + " FLOAT NOT NULL);";


            db.execSQL(createTableZonaSegura);
            db.execSQL(createTable);
            db.execSQL(createTableVoluntarios);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + UsersAppContract.TaskEntry.TABLE);
            onCreate(db);
        }

}
