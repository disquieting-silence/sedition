package dsq.sedition.util;

import dsq.sedition.scene.Coordinate;
import dsq.sedition.sprite.*;

import java.util.ArrayList;
import java.util.List;

// FIX 17/08/12 Possibly move / rename.
public class Panels {

    public static Quad quad(final float x, final float y, final float width, final float height, final Colour colour) {

        final Material material = new AmbDiffMaterial(colour);
        final Coordinate p1 = new Coordinate(x, y, -1);
        final Coordinate p2 = new Coordinate(x + width, y, -1);
        final Coordinate p3 = new Coordinate(x + width, y + height, -1);
        final Coordinate p4 = new Coordinate(x, y + height, -1);

        return new DefaultQuad(p4, p3, p2, p1, material, colour);
    }
}

