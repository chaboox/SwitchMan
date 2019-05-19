package com.chaboox.switchman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by moi on 27/01/2017.
 */
public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Switch> switchs;
   private RectPlayer player;
    private int obstacleHeight;
    private int color;
    private long startTime;
    private boolean facile=true;

   private int x=0;
    public static int cpt=0;

    private int zero =0;
    private int color2;

    public ObstacleManager(int obstacleHeight,Rect rectP){


        this.obstacleHeight = obstacleHeight;

        startTime= System.currentTimeMillis();
        obstacles = new ArrayList<>();
        player = new RectPlayer(rectP,color2);
        switchs = new ArrayList<>();
        populateObstacles();
    }

    public boolean playerCollide(RectPlayer player){
        for(Obstacle ob : obstacles){
            if(ob.playerCollide(player)) return true;
        }
        return false;
    }
    public boolean switchCollide(RectPlayer player){
        for(Switch ob : switchs){
            if(ob.switchCollide(player)){  ob.visible= false; return true;}
        }
        return false;
    }
    public RectPlayer getPlayer(){
        return player;
    }

    private void populateObstacles(){
        int rand;
        int currY =(int)(Constants.SCREEN_WIDTH*1.5);
        while (currY > 0){  if (currY > Constants.SCREEN_WIDTH*3/2)

            rand = (int)(Math.random()*(3));

            else  rand =0;
          if (rand == 2) zero = 1; else zero = 0;
            obstacles.add(new Obstacle(obstacleHeight,color,Constants.CASE_WIDHT,currY,rand*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero));

            currY-= (Constants.CASE_WIDHT*2+rand*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero);
        }

    }


    public void update(){
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        if(elapsedTime>100) elapsedTime=0;
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_WIDTH/3500.0f;

        x=(int)(speed * elapsedTime);

       if(obstacles.size()!=0){
        player.incrementY(x);
        if(Constants.upd){
        for (Obstacle ob:obstacles) {
            ob.incrementY(x);

        }
        for (Switch ob:switchs) {
            ob.incrementY(x);

        }}}





       if(obstacles.get(obstacles.size()-1).getRectangle2().right<=0){
           int rands = (int)(Math.random()*(4));
          if(facile)
           if(Constants.SCORE<1)rands = (int)(Math.random()*(7));
           else facile=false;
            int un = 1;
            int rand=(int)(Math.random()*(3));
         ;
           if (rand == 2) zero = 1; else zero = 0;
           if(rand == 0) un = 0;
           if(rands==1)
           switchs.add(0, new Switch((int)(obstacleHeight+(un+rand)*Constants.CASE_WIDHT*1.5),
                   color2,Constants.CASE_WIDHT,
                   obstacles.get(0).getRectangle2().left+rand*Constants.CASE_WIDHT*2-rand*Constants.CASE_WIDHT+Constants.CASE_WIDHT*2-zero*Constants.CASE_WIDHT/2+Constants.CASE_WIDHT*zero));
            obstacles.add(0, new Obstacle(obstacleHeight,
                    color,Constants.CASE_WIDHT,
                    obstacles.get(0).getRectangle2().right+rand*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                    rand*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
            if(switchs.size()>0)
           if(switchs.get(switchs.size()-1).getRect().right<=0) switchs.remove(switchs.size()-1);
            obstacles.remove(obstacles.size()-1);
           if(cpt<14) { cpt++;}

        }

    }
    public void draw(Canvas canvas){
        player.draw(canvas);
        for (Obstacle ob:obstacles)
            ob.draw(canvas);
        for (Switch ob:switchs)
            ob.draw(canvas);


    }
}
