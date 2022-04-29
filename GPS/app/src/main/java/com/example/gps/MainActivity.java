package com.example.gps;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {

    LocationManager lm;
    Location oldloc;
    Location newloc;
    Location firstlocation;

    TextView lat;
    TextView lon;
    TextView address;
    TextView distance;
    TextView time;

    List<Address> addresslist;
    String oldaddress = "";
    Geocoder geo;

    double dist = 0;
    double totaltraveled = 0;
    double oldtime = -1;
    double changetime = -1;
    boolean freeze = true;
    boolean firstrun = true;

    Button reset;
    Switch appearance;

    ConstraintLayout cl;

    ArrayList locations = new ArrayList();
    ArrayList<Double> times = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        geo = new Geocoder(MainActivity.this, Locale.US);
        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);
        time = findViewById(R.id.time);
        address = findViewById(R.id.address);
        distance = findViewById(R.id.dist);
        reset = findViewById(R.id.button);
        appearance = findViewById(R.id.switch1);
        cl = (ConstraintLayout) findViewById(R.id.layout);

        addresslist = new List<Address>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Address> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Address address) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Address> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Address> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {
            }

            @Override
            public Address get(int i) {
                return null;
            }

            @Override
            public Address set(int i, Address address) {
                return null;
            }

            @Override
            public void add(int i, Address address) {
            }

            @Override
            public Address remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Address> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Address> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Address> subList(int i, int i1) {
                return null;
            }
        };

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();



    }

    public void setallviews(int color, int color2){
        lat.setTextColor(color);
        lon.setTextColor(color);
        address.setTextColor(color);
        distance.setTextColor(color2);
        appearance.setTextColor(color);
        time.setTextColor(color);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        if(oldtime > 0) {
            changetime = (SystemClock.elapsedRealtime() / 1000) - oldtime;
        }
        else{
            changetime = (0);
        }

        oldtime = (SystemClock.elapsedRealtime()/1000);

        oldloc = newloc;
        newloc = location;

        if(addresslist.get(0) != null) {
            oldaddress = addresslist.get(0).getAddressLine(0);
        }

        lat.setText("Latitude:   " + location.getLatitude() + "");
        lon.setText("Longitude:  " + location.getLongitude() + "");
        try {
            addresslist = (geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        address.setText(addresslist.get(0).getAddressLine(0));

        if (oldloc != null && newloc != null) {

            if(freeze){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                freeze = false;
            }

            dist = oldloc.distanceTo(newloc);
            totaltraveled += dist;
            distance.setText(totaltraveled + " m");
        }

        darkmode();
        reset();

        times.add(changetime);
        if(oldaddress.equals("")) {
            locations.add(addresslist.get(0).getAddressLine(0));
        }
        else{
            locations.add(oldaddress);
        }
        //print();

        sort();
        firstrun = false;
    }

    public void print(){

        Log.d("abc", "Times");
        for(int i = 0; i < times.size(); i++){
            Log.d("abc", times.get(i) + ", ");
        }

        Log.d("abc", "Locations");
        for(int i = 0; i < locations.size(); i++){
            Log.d("abc", locations.get(i) + ", ");
        }
        Log.d("abc", "____________________________________________________________________________");
    }

    public void sort(){
        double highest = 0;
        int index = 0;

        if(times.size() > 0) {
            for (int i = 0; i < times.size(); i++) {
                if (times.get(i) > highest) {
                    highest = times.get(i);
                    index = i;
                }
            }

            if(firstrun) {
                time.setText("Favorite Place   --:--");
            }
            else{
                time.setText("Favorite Place: " + locations.get(index) + " for " + times.get(index) + " seconds");
            }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);
            darkmode();
            reset();

        } else {
            darkmode();
            reset();
            return;
        }
    }

    public void darkmode(){
        appearance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cl.setBackgroundColor(getResources().getColor(R.color.black));
                    setallviews(Color.WHITE, Color.CYAN);
                    appearance.setText("Light Mode");
                }
                else{
                    cl.setBackgroundColor(getResources().getColor(R.color.white));
                    setallviews(Color.BLACK, Color.RED);
                    appearance.setText("Dark Mode");
                }
            }
        });

    }

    public void reset(){

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totaltraveled = 0;
                distance.setText(totaltraveled+" m");
            }
        });

    }

}



