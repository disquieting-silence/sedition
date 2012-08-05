package dsq.sedition.event;

import android.content.Context;
import dsq.sedition.core.Player;
import dsq.sedition.sound.NoSoundManager;
import dsq.sedition.sound.SoundManager;

public class DefaultEventListener implements EventListener {
    
    private final SoundManager sounds;

    public DefaultEventListener(final Context context) {
        sounds = new NoSoundManager();
    }

    @Override
    public void startGame() {
        sounds.playMusic();
    }

    @Override
    public void collision(final Player player) {
        sounds.playHitWall();
    }

    @Override
    public void stop(final Player player) {
        sounds.playStop();
    }

    @Override
    public void moving(final Player player) {
        sounds.playRunning(player.getSpeed());
    }

    @Override
    public void init() {
        sounds.init();
    }
}
