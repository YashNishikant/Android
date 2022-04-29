package com.example.widgetpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Switch switch1;
    Switch switch2;
    Switch switch3;
    TextView colordisplay;
    EditText verification;
    Button verify;
    EditText database;
    Button check;
    SeekBar seekbar;
    TextView textsize;
    TextView displayverification;

    boolean checked1;
    boolean checked2;
    boolean checked3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);
        switch3 = findViewById(R.id.switch3);
        colordisplay = findViewById(R.id.colortext);
        verification = findViewById(R.id.editTextverification);
        verify = findViewById(R.id.buttonverify);
        database = findViewById(R.id.editTextdatabase);
        check = findViewById(R.id.buttondatabase);
        seekbar = findViewById(R.id.seekBar);
        textsize = findViewById(R.id.textsize);
        displayverification = findViewById(R.id.verificationtext);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                textsize.setScaleX(i+1);
                textsize.setScaleY(i+1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList emailarray = new ArrayList<String>();
                String verificationtext = verification.getText().toString();
                boolean valid = false;

                int atindex = -1;
                int comindex = -1;
                for(int i = 0; i < verificationtext.length(); i++){
                    emailarray.add(verificationtext.charAt(i));

                    if(verificationtext.charAt(i) == '@'){
                        atindex = i;
                    }

                    try {
                        if (verificationtext.charAt(i) == '.' && verificationtext.charAt(i+1) == 'c' && verificationtext.charAt(i+2) == 'o' && verificationtext.charAt(i+3) == 'm') {
                            comindex = i;
                        }
                    }
                    catch(Exception e){
                        comindex = -1;
                        valid = false;
                    }
                }

                if((atindex < comindex) && (atindex > 0 && comindex > 0)){
                    valid = true;
                }
                else{
                    valid = false;
                }
                if(valid){
                    displayverification.setText("Verified!");
                }
                else{
                    displayverification.setText("Not Verified");
                }
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checked1 = true;
                }
                else{
                    checked1 = false;
                }
                if(checked1 && checked2 && checked3){
                    colordisplay.setTextColor(Color.BLUE);
                }

                if(checked1 && !checked2 && checked3){
                    colordisplay.setTextColor(Color.RED);
                }

                if(!checked1 && !checked2 && !checked3){
                    colordisplay.setTextColor(Color.BLACK);
                }
                if(!checked1 && !checked2 && checked3){
                    colordisplay.setTextColor(Color.GREEN);
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    checked2 = true;
                }
               else{
                    checked2 = false;
                }
                if(checked1 && checked2 && checked3){
                    colordisplay.setTextColor(Color.BLUE);
                }

                if(checked1 && !checked2 && checked3){
                    colordisplay.setTextColor(Color.RED);
                }

                if(!checked1 && !checked2 && !checked3){
                    colordisplay.setTextColor(Color.BLACK);
                }
                if(!checked1 && !checked2 && checked3){
                    colordisplay.setTextColor(Color.GREEN);
                }
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    checked3 = true;
                }
                else{
                    checked3 = false;
                }

                if(checked1 && checked2 && checked3){
                    colordisplay.setTextColor(Color.BLUE);
                }

                if(checked1 && !checked2 && checked3){
                    colordisplay.setTextColor(Color.RED);
                }

                if(!checked1 && !checked2 && !checked3){
                    colordisplay.setTextColor(Color.BLACK);
                }
                if(!checked1 && !checked2 && checked3){
                    colordisplay.setTextColor(Color.GREEN);
                }
            }
        });
    }
}

