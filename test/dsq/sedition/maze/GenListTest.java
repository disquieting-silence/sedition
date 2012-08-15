package dsq.sedition.maze;

import junit.framework.TestCase;

import java.util.List;

import static java.util.Arrays.*;

public class GenListTest extends TestCase {
    
    public void testV() {
        checkV(asList(
                new Spot(0, 0), new Spot(1, 0)
        ), 1, 1);
        
        checkV(asList(
                new Spot(0, 0), new Spot(1, 0), new Spot(2, 0), new Spot(3, 0),
                new Spot(0, 1), new Spot(1, 1), new Spot(2, 1), new Spot(3, 1),
                new Spot(0, 2), new Spot(1, 2), new Spot(2, 2), new Spot(3, 2)
        ), 3, 3);

        checkV(asList(
                new Spot(0, 0), new Spot(1, 0), new Spot(2, 0), new Spot(3, 0), new Spot(4, 0), new Spot(5, 0),
                new Spot(0, 1), new Spot(1, 1), new Spot(2, 1), new Spot(3, 1), new Spot(4, 1), new Spot(5, 1),
                new Spot(0, 2), new Spot(1, 2), new Spot(2, 2), new Spot(3, 2), new Spot(4, 2), new Spot(5, 2)
        ), 5, 3);

        checkV(asList(
                new Spot(0, 0), new Spot(1, 0), new Spot(2, 0), new Spot(3, 0),
                new Spot(0, 1), new Spot(1, 1), new Spot(2, 1), new Spot(3, 1),
                new Spot(0, 2), new Spot(1, 2), new Spot(2, 2), new Spot(3, 2),
                new Spot(0, 3), new Spot(1, 3), new Spot(2, 3), new Spot(3, 3),
                new Spot(0, 4), new Spot(1, 4), new Spot(2, 4), new Spot(3, 4)
        ), 3, 5);

    }

    public void testH() {
        checkH(asList(
                new Spot(0, 0), new Spot(0, 1)
        ), 1, 1);

        checkH(asList(
                new Spot(0, 0), new Spot(1, 0), new Spot(2, 0),
                new Spot(0, 1), new Spot(1, 1), new Spot(2, 1),
                new Spot(0, 2), new Spot(1, 2), new Spot(2, 2),
                new Spot(0, 3), new Spot(1, 3), new Spot(2, 3)
        ), 3, 3);

        checkH(asList(
                new Spot(0, 0), new Spot(1, 0), new Spot(2, 0), new Spot(3, 0), new Spot(4, 0),
                new Spot(0, 1), new Spot(1, 1), new Spot(2, 1), new Spot(3, 1), new Spot(4, 1),
                new Spot(0, 2), new Spot(1, 2), new Spot(2, 2), new Spot(3, 2), new Spot(4, 2),
                new Spot(0, 3), new Spot(1, 3), new Spot(2, 3), new Spot(3, 3), new Spot(4, 3)
        ), 5, 3);

        checkH(asList(
                new Spot(0, 0), new Spot(1, 0), new Spot(2, 0),
                new Spot(0, 1), new Spot(1, 1), new Spot(2, 1),
                new Spot(0, 2), new Spot(1, 2), new Spot(2, 2),
                new Spot(0, 3), new Spot(1, 3), new Spot(2, 3),
                new Spot(0, 4), new Spot(1, 4), new Spot(2, 4),
                new Spot(0, 5), new Spot(1, 5), new Spot(2, 5)
        ), 3, 5);

    }
    
    private void checkV(final List<Spot> expected, final int width, final int height) {
        final List<Spot> actual = GenList.vertical(width, height);
        check(expected, actual);
    }

    private void checkH(final List<Spot> expected, final int width, final int height) {
        final List<Spot> actual = GenList.horizontal(width, height);
        check(expected, actual);
    }

    private void check(final List<Spot> expected, final List<Spot> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            final Spot a = actual.get(i);
            final Spot e = expected.get(i);
            assertEquals(e.x, a.x);
            assertEquals(e.z, a.z);
        }
    }
}
