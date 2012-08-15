package dsq.sedition.maze;

import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

import java.util.*;

import static dsq.sedition.maze.Direction.NORTH;

public class Cann {
    
    private Cann() { }
    
    public static Path cann(final Spot start, final int width, final int height, final Spot finish) {
        final Option<List<Direction>> movesOption = cann(start, finish, width, height, new HashSet<Integer>());
        final List<Direction> moves = movesOption.getOr(new LinkedList<Direction>());


        return new Path(start, moves, finish);
    }
    
    private static Option<List<Direction>> cann(final Spot current, final Spot finish, final int width, final int height, final Set<Integer> been) {
        if (current.x == finish.x && current.z == finish.z)
            return new Some<List<Direction>>(new ArrayList<Direction>());
        
        final Direction[] moves = Direction.values();
        for (Direction move : moves) {
            final Option<Spot> nextOption = Gat.gat(current, move, 0, width - 1, 0, height - 1);
          
            if (nextOption.isSet()) {
                final Spot next = nextOption.getOrDie();
                System.out.println("Next = (" + next.x + ", " + next.z + ")");
                final int nextId = Spots.hash(next, width);
                System.out.println("nextId = " + nextId);
                System.out.println("been = " + been);
                if (!been.contains(nextId)) {
                    final Set<Integer> set = new HashSet<Integer>(been);
                    set.add(nextId);
                    final Option<List<Direction>> restOption = cann(next, finish, width, height, set);
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
}
