package com.chaboox.switchman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;

/**
 * Created by moi on 07/02/2017.
 */
public class MenuScene implements Scene {
    private Rect r = new Rect();
    private Bitmap playImg;
    private Bitmap scoreImg;
    private Bitmap rateImg;
    private Bitmap howImg;
    private RectPlayer playP;

    private long startTime;
    private Bitmap fontI ;
    private Rect fond;

    private int x=0;
    Rect play;
    Rect score;
    Rect rate;
    Rect how;
        private SoundPlayer sound;

    public MenuScene(){


            sound = new SoundPlayer(Constants.CURRENT_CONTEXT);
        BitmapFactory bf = new BitmapFactory();


        fontI = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skyf);
        fond = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        playImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.playy);
        scoreImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.podium);
        howImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.how);
        rateImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ratee);
        play = new Rect(2*Constants.CASE_WIDHT-Constants.CASE_WIDHT/4,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT),
                    5*Constants.CASE_WIDHT-Constants.CASE_WIDHT/4,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT-+1.5*Constants.CASE_WIDHT) );

         score= new Rect(5*Constants.CASE_WIDHT-Constants.CASE_WIDHT/4+Constants.CASE_WIDHT*3/2,
                 Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT),
                 8*Constants.CASE_WIDHT-Constants.CASE_WIDHT/4+Constants.CASE_WIDHT*3/2,
                 Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT-+1.5*Constants.CASE_WIDHT) );
         rate=  new Rect(Constants.SCREEN_WIDTH-5*Constants.CASE_WIDHT+Constants.CASE_WIDHT/4,
                 Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT),
                 Constants.SCREEN_WIDTH-2*Constants.CASE_WIDHT+Constants.CASE_WIDHT/4,
                 Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT-+1.5*Constants.CASE_WIDHT) );

        how=  new Rect( 11*Constants.CASE_WIDHT-Constants.CASE_WIDHT/4,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT),
                14*Constants.CASE_WIDHT-Constants.CASE_WIDHT/4,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT-+1.5*Constants.CASE_WIDHT) );

        playP = new RectPlayer(new Rect(3*Constants.CASE_WIDHT,
                Constants.SCREEN_HEIGHT-(int)(0.10*Constants.SCREEN_HEIGHT+1.5*Constants.CASE_WIDHT-Constants.CASE_WIDHT),
                4*Constants.CASE_WIDHT,
                Constants.SCREEN_HEIGHT-(int)(0.10*Constants.SCREEN_HEIGHT+1.5*Constants.CASE_WIDHT) ),Color.BLACK);

    }
    @Override
    public void update() {
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_WIDTH/4000.0f;
        if(elapsedTime>200) elapsedTime=0;
        x=(int)(speed * elapsedTime);

        Constants.S=2;
            playP.setRectangle(new Rect(3*Constants.CASE_WIDHT,
                Constants.SCREEN_HEIGHT-(int)(0.10*Constants.SCREEN_HEIGHT+1.5*Constants.CASE_WIDHT-Constants.CASE_WIDHT),
                4*Constants.CASE_WIDHT,
                Constants.SCREEN_HEIGHT-(int)(0.10*Constants.SCREEN_HEIGHT+1.5*Constants.CASE_WIDHT) ));
        playP.incrementY(x);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(fontI, null, fond, new Paint());

        Paint paint = new Paint();
        paint.setTextSize(Constants.CASE_WIDHT/2);
        paint.setColor(Color.WHITE);
        drawCenterText(canvas,paint,"(C) .CHABOOX 2017",Constants.SCREEN_HEIGHT-Constants.CASE_WIDHT/2);


        paint.setTextSize(2*Constants.CASE_WIDHT);
        paint.setColor(Color.BLACK);


        playP.draw(canvas);
        canvas.drawBitmap(playImg, null, play, new Paint());
        canvas.drawBitmap(scoreImg, null, score, new Paint());
        canvas.drawBitmap(rateImg, null,rate, new Paint());
        canvas.drawBitmap(howImg, null,how, new Paint());
         drawCenterText(canvas,paint,"SwitchMan",2*Constants.CASE_WIDHT);

    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        int gx,gy;

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(SceneManager.ACTIVE_SCENE == 0){
                gx=(int)event.getX();
                gy= (int) event.getY();
                if(play.contains(gx,gy)){
                    sound.playTorSound();
                    int  tuto = Leader.preferences.getInt("TUTO", 0);
                    if(tuto==0) { SceneManager.ACTIVE_SCENE=3;}
                    else
                    {  Constants.S= 1; SceneManager.ACTIVE_SCENE=1;}

            }
                if(rate.contains(gx,gy)){sound.playTorSound();Leader l = new Leader();l.rate(); }
                if(how.contains(gx,gy)){sound.playTorSound(); SceneManager.ACTIVE_SCENE=3;}
                if(score.contains(gx,gy)){sound.playTorSound(); Leader l = new Leader();
                l.affScore();}
        }
    }}

    @Override
    public void reset() {

    }
    private void drawCenterText(Canvas canvas, Paint paint, String text,int yt) {
        Typeface tf =Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(),"fonts/8bitOperatorPlus8-Bold.ttf");

        paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);

        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;

        float y = yt;
        canvas.drawText(text, x, y, paint);

    }
}
