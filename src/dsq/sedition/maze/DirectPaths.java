package dsq.sedition.maze;

import java.util.ArrayList;
import java.util.List;

public class DirectPaths {

    public static Path calculate(final Spot start, final int width, final int height, final Spot finish) {
        final List<Direction> r = new ArrayList<Direction>();
        final List<Direction> h = horizontal(start.x, finish.x);
        final List<Direction> v = vertical(start.z, finish.z);
        
        if (Math.random() > 0.5) {
            r.addAll(h);
            r.addAll(v);
        } else {
            r.addAll(v);
            r.addAll(h);
        }
        return new Path(start, r, finish);
    }
    
    private static List<Direction> horizontal(final int x1, final int x2) {
        if (x1 < x2) return Repeat.repeat(Direction.EAST, x2 - x1);
        else if (x1 > x2) return Repeat.repeat(Direction.WEST, x1 - x2);
        else return new ArrayList<Direction>();
    }

    private static List<Direction> vertical(final int z1, final int z2) {
        if (z1 < z2) return Repeat.repeat(Direction.SOUTH, z2 - z1);
        else if (z1 > z2) return Repeat.repeat(Direction.NORTH, z1 - z2);
        else return new ArrayList<Direction>();
    }
}
