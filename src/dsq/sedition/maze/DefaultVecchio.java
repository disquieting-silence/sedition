package dsq.sedition.maze;

import dsq.sedition.util.None;
import dsq.sedition.util.Option;
import dsq.sedition.util.Some;

import java.util.HashMap;
import java.util.Map;

public class DefaultVecchio implements Vecchio {
    
    private final int width;
    // FIX 15/08/12 Probably not needed
    private final int height;
    private final Map<Integer, Spot> data = new HashMap<Integer, Spot>();

    public DefaultVecchio(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Option<Spot> get(final int x, final int z) {
        final int id = id(x, z);
        final Spot spot = data.get(id);
        return spot == null ? new None<Spot>() : new Some<Spot>(spot);
    }

    private int id(final int x, final int z) {
        return Spots.hash(new Spot(x, z), width);
    }
    
    private int id(final Spot spot) {
        return Spots.hash(spot, width);
    }

    @Override
    public void add(final Spot spot) {
        final int id = id(spot);
        data.put(id, spot);
    }

    @Override
    public void add(final int x, final int z) {
        add(new Spot(x, z));
    }

    @Override
    public boolean contains(final int x, final int z) {
        final int id = id(x, z);
        return data.containsKey(id);
    }
}
