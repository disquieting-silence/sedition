package dsq.sedition.core;

import dsq.sedition.collision.Collidable;
import dsq.sedition.collision.Line2D;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.sprite.*;
import dsq.sedition.util.Roofs;
import dsq.sedition.util.Walls;

import java.util.ArrayList;
import java.util.List;

public class DefaultLevel implements Level {

    private final List<Wall> walls;
    private final List<Roof> roofs;
    private final List<Collidable> obstacles;
    private final Coordinate start;
    private final Coordinate finish;

    // FIX 11/06/12 Should floor be here?        
    public DefaultLevel(final Coordinate start, final Coordinate finish, final List<Line2D> lines) {
        this.start = start;
        this.finish = finish;
        walls = Walls.walls(lines);
        roofs = Roofs.roofs(lines);
        obstacles = new ArrayList<Collidable>();
        obstacles.addAll(walls);
    }

    @Override
    public List<Sprite> sprites(final ViewState viewState) {
        return viewState == ViewState.TOP ? topView() : playerView();        
    }

    // FIX 16/08/12 Clean up.
    private List<Sprite> playerView() {
        final List<Sprite> r = new ArrayList<Sprite>(walls);
        final Colour white = new DefaultColour(1.0f, 1.0f, 1.0f, 1.0f);
        r.add(new DefaultMarker(new Coordinate(finish.x, 0.05f, finish.z), 0.05f, white));
        return r;
    }

    // FIX 16/08/12 Clean up.
    private List<Sprite> topView() {
        final List<Sprite> r = new ArrayList<Sprite>(roofs);
        Sprite marker = new PlayerMarker(start, 10, new DefaultColour(1.0f, 0.0f, 0.0f, 1));
        r.add(marker);
        Sprite checkpoint = new PlayerMarker(finish, 10, new DefaultColour(0.0f, 1.0f, 0.0f, 1));
        r.add(checkpoint);
        return r;
    }

    @Override
    public List<Collidable> obstacles(final ViewState viewState) {
        return viewState == ViewState.TOP ? new ArrayList<Collidable>() : new ArrayList<Collidable>(obstacles);
    }

    @Override
    public ArrayList<Sprite> allSprites() {
        final ArrayList<Sprite> r = new ArrayList<Sprite>();
        r.addAll(roofs);
        r.addAll(walls);
        return r;
    }

    @Override
    public Coordinate start() {
        return start;
    }
}
