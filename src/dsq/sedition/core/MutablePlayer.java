package dsq.sedition.core;

import dsq.sedition.collision.Collidable;
import dsq.sedition.scene.Coordinate;

import java.util.List;

public interface MutablePlayer extends Player {
    void setTurnRate(float amount);
    void setAcceleration(float amount);
    void update(final List<Collidable> obstacles);

    void setPosition(Coordinate start);
    void setDirection(float degrees);
}
