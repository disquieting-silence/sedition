package dsq.sedition.light;

import javax.microedition.khronos.opengles.GL10;
import java.nio.FloatBuffer;

public class Sun implements Light {

    private static final FloatBuffer position = FloatBuffer.wrap(new float[] { 0.0f, 100f, 0.0f, 0.0f});
    private static final FloatBuffer ambdiff = FloatBuffer.wrap(new float[] { 0.0f, 0.0f, 0.0f, 1.0f });
    
    private final int id;

    public Sun(final GL10 g, final int id) {
        this.id = id;
        g.glLightfv(id, GL10.GL_POSITION, position);
        g.glLightfv(id, GL10.GL_AMBIENT_AND_DIFFUSE, ambdiff);                 
    }

    @Override
    public void use(final GL10 g) {
        g.glEnable(id);
    }

    @Override
    public void hide(final GL10 g) {
        g.glDisable(id);
    }
}
