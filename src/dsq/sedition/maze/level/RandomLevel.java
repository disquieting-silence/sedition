package dsq.sedition.maze.level;

import dsq.sedition.collision.Line2D;
import dsq.sedition.maze.util.Joiner;
import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.path.Pather;
import dsq.sedition.maze.path.RandomPather;
import dsq.sedition.maze.walls.HWalls;
import dsq.sedition.maze.walls.VWalls;
import dsq.sedition.util.None;

import java.util.ArrayList;
import java.util.List;

public class RandomLevel implements MazeLevel {
    
    private final List<Line2D> walls;
    private final Spot start;
    private final Spot finish;
    
    private static double WALL_CHANCE = 1.0;
    
    private final Pather paths = new RandomPather(60);
    private final Generator generator = new DefaultGenerator();
    
    public RandomLevel(final int width, final int height) {
        start = randomSpot(width, height);

        final Bounds bounds = new Bounds(width, height);
        final Path solution = paths.generate(start, new None<Spot>(), bounds);
        finish = solution.finish;
        final Blueprint blueprint = generator.gen(solution, bounds);

        final List<Spot> vSpots = blueprint.vWalls();
        final List<Spot> hSpots = blueprint.hWalls();

        walls = new ArrayList<Line2D>();
        final List<Line2D> vWalls = Joiner.join(vSpots, new VWalls());
        final List<Line2D> hWalls = Joiner.join(hSpots, new HWalls());

        for (Line2D w : vWalls) {
            if (Math.random() < WALL_CHANCE) walls.add(w);
        }

        for (Line2D w : hWalls) {
            if (Math.random() < WALL_CHANCE) walls.add(w);
        }
    }
    
    @Override
    public List<Line2D> walls() {
        return walls;        
    }

    @Override
    public Spot start() {
        return start;
    }

    @Override
    public Spot finish() {
        return finish;
    }

    private Spot randomSpot(final int width, final int height) {
        final int w = (int) (Math.random() * width);
        final int h = (int)(Math.random() * height);
        return new Spot(w, h);
    }
    
}
