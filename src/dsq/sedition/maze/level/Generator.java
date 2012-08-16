package dsq.sedition.maze.level;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Path;

public interface Generator {
    Blueprint gen(final Path solution, final Bounds bounds);
}
