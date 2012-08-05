package dsq.sedition.core;

import dsq.sedition.collision.Collidable;

import java.util.List;

public interface MutablePlayer extends Player {
    void setTurnRate(float amount);
    void setAcceleration(float amount);
    void update(final List<Collidable> obstacles);
}
