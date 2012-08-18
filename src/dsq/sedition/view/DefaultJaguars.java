package dsq.sedition.view;

import android.opengl.GLU;
import dsq.sedition.sprite.Colour;

import javax.microedition.khronos.opengles.GL10;
import java.util.Arrays;
import java.util.List;

public class DefaultJaguars implements Jaguars {

    @Override
    public void projection(final GL10 g, final int width, final int height, final float farClip) {
        int h = height == 0 ? 1 : height;

//        g.glViewport(0, 0, width, h);
        g.glMatrixMode(GL10.GL_PROJECTION);
        g.glLoadIdentity();

        final float aspect = (float) width / (float) h;
        GLU.gluPerspective(g, 90f, aspect, 0.1f, farClip);
    }

    @Override
    public void draw(final GL10 g, final Box box, final List<? extends GameModel> models, final Colour bg) {
        setBackground(g, box, bg);
        g.glViewport(box.x, box.y, box.width, box.height);
        for (GameModel model : models) {
            model.use(g);
        }
    }

    @Override
    public void draw(final GL10 g, final Box box, final GameModel model, final Colour bg) {
        draw(g, box, Arrays.asList(model), bg);
    }

    public void setBackground(final GL10 g, final Box box, final Colour bg) {
        g.glEnable(GL10.GL_SCISSOR_TEST);
        g.glScissor(box.x, box.y, box.width, box.height);
        g.glClearColor(bg.red(), bg.green(), bg.blue(), bg.alpha());
        g.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        g.glDisable(GL10.GL_SCISSOR_TEST);
    }
}
