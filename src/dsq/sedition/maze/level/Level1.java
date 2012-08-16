package dsq.sedition.maze.level;

import dsq.sedition.collision.Line2D;
import dsq.sedition.core.DefaultLevel;
import dsq.sedition.core.Level;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.level.MazeLevel;
import dsq.sedition.maze.level.RandomLevel;
import dsq.sedition.scene.Coordinate;

import java.util.ArrayList;
import java.util.List;

// FIX 16/08/12 I think this structure needs revisiting.
public class Level1  {
    
    private static final int MARGIN = 4;
    
    private Level1() {}
    
    public static Level level() {
        final int width = 10;
        final int height = 10;
        final MazeLevel level = new RandomLevel(width, height);
        final List<Line2D> walls = level.walls();
        final List<Line2D> scaled = new ArrayList<Line2D>();
        for (final Line2D w : walls) {
            final float x1 = (w.x1 - width / 2) * MARGIN;
            final float x2 = (w.x2 - width / 2) * MARGIN;
            final float z1 = (w.z1 - height / 2) * MARGIN;
            final float z2 = (w.z2 - height / 2) * MARGIN;
            scaled.add(new Line2D(x1, z1, x2, z2));
        }

        final Coordinate start = coord(level.start(), width, height);
        final Coordinate finish = coord(level.finish(), width, height);
        return new DefaultLevel(start, finish, scaled);
    }

    private static Coordinate coord(final Spot spot, final int width, final int height) {
        int x = spot.x - width/2;
        int z = spot.z - height/2;
        return new Coordinate(x * MARGIN + 0.5f, 0, z * MARGIN + 0.5f);
    }

}
