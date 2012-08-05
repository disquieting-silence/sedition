package dsq.sedition.sprite;

import dsq.sedition.light.Normal;

import java.nio.FloatBuffer;

public interface Quad {
    Normal normal();
    Material material();
    FloatBuffer vertices();
    FloatBuffer texture();
    Colour colour();
}
