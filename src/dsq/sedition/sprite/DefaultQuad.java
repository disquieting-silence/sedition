package dsq.sedition.sprite;

import dsq.sedition.light.Normal;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.util.Buffers;
import dsq.sedition.util.Normals;

import java.nio.FloatBuffer;

public class DefaultQuad implements Quad {

    private final Normal normal;
    private final FloatBuffer vertices;
    private final FloatBuffer texture;
    private final Material material;
    private final Colour colour;

    public DefaultQuad(final Coordinate p1, final Coordinate p2, final Coordinate p3, final Coordinate p4, final Material material, final Colour colour) {

        this.material = material;
        this.colour = colour;

        normal = Normals.normal(p1, p2, p3);
        final float[] coords = {
                p4.x, p4.y, p4.z,
                p1.x, p1.y, p1.z,
                p3.x, p3.y, p3.z,
                p2.x, p2.y, p2.z,
        };
        vertices = Buffers.floats(coords);
        texture = Buffers.floats(new float[]{
                0.0f, 1.0f,     // top left     (V2)
                0.0f, 0.0f,     // bottom left  (V1)
                1.0f, 1.0f,     // top right    (V4)
                1.0f, 0.0f      // bottom right (V3)
        });
    }

    @Override
    public Normal normal() {
        return normal;
    }

    @Override
    public Material material() {
        return material;
    }

    @Override
    public FloatBuffer vertices() {
        return vertices;
    }

    @Override
    public FloatBuffer texture() {
        return texture;
    }

    @Override
    public Colour colour() {
        return colour;
    }
}
