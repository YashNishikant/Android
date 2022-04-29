package com.example.images;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioButton r1;
    RadioButton r2;
    RadioButton r3;

    RadioButton v1;
    RadioButton v2;
    RadioButton v3;
    RadioButton v4;

    ImageView imageview;

    Button b;

    TextView winview;
    TextView score;

    int CPUint;
    String choice = "";
    String choiceCPU = "";

    int mywin = 0;
    int cpuWin = 0;

    boolean win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview = findViewById(R.id.imageView);

        r1 = findViewById(R.id.radioButton2);
        r2 = findViewById(R.id.radioButton3);
        r3 = findViewById(R.id.radioButton4);

        v1 = findViewById(R.id.radioButton9);
        v2 = findViewById(R.id.radioButton10);
        v3 = findViewById(R.id.radioButton11);
        v4 = findViewById(R.id.radioButton);

        winview = findViewById(R.id.textView3);
        score = findViewById(R.id.textView4);

        b = findViewById(R.id.button);

        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "VOLUME TOO LOUD", Toast.LENGTH_SHORT).show();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CPUint = (int)(Math.random()*3)+1;
                if(CPUint == 1){
                    choiceCPU = "r";
                    imageview.setImageResource(R.drawable.rock);
                }
                if(CPUint == 2){
                    choiceCPU = "p";
                    imageview.setImageResource(R.drawable.paper);
                }
                if(CPUint == 3){
                    choiceCPU = "s";
                    imageview.setImageResource(R.drawable.scissors);
                }

                wincheck("r", "p", true);
                wincheck("r", "s", false);
                wincheck("p", "r", false);
                wincheck("p", "s", true);
                wincheck("s", "r", true);
                wincheck("s", "p", false);

                if(win){
                    winview.setText("You Win!");
                    mywin++;
                    score.setText("You: " + mywin + " | CPU: " + cpuWin);
                }
                else{
                    winview.setText("You Lose");
                    cpuWin++;
                    score.setText("You: " + mywin + " | CPU: " + cpuWin);
                }
            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "r";
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "p";
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = "s";
            }
        });
    }

    public void wincheck(String cpu, String me, boolean setwin){
        if(choice.equals(me) && choiceCPU.equals(cpu)){
            win = setwin;
        }
        if(choice.equals(choiceCPU)){
            win = false;
        }
    }

}

