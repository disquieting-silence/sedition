package dsq.sedition.maze.level;

import dsq.sedition.collision.Line2D;
import dsq.sedition.maze.data.Spot;

import java.util.List;

public interface MazeLevel {
    List<Line2D> walls();
    Spot start();
    Spot finish();
}
