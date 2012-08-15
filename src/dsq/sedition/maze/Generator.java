package dsq.sedition.maze;

import java.util.List;

// FIX 15/08/12 Rename me. Obviously.
public class Generator {
    // Putting it all together.
    
    private Generator() { }
    
    public static Skeleton skeleton(final int width, final int height, final Spot start, final Spot finish) {
        final List<Spot> hSpots = GenList.horizontal(width, height);
        final List<Spot> vSpots = GenList.vertical(width, height);


        final Skeleton walls = generate(width, hSpots, vSpots);
        System.out.println("Walls calculated");
        final Path path = Paths.calculate(start, width, height, finish);
        System.out.println("Path calculated");
        return Clearway.makeClear(path, walls, 0, width - 1, 0, height - 1);
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
}
