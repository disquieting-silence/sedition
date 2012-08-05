package dsq.sedition.core;

import dsq.sedition.scene.Coordinate;

public interface Player {
    Coordinate getPosition();
    float getDirection();
    float getSpeed();
}
