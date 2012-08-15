package dsq.sedition.maze;

import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

import java.util.List;
import java.util.Map;

// FIX 15/08/12 Rename me.
public class Gat {

    private Gat() {}

    public static Option<Spot> gat(final Spot start, final Direction dir, int minX, int maxX, int minZ, int maxZ) {
        final Spot raw = spot(start, dir);
        return (raw.x >= minX && raw.x <= maxX && raw.z >= minZ && raw.z <= maxZ) ? new Some<Spot>(raw) : new None<Spot>();
    }

    private static Spot spot(final Spot start, final Direction dir) {
        switch (dir) {
            case NORTH: return new Spot(start.x, start.z - 1);
            case EAST: return new Spot(start.x + 1, start.z);
            case SOUTH: return new Spot(start.x, start.z + 1);
            case WEST: return new Spot(start.x - 1, start.z);
            default: return start;
        }
    }
}

/*
  generate the vertical and horizontal walls
  generate the path: start, [directions]
  identify which walls are on that path and remove them from the list.

  sort the walls .. .and merge adjacent walls.





*/