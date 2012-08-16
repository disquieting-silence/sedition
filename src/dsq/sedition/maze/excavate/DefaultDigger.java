package dsq.sedition.maze.excavate;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Direction;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.level.Blueprint;
import dsq.sedition.maze.util.Travel;

import java.util.List;

import static dsq.sedition.maze.data.Direction.EAST;
import static dsq.sedition.maze.data.Direction.NORTH;
import static dsq.sedition.maze.data.Direction.SOUTH;

public class DefaultDigger implements Digger {
    @Override
    public Blueprint excavate(final List<Path> paths, final Blueprint input, final Bounds bounds) {
        final Blueprint r = input.copy();

        // FIX 16/08/12 Can move this out. But then arguments would be mutated or copied ... consider.
        for (Path path : paths) {
            Spot current = path.start;
            for (final Direction move : path.moves) {
                if (move == NORTH) r.removeH(current.x, current.z);
                else if (move == EAST) r.removeV(current.x + 1, current.z);
                else if (move == SOUTH) r.removeH(current.x, current.z + 1);
                else r.removeV(current.x, current.z);

                // FIX 15/08/12 Really shouldn't use getOrDie so liberally. Need to do this a better way.
                current = Travel.go(current, move, bounds).getOrDie();
            }
        }
        return r;
    }
}
