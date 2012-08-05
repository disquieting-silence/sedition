package dsq.sedition.scene;

public class DefaultPlayerBounce implements PlayerBounce {
    
    private final int segments;
    private final float variance;
    private int tick = 0;

    public DefaultPlayerBounce(final int segments, final float variance) {
        this.segments = segments;
        this.variance = variance;
        tick = segments;
    }

    @Override
    public float level() {
        return (float)Math.sin(tick * 360.0 / segments * Math.PI / 180) * variance;
    }

    @Override
    public void update() {
        tick--;
        if (tick < 0) tick = segments;
    }
}