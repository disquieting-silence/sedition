package dsq.sedition.maze;

import junit.framework.TestCase;

public class PathsTest extends TestCase {
    
    public void testExample() {
        final Path path = Paths.calculate(new Spot(0, 0), 15, 14, new Spot(5, 6));
        System.out.println("start = (" + path.start.x + ", " + path.start.z + ")");
        System.out.println(path.moves);
    }
}
