package com.example.qrescato;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashQRescato extends AppCompatActivity implements Animation.AnimationListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_qrescato);

        ImageView planeta = (ImageView) findViewById(R.id.imgtierragif);
        ImageView huellas = (ImageView) findViewById(R.id.gifcarga);
        ImageView Q = (ImageView) findViewById(R.id.q);
        ImageView rr = (ImageView) findViewById(R.id.r);
        ImageView astreo = (ImageView) findViewById(R.id.astreo);

        Animation animation1Desvancer = AnimationUtils.loadAnimation(this,R.anim.animdesvanecer);
        Q.startAnimation(animation1Desvancer);

        Animation animation2aparecerhuellas = AnimationUtils.loadAnimation(this,R.anim.animalpha);
        huellas.startAnimation(animation2aparecerhuellas);

        Animation animation2aparecer = AnimationUtils.loadAnimation(this,R.anim.animalpha);
        planeta.startAnimation(animation2aparecer);

        Animation animationR = AnimationUtils.loadAnimation(this,R.anim.animalpha);
        rr.startAnimation(animationR);

        Animation animationAstreo = AnimationUtils.loadAnimation(this,R.anim.animalpha);
        astreo.startAnimation(animationAstreo);


    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent transicion = new Intent(this, MenuRastreo.class);
        //startActivity(transicion);
        //finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
