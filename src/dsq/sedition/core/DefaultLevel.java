package dsq.sedition.core;

import dsq.sedition.collision.Collidable;
import dsq.sedition.collision.Line2D;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.sprite.PlayerMarker;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.sprite.Roof;
import dsq.sedition.util.Roofs;
import dsq.sedition.util.Walls;
import dsq.sedition.sprite.Wall;

import java.util.ArrayList;
import java.util.List;

public class DefaultLevel implements Level {

    private final List<Wall> walls;
    private final List<Roof> roofs;
    private final List<Collidable> obstacles;
    private final Coordinate start;

    // FIX 11/06/12 Should floor be here?        
    public DefaultLevel(final Coordinate start, final List<Line2D> lines) {
        this.start = start;
        walls = Walls.walls(lines);
        roofs = Roofs.roofs(lines);
        obstacles = new ArrayList<Collidable>();
        obstacles.addAll(walls);
    }

    @Override
    public List<Sprite> sprites(final ViewState viewState) {
        return viewState == ViewState.TOP ? topView() : new ArrayList<Sprite>(walls);        
    }

    private List<Sprite> topView() {
        final List<Sprite> r = new ArrayList<Sprite>(roofs);
        Sprite marker = new PlayerMarker(start);
        r.add(marker);
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
