package dsq.sedition.view;

import dsq.sedition.sprite.Colour;
import dsq.sedition.sprite.Quad;
import dsq.sedition.util.Panels;
import dsq.sedition.util.Quads;

import javax.microedition.khronos.opengles.GL10;

public class DefaultViewPanel implements ViewPanel {
    
    private final int num;
    private final Quad[] sprites;
    private final float hPad;
    private final float vPad;

    public DefaultViewPanel(final int num, final Colour colour, final float hPad, final float vPad) {
        this.num = num;
        this.hPad = hPad;
        this.vPad = vPad;
        sprites = new Quad[num];
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = Panels.quad(i, 0, 1, 1, colour);
        }
    }

    public void draw(final GL10 g, final int[] textures) {
        g.glDisable(GL10.GL_LIGHTING);
        g.glMatrixMode(GL10.GL_PROJECTION);
        g.glPushMatrix();
        g.glLoadIdentity();
        g.glOrthof(-hPad, num + hPad, -vPad, 1 + vPad, -1, 1);
        g.glMatrixMode(GL10.GL_MODELVIEW);
        g.glPushMatrix();
        g.glLoadIdentity();
        for (int i = 0; i < sprites.length; i++) {
            Quads.draw(g, sprites[i], textures[i % textures.length]);
        }
        g.glPopMatrix();
        g.glMatrixMode(GL10.GL_PROJECTION);
        g.glPopMatrix();
        g.glMatrixMode(GL10.GL_MODELVIEW);
        g.glEnable(GL10.GL_LIGHTING);
    }
}
