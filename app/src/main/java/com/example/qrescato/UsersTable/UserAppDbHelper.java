package com.example.qrescato.UsersTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserAppDbHelper extends SQLiteOpenHelper{
        public UserAppDbHelper(Context context) {
            super(context, UsersAppContract.DB_NAME, null, UsersAppContract.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + UsersAppContract.TaskEntry.TABLE + " ( " +
                    UsersAppContract.TaskEntry.CORREO + " TEXT NOT NULL PRIMARY KEY , " +
                    UsersAppContract.TaskEntry.PASSWORD_USUARIO + " TEXT NOT NULL, " +
                    UsersAppContract.TaskEntry.NOMBRE_USUARIO + " TEXT NOT NULL);";

            db.execSQL(createTable);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + UsersAppContract.TaskEntry.TABLE);
            this.onCreate(db);
        }

}
