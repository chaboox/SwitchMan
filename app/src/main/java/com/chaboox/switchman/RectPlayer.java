package com.chaboox.switchman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by moi on 26/01/2017.
 */
public class RectPlayer implements GameObject {
   private Rect rectangle;
    private int color;

    double s=1;

    private int x=0;
    private double yp=0;
    private boolean fall= false;



    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;

    private AnimationManager animManager ;

    private int state= 2;
    public static boolean TrueScore= false;


    private Bitmap idleImg ;
    private Bitmap walk1;
    private Bitmap walk2 ;
    private Bitmap walk3 ;
    private Bitmap walk4 ;
    public void setRectangle(Rect rec){rectangle=rec;}
    public Rect getRectangle(){
        return rectangle;
    }

    public RectPlayer(Rect rectangle,int color){

         this.rectangle=rectangle;
        this.color=color;
        BitmapFactory bf = new BitmapFactory();

             idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.walid22);
             walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.walidsaut2);
             walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.walidsaut2);
             walk3 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.walid11);
             walk4 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.walid22);
            idle = new Animation(new Bitmap[]{idleImg}, 2);
            walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);
            walkLeft = new Animation(new Bitmap[]{walk3, walk4}, 0.5f);

            animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});
}



    public void incrementY(int y){


        if(rectangle.bottom == (int)(Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)) && Constants.COLI== false){
            fall = true; x=0;
        }
          x=y+x;
        if(!fall){

        if( s== 1 && Constants.S != 1){

            x=0;

            s = Constants.S; Constants.S=1;}
        if(x<2*s*Constants.CASE_WIDHT){
           if(s==2 || s== 3)
           { if(x<s*Constants.CASE_WIDHT && Constants.S !=1) Constants.S = 1;
               if(x>s*Constants.CASE_WIDHT )
                   state= 1;
             yp=1.5*Math.sqrt(s*s*Constants.CASE_WIDHT*Constants.CASE_WIDHT-(x-s*Constants.CASE_WIDHT)*(x-s*Constants.CASE_WIDHT));
               if (yp>s*Constants.CASE_WIDHT && TrueScore)if(ObstacleManager.cpt>13){Constants.SCORE++; TrueScore=false;}}
           else {yp=0 ;state=2; }
         }
        else{state=2;TrueScore= true; x=(int)(x-2*s*Constants.CASE_WIDHT);  yp=0 ; s=Constants.S ; Constants.S =1; }



        rectangle.top =(int)(Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)-Constants.CASE_WIDHT-yp);
            rectangle.bottom=(int)(Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)-yp);
            animManager.playAnim(state);
            animManager.update();
       }
   else {
            state=1;
             Constants.upd = false;
            rectangle.top =(int)(Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)-Constants.CASE_WIDHT+2*x);
            rectangle.bottom= (int)(Constants.SCREEN_HEIGHT-(int)(0.30*Constants.SCREEN_HEIGHT)+2*x);
            animManager.playAnim(state);
            animManager.update();
        }





    }
    @Override public void draw(Canvas canvas){

        animManager.draw(canvas, rectangle);

    }
    @Override public void update(){



    }
    public void update(Point point){
        rectangle.set(point.x-rectangle.width()/2,point.y-rectangle.height()/2,point.x+rectangle.width()/2,point.y+rectangle.height()/2);
    }
}
