package dsq.sedition.maze.path;

import dsq.sedition.maze.data.Bounds;
import dsq.sedition.maze.data.Direction;
import dsq.sedition.maze.data.Path;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.util.Spots;
import dsq.sedition.maze.util.Travel;
import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

import java.util.*;

/* This is terribly inefficient, and can overflow the stack. Best not to use ... but I've kept it here so that the
code still exists.
 */
public class IndirectPather implements Pather {

    private static final long TIMEOUT = 100L;

    @Override
    public Path generate(final Spot start, final Option<Spot> finishOption, final Bounds bounds) {
        final Spot finish = finishOption.getOrDie();
        final int startId = Spots.hash(start, bounds.width);
        final HashSet<Integer> set = new HashSet<Integer>();
        set.add(startId);
        Option<List<Direction>> movesOption = calculate(System.currentTimeMillis(), start, finish, bounds, set);
        while (!movesOption.isSet())
            movesOption = calculate(System.currentTimeMillis(), start, finish, bounds, set);

        final List<Direction> moves = movesOption.getOr(new LinkedList<Direction>());
        return new Path(start, moves, finish);
    }

    // FIX 16/08/12 This seems to loop endlessly a bit. Need to fix.
    private Option<List<Direction>> calculate(final long time, final Spot current, final Spot finish, final Bounds bounds, final Set<Integer> been) {
        if (System.currentTimeMillis() - time > TIMEOUT) return new None<List<Direction>>();

        if (current.x == finish.x && current.z == finish.z) {
            return new Some<List<Direction>>(new ArrayList<Direction>());
        }

        final List<Direction> moves = allMoves();

        for (Direction move : moves) {
            final Option<Spot> nextOption = Travel.go(current, move, bounds);

            if (nextOption.isSet()) {
                final Spot next = nextOption.getOrDie();
                final int nextId = Spots.hash(next, bounds.width);
                if (!been.contains(nextId)) {
                    final Set<Integer> set = new HashSet<Integer>(been);
                    set.add(nextId);
                    final Option<List<Direction>> restOption = calculate(time, next, finish, bounds, set);
                    if (restOption.isSet()) {
                        final List<Direction> rest = restOption.getOrDie();
                        // FIX 15/08/12 Should I duplicate here? This really is so much easier when you have immutability ...
                        rest.add(0, move);
                        return new Some<List<Direction>>(rest);
                    }
                }
            }
        }
        return new None<List<Direction>>();

    }

    private List<Direction> allMoves() {
        final List<Direction> r = Arrays.asList(Direction.values());
        // FIX 15/08/12 To re-gen a particular maze, will want to specify the seed.
        Collections.shuffle(r);
        return r;
    }
}
