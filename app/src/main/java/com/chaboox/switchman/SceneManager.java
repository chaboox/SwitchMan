package com.chaboox.switchman;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by moi on 03/02/2017.
 */
public class SceneManager {
    private boolean pre=true;
    private boolean preH=true;
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static  int ACTIVE_SCENE;

    public SceneManager(){
        ACTIVE_SCENE = 4;

        scenes.add(0,new logoScene());
        scenes.add(0,new HowScene());

        scenes.add(0, new GameOverScene());
        scenes.add(0,new GameplayScene());

        scenes.add(0, new MenuScene());

    }

    public void recieveTouch(MotionEvent event){
        scenes.get(ACTIVE_SCENE).recieveTouch(event);

    }


    public void update(){
        if(scenes.size()==5 && ACTIVE_SCENE==0)scenes.remove(4);
        if(ACTIVE_SCENE==1 && pre){pre = false; scenes.get(ACTIVE_SCENE).reset();}

        if(ACTIVE_SCENE==2 &&pre== false) pre=true;
        if(ACTIVE_SCENE==3 && preH){preH = false; scenes.get(ACTIVE_SCENE).reset();}

        if(ACTIVE_SCENE==0 &&preH== false) preH=true;
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas){


            scenes.get(ACTIVE_SCENE).draw(canvas);
    }

}
