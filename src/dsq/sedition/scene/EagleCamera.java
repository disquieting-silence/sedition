package dsq.sedition.scene;

import javax.microedition.khronos.opengles.GL10;

public class EagleCamera implements ScaledCamera {

    private float scale = 0.6f;
    private final Coordinate centre;
    
    public EagleCamera(final Coordinate centre) {
        this.centre = centre;
    }

    @Override
    public void position(final GL10 g) {
        g.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        g.glTranslatef(0f, -25, 0f);
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
