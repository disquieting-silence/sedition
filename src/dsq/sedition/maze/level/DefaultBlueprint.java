package dsq.sedition.maze.level;

import dsq.sedition.maze.data.Spot;
import dsq.sedition.maze.util.Spots;
import dsq.sedition.maze.walls.HWalls;
import dsq.sedition.maze.walls.VWalls;
import dsq.sedition.maze.walls.Walls;
import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

import java.util.*;

// FIX 15/08/12 Split me. Please. I beg you.
public class DefaultBlueprint implements Blueprint {
    
    private final int width;
    private final Map<Integer, Spot> horizontal = new HashMap<Integer, Spot>();
    private final Map<Integer, Spot> vertical = new HashMap<Integer, Spot>();

    public DefaultBlueprint(final int width) {
        this.width = width;
    }
    
    @Override
    public Option<Spot> getV(final int x, final int z) {
        return get(x, z, vertical);
    }

    @Override
    public Option<Spot> getH(final int x, final int z) {
        return get(x, z, horizontal);
    }
    
    @Override
    public void addV(final Spot spot) {
        add(spot, vertical);
    }

    @Override
    public void addV(final int x, final int z) {
        add(x, z, vertical);
    }

    @Override
    public void addH(final Spot spot) {
        add(spot, horizontal);
    }

    @Override
    public void addH(final int x, final int z) {
        add(x, z, horizontal);
    }

    @Override
    public boolean containsV(final int x, final int z) {
        return contains(x, z, vertical);
    }

    @Override
    public boolean containsH(final int x, final int z) {
        return contains(x, z, horizontal);
    }

    @Override
    public Option<Spot> removeV(final int x, final int z) {
        return remove(x, z, vertical);
    }

    @Override
    public Option<Spot> removeV(final Spot spot) {
        return remove(spot.x, spot.z, vertical);
    }

    @Override
    public Option<Spot> removeH(final int x, final int z) {
        return remove(x, z, horizontal);
    }

    @Override
    public Option<Spot> removeH(final Spot spot) {
        return remove(spot.x, spot.z, horizontal);
    }

    @Override
    public List<Spot> vWalls() {
        final List<Spot> r = new ArrayList<Spot>(vertical.values());
        return sort(r, new VWalls());
    }

    @Override
    public List<Spot> hWalls() {
        final List<Spot> r = new ArrayList<Spot>(horizontal.values());
        return sort(r, new HWalls());
    }

    private List<Spot> sort(final List<Spot> input, final Walls walls) {
        final List<Spot> r = new ArrayList<Spot>(input);
        Collections.sort(r, walls.comparator());
        return r;
    }

    @Override
    public Blueprint copy() {
        final Blueprint r = new DefaultBlueprint(width);
        for (Integer x : horizontal.keySet()) {
            r.addH(horizontal.get(x));
        }
        for (Integer x : vertical.keySet()) {
            r.addV(vertical.get(x));
        }
        return r;
    }

    private int id(final int x, final int z) {
        return Spots.hash(new Spot(x, z), width);
    }

    private int id(final Spot spot) {
        return Spots.hash(spot, width);
    }

    public void add(final Spot spot, final Map<Integer, Spot> map) {
        final int id = id(spot);
        map.put(id, spot);
    }

    public void add(final int x, final int z, final Map<Integer, Spot> map) {
        add(new Spot(x, z), map);
    }

    private Option<Spot> remove(final int x, final int z, final Map<Integer, Spot> map) {
        final int id = id(x, z);
        final Spot r = map.remove(id);
        return opt(r);
    }

    public boolean contains(final int x, final int z, final Map<Integer, Spot> map) {
        final int id = id(x, z);
        return map.containsKey(id);
    }

    private Option<Spot> get(final int x, final int z, Map<Integer, Spot> map) {
        final int id = id(x, z);
        final Spot spot = map.get(id);
        return opt(spot);
    }

    private Option<Spot> opt(final Spot spot) {
        return spot == null ? new None<Spot>() : new Some<Spot>(spot);
    }
}
