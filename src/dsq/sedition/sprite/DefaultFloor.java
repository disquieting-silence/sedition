package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.util.Quads;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

/* I would investigate VBOs, but they don't work on Froyo, apparently. */
public class DefaultFloor implements Floor {
    
    private static final int SIZE = 200;

    private int[] textures = new int[1];

    private final Quad delegate;

    public DefaultFloor() {

        float startX = -SIZE / 2.0f;
        float startZ = -SIZE / 2.0f;

        Coordinate p1 = new Coordinate(startX, 0.0f, startZ);
        Coordinate p2 = new Coordinate(startX + SIZE, 0.0f, startZ);
        Coordinate p3 = new Coordinate(startX + SIZE, 0.0f, startZ + SIZE);
        Coordinate p4 = new Coordinate(startX, 0.0f, startZ + SIZE);

        final Material material = new AmbDiffMaterial(new DefaultColour(0.0f, 0.1f, 0.9f, 1.0f));
        final DefaultColour colour = new DefaultColour(0.9f, 0.9f, 0.9f, 1);
        delegate = new DefaultQuad(p1, p2, p3, p4, material, colour);
   
    }

    @Override
    public void draw(GL10 g) {
        Quads.draw(g, delegate, textures[0]);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(1, textures, 0);
        Textures.load(g, context, textures[0], R.drawable.floor);
    }
}