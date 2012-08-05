package dsq.sedition.light;

public class LightPosition {
    public final float x;
    public final float y;
    public final float z;
    public final LightType w;

    public LightPosition(final float x, final float y, final float z, final LightType w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
