package com.example.practice2;

import static android.graphics.Color.rgb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button r;
    Button b;
    Button g;
    Button c;
    Button gr;
    Button m;

    LinearLayout l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r = findViewById(R.id.bRed);
        b = findViewById(R.id.bBlue);
        g = findViewById(R.id.bGreen);
        c = findViewById(R.id.bCyan);
        gr = findViewById(R.id.bGray);
        m = findViewById(R.id.bMagenta);
        l = findViewById(R.id.mainlayout);
    }

    public void red(View v){
        r.setTextColor(Color.RED);
        b.setTextColor(Color.RED);
        g.setTextColor(Color.RED);
        c.setTextColor(Color.RED);
        gr.setTextColor(Color.RED);
        m.setTextColor(Color.RED);
    }

    public void blue(View v){
        r.setTextColor(Color.BLUE);
        b.setTextColor(Color.BLUE);
        g.setTextColor(Color.BLUE);
        c.setTextColor(Color.BLUE);
        gr.setTextColor(Color.BLUE);
        m.setTextColor(Color.BLUE);
    }

    public void green(View v){
        r.setTextColor(Color.GREEN);
        b.setTextColor(Color.GREEN);
        g.setTextColor(Color.GREEN);
        c.setTextColor(Color.GREEN);
        gr.setTextColor(Color.GREEN);
        m.setTextColor(Color.GREEN);
    }

    public void cyan(View v){
        l.setBackgroundColor(Color.CYAN);
    }

    public void gray(View v){
        l.setBackgroundColor(Color.GRAY);
    }

    public void magenta(View v){
        l.setBackgroundColor(Color.MAGENTA);
    }

}