package com.example.wack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Game extends AppCompatActivity {

    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    ImageView board;
    ImageView stopwatch;
    TextView scoretext;
    TextView TimerWindow;
    TextView overflowtext;
    Timer timer;
    TimerTask timerthread;
    AtomicInteger CurrentTime;
    AtomicInteger score;
    ArrayList<Target> targetList = new ArrayList<Target>();
    ConstraintLayout layout;
    int marginleft = 10;
    int margintop = 10;
    int overflowcounter = 0;
    int counterrow = 0;
    final int SET_TIME = 60;
    boolean gameEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        layout = findViewById(R.id.constLayout);

        score = new AtomicInteger();
        CurrentTime = new AtomicInteger();
        CurrentTime.set(SET_TIME);

        overflowtext = findViewById(R.id.overflowCount);
        overflowtext.setVisibility(View.INVISIBLE);

        stopwatch = findViewById(R.id.stopwatchimage);
        stopwatch.setImageResource(R.drawable.stopwatch);

        Target t1 = new Target(R.drawable.meme);
        targetList.add(t1);
        Target t2 = new Target(R.drawable.meme);
        targetList.add(t2);
        Target t3 = new Target(R.drawable.meme);
        targetList.add(t3);
        Target t4 = new Target(R.drawable.meme);
        targetList.add(t4);
        Target t5 = new Target(R.drawable.meme);
        targetList.add(t5);
        Target t6 = new Target(R.drawable.meme);
        targetList.add(t6);
        Target t7 = new Target(R.drawable.meme);
        targetList.add(t7);
        Target t8 = new Target(R.drawable.meme);
        targetList.add(t8);
        Target t9 = new Target(R.drawable.meme);
        targetList.add(t9);

        scoretext = findViewById(R.id.scoreview);
        TimerWindow = findViewById(R.id.textView2);

        timerthread = new TimerTask() {
            @Override
            public void run() {
                if (CurrentTime.get() > 0) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            CurrentTime.getAndDecrement();
                            TimerWindow.setText("" + CurrentTime);

                            for(int i = 0; i < targetList.size(); i++) {
                                //LOOP_________________________________________________

                                int powerup = (int)(Math.random()*10)+1;

                                if (targetList.get(i).getTimeBoolean()) {
                                    targetList.get(i).setTimeAppear(CurrentTime.get());
                                    targetList.get(i).setTimeBoolean(false);
                                }
                                if (CurrentTime.get() == targetList.get(i).getTimeAppear()) {
                                    setImageResources(i, R.drawable.meme);
                                    removeAbilities(targetList.get(i));

                                    if(powerup == 1){
                                        int powertype = (int)(Math.random()*2)+1;

                                        if(powertype == 1){
                                            targetList.get(i).setPowerUp(true);
                                            targetList.get(i).setPowerDown(false);
                                        }
                                        if(powertype == 2 && CurrentTime.get() > 16){
                                            targetList.get(i).setPowerUp(false);
                                            targetList.get(i).setPowerDown(true);
                                        }

                                        if(targetList.get(i).isPowerUp()){
                                            setImageResources(i, R.drawable.powerup);
                                        }
                                        else{
                                            setImageResources(i, R.drawable.powerdown);
                                        }

                                    }

                                    targetList.get(i).setHit(false);
                                    targetappear(i, targetList.get(i));
                                    targetList.get(i).setTimeDissapear(CurrentTime.get());
                                }
                                if (CurrentTime.get() == targetList.get(i).getTimeDissapear()) {
                                    if (targetList.get(i).getAppearance())
                                        targetdisappear(i, targetList.get(i));

                                    targetList.get(i).setTimeAppear(CurrentTime.get());
                                }

                                //LOOP_________________________________________________
                            }

                            if(CurrentTime.get() <= 0){
                                gameEnd = true;
                                addImageView(300, 130, R.drawable.finalscreen);
                                addTextView(400, 250, "FINAL SCORE:", Color.BLACK, 35);
                                addTextView(700, 450, ""+score.get(), Color.BLUE, 90);
                            }

                        }
                    });
                }
            }
        };

        setImageID();
        pairObjectImages(image1, t1);
        pairObjectImages(image2, t2);
        pairObjectImages(image3, t3);
        pairObjectImages(image4, t4);
        pairObjectImages(image5, t5);
        pairObjectImages(image6, t6);
        pairObjectImages(image7, t7);
        pairObjectImages(image8, t8);
        pairObjectImages(image9, t9);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image1, t1);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image2, t2);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image3, t3);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image4, t4);
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image5, t5);
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image6, t6);
            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image7, t7);
            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image8, t8);
            }
        });
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetHit(image9, t9);
            }
        });

        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerthread, 0, 1000);
    }
    public void pairObjectImages(ImageView i, Target t){
        i.setImageResource(t.getImageID());
    }
    public void setImageID(){
        image1 = findViewById(R.id.target1);
        imageresources(image1, R.drawable.meme);
        image2 = findViewById(R.id.target2);
        imageresources(image2, R.drawable.meme);
        image3 = findViewById(R.id.target3);
        imageresources(image3, R.drawable.meme);
        image4 = findViewById(R.id.target4);
        imageresources(image4, R.drawable.meme);
        image5 = findViewById(R.id.target5);
        imageresources(image5, R.drawable.meme);
        image6 = findViewById(R.id.target6);
        imageresources(image6, R.drawable.meme);
        image7 = findViewById(R.id.target7);
        imageresources(image7, R.drawable.meme);
        image8 = findViewById(R.id.target8);
        imageresources(image8, R.drawable.meme);
        image9 = findViewById(R.id.target9);
        imageresources(image9, R.drawable.meme);
        board = findViewById(R.id.MainBoard);
        imageresources(board, R.drawable.pitboard);
    }
    public void imageresources(ImageView I, int id){
        I.setImageResource(id);
        if(id != R.drawable.pitboard)
            I.setVisibility(View.INVISIBLE);
    }
    public void targetappear(int i, Target t1){

        if(i == 0){
            image1.setVisibility(View.VISIBLE);
            image1.startAnimation(t1.getAppearAnimation());
        }
        if(i == 1){
            image2.setVisibility(View.VISIBLE);
            image2.startAnimation(t1.getAppearAnimation());
        }
        if(i == 2){
            image3.setVisibility(View.VISIBLE);
            image3.startAnimation(t1.getAppearAnimation());
        }
        if(i == 3){
            image4.setVisibility(View.VISIBLE);
            image4.startAnimation(t1.getAppearAnimation());
        }
        if(i == 4){
            image5.setVisibility(View.VISIBLE);
            image5.startAnimation(t1.getAppearAnimation());
        }
        if(i == 5){
            image6.setVisibility(View.VISIBLE);
            image6.startAnimation(t1.getAppearAnimation());
        }
        if(i == 6){
            image7.setVisibility(View.VISIBLE);
            image7.startAnimation(t1.getAppearAnimation());
        }
        if(i == 7){
            image8.setVisibility(View.VISIBLE);
            image8.startAnimation(t1.getAppearAnimation());
        }
        if(i == 8){
            image9.setVisibility(View.VISIBLE);
            image9.startAnimation(t1.getAppearAnimation());
        }
        t1.setAppearance(true);
    }
    public void setImageResources(int i, int image){

        if(i == 0){
            image1.setImageResource(image);
        }
        if(i == 1){
            image2.setImageResource(image);
        }
        if(i == 2){
            image3.setImageResource(image);
        }
        if(i == 3){
            image4.setImageResource(image);
        }
        if(i == 4){
            image5.setImageResource(image);
        }
        if(i == 5){
            image6.setImageResource(image);
        }
        if(i == 6){
            image7.setImageResource(image);
        }
        if(i == 7){
            image8.setImageResource(image);
        }
        if(i == 8) {
            image9.setImageResource(image);
        }

    }
    public void targetdisappear(int i, Target t1){

        if(i == 0){
            image1.setVisibility(View.INVISIBLE);
            image1.startAnimation(t1.getDisappearAnimation());
        }
        if(i == 1){
            image2.setVisibility(View.INVISIBLE);
            image2.startAnimation(t1.getDisappearAnimation());
        }
        if(i == 2){
            image3.setVisibility(View.INVISIBLE);
            image3.startAnimation(t1.getDisappearAnimation());
        }
        if(i == 3){
            image4.setVisibility(View.INVISIBLE);
            image4.startAnimation(t1.getDisappearAnimation());
        }
        if(i == 4){
            image5.setVisibility(View.INVISIBLE);
            image5.startAnimation(t1.getDisappearAnimation());
        }
        if(i == 5){
            image6.setVisibility(View.INVISIBLE);
            image6.startAnimation(t1.getDisappearAnimation());
        }
        if(i == 6){
            image7.setVisibility(View.INVISIBLE);
            image7.startAnimation(t1.getDisappearAnimation());
        }
        if(i == 7){
            image8.setVisibility(View.INVISIBLE);
            image8.startAnimation(t1.getDisappearAnimation());
        }
        if(i == 8){
            image9.setVisibility(View.INVISIBLE);
            image9.startAnimation(t1.getDisappearAnimation());
        }
        t1.setAppearance(false);
    }
    public void targetHit(ImageView i1, Target t1){
        if(!t1.getHit() && !gameEnd && !t1.isPowerUp() && !t1.isPowerDown()) {

            score.getAndIncrement();
            scoretext.setText("Score: " + score);

            i1.setVisibility(View.INVISIBLE);
            i1.startAnimation(t1.getDisappearAnimation());
            i1.setImageResource(R.drawable.uncannymeme);
            t1.setAppearance(false);
            t1.setHit(true);

            if(score.get() < 20) {

                addImageView(margintop, marginleft, R.drawable.scoreicon);
                marginleft += 100;
                counterrow++;

                if (counterrow > 9) {
                    margintop += 100;
                    marginleft = 10;
                    counterrow = 0;
                }

            }
            else{
                overflowtext.setVisibility(View.VISIBLE);
                overflowcounter++;
                overflowtext.setText("+"+overflowcounter);
            }
        }
        else if(!t1.getHit() && !gameEnd && t1.isPowerUp() && !t1.isPowerDown()){
            CurrentTime.set(CurrentTime.get()+15);

            i1.setVisibility(View.INVISIBLE);
            i1.startAnimation(t1.getDisappearAnimation());
            i1.setImageResource(R.drawable.uncannymeme);
            t1.setAppearance(false);
            t1.setHit(true);

            for(int i = 0; i < targetList.size(); i++) {
                targetList.get(i).setTimeAppear(targetList.get(i).getTimeAppear() + 15);
                targetList.get(i).setTimeDissapear(targetList.get(i).getTimeDissapear() + 15);
            }

        }
        else if(!t1.getHit() && !gameEnd && !t1.isPowerUp() && t1.isPowerDown()){
            CurrentTime.set(CurrentTime.get()-15);

            i1.setVisibility(View.INVISIBLE);
            i1.startAnimation(t1.getDisappearAnimation());
            i1.setImageResource(R.drawable.uncannymeme);
            t1.setAppearance(false);
            t1.setHit(true);

            for(int i = 0; i < targetList.size(); i++) {
                targetList.get(i).setTimeAppear(targetList.get(i).getTimeAppear() - 15);
                targetList.get(i).setTimeDissapear(targetList.get(i).getTimeDissapear() - 15);
            }

        }
    }
    public void removeAbilities(Target t){
        t.setPowerUp(false);
        t.setPowerDown(false);

    }
    public void addImageView(int marginTOP, int marginLEFT, int image){

        ImageView newimage = new ImageView(this);
        newimage.setId(View.generateViewId());
        newimage.setImageResource(image);

        ConstraintSet CS = new ConstraintSet();
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        newimage.setLayoutParams(params);

        layout.addView(newimage);

        CS.clone(layout);
        CS.connect(newimage.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, marginTOP);
        CS.connect(newimage.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, marginLEFT);
        CS.setVerticalBias(newimage.getId(), 0.3f);
        CS.setHorizontalBias(newimage.getId(), 0.75f);
        CS.applyTo(layout);
    }
    public void addTextView(int marginTOP, int marginLEFT, String text, int c, int size){

        TextView newText = new TextView(this);
        newText.setId(View.generateViewId());

        ConstraintSet CS = new ConstraintSet();
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        newText.setLayoutParams(params);

        layout.addView(newText);

        CS.clone(layout);
        CS.connect(newText.getId(), ConstraintSet.TOP, layout.getId(), ConstraintSet.TOP, marginTOP);
        CS.connect(newText.getId(), ConstraintSet.LEFT, layout.getId(), ConstraintSet.LEFT, marginLEFT);
        CS.setVerticalBias(newText.getId(), 0.3f);
        CS.setHorizontalBias(newText.getId(), 0.75f);
        CS.applyTo(layout);
        newText.setText(text);
        newText.setTextSize(size);
        newText.setTextColor(c);
    }

}
