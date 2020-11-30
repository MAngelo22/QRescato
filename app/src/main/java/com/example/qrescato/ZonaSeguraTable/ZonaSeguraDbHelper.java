package com.example.qrescato.ZonaSeguraTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qrescato.UsersTable.UsersAppContract;

public class ZonaSeguraDbHelper extends SQLiteOpenHelper {
    public ZonaSeguraDbHelper(Context context) {
        super(context, ZonaSeguraContract.DB_NAME, null, UsersAppContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ZonaSeguraContract.TaskEntry.TABLE + " ( " +
                ZonaSeguraContract.TaskEntry.NOMBRE + " TEXT NOT NULL PRIMARY KEY , "+
                ZonaSeguraContract.TaskEntry.CORREO + "TEXT NOT NULL  , "+
                ZonaSeguraContract.TaskEntry.LONGITUD + "FLOAT NOT NULL  , "+
                ZonaSeguraContract.TaskEntry.LATITUD + "FLOAT NOT NULL  , "+
                ZonaSeguraContract.TaskEntry.TLFN + "NUMBER NOT NULL);";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ZonaSeguraContract.TaskEntry.TABLE);
        onCreate(db);
    }
}
