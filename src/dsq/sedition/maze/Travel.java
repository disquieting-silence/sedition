package dsq.sedition.maze;

import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

public class Travel {

    private Travel() {}

    public static Option<Spot> go(final Spot start, final Direction dir, int minX, int maxX, int minZ, int maxZ) {
        final Spot raw = move(start, dir);
        return (raw.x >= minX && raw.x <= maxX && raw.z >= minZ && raw.z <= maxZ) ? new Some<Spot>(raw) : new None<Spot>();
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