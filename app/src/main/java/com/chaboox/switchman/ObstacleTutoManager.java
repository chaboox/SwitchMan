package com.chaboox.switchman;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by moi on 30/05/2017.
 */

public class ObstacleTutoManager     {
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Switch> switchs;
    private RectPlayer player;
    private RectPlayer player2;
    private int obstacleHeight;
    private int color;
    private long startTime;
    private int left=0;
    private int right;
    private int x=0;
    private int i=-1;
    private boolean stop = false;
    public static int cpt=0;

    private int zero =0;
    private int color2;

    public ObstacleTutoManager(int obstacleHeight,Rect rectP,Rect rectP2){


        this.obstacleHeight = obstacleHeight;

        startTime= System.currentTimeMillis();
        obstacles = new ArrayList<>();
        player = new RectPlayer(rectP,color2);
        player2 = new RectPlayer(rectP2,color2);
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
    public RectPlayer getPlayer2(){
        return player2;
    }
    public boolean playerDown(){
        if (player.getRectangle().bottom==Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)) return true;
        else return false;
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
        // int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);

        if(elapsedTime>100) elapsedTime=0;
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_WIDTH/3500.0f;


        x=(int)(speed * elapsedTime);

        if(obstacles.size()!=0){
            player.incrementY(x);
            player2.incrementY(x);
            if(Constants.upd){
                for (Obstacle ob:obstacles) {
                    ob.incrementY(x);

                }
                for (Switch ob:switchs) {
                    ob.incrementY(x);

                }}}





        if(obstacles.get(obstacles.size()-1).getRectangle2().right<=0){
          if(i<23)
            switch (i){
                case -1: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+1*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        1*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 0: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 1: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+2*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        2*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 2: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 3 :
                    switchs.add(0, new Switch((int)(obstacleHeight+(0+0)*Constants.CASE_WIDHT*1.5),
                            color2,Constants.CASE_WIDHT,
                            obstacles.get(0).getRectangle2().left+0*Constants.CASE_WIDHT*2-0*Constants.CASE_WIDHT+Constants.CASE_WIDHT*2-zero*Constants.CASE_WIDHT/2+Constants.CASE_WIDHT*zero));
                    obstacles.add(0, new Obstacle(obstacleHeight,
                            color,Constants.CASE_WIDHT,
                            obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                            0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 4: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 5: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+2*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        2*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 6: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 7 :
                    switchs.add(0, new Switch((int)(obstacleHeight+(0+0)*Constants.CASE_WIDHT*1.5),
                            color2,Constants.CASE_WIDHT,
                            obstacles.get(0).getRectangle2().left+0*Constants.CASE_WIDHT*2-0*Constants.CASE_WIDHT+Constants.CASE_WIDHT*2-zero*Constants.CASE_WIDHT/2+Constants.CASE_WIDHT*zero));
                    obstacles.add(0, new Obstacle(obstacleHeight,
                            color,Constants.CASE_WIDHT,
                            obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                            0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 8: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 9 :
                    switchs.add(0, new Switch((int)(obstacleHeight+(0+0)*Constants.CASE_WIDHT*1.5),
                            color2,Constants.CASE_WIDHT,
                            obstacles.get(0).getRectangle2().left+0*Constants.CASE_WIDHT*2-0*Constants.CASE_WIDHT+Constants.CASE_WIDHT*2-zero*Constants.CASE_WIDHT/2+Constants.CASE_WIDHT*zero));
                    obstacles.add(0, new Obstacle(obstacleHeight,
                            color,Constants.CASE_WIDHT,
                            obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                            0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 10: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 11:
                    switchs.add(0, new Switch((int)(obstacleHeight+(1+2)*Constants.CASE_WIDHT*1.5),
                            color2,Constants.CASE_WIDHT,
                            obstacles.get(0).getRectangle2().left+2*Constants.CASE_WIDHT*2-2*Constants.CASE_WIDHT+Constants.CASE_WIDHT*2-zero*Constants.CASE_WIDHT/2+Constants.CASE_WIDHT*zero));
                    obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+2*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        2*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 12: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 13: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+1*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        1*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 14: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 15: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+1*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        1*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 16: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 17: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 18: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;
                case 19: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;


                case 20: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;


                case 21: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;

                case 22: obstacles.add(0, new Obstacle(obstacleHeight,
                        color,Constants.CASE_WIDHT,
                        obstacles.get(0).getRectangle2().right+0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ,
                        0*Constants.CASE_WIDHT*2+Constants.CASE_WIDHT*zero ));
                    break;


            } i++;


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
