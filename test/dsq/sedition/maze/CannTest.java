package dsq.sedition.maze;

import junit.framework.TestCase;

public class CannTest extends TestCase {
    
    public void test() {
        final Path path = Cann.cann(new Spot(0, 0), 15, 14, new Spot(5, 6));
        System.out.println("start = (" + path.start.x + ", " + path.start.z + ")");
        for (final Direction move : path.moves) {
            System.out.println("move = " + move);
        }

    }
}
