package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView lontxt;
    TextView lattxt;
    TextView citytxt;

    TextView weathertxt1;
    TextView desctxt1;

    TextView weathertxt2;
    TextView desctxt2;

    TextView weathertxt3;
    TextView desctxt3;

    TextView weathertxt4;
    TextView desctxt4;

    TextView temp1;
    TextView temp2;
    TextView temp3;
    TextView temp4;

    TextView time1;
    TextView time2;
    TextView time3;
    TextView time4;

    JSONObject coords;
    String city;
    String lat;
    String lon;
    EditText edittext;
    Button b;
    String currentmessage;
    ImageView image;
    ImageView image2;
    ImageView image3;
    ImageView image4;

    boolean erroroccured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lontxt = findViewById(R.id.latitude);
        lattxt = findViewById(R.id.longitude);
        citytxt = findViewById(R.id.City);
        edittext = findViewById(R.id.et);
        b = findViewById(R.id.button);

        weathertxt1 = findViewById(R.id.weather);
        weathertxt2 = findViewById(R.id.weather2);
        weathertxt3 = findViewById(R.id.weather3);
        weathertxt4 = findViewById(R.id.weather4);

        desctxt1 = findViewById(R.id.description);
        desctxt2 = findViewById(R.id.description2);
        desctxt3 = findViewById(R.id.description3);
        desctxt4 = findViewById(R.id.description4);

        image = findViewById(R.id.imageView);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);

        image.setImageResource(R.drawable.defaulticon);
        image2.setImageResource(R.drawable.defaulticon);
        image3.setImageResource(R.drawable.defaulticon);
        image4.setImageResource(R.drawable.defaulticon);

        temp1 = findViewById(R.id.temp1);
        temp2 = findViewById(R.id.temp2);
        temp3 = findViewById(R.id.temp3);
        temp4 = findViewById(R.id.temp4);

        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);

        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentmessage = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new myclass("https://api.openweathermap.org/data/2.5/weather?zip=" + currentmessage + "&appid=236d02bbfecda79e4671674aa5e0c94b").execute();

            }
        });

    }

    class myclass extends AsyncTask<Void, Void, JSONObject> {

        String url;
        URL myurl;
        URLConnection myconnection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        String info;
        JSONObject json;

        public myclass(String url1){
            url = url1;
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {

            try {
                myurl = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                myconnection = myurl.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                inputStream = myconnection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                info = bufferedReader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                json = new JSONObject(info);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;
        }

        void setAllImages(int d){
            image.setImageResource(d);
            image2.setImageResource(d);
            image3.setImageResource(d);
            image4.setImageResource(d);
        }

        @Override
        protected void onPostExecute(JSONObject result) {

            try {
                b.setText("CONFIRM");
                coords = result.getJSONObject("coord");
                lon = result.getJSONObject("coord").getString("lon");
                lat = result.getJSONObject("coord").getString("lat");
                city = result.getString("name");
                erroroccured = false;
            } catch (Exception e) {
                b.setText("INVALID");
                setAllImages(R.drawable.error);
                lattxt.setText("Latitude");
                lontxt.setText("Longitude");
                citytxt.setText("City");
                erroroccured = true;
                weathertxt1.setText("Error");
                weathertxt2.setText("Error");
                weathertxt3.setText("Error");
                weathertxt4.setText("Error");
                desctxt1.setText("");
                desctxt2.setText("");
                desctxt3.setText("");
                desctxt4.setText("");
            }
            if(!erroroccured) {
                lattxt.setText("Latitude:  " + lat);
                lontxt.setText("Longitude:  " + lon);
                citytxt.setText("City:  " + city);
            }

            new myclass2("https://api.openweathermap.org/data/2.5/onecall?lat=" + lat + "&lon=" + lon + "&exclude=daily,minutely&appid=236d02bbfecda79e4671674aa5e0c94b").execute();
        }

    }

    class myclass2 extends AsyncTask<Void, Void, JSONObject> {

        String url;
        URL myurl;
        URLConnection myconnection;
        InputStream inputStream;
        BufferedReader bufferedReader;
        String info;
        JSONObject json;


        public myclass2(String url1){
            url = url1;
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {

            try {
                myurl = new URL(url);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                myconnection = myurl.openConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                inputStream = myconnection.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try{
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                info = bufferedReader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                json = new JSONObject(info);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;
        }

        public void settextviews(JSONObject result, TextView str, TextView desc, TextView temperaturetxt, TextView timetxt, int index, ImageView i){

            JSONObject hours;
            String weatherstr = "";
            String weatherdesc = "";
            String temp = "";
            String time = "";
            int unixtime;
            Date d;
            String finalformat = "";
            Double tempvalue;
            Double fVal = 0.0;
            String hourvalue = "";

            try {
                hours = (JSONObject)(result.getJSONArray("hourly").get(index));
                weatherstr = ((JSONObject)(hours.getJSONArray("weather").get(0))).getString("main");
                weatherdesc = ((JSONObject)(hours.getJSONArray("weather").get(0))).getString("description");

                temp = hours.getString("temp");
                tempvalue = Double.parseDouble(temp);
                fVal = (double)(9/5)*(tempvalue-273.15) + 32;

                time = hours.getString("dt");
                unixtime = Integer.parseInt(time);
                d = new java.util.Date(unixtime*1000l);
                SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("hh:mm a");
                dateFormat.setTimeZone(java.util.TimeZone.getTimeZone("GMT-5"));
                finalformat = dateFormat.format(d);

                System.out.println(hourvalue);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            str.setText(weatherstr);
            desc.setText(weatherdesc.toUpperCase(Locale.ROOT));
            temperaturetxt.setText(fVal + " °F");
            timetxt.setText(finalformat);

            try {
                if (weatherstr.equalsIgnoreCase("Clear")) {
                    i.setImageResource(R.drawable.sunnyclear);
                }
                if (weatherstr.equalsIgnoreCase("Clouds")) {
                    i.setImageResource(R.drawable.cloudy);
                }
                if (weatherstr.equalsIgnoreCase("Rain")) {
                    i.setImageResource(R.drawable.rainy);
                }
                if (weatherstr.equalsIgnoreCase("Snow")) {
                    i.setImageResource(R.drawable.snowy);
                }
                if (weatherstr.equalsIgnoreCase("Thunderstorm")) {
                    i.setImageResource(R.drawable.storm);
                }
                if (weatherstr.equalsIgnoreCase("Drizzle")) {
                    i.setImageResource(R.drawable.rainy);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }

        @Override
        protected void onPostExecute(JSONObject result) {

            try {
                if(!erroroccured) {
                    settextviews(result, weathertxt1, desctxt1, temp1, time1,0, image);
                    settextviews(result, weathertxt2, desctxt2, temp2, time2,1, image2);
                    settextviews(result, weathertxt3, desctxt3, temp3, time3,2, image3);
                    settextviews(result, weathertxt4, desctxt4, temp4, time4,3, image4);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}


