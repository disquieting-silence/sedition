package dsq.sedition.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Buffers {

    private Buffers() {}

    public static FloatBuffer floats(final float[] input) {
        final ByteBuffer vb = ByteBuffer.allocateDirect(input.length * 4);
        vb.order(ByteOrder.nativeOrder());
        final FloatBuffer r = vb.asFloatBuffer();
        r.put(input);
        r.position(0);
        return r;
    }
}
