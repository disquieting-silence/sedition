package dsq.sedition;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import dsq.sedition.core.DefaultGame;
import dsq.sedition.core.Level;
import dsq.sedition.event.DefaultEventListener;
import dsq.sedition.event.EventListener;
import dsq.sedition.gl.GLRenderer;
import dsq.sedition.maze.level.Level1;
import dsq.sedition.ui.DefaultGameUi;
import dsq.sedition.ui.GameUi;
import dsq.sedition.core.Game;

/* This project has been inspired by: http://www.javacodegeeks.com/2011/09/android-game-development-switching-from.html */
public class Main extends Activity {
    
    private GLSurfaceView glSurfaceView;

    private Level level1 = Level1.level();
    private final EventListener events = new DefaultEventListener(this);
    private final Game game = new DefaultGame(level1, events);
    private final GLRenderer renderer = new GLRenderer(this, game);

    private final GameUi ui = new DefaultGameUi();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        events.init();
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setFullScreen();

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setOnTouchListener(ui.onTouch(game));
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
