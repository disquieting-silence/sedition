package dsq.sedition.util;

import dsq.sedition.scene.Coordinate;
import dsq.sedition.sprite.*;

import java.util.ArrayList;
import java.util.List;

public class Panels {

    public static List<Quad> make(final int num, final float left, final float top, final float right, final float bottom, final Colour colour) {
        final List<Quad> r = new ArrayList<Quad>();

        final float width = (right - left) / num;
        final float height = Math.abs(bottom - top);

        final float centre = (right + left) / 2;
        final float start = centre - (num / 2.0f) * width;

        for (int i = 0; i < num; i++) {
            r.add(sprite(start + width * i, top, width, height, colour));
        }
        return r;
    }

    private static Quad sprite(final float x, final float y, final float width, final float height, final Colour colour) {

        final Material material = new AmbDiffMaterial(colour);
        final Coordinate p1 = new Coordinate(x, y, -1);
        final Coordinate p2 = new Coordinate(x + width, y, -1);
        final Coordinate p3 = new Coordinate(x + width, y + height, -1);
        final Coordinate p4 = new Coordinate(x, y + height, -1);

        return new DefaultQuad(p4, p3, p2, p1, material, colour);
    }
}

