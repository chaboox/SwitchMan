package com.chaboox.switchman;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameUtils;

public class MainActivity extends Activity  implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    private static int RC_SIGN_IN = 9001;

    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInflow = true;
    private boolean mSignInClicked = false;

    private GamePanel gamePanel;
    public  static GoogleApiClient mGoogleApiClient;
    public static ConnectionResult cR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Constants.CURRENT_ACTIVITY=this;
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
               .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .addScope(Games.SCOPE_GAMES)

                .build();

        DisplayMetrics dm =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        Constants.CASE_WIDHT = (int)(Constants.SCREEN_WIDTH*0.05);

        gamePanel=new GamePanel(this);
        setContentView(gamePanel);


    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode , event);
    }

    @Override
    public void onStart() {

        Constants.PAUSE= false;

        super.onStart();

    }

    @Override
    public void onResume() {

        super.onResume();
        gamePanel.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();

            gamePanel.onPause();
    }

    @Override
    public void onStop() {

        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Constants.CURRENT_ACTIVITY.startActivityForResult(Games.Leaderboards.getLeaderboardIntent(MainActivity.mGoogleApiClient,
                "CgkIuffC3J8GEAIQAg" ), 1);
        if(Constants.ON_LIGNE==false) Leader.preferences.getInt("le score", 0);
        Games.Leaderboards.submitScore(MainActivity.mGoogleApiClient, "CgkIuffC3J8GEAIQAg", Leader.preferences.getInt("le score", 0));
    }

    @Override
    public void onConnectionSuspended(int i) {

        mGoogleApiClient.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        cR=connectionResult;
       if (mResolvingConnectionFailure) {


            return;
        }


        if (mSignInClicked || mAutoStartSignInflow) {

            mAutoStartSignInflow = true;
            mSignInClicked = true;
            mResolvingConnectionFailure = false;



            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, connectionResult,
                    RC_SIGN_IN,1995

            )) {
                mResolvingConnectionFailure = false;
            }
        }


    }
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
       if (requestCode == RC_SIGN_IN) {

            mSignInClicked = false;
            mResolvingConnectionFailure = false;
            if (resultCode == RESULT_OK) {

                mGoogleApiClient.connect();

            } else {

               BaseGameUtils.showActivityResultError(this,
                        requestCode, resultCode, 1995);
            }
        }
    }


}
