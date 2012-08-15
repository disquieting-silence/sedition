package dsq.sedition.maze;

import java.util.List;

public class Path {
    public final Spot start;
    public final List<Direction> moves;
    public final Spot finish;

    public Path(final Spot start, final List<Direction> moves, final Spot finish) {
        this.start = start;
        this.moves = moves;
        this.finish = finish;
    }
}
