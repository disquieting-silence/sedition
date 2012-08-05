package dsq.sedition.sprite;

public class DefaultColour implements Colour {

    private final float red;
    private final float green;
    private final float blue;
    private final float alpha;

    public DefaultColour(final float red, final float green, final float blue, final float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    @Override
    public float red() {
        return red;
    }

    @Override
    public float green() {
        return green;
    }

    @Override
    public float blue() {
        return blue;
    }

    @Override
    public float alpha() {
        return alpha;
    }
}
