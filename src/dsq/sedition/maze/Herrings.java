package dsq.sedition.maze;

import dsq.sedition.util.Option;

import java.util.ArrayList;
import java.util.List;

public class Herrings {
    public static List<Path> herrings(final Path solution, final int width, final int height, final double rate) {
        final List<Path> r = new ArrayList<Path>();
        
        Spot current = solution.start;
        for (Direction move : solution.moves) {
            // FIX 16/08/12 Should this use width -1 ?
            final Option<Spot> next = Travel.go(current, move, 0, width, 0, height);
            if (Math.random() < rate) {
                final int herringX = deviate(current.x, 3, 0, width - 1);
                final int herringZ = deviate(current.z, 3, 0, height -1);
                final Spot herring = new Spot(herringX, herringZ);
                if (herring.x != solution.finish.x || herring.z != solution.finish.z) {
                    final Path fake = DirectPaths.calculate(current, width, height, herring);
                    r.add(fake);
                }
            }
            current = next.getOrDie();
        }
        return r;
    }

    private static int deviate(final int value, final int amount, final int min, final int max) {
        final int v = (int) (Math.random() * amount);
        return Math.max(min, Math.min(max, value + v));
    }

}
