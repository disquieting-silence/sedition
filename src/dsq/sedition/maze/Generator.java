package dsq.sedition.maze;

import java.util.ArrayList;
import java.util.List;

// FIX 15/08/12 Rename me. Obviously.
public class Generator {
    // Putting it all together.
    
    private Generator() { }
    
    public static Skeleton skeleton(final Path solution, final int width, final int height) {
        final List<Spot> hSpots = GenList.horizontal(width, height);
        final List<Spot> vSpots = GenList.vertical(width, height);

        // Temporary.
        final List<Path> paths = new ArrayList<Path>();
//        final Path solution = complex(width, height, start, finish);
        paths.add(solution);
        paths.addAll(Herrings.herrings(solution, width, height, 0.5));

        final Skeleton walls = generate(width, hSpots, vSpots);
        return Clearway.makeClear(paths, walls, 0, width - 1, 0, height - 1);
    }

    private static Path basic(final Spot start, final int width, final int height, final int num) {
        return RandomPaths.calculate(start, width, height, num);
    }

    private static Path complex(final Spot start, final int width, final int height, final Spot finish) {
        return Paths.calculate(start, width, height, finish);
    }

    private static Skeleton generate(final int width, final List<Spot> hSpots, final List<Spot> vSpots) {
        final Skeleton r = new DefaultSkeleton(width);
        for (Spot vSpot : vSpots) {
            r.addV(vSpot);
        }

        for (Spot hSpot : hSpots) {
            r.addH(hSpot);
        }
        return r;
    }

    // FIX 16/08/12 Dupe with random level.
    private static Spot randomSpot(final int width, final int height) {
        final int w = (int) (Math.random() * width);
        final int h = (int)(Math.random() * height);
        return new Spot(w, h);
    }
}
