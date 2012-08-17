package dsq.sedition.core;

import dsq.sedition.scene.Coordinate;

public interface Player {
    public static final float MAX_SPEED = 0.30f;

    Coordinate getPosition();
    float getDirection();
    float getSpeed();
}
