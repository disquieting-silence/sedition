package dsq.sedition.core;

import dsq.sedition.collision.Collidable;
import dsq.sedition.collision.Collisions;
import dsq.sedition.event.EventListener;
import dsq.sedition.scene.Coordinate;

import java.util.List;

public class DefaultPlayer implements MutablePlayer {

    private Coordinate pos;
    private float direction;
    private float speed;
    private float dirSpeed;
    private float acceleration;
    private final EventListener events;

    public DefaultPlayer(final Coordinate pos, final float direction, final EventListener events) {
        this.pos = pos;
        this.direction = direction;
        this.events = events;
    }

    @Override
    public Coordinate getPosition() {
        return pos;
    }

    @Override
    public float getDirection() {
        return direction;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void setTurnRate(final float amount) {
        // FIX 3/06/12 Rename some of these things.
        dirSpeed = amount;
    }

    @Override
    public void setAcceleration(final float amount) {
        acceleration = amount;
    }

    @Override
    public void update(final List<Collidable> obstacles) {
        updateSpeed();
        updateDirection();
        final Coordinate newPos = newPosition();
        final boolean collision = Collisions.hits(newPos, obstacles, MAX_SPEED);
        if (collision) {
            events.collision(this);
            speed = 0;
        }
        pos = collision ? pos : newPos;
    }

    private void updateSpeed() {
        speed += acceleration;
        speed = Math.max(0, Math.min(speed, MAX_SPEED));
        if (speed == 0) events.stop(this);
        else events.moving(this);
    }

    private Coordinate newPosition() {
        float z = pos.z - (float) Math.cos(direction * Math.PI / 180) * speed;
        float x = pos.x + (float) Math.sin(direction * Math.PI / 180) * speed;
        return new Coordinate(x, 0, z);
    }

    private void updateDirection() {
        direction += dirSpeed;
        if (direction < 0.0) direction += 360.0f;
        if (direction > 360) direction -= 360.0f;
    }
}
