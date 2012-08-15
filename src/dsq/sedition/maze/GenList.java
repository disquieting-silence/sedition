package dsq.sedition.maze;

import java.util.ArrayList;
import java.util.List;

public class GenList {

    private GenList() { }

    public static List<Spot> vertical(int width, int height) {
        final List<Spot> r = new ArrayList<Spot>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j <= width; j++) {
                r.add(new Spot(j, i));
            }
        }
        return r;
    }

    public static List<Spot> horizontal(int width, int height) {
        final List<Spot> r = new ArrayList<Spot>();
        for (int i = 0; i <= height; i++) {
            for (int j = 0; j < width; j++) {
                r.add(new Spot(j, i));
            }
        }
        return r;
    }
}
