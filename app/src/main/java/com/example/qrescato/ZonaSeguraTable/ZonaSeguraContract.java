package com.example.qrescato.ZonaSeguraTable;

import android.provider.BaseColumns;

public class ZonaSeguraContract {

    public static final String DB_NAME = "com.example.qrescato.qrastreo";
    public static final int DB_VERSION = 5;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "zonasegura";

        public static final String NOMBRE = "Nombre";
        public static final String CORREO = "Correo";
        public static final String TLFN = "TelefonoZS";
        public static final String LONGITUD = "LongZS";
        public static final String LATITUD = "LatZS";
    }
}
