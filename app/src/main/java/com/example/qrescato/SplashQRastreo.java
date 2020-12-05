package com.example.qrescato;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class SplashQRastreo extends AppCompatActivity implements Animation.AnimationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_qrastreo);

        Integer yourValue = 150;

        ImageView qrr = findViewById(R.id.qr);
        ImageView tierra = findViewById(R.id.lupa);
        ImageView Q = findViewById(R.id.q);
        ImageView rr = findViewById(R.id.r);
        ImageView astreo = findViewById(R.id.astreo);
        ImageView imageView= findViewById(R.id.fondodib);

        Animation animation1Desvancer = AnimationUtils.loadAnimation(this,R.anim.animdesvanecer);
        Q.startAnimation(animation1Desvancer);



        Animation animationR = AnimationUtils.loadAnimation(this,R.anim.animalpha);
        rr.startAnimation(animationR);
        tierra.startAnimation(animationR);
        qrr.startAnimation(animationR);
        astreo.startAnimation(animationR);

        animationR.setAnimationListener(this);

        //imageView.setAlpha(yourValue); //  some value 0-255 where 0 is fully transparent and 255 is fully opaque

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent transicion = new Intent(this, CamaraQR.class);
        startActivity(transicion);
        //finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
