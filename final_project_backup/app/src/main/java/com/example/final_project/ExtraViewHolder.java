package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class ExtraViewHolder extends AppCompatActivity {

    ViewPager2 vp2;
    FragmentViewPagerAdapter adapter;
    Button sendback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_view_holder);

        vp2 = findViewById(R.id.id_viewpager);
        sendback = findViewById(R.id.id_button_sendback);

        adapter = new FragmentViewPagerAdapter(this);
        vp2.setAdapter(adapter);

        sendback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendback = new Intent(ExtraViewHolder.this, StartScreen.class);
                startActivity(sendback);
                finish();
            }
        });

    }
}