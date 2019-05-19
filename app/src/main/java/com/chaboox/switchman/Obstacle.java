package com.chaboox.switchman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by moi on 26/01/2017.
 */
public class Obstacle implements GameObject {
    private Rect rectangle;
    private Rect rectangle2;


    private Bitmap islande1;
    private Bitmap islande2;

    public Rect getRectangle2(){
        return rectangle2;
    }
    public void incrementY(float y){

        rectangle.right -=y;
        rectangle.left-=y;
        rectangle2.right-=y;
        rectangle2.left-=y;



    }

    public Obstacle(int rectHeight ,int color,int size,int startY,int playerGap){
        BitmapFactory bf = new BitmapFactory();
        if(playerGap==0) {
            islande1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.isq1);
            islande2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.isq2);

        }
        else {
            islande1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.islande1);
            islande2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.islande2);
        }

       rectangle= new Rect(startY-2*size-playerGap,
               Constants.SCREEN_HEIGHT-rectHeight,
               startY-size-playerGap,
               Constants.SCREEN_HEIGHT-rectHeight+2*Constants.CASE_WIDHT);
        rectangle2 = new Rect(startY-size,
                Constants.SCREEN_HEIGHT-rectHeight
                ,startY,
                Constants.SCREEN_HEIGHT-rectHeight+2*Constants.CASE_WIDHT);
    }
   public boolean playerCollide(RectPlayer player){
    if(( player.getRectangle().bottom >=rectangle.top && player.getRectangle().right>rectangle.left && player.getRectangle().right<=rectangle.right
     ||player.getRectangle().right>rectangle2.left && player.getRectangle().right<=rectangle2.right )
            || player.getRectangle().left>=rectangle.left && player.getRectangle().left<rectangle.right) return  true;
      else return false;

   }

    @Override
    public void draw(Canvas canvas) {



        canvas.drawBitmap(islande1, null, rectangle, new Paint());
        canvas.drawBitmap(islande2, null, rectangle2, new Paint());
    }

    @Override
    public void update() {

    }
}
