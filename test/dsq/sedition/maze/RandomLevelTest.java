package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;
import dsq.sedition.maze.level.RandomLevel;
import junit.framework.TestCase;

import java.util.List;

public class RandomLevelTest extends TestCase {
    public void test() {
//        for (int i = 0; i < 200; i++) {
//            new RandomLevel(10, 10);
//        }
        final RandomLevel level = new RandomLevel(3, 3);
        final List<Line2D> actual = level.walls();
        for (Line2D line2D : actual) {
            System.out.println("line2D = " + "("  + (int)line2D.x1 + ", " + (int)line2D.z1 + ") -> (" +
                (int)line2D.x2 + ", " + (int)line2D.z2 + ")");
        }
    }
}
