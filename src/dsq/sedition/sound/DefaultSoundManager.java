package dsq.sedition.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import dsq.sedition.R;

import java.util.Date;

/* TODO: This stuff continues even when I exit the application !!!! */
public class DefaultSoundManager implements SoundManager {

    public static final int UNDEFINED = -1;
    private MediaPlayer player;


    private static final int MAX_STREAMS = 4;
    private static final int STREAM_TYPE = AudioManager.STREAM_MUSIC;
    private static final int QUALITY = 100;

    private final SoundPool pool = new SoundPool(MAX_STREAMS, STREAM_TYPE, QUALITY);
    
    // FIX 30/06/12 Use an option ....
    private int runningId = UNDEFINED;

    private int soundHitWall;
    private int soundSteps;
    private final Context context;

    public DefaultSoundManager(final Context context) {
        this.context = context;
    }

    @Override
    public void playHitWall() {
        final float volume = getVolume();
        pool.play(soundHitWall, volume, volume, 5, 0, 0.5f);
    }
    
    private float getVolume() {
        final AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        final int current = manager.getStreamVolume(STREAM_TYPE);
        final int max = manager.getStreamMaxVolume(STREAM_TYPE);
        return 1.0f;
    }

    @Override
    public void playRunning(final float speed) {
        final float volume = getVolume();
        if (runningId == UNDEFINED) runningId = pool.play(soundSteps, volume, volume, 1, -1, 1.5f);
        else pool.setRate(runningId, speed / 0.1f);
    }

    @Override
    public void playStop() {
        if (runningId != UNDEFINED) pool.stop(runningId);
        runningId = UNDEFINED;         
    }

    @Override
    public void playMusic() {
        Log.v("SEDITION", new Date() + " sound pool: " + pool);
        player.start();
    }

    @Override
    public void init() {
        Log.v("SEDITION", new Date() + " init called.");
        player = MediaPlayer.create(context, R.raw.celtic_a);

        soundHitWall = pool.load(context, R.raw.crash, 5);
        soundSteps = pool.load(context, R.raw.footsteps, 1);
        Log.v("SEDITION", new Date() + " Initialised sounds");

    }
}
