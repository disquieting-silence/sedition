package dsq.sedition.maze;

import junit.framework.TestCase;

public class PathsTest extends TestCase {
    
    public void testExample() {
        final Path path = Paths.calculate(new Spot(0, 0), 15, 14, new Spot(5, 6));
        System.out.println("start = (" + path.start.x + ", " + path.start.z + ")");
        System.out.println(path.moves);
        
        final Path dPath = DirectPaths.calculate(new Spot(7, 4), 15, 14, new Spot(5, 6));
        System.out.println("start = (" + dPath.start.x + ", " + dPath.start.z + ")");
        System.out.println(dPath.moves);
    }
}
