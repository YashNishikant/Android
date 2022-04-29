package com.example.wack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final String intentkey = "ABCD";
    static final int loadcode = 1234;

    Button START;

    ImageView normal;
    ImageView power;
    ImageView trap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normal = findViewById(R.id.N);
        power = findViewById(R.id.P);
        trap = findViewById(R.id.T);

        normal.setImageResource(R.drawable.meme);
        power.setImageResource(R.drawable.powerup);
        trap.setImageResource(R.drawable.powerdown);

        START = findViewById(R.id.STARTBUTTON);

        START.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                intent.putExtra(intentkey, "Game Started");
                startActivity(intent);
            }
        });

    }
}
