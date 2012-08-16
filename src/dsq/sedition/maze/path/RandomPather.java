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

public class RandomPather implements Pather {
    
    private final int amount;

    private static final int NUM_ATTEMPTS = 20;

    public RandomPather(final int amount) {
        this.amount = amount;
    }

    @Override
    public Path generate(final Spot start, final Option<Spot> finishOption, final Bounds bounds) {
        Path current = new Path(start, new ArrayList<Direction>(), start);
        for (int i = 0; i < NUM_ATTEMPTS; i++) {
            final Path candidate = calculate(start, bounds, amount);
            final int steps = candidate.moves.size();
            if (steps == amount) return candidate;
            else if (steps > current.moves.size()) current = candidate;
        }
        return current;        
    }

    private Path calculate(final Spot start, final Bounds bounds, final int amount) {
        final Set<Integer> set = new HashSet<Integer>();
        final int id = Spots.hash(start, bounds.width);
        set.add(id);
        final List<Direction> moves = new ArrayList<Direction>();

        Spot current = start;
        for (int i = 0; i < amount; i++) {
            final Option<Direction> moveOption = possibleMove(current, set, bounds);
            if (moveOption.isSet()) {
                final Direction move = moveOption.getOrDie();
                final Option<Spot> nextOption = Travel.go(current, move, bounds);
                // FIX 16/08/12 Should be safe.
                current = nextOption.getOrDie();
                moves.add(move);
                set.add(Spots.hash(current, bounds.width));
            } else {
                return new Path(start, moves, current);
            }
        }
        return new Path(start, moves, current);
    }

    private static Option<Direction> possibleMove(final Spot current, final Set<Integer> set, final Bounds bounds) {
        final List<Direction> values = Arrays.asList(Direction.values());
        // FIX 15/08/12 To re-gen a particular maze, will want to specify the seed.
        Collections.shuffle(values);
        for (final Direction direction : values) {
            // FIX 16/08/12 This is getting out of hand.
            final Option<Spot> nextOption = Travel.go(current, direction, bounds);
            if (nextOption.isSet()) {
                final Spot next = nextOption.getOrDie();
                final int id = Spots.hash(next, bounds.width);
                if (!set.contains(id)) return new Some<Direction>(direction);
            }
        }
        return new None<Direction>();
    }

}
