package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.util.Quads;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

public class PlayerMarker implements Sprite {

    private int[] textures = new int[1];
    private static final float SIZE = 4;
    private static final float PAD = 0.4f;
    
    private final float y;

    private final Quad[] delegates = new Quad[2];

    public PlayerMarker(final Coordinate pos, final float y, final Colour colour) {

        float z1 = pos.z - SIZE/2;
        float z2 = pos.z + SIZE/2;
        float x1 = pos.x - SIZE/2 - PAD;
        float x2 = pos.x - SIZE/2 + PAD;
        float x3 = pos.x + SIZE/2 - PAD;
        float x4 = pos.x + SIZE/2 + PAD;

        this.y = y;
        
        final Material material = new AmbDiffMaterial(colour);
        delegates[0] = new DefaultQuad(c(x1, z1), c(x2, z1), c(x4, z2), c(x3, z2), material, colour);
        delegates[1] = new DefaultQuad(c(x1, z2), c(x2, z2), c(x4, z1), c(x3, z1), material, colour);
    }

    private Coordinate c(final float x, final float z) {
        return new Coordinate(x, y, z);
    }

    @Override
    public void draw(GL10 g) {
        Quads.draw(g, delegates[0], textures[0]);
        Quads.draw(g, delegates[1], textures[0]);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(1, textures, 0);
        Textures.load(g, context, textures[0], R.drawable.glob);
    }
}
