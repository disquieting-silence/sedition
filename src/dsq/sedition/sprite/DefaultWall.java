package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.collision.Line2D;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.util.Quads;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

public class DefaultWall implements Wall {
    
    private final int[] textures = new int[1];
    private final Quad delegate;
    private final Line2D line;

    public DefaultWall(final float x1, final float z1, final float x2, final float z2, final float height) {

        final Coordinate p1 = new Coordinate(x1, 0, z1);
        final Coordinate p2 = new Coordinate(x2, 0, z2);
        final Coordinate p3 = new Coordinate(x2, height, z2);
        final Coordinate p4 = new Coordinate(x1, height, z1);

        
        line = new Line2D(x1, z1, x2, z2);

        final Material material = new AmbDiffMaterial(new DefaultColour(1.0f, 1.0f, 1.0f, 1.0f));
        final DefaultColour colour = new DefaultColour(0.9f, 0.9f, 0.9f, 1);

        delegate = new DefaultQuad(p1, p2, p3, p4, material, colour);
    }

    @Override
    public void use(final GL10 g) {
        Quads.draw(g, delegate, textures[0]);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(1, textures, 0);
        Textures.load(g, context, textures[0], R.drawable.glob);
    }

    @Override
    public Line2D line() {
        return line;
    }
}
