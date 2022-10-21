package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Saving extends AppCompatActivity {

    EditText et;
    TextView dist;
    TextView time;
    TextView avgspeed;
    Button finalsave;
    Button dontsave;
    String workoutname;

    private float distValue;
    private float timeValue;

    public static String key = "5h5nm5gfc456436w5jk43l543";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        et = findViewById(R.id.id_WORKOUT_NAME);
        dist = findViewById(R.id.id_SAVE_DIST);
        time = findViewById(R.id.id_SAVE_TIME);
        avgspeed = findViewById(R.id.id_AVERAGE_SPEED);
        finalsave = findViewById(R.id.finalsavebutton);
        dontsave = findViewById(R.id.id_button_dontsave);

        distValue = getIntent().getLongExtra(MainWorkoutActivity.key2, 0);
        timeValue = getIntent().getLongExtra(MainWorkoutActivity.key, 0);

        dist.setText("Distance: " + distValue + " m");
        time.setText("Time: " + timeValue + " s");
        avgspeed.setText("Average Speed: " + (int)(distValue/timeValue) + " m/s");

        dontsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendback = new Intent(Saving.this, StartScreen.class);
                startActivity(sendback);
                finish();
            }
        });

        finalsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendback = new Intent(Saving.this, MainWorkoutActivity.class);
                sendback.putExtra(key, workoutname);
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
                workoutname = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}