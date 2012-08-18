package dsq.sedition.maze.level;

import dsq.sedition.collision.Collidable;
import dsq.sedition.collision.Line2D;
import dsq.sedition.core.Level;
import dsq.sedition.core.ViewState;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.sprite.*;
import dsq.sedition.util.Colours;
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
    private final Sprite startMarker;
    private final Sprite finishMarker;
    private final Sprite checkpointMarker;
    private static final float TOP_MARKER_Y = 10;
    private static final float PLAYER_MARKER_Y = 0.05f;
    
    private final List<Sprite> groundSprites;
    private final List<Sprite> topSprites;

    // FIX 11/06/12 Should floor be here?        
    public DefaultLevel(final Coordinate start, final Coordinate finish, final List<Line2D> lines) {
        this.start = start;
        this.finish = finish;
        walls = Walls.walls(lines);
        roofs = Roofs.roofs(lines);
        obstacles = new ArrayList<Collidable>();
        obstacles.addAll(walls);
        startMarker = new PlayerMarker(start, TOP_MARKER_Y, Colours.RED);
        finishMarker = new PlayerMarker(finish, TOP_MARKER_Y, Colours.GREEN);
        checkpointMarker = new DefaultMarker(new Coordinate(finish.x, PLAYER_MARKER_Y, finish.z), PLAYER_MARKER_Y, Colours.WHITE);
        
        groundSprites = new ArrayList<Sprite>(walls);
        groundSprites.add(checkpointMarker);
        
        topSprites = new ArrayList<Sprite>(roofs);
        topSprites.add(startMarker);
        topSprites.add(finishMarker);
    }

    @Override
    public List<Sprite> sprites(final ViewState viewState) {
        return viewState == ViewState.TOP ? new ArrayList<Sprite>(topSprites) :new ArrayList<Sprite>(groundSprites);
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
