package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Button b;
    static final String key = "6473fj80q97346q98g0v7890346vqjn";
    static final int loadcode = 9678;
    TextView t;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        b = findViewById(R.id.button2);
        t = findViewById(R.id.textView4);
        et = findViewById(R.id.editTextTextPersonName);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendback = new Intent(MainActivity2.this, MainActivity.class);
                sendback.putExtra(key, "From Activity 2");
                setResult(RESULT_OK, sendback);
                finish();
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                t.setText(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}