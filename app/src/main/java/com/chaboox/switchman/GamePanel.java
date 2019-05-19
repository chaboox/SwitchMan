package com.chaboox.switchman;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by moi on 26/01/2017.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static MainThread thread;
    private SceneManager manager;


    public static SurfaceHolder sh;

    public GamePanel(Context context){
        super(context);

        getHolder().addCallback(this);
        sh = getHolder();
           Constants.CURRENT_CONTEXT = context;
        thread= new MainThread(getHolder(),this);


        manager = new SceneManager();
        setFocusable(true);

    }


    @Override
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){}
    @Override
    public void surfaceCreated(SurfaceHolder holder){



    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

    }


    @Override
    public boolean onTouchEvent (MotionEvent event) {
        manager.recieveTouch(event);
        return true;
    }

    public void update(){
        manager.update();
       }
    @Override
    public void draw(Canvas canvas){

        super.draw(canvas);
        manager.draw(canvas);



    }









    public void onResume(){


           thread= new MainThread(getHolder(),this);
            thread.setRunning(true);


        thread.start();

}
    public void onPause(){
        thread.setRunning(false);
        if(thread != null){
            Thread moribund = thread;
            thread = null;
            moribund.interrupt();
        }
    }

}
