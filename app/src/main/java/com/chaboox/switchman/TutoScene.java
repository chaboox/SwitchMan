package com.chaboox.switchman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by moi on 10/02/2017.
 */
public class TutoScene  {

    boolean longLeft= false;
    boolean shortLeft= false;
    Rect longR ;

    Rect shortR ;
    Bitmap longB;
    Bitmap shortB;
    public TutoScene(){
        BitmapFactory bf = new BitmapFactory();
        longB = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.long1);
        shortB = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.short1);
        longR = new Rect(0,Constants.SCREEN_HEIGHT-(int)(0.25*Constants.SCREEN_HEIGHT),
                Constants.SCREEN_WIDTH/3,Constants.SCREEN_HEIGHT-(int)(0.25*Constants.SCREEN_HEIGHT)+Constants.SCREEN_WIDTH/27);
        shortR = new Rect(Constants.SCREEN_WIDTH-Constants.SCREEN_WIDTH/3,Constants.SCREEN_HEIGHT-(int)(0.25*Constants.SCREEN_HEIGHT),
                Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT-(int)(0.25*Constants.SCREEN_HEIGHT)+Constants.SCREEN_WIDTH/27);


    }
 public void reset(){
     shortR.right =Constants.SCREEN_WIDTH;
     shortR.left = Constants.SCREEN_WIDTH-Constants.SCREEN_WIDTH/3;
     longR.right =Constants.SCREEN_WIDTH/3;
     longR.left = 0;

 }


    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        canvas.drawBitmap(longB, null, longR, new Paint());
        canvas.drawBitmap(shortB, null, shortR, new Paint());



    }





public Boolean change(long x){

if(longR.left==0) longLeft = true;
    if(longLeft) {
        longR.right += x;
        longR.left += x;
        shortR.right -= x;
        shortR.left -= x;
        if(shortR.left<=0){
            longR.right =Constants.SCREEN_WIDTH;
            longR.left = Constants.SCREEN_WIDTH-Constants.SCREEN_WIDTH/3;
            shortR.right =Constants.SCREEN_WIDTH/3;
            shortR.left = 0;
            longLeft = false;

        return false;
        }
    }
    if(shortR.left==0) shortLeft = true;
    if(shortLeft) {
        longR.right -= x;
        longR.left -= x;
        shortR.right += x;
        shortR.left += x;
        if(longR.left<=0){
            shortR.right =Constants.SCREEN_WIDTH;
            shortR.left = Constants.SCREEN_WIDTH-Constants.SCREEN_WIDTH/3;
            longR.right =Constants.SCREEN_WIDTH/3;
            longR.left = 0;
            shortLeft = false;

            return false;

        }
    }


return true;
}
}
