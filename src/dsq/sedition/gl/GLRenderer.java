package dsq.sedition.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import dsq.sedition.core.Game;
import dsq.sedition.light.*;
import dsq.sedition.options.Options;
import dsq.sedition.view.*;
import dsq.sedition.scene.SceneDraw;
import dsq.sedition.sprite.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.util.List;

public class GLRenderer implements GLSurfaceView.Renderer {

    private final Context context;
    private final Game game;
    
    private int currentWidth;
    private int currentHeight;
    
    // FIX 11/06/12 Should I move floor into level ?
    private Sprite floorSprite;
    private Sprite timeSprite;
    private Sprite commandsSprite;
    private Sprite speedSprite;
    
    private Viewport mainView;
    private Viewport dashboardView;
    private Viewport timerView;
    private Viewport speedView;

    private Light sun;
    private Light headlight;
    
    private Jaguar topView = new TopJaguar();
    private Jaguar groundView = new GroundJaguar();

    public GLRenderer(final Context context, final Game game) {
        this.context = context;
        this.game = game;
    }

    @Override
    public void onDrawFrame(final GL10 g) {
        g.glMatrixMode(GL10.GL_MODELVIEW);
        g.glLoadIdentity();
        topView.onDraw(g, currentWidth, currentHeight, game);
//        g.glViewport(0, 0, currentWidth, currentHeight);
//        g.glClearColor(0.3f, 0.3f, 0.3f, 0.3f);
//        g.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
//        drawDashboard(g);
//        drawTimer(g);
//        drawMain(g);
//        drawSpeed(g);
        game.update();
    }

    private void drawSpeed(final GL10 g) {
        speedView.draw(g, new DefaultColour(1.0f, 0, 0, 1), new SceneDraw() {
            @Override
            public void draw(final GL10 g) {
                speedSprite.use(g);
            }
        });
    }

    private void drawDashboard(final GL10 g) {
        dashboardView.draw(g, new DefaultColour(0.3f, 0.3f, 0.3f, 1.0f), new SceneDraw() {
            @Override
            public void draw(final GL10 g) {
                commandsSprite.use(g);
            }
        });
    }

    private void drawMain(final GL10 g) {
//        mainView.setBackground(g, 0, 0, 0, 1.0f);
        g.glMatrixMode(GL10.GL_MODELVIEW);
        g.glLoadIdentity();
        final Colour black = new DefaultColour(0, 0, 0, 1);
        mainView.draw(g, black, new SceneDraw() {
            @Override
            public void draw(final GL10 g) {
                sun.display(g);
                game.camera().use(g);
                headlight.display(g);
//                drawScene(g);
            }
        });
    }

    private void drawTimer(final GL10 g) {
        timerView.draw(g, new DefaultColour(0.1f, 0.1f, 0.1f, 1.0f), new SceneDraw() {
            @Override
            public void draw(final GL10 g) {
                timeSprite.use(g);
            }
        });
    }

    private void drawScene(final GL10 g) {
        floorSprite.use(g);
        final List<Sprite> sprites = game.sprites();
        for (Sprite sprite : sprites) {
            sprite.use(g);
        }
    }

    @Override
    public void onSurfaceCreated(final GL10 g, final EGLConfig config) {

//        sun = new Sun(g, GL10.GL_LIGHT0);
//        headlight = new Headlight(g, GL10.GL_LIGHT1, new LightPosition(0, 0, 0, LightType.DIRECTIONAL), new LightDirection(0, 0, -1f));
//        floorSprite = new DefaultFloor();
//        timeSprite = new CountdownSprite(game.timer());
//        commandsSprite = new CommandsSprite();
//        speedSprite = new SpeedSprite(game.player());
//
//        // FIX 2/06/12 Make floorSprite a sprite?
//        floorSprite.loadGLTexture(g, this.context);
//        timeSprite.loadGLTexture(g, this.context);
//        commandsSprite.loadGLTexture(g, this.context);
//        speedSprite.loadGLTexture(g, this.context);
        topView.onCreate(g, context, game);
        
        // FIX 11/06/12 This is a bit clunky
        final List<Sprite> allSprites = game.allSprites();
        for (Sprite allSprite : allSprites) {
            allSprite.loadGLTexture(g, context);
        }

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
//        int h = height == 0 ? 1 : height;
//
//        g.glViewport(0, 0, width, h);
//        g.glMatrixMode(GL10.GL_PROJECTION);
//        g.glLoadIdentity();
//
//        final float aspect = (float) width / (float) h;
//        GLU.gluPerspective(g, 90f, aspect, 0.1f, farClip());
//
//        g.glMatrixMode(GL10.GL_MODELVIEW);
//        g.glLoadIdentity();
//
//        final int dashboardHeight = (int)(0.15 * currentHeight);
//        final int timerHeight = (int)(0.08 * currentHeight);
//        final int speedHeight = (int)(0.02 * currentHeight);
//
//        final int mainHeight = height - dashboardHeight - timerHeight - speedHeight;
//        dashboardView = new DefaultViewport(0, 0, width, dashboardHeight);
//        speedView = new DefaultViewport(0, dashboardHeight, width, speedHeight);
//        mainView = new DefaultViewport(0, dashboardHeight + speedHeight, width, mainHeight);
//        timerView = new DefaultViewport(0, height - timerHeight, width, timerHeight);
        topView.onResize(g, currentWidth, currentHeight, game);
    }

    private float farClip() {
        final Options options = game.getOptions();
        switch (options.difficulty) {
            case EASY: return 100.0f;
            case MEDIUM: return 20.0f;
            case HARD: return 10.0f;
            case EXTREME: return 5.0f;
            case ASPIRATIONAL: return 1.0f;
        }
        return 100.0f;
    }
}