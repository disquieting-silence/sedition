package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.util.Joiner;
import dsq.sedition.maze.walls.HWalls;
import dsq.sedition.maze.walls.VWalls;
import junit.framework.TestCase;

import java.util.*;

public class JoinerTest extends TestCase {
    
    public void test() {
        checkH(new ArrayList<Line2D>(), new ArrayList<Spot>());
        checkV(new ArrayList<Line2D>(), new ArrayList<Spot>());
        checkH(Arrays.asList(
            new Line2D(3, 1, 6, 1),
            new Line2D(8, 1, 9, 1),
            new Line2D(9, 2, 11, 2)
        ), Arrays.asList(
            new Spot(3, 1), new Spot(4, 1), new Spot(5, 1),
            new Spot(8, 1),
            new Spot(9, 2),new Spot(10, 2)
        ));

        checkV(Arrays.asList(
                new Line2D(3, 1, 3, 4), new Line2D(4, 1, 4, 2), new Line2D(5, 1, 5, 2),
                new Line2D(8, 2, 8, 3),
                new Line2D(9, 3, 9, 4), new Line2D(10, 3, 10, 4)
        ), Arrays.asList(
                new Spot(3, 1), new Spot(4, 1), new Spot(5, 1),
                new Spot(3, 2), new Spot(8, 2),
                new Spot(3, 3), new Spot(9, 3),new Spot(10, 3)
        ));
        
    }
    
    private void checkH(final List<Line2D> expected, final List<Spot> input) {
        final HWalls walls = new HWalls();
        Collections.sort(input, walls.comparator());
        final List<Line2D> actual = Joiner.join(input, walls);
        check(expected, actual);
    }

    private void checkV(final List<Line2D> expected, final List<Spot> input) {
        final VWalls walls = new VWalls();
        Collections.sort(input, walls.comparator());
        final List<Line2D> actual = Joiner.join(input, walls);
        check(expected, actual);
    }

    private void check(final List<Line2D> expected, final List<Line2D> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            final Line2D e = expected.get(i);
            final Line2D a = actual.get(i);
            assertEquals(e.x1, a.x1);
            assertEquals(e.z1, a.z1);
            assertEquals(e.x2, a.x2);
            assertEquals(e.z2, a.z2);
        }
    }
}
