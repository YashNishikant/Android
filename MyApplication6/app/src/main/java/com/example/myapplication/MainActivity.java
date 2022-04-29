package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONObject schoolInfo = new JSONObject();
        try {
            schoolInfo.put("Name", "Yash");
            schoolInfo.put("Grade", "11");
            schoolInfo.put("ID", "4352798");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject compSci = new JSONObject();
        try {
            compSci.put("grade", "B");
            compSci.put("Percent", "86");
            schoolInfo.put("CS", compSci);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = schoolInfo.toString();

        try {
            JSONObject jsoninternet = new JSONObject(str);
            Log.d("abc", jsoninternet.getString("Name"));
            JSONObject myclass = new JSONObject();
            myclass = jsoninternet.getJSONObject("CS");
            Log.d("abc",myclass.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray clubs = new JSONArray();
        clubs.put("CSClub");
        clubs.put("VVollunteers");

        try {
            schoolInfo.put("School Clubs", clubs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("abc","info" + schoolInfo.toString());

    }
}