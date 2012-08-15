package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;

import java.util.Arrays;
import java.util.List;

public class LegacyLevel implements MazeLevel {
    @Override
    public List<Line2D> walls() {
        return Arrays.asList(
            v(-18, 1, 0),
            v(-16, -10, 1),
            v(-9, -7, 1),
            v(-4, -1, 1),
            v(-10, -8, 2),
            v(-4, -2, 2),
            v(-10, -8, 3),
            v(-7, -6, 3),
            v(-15, -11, 4),
            v(-6, -5, 4),
            v(-4, -2, 4),
            v(-19, -18, 5),
            v(-13, -7, 5),
            v(-3, -1, 5),
            v(-19, -18, 6),
            v(-15, -14, 6),
            v(-13, -12, 6),
            v(-9, -6, 6),
            v(-3, 1, 6),
            v(-18, -17, 7),
            v(-16, -13, 7),
            v(-8, -7, 7),
            v(-6, -5, 7),
            v(-17, -13, 8),
            v(-12, -10, 8),
            v(-9, -8, 8),
            v(-6, -4, 8),
            v(-18, -3, 9),

            h(0, 5, -18),
            h(6, 9, -18),
            h(1, 6, -17),
            h(7, 8, -17),
            h(1, 7, -16),
            h(4, 6, -15),
            h(6, 7, -14),
            h(5, 6, -13),
            h(6, 8, -12),
            h(1, 4, -11),
            h(2, 3, -10),
            h(6, 8, -9),
            h(2, 3, -8),
            h(7, 8, -8),
            h(0, 2, -7),
            h(3, 5, -7),
            h(7, 9, -7),
            h(0, 3, -6),
            h(4, 6, -6),
            h(7, 8, -6),
            h(4, 7, -5),
            h(1, 2, -4),
            h(4, 8, -4),
            h(5, 9, -3),
            h(3, 4, -2),
            h(1, 5, -1),
            h(0, 6, 1)
        );
    }

    @Override
    public Spot start() {
        return new Spot(0, 0);
    }

    @Override
    public Spot finish() {
        return new Spot(1, 0);
    }

    private static Line2D h(float x1, float x2, float z) {
        return new Line2D(x1, z, x2, z);
    }

    private static Line2D v(float z1, float z2, float x) {
        return new Line2D(x, z1, x, z2);
    }
}
