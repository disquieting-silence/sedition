package dsq.sedition.maze;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoinerTest extends TestCase {
    
    public void test() {
        checkH(new ArrayList<HWall>(), new ArrayList<Spot>());
        checkH(Arrays.asList(
            new HWall(1, 3, 6),
            new HWall(1, 8, 9),
            new HWall(2, 9, 11)                
        ), Arrays.asList(
            new Spot(3, 1), new Spot(4, 1), new Spot(5, 1),
            new Spot(8, 1),
            new Spot(9, 2),new Spot(10, 2)
        ));
        
    }
    
    private void checkH(final List<HWall> expected, final List<Spot> input) {
        final List<HWall> actual = Joiner.joinH(input);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            final HWall e = expected.get(i);
            final HWall a = actual.get(i);
            assertEquals(e.z, a.z);
            assertEquals(e.start, a.start);
            assertEquals(e.finish, a.finish);
        }
    }
}
