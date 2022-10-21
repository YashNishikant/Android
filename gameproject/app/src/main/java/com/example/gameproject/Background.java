package com.example.gameproject;

import android.graphics.Bitmap;

public class Background {

    private float x;
    private float y;
    private Bitmap image;

    public Background(Bitmap bitmap, float x, float y){
        this.x = x;
        this.y = y;
        image = bitmap;
    }

    public Bitmap getBitMap(){
        return image;
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
    public void movedown(int speed){
        y += speed;
    }

}

