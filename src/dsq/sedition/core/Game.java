package dsq.sedition.core;

import dsq.sedition.scene.Camera;
import dsq.sedition.sprite.Sprite;

import java.util.List;

public interface Game {
    void turnLeft();
    void turnRight();
    void speedUp();
    void slowDown();
    void holdPace();

    void update();

    void stopTurning();

    Player player();
    Camera camera();
    List<Sprite> sprites();

    List<Sprite> allSprites();
}
