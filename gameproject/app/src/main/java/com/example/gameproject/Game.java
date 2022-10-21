package com.example.gameproject;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends AppCompatActivity implements SensorEventListener {

    GameSurface gameSurface;

    int time = 60;

    float movefactor = 0;
    int spawnTime;
    boolean lock;
    boolean displayrespawntime;
    boolean gameEnd;
    boolean easymode = true;

    Timer timer;
    TimerTask timerthread;

    ArrayList<Obstacle> obList;
    ArrayList<Background> bgList;
    Player p;

    Bitmap stopwatch;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        p = new Player(325,800);

        mp = MediaPlayer.create(this, R.raw.not_minecraft_music);
        mp.start();

        stopwatch = BitmapFactory.decodeResource(getResources(), R.drawable.stopwatch);
        obList = new ArrayList<>();
        bgList = new ArrayList<>();
        bgList.add(new Background(BitmapFactory.decodeResource(getResources(), R.drawable.backgroundempty), 0f, -2000f));
        bgList.add(new Background(BitmapFactory.decodeResource(getResources(), R.drawable.backgroundempty), 0f, 0f));

        gameSurface = new GameSurface(this);
        setContentView(gameSurface);

        SensorManager sensormanager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor accSensor = sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensormanager.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);

        timerthread = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(!gameEnd){
                        time--;

                        if (time == 0) {
                            gameEnd = true;
                        }

                        if (p.getLifeStatus())
                            spawnTime = time;

                        if (!p.getLifeStatus()) {
                            if (!lock) {
                                spawnTime = time - 5;
                                displayrespawntime = true;
                                lock = true;
                            }

                            if (spawnTime == time) {
                                p.resetPos();
                                p.setLifeStatus(true);
                                lock = false;
                            }


                        } else {
                            spawnTime = time;
                            displayrespawntime = false;
                        }

                        if ((time % Obstacle.delay == 0) && p.getLifeStatus()) {
                            if(easymode){
                                spawnobject(gameSurface.screenWidth);
                            }
                            else{
                                spawnobject(gameSurface.screenWidth);
                                spawnobject(gameSurface.screenWidth);
                            }
                        }

                    }

                    }
                });
            }
        };

        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerthread, 0, 1000);

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
        if(!gameEnd) {
            if (p.getLifeStatus()) {
                movefactor = (Player.speed * (sensorEvent.values[0]));
            } else {
                movefactor = 0;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public class GameSurface extends SurfaceView implements Runnable {

        Thread gameThread;
        SurfaceHolder holder;
        volatile boolean running = false;
        Bitmap playerBitmap;
        Bitmap playerKilledBitmap;
        Bitmap background;
        Paint paintProperty;
        int screenWidth;
        int screenHeight;

        public GameSurface(Context context) {
            super(context);
            holder=getHolder();
            playerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship);
            playerKilledBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship_destroyed);
            background = BitmapFactory.decodeResource(getResources(), R.drawable.backgroundempty);
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

                //DRAWING GAME
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.CYAN);
                drawBitMaps(canvas, playerBitmap, playerKilledBitmap, screenWidth, screenHeight);

                //MOVMENT AND COLLISION
                horizontalbounds(screenWidth);
                if(!gameEnd)
                Collision();
                backgroundMovement();
                lifecheck();

                if(gameEnd)
                p.speedAway();

                //UI
                time_and_score(canvas, screenWidth, p, paintProperty);
                difficulty();

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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                easymode = !easymode;
                break;
        }
        return true;
    }

    void difficulty(){
        if(easymode){
            Obstacle.speed = 10f;
            Player.speed = 2.5f;
            Obstacle.delay = 2;
        }
        else{
            Obstacle.speed = 20f;
            Player.speed = 5f;
            Obstacle.delay = 1f;
        }
    }

    void lifecheck(){
        if(!p.getLifeStatus()){
            p.fall();
        }
    }

    void time_and_score(Canvas canvas, float scwidth, Player player, Paint p){

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Inconsolata-Light.ttf");
        Paint textPaint = new Paint();
        textPaint.setTextSize(90);
        textPaint.setTypeface(typeface);

        if(!gameEnd) {
            canvas.drawText("Score: " + Player.SCORE, 50, 150, textPaint);

            if(easymode)
                canvas.drawText("Difficulty: Easy", 50, 250, textPaint);
            else
                canvas.drawText("Difficulty: Hard", 50, 250, textPaint);

            canvas.drawText("" + time, scwidth - 200, 150, textPaint);
        }
        else{
            canvas.drawText("Score: " + Player.SCORE, 350, 600, textPaint);
        }
        if(displayrespawntime && (time-spawnTime) > 0 && !gameEnd){
            canvas.drawText("Respawn:\n" + (time-spawnTime), 350, 700, textPaint);
        }

        canvas.drawBitmap(stopwatch, null, new RectF(10, 10, 10, 10), null);

    }

    void backgroundMovement(){
        if(bgList.get(1).getPosY() > 1840){
            bgList.get(1).setPosY(bgList.get(0).getPosY()-2000f);
        }
        if(bgList.get(0).getPosY() > 1840){
            bgList.get(0).setPosY(bgList.get(1).getPosY()-2000f);
        }
    }

    void drawBitMaps(Canvas canvas, Bitmap playerBitMap, Bitmap playerkilled, float scWidth, float scHeight){

        if(!gameEnd)
            canvas.drawBitmap(stopwatch, null, new RectF(50, 10, 100, 100), null);

        for(int i = 0; i < bgList.size(); i++) {
            canvas.drawBitmap(bgList.get(i).getBitMap(), null, new RectF(bgList.get(i).getPosX(), bgList.get(i).getPosY(), bgList.get(i).getPosX()+scWidth, bgList.get(i).getPosY()+scHeight), null);
            bgList.get(i).movedown(3);
        }
        for(int i = 0; i < obList.size(); i++){
            canvas.drawBitmap(obList.get(i).getBitImage(),obList.get(i).getPosX(),obList.get(i).getPosY(),null);
            obList.get(i).movedown();

            if(obList.get(i).getPosY() > gameSurface.screenHeight + 350){
                obList.remove(i);
            }
        }

        if(p.getLifeStatus())
            canvas.drawBitmap(playerBitMap,p.getPosX(),p.getPosY(),null);
        else
            canvas.drawBitmap(playerkilled,p.getPosX(),p.getPosY(),null);

    }

    void horizontalbounds(float screenWidth){
        p.setPosX(p.getPosX()-movefactor);

        if(p.getPosX() >= screenWidth-100){
            p.setPosX(screenWidth-100);
        }
        if(p.getPosX() <= 0){
            p.setPosX(1);
        }
    }

    void spawnobject(int screenwidth){

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.meteor);

        obList.add(new Obstacle(
                (float)(Math.random()*screenwidth-50),
                -(float)(Math.random()*1000),
                Obstacle.speed));

        obList.get(obList.size()-1).setBitImage(image);

    }

    public void Collision(){
        for(int i = 0; i < obList.size(); i++){
            if(p.getBounds().intersect(obList.get(i).getBounds()) && obList.get(i).canChangeScore()){
                p.setLifeStatus(false);

                if(Player.SCORE > 0){
                    Player.SCORE--;
                }

                obList.get(i).disableCanChangeScore();

                mp = MediaPlayer.create(this, R.raw.lose);
                mp.start();

            }
            if(p.getBounds().intersect(obList.get(i).getPointBounds()) && obList.get(i).canChangeScore()){
                Player.SCORE++;
                obList.get(i).disableCanChangeScore();
            }
        }
    }

}
