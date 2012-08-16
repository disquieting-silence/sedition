package dsq.sedition.maze.level;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.excavate.DefaultDigger;
import dsq.sedition.maze.excavate.Digger;
import dsq.sedition.maze.herring.DefaultHerring;
import dsq.sedition.maze.herring.Herring;
import dsq.sedition.maze.util.GenList;

import java.util.ArrayList;
import java.util.List;

public class DefaultGenerator implements Generator {

    private static Herring herrings = new DefaultHerring(0.5);
    private static Digger digger = new DefaultDigger();

    @Override
    public Blueprint gen(final Path solution, final Bounds bounds) {
        final List<Spot> hSpots = GenList.horizontal(bounds);
        final List<Spot> vSpots = GenList.vertical(bounds);

        final List<Path> paths = new ArrayList<Path>();
        paths.add(solution);
        paths.addAll(herrings.herrings(solution, bounds));

        final Blueprint walls = generate(bounds, hSpots, vSpots);
        return digger.excavate(paths, walls, bounds);
    }

    private Blueprint generate(final Bounds bounds, final List<Spot> hSpots, final List<Spot> vSpots) {
        final Blueprint r = new DefaultBlueprint(bounds.width);
        for (Spot vSpot : vSpots) r.addV(vSpot);
        for (Spot hSpot : hSpots) r.addH(hSpot);
        return r;
    }
}
