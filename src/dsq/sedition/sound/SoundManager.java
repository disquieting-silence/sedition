package dsq.sedition.sound;

public interface SoundManager {

    void playHitWall();
    void playRunning(float speed);
    void playStop();
    void playMusic();

    void init();
}
