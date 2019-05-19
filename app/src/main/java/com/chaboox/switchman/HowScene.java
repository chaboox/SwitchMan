package com.chaboox.switchman;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;

/**
 * Created by moi on 29/05/2017.
 */

public class HowScene implements Scene {
   ;

    private Rect r = new Rect();

    private ObstacleTutoManager obstacleManager;

    private TutoScene tuto;


    private SoundPlayer sound;
    private long gameStart;
    private long curr;




    private boolean change= false;

    private long startTime;
    private Bitmap fontI ;

    private Rect fond;

 private boolean stop=false;
    private boolean touche=true;
    private boolean ready=false;



    private int b=0;
    Boolean []tabBool = {false,true,false,false,false};
    public HowScene(){
        tuto = new TutoScene();
        sound = new SoundPlayer(Constants.CURRENT_CONTEXT);
        BitmapFactory bf = new BitmapFactory();
        fontI = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skyf);

        fond = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);


     startTime= System.currentTimeMillis();
    }
    @Override
    public void update() {if(!stop)
     if(SceneManager.ACTIVE_SCENE == 3){
      obstacleManager.update();
      if(obstacleManager.playerCollide(obstacleManager.getPlayer2()) ){
       Constants.COLI=true;
          touche = true;

      }

      else if (touche && obstacleManager.playerCollide(obstacleManager.getPlayer()) ) {stop= true;
      touche=false;}

      if(obstacleManager.switchCollide(obstacleManager.getPlayer())){
        change= true;
       startTime= System.currentTimeMillis();
          sound.playSwitchSound();

       if(Constants.SAUT) Constants.SAUT= false; else Constants.SAUT= true;  }

      if(change){
       change =tuto.change((System.currentTimeMillis()-startTime)*(Constants.SCREEN_WIDTH-Constants.SCREEN_WIDTH/3)/1000);

      }

      if(obstacleManager.getPlayer().getRectangle().bottom>=Constants.SCREEN_HEIGHT) {


       SceneManager.ACTIVE_SCENE=2;}


     }

    }
    @Override
    public void draw(Canvas canvas) {  canvas.drawBitmap(fontI, null, fond, new Paint());

     obstacleManager.draw(canvas );

     Paint paint = new Paint();
     paint.setTextSize(Constants.SCREEN_HEIGHT/8);
     paint.setColor(Color.BLACK);
     Typeface tf =Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(),"fonts/8bitOperatorPlus8-Regular.ttf");

     paint.setTypeface(tf);
        if(!ready)
        drawCenterText(canvas,paint,"How to play",2*Constants.CASE_WIDHT);
        else{
            curr= System.currentTimeMillis();
        if(curr-gameStart>=0 && curr-gameStart<=3200) {
            drawCenterText(canvas,paint,"YOU ARE READY NOW",3*Constants.CASE_WIDHT);
        }
            if(curr-gameStart>=1500 && curr-gameStart<=3200) {
                drawCenterText(canvas,paint,"Not sure :3",5*Constants.CASE_WIDHT);
            }
            if(curr-gameStart>=3700) {
                int  tuto = Leader.preferences.getInt("TUTO", 0);
                if(tuto==0) { SceneManager.ACTIVE_SCENE=1; Constants.S= 1; Leader.editor.putInt("TUTO", 1);
                }
                else
                SceneManager.ACTIVE_SCENE =0;

            }
        }


      tuto.draw(canvas);




    }
    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {  int gx;

     if(event.getAction()==MotionEvent.ACTION_DOWN) {

      {if(stop && b<5) if(tabBool[b]==(event.getX() < Constants.SCREEN_WIDTH / 2)){
          if (SceneManager.ACTIVE_SCENE == 3) {
              stop=false;
              b++;

       gx = (int) event.getX();


       if (gx > Constants.SCREEN_WIDTH / 2) {

        if (Constants.SAUT) Constants.S = 3;
        else Constants.S = 2;
       }
       if (gx <= Constants.SCREEN_WIDTH / 2) {

        if (Constants.SAUT) Constants.S = 2;
        else Constants.S = 3;
       }
      }}
          else sound.playNoSound();
       if(b==5&&stop){ ready=true;  gameStart = System.currentTimeMillis();b++;}


      }
     }

    }

    @Override
    public void reset() {
     tuto.reset();

        ready=false;
        b=0;
        stop= false;


     obstacleManager.cpt=0;
     Constants.upd= true;
     Constants.COLI = true;
     Constants.SCORE = 0;
     Constants.SAUT = false;
     Constants.S=1;
     change= false;
     obstacleManager = new ObstacleTutoManager((int)(0.30*Constants.SCREEN_HEIGHT),new Rect(Constants.SCREEN_WIDTH*2-36*Constants.CASE_WIDHT -Constants.CASE_WIDHT/2 , Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)-Constants.CASE_WIDHT , Constants.SCREEN_WIDTH*2-35*Constants.CASE_WIDHT-Constants.CASE_WIDHT/2,Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)),
             new Rect(Constants.SCREEN_WIDTH*2-36*Constants.CASE_WIDHT -Constants.CASE_WIDHT/2 +Constants.CASE_WIDHT*3/2, Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)-Constants.CASE_WIDHT , Constants.SCREEN_WIDTH*2-35*Constants.CASE_WIDHT-Constants.CASE_WIDHT/2+Constants.CASE_WIDHT*3/2,Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)));

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
