package com.example.qrescato;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class CamaraQR extends AppCompatActivity {

    private Button btnScanner;
    private TextView tvBArCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScanner = findViewById(R.id.button);
        tvBArCode = findViewById(R.id.texto);

        btnScanner.setOnClickListener(mOnClickListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null)
            if (result.getContents() != null){
                tvBArCode.setText("El codigo es:\n" + result.getContents());
            }else{
                tvBArCode.setText("Error al escanear");
            }
    }

    private View.OnClickListener mOnClickListener;

    {
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        new IntentIntegrator(CamaraQR.this).initiateScan();
                        break;
                }
            }
        };
    }

}