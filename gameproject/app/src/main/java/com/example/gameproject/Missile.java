package com.example.gameproject;
import android.graphics.Bitmap;

public class Missile {

    private float x;
    private float y;
    private Bitmap MissileImage;
    private float speed;
    public static int MISSILE_COUNT = 20;


    public Missile(float x, float y, float speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public float getPosX(){
        return x;
    }
    public float getPosY(){
        return y;
    }
    public void setPosX(float x){
        this.x = x;
    }
    public void setPosY(float y){
        this.y = y;
    }
    public void movedown(){
        y += 1*speed;
    }

    public void setBitImage(Bitmap bitmap){
        MissileImage = bitmap;
    }

    public Bitmap getBitImage(){
        return MissileImage;
    }

}
