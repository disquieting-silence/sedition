package dsq.sedition.maze;

import dsq.sedition.collision.Line2D;

import java.util.ArrayList;
import java.util.List;

public class RandomLevel implements MazeLevel {
    
    private final List<Line2D> walls;
    
    public RandomLevel(final int width, final int height) {
        final Spot start = randomSpot(width, height);
        // FIX 15/08/12 It could be the same square !
        final Spot finish = randomSpot(width, height);
        final Vecchio vec = Hyena.hyena(width, height, start, finish);

        final List<Spot> vSpots = vec.vWalls();
        final List<Spot> hSpots = vec.hWalls();
//        final List<HWall> hWalls = Joiner.join(hSpots, new HWalls());

        walls = new ArrayList<Line2D>();

    }
    
    @Override
    public List<Line2D> walls() {
        return walls;        
    }
    
    private Spot randomSpot(final int width, final int height) {
        final int w = (int) (Math.random() * width);
        final int h = (int)(Math.random() * height);
        return new Spot(w, h);
    }
    
}
