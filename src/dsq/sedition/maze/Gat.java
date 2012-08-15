package dsq.sedition.maze;

import java.util.List;
import java.util.Map;

// FIX 15/08/12 Rename me.
public class Gat {

    private Gat() {}

    public static Spot gat(final Spot start, final Direction dir) {
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





*/