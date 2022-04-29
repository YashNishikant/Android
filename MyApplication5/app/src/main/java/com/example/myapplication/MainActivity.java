package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioGroup rpsChoice;

    String choice = "";
    String CPUchoice = "";

    Button play;

    ImageView i;

    int win;

    TextView windisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.volumegroup);
        rpsChoice = findViewById(R.id.radiogroup);
        play = findViewById(R.id.button);
        i = findViewById(R.id.imageView);
        windisplay = findViewById(R.id.textView4);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int random = (int)(Math.random()*3)+1;

                if(random == 1){
                    CPUchoice = "r";
                    i.setImageResource(R.drawable.rock);
                }
                if(random == 2){
                    CPUchoice = "p";
                    i.setImageResource(R.drawable.paper);
                }
                if(random == 3){
                    CPUchoice = "s";
                    i.setImageResource(R.drawable.scissors);
                }

                checkforwin("r", "p", 0);
                checkforwin("r", "s", 1);
                checkforwin("p", "r", 1);
                checkforwin("p", "s", 0);
                checkforwin("s", "r", 0);
                checkforwin("s", "p", 1);

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.tooloudlmao){
                    Toast.makeText(MainActivity.this, "TOO LOUD", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rpsChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.r){
                    choice = "r";
                }
                if(i == R.id.p){
                    choice = "p";
                }
                if(i == R.id.s){
                    choice = "s";
                }
            }
        });
    }

    public void checkforwin(String mychoice, String cpuChoice, int b){

        if(choice.equals(mychoice) && CPUchoice.equals(cpuChoice) && !(choice.equals(CPUchoice))){
            win = b;
        }
        if (choice.equals(CPUchoice)){
            win = 3;
        }

        System.out.println(win);

        if(win == 1){
            windisplay.setText("YOU WIN!");
        }
        else if (win == 0){
            windisplay.setText("YOU LOSE");
        }
        else if (win == 3){
            windisplay.setText("TIE!!!");
        }
    }

}