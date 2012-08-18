package dsq.sedition.light;

import javax.microedition.khronos.opengles.GL10;
import java.nio.FloatBuffer;

import static java.nio.FloatBuffer.*;

// FIX 2/06/12 Not convinced by this separation. Probably shouldn't be on here ... this should be just be a collection
public class Headlight implements Light {

    // FIX 2/06/12 Generalise the colour information when it becomes obvious where to
    private static final FloatBuffer ambient = wrap(new float[] {1.0f,1.0f, 1.0f, 1.0f});
    private static final FloatBuffer diffuse = wrap(new float[] {1.0f,1.0f, 1.0f, 1.0f});
    private static final FloatBuffer specular = wrap(new float[] {1.0f,1.0f, 1.0f, 1.0f});
    
    private final int id;
    private final LightPosition position;
    private final LightDirection direction;

    public Headlight(final GL10 g, final int id, final LightPosition position, final LightDirection direction) {
        this.id = id;
        this.position = position;
        this.direction = direction;

        g.glEnable(id);
        g.glLightfv(id, GL10.GL_AMBIENT, ambient);
        g.glLightfv(id, GL10.GL_DIFFUSE, diffuse);
        g.glLightfv(id, GL10.GL_SPECULAR, specular);
        g.glLightf(id, GL10.GL_SPOT_CUTOFF, 40);
        g.glLightf(id, GL10.GL_SPOT_EXPONENT, 0.9f);
        g.glLightf(id, GL10.GL_QUADRATIC_ATTENUATION, 0.0f);
    }

    @Override
    public void use(final GL10 g) {
        g.glEnable(id);
        // FIX 2/06/12 Not sure about ordinal.
        g.glLightfv(id, GL10.GL_POSITION, wrap(new float[]{position.x, position.y, position.z, position.w.ordinal()}));
        g.glLightfv(id, GL10.GL_SPOT_DIRECTION, wrap(new float[]{direction.x, direction.y, direction.z}));
    }

    @Override
    public void hide(final GL10 g) {
        g.glDisable(id);
    }
}