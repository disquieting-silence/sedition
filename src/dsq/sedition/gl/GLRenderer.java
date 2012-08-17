package dsq.sedition.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import dsq.sedition.core.Game;
import dsq.sedition.light.*;
import dsq.sedition.scene.DefaultViewport;
import dsq.sedition.scene.SceneDraw;
import dsq.sedition.scene.Viewport;
import dsq.sedition.sprite.*;
import dsq.sedition.timer.DefaultMutableTimer;
import dsq.sedition.timer.MutableTimer;
import dsq.sedition.timer.Timer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
import java.util.List;

public class GLRenderer implements GLSurfaceView.Renderer {

    public static final int DASHBOARD_HEIGHT = 100;
    public static final int TIMER_HEIGHT = 50;

    private final Context context;
    private final Game game;
    
    private int currentWidth;
    private int currentHeight;
    
    // FIX 11/06/12 Should I move floor into level ?
    private Floor floor;
    private Sprite timer;
    private Sprite commands;
    
    private Viewport mainView;
    private Viewport dashboardView;
    private Viewport timerView;

    private Light sun;
    private Light headlight;

    public GLRenderer(final Context context, final Game game) {
        this.context = context;
        this.game = game;
    }

    @Override
    public void onDrawFrame(final GL10 g) {
        g.glViewport(0, 0, currentWidth, currentHeight);
        g.glClearColor(0.3f, 0.3f, 0.3f, 0.3f);
        g.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        drawDashboard(g);
        drawTimer(g);
        drawMain(g);
        game.update();
    }

    private void drawDashboard(final GL10 g) {
        dashboardView.draw(g, new DefaultColour(0.3f, 0.3f, 0.3f, 1.0f), new SceneDraw() {
            @Override
            public void draw(final GL10 g) {
                g.glDisable(GL10.GL_LIGHTING);
                g.glMatrixMode(GL10.GL_PROJECTION);
                g.glPushMatrix();
                g.glLoadIdentity();
                g.glOrthof(0, 3, 0, 1, -1, 1);
                g.glMatrixMode(GL10.GL_MODELVIEW);
                g.glPushMatrix();
                g.glLoadIdentity();
                commands.draw(g);
                g.glPopMatrix();
                g.glMatrixMode(GL10.GL_PROJECTION);
                g.glPopMatrix();
                g.glMatrixMode(GL10.GL_MODELVIEW);
                g.glEnable(GL10.GL_LIGHTING);
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
                game.camera().position(g);
                headlight.display(g);
                drawScene(g);
            }
        });
    }

    private void drawTimer(final GL10 g) {
        timerView.draw(g, new DefaultColour(0.1f, 0.1f, 0.1f, 1.0f), new SceneDraw() {
            @Override
            public void draw(final GL10 g) {
                g.glPushMatrix();
                g.glLoadIdentity();
                timer.draw(g);
                g.glPopMatrix();
            }
        });
    }

    private void drawScene(final GL10 g) {
        floor.draw(g);
        final List<Sprite> sprites = game.sprites();
        for (Sprite sprite : sprites) {
            sprite.draw(g);
        }
    }

    @Override
    public void onSurfaceCreated(final GL10 g, final EGLConfig config) {

        sun = new Sun(g, GL10.GL_LIGHT0);
        headlight = new Headlight(g, GL10.GL_LIGHT1, new LightPosition(0, 0, 0, LightType.DIRECTIONAL), new LightDirection(0, 0, -1f));
        floor = new DefaultFloor();
        timer = new CountdownSprite(game.timer());
        commands = new CommandsSprite();

        // FIX 2/06/12 Make floor a sprite?
        floor.loadGLTexture(g, this.context);
        timer.loadGLTexture(g, this.context);
        commands.loadGLTexture(g, this.context);
        
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
        int h = height == 0 ? 1 : height;

        currentWidth = width;
        currentHeight = height;

        g.glViewport(0, 0, width, h);
        g.glMatrixMode(GL10.GL_PROJECTION);
        g.glLoadIdentity();

        final float aspect = (float) width / (float) h;
        GLU.gluPerspective(g, 90f, aspect, 0.1f, 100.0f);

        g.glMatrixMode(GL10.GL_MODELVIEW);
        g.glLoadIdentity();

        final int mainHeight = height - DASHBOARD_HEIGHT - TIMER_HEIGHT;
        mainView = new DefaultViewport(0, DASHBOARD_HEIGHT, width, mainHeight);
        dashboardView = new DefaultViewport(0, 0, width, DASHBOARD_HEIGHT);
        timerView = new DefaultViewport(0, height - TIMER_HEIGHT, width, TIMER_HEIGHT);
    }
}