package dsq.sedition.view;

import android.opengl.GLU;
import dsq.sedition.core.Game;

import javax.microedition.khronos.opengles.GL10;

public class DefaultJaguars implements Jaguars {

    @Override
    public void projection(final GL10 g, final int width, final int height, final float farClip) {
        int h = height == 0 ? 1 : height;

        g.glViewport(0, 0, width, h);
        g.glMatrixMode(GL10.GL_PROJECTION);
        g.glLoadIdentity();

        final float aspect = (float) width / (float) h;
        GLU.gluPerspective(g, 90f, aspect, 0.1f, farClip);
    }
}
