package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.g);
        imageView = findViewById(R.id.I);

        imageView.setImageResource(R.drawable.gauntlet);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.st){
                    imageView.setImageResource(R.drawable.stormbreaker);
                    Toast.makeText(MainActivity.this, "You Chose Stormbreaker", Toast.LENGTH_SHORT).show();
                }
                if(i == R.id.sh){
                    imageView.setImageResource(R.drawable.capshield);
                    Toast.makeText(MainActivity.this, "You Chose Shield", Toast.LENGTH_SHORT).show();
                }
                if(i == R.id.r){
                    imageView.setImageResource(R.drawable.repulsors);
                    Toast.makeText(MainActivity.this, "You Chose Repulsors", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}