package dsq.sedition.view;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.core.Game;
import dsq.sedition.sprite.DefaultColour;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.util.Colours;

import javax.microedition.khronos.opengles.GL10;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// FIX 18/08/12 Rename .
public class TopJaguar implements Jaguar {

    public static final double COMMANDS_HEIGHT = 0.15;

    private Sprite commands;

    private final Jaguars jags = new DefaultJaguars();

    @Override
    public void onCommand(final Game game, final float left, final float top) {
    }

    @Override
    public void offCommand(final Game game) {
    }

    @Override
    public void onCreate(final GL10 g, final Context context, final Game game) {
        commands = new PanelSprite(new int[] { R.drawable.glob }, Colours.RED, 0, 0);
        commands.loadGLTexture(g, context);
    }

    @Override
    public void onResize(final GL10 g, final int width, final int height, final Game game) {
        jags.projection(g, width, height, 100.0f);
    }

    @Override
    public void onDraw(final GL10 g, final int width, final int height, final Game game) {
        final Box dashBox = new Box(0, 0, width, (int) (COMMANDS_HEIGHT * height));
        final Box mainBox = new Box(0, dashBox.height, width, height - dashBox.height);
        
        jags.draw(g, dashBox, commands, new DefaultColour(0.3f, 0.3f, 0.3f, 1.0f));
        drawMain(g, game, mainBox);
    }

    private void drawMain(final GL10 g, final Game game, final Box mainBox) {
        final List<GameModel> mainModels = new ArrayList<GameModel>(game.sprites());
        mainModels.add(0, game.camera());
        jags.draw(g, mainBox, mainModels, new DefaultColour(0.5f, 0.5f, 0.5f, 1.0f));
    }
}
