package dsq.sedition.maze.util;

import dsq.sedition.maze.data.Spot;

public class Spots {
    private Spots()  { }
    
    public static int hash(final Spot spot, int width) {
        // FIX 16/08/12 The plus one and minus one problems are getting out of hand.
        return spot.z * (width + 1) + spot.x;
    }
}
