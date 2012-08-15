package dsq.sedition.maze;

import java.util.ArrayList;
import java.util.List;

public class Repeat {
    public static <A> List<A> repeat(final A a, final int num) {
        final List<A> r = new ArrayList<A>();
        for (int i = 0; i < num; i++) {
             r.add(a);
        }
        return r;
    }
}
