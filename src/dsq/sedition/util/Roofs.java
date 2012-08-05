package dsq.sedition.util;

import dsq.sedition.collision.Line2D;
import dsq.sedition.sprite.DefaultRoof;
import dsq.sedition.sprite.Roof;

import java.util.ArrayList;
import java.util.List;

public class Roofs {
    
    private Roofs() {}
    
    public static List<Roof> roofs(final List<Line2D> lines) {
        final List<Roof> r = new ArrayList<Roof>();
        for (Line2D line : lines) {
            r.add(new DefaultRoof(line.x1, line.z1, line.x2, line.z2));
        }
        return r;
    }
}
