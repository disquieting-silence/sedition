package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;

import java.util.ArrayList;
import java.util.List;

public class Joiner {
    private Joiner() { }
    
    
    public static List<Line2D> join(final List<Spot> spots, final Walls walls) {
        final List<Line2D> r = new ArrayList<Line2D>();
        
        final int index = common(spots, walls);
        // FIX 15/08/12 Invariant: index will be -1, or 1+ (look at code for commonH)
        if (index > -1) {
            final List<Spot> current = spots.subList(0, index);
            final List<Spot> after = spots.subList(index, spots.size());

            final Spot first = current.get(0);
            final Spot last = current.get(current.size() - 1);
            final Line2D wall = walls.nu(first, last);
            r.add(wall);
            final List<Line2D> rest = join(after, walls);
            r.addAll(rest);
        }

        return r;
    }
    private static int common(final List<Spot> spots, final Walls walls) {
        if (spots.size() == 0) return -1;
        Spot prev = spots.get(0);
        for (int i = 1; i < spots.size(); i++) {
            final Spot s = spots.get(i);
            if (!walls.adjoined(prev, s)) return i;
            prev = s;
        }
        // FIX 15/08/12 Not sure about this value.
        return spots.size();
    }
}
