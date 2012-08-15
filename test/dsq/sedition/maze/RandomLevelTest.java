package dsq.sedition.maze;

import junit.framework.TestCase;

public class RandomLevelTest extends TestCase {
    public void test() {
        for (int i = 0; i < 200; i++) {
            new RandomLevel(10, 10);
        }
    }
}
