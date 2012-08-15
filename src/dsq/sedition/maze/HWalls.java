package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;

import java.util.Comparator;

public class HWalls implements Walls {

    @Override
    public boolean adjoined(final Spot w1, final Spot w2) {
        return w1.z == w2.z && w2.x - w1.x == 1;
    }

    @Override
    public Line2D nu(final Spot w1, final Spot w2) {
        return new Line2D(w1.x, w1.z, w2.x + 1, w1.z);
    }

    @Override
    public Comparator<Spot> comparator() {
        return new Comparator<Spot>() {
            @Override
            public int compare(final Spot a, final Spot b) {
                return a.z == b.z ? a.x - b.x : a.z - b.z;
            }
        };
    }
}
