package com.chaboox.switchman;

/**
 * Created by moi on 07/02/2017.
 */


        import android.content.Context;
        import android.media.AudioAttributes;
        import android.media.AudioManager;
        import android.media.SoundPool;
        import android.os.Build;
        import android.util.Log;


public class SoundPlayer {

   private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 2;

    private static SoundPool soundPool;
    private static int switchSound;
    private static int torSound;
    private static int fallSound;
    private static int no;

   public SoundPlayer(Context context) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();

        } else {

            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC, 0);
        }


        switchSound = soundPool.load(context, R.raw.elec3, 1);
        torSound = soundPool.load(context, R.raw.torpedo, 1);
        fallSound = soundPool.load(context, R.raw.hit, 1);
        no = soundPool.load(context, R.raw.no, 1);


    }

    public void playSwitchSound() {


        soundPool.play(switchSound, 0.1f, 0.1f, 1, 0, 1.0f);
    }

    public void playTorSound() {
        soundPool.play(torSound, 0.2f, 0.2f, 1, 0, 1.0f);
    }
    public void playNoSound() {
        soundPool.play(no, 0.2f, 0.2f, 1, 0, 1.0f);
    }
    public void playFallSound() {
        soundPool.play(fallSound, 0.2f, 0.2f, 1, 0, 1.0f);
    }

}
