package dsq.sedition.core;

import dsq.sedition.options.Options;
import dsq.sedition.scene.Camera;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.timer.Timer;

import java.util.List;

public interface Game {
    void turnLeft();
    void turnRight();
    void speedUp();
    void slowDown();
    void holdPace();
    
    void setOptions(final Options options);
    Options getOptions();

    void update();

    void stopTurning();

    Player player();
    Camera camera();
    List<Sprite> sprites();
    Timer timer();

    List<Sprite> allSprites();
    ViewState currentView();

    // FIX 18/08/12 Not happy with this mutability either.
    void skipToGround();
}
