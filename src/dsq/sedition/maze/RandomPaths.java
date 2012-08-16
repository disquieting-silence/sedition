package dsq.sedition.maze;

import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

import java.util.*;

public class RandomPaths {
    
    private static final int NUM_ATTEMPTS = 20;

    public static Path calculate(final Spot start, final int width, final int height, final int amount) {
        
        Path current = new Path(start, new ArrayList<Direction>(), start);
        for (int i = 0; i < NUM_ATTEMPTS; i++) {
            final Path candidate = calculate(start, width, height, amount, i == NUM_ATTEMPTS - 1);
            final int steps = candidate.moves.size();
            if (steps == amount) return candidate;
            else if (steps > current.moves.size()) current = candidate;
        }
        return current;
    }

    private static Path calculate(final Spot start, final int width, final int height, final int amount, final boolean force) {
        final Set<Integer> set = new HashSet<Integer>();
        final int id = Spots.hash(start, width);
        set.add(id);
        final List<Direction> moves = new ArrayList<Direction>();

        Spot current = start;
        for (int i = 0; i < amount; i++) {
            final Option<Direction> moveOption = possibleMove(current, set, width, height);
            if (moveOption.isSet()) {
                final Direction move = moveOption.getOrDie();
                final Option<Spot> nextOption = Travel.go(current, move, 0, width - 1, 0, height - 1);
                // FIX 16/08/12 Should be safe.
                current = nextOption.getOrDie();
                moves.add(move);
                set.add(Spots.hash(current, width));
            } else {
                return new Path(start, moves, current);
            }
        }
        return new Path(start, moves, current);
    }

   

    private static Option<Direction> possibleMove(final Spot current, final Set<Integer> set, final int width, final int height) {
        final List<Direction> values = Arrays.asList(Direction.values());
        // FIX 15/08/12 To re-generate a particular maze, will want to specify the seed.
        Collections.shuffle(values);
        for (final Direction direction : values) {
            // FIX 16/08/12 This is getting out of hand.
            final Option<Spot> nextOption = Travel.go(current, direction, 0, width - 1, 0, height - 1);
            if (nextOption.isSet()) {
                final Spot next = nextOption.getOrDie();
                final int id = Spots.hash(next, width);
                if (!set.contains(id)) return new Some<Direction>(direction);
            }
        }
        return new None<Direction>();
    }
}
