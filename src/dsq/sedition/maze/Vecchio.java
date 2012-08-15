package dsq.sedition.maze;

import dsq.sedition.util.Option;

// FIX 15/08/12 Rename me.
public interface Vecchio {
    
    Option<Spot> get(final int x, final int z);
    void add(final Spot spot);
    void add(final int x, final int z);
    boolean contains(final int x, final int z);
}
