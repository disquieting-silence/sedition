package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;

import java.util.Comparator;

public interface Walls {
    boolean adjoined(final Spot w1, final Spot w2);
    Line2D nu(final Spot w1, final Spot w2);
    Comparator<Spot> comparator();
}
