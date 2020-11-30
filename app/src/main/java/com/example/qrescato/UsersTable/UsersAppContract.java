package com.example.qrescato.UsersTable;

import android.provider.BaseColumns;

public class UsersAppContract {

        public static final String DB_NAME = "com.example.qrescato.qrastreo";
        public static final int DB_VERSION = 1;

        public class TaskEntry implements BaseColumns {
            public static final String TABLE = "usuarios";

            public static final String NOMBRE_USUARIO = "username";
            public static final String PASSWORD_USUARIO = "password";
            public static final String CORREO = "Correo";//PK

        }
}
