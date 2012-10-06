package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.util.Quads;

import javax.microedition.khronos.opengles.GL10;

public class DefaultMarker implements Sprite {
    private static final float SIZE = 0.8f;
    private static final float PAD = 0;
    private static final float HEIGHT = 10.0f;

    private final float y;

    private final Quad delegate;
    
    private final Quad[] shields;

    public DefaultMarker(final Coordinate pos, final float y, final Colour colour) {

        float z1 = pos.z - SIZE/2;
        float z2 = pos.z + SIZE/2;
        float x1 = pos.x - SIZE/2;
        float x2 = pos.x + SIZE/2;

        this.y = y;

        final Material material = new AmbDiffMaterial(colour);
        final Colour shadeColour = new DefaultColour(colour.red(), colour.green(), colour.blue(), 0.3f);
        final Material shade = new AmbDiffMaterial(new DefaultColour(colour.red() / 2, colour.green() / 2, colour.blue(), 0.1f));
        delegate = new DefaultQuad(c(x1, y, z1), c(x2, y, z1), c(x2, y, z2), c(x1, y, z2), material, colour);
        
        float y1 = y;
        float y2 = y + HEIGHT;
        
        final Coordinate a1 = c(x1, y1, z1);
        final Coordinate a2 = c(x2, y1, z1);
        final Coordinate a3 = c(x2, y1, z2);
        final Coordinate a4 = c(x1, y1, z2);
        
        final Coordinate a5 = c(x1, y2, z1);
        final Coordinate a6 = c(x2, y2, z1);
        final Coordinate a7 = c(x2, y2, z2);
        final Coordinate a8 = c(x1, y2, z2);
        
        shields = new Quad[4];
        shields[0] = new DefaultQuad(a1, a5, a6, a2, shade, shadeColour);
        shields[1] = new DefaultQuad(a4, a8, a5, a1, shade, shadeColour);
        shields[2] = new DefaultQuad(a3, a7, a8, a4, shade, shadeColour);
        shields[3] = new DefaultQuad(a2, a6, a7, a3, shade, shadeColour);
    }

    private Coordinate c(final float x, final float y, final float z) {
        return new Coordinate(x, y, z);
    }

    @Override
    public void use(GL10 g) {
        Quads.draw(g, delegate, 0);


        for (Quad shield : shields) {
            Quads.draw(g, shield, 0);
        }
//        g.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {

    }
}
