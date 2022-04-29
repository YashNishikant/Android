package com.example.widgetsv3;

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

    Switch aSwitch;
    EditText editText;
    TextView displaycolor;
    Button testbutton;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch1);
        editText = findViewById(R.id.coloredit);
        displaycolor = findViewById(R.id.colortext);
        testbutton = findViewById(R.id.button);
        seekBar = findViewById(R.id.seekBar);

        seekBar.setEnabled(false);

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

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equalsIgnoreCase("red")){
                    displaycolor.setTextColor(Color.RED);
                }
                else if(charSequence.toString().equalsIgnoreCase("blue")){
                    displaycolor.setTextColor(Color.BLUE);
                }
                else if(charSequence.toString().equalsIgnoreCase("green")){
                    displaycolor.setTextColor(Color.GREEN);
                }
                else{
                    displaycolor.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(i > 0) {
                    testbutton.setScaleX(i);
                }
                else{
                    testbutton.setScaleX(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}