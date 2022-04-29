package com.example.a1_5_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject schoolInfo = new JSONObject();
        try {
            schoolInfo.put("Name", "rtewfdgregewewrsdf");
            schoolInfo.put("Grade", "10");
            schoolInfo.put("ID", "5164987327896154154367289678945132769854123967897685431789541239678");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("abc", schoolInfo.toString());

        JSONObject compSci = new JSONObject();

    }
}