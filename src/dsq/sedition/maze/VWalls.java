package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;

import java.util.Comparator;

public class VWalls implements Walls {

    @Override
    public boolean adjoined(final Spot w1, final Spot w2) {
        return w1.x == w2.x && w2.z - w1.z == 1;
    }

    @Override
    public Line2D nu(final Spot w1, final Spot w2) {
        return new Line2D(w1.x, w1.z, w1.x, w2.z + 1);
    }

    @Override
    public Comparator<Spot> comparator() {
        return new Comparator<Spot>() {
            @Override
            public int compare(final Spot a, final Spot b) {
                return a.x == b.x ? a.z - b.z : a.x - b.x;
            }
        };
    }
}
