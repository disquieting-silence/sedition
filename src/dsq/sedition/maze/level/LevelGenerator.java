package dsq.sedition.maze.level;

import dsq.sedition.core.Level;
import dsq.sedition.options.Options;

public interface LevelGenerator {
    Level generate(Options options);
}
