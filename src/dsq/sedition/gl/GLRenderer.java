package dsq.sedition.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import dsq.sedition.core.Game;
import dsq.sedition.core.ViewState;
import dsq.sedition.light.*;
import dsq.sedition.view.*;
import dsq.sedition.sprite.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.util.List;

public class GLRenderer implements GLSurfaceView.Renderer, GameViewer {

    private final Context context;
    private final Game game;
    
    private int currentWidth;
    private int currentHeight;
    
    private Light sun;

    private final GameView topView = new TopGameView();
    private final GameView groundView = new GroundGameView();
    private final CoreSprites sprites;
    
    private GameView current = topView;

    public GLRenderer(final CoreSprites sprites, final Context context, final Game game) {
        this.sprites = sprites;
        this.context = context;
        this.game = game;
    }

    @Override
    public void onDrawFrame(final GL10 g) {
        clear(g);
        g.glMatrixMode(GL10.GL_MODELVIEW);
        g.glLoadIdentity();
        sun.use(g);
        final GameView newCurrent = getView();
        if (current != newCurrent) newCurrent.onResize(g, currentWidth, currentHeight, game);
        current = newCurrent;
        current.onDraw(g, currentWidth, currentHeight, game);
        game.update();
    }

    private void clear(final GL10 g) {
        g.glViewport(0, 0, currentWidth, currentHeight);
        g.glClearColor(0.3f, 0.7f, 0.3f, 0.3f);
        g.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void onSurfaceCreated(final GL10 g, final EGLConfig config) {
        sun = new Sun(g, GL10.GL_LIGHT0);
        topView.onCreate(g, context, game);
        groundView.onCreate(g, context, game);
        
        sprites.load(g, context);
//
//        // FIX 11/06/12 This is a bit clunky
//        final List<Sprite> allSprites = game.allSprites();
//        for (Sprite allSprite : allSprites) {
//            allSprite.loadGLTexture(g, context);
//        }

        g.glEnable(GL10.GL_TEXTURE_2D);
        g.glShadeModel(GL10.GL_SMOOTH);
        g.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        setupDepth(g);
        g.glEnable(GL10.GL_LIGHTING);
        g.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    private void setupDepth(final GL10 g) {
        g.glClearDepthf(1.0f);
        g.glEnable(GL10.GL_DEPTH_TEST);
        g.glDepthFunc(GL10.GL_LEQUAL);
    }
    
    @Override
    public void onSurfaceChanged(final GL10 g, final int width, final int height) {
        currentWidth = width;
        currentHeight = height;
        current = getView();
        current.onResize(g, currentWidth, currentHeight, game);
    }

    public GameView getView() {
        return game.currentView() == ViewState.GROUND ? groundView : topView;
    }
}