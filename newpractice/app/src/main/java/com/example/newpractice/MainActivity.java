package com.example.newpractice;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView t;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        t = findViewById(R.id.itemview);
        b = findViewById(R.id.button);

        final ArrayList<String> list = new ArrayList();

        list.add("Power Stone");
        list.add("Space Stone");
        list.add("Reality Stone");
        list.add("Soul Stone");
        list.add("Time Stone");
        list.add("Mind Stone");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!list.isEmpty()) {
                    t.setText(list.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                t.setText("EMPTY");

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                spinner.setAdapter(null);
            }
        });

    }
}

