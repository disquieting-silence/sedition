package dsq.sedition.view;

import android.content.Context;
import android.opengl.GLU;
import dsq.sedition.core.Game;
import dsq.sedition.options.Options;

import javax.microedition.khronos.opengles.GL10;

public class GroundJaguar implements Jaguar {
    
    private final Jaguars jags = new DefaultJaguars();
    
    @Override
    public void onCommand(final Game game, final float left, final float top) {
    }

    @Override
    public void offCommand(final Game game) {
    }

    @Override
    public void onCreate(final GL10 g, final Context context, final Game game) {
    }

    @Override
    public void onResize(final GL10 g, final int width, final int height, final Game game) {
        final float clip = farClip(game);
        jags.projection(g, width, height, clip);
    }

    @Override
    public void onDraw(final GL10 g, final int width, final int height, final Game game) {

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
