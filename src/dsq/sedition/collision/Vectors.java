package dsq.sedition.collision;

public class Vectors {
    
    private Vectors() {}
    
    public static Vector2D mult(final Vector2D v, final float coefficient) {
        return new Vector2D(coefficient * v.x, coefficient * v.z);
    }
    
    public static float dot(final Vector2D v1, final Vector2D v2) {
        return v1.x * v2.x + v1.z * v2.z;
    }
}
