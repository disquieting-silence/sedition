package dsq.sedition.maze.data;

public class Bounds {
    public final int minX;
    public final int maxX;
    public final int minZ;
    public final int maxZ;
    public final int width;
    public final int height;

    public Bounds(final int width, final int height) {
        this(width, height, 0, width - 1, 0, height - 1);
    }
    
    public Bounds(final int width, final int height, final int minX, final int maxX, final int minZ, final int maxZ) {
        this.minX = minX;
        this.maxX = maxX;
        this.minZ = minZ;
        this.maxZ = maxZ;
        this.width = width;
        this.height = height;
    }
}
