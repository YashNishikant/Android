package com.example.intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button b;
    Button u;
    TextView t;
    static final String key = "08763954bwm780435w6b9vw035849p";
    static final int loadcode = 4352;

    Spinner s;

    RadioGroup r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = findViewById(R.id.textView3);
        b = findViewById(R.id.THISBUTTON);
        r = findViewById(R.id.rg);
        u = findViewById(R.id.button3);
        s = findViewById(R.id.spinner);

        ArrayList<String> items = new ArrayList();
        items.add("Neotheater");
        items.add("OK Orchestra");
        items.add("The Click");

        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_expandable_list_item_1, items);

        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra(key, "From Activity One");
                //startActivityForResult(intent, loadcode);
                startActivity(intent);
            }
        });

        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButton) {
                    t.setText("a");
                }
                if(i == R.id.radioButton2) {
                    t.setText("b");
                }
                if(i == R.id.radioButton3) {
                    t.setText("c");
                }

            }
        });

        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Told you, this is useless...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == loadcode && data != null){
            t.setText(data.getStringExtra(MainActivity2.key));
        }

    }
}