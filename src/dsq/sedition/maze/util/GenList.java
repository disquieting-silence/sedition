package dsq.sedition.maze.util;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Spot;

import java.util.ArrayList;
import java.util.List;

public class GenList {

    private GenList() { }

    public static List<Spot> vertical(final Bounds bounds) {
        final List<Spot> r = new ArrayList<Spot>();
        for (int i = 0; i < bounds.height; i++) {
            for (int j = 0; j <= bounds.width; j++) {
                r.add(new Spot(j, i));
            }
        }
        return r;
    }

    public static List<Spot> horizontal(final Bounds bounds) {
        final List<Spot> r = new ArrayList<Spot>();
        for (int i = 0; i <= bounds.height; i++) {
            for (int j = 0; j < bounds.width; j++) {
                r.add(new Spot(j, i));
            }
        }
        return r;
    }
}
