package dsq.sedition.collision;

import dsq.sedition.scene.Coordinate;

import java.util.List;

// FIX 9/06/12 Probably move.
public class Collisions {
    private Collisions() {}

    // FIX 9/06/12 An option might be a better approach than a boolean, but I don't want to write it again ...
    public static boolean hits(final Coordinate nu, final List<Collidable> obstacles, final float speed) {
        for (final Collidable obstacle : obstacles) {
            if (hits(nu, obstacle, speed)) return true;
        }
        return false;        
    }

    private static boolean hits(final Coordinate nu, final Collidable obstacle, final float speed) {
        
        /* Here we go ... Inspired by: http://stackoverflow.com/questions/1073336/circle-line-collision-detection */
        final Line2D line = obstacle.line();
        Vector2D d = new Vector2D(line.x2 - line.x1, line.z2 - line.z1);
        Vector2D f = new Vector2D(line.x1 - nu.x, line.z1 - nu.z);
        
        float a = Vectors.dot(d, d);
        float b = Vectors.dot(Vectors.mult(f, 2), d);
        float c = Vectors.dot(f, f) - (float)Math.pow(speed, 2);
        
        float discSq = (float)Math.pow(b, 2) - 4 * a * c;
        if (discSq < 0) return false;

        final float disc = (float)Math.sqrt(discSq);

        final float attempt1 = (-b + disc) / (2 * a);
        final float attempt2 = (-b - disc) / (2 * a);

        return attempt1 >= 0 && attempt2 <= 1;
    }
}
