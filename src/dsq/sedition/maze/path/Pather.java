package dsq.sedition.maze.path;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.util.Option;

public interface Pather {
    Path generate(final Spot start, final Option<Spot> finishOption, final Bounds bounds);
}
