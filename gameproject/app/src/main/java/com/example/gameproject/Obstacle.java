
package com.example.gameproject;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.shapes.RectShape;

public class Obstacle {

    private float x;
    private float y;
    private float width;
    private float height;
    private Bitmap MissileImage;
    public static float speed = 10f;
    public static float delay = 2;
    private boolean increasescore;

    public Obstacle(float x, float y, float speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        width = 140;
        height = 130;
        increasescore = true;
    }

    public float getHeight() {
        return height;
    }
    public float getWidth() {
        return width;
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
    public void disableCanChangeScore(){
        increasescore = false;
    }
    public boolean canChangeScore(){
        return increasescore;
    }
    public Rect getBounds(){
        return new Rect((int)x,(int)y,(int)(x+width),(int)(y+height));
    }
    public Rect getPointBounds(){
        return new Rect((int)x-9000,(int)y,(int)(x+18000),(int)(y+2));
    }
}
