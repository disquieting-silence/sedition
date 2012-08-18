package dsq.sedition.view;

import android.opengl.GLU;
import dsq.sedition.core.Game;

import javax.microedition.khronos.opengles.GL10;

// FIX 18/08/12 Rename .
public class TopJaguar implements Jaguar {

    private final Jaguars jags = new DefaultJaguars();

    @Override
    public void onCommand(final Game game, final float left, final float top) {
    }

    @Override
    public void offCommand(final Game game) {
    }

    @Override
    public void onResize(final GL10 g, final int width, final int height, final Game game) {

    }

    @Override
    public void projection(final GL10 g, final int width, final int height, final Game game) {
        jags.projection(g, width, height, 100.0f);
    }

    @Override
    public void model(final GL10 g, final int width, final int height, final Game game) {

    }
}
