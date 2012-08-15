package dsq.sedition.maze;

import java.util.List;

// FIX 15/08/12 Rename me. Obviously.
public class Hyena {
    // Putting it all together.
    
    private Hyena() { }
    
    public static Vecchio hyena(final int width, final int height, final Spot start, final Spot finish) {
        final List<Spot> hSpots = GenList.horizontal(width, height);
        final List<Spot> vSpots = GenList.vertical(width, height);

        final Vecchio walls = generate(width, hSpots, vSpots);
        final Path path = Cann.cann(start, width, height, finish);
        return Jor.jor(path, walls, 0, width - 1, 0, height - 1);
    }

    private static Vecchio generate(final int width, final List<Spot> hSpots, final List<Spot> vSpots) {
        final Vecchio r = new DefaultVecchio(width);
        for (Spot vSpot : vSpots) {
            r.addV(vSpot);
        }

        for (Spot hSpot : hSpots) {
            r.addH(hSpot);
        }
        return r;
    }
}
