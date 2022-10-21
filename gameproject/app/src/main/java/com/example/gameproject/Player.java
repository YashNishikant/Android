
package com.example.gameproject;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

public class Player {

    private float x;
    private float y;
    private float width;
    private float height;
    private Bitmap image;
    private float decrease;
    private float increase;
    private float gravity;
    public static float speed = 2.5f;
    public static int SCORE;
    public boolean alive;
    private float originalX;
    private float originalY;

    public Player(float x, float y){
        this.x = x;
        this.y = y;
        originalX = x;
        originalY = y;
        width = 94;
        height = 94;
        SCORE = 0;
        alive = true;
        decrease = -20f;
        increase = 20f;
        gravity = 3f;
    }

    public void fall(){
        y += decrease;
        decrease += gravity;
    }

    public void speedAway(){
        y -= increase;
        increase += gravity/4;

        if(increase > 90){
            increase = 0;
        }

    }

    public void resetPos(){
        x = originalX;
        y = originalY;
        decrease = -20f;

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
        image = bitmap;
    }

    public Bitmap getBitImage(){
        return image;
    }

    public boolean getLifeStatus(){
        return alive;
    }

    public void setLifeStatus(boolean x){
        alive = x;
    }

    public Rect getBounds(){
        return new Rect((int)x,(int)y,(int)(x+width),(int)(y+height));
    }

}

