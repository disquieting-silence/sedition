package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.util.Quads;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

// FIX 11/06/12 This class has some dupe with wall.
public class DefaultRoof implements Roof {

    private static final float PAD = 0.5f;
    private static final float POS_Y = 5;
    private final Quad delegate;


    private int[] textures = new int[1];

    public DefaultRoof(final float x1, final float z1, final float x2, final float z2) {
        final boolean zwall = x1 == x2;
        final Coordinate p1 = zwall ? c(x1 - PAD, z1) : c(x1, z1 - PAD);
        final Coordinate p2 = zwall ? c(x1 + PAD, z1) : c(x1, z1 + PAD);
        final Coordinate p3 = zwall ? c(x1 + PAD, z2) : c(x2, z1 + PAD);
        final Coordinate p4 = zwall ? c(x1 - PAD, z2) : c(x2, z1 - PAD);

        final Material material = new AmbDiffMaterial(new DefaultColour(1.0f, 1.0f, 1.0f, 1.0f));
        final DefaultColour colour = new DefaultColour(0.9f, 0.9f, 0.9f, 1);

        delegate = new DefaultQuad(p1, p2, p3, p4, material, colour);
    }

    private Coordinate c(final float x, final float z) {
        return new Coordinate(x, POS_Y, z);        
    }

    @Override
    public void draw(final GL10 g) {
        Quads.draw(g, delegate, 0);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(1, textures, 0);
        Textures.load(g, context, textures[0], R.drawable.glob);
    }
}
