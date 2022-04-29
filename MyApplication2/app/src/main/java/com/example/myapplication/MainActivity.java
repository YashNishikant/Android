package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button TL;
    Button BL;
    Button TR;
    Button BR;
    Button M;

    TextView txtTL;
    TextView txtBL;
    TextView txtTR;
    TextView txtBR;
    TextView txtM;

    boolean tl = false;
    boolean tr = false;
    boolean bl = false;
    boolean br = false;
    boolean m = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TL = findViewById(R.id.TL);
        BL = findViewById(R.id.BL);
        TR = findViewById(R.id.TR);
        BR = findViewById(R.id.BR);
        M = findViewById(R.id.MidButton);

        txtTL = findViewById(R.id.txtTL);
        txtBL = findViewById(R.id.txtBL);
        txtTR = findViewById(R.id.txtTR);
        txtBR = findViewById(R.id.txtBR);
        txtM = findViewById(R.id.txtM);

        if(tl)
            txtTL.setText("ON");
        else
            txtTL.setText("OFF");

        if(tr)
            txtTR.setText("ON");
        else
            txtTR.setText("OFF");

        if(bl)
            txtBL.setText("ON");
        else
            txtBL.setText("OFF");

        if(br)
            txtBR.setText("ON");
        else
            txtBR.setText("OFF");

        if(m)
            txtM.setText("ON");
        else
            txtM.setText("OFF");

    }

    public void onclickTL(View v){

        tl = !tl;
        if(tl)
            txtTL.setText("ON");
        else
            txtTL.setText("OFF");
    }

    public void onclickTR(View v){

        tr = !tr;
        if(tr)
            txtTR.setText("ON");
        else
            txtTR.setText("OFF");
    }

    public void onclickBL(View v){

        bl = !bl;

        if(bl)
            txtBL.setText("ON");
        else
            txtBL.setText("OFF");
    }

    public void onclickBR(View v){
        br = !br;

        if(br)
            txtBR.setText("ON");
        else
            txtBR.setText("OFF");
    }

    public void onclickM(View v){
        m = !m;

        if(m)
            txtM.setText("ON");
        else
            txtM.setText("OFF");
    }

}