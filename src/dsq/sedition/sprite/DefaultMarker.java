package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.util.Quads;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

public class DefaultMarker implements Sprite {
    private static final float SIZE = 1;
    private static final float PAD = 0;

    private final float y;

    private final Quad delegate;

    public DefaultMarker(final Coordinate pos, final float y, final Colour colour) {

        float z1 = pos.z - SIZE/2;
        float z2 = pos.z + SIZE/2;
        float x1 = pos.x - SIZE/2 - PAD;
        float x2 = pos.x - SIZE/2 + PAD;
        float x3 = pos.x + SIZE/2 - PAD;
        float x4 = pos.x + SIZE/2 + PAD;

        this.y = y;

        final Material material = new AmbDiffMaterial(colour);
        delegate = new DefaultQuad(c(x1, z1), c(x4, z1), c(x4, z2), c(x1, z2), material, colour);
    }

    private Coordinate c(final float x, final float z) {
        return new Coordinate(x, y, z);
    }

    @Override
    public void use(GL10 g) {
        Quads.draw(g, delegate, 0);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {

    }
}
