package dsq.sedition.util;

import dsq.sedition.light.Normal;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.sprite.Colour;
import dsq.sedition.sprite.Material;
import dsq.sedition.sprite.Quad;

import javax.microedition.khronos.opengles.GL10;
import java.nio.FloatBuffer;

public class Quads {
    
    private static final int NUM_VERTICES = 4;

    private Quads() {}

    public static void draw(final GL10 g, final Quad quad, final int textureId) {
        
        g.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        g.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        final Normal normal = quad.normal();
        final Material material = quad.material();
        final FloatBuffer vertices = quad.vertices();
        final FloatBuffer texture = quad.texture();
        final Colour colour = quad.colour();

        material.use(g);

        g.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
        g.glColor4f(colour.red(), colour.green(), colour.blue(), colour.alpha());
        g.glVertexPointer(Coordinate.NUM_COORDS_IN_VERTEX, GL10.GL_FLOAT, 0, vertices);
        g.glNormal3f(normal.x, normal.y, normal.z);
        g.glTexCoordPointer(Coordinate.NUM_COORDS_IN_TEXTURE, GL10.GL_FLOAT, 0, texture);

        g.glFrontFace(GL10.GL_CW);
        g.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, NUM_VERTICES);
        
        g.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        g.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }
}
