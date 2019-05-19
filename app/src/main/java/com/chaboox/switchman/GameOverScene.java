package com.chaboox.switchman;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import com.google.android.gms.games.Games;


/**
 * Created by moi on 04/02/2017.
 */
public class GameOverScene implements Scene {

    private Bitmap sky ;
    private Bitmap replayImg ;
    private Bitmap scoreImg;
    private Bitmap homeImg;
    private SoundPlayer sound;
    Rect replay;
    Rect score;
    Leader l;
    Rect home;

    private Rect r = new Rect();
    private Rect bl = new Rect(0,0,Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
   public GameOverScene(){

       l = new Leader();
        BitmapFactory bf = new BitmapFactory();
        sound = new SoundPlayer(Constants.CURRENT_CONTEXT);


        replayImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.replay);
        scoreImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.podium);
        homeImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.home);

        sky = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.skyf);

        replay = new Rect(2*Constants.CASE_WIDHT,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT),
                5*Constants.CASE_WIDHT,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT-+1.5*Constants.CASE_WIDHT) );
        home= new Rect(Constants.SCREEN_WIDTH/2-Constants.CASE_WIDHT*3/2,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT),
                Constants.SCREEN_WIDTH/2+Constants.CASE_WIDHT*3/2,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT-+1.5*Constants.CASE_WIDHT) );
        score=  new Rect(Constants.SCREEN_WIDTH-5*Constants.CASE_WIDHT,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT),
                Constants.SCREEN_WIDTH-2*Constants.CASE_WIDHT,
                Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT-+1.5*Constants.CASE_WIDHT) );


    }
    @Override
    public void recieveTouch(MotionEvent event) {
        int gx,gy;

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(SceneManager.ACTIVE_SCENE == 2){
                gx=(int)event.getX();
                gy= (int) event.getY();
                if(replay.contains(gx,gy)){sound.playTorSound(); SceneManager.ACTIVE_SCENE=1;

                }
                if(score.contains(gx,gy)){sound.playTorSound(); Leader l = new Leader();
                    l.affScore();}
                if(home.contains(gx,gy)){ sound.playTorSound(); SceneManager.ACTIVE_SCENE=0;}

            }
        }}

    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        if(MainActivity.mGoogleApiClient.isConnected())

        Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQAw");
        canvas.drawBitmap(sky, null, bl, new Paint());

        paint.setTextSize(3*Constants.CASE_WIDHT);
        paint.setColor(Color.BLACK);

       l.saveLeaderScore(Constants.SCORE);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Constants.CURRENT_CONTEXT);

        int prScore = preferences.getInt(Leader.BSCORE, 0);

        if(MainActivity.mGoogleApiClient.isConnected() && prScore>19)
        Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQBA");
        if(MainActivity.mGoogleApiClient.isConnected() && prScore>49)
            Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQBQ");
        if(MainActivity.mGoogleApiClient.isConnected() && prScore>99)
            Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQBg");
        if(MainActivity.mGoogleApiClient.isConnected() && prScore>199)
            Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQBw");
        if(MainActivity.mGoogleApiClient.isConnected() && prScore>299)
            Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQCA");
        if(MainActivity.mGoogleApiClient.isConnected() && prScore>399)
            Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQCQ");
        if(MainActivity.mGoogleApiClient.isConnected() && prScore>500)
            Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQCg");
        if(MainActivity.mGoogleApiClient.isConnected() && prScore>999)
            Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQCw");
        if(MainActivity.mGoogleApiClient.isConnected() && prScore>1500)
            Games.Achievements.unlock(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQDA");



        drawCenterText2(canvas,paint,"GAME OVER",2*Constants.CASE_WIDHT+Constants.CASE_WIDHT/2);
        paint.setTextSize(2*Constants.CASE_WIDHT);
        drawCenterTextR(canvas, paint, "SCORE : "+Constants.SCORE,Constants.SCREEN_HEIGHT/3+Constants.SCREEN_HEIGHT/10);
        drawCenterTextR(canvas,paint, "  BEST : "+prScore,Constants.SCREEN_HEIGHT/2+Constants.SCREEN_HEIGHT/8);

        canvas.drawBitmap(replayImg, null, replay, new Paint());
        canvas.drawBitmap(scoreImg, null, score, new Paint());
        canvas.drawBitmap(homeImg, null,home, new Paint());

    }

    @Override
    public void update() {


    }

    @Override
    public void terminate() {

    }

    private void drawCenterText2(Canvas canvas, Paint paint, String text,int yt) {
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
    private void drawCenterTextR(Canvas canvas, Paint paint, String text,int yt) {
        Typeface tf =Typeface.createFromAsset(Constants.CURRENT_CONTEXT.getAssets(),"fonts/8bitOperatorPlus8-Regular.ttf");

        paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.LEFT);

        canvas.getClipBounds(r);

        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;

        float y = yt;
        canvas.drawText(text, x, y, paint);

    }
    @Override
    public void reset(){}

}
