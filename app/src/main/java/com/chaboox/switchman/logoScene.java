package com.chaboox.switchman;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

/**
 * Created by moi on 11/02/2017.
 */
public class logoScene implements Scene {
    private Bitmap fontI;
    private long gameStart;
    private long curr;

    private Rect bl;
    public logoScene(){
        gameStart = System.currentTimeMillis();
        BitmapFactory bf = new BitmapFactory();

        bl = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        fontI = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.logo);
    }
    @Override
    public void recieveTouch(MotionEvent event) {

    }

    @Override
    public void draw(Canvas canvas) {
               canvas.drawBitmap(fontI, null, bl, new Paint());


    }

    @Override
    public void update() {

        curr= System.currentTimeMillis();
        if(curr-gameStart>3900) {
            SceneManager.ACTIVE_SCENE=0;

        }
    }

    @Override
    public void terminate() {

    }

    @Override
    public void reset(){}

}

