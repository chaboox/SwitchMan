package com.chaboox.switchman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;


public class Switch implements GameObject {


    private Bitmap idleImg;

    public boolean visible= true;
    private Rect rectangle;
    private int color;
    public Switch(int rectHeight ,int color,int size,int startY){
        BitmapFactory bf = new BitmapFactory();
         idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.yo);

        this.color = color;

        rectangle = new Rect(startY-size/2, Constants.SCREEN_HEIGHT-rectHeight-size, startY+size/2,Constants.SCREEN_HEIGHT-rectHeight );
    }
    @Override
    public void draw(Canvas canvas) {
        if(visible) {
            canvas.drawBitmap(idleImg, null, rectangle, new Paint());


        }
    }

    @Override
    public void update() {


    }
    public Rect getRect(){
        return rectangle;
    }
    public void incrementY(float y){

        rectangle.right -=y;
        rectangle.left-=y;


    }
    public boolean switchCollide(RectPlayer player){ //if(player.getRectangle().bottom ==rectangle.top) return true;

        if(!visible) return false;
        return Rect.intersects(rectangle, player.getRectangle()) ;

    }
}
