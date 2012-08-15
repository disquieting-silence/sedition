package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;

import java.util.List;

public interface MazeLevel {
    List<Line2D> walls();
    Spot start();
    Spot finish();
}
