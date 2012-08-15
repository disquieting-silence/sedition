package dsq.sedition.maze;

import dsq.sedition.util.Option;

import java.util.List;

// FIX 15/08/12 Rename me.
public interface Skeleton {
    
    Option<Spot> getV(final int x, final int z);
    Option<Spot> getH(final int x, final int z);
    void addV(final Spot spot);
    void addV(final int x, final int z);
    void addH(final Spot spot);
    void addH(final int x, final int z);
    boolean containsV(final int x, final int z);
    boolean containsH(final int x, final int z);
    Option<Spot> removeV(final int x, final int z);
    Option<Spot> removeV(final Spot spot);
    Option<Spot> removeH(final int x, final int z);
    Option<Spot> removeH(final Spot spot);
    
    List<Spot> vWalls();
    List<Spot> hWalls();
    
    Skeleton copy();
}
