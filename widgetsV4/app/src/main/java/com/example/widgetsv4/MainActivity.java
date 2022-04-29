package com.example.widgetsv4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Switch SwitchOne;
    Switch SwitchTwo;
    Switch SwitchThree;
    SeekBar seekBar;

    EditText v;
    EditText d;

    boolean sone;
    boolean stwo;
    boolean sthree;
    boolean verified;
    boolean dbverified;

    Button b;

    TextView t;
    TextView t2;
    TextView t3;
    TextView t4;

    ArrayList newlist = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newlist.add("ValidEmailOne@gmail.com");
        newlist.add("ValidEmailTwo@hotmail.com");
        newlist.add("ValidEmailThree@yahoo.com");

        SwitchOne = findViewById(R.id.switch1);
        SwitchTwo = findViewById(R.id.switch2);
        SwitchThree = findViewById(R.id.switch3);
        seekBar = findViewById(R.id.seekBar);
        t2 = findViewById(R.id.textView2);
        t = findViewById(R.id.textView);
        v = findViewById(R.id.editTextTextPersonName);
        b = findViewById(R.id.button);
        t3 = findViewById(R.id.textView3);
        t4 = findViewById(R.id.textView4);
        d = findViewById(R.id.editTextTextPersonName2);

        d.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                for(int j = 0; j < newlist.size(); j++) {
                    if (charSequence.toString().equals(newlist.get(j))){
                        dbverified = true;
                        break;
                    }
                    else{
                        dbverified = false;
                    }
                }


                if(dbverified){
                    t4.setText("Verified!");
                }
                else{
                    t4.setText("Not Verified");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verified){
                    t3.setText("Verified!");
                }
                else{
                    t3.setText("Not Verified");
                }
            }
        });

        v.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().contains("@") && charSequence.toString().contains(".com") && (charSequence.toString().indexOf("@") < charSequence.toString().indexOf(".com"))){
                    verified = true;
                }
                else{
                    verified = false;
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
                    t2.setScaleX(i);
                    t2.setScaleY(i);
                }
                else{
                    t2.setScaleX(1);
                    t2.setScaleY(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SwitchOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    sone = true;
                }
                else{
                    sone = false;
                }
                if(sone && stwo && sthree){
                    t.setTextColor(Color.BLUE);
                }
                if(sone && !stwo && sthree){
                    t.setTextColor(Color.RED);
                }
                if(!sone && !stwo && sthree){
                    t.setTextColor(Color.GREEN);
                }
            }
        });

        SwitchTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    stwo = true;
                }
                else{
                    stwo = false;
                }
                if(sone && stwo && sthree){
                    t.setTextColor(Color.BLUE);
                }
                if(sone && !stwo && sthree){
                    t.setTextColor(Color.RED);
                }
                if(!sone && !stwo && sthree){
                    t.setTextColor(Color.GREEN);
                }
            }
        });

        SwitchThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    sthree = true;
                }
                else{
                    sthree = false;
                }
                if(sone && stwo && sthree){
                    t.setTextColor(Color.BLUE);
                }
                if(sone && !stwo && sthree){
                    t.setTextColor(Color.RED);
                }
                if(!sone && !stwo && sthree){
                    t.setTextColor(Color.GREEN);
                }
            }
        });

    }
}