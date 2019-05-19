package com.chaboox.switchman;



import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;
import android.preference.PreferenceManager;

import com.google.android.gms.games.Games;


/**
 * Created by moi on 04/06/2017.
 */

public class Leader
         {

    public final static String BSCORE ="le score";

    public  static SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Constants.CURRENT_CONTEXT);
    public static SharedPreferences.Editor editor = preferences.edit();
    private static int  prScore = preferences.getInt(BSCORE, 0);


    public Leader(){

    }






    public void affScore(){
        if(!MainActivity.mGoogleApiClient.isConnected())  MainActivity.mGoogleApiClient.connect();
       if(MainActivity.mGoogleApiClient.isConnected())

        Constants.CURRENT_ACTIVITY.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(MainActivity.mGoogleApiClient,
                "CgkIuffC3J8GEAIQDw" ), 1);
    }
    public void saveLeaderScore(int score){

        prScore = preferences.getInt(BSCORE, 0);
        if(prScore<Constants.SCORE){
            editor.putInt(BSCORE, Constants.SCORE);
            if(MainActivity.mGoogleApiClient.isConnected()) {
                Games.Leaderboards.submitScore(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQDw", score);
                Constants.ON_LIGNE=true;
            }else Constants.ON_LIGNE= false;
            editor.commit();}


    }
             public void rate(){
               Intent rateApp = new Intent(Intent.ACTION_VIEW);
                 rateApp.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.chaboox.switchman"));
                 Constants.CURRENT_ACTIVITY.startActivity(rateApp);
             }



}
