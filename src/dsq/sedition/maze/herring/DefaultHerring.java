package dsq.sedition.maze.herring;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Direction;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.path.DirectPather;
import dsq.sedition.maze.path.Pather;
import dsq.sedition.maze.util.Travel;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

import java.util.ArrayList;
import java.util.List;

public class DefaultHerring implements Herring {
    
    private final double rate;
    private final Pather direct = new DirectPather();

    public static final int DEVIATION = 4;

    public DefaultHerring(final double rate) {
        this.rate = rate;
    }

    @Override
    public List<Path> herrings(final Path solution, final Bounds bounds) {
        final List<Path> r = new ArrayList<Path>();

        Spot current = solution.start;
        for (Direction move : solution.moves) {
            final Option<Spot> next = Travel.go(current, move, bounds);
            if (Math.random() < rate) {
                final int herringX = deviate(current.x, DEVIATION, bounds.minX, bounds.maxX);
                final int herringZ = deviate(current.z, DEVIATION, bounds.minZ, bounds.maxZ);
                final Spot herring = new Spot(herringX, herringZ);
                if (herring.x != solution.finish.x || herring.z != solution.finish.z) {
                    final Path fake = direct.generate(current, new Some<Spot>(herring), bounds);
                    r.add(fake);
                }
            }
            current = next.getOrDie();
        }
        return r;
    }

    private int deviate(final int value, final int amount, final int min, final int max) {
        final int v = (int) (Math.random() * amount);
        return Math.max(min, Math.min(max, value + v));
    }
}
