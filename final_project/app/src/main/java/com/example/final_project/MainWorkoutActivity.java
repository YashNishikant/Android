package com.example.final_project;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainWorkoutActivity extends AppCompatActivity implements LocationListener {

    LocationManager lm;
    Location oldloc;
    Location newloc;

    TextView lat;
    TextView lon;
    TextView distance;
    TextView timedisplay;

    Geocoder geo;

    boolean freeze = true;
    boolean firstrun = true;
    boolean pause = false;

    ConstraintLayout cl;

    Button SAVE;

    long currentSeconds = 0;
    long totaltraveled = 0;

    public static double latValue = 0;
    public static double lonValue = 0;

    String currentName = "";

    Timer timer;
    TimerTask timerthread;

    public static String key = "98743wnvtyfr098twkud8e9vt7098537ty9034wr";
    public static String key2 = "65hj45k64n4565j76lkj78lkj8lkj890324k34j";
    int loadcode = 32425;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_workout);

        SAVE = findViewById(R.id.BUTTON_END);

        geo = new Geocoder(MainWorkoutActivity.this, Locale.US);

        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);
        distance = findViewById(R.id.dist);
        cl = findViewById(R.id.layout);
        timedisplay = findViewById(R.id.TIME_DISPLAY);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainWorkoutActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause = true;
                Intent intent = new Intent(MainWorkoutActivity.this, Saving.class);
                intent.putExtra(key, currentSeconds);
                intent.putExtra(key2, totaltraveled);
                startActivityForResult(intent, loadcode);

            }
        });

        timerthread = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!pause) {
                            currentSeconds++;
                        }
                        timedisplay.setText(""+currentSeconds);
                    }
                });
            }
        };

        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerthread, 0, 1000);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        oldloc = newloc;
        newloc = location;

        latValue = location.getLatitude();
        lonValue = location.getLongitude();

        lat.setText("Latitude:   " + location.getLatitude() + "");
        lon.setText("Longitude:  " + location.getLongitude() + "");


        if (oldloc != null && newloc != null) {

            if(freeze){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                freeze = false;
            }

            totaltraveled += oldloc.distanceTo(newloc);
            distance.setText(totaltraveled + " m");
        }

        firstrun = false;
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);

        } else {
            Toast("Please enable location permissions on this app");
            Intent returntoStart = new Intent(MainWorkoutActivity.this, StartScreen.class);
            startActivity(returntoStart);
            return;
        }
    }

    void Toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == loadcode && data != null){
            currentName = data.getStringExtra(Saving.key);
            DataAccess.addWorkout(new Workout(currentName, currentSeconds, (int) totaltraveled, true), false);
            Toast("Workout Saved!");

            Intent returntoStart = new Intent(MainWorkoutActivity.this, StartScreen.class);
            startActivity(returntoStart);

        }
    }

    public static class DataAccess {

        private static DatabaseReference dbRef;
        private static DatabaseReference dbRef2;
        private static DatabaseReference users;
        private static String userNodeData;
        private static String mainNode = "Users";
        public static long workoutcount = 0;

        private static ArrayList names = new ArrayList();
        private static ArrayList times = new ArrayList();
        private static ArrayList distances = new ArrayList();
        private static ArrayList displays = new ArrayList();
        private static ArrayList userslist = new ArrayList();

        private static boolean datacomplete;
        private static boolean empty;

        public DataAccess() {

            userNodeData = SignInScreen.getUsername();
            dbRef = FirebaseDatabase.getInstance().getReference().child(mainNode).child(userNodeData);
        }

        public static void addWorkout(Workout w, boolean emptyworkout) {
            if(!emptyworkout)
                dbRef.child(w.getName()).setValue(w);
            else
                dbRef.child("Empty").setValue(w);
        }

        public static void getUsers(){
            mainNode = "Users";
            users = FirebaseDatabase.getInstance().getReference().child(mainNode);
            users.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for(DataSnapshot s : snapshot.getChildren()){
                            userslist.add(s.getKey());

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

        public static void fillArrays() {

            names.clear();
            times.clear();
            distances.clear();
            displays.clear();

            datacomplete = false;

            userNodeData = SignInScreen.getUserNode();
            dbRef = FirebaseDatabase.getInstance().getReference().child(mainNode).child(userNodeData);
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        for(DataSnapshot s : snapshot.getChildren()){
                            dbRef2 = FirebaseDatabase.getInstance().getReference().child(mainNode).child(userNodeData).child(s.getKey());
                            dbRef2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot s : snapshot.getChildren()) {
                                        if (s.getKey().equals("name")) {
                                            names.add(s.getValue());
                                        }
                                        if (s.getKey().equals("time")) {
                                            times.add(s.getValue());
                                        }
                                        if (s.getKey().equals("distance")) {
                                            distances.add(s.getValue());
                                        }
                                        if (s.getKey().equals("display")) {
                                            displays.add(s.getValue());
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        }
                        datacomplete = true;
                        empty = false;
                    }
                    else{
                        datacomplete = true;
                        empty = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }


        public static long getWorkoutCount() {

            userNodeData = SignInScreen.getUserNode();
            dbRef = FirebaseDatabase.getInstance().getReference().child(mainNode).child(userNodeData);
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        workoutcount = snapshot.getChildrenCount();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            return workoutcount;
        }

        public static ArrayList getNamesArray(){
            return names;
        }

        public static ArrayList getTimesArray(){
            return times;
        }

        public static ArrayList getDistanceArray(){
            return distances;
        }

        public static ArrayList getDiplaysArray(){
            return displays;
        }

        public static ArrayList getUsersArray(){
            return userslist;
        }

        public static boolean dataRetrieveComplete(){
            return datacomplete;
        }

        public static boolean dataIsEmpty(){
            return empty;
        }

        public static void  clearMainNode(){
            userNodeData = "";
        }

    }
}