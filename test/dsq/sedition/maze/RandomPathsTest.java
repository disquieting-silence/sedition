package dsq.sedition.maze;

import junit.framework.TestCase;

public class RandomPathsTest extends TestCase {
    
    public void test() {
        final Path path = RandomPaths.calculate(new Spot(1, 1), 10, 10, 30);
        System.out.println("path.moves = " + path.moves.size());
    }
    
}
