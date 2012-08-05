package dsq.sedition.sprite;

import dsq.sedition.util.Buffers;

import javax.microedition.khronos.opengles.GL10;
import java.nio.FloatBuffer;

public class AmbDiffMaterial implements Material {
    
    private final FloatBuffer buffer;

    public AmbDiffMaterial(final Colour colour) {
        buffer = Buffers.floats(new float[]{colour.red(), colour.green(), colour.blue(), colour.alpha()});
    }

    @Override
    public void use(final GL10 g) {
        g.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT_AND_DIFFUSE, buffer);
    }
}
