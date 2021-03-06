package dsq.sedition.maze;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Direction;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.util.Travel;
import junit.framework.TestCase;

import static dsq.sedition.maze.data.Direction.*;

public class TravelTest extends TestCase {
    
    public void test() {
        check(0, -1, new Spot(0, 0), NORTH);
        check(0, 3, new Spot(0, 2), SOUTH);
        check(2, 1, new Spot(2, 2), NORTH);
        check(3, 1, new Spot(2, 1), EAST);
        check(2, 5, new Spot(3, 5), WEST);
    }

    private void check(final int x, final int z, final Spot start, final Direction dir) {
        final Spot actual = Travel.go(start, dir, new Bounds(20, 20, -5, 15, -5, 15)).getOrDie();
        assertEquals(x, actual.x);
        assertEquals(z, actual.z);
    }
}
