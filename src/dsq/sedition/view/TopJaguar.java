package dsq.sedition.view;

import android.content.Context;
import dsq.sedition.core.Game;
import dsq.sedition.scene.SceneDraw;
import dsq.sedition.sprite.DefaultColour;
import dsq.sedition.sprite.Sprite;

import javax.microedition.khronos.opengles.GL10;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// FIX 18/08/12 Rename .
public class TopJaguar implements Jaguar {

    public static final double COMMANDS_HEIGHT = 0.15;
    private Viewport mainView;
    private Viewport dashboardView;

    private Sprite commandsSprite;

    private final Jaguars jags = new DefaultJaguars();

    @Override
    public void onCommand(final Game game, final float left, final float top) {
    }

    @Override
    public void offCommand(final Game game) {
    }

    @Override
    public void onCreate(final GL10 g, final Context context, final Game game) {
        commandsSprite = new CommandsSprite();
        commandsSprite.loadGLTexture(g, context);
    }

    @Override
    public void onResize(final GL10 g, final int width, final int height, final Game game) {
        final int dashboardHeight = (int)(0.15 * height);
        
        final int mainHeight = height - dashboardHeight;
        dashboardView = new DefaultViewport(0, 0, width, dashboardHeight);
        mainView = new DefaultViewport(0, dashboardHeight, width, mainHeight);
        jags.projection(g, width, height, 100.0f);
    }

    @Override
    public void onDraw(final GL10 g, final int width, final int height, final Game game) {
        g.glViewport(0, 0, width, height);
        g.glClearColor(0.3f, 0.7f, 0.3f, 0.3f);
        g.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        final Box dashBox = new Box(0, 0, width, (int) (COMMANDS_HEIGHT * height));
        final Box mainBox = new Box(0, dashBox.height, width, height - dashBox.height);
        
        jags.draw(g, dashBox, Arrays.asList(commandsSprite), new DefaultColour(0.3f, 0.3f, 0.3f, 1.0f));
        drawMain(g, game, mainBox);
    }

    private void drawMain(final GL10 g, final Game game, final Box mainBox) {
        final ArrayList<GameModel> mainModels = new ArrayList<GameModel>(game.sprites());
        mainModels.add(0, game.camera());
        jags.draw(g, mainBox, mainModels, new DefaultColour(0.5f, 0.5f, 0.5f, 1.0f));
    }
}
