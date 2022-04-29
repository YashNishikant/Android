package com.example.gameproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    GameSurface gameSurface;

    int x = 325;
    int y = 800;
    int speed = 5;

    ArrayList<Missile> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mList = new ArrayList<>();

        for(int i = 0; i < Missile.MISSILE_COUNT; i++){
            mList.add(new Missile((float)(Math.random()*600),
                              -(float)(Math.random()*100)+0,
                            (float)(Math.random()*15)+10) );

        }

        gameSurface = new GameSurface(this);
        setContentView(gameSurface);

        SensorManager sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor accSensor = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensormanager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause(){
        super.onPause();
        gameSurface.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameSurface.resume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x -= speed*(sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public class GameSurface extends SurfaceView implements Runnable {

        Thread gameThread;
        SurfaceHolder holder;
        volatile boolean running = false;
        Bitmap player;
        Bitmap background;

        Paint paintProperty;

        int screenWidth;
        int screenHeight;

        public GameSurface(Context context) {
            super(context);
            holder=getHolder();

            for(int i = 0; i < Missile.MISSILE_COUNT; i++){
                Bitmap missile = BitmapFactory.decodeResource(getResources(),R.drawable.spacemissiles_009);
                mList.get(i).setBitImage(missile);
            }

            player = BitmapFactory.decodeResource(getResources(),R.drawable.ship_0012);
            background = BitmapFactory.decodeResource(getResources(),R.drawable.backgroundempty);

            Display screenDisplay = getWindowManager().getDefaultDisplay();
            Point sizeOfScreen = new Point();
            screenDisplay.getSize(sizeOfScreen);
            screenWidth=sizeOfScreen.x;
            screenHeight=sizeOfScreen.y;

            paintProperty= new Paint();
        }

        @Override
        public void run() {
            while (running == true){
                if (holder.getSurface().isValid() == false)
                    continue;

                Canvas canvas= holder.lockCanvas();
                canvas.drawBitmap(background,0,0,null);
                canvas.drawBitmap(player,x,y,null);

                for(int i = 0; i < mList.size(); i++){
                    canvas.drawBitmap(mList.get(i).getBitImage(),mList.get(i).getPosX(),mList.get(i).getPosY(),null);
                    mList.get(i).movedown();
                }

                paintProperty.setTextSize(100);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void resume(){
            running=true;
            gameThread=new Thread(this);
            gameThread.start();
        }

        public void pause() {
            running = false;
            while (true) {
                try {
                    gameThread.join();
                } catch (InterruptedException e) {
                }
            }
        }
    }
}