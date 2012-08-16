package dsq.sedition.maze.util;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Direction;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

public class Travel {

    private Travel() {}

    public static Option<Spot> go(final Spot start, final Direction dir, final Bounds bounds) {
        final Spot raw = move(start, dir);
        return (raw.x >= bounds.minX && raw.x <= bounds.maxX && raw.z >= bounds.minZ && raw.z <= bounds.maxZ) ?
            new Some<Spot>(raw) : new None<Spot>();
    }

    private static Spot move(final Spot start, final Direction dir) {
        switch (dir) {
            case NORTH: return new Spot(start.x, start.z - 1);
            case EAST: return new Spot(start.x + 1, start.z);
            case SOUTH: return new Spot(start.x, start.z + 1);
            case WEST: return new Spot(start.x - 1, start.z);
            default: return start;
        }
    }
}