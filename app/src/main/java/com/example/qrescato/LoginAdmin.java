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

public class LoginAdmin extends AppCompatActivity implements Animation.AnimationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginadmin);

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

        if (usuario.getText().toString().equals("Admin") && password.getText().toString().equals("123")) {
            Intent intent = new Intent(this, MenuAdmin.class);
            startActivity(intent);

        } else {
                Toast toastUsu = Toast.makeText(this, "Comprueba nombre y contraseña", Toast.LENGTH_LONG);
                toastUsu.show();
                usuario.getText().toString().replace("", "");
                password.getText().toString().replace("", "");
            }
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

    //La funcion de volver al menu anterior
    public void volver(View view) {
        Intent cambioUs = new Intent(this, MenuRescato.class);
        startActivity(cambioUs);
    }
}
