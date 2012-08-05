package dsq.sedition.util;

import dsq.sedition.light.Normal;
import dsq.sedition.scene.Coordinate;

public class Normals {
    
    private Normals() {}
    
    // FIX 2/06/12 Move me.
    private static Coordinate subtract(final Coordinate v1, final Coordinate v2) {
        return new Coordinate(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }
    
    public static Normal normal(final Coordinate v1, final Coordinate v2, final Coordinate v3) {
        final Coordinate c1 = subtract(v3, v1);
        final Coordinate c2 = subtract(v2, v1);

        return new Normal(
            c1.y * c2.z - c2.y * c1.z,
            c1.z * c2.x - c1.x * c2.z,
            c1.x * c2.y - c1.y * c2.x
        );
    }
}