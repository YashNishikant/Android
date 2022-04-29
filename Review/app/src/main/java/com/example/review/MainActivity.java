package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button switchactivity;
    ImageView image;
    RadioGroup radioGroup;
    EditText e;
    static final String key = "9087623659873245";
    final int loadcode = 123321;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.rg);
        switchactivity = findViewById(R.id.switchactivity);
        image = findViewById(R.id.imageView2);
        e = findViewById(R.id.edit);
        t = findViewById(R.id.color);

        switchactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loadActivity = new Intent(MainActivity.this, MainActivity2.class);
                loadActivity.putExtra(key, "From Activity 1");
                startActivityForResult(loadActivity, loadcode);
            }
        });

        e.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equalsIgnoreCase("RED")){
                    t.setTextColor(Color.RED);
                }
                if(charSequence.toString().equalsIgnoreCase("BLUE")){
                    t.setTextColor(Color.BLUE);
                }
                if(charSequence.toString().equalsIgnoreCase("GREEN")){
                    t.setTextColor(Color.GREEN);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radioButton){
                    image.setImageResource(R.drawable.repulsors);
                }
                if(i == R.id.radioButton2){
                    image.setImageResource(R.drawable.stormbreaker);
                }
                if(i == R.id.radioButton3){
                    image.setImageResource(R.drawable.capshield);
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == loadcode ){
            Toast.makeText(MainActivity.this, data.getStringExtra(MainActivity2.key), Toast.LENGTH_SHORT).show();
        }

    }

}