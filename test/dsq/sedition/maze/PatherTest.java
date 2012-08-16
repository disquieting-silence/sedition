package dsq.sedition.maze;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.path.DirectPather;
import dsq.sedition.maze.path.Pather;
import dsq.sedition.maze.path.RandomPather;
import dsq.sedition.util.None;
import dsq.sedition.util.Some;
import junit.framework.TestCase;

public class PatherTest extends TestCase {
    
    final Pather direct = new DirectPather();
    final Pather random = new RandomPather(40);
    
    public void testExample() {
        final Path path = direct.generate(new Spot(0, 0), new Some<Spot>(new Spot(5, 6)), new Bounds(15, 14));
        System.out.println("start = (" + path.start.x + ", " + path.start.z + ")");
        System.out.println(path.moves);
        
        final Path dPath = direct.generate(new Spot(7, 4), new Some<Spot>(new Spot(5, 6)), new Bounds(15, 14));
        System.out.println("start = (" + dPath.start.x + ", " + dPath.start.z + ")");
        System.out.println(dPath.moves);

        final Path rpath = random.generate(new Spot(1, 1), new None<Spot>(), new Bounds(10, 30));
        System.out.println("path.moves = " + rpath.moves.size());
    }
}
