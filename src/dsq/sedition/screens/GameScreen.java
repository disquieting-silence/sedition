package dsq.sedition.screens;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import dsq.sedition.core.DefaultGame;
import dsq.sedition.core.Game;
import dsq.sedition.event.DefaultEventListener;
import dsq.sedition.event.EventListener;
import dsq.sedition.gl.GLRenderer;
import dsq.sedition.maze.level.DefaultLevelGenerator;
import dsq.sedition.maze.level.LevelGenerator;
import dsq.sedition.options.Difficulty;
import dsq.sedition.options.Options;
import dsq.sedition.sprite.CoreSprites;
import dsq.sedition.sprite.DefaultCoreSprites;
import dsq.sedition.ui.DefaultGameUi;
import dsq.sedition.ui.GameUi;

/* This project has been inspired by: http://www.javacodegeeks.com/2011/09/android-game-development-switching-from.html */
public class GameScreen extends Activity {

    private GLSurfaceView glSurfaceView;

    private CoreSprites sprites = new DefaultCoreSprites();
    private LevelGenerator levels = new DefaultLevelGenerator(sprites);
    private final EventListener events = new DefaultEventListener(this);
    private Game game;
    private GLRenderer renderer;

    private final GameUi ui = new DefaultGameUi();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        // FIX 18/08/12 This probably isn't taking into account saved state. Maybe I do need thedroid here.
        final Options options = (Options)getIntent().getSerializableExtra(Options.KEY);

        game = new DefaultGame(levels, events, options != null ? options : new Options(Difficulty.EASY));
        renderer = new GLRenderer(sprites, this, game);

        events.init();

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setFullScreen();

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setOnTouchListener(ui.onTouch(game, renderer));
        glSurfaceView.setRenderer(renderer);
        setContentView(glSurfaceView);
        events.startGame();
    }

    private void setFullScreen() {
        final int fullScreen = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(fullScreen, fullScreen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }
}
