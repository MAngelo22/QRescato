package com.example.qrescato.VoluntariosTable;

import android.provider.BaseColumns;

public class VoluntariosContract {

    public static final String DB_NAME = "com.example.qrescato.qrastreo";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "voluntarios";

        public static final String NOMBRE_USUARIO = "UserName";
        public static final String PASSWORD_USUARIO = "Password";
        public static final String CORREO = "Correo";
        public static final String TLFN = "TelefonoV";
        public static final String LONGITUD = "LongV";
        public static final String LATITUD = "LatV";
    }
}
