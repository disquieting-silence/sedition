package dsq.sedition.scene;

public class Coordinate {

    public static final int NUM_COORDS_IN_VERTEX = 3;
    public static final int NUM_COORDS_IN_TEXTURE = 2;

    public final float x;
    public final float y;
    public final float z;

    public Coordinate(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    // FIX 9/06/12 Remove.
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ") -- (" + x + ", " + z + ")";
    }


}

