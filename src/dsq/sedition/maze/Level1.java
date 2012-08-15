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
        final List<Line2D> walls = new LegacyLevel().walls();
        final List<Line2D> scaled = new ArrayList<Line2D>();
        for (final Line2D w : walls) {
            scaled.add(new Line2D(w.x1 * WIDTH, w.z1 * WIDTH, w.x2 * WIDTH, w.z2 * WIDTH));
        }

        return new DefaultLevel(new Coordinate(0.5f * WIDTH, 0, -2.0f * WIDTH), scaled);
    }

}
