package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;
import dsq.sedition.core.DefaultLevel;
import dsq.sedition.core.Level;
import dsq.sedition.scene.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class Level1  {
    
    private static final int WIDTH = 4;
    
    private Level1() {}
    
    public static Level level() {
        final List<Line2D> r = new ArrayList<Line2D>();
        xWalls(r);
        r.add(z(-18, 1, 0));
        r.add(z(-16, -10, 1));
        r.add(z(-9, -7, 1));
        r.add(z(-4, -1, 1));
        r.add(z(-10, -8, 2));
        r.add(z(-4, -2, 2));
        r.add(z(-10, -8, 3));
        r.add(z(-7, -6, 3));
        r.add(z(-15, -11, 4));
        r.add(z(-6, -5, 4));
        r.add(z(-4, -2, 4));
        r.add(z(-19, -18, 5));
        r.add(z(-13, -7, 5));
        r.add(z(-3, -1, 5));
        r.add(z(-19, -18, 6));
        r.add(z(-15, -14, 6));
        r.add(z(-13, -12, 6));
        r.add(z(-9, -6, 6));
        r.add(z(-3, 1, 6));
        r.add(z(-18, -17, 7));
        r.add(z(-16, -13, 7));
        r.add(z(-8, -7, 7));
        r.add(z(-6, -5, 7));
        r.add(z(-17, -13, 8));
        r.add(z(-12, -10, 8));
        r.add(z(-9, -8, 8));
        r.add(z(-6, -4, 8));
        r.add(z(-18, -3, 9));

        return new DefaultLevel(new Coordinate(0.5f * WIDTH, 0, -2.0f * WIDTH), r);
    }

    private static void xWalls(final List<Line2D> r) {
        r.add(x(0, 5, -18));
        r.add(x(6, 9, -18));
        r.add(x(1, 6, -17));
        r.add(x(7, 8, -17));
        r.add(x(1, 7, -16));
        r.add(x(4, 6, -15));
        r.add(x(6, 7, -14));
        r.add(x(5, 6, -13));
        r.add(x(6, 8, -12));
        r.add(x(1, 4, -11));
        r.add(x(2, 3, -10));
        r.add(x(6, 8, -9));
        r.add(x(2, 3, -8));
        r.add(x(7, 8, -8));
        r.add(x(0, 2, -7));
        r.add(x(3, 5, -7));
        r.add(x(7, 9, -7));
        r.add(x(0, 3, -6));
        r.add(x(4, 6, -6));
        r.add(x(7, 8, -6));
        r.add(x(4, 7, -5));
        r.add(x(1, 2, -4));
        r.add(x(4, 8, -4));
        r.add(x(5, 9, -3));
        r.add(x(3, 4, -2));
        r.add(x(1, 5, -1));
        r.add(x(0, 6, 1));
    }

    private static Line2D x(float x1, float x2, float z) {
        return new Line2D(x1 * WIDTH, z * WIDTH, x2 * WIDTH, z * WIDTH);
    }

    private static Line2D z(float z1, float z2, float x) {
        return new Line2D(x * WIDTH, z1 * WIDTH, x * WIDTH, z2 * WIDTH);
    }
}
