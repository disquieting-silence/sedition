package dsq.sedition.maze;

import java.util.ArrayList;
import java.util.List;

public class Joiner {
    private Joiner() { }
    
    
    public static List<HWall> joinH(final List<Spot> spots) {
        final List<HWall> r = new ArrayList<HWall>();
        
        final int index = commonH(spots);
        // FIX 15/08/12 Invariant: index will be -1, or 1+ (look at code for commonH)
        if (index > -1) {
            final List<Spot> current = spots.subList(0, index);
            final List<Spot> after = spots.subList(index, spots.size());

            final Spot first = current.get(0);
            final Spot last = current.get(current.size() - 1);
            r.add(new HWall(first.z, first.x, last.x + 1));
            final List<HWall> rest = joinH(after);
            r.addAll(rest);
            
        }


        return r;
    }
    
    private static int commonH(final List<Spot> spots) {
        if (spots.size() == 0) return -1;
        Spot prev = spots.get(0);
        for (int i = 1; i < spots.size(); i++) {
            final Spot s = spots.get(i);
            if (prev.z != s.z || s.x - prev.x > 1) return i;
            prev = s;
        }
        // FIX 15/08/12 Not sure about this return value.
        return spots.size();
    }
}
