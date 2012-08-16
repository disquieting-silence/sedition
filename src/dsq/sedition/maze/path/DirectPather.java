package dsq.sedition.maze.path;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Direction;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.util.Repeat;
import dsq.sedition.util.Option;

import java.util.ArrayList;
import java.util.List;

public class DirectPather implements Pather {
    @Override
    public Path generate(final Spot start, final Option<Spot> finishOption, final Bounds bounds) {
        final Spot finish = finishOption.getOrDie();
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

    private List<Direction> horizontal(final int x1, final int x2) {
        if (x1 < x2) return Repeat.repeat(Direction.EAST, x2 - x1);
        else if (x1 > x2) return Repeat.repeat(Direction.WEST, x1 - x2);
        else return new ArrayList<Direction>();
    }

    private List<Direction> vertical(final int z1, final int z2) {
        if (z1 < z2) return Repeat.repeat(Direction.SOUTH, z2 - z1);
        else if (z1 > z2) return Repeat.repeat(Direction.NORTH, z1 - z2);
        else return new ArrayList<Direction>();
    }
}
