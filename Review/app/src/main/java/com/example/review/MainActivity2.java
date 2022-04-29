package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button returnback;
    ImageView image;

    static final String key = "876143257864354";

    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        image = findViewById(R.id.imageView);
        returnback = findViewById(R.id.switchactivity);
        s = findViewById(R.id.spinner2);

        ArrayList<String> List = new ArrayList();
        List.add("Iron Man");
        List.add("Captain America");
        List.add("Thor");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,List);
        s.setAdapter(adapter);

        Toast.makeText(MainActivity2.this, getIntent().getStringExtra(MainActivity.key), Toast.LENGTH_SHORT).show();

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(List.get(i).toString().equals("Iron Man")){
                    image.setImageResource(R.drawable.repulsors);
                }
                if(List.get(i).toString().equals("Captain America")){
                    image.setImageResource(R.drawable.capshield);
                }
                if(List.get(i).toString().equals("Thor")){
                    image.setImageResource(R.drawable.stormbreaker);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

                returnback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent loadprevious = new Intent(MainActivity2.this, MainActivity.class);
                        loadprevious.putExtra(key, "From Activity 2");
                        setResult(RESULT_OK, loadprevious);

                        finish();

                    }
                });

    }
}