package dsq.sedition.maze;

import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

import java.util.*;

public class Paths {

    public static final long TIMEOUT = 100L;

    private Paths() { }
    
    public static Path calculate(final Spot start, final int width, final int height, final Spot finish) {
        final HashSet<Integer> set = new HashSet<Integer>(Spots.hash(start, width));
        Option<List<Direction>> movesOption = calculate(System.currentTimeMillis(), start, finish, width, height, set);
        while (!movesOption.isSet())
            movesOption = calculate(System.currentTimeMillis(), start, finish, width, height, set);

        final List<Direction> moves = movesOption.getOr(new LinkedList<Direction>());
        return new Path(start, moves, finish);
    }

    // FIX 16/08/12 This seems to loop endlessly a bit. Need to fix.
    private static Option<List<Direction>> calculate(final long time, final Spot current, final Spot finish, final int width, final int height, final Set<Integer> been) {
        if (System.currentTimeMillis() - time > TIMEOUT) return new None<List<Direction>>();
        
        if (current.x == finish.x && current.z == finish.z) {
            return new Some<List<Direction>>(new ArrayList<Direction>());
        }
        
        final List<Direction> moves = allMoves();

        for (Direction move : moves) {
            final Option<Spot> nextOption = Travel.go(current, move, 0, width - 1, 0, height - 1);
          
            if (nextOption.isSet()) {
                final Spot next = nextOption.getOrDie();
                final int nextId = Spots.hash(next, width);
                if (!been.contains(nextId)) {
                    final Set<Integer> set = new HashSet<Integer>(been);
                    set.add(nextId);
                    final Option<List<Direction>> restOption = calculate(time, next, finish, width, height, set);
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

    private static List<Direction> allMoves() {
        final List<Direction> r = Arrays.asList(Direction.values());
        // FIX 15/08/12 To re-generate a particular maze, will want to specify the seed.
        Collections.shuffle(r);
        return r;
    }
}
