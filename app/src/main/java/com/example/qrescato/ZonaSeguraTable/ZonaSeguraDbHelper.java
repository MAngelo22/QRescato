package com.example.qrescato.ZonaSeguraTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qrescato.ZonaSeguraTable.ZonaSeguraContract;

public class ZonaSeguraDbHelper extends SQLiteOpenHelper {
    public ZonaSeguraDbHelper(Context context) {
        super(context, ZonaSeguraContract.DB_NAME, null, ZonaSeguraContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ZonaSeguraContract.TaskEntry.TABLE + " ( " +
                ZonaSeguraContract.TaskEntry.NOMBRE + " TEXT NOT NULL PRIMARY KEY, "+
                ZonaSeguraContract.TaskEntry.CORREO + " TEXT NOT NULL, "+
                ZonaSeguraContract.TaskEntry.LONGITUD + " FLOAT NOT NULL, "+
                ZonaSeguraContract.TaskEntry.LATITUD + " FLOAT NOT NULL, "+
                ZonaSeguraContract.TaskEntry.TLFN + " NUMBER NOT NULL);";

        db.execSQL(createTable);
//        db.execSQL("insert into " + ZonaSeguraContract.TaskEntry.TABLE + "(Nombre, Correo, TelefonoZS, LongZS, LatZS) " +
//                "VALUES ('Zarpas y Colmillos','contacto@zarpasycolmillos.es', 648171717,-3.728961, 40.387374)," +
//                "('PROA', 'proa@noseque.com', 915471992, -3.7326006, 40.3810592),"+
//                "('Sociedad Protectora de Animales y Plantas de Madrid', 'proa@noseque.com', 913119133, -3.717193, 40.464177),"+
//                "('ANAA Protectora de animales', 'anaa@anaaweb.org', 916672036, -3.4748668, 40.674798),"+
//                "('La camada', 'lacamadaguada@hotmail.com', 6667775141, -3.174118, 40.642523),"+
//                "('ALBA', 'proa@noseque.com', 609291930, -3.397807, 40.540471)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ZonaSeguraContract.TaskEntry.TABLE);
        onCreate(db);
    }
}
