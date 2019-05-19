package com.chaboox.switchman;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by moi on 03/02/2017.
 */
public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void recieveTouch(MotionEvent event);
    public void reset();
}
