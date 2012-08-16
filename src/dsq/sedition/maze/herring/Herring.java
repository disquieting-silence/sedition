package dsq.sedition.maze.herring;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Path;

import java.util.List;

public interface Herring {
    List<Path> herrings(final Path solution, final Bounds bounds);
}
