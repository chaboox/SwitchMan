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
import android.provider.Settings;
import android.view.MotionEvent;

/**
 * Created by moi on 03/02/2017.
 */
public class  GameplayScene implements Scene {
    private Rect fond;
    private Rect r = new Rect();

    private ObstacleManager obstacleManager;

    private boolean gameOver=false;
    private TutoScene tuto;


    private SoundPlayer sound;
    private long gameStart;
    private long curr;
    private long startTime;

    private Bitmap fontI ;

    private boolean change= false;
    public GameplayScene(){
        tuto = new TutoScene();


        BitmapFactory bf = new BitmapFactory();
         fontI = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skyf);
        fond = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
        sound = new SoundPlayer(Constants.CURRENT_CONTEXT);


        gameStart = System.currentTimeMillis();
    }
    @Override
    public void reset(){
        tuto.reset();



        obstacleManager.cpt=0;
        Constants.upd= true;
        Constants.COLI = true;
        Constants.SCORE = 0;
        Constants.SAUT = false;
        Constants.S=1;
        change= false;
        obstacleManager = new ObstacleManager((int)(0.30*Constants.SCREEN_HEIGHT),new Rect(Constants.SCREEN_WIDTH*2-36*Constants.CASE_WIDHT -Constants.CASE_WIDHT/2 , Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)-Constants.CASE_WIDHT , Constants.SCREEN_WIDTH*2-35*Constants.CASE_WIDHT-Constants.CASE_WIDHT/2,Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)));
        gameStart = System.currentTimeMillis();
    }
    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        int gx;

        if(event.getAction()==MotionEvent.ACTION_DOWN) {

            {if (SceneManager.ACTIVE_SCENE == 1) {
                gx = (int) event.getX();


                if (gx > Constants.SCREEN_WIDTH / 2) {

                    if (Constants.SAUT) Constants.S = 3;
                    else Constants.S = 2;
                }
                if (gx <= Constants.SCREEN_WIDTH / 2) {

                    if (Constants.SAUT) Constants.S = 2;
                    else Constants.S = 3;
                }
            }
            if (gameOver) {
                reset();
                gameOver = false;
            }


        }
        }

    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(fontI, null, fond, new Paint());


        obstacleManager.draw(canvas );
        Paint paint = new Paint();
        paint.setTextSize(Constants.SCREEN_HEIGHT/8);
        paint.setColor(Color.BLACK);
        Typeface tf =Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(),"fonts/8bitOperatorPlus8-Regular.ttf");

        paint.setTypeface(tf);
        canvas.drawText(""+ Constants.SCORE, Constants.SCREEN_WIDTH/2 ,Constants.SCREEN_HEIGHT/7 ,paint);


         curr= System.currentTimeMillis();
        if(curr-gameStart>=0 && curr-gameStart<=3700) {
            tuto.draw(canvas);
        }



         if(curr-gameStart>=2200 && curr-gameStart<=3100) {

            paint.setColor(Color.BLACK);
            paint.setTextSize(Constants.CASE_WIDHT);
            drawCenterText(canvas, paint,"Start");
        }




    }


    @Override
    public void update() {
        if(SceneManager.ACTIVE_SCENE == 1){
            obstacleManager.update();
            if(obstacleManager.playerCollide(obstacleManager.getPlayer())){
                Constants.COLI=true;

            }
            else Constants.COLI=false;
            if(obstacleManager.switchCollide(obstacleManager.getPlayer())){

                startTime= System.currentTimeMillis();

                sound.playSwitchSound();
                if(Constants.SAUT) Constants.SAUT= false; else Constants.SAUT= true;  }



            if(obstacleManager.getPlayer().getRectangle().bottom>=Constants.SCREEN_HEIGHT) {
                sound.playFallSound();

                SceneManager.ACTIVE_SCENE=2;}


        }

    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        Typeface tf =Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(),"fonts/8bitOperatorPlus8-Bold.ttf");

        paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.LEFT);

        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
         float y = cHeight / 2f + r.height() / 2f - r.bottom;

        canvas.drawText(text, x, y, paint);

    }



}
