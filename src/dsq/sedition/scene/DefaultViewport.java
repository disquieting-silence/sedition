package dsq.sedition.scene;

import javax.microedition.khronos.opengles.GL10;

public class DefaultViewport implements Viewport {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public DefaultViewport(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void setBackground(final GL10 g, final float red, final float green, final float blue, final float alpha) {
        g.glEnable(GL10.GL_SCISSOR_TEST);
        g.glClearColor(red, green, blue, alpha);
        g.glScissor(x, y, width, height);
        g.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        g.glDisable(GL10.GL_SCISSOR_TEST);
    }

    @Override
    public void setActive(final GL10 g) {
        g.glViewport(x, y, width, height);
    }
}
