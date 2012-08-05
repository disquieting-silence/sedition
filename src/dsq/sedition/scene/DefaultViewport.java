package dsq.sedition.scene;

import javax.microedition.khronos.opengles.GL10;

// FIX 3/06/12 Fix this later.
public class DefaultViewport implements Viewport {
    private final int x;
    private final int y;
    private final int viewY;
    private final int width;
    private final int height;

    public DefaultViewport(final int x, final int y, final int viewY, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.viewY = viewY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setBackground(final GL10 g, final float red, final float green, final float blue, final float alpha) {
        g.glEnable(GL10.GL_SCISSOR_TEST);
        g.glClearColor(red, green, blue, alpha);
        g.glScissor(x, viewY, width, height);
        g.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        g.glDisable(GL10.GL_SCISSOR_TEST);
    }

    @Override
    public void setActive(final GL10 g) {
        g.glViewport(x, viewY, width, height);
    }
}
