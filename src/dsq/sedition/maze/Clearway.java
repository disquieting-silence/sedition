package dsq.sedition.maze;

import java.util.Arrays;
import java.util.List;

import static dsq.sedition.maze.Direction.*;

public class Clearway {
    private Clearway() { }
    
    public static Skeleton makeClear(final List<Path> paths, final Skeleton input, final int minX, final int maxX, final int minZ, final int maxZ) {
        final Skeleton r = input.copy();

        // FIX 16/08/12 Can move this out. But then arguments would be mutated or copied ... consider.
        for (Path path : paths) {
            Spot current = path.start;
            for (final Direction move : path.moves) {
                if (move == NORTH) r.removeH(current.x, current.z);
                else if (move == EAST) r.removeV(current.x + 1, current.z);
                else if (move == SOUTH) r.removeH(current.x, current.z + 1);
                else r.removeV(current.x, current.z);

                // FIX 15/08/12 Really shouldn't use getOrDie so liberally. Need to do this a better way.
                current = Travel.go(current, move, minX, maxX, minZ, maxZ).getOrDie();
            }            
        }
        return r;
    }
    
    public static Skeleton makeClear(final Path path, final Skeleton input, final int minX, final int maxX, final int minZ, final int maxZ) {
        return makeClear(Arrays.asList(path), input, minX, maxX, minZ, maxZ);
    }
}
