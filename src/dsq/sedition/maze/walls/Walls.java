package dsq.sedition.maze.walls;

import dsq.sedition.collision.Line2D;
import dsq.sedition.maze.data.Spot;

import java.util.Comparator;

public interface Walls {
    boolean adjoined(final Spot w1, final Spot w2);
    Line2D nu(final Spot w1, final Spot w2);
    Comparator<Spot> comparator();
}
