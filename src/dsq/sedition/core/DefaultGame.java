package dsq.sedition.core;

import android.graphics.drawable.LevelListDrawable;
import dsq.sedition.collision.Collidable;
import dsq.sedition.event.EventListener;
import dsq.sedition.options.Options;
import dsq.sedition.scene.GameCamera;
import dsq.sedition.scene.Camera;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.scene.GlimpseCamera;
import dsq.sedition.timer.DefaultMutableTimer;
import dsq.sedition.timer.MutableTimer;
import dsq.sedition.timer.Timer;

import java.util.List;

public class DefaultGame implements Game {

    public static final float ZOOM_OUT_RATE = 0.0002f;
    private final MutablePlayer player;
    private final GlimpseCamera camera;
    
    private ViewState viewState = ViewState.TOP;
    private Level level;
    private final MutableTimer clock;
    private Options options;

    // FIX 9/06/12 Clean this up.
    public DefaultGame(final Level level, final EventListener events) {
        this.level = level;
        player = new DefaultPlayer(level.start(), randomDirection(), events);
        camera = new GameCamera(player);
        clock = new DefaultMutableTimer(events);
    }

    private float randomDirection() {
        return (float)(Math.random() * 360);
    }

    @Override
    public void changeLevel(final Level level) {
        // FIX 18/08/12 Should I check the viewState here?
        this.level = level;
        // FIX 18/08/12 Mutating is slightly cleaner than the alternative of watching what has already received a Player.
        // FIX 18/08/12 I think my divisions are wrong.
        player.setPosition(level.start());
        player.setDirection(randomDirection());
    }

    @Override
    public void turnLeft() {
        player.setTurnRate(-2f);
    }

    @Override
    public void turnRight() {
        player.setTurnRate(2f);
    }

    @Override
    public void speedUp() {
        player.setAcceleration(0.003f);
    }

    @Override
    public void slowDown() {
        player.setAcceleration(-0.0005f);
    }

    @Override
    public void holdPace() {
        player.setAcceleration(-0.0001f);
    }

    // FIX 18/08/12 Don't like this mutability here.
    @Override
    public void setOptions(final Options options) {
        this.options = new Options(options.difficulty);
    }

    @Override
    public Options getOptions() {
        return new Options(options.difficulty);
    }

    @Override
    public Player player() {
        return player;
    }

    @Override
    public Camera camera() {
        return camera;
    }

    @Override
    public List<Sprite> sprites() {
        return level.sprites(viewState);
    }

    @Override
    public Timer timer() {
        return clock;
    }

    @Override
    public List<Sprite> allSprites() {
        return level.allSprites();
    }

    @Override
    public ViewState currentView() {
        return viewState;
    }

    @Override
    public void update() {
        // FIX 11/06/12 A better approach for this dual state would be better. Dare I say fold?
        if (viewState == ViewState.TOP) {
            final float newScale = camera.getScale() - ZOOM_OUT_RATE;
            camera.setScale(newScale);
            if (newScale < 0.3) skipToGround();
        } else {
            final List<Collidable> obstacles = level.obstacles(viewState);
            clock.update();
            player.update(obstacles);
        }
    }

    // FIX 18/08/12 Not happy with this mutability either.
    @Override
    public void skipToGround() {
        camera.transition();
        final long amount = gameTimer();
        clock.start(amount);
        viewState = ViewState.GROUND;
    }

    private long gameTimer() {
        switch (options.difficulty) {
            case EASY: return 120000L;
            case MEDIUM: return 85000L;
            case HARD: return 45000L;
            case EXTREME: return 20000L;
            case ASPIRATIONAL: return 8000L;
        }
        return 85000L;
    }

    @Override
    public void stopTurning() {
        player.setTurnRate(0);
    }

}
