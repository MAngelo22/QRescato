package com.example.qrescato;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.qrescato.UsersTable.UserAppDbHelper;
import com.example.qrescato.UsersTable.UsersAppContract;
import com.google.android.material.textfield.TextInputEditText;

public class PasswordChange extends AppCompatActivity implements Animation.AnimationListener  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        final TextView textName = (TextView) findViewById(R.id.textName);
        final TextView textPassword = (TextView) findViewById(R.id.textPas);

        //Aqui hago un onclick escuchando a la funcion de vaciar
        textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textName);
            }
        });
        textPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {
                vaciar(textPassword);
            }
        });

        //getSupportActionBar().hide();
    }

    public void vaciar(TextView tv) {
        tv.setText("");
    }

    public void login(View view) {
        //Vacia las cajas de texto
        TextInputEditText usuario = (TextInputEditText) findViewById(R.id.textName);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.textPas);

        UserAppDbHelper mHelper = new UserAppDbHelper(this);

        SQLiteDatabase db2 = mHelper.getReadableDatabase();
        String[] args = new String[]{usuario.getText().toString()};
        Cursor cursor = db2.query(UsersAppContract.TaskEntry.TABLE,// EN ESTA TABLA
                new String[]{
                        UsersAppContract.TaskEntry.NOMBRE_USUARIO,// BUSCAME ESTOS CAMPOS/COLUMNAS
                        UsersAppContract.TaskEntry.PASSWORD_USUARIO
                },
                "username=?", //DONDE username SEA IGUAL A
                args,// EL STRING QUE HEMOS METIDO ARRIBA EN args
                null, // NO PONGO GROUP
                null, null); // NO PONGO ORDER BY

        // SELECT USERNAME, PASSWORD WHERE USERNAME = "PEPE"

        // El metodo moveToNext() entra en funcionamiento cada vez que tu consulta o query, devuelve un resultado
        // Cuando un usuario no esta en la base de datos, no va a hacer nada, ya que la logica de dentro solo actuara
        // Cuando se devuelva algo de tu select

        if (cursor.getCount() == 0) {
            Toast toastUsu = Toast.makeText(this, "Comprueba nombre y contraseña", Toast.LENGTH_LONG);
            toastUsu.show();
            usuario.getText().toString().replace("", "");
            password.getText().toString().replace("", "");
        }
        while (cursor.moveToNext()) {

            String passwordParaHacerMatch = cursor.getString(
                    (Integer) cursor.getColumnIndex(UsersAppContract.TaskEntry.PASSWORD_USUARIO)
            );

            if (password.getText().toString().equals(passwordParaHacerMatch)) {
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
            } else {
                Toast toastUsu = Toast.makeText(this, "Comprueba nombre y contraseña", Toast.LENGTH_LONG);
                toastUsu.show();
                usuario.getText().toString().replace("", "");
                password.getText().toString().replace("", "");
            }
        }
        cursor.close();
        db2.close();
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
