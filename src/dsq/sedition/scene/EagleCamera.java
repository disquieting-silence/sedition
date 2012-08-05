package dsq.sedition.scene;

import javax.microedition.khronos.opengles.GL10;

public class EagleCamera implements ScaledCamera {

    private float scale = 1.0f;

    @Override
    public void position(final GL10 g) {
        g.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        g.glTranslatef(-9.0f, -25, 12.0f);
        g.glScalef(scale, scale, scale);
    }

    @Override
    public void setScale(final float scale) {
        this.scale = scale;
    }

    @Override
    public float getScale() {
        return scale;
    }
}
