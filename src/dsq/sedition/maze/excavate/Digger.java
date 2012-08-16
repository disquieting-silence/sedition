package dsq.sedition.maze.excavate;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.level.Blueprint;

import java.util.List;

public interface Digger {
    Blueprint excavate(final List<Path> paths, final Blueprint input, final Bounds bounds);
}
