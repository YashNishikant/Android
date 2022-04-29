package com.example.wack;

import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class Target {

    private int image;
    private boolean appear;
    private int timeDissapear = 0;
    private int timeAppear = 0;
    private boolean settime = true;
    private int rarity = 15;
    private boolean hasbeenhit;
    private boolean isPowerUp;
    private boolean isPowerDown;


    ScaleAnimation APPEARANIMATION;
    ScaleAnimation DISAPPEARANIMATION;

    public Target(int image){
        this.image = image;

        APPEARANIMATION = new ScaleAnimation(0, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        APPEARANIMATION.setDuration(500);

        DISAPPEARANIMATION = new ScaleAnimation(1f, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        DISAPPEARANIMATION.setDuration(500);

    }

    public ScaleAnimation getAppearAnimation(){
        return APPEARANIMATION;
    }
    public ScaleAnimation getDisappearAnimation(){
        return DISAPPEARANIMATION;
    }
    public void setTimeDissapear(int CurrTime){timeDissapear = CurrTime - ((int)((Math.random() * (rarity/2)) + 1));}
    public int getTimeDissapear(){
        return timeDissapear;
    }
    public void setTimeAppear(int CurrTime){timeAppear = CurrTime - ((int)((Math.random() * rarity) + 1));}
    public int getTimeAppear(){
        return timeAppear;
    }
    public void setTimeBoolean(boolean x){
        settime = x;
    }
    public boolean getTimeBoolean(){
        return settime;
    }
    public int getImageID(){
        return image;
    }
    public boolean getAppearance(){
        return appear;
    }
    public void setAppearance(boolean x){
        appear = x;
    }
    public void setHit(boolean x){
        hasbeenhit = x;
    }
    public boolean getHit(){
        return hasbeenhit;
    }
    public boolean isPowerUp() {
        return isPowerUp;
    }
    public void setPowerUp(boolean powerUp) {
        isPowerUp = powerUp;
    }
    public boolean isPowerDown() {return isPowerDown;}
    public void setPowerDown(boolean powerDown) {isPowerDown = powerDown;}

}
