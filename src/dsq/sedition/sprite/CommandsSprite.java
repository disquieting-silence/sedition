package dsq.sedition.sprite;

import android.content.Context;
import dsq.sedition.R;
import dsq.sedition.util.Textures;

import javax.microedition.khronos.opengles.GL10;

public class CommandsSprite implements Sprite {

    private final int [] textures = new int[3];

    private final ViewPanel view;

    public CommandsSprite() {
        view = new DefaultViewPanel(textures.length, new DefaultColour(1.0f, 1.0f, 1.0f, 1.0f), 0, 0);
    }

    @Override
    public void draw(final GL10 g) {
        view.draw(g, textures);
    }

    @Override
    public void loadGLTexture(final GL10 g, final Context context) {
        g.glGenTextures(textures.length, textures, 0);
        Textures.load(g, context, textures[0], R.drawable.back);
        Textures.load(g, context, textures[1], R.drawable.back);
        Textures.load(g, context, textures[2], R.drawable.back);
    }
}
