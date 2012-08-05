package dsq.sedition.util;

import dsq.sedition.collision.Line2D;
import dsq.sedition.sprite.DefaultWall;
import dsq.sedition.sprite.Wall;

import java.util.ArrayList;
import java.util.List;

public class Walls {

    public static final int WALL_HEIGHT = 10;

    private Walls() {}
    
    public static List<Wall> walls(final List<Line2D> lines) {
        final List<Wall> r = new ArrayList<Wall>();
        for (Line2D line : lines) {
            r.add(new DefaultWall(line.x1, line.z1, line.x2, line.z2, WALL_HEIGHT));
        }
        return r;
    }
}
