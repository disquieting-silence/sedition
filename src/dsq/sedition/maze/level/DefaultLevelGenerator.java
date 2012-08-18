package dsq.sedition.maze.level;

import dsq.sedition.collision.Line2D;
import dsq.sedition.core.Level;
import dsq.sedition.maze.data.Spot;
import dsq.sedition.options.Options;
import dsq.sedition.scene.Coordinate;
import dsq.sedition.sprite.CoreSprites;

import java.util.ArrayList;
import java.util.List;

public class DefaultLevelGenerator implements LevelGenerator {
    
    private final CoreSprites sprites;

    public DefaultLevelGenerator(final CoreSprites sprites) {
        this.sprites = sprites;
    }

    @Override
    public Level generate(final Options options) {
        final int width = 10;
        final int height = 10;
        
        final float margin = getMargin(options);
        final MazeLevel level = new RandomLevel(width, height);
        final List<Line2D> walls = level.walls();
        final List<Line2D> scaled = new ArrayList<Line2D>();
        for (final Line2D w : walls) {
            final float x1 = (w.x1 - width / 2) * margin;
            final float x2 = (w.x2 - width / 2) * margin;
            final float z1 = (w.z1 - height / 2) * margin;
            final float z2 = (w.z2 - height / 2) * margin;
            scaled.add(new Line2D(x1, z1, x2, z2));
        }

        final Coordinate start = coord(level.start(), width, height, margin);
        final Coordinate finish = coord(level.finish(), width, height, margin);
        return new DefaultLevel(sprites, start, finish, scaled);
    }

    private float getMargin(final Options options) {
        switch (options.difficulty) {
            case EASY: return 4;
        }
        return 2;
    }

    private static Coordinate coord(final Spot spot, final int width, final int height, final float margin) {
        int x = spot.x - width/2;
        int z = spot.z - height/2;
        return new Coordinate(x * margin + 0.5f, 0, z * margin + 0.5f);
    }
}
