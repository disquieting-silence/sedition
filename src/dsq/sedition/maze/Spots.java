package dsq.sedition.maze;

public class Spots {
    private Spots()  { }
    
    public static int hash(final Spot spot, int width) {
        return spot.z * width + spot.x;
    }
}
