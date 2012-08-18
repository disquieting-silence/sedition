package dsq.sedition.view;

import android.content.Context;
import dsq.sedition.core.Game;
import dsq.sedition.light.*;
import dsq.sedition.options.Options;
import dsq.sedition.scene.SceneDraw;
import dsq.sedition.sprite.Colour;
import dsq.sedition.sprite.DefaultColour;
import dsq.sedition.sprite.DefaultFloor;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.util.Colours;

import javax.microedition.khronos.opengles.GL10;
import java.util.ArrayList;
import java.util.List;

public class GroundJaguar implements Jaguar {
    
    private final Jaguars jags = new DefaultJaguars();

    private Sprite floor;
    private Sprite timer;
    private Sprite commands;
    private Sprite speedo;

    private Light headlight;
    
    @Override
    public void onCommand(final Game game, final float left, final float top) {
    }

    @Override
    public void offCommand(final Game game) {
    }

    @Override
    public void onCreate(final GL10 g, final Context context, final Game game) {
        floor = new DefaultFloor();
        timer = new CountdownSprite(game.timer());
        commands = new CommandsSprite();
        speedo = new SpeedSprite(game.player());
        headlight = new Headlight(g, GL10.GL_LIGHT1, new LightPosition(0, 0, 0, LightType.DIRECTIONAL), new LightDirection(0, 0, -1f));
        
        floor.loadGLTexture(g, context);
        timer.loadGLTexture(g, context);
        commands.loadGLTexture(g, context);
        speedo.loadGLTexture(g, context);
    }

    @Override
    public void onResize(final GL10 g, final int width, final int height, final Game game) {
        final float clip = farClip(game);
        jags.projection(g, width, height, clip);
    }

    @Override
    public void onDraw(final GL10 g, final int width, final int height, final Game game) {
        // FIX 18/08/12 Perhaps I need to reset the projection here.
        final int dashboardHeight = (int)(0.15 * height);
        final int timerHeight = (int)(0.08 * height);
        final int speedHeight = (int)(0.02 * height);

        final int mainHeight = height - dashboardHeight - timerHeight - speedHeight;

        final Box dashBox = new Box(0, 0, width, dashboardHeight);
        final Box speedBox = new Box(0, dashboardHeight, width, speedHeight);
        final Box mainBox = new Box(0, dashboardHeight + speedHeight, width, mainHeight);
        final Box timerBox = new Box(0, height - timerHeight, width, timerHeight);

        jags.draw(g, timerBox, timer, new DefaultColour(0.1f, 0.1f, 0.1f, 1.0f));
        jags.draw(g, dashBox, commands, new DefaultColour(0.3f, 0.3f, 0.3f, 1.0f));
        jags.draw(g, speedBox, speedo, new DefaultColour(1.0f, 0f, 0f, 1.0f));

        drawMain(g, game, mainBox);
    }

    private void drawMain(final GL10 g, final Game game, final Box mainBox) {
        final List<GameModel> mainModels = new ArrayList<GameModel>(game.sprites());
        mainModels.add(0, floor);
//        mainModels.add(0, headlight);
        mainModels.add(0, game.camera());
        jags.draw(g, mainBox, mainModels, Colours.BLACK);
    }

    private float farClip(final Game game) {
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