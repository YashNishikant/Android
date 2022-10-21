package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.widget.Toast;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class StartScreen extends AppCompatActivity implements LocationListener{

    Button moreviews;
    Button start;
    Button signOutButton;

    LocationManager lm;
    Geocoder geo;

    Timer timer;
    TimerTask timerthread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geo = new Geocoder(StartScreen.this, Locale.US);

        moreviews = findViewById(R.id.id_button_moreviews);
        start = findViewById(R.id.id_button_start);
        signOutButton = findViewById(R.id.id_button_signout);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, MainWorkoutActivity.class);
                startActivity(intent);
            }
        });

        moreviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartScreen.this, ExtraViewHolder.class);
                startActivity(intent);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SignInScreen.clearlogin();

                Intent signOut = new Intent(StartScreen.this, SignInScreen.class);
                MainWorkoutActivity.DataAccess.clearMainNode();
                startActivity(signOut);
            }
        });

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(StartScreen.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);

        timerthread = new TimerTask() {
            @Override
            public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(MainWorkoutActivity.DataAccess.dataRetrieveComplete()){
                                enableButton(moreviews, "More");
                            }
                            else{
                                disableButton(moreviews, "Wait...");
                            }
                        }
                    });
            }
        };

        MainWorkoutActivity.DataAccess.getWorkoutCount();
        MainWorkoutActivity.DataAccess.fillArrays();

        if(getIntent().getBooleanExtra(SignInScreen.key2, false)){
            MainWorkoutActivity.DataAccess.addWorkout(new Workout("",0,0,false),true);
        }

        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerthread, 0, 10);

            if(getIntent().getStringExtra(SignInScreen.key) != null) {
                Toast(getIntent().getStringExtra(SignInScreen.key));
            }
        }

        @Override
        public void onLocationChanged(@NonNull Location location) {
            MainWorkoutActivity.lonValue = location.getLongitude();
            MainWorkoutActivity.latValue = location.getLatitude();

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            Intent returnback = new Intent(StartScreen.this, SignInScreen.class);
            startActivity(returnback);

            Toast("Permissions changed, please sign in again");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);

            } else {
                return;
            }
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        public static void disableButton(Button b, String text){
            b.setText(text);
            b.setEnabled(false);
        }

        public static void enableButton(Button b, String text){
            b.setText(text);
            b.setEnabled(true);
        }


        void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}