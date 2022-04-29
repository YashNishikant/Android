package com.example.widgetsv2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText edittext;
    TextView t;
    SeekBar seekBar;
    Button tb;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittext = findViewById(R.id.editTextTextPersonName);
        t = findViewById(R.id.textView2);
        seekBar = findViewById(R.id.seekBar);
        tb = findViewById(R.id.testbutton);
        aSwitch = findViewById(R.id.switch1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b){
                    seekBar.setEnabled(false);
                }
                else{
                    seekBar.setEnabled(true);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i == 0){
                    tb.setScaleX(1);
                }
                else{
                    tb.setScaleX(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().equalsIgnoreCase("red")){
                    t.setTextColor(Color.RED);
                }
                else if(charSequence.toString().equalsIgnoreCase("blue")){
                    t.setTextColor(Color.BLUE);
                }
                else if(charSequence.toString().equalsIgnoreCase("green")){
                    t.setTextColor(Color.GREEN);
                }
                else{
                    t.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}