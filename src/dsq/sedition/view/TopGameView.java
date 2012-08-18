package dsq.sedition.view;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.core.Game;
import dsq.sedition.sprite.DefaultColour;
import dsq.sedition.sprite.Sprite;
import dsq.sedition.util.Colours;

import javax.microedition.khronos.opengles.GL10;
import java.util.ArrayList;
import java.util.List;

public class TopGameView implements GameView {

    public static final double COMMANDS_HEIGHT = 0.20;

    private Sprite commands;

    private final Views views = new DefaultViews();

    @Override
    public void onTouch(final Game game, final float left, final float top) {
        if (top >= 1.0 - COMMANDS_HEIGHT) {
            // FIX 18/08/12 Temporary
            if (left <= 0.5 ) {
                game.newLevel();
            } else {
                game.skipToGround();
            }

//
        }
    }

    @Override
    public void offTouch(final Game game) { }

    @Override
    public void moveTouch(final Game game, final float left, final float top) { }

    @Override
    public void onCreate(final GL10 g, final Context context, final Game game) {
        commands = new PanelSprite(new int[] { 
                R.drawable.generate, 
                R.drawable.black,
                R.drawable.confirm
        }, Colours.GREEN, 0.2f, 0.2f);
        commands.loadGLTexture(g, context);
    }

    @Override
    public void onResize(final GL10 g, final int width, final int height, final Game game) {
        views.projection(g, width, height, 100.0f);
    }

    @Override
    public void onDraw(final GL10 g, final int width, final int height, final Game game) {
        final Box dashBox = new Box(0, 0, width, (int) (COMMANDS_HEIGHT * height));
        final Box mainBox = new Box(0, dashBox.height, width, height - dashBox.height);

        views.draw(g, dashBox, commands, Colours.BLACK);
        drawMain(g, game, mainBox);
    }

    private void drawMain(final GL10 g, final Game game, final Box mainBox) {
        final List<GameModel> mainModels = new ArrayList<GameModel>(game.sprites());
        mainModels.add(0, game.camera());
        views.draw(g, mainBox, mainModels, new DefaultColour(0.5f, 0.5f, 0.5f, 1.0f));
    }
}
